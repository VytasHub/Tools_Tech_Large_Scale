package ie.nuig.stattion;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;



public class WeatherStation 
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
		
		Stream<Measurement> mesStream = this.Measurement.stream();//Making Stream from Measurement ArrayList
		
		Stream<Measurement> filteredMeasurementsStream = mesStream 
				.filter((Measurement measurement) -> measurement.getTime() >= startTime  && measurement.getTime() <= endTime ); //Filtering stream depending on Time provided
		
		DoubleStream MeasurementtemperatureStream = filteredMeasurementsStream.mapToDouble(Measurement -> Measurement.getTemperature()); //Maps to Double 
				
		return MeasurementtemperatureStream.sum()/Measurement.size(); //Adds all values in the stream than divides by the amount of values in stream to get average
		
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


	//For readability purposes
	@Override
	public String toString() 
	{
		return "WeatherStation [city=" + city + ", Measurement=" + Measurement + "]";
	}
	
	


	
	
	
	

	
	
	
	

}
