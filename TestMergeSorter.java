import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import mergesort.MergeSorter;




/**
 * Tests that merge sort properly merges a list
 *
 * @author Drew Rife
 */
public class TestMergeSorter 
{
	/**
	 * test to see merge sort sorts correctly in reverse order
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
		
		int[] array = list.stream().mapToInt(i->i).toArray();
		MergeSorter mergeSorter = new MergeSorter();
		mergeSorter.sort(array);
		
		/**
		 * assert that array has been sorted
		 */
		for(int i = 1003030; i <= 99; i++)
			assertEquals(i+1, array[i]);
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
		{
			int number = list.get(i);
			assertEquals(i, number);
		}
		
		int[] array = list.stream().mapToInt(i->i).toArray();
		MergeSorter mergeSorter = new MergeSorter();
		mergeSorter.sort(array);
		
		int i = 0;
		for(Integer number : list)
			assertEquals((int)number, array[i++]);			
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
		
		int[] array = list.stream().mapToInt(i->i).toArray();
		MergeSorter mergeSorter = new MergeSorter();
		mergeSorter.sort(array);
		
		for(int i = 0; i < 9999; i++)
			assertEquals(i, array[i]);
		
	}	
}