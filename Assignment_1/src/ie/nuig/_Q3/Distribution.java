package ie.nuig._Q3;

import java.util.ArrayList;

public class Distribution 
{
	
private static ArrayList<Thread> sortedBuckets = new ArrayList<Thread>();//Making ArrayList of threads, good house keeping


    //Added extra parameter to take in  Helper interface to be used to sort bucket
	public int[] bucketSort(int[] numbers, int bucketCount,Helper helperSortFunction) throws InterruptedException 
	{
		
		if (numbers.length <= 1)
			return numbers;
		
		int maxVal = numbers[0];
		int minVal = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) 
		{
			if (numbers[i] > maxVal)
				maxVal = numbers[i];
			if (numbers[i] < minVal)
				minVal = numbers[i];
		}
		
		double interval = ((double) (maxVal - minVal + 1)) / bucketCount; // range
																			// of
																			// bucket
		ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
		for (int i = 0; i < bucketCount; i++) // Initialise buckets (initially
		{										// empty)
			buckets[i] = new ArrayList<Integer>();
			
		}
		
		for (int i = 0; i < numbers.length; i++) // distribute numbers to
													// buckets
			buckets[(int) ((numbers[i] - minVal) / interval)].add(numbers[i]);
		
		int k = 0;
		
		for (int i = 0; i < buckets.length; i++) 
		{
			//Making ThreadWorker helper and assigning bucket to it and passing in Helper interface object helperSortFunction
			ThreadWorker helper1 = new ThreadWorker(buckets[i],helperSortFunction);
			
			 //Creating Thread and adding it to sortedBuckets Thread ArrayList
			sortedBuckets.add(i, new Thread(helper1));
			
			//Starting all the Threads from the sortedBuckets ArrayList
			sortedBuckets.get(i).start();
		
			
		}
	
		
		for (int i = 0; i < buckets.length; i++) //t for threads
		{ 
			//Joining all the Threads which also sorts Bucket as hole unit against other buckets
			sortedBuckets.get(i).join(); 
			
			for (int j = 0; j < buckets[i].size(); j++) 
			{ // update array with
				
				numbers[k] = buckets[i].get(j);
				k++;
				
			}
			
		}
		
		return numbers;
		
	}

	
	
	

	
	

}
