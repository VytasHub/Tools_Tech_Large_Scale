package ie.nuig.stattion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;

import scala.Tuple2;



public class WeatherStation  implements java.io.Serializable
{

	//Declaring variables
	private String city;
	private  ArrayList<Measurement> Measurement = new ArrayList<Measurement>();//Declaring ArrayList of Measurements objects (time, temperature) which belong to each station
	private static ArrayList<WeatherStation> stations = new ArrayList<WeatherStation>(); //Making Static so it belongs to class instead of object
	
	

	public WeatherStation(String city, ArrayList<ie.nuig.stattion.Measurement> Measurement) 
	{
		this.city = city;
		this.Measurement = Measurement;
		stations.add(this);// Every time you make new weather station it will get added to this list
	}
	
	//Simple Getters and Setters
	public String getCity() 
	{
		return city;
	}


	public void setCity(String city) 
	{
		this.city = city;
	}


	public ArrayList<Measurement> getMeasurement() 
	{
		return Measurement;
	}


	public void setMeasurement(ArrayList<Measurement> measurement) 
	{
		Measurement = measurement;
	}


	public static ArrayList<WeatherStation> getStation() 
	{
		return stations;
	}


	public static void setStation(ArrayList<WeatherStation> stations) 
	{
		WeatherStation.stations = stations;
	}
	
	public Double averageTemperature(double startTime,double endTime) 
	{ 
		
		double answer = 0;
		
		return answer;
		
	}
	
	public long countTemperature(double temp) 
	{ 
		double lowerBound  = temp -1; //Creating lower Bound
		double higherBound = temp +2; //Creating upper bound because we subtracted 1 we add 2
		System.out.println("Stations"+stations);//See that stations are getting populated
		System.out.println(" ");
		
		Stream<Double> mesStream = this.stations.parallelStream()        //Creating Stream from Stations which contains Measurement Objects 
					.map(stations ->  stations.getMeasurement()) //Map stations to Measurement
					.flatMap(Measurement -> Measurement.parallelStream())//Make Stream of Measurement because its An ArrayList of Objects
					.filter(tempStream ->  tempStream.getTemperature() >= lowerBound  && tempStream.getTemperature() <= higherBound)//Filtering all measurements to meet all values between 18 to 20
					.map(Measurement ->  Measurement.getTemperature());//Mapping Measurements
			
		return mesStream.count();//Returning Stream count the amount of values that satisfied the filter
		
	}
	
	public Integer countTemperatureRDD(double temp) 
	{ 
		//line is weather station
		double answer = 0;
		
		double lowerBound  = temp -1; //Creating lower Bound
		double higherBound = temp +2; //Creating upper bound because we subtracted 1 we add 2
		
		
		//Instantiating sparkConf and seting the properties
		SparkConf sparkConf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory","2g");
		
		//Passing in sparkConf to sc
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		
		//Parallelizing  rddStations
		JavaRDD<WeatherStation> rddStations = sc.parallelize(stations);
		
		//Iterating throw all Measurements objects and flat mapping
		JavaRDD<Measurement> stationsMeasurments = rddStations.flatMap(flat ->  flat.getMeasurement().iterator());
		
		//Filtering RDD on range specified
		JavaRDD<Measurement> stationsfiltered = stationsMeasurments.filter(tempstation ->  tempstation.getTemperature() >= lowerBound  && tempstation.getTemperature() <= higherBound);
		
		//Mapping Measurement to value 1
		JavaPairRDD<Double, Integer> maped = stationsfiltered.mapToPair((Measurement s) -> new Tuple2<Double, Integer>(temp, 1));//Small Issue here should not be passing temp but s
		
		//Reducing Measurements
		JavaPairRDD<Double, Integer> counts = maped.reduceByKey((Integer i1, Integer i2) -> i1 + i2);
		
		//Materializing it RDD by calling collect
		List<Tuple2<Double, Integer>> output = counts.collect();
		
		//Accessing index 0 of attribute 2
		Integer tempcount = output.get(0)._2;
		
		//Returning amount of Temperatures within range
		return tempcount;
	
	}
	
	
	//For readability purposes
	@Override
	public String toString() 
	{
		return "WeatherStation [city=" + city + ", Measurement=" + Measurement + "]";
	}
	
	


	
	
	
	

	
	
	
	

}
