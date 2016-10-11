package ie.nuig.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ie.nuig._Q4.*; //Importing Q4 package
import ie.nuig.randarray.RandomizeArray;

public class Runner_Q4 
{
	
	static Helper LambdaSort = (ArrayList<Integer> list) -> Collections.sort(list); //Declaring Lambda Function passing in list and calling  Collections.sort() on it


	public static void main(String[] args) throws InterruptedException 
	{
		int[] anArray = new 	int[20];
		
		RandomizeArray rnd = new RandomizeArray();  //Making RandomizeArray Object
		rnd.rndArray(anArray, 50);					//Allocating a random in between 1  and 50 to all arrays elements
		
		System.out.println("=== Q4 ====");
		
		System.out.println("Unsorted Array: " + Arrays.toString(anArray));//Printing out Unsorted array
	
		Distribution go = new Distribution();
		anArray = go.bucketSort(anArray,4,LambdaSort);//Providing Arguments anArray and buckets size and passing in lambda expression
		
		System.out.println("Sorted   Array: " + Arrays.toString(anArray));//Printing out sorted Array
			
	}
	
}
