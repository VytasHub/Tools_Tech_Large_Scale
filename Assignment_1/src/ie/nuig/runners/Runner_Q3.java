package ie.nuig.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ie.nuig._Q3.*; //Importing Q3 package
import ie.nuig.randarray.RandomizeArray;





public class Runner_Q3 
{
	
	static Helper myHelperSortFn  = new Helper()  //Creating Interface Object
	{
		public void CollectionsSort(ArrayList<Integer> list) //Making Anonymous class
		{
			Collections.sort(list);  //Defining method functionality calling Collections.sort on list
		}
	};
	
	
	
	public static void main(String[] args) throws InterruptedException 
	{
		int[] anArray = new 	int[20];
		
		RandomizeArray rnd = new RandomizeArray();  //Making RandomizeArray Object
		rnd.rndArray(anArray, 50);					 //Allocating a random in between 1  and 50 to all arrays elements
		
		System.out.println("=== Q3 ====");
		
		System.out.println("Unsorted Array: " + Arrays.toString(anArray));//Printing out unsorted Array
	
		Distribution go = new Distribution();			 //Making Distribution Object
		anArray = go.bucketSort(anArray,4,myHelperSortFn);//Providing Arguments anArray and buckets size and passing in sorting Function
		
		System.out.println("Sorted   Array: " + Arrays.toString(anArray));//Printing out sorted Array
			
	}	

}