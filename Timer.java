package sharedClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heapsort.HeapSorter;
import mergesort.MergeSorter;
import quicksort.QuickSorter;

/**
 * @author Drew Rife
 *
 * Runs the algorithms and stores the times of those values
 */
public class Timer 
{
	private String algorithmToRun;
	private String file;
	private long startTime;
	private long endTime;
	
	/**
	 * constructor for Timer
	 * 
	 * @param algorithmToRun
	 */
	public Timer(String algorithmToRun, String file)
	{
		this.algorithmToRun = algorithmToRun;
		this.file = file;
	}
	
	/**
	 * runs the algorithm
	 */
	public void run(ArrayList<Integer> numbersToSort)
	{
		switch(this.algorithmToRun)
		{
		case "QuickSort" :
			doQuickSort(this.file, numbersToSort);
			break;
		case "MergeSort" :
			doMergeSort(this.file, numbersToSort);
			break;
		case "HeapSort" :
			doHeapSort(this.file, numbersToSort);
			break;
		}
	}
	
	/**
	 * does the quicksort algorithm
	 * 
	 * @param file
	 * @param arrayList
	 */
	public void doQuickSort(String file, ArrayList<Integer> numbersToSort) 
	{
		int lowerEnd = 0;
		int upperEnd = numbersToSort.size()-1;
		
		QuickSorter quickSorter = new QuickSorter();
		
		this.startTime = System.nanoTime();
		quickSorter.quickSort(numbersToSort, lowerEnd, upperEnd);
		this.endTime = System.nanoTime();
	}

	/**
	 * does the mergesort algorithm
	 *  
	 * @param file
	 * @param arrayList
	 */
	public void doMergeSort(String file, ArrayList<Integer> numbersToSort) 
	{
		/* convert arraylist to array int */
		int[] listToSort = numbersToSort.stream().mapToInt(i -> i).toArray();
		
		MergeSorter mergeSort = new MergeSorter();

		this.startTime = System.nanoTime();
		mergeSort.sort(listToSort);
		this.endTime = System.nanoTime();
	}

	/**
	 * does the heapsort algorithm
	 * 
	 * @param file
	 * @param arrayList
	 */
	public void doHeapSort(String file, ArrayList<Integer> numbersToSort) 
	{
		/* convert arraylist to array int */
		int[] listToSort = numbersToSort.stream().mapToInt(i -> i).toArray();
		
		HeapSorter heapSorter = new HeapSorter();
		
		this.startTime = System.nanoTime();
		listToSort = heapSorter.heapSort(listToSort);
		this.endTime = System.nanoTime();
	}
	
	/**
	 * @return the duration
	 */
	public double getDuration()
	{
		return ((endTime - startTime) * Math.pow(10, -6));
	}
	
	/**
	 * @return startTime
	 */
	public double getStartTime()
	{
		return this.startTime;
	}
	
	/**
	 * @return endTime
	 */
	public double getEndTime()
	{
		return this.endTime;
	}
	
	/**
	 * @return the algorithmToRun
	 */
	public String getAlgorithm()
	{
		return this.algorithmToRun;
	}
	
	/**
	 * @return file
	 */
	public String getFile()
	{
		return this.file;
	}
	
	/* overrides to string */
	@Override 
	public String toString()
	{
		DIRS dirs;
		String[] filePath = this.file.split("/");
		
		String output = filePath[DIRS.SECOND_LEVEL.getValue()] + "/";
		output += filePath[DIRS.THIRD_LEVEL.getValue()] + "/";
		output += filePath[DIRS.FILE_NAME.getValue()] + ",";
		output += this.algorithmToRun + ",";
		output += this.getDuration();
		
		return output;
	}
	
	/* enum representing dirs */
	private enum DIRS {
		TOP_LEVEL (0), 
		SECOND_LEVEL (1), 
		THIRD_LEVEL (2), 
		FILE_NAME (3);
	
		private final int value;
		DIRS(int value)
		{
			this.value = value;
		}
		
		private int getValue() { return value; }
	};
}
