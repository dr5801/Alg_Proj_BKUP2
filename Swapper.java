package sharedClasses;
import java.util.ArrayList;

/**
 * Swapper class for swapping values
 * 
 * @author drew
 *
 */
public class Swapper 
{
	/**
	 * swaps the values at the given indexes in the list
	 * 
	 * @param listOfNumbers
	 * @param index1
	 * @param index2
	 */
	public static void swap(ArrayList<Integer> listOfNumbers, int index1, int index2)
	{
		int temp = listOfNumbers.get(index1);
		listOfNumbers.set(index1, listOfNumbers.get(index2));
		listOfNumbers.set(index2, temp);
	}
	
	/**
	 * swaps the values within an array
	 * 
	 * @param listOfNumbers
	 * @param index1
	 * @param index2
	 */
	public static void swap(int[] listOfNumbers, int index1, int index2)
	{
		int temp = listOfNumbers[index1];
		listOfNumbers[index1] = listOfNumbers[index2];
		listOfNumbers[index2] = temp;
	}
}
