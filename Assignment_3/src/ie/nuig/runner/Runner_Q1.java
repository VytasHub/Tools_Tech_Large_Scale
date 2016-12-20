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
		Measurement reading7 = new Measurement(1700,19.2);
		
		
		
		//Adding Measurements to Berlin station
		mesearumentsBerlin.add(reading0);
		mesearumentsBerlin.add(reading1);
		mesearumentsBerlin.add(reading2);
		mesearumentsBerlin.add(reading3);
		
		//Adding Measurements to Paris station
		mesearumentsParis.add(reading4);
		mesearumentsParis.add(reading5);
		mesearumentsParis.add(reading6);
		mesearumentsParis.add(reading7);
		
		
		
		WeatherStation s1 = new WeatherStation("Berlin", mesearumentsBerlin);//Printing out for verification purposes
		WeatherStation s2 = new WeatherStation("Paris", mesearumentsParis);  //Printing out for verification purposes
		
	
	
		//Getting all the temperatures within range provided +/- 1 each station can access all other stations measurements via static stations ArrayList
		//We can call countTemperature() method on any Station Object
		System.out.println("Stream +/- 1 is: " + s1.countTemperature(19));
		
		
		//Calling countTemperatureRDD using RDDs
		System.out.println("RDD    +/- 1 is: " + s1.countTemperatureRDD(19));
		
		
		System.out.println("FIN");
		
		
		
		

	}

}
