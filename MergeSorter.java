package mergesort;

/**
 * @author Drew Rife
 * 
 * MergeSorter that merge sorts a list
 */
public class MergeSorter 
{
	public void sort(int[] numbersToSort)
	{
		int[] array = mergeSort(numbersToSort);
	}
	
	/**
	 * starts the merge sort and sorts the list recursively
	 * 
	 * @param arrayA
	 * @return sorted arrayA
	 */
	private int[] mergeSort(int[] arrayA)
	{
		if(arrayA.length <= 1) return arrayA;
		
		int[] arrayB = new int[arrayA.length/2];
		int[] arrayC = new int[arrayA.length - arrayB.length];
		
		copyAToB(arrayA, arrayB);
		copyAToC(arrayA, arrayC);
		mergeSort(arrayB);
		mergeSort(arrayC);
		merge(arrayA, arrayB, arrayC);
		return arrayA;
	}
	
	/**
	 * merge b and c into a
	 * 
	 * @param arrayA
	 * @param arrayB
	 * @param arrayC
	 */
	private void merge(int[] arrayA, int[] arrayB, int[] arrayC) 
	{
		int indexOfArrayA = 0;
		int indexOfArrayB = 0;
		int indexOfArrayC = 0;
		
		while(indexOfArrayB < arrayB.length && indexOfArrayC < arrayC.length)
		{
			if(arrayB[indexOfArrayB] <= arrayC[indexOfArrayC])
				arrayA[indexOfArrayA++] = arrayB[indexOfArrayB++];
			else
				arrayA[indexOfArrayA++] = arrayC[indexOfArrayC++];
		}
		
		/* copies contents of arrayC starting from it's currentIndex on to arrayA from arrayA's current index to arrayC.length - currentIndexOfArrayC */
		System.arraycopy(arrayC, indexOfArrayC, arrayA, indexOfArrayA, arrayC.length-indexOfArrayC);
		
		/* copies contents of arrayB starting from it's currentIndex on to arrayA from arrayA's current index to arrayB.length - currentIndexOfArrayB */
		System.arraycopy(arrayB, indexOfArrayB, arrayA, indexOfArrayA, arrayB.length-indexOfArrayB);
	}

	/**
	 * copies content from arrayA to arrayC
	 * 
	 * @param arrayA
	 * @param arrayC
	 */
	private void copyAToC(int[] arrayA, int[] arrayC) 
	{
		System.arraycopy(arrayA, arrayA.length/2, arrayC, 0, arrayC.length);
	}

	/**
	 * copies content from arrayA to arrayB
	 * 
	 * @param arrayA
	 * @param arrayB
	 */
	private void copyAToB(int[] arrayA, int[] arrayB)
	{
		System.arraycopy(arrayA, 0, arrayB, 0, arrayB.length);
	}
}
