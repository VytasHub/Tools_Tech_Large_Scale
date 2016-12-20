package ie.nuig._Q2;

import java.util.ArrayList;


import ie.nuig._Q2.ThreadWorker;

public class DistributionSorting 
{
	private static ArrayList<Thread> sortedBuckets = new ArrayList<Thread>();//Making ArrayList of threads, good house keeping


	 
		public int[] bucketSort(int[] numbers, int bucketCount) throws InterruptedException 
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
			for (int i = 0; i < bucketCount; i++) // initialize buckets (initially
			{										// empty)
				buckets[i] = new ArrayList<Integer>();
				//sortedBuckets.set(i, null);
			}
			
			for (int i = 0; i < numbers.length; i++) // distribute numbers to
														// buckets
				buckets[(int) ((numbers[i] - minVal) / interval)].add(numbers[i]);
			
			int k = 0;
			
			for (int i = 0; i < buckets.length; i++) 
			{
				ThreadWorker helper = new ThreadWorker(buckets[i]);//Making ThreadWorker helper and assigning bucket to it
				
				sortedBuckets.add(i, new Thread(helper));  //Creating Thread and adding it to sortedBuckets Thread ArrayList
				
				sortedBuckets.get(i).start();// Starting all the Threads from the sortedBuckets ArrayList
				
			}
		
			for (int i = 0; i < buckets.length; i++) //t for threads
			{ 
				sortedBuckets.get(i).join();//Joining all the Threads joins only when threads are finished
				
				for (int j = 0; j < buckets[i].size(); j++) 
				{ // update array with
					
					numbers[k] = buckets[i].get(j);
					k++;
				}
				
			}
			
			return numbers;
			
		}

		
		
		

		

}
