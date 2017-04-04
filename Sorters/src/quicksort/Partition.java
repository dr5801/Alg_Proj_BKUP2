package quicksort;
import java.util.ArrayList;

import sharedClasses.Swapper;

/**
 * Stores the ArrayList listOfNumbers, start, and end of the partition
 * 
 * @author drew
 *
 */
public class Partition 
{
	private int start;
	private int end;
	private ArrayList<Integer> numbersToSort;
		
	/**
	 * partitions the array passed in based on the Dutch Flag 3-way partition
	 * 
	 * @param numbersToSort
	 * @param startPosition
	 * @param endPosition
	 * @param endPosition 
	 */
	public ArrayList<Integer> partitionList(ArrayList<Integer> numbersToSort, int lowerEnd, int upperEnd, int pivot) 
	{
		this.start = lowerEnd;
		int n = upperEnd;
		this.end = lowerEnd;
		
		while(this.end <= n)
		{
			if(numbersToSort.get(this.end) < pivot)
			{
				Swapper.swap(numbersToSort, this.start++, this.end++);
			}
			else if(numbersToSort.get(this.end) > pivot)
			{
				Swapper.swap(numbersToSort, this.end, n--);
			}
			else
			{
				this.end++;
			}
		}
		
		this.numbersToSort = numbersToSort;
		return this.numbersToSort;
	}
	
	/**
	 * @return numbersToSort
	 */
	public ArrayList<Integer> getNumbersToSort()
	{
		return this.numbersToSort;
	}
	
	/**
	 * @return start
	 */
	public int getStart()
	{
		return this.start;
	}
	
	/**
	 * @param start
	 */
	public void setStart(int start)
	{
		this.start = start;
	}
	
	/**
	 * @return end
	 */
	public int getEnd()
	{
		return this.end;
	}
	
	/**
	 * @param end
	 */
	public void setEnd(int end)
	{
		this.end = end;
	}
}
