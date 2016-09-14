package ie.nuig.runner;

import java.util.Arrays;
import java.util.Random;

import ie.nuig.search.SearchAlgo;

public class RunnerClass 
{

	public static void main(String[] args) 
	{
		Random rand = new Random();
		
		  int[] anArray = new 	int[20];
		  int  n;
		    	for(int i=0;i<anArray.length;i++)
		    	{
		    		n = rand.nextInt(50) + 1;
		    		anArray[i] = n;
		    	}

		    	System.out.println(Arrays.toString(anArray));
		   
		
		
		
		SearchAlgo s = new SearchAlgo();
		int[] returnedArray = new 	int[20];
		returnedArray = s.bucketSort(anArray, 20);
		
		System.out.println(Arrays.toString(returnedArray));
		
	}
	
	
		  
		

}
