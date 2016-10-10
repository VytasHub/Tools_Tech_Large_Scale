package ie.nuig.stattion;

import java.awt.List;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;



public class Measurement 
{
	private Integer time;
	private static double temperature;
	private ArrayList<Measurement> Measurement = new ArrayList<Measurement>();
	
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
	
	static Double averageTemperature(ArrayList<Measurement> bl,double startTime,double endTime) 
	{ 
		
		Stream<Measurement> MeasurementStream = bl.stream();  
		
		Stream<Measurement> filteredMeasurementsStream = MeasurementStream
				.filter((Measurement measurement) -> startTime >= measurement.temperature && measurement.temperature <= endTime); 
		
		DoubleStream MeasurementtemperatureStream = filteredMeasurementsStream.mapToDouble(Measurement -> Measurement.temperature); 
				
		return MeasurementtemperatureStream.sum(); 
		
	}
	
	
}
