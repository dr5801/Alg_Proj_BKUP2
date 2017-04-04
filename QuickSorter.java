package quicksort;
import java.util.ArrayList;

import sharedClasses.Swapper;

/**
 * 
 * @author Drew Rife
 * 
 * Class for quicksorting
 *
 */
public class QuickSorter 
{
	/**
	 * quick sorts a list
	 * 
	 * @param numbersToSort
	 * @param upperEnd 
	 * @param lowerEnd 
	 * @param outputFile
	 */
	public ArrayList<Integer> quickSort(ArrayList<Integer> numbersToSort, int lowerEnd, int upperEnd)
	{
		if(lowerEnd < upperEnd)
		{
			/* get the pivot value */
			int pivot = getPivot(numbersToSort, lowerEnd, upperEnd);
			
			/* create a new partition for the past in list */
			Partition partition = new Partition();
			ArrayList<Integer> listOfNumbers = partition.partitionList(numbersToSort, lowerEnd, upperEnd, pivot);
			
			/* recursively sort the list */
			quickSort(listOfNumbers, partition.getEnd(), upperEnd);
			quickSort(listOfNumbers, lowerEnd, partition.getStart()-1);
		}
		return numbersToSort;
	}

	/**
	 * gets a pivot value by taking the median of 3 randomly chose values from the list
	 * @param numbersToSort
	 * @return the pivot value
	 */
	public int getPivot(ArrayList<Integer> numbersToSort, int lowerEnd, int upperEnd) 
	{
		int medianIndex = (upperEnd - lowerEnd)/2 + lowerEnd;
		ArrayList<Integer> threeValues = new ArrayList<Integer>();
		threeValues.add(numbersToSort.get(lowerEnd));
		threeValues.add(numbersToSort.get(medianIndex));
		threeValues.add(numbersToSort.get(upperEnd));
		
		/* sort the 3 values to have the medium index at position 1 between 0..2 */
		if(threeValues.get(0) > threeValues.get(1)) 
			Swapper.swap(threeValues, 0, 1);
		
		if(threeValues.get(1) > threeValues.get(2)) 
			Swapper.swap(threeValues, 1, 2);
		
		if(threeValues.get(0) < threeValues.get(1)) 
			return threeValues.get(1);
		else
			return threeValues.get(0);
	}
}
