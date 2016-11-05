package ie.nuig.runner;


import java.io.IOException;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;//Very important connect 2 columns and needs to be static



public class TwitterKMeans 
{

	public static void main(String[] args) throws IOException 
	{

		String path = "twitter2D.txt";
		
		SparkConf sparkConf = new SparkConf().setAppName("twitter_Dataset").setMaster("local[2]").set("spark.executor.memory", "2g");
		JavaSparkContext spark = new JavaSparkContext(sparkConf);
		
		//SparkSession allows to use the read method
		SparkSession sparkbuilder = SparkSession.builder().master("local").appName("twitter_SparkSession").getOrCreate();
		
		//"inferSchema", true figures out the type otherwise its all string causes all sorts of bad action
		Dataset<Row> dataset = sparkbuilder.read().format("csv").option("inferSchema", true).load(path);
		
		
		//Use VectorAssembler to change _c0,_c1 longitude  and latitude to Vectors because KMeans takes only Vectors as its parameter
		VectorAssembler va = new VectorAssembler().setInputCols(new String[] {"_c0","_c1"}).setOutputCol("features");
		
		//Transform Dataset using VectorAssembler so it adds feature colum with one vector from colums "_c0","_c1"
		Dataset<Row> vectorisedDataset = va.transform(dataset);
		
		//Using sql coalesce expression to concat two columns
		Dataset<Row> coalescedDataset  = vectorisedDataset.withColumn("c4_and_c5",coalesce(vectorisedDataset.col("_c5"),vectorisedDataset.col("_c4")));
	
		// Trains a k-means model.
		KMeans kmeans = new KMeans()
				.setK(5)//Set K nearest neighbor count 5 improved performance
				.setSeed(1L);
		
		KMeansModel model = kmeans.fit(coalescedDataset);
		
		
		//Add prediction for printing out tweets with cluster number
		Dataset<Row> vectorisedDatasetandPrediction = model.transform(coalescedDataset);
		
		//Printing out whole table
		coalescedDataset.show();
		
		
		//Printing out tweet and cluster number
		for (Row r : vectorisedDatasetandPrediction.select("c4_and_c5","prediction").collectAsList()) 
		{
			//Getting first String element and second element int cluster number 
			System.out.println("Tweets " + r.getString(0) +  " is in cluster "  + r.getInt(1));
		}
		
		//All done
		System.out.println("Fin");
		spark.stop();
		spark.close();


	}

}
