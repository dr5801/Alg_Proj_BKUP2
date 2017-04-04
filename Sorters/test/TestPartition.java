import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import quicksort.Partition;
import quicksort.QuickSorter;

/**
 * Tests the Partition class
 * 
 * @author drew
 *
 */
public class TestPartition 
{
	/**
	 * Tests the initialization of Paritioner
	 */
	@Test
	public void testInitialization()
	{
		ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
		int start = 2;
		int end = 4;
		
		for(int i = 0; i < 40; i++)
		{
			listOfNumbers.add(i);
		}
		
		Partition partition = new Partition();
		partition.setEnd(end);
		partition.setStart(start);
		
		assertEquals(start, partition.getStart());
		assertEquals(end, partition.getEnd());
	}
	
	@Test
	public void testParition()
	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(700); 
		array.add(300);
		array.add(400);
		array.add(600);
		array.add(700);
		array.add(450);
		array.add(502);
		
		int lowerEnd = 0;
		int upperEnd = array.size()-1;
		
		QuickSorter quickSorter = new QuickSorter();
		int pivotIndex = quickSorter.getPivot(array, lowerEnd, upperEnd);
		
		Partition partition = new Partition();
		partition.partitionList(array, lowerEnd, upperEnd, pivotIndex);
		
		int expected = array.get(partition.getStart());
		assertEquals(600, expected);
		
		expected = array.get(partition.getEnd());
		assertEquals(700, expected);
	}
}
