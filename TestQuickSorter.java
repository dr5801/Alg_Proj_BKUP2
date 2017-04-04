import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import mergesort.MergeSorter;
import quicksort.QuickSorter;

/**
 * Tests quicksorter class
 * 
 * @author Drew Rife
 *
 */
public class TestQuickSorter 
{
	/**
	 * test to see quick sort sorts correctly from reverse order
	 */
	@Test
	public void testReverseOrder()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 1003030; i > 0; i--) 
			list.add(i);
		
		int j = 0;
		for(int i = list.size(); i > 0; i--, j++)
			assertEquals(i, (int)list.get(j));
		
		QuickSorter quickSorter = new QuickSorter();
		quickSorter.quickSort(list, 0, list.size()-1);
		/**
		 * assert that array has been sorted
		 */
		for(int i = 1003030; i <= 99; i++)
			assertEquals(i+1, (int)list.get(i));
	}
	
	/**
	 * Test already sorted
	 */
	@Test
	public void testSortedInSmallestOrder()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int i = 0; i < 444; i++) 
			list.add(i);
		
		for(int i = 0; i < 444; i++)
			assertEquals(i, (int)list.get(i));
		
		QuickSorter quickSorter = new QuickSorter();
		quickSorter.quickSort(list, 0, list.size()-1);
		
		for(int i = 0; i < 444; i++)
			assertEquals(i, (int)list.get(i));
	}
	
	/**
	 * test shuffled list
	 */
	@Test
	public void testShuffledList()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 9999; i++)
			list.add(i);
		
		Collections.shuffle(list);
		
		QuickSorter quickSorter = new QuickSorter();
		quickSorter.quickSort(list, 0, list.size()-1);
		
		for(int i = 0; i < 9999; i++)
			assertEquals(i, (int)list.get(i));
	}	
}
