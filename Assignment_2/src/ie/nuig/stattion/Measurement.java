package ie.nuig.stattion;

public class Measurement 
{
	private Integer time;
	private double temperature;
	
	public Measurement(Integer time, double temperature) 
	{
		super();
		this.time = time;
		this.temperature = temperature;
	}
	
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
	
	
}
