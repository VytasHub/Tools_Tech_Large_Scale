package ie.nuig.randarray;

import java.util.Random;

public class RandomizeArray 
{
	
	
	//Takes in array and creates random element in specified range to avoid duplication
	public int[] rndArray(int[] anArray,int rndRange) 
	{
		Random rand = new Random();
		int  n;
		for(int i=0;i<anArray.length;i++)
		{
		    n = rand.nextInt(rndRange) + 1;
		    anArray[i] = n;
		}
		return anArray;
	
	}
}
