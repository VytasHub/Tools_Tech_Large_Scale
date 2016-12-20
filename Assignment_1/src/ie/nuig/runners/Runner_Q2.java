package ie.nuig.runners;

import java.util.Arrays;

import ie.nuig._Q2.DistributionSorting; //Importing Q2 package
import ie.nuig.randarray.RandomizeArray;

public class Runner_Q2 
{

	public static void main(String[] args) throws InterruptedException 
	{
		int[] anArray = new 	int[20];
		
		RandomizeArray rnd = new RandomizeArray();   //Making RandomizeArray Object
		rnd.rndArray(anArray, 50);					 //Allocating a random in between 1  and 50 to all arrays elements
		
		System.out.println("=== Q2 ====");
		System.out.println("Unsorted Array: " + Arrays.toString(anArray)); //Printing out Unsorted array
		
		DistributionSorting go = new DistributionSorting(); //Making DistributionSorting Object
		anArray = go.bucketSort(anArray,4);					 //Providing Arguments anArray and buckets size
		
		System.out.println("Sorted   Array: " + Arrays.toString(anArray));//Printing out sorted Array
		
	}

}
