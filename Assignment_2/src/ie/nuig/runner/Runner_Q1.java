package ie.nuig.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ie.nuig.stattion.Measurement;
import ie.nuig.stattion.WeatherStation;

public class Runner_Q1 
{

	public static void main(String[] args) 
	{
		
		ArrayList<Measurement> mesearumentsBerlin = new ArrayList<Measurement>();//Making ArrayList of Measurements objects for Berlin Station
		ArrayList<Measurement> mesearumentsParis = new ArrayList<Measurement>(); //Making ArrayList of Measurements objects for Paris Station
		
		
		System.out.println("===Q1===");
		//Declaring Measurements Objects
		Measurement reading0 = new Measurement(1100,20);
		Measurement reading1 = new Measurement(1200,11.7);
		Measurement reading2 = new Measurement(1300,-5.4);
		Measurement reading3 = new Measurement(1400,18.7);
		
		Measurement reading4 = new Measurement(1500,8.4);
		Measurement reading5 = new Measurement(1600,19.2);
		Measurement reading6 = new Measurement(1700,7.2);
		
		
		//Adding Measurements to Berlin station
		mesearumentsBerlin.add(reading0);
		mesearumentsBerlin.add(reading1);
		mesearumentsBerlin.add(reading2);
		mesearumentsBerlin.add(reading3);
		
		//Adding Measurements to Paris station
		mesearumentsParis.add(reading4);
		mesearumentsParis.add(reading5);
		mesearumentsParis.add(reading6);
		
		
		
		WeatherStation s1 = new WeatherStation("Berlin", mesearumentsBerlin);//Printing out for verification purposes
		WeatherStation s2 = new WeatherStation("Paris", mesearumentsParis);  //Printing out for verification purposes
		
		//Printing out Stations to see they contain all the info
		System.out.println("Berlin " + s1.toString());
		System.out.println("Paris " + s2.toString());
		System.out.println(" ");
		
		
		
		System.out.println("  ");
		//Getting Average temperature within given time range in this case all values meet time criteria
		System.out.println("Avg Berlin: " + s1.averageTemperature(1000, 1800));
		System.out.println("Avg Paris : " + s2.averageTemperature(1000, 1800));
		System.out.println("  ");

	
		
		
	

	}

}
