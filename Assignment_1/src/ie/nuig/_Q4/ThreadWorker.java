package ie.nuig._Q4;

import java.util.ArrayList;




public class ThreadWorker  implements Runnable 
{
	
	private ArrayList<Integer> list = new ArrayList<Integer>(); //Define ArrayList so Thread can read it in
	private Helper method;//Creates Helper object that has sorter method
	

	

	public ThreadWorker(ArrayList<Integer> list, Helper method) 
	{
		super();
		this.list = list;
		this.method = method;
	}

	public void run() //Defining what Thread is doing by passing list to method Object applying CollectionsSort method
	{
		
		method.CollectionsSort(list);
		
	}
	
	public ArrayList<Integer> getList() 
	{
		return list;
	}
	

	
	


	
	
	
	

}
