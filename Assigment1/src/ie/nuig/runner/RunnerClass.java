package ie.nuig.runner;

import java.util.Arrays;
import java.util.Random;


import ie.nuig.Threads.ThreadWorker;
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

		    	
		   
		
		
		
		//SearchAlgo s = new SearchAlgo();
		
		//s.bucketSort(anArray, 1);
		    	
		    	Thread t3 = new Thread(new ThreadWorker(anArray,2));
				
		    	t3.start();
		
		
		
		 
		
		
	}
	
	
		  
		

}
