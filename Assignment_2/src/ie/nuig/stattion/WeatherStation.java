package ie.nuig.stattion;

import java.util.ArrayList;

public class WeatherStation 
{
	
	private String city;
	private ArrayList<String> Measurement = new ArrayList<String>();
	private static ArrayList<String> stations = new ArrayList<String>();
	
	
	public WeatherStation(String city, ArrayList<String> measurement) 
	{
		super();
		this.city = city;
		Measurement = measurement;
	}
	
	
	public static ArrayList<String> getStations() 
	{
		return stations;
	}
	public static void setStations(ArrayList<String> stations) 
	{
		WeatherStation.stations = stations;
	}
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public ArrayList<String> getMeasurement() 
	{
		return Measurement;
	}
	public void setMeasurement(ArrayList<String> measurement) 
	{
		Measurement = measurement;
	}
	

}
