package ie.nuig.runner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import scala.Tuple2;



public class Runner_Q2_Preprocessing_No 
{

	public static void main(String[] args) throws IOException 
	{
		//Area under ROC  = 0.7268718935385597
		String path = "imdb_labelled.txt";

		//sparkConf using 4 cores 
		SparkConf sparkConf = new SparkConf().setAppName("SpamClassifierUsingRDDs").setMaster("local[2]").set("spark.executor.memory", "2g");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
	
		//Getting the path
		JavaRDD<String> file = sc.textFile(path);
		
		//Hashing increasing number of buckets to avoid collisions increase ROC
		final HashingTF tf = new HashingTF(10000);//To avoid collisions number is increased 

		//Reading in one long String and splitting into Strings with numbers by next line
		JavaRDD<String> lines = file.flatMap(flat -> Arrays.asList(flat.split("\n")).iterator());
		
		//Splits Sentences into Sentence and a number by tab
		JavaRDD<List<String>> linesNumbers = lines.map(flat -> Arrays.asList(flat.split("\t")));
		
		//Making Tuple String Integer and doing .toLowerCase() and .replaceAll("[^a-z]", " ") that just allows a-z excluding everything else
		JavaRDD<Tuple2<String, Integer>> mapedlinesNumbers = linesNumbers.map((listobject) -> new Tuple2<String, Integer>(listobject.get(0),Integer.parseInt(listobject.get(1))));//Have my Tuple action in action
		
		//Splitting by second element of the tuple s._1 because its a string , Labeling data to be used for SVM
		JavaRDD<LabeledPoint> labeledMapedlinesNumbers = mapedlinesNumbers.map(tup -> new LabeledPoint(tup._2, tf.transform(Arrays.asList(tup._1.split(" ")))));
		
		
		//=====Spark SVM=====
		//Dividing data 60% training 40% testing
		JavaRDD<LabeledPoint> training = labeledMapedlinesNumbers.sample(false, 0.6, 11L);
		
		//Caching locally
		training.cache();
		
		//Assigning test data to RDD
		JavaRDD<LabeledPoint> test = labeledMapedlinesNumbers.subtract(training);

		//Defining number of Iteration 1000 , accuracy increases to 1000 iterations no accuracy gains beyond a 1000
		int numIterations = 1000;
		
		//Building mode using 60% of data
		final SVMModel model = SVMWithSGD.train(training.rdd(), numIterations);

		//Clears Threshold
		
		
		//Originally Anonymous class transformed to lambda expression
		JavaRDD<Tuple2<Object, Object>> scoreAndLabels = test.map(p -> 
		{
		  Double score = model.predict(p.features());
		  return new Tuple2<Object, Object>(score, p.label());
		}
		);
		
		//Clears Threshold
		model.clearThreshold();

		//Instance metrics object to get ROC value
		BinaryClassificationMetrics metrics = new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
		
		//Calling areaUnderROC() to metrics object
		double auROC = metrics.areaUnderROC();
		
		//Print labels sentiments
		System.out.println(scoreAndLabels.take(6));
		
		
		//Printing out ROC
		System.out.println("Area under ROC  = " + auROC);
		//=====Spark SVM=====		
		
	
		System.out.println("FIN");
		

	}

}
