package ie.nuig.stattion;


import java.util.ArrayList;




public class Measurement 
{
	

	private Integer time;
	private double temperature;
	private ArrayList<Measurement> Measurement = new ArrayList<Measurement>(); //Declaring Measurement array list so each station can have multiply measurements objects
	
	
	public Measurement(Integer time, double temperature) //Constructor
	{
		this.time = time;
		this.temperature = temperature;
		
	}
	
	//Simple Getters Setters
	public Integer getTime() 
	{
		return time;
	}

	public void setTime(Integer time) 
	{
		this.time = time;
	}

	public double getTemperature() 
	{
		return temperature;
	}

	public void setTemperature(double temperature) 
	{
		this.temperature = temperature;
	}
	
	
	//For readability purposes
	@Override
	public String toString() 
	{
		return "Measurement [time=" + time + ", temperature=" + temperature + "]";
	}
	
	
}
