package ie.nuig.stattion;

import java.util.ArrayList;

public class WeatherStation 
{

	private String city;
	private ArrayList<Measurement> Measurement = new ArrayList<Measurement>();
	private static ArrayList<WeatherStation> station = new ArrayList<WeatherStation>();
	

	public WeatherStation(String city, ArrayList<ie.nuig.stattion.Measurement> measurement) 
	{
		super();
		this.city = city;
		Measurement = measurement;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public ArrayList<Measurement> getMeasurement() {
		return Measurement;
	}


	public void setMeasurement(ArrayList<Measurement> measurement) {
		Measurement = measurement;
	}


	public static ArrayList<WeatherStation> getStation() {
		return station;
	}


	public static void setStation(ArrayList<WeatherStation> station) {
		WeatherStation.station = station;
	}
	
	
	
	

}
