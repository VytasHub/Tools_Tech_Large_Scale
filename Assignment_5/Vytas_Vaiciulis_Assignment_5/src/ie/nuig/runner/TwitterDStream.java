package ie.nuig.runner;

import java.io.IOException;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import java.util.Arrays;
import scala.Tuple2;
import org.apache.spark.streaming.twitter.*;
import com.google.common.io.Files;
import twitter4j.Status;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class TwitterDStream {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		
		
		//Defining Twiters connections strings 
		final String consumerKey = "TMtBheUGEcSkV0CxtbBZtRttY";
		final String consumerSecret = "oZ9hGX29P0a3B6BJRzc3G6o6ONOGXEHSuKS23C4dSociZGFgA3";
		final String accessToken = "1413799009-huWjwXOCZIuz75WiQJd5qJTubhrlt12TqBZyaDj";
		final String accessTokenSecret = "hcK8sqVBaEhMeQqwWM0nMnSfg0YVlN7tQbqOEWkEVOdWk";

		//Spark connection supplying sparkConf object with info
		SparkConf sparkConf = new SparkConf().setAppName("twitter_Dataset").setMaster("local[2]")
				.set("spark.executor.memory", "2g");

		//Calling jssc every 3 seconds
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(3000));
		
		//Fixing an error
		jssc.checkpoint(Files.createTempDir().getAbsolutePath());

		//Supplying Twiter credentials for connection
		System.setProperty("twitter4j.oauth.consumerKey",consumerKey);
		System.setProperty("twitter4j.oauth.consumerSecret",consumerSecret);
		System.setProperty("twitter4j.oauth.accessToken",accessToken);
		System.setProperty("twitter4j.oauth.accessTokenSecret",accessTokenSecret);
		
		
		//Creating first DStream
		JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(jssc);

		//JavaDStream for average tweets 
		JavaDStream<String> avg = twitterStream.map(new Function<Status, String>() 
		{
			double total = 0;
			double tweetCount = 0;
			double avgcount = 0;
			
			//To count the avg of a tweet addition division  and if statement is used which all are Constant time ruining operations
			public String call(Status status)
			{
				String avg = " ";
				String  info;
				info = status.getText();
				Arrays.asList(info.split(" ")).size();
				total = total + Arrays.asList(info.split(" ")).size();
				tweetCount++;
				if(tweetCount==10)
				{
					avg = null;
					avgcount = total/tweetCount;
					avg = " Average " + avgcount;
					tweetCount = 0;	
				}
				
				info = info + " --Count:" + Arrays.asList(info.split(" ")).size() + " Total:" + total + avg;
				
				return info;
			}
			
		});
		
		//All Anonymous classes has been turned in to Lambda expressions a nice feature of Java 8 makes it shorter and more readable
		//Gets all twitter text
		JavaDStream<String> statuses = twitterStream.map(status -> status.getText());

		//Splits all words into array elements
		JavaDStream<String> words = statuses.flatMap(in -> Arrays.asList(in.split(" ")).iterator());

		//Detects all words that start with #
		JavaDStream<String> hashTags = words.filter(word -> word.startsWith("#"));
		
		//Puts hastag word in tuple to count its frequency
		JavaPairDStream<String, Integer> tuples = hashTags.mapToPair(in -> new Tuple2<String, Integer>(in, 1));

		//Calculates top hastags every 3 minutes
		JavaPairDStream<String, Integer> counts = tuples
				.reduceByKeyAndWindow((Function2<Integer, Integer, Integer>) 
						(i1, i2) -> i1 + i2, (i1, i2) -> i1 - i2, new Duration(60 * 3 * 1000), new Duration(15000));

		//Swaps type the order of the tuples
		JavaPairDStream<Integer, String> swappedCounts = counts.mapToPair(in -> in.swap());
		
		//Organizing 10 hastags in order
		JavaPairDStream<Integer, String> sortedCounts = swappedCounts
				.transformToPair((Function<JavaPairRDD<Integer, String>, JavaPairRDD<Integer, String>>) in -> in.sortByKey(false));
		
		//Prints out 10 hashtags
		sortedCounts.foreachRDD((VoidFunction<JavaPairRDD<Integer, String>>) rddStream -> {
			String out = "\n Top 10 hashtags:\n";
			for (Tuple2<Integer, String> t : rddStream.take(10)) 
			{
				out = out + t.toString() + "\n";
			}
			System.out.println(out);
		});
		
		//Disabling all warnings very distracting
		Logger logger = Logger.getRootLogger();
		logger.setLevel(Level.ERROR);
		
		//Printing out average
		avg.print();
		
		//Printing out hastags out top hashtags over last 3 minutes as well as top 10 hastags
		sortedCounts.print();
		
		//Starts the tweet stream and awaitTermination waits termination so keeps the tweets going
        jssc.start();
        jssc.awaitTermination();

		System.out.println("Fin");
	
	}

}
