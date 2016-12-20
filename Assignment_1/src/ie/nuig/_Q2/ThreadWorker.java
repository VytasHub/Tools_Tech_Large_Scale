package ie.nuig._Q2;

import java.util.ArrayList;
import java.util.Collections;

import ie.nuig._Q3.Helper;


public class ThreadWorker  implements Runnable 
{
	
	private ArrayList<Integer> list = new ArrayList<Integer>(); //Creating ArrayList so Thread is able to take in a bucket
	

	public ThreadWorker(ArrayList<Integer> list) //Constructor
	{
		this.list = list;
	}

	public void run() //Defining what Thread is doing in case sorting using Collections.sort();	
	{
		Collections.sort(list);
	}
	
	public ArrayList<Integer> getList() //Gets sorted List
	{
		return list;
	}
	
	
	
}
