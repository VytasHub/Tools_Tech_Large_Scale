package ie.nuig.stattion;

import java.awt.List;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;



public class Measurement 
{
	private Integer time;
	private static double temperature;
	
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
	
	static Double averageTemperature(ListMeasurement bl, Integer y) 
	{ 
		
		Stream<Measurement> MeasurementStream = bl.stream();  
		
		Stream<Measurement> filteredMeasurementsStream = MeasurementStream
				.filter((Measurement book) -> startTime >= Measurement.temperature && Measurement.temperature <= endTime); 
		
		DoubleStream MeasurementtemperatureStream = filteredMeasurementsStream.mapToDouble(Measurement -> Measurement.temperature); 
				
		return MeasurementtemperatureStream.sum(); 
		
	}
	
	
}
