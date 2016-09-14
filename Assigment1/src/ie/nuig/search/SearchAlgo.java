package ie.nuig.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SearchAlgo
{
	

	public static int[] bucketSort(int[] numbers, int bucketCount) 
	{
		System.out.println("Unsorted Array: " + Arrays.toString(numbers));  //Unsorted
		
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
												// empty)
			buckets[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < numbers.length; i++) // distribute numbers to
													// buckets
			buckets[(int) ((numbers[i] - minVal) / interval)].add(numbers[i]);
		
		int k = 0;
		
		for (int i = 0; i < buckets.length; i++) 
		{
			Collections.sort(buckets[i]); // calls Java's built-in merge sort
											// (as a kind of �helper� sort)
			for (int j = 0; j < buckets[i].size(); j++) 
			{ // update array with
															// the bucket
															// content
				numbers[k] = buckets[i].get(j);
				k++;
			}
		}
		System.out.println("Sorted Array:   " + Arrays.toString(numbers)); //Sorted
		return numbers;
	}
	
	

}
