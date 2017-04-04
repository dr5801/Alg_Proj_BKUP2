import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import sharedClasses.Timer;

/**
 * 
 * @author Drew Rife
 * 
 * Implements Merge Sort, Quick Sort, and Heap Sort
 *
 */
public class Runner 
{
	public static ArrayList<String> directories;
	private static HashMap<String, ArrayList<Integer>> listOfNumbers;
	private static List<Timer> listOfTimes;
	private static final int numFilesPerDir = 30;
	private static final String resultsDir = "Results/";
	private static final String resultsFile = "results.csv";
	
	/**
	 * main class to run
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		listOfTimes = new ArrayList<Timer>();
		fillListOfDirectories();
		generateFullFilePaths();
		
		System.out.print("Running all sorts for each file ...");
		for(String file : listOfNumbers.keySet())
		{
			recordQuickSortResults(file, listOfNumbers.get(file));
			recordMergeSortResults(file, listOfNumbers.get(file));
			recordHeapSortResults(file, listOfNumbers.get(file));
		}
		System.out.println(" DONE");
		
		/* sort the list of times so they print orderly to the file */
		sortListOfTimes();
		createResultsDir();
		writeToOutput();
	}
	
	/**
	 * sorts the entire list of times by algorithm, then by top level dir, then by it's subdir
	 */
	private static void sortListOfTimes() 
	{
		sortByAlgorithm();
	}


	/**
	 * sorts by algorithm
	 */
	private static void sortByAlgorithm() 
	{
		ArrayList<Timer> quickSort = new ArrayList<Timer>();
		ArrayList<Timer> mergeSort = new ArrayList<Timer>();
		ArrayList<Timer> heapSort = new ArrayList<Timer>();
		
		/* save all the sorts into their own lists */
		for(Timer timer : listOfTimes)
		{
			switch(timer.getAlgorithm())
			{
			case "QuickSort" :
				quickSort.add(timer);
				break;
			case "MergeSort" :
				mergeSort.add(timer);
				break;
			case "HeapSort" :
				heapSort.add(timer);
				break;
			}
		}
		listOfTimes.removeAll(listOfTimes);
		
		listOfTimes.addAll(sortByDir(quickSort));
		listOfTimes.addAll(sortByDir(mergeSort));
		listOfTimes.addAll(sortByDir(heapSort));
	}

	/**
	 * sorts by the top level dir
	 * 
	 * @param dirsToSort
	 * @return
	 */
	private static ArrayList<Timer> sortByDir(ArrayList<Timer> dirsToSort) 
	{
		ArrayList<Timer> small_list = new ArrayList<Timer>();
		ArrayList<Timer> medium_list = new ArrayList<Timer>();
		ArrayList<Timer> large_list = new ArrayList<Timer>();
		
		for(Timer timer : dirsToSort)
		{
			if(timer.getFile().contains("small_list"))
				small_list.add(timer);
			else if(timer.getFile().contains("medium_list"))
				medium_list.add(timer);
			else
				large_list.add(timer);
		}
		
		dirsToSort.removeAll(dirsToSort);
		dirsToSort.addAll(sortSubDir(small_list));
		dirsToSort.addAll(sortSubDir(medium_list));
		dirsToSort.addAll(sortSubDir(large_list));
		
		return dirsToSort;
	}

	/**
	 * sorts by next level subdir 
	 * 
	 * @param list
	 * @return
	 */
	private static ArrayList<Timer> sortSubDir(ArrayList<Timer> list) 
	{
		ArrayList<Timer> unsorted = new ArrayList<Timer>();
		ArrayList<Timer> sorted_S_L = new ArrayList<Timer>();
		ArrayList<Timer> sorted_L_S = new ArrayList<Timer>();
		
		for(Timer timer : list)
		{
			if(timer.getFile().contains("unsorted"))
				unsorted.add(timer);
			else if(timer.getFile().contains("sorted_smallest_largest"))
				sorted_S_L.add(timer);
			else
				sorted_L_S.add(timer);
		}
		
		list.removeAll(list);
		list.addAll(unsorted);
		list.addAll(sorted_S_L);
		list.addAll(sorted_L_S);
		
		return list;
	}

	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	private static void writeToOutput() throws FileNotFoundException 
	{
		System.out.print("Writing to file ... ");
		PrintWriter pw = new PrintWriter(new File(resultsDir+resultsFile));
		pw.append("FILE,ALGORITHM,DURATION\n");
		pw.flush();
		for(Timer timer : listOfTimes)
		{
			pw.append(timer.toString() + "\n");
			pw.flush();
		}
		System.out.println("DONE");
		
	}


	private static void createResultsDir() throws FileNotFoundException 
	{
		System.out.println("Writing results to : " + resultsDir + resultsFile);
		
		File dir = new File(resultsDir);
		File file = new File(resultsDir+resultsFile);
		
		/* create the directory if it doesn't already exist */
		if(!dir.exists())
		{
			System.out.println("\n" + resultsDir + " does not exist.");
			System.out.print("Creating ... ");
			dir.mkdir();
			System.out.println("DONE");
		}
		
		/* delete the file if it already exists */
		if(file.exists())
		{
			file.delete();
		}
	}

	/**
	 * creates a new timer object that runs and records the sorter specified in the constructor
	 * @param file
	 * @param numbersToSort
	 */
	private static void recordQuickSortResults(String file, ArrayList<Integer> numbersToSort) 
	{
		Timer timer = new Timer("QuickSort", file);
		timer.run(numbersToSort);
		listOfTimes.add(timer);
	}
	
	/**
	 * creates a new timer object that runs and records the sorter specified in the constructor
	 * @param file
	 * @param numbersToSort
	 */
	private static void recordMergeSortResults(String file, ArrayList<Integer> numbersToSort)
	{
		Timer timer = new Timer("MergeSort", file);
		timer.run(numbersToSort);
		listOfTimes.add(timer);
	}
	
	/**
	 * creates a new timer object that runs and records the sorter specified in the constructor
	 * @param file
	 * @param numbersToSort
	 */
	private static void recordHeapSortResults(String file, ArrayList<Integer> numbersToSort)
	{
		Timer timer = new Timer("HeapSort", file);
		timer.run(numbersToSort);
		listOfTimes.add(timer);
	}

	/**
	 * reads from file and puts them in the listOfNumbers
	 * @throws FileNotFoundException 
	 */
	private static void generateFullFilePaths() throws FileNotFoundException 
	{
		listOfNumbers = new HashMap<String, ArrayList<Integer>>();
		for(String dir : directories)
		{
			for(int fileNum = 1; fileNum <= numFilesPerDir; fileNum++)
			{
				String file_path = dir;
				
				if(file_path.contains("unsorted"))
				{
					file_path += "unsorted_";
				}
				else 
				{
					file_path += "sorted_";
				}
				
				file_path += Integer.toString(fileNum);
				file_path += ".txt";
			
				readFromFiles(file_path, dir);
			}
		}
	}

	/**
	 * reads from text files and puts them in a list in a HashMap identified by their file path as key
	 * @param file_path
	 * @throws FileNotFoundException 
	 */
	private static void readFromFiles(String file_path, String dir) throws FileNotFoundException 
	{
		ArrayList<Integer> numbersArray = new ArrayList<Integer>();
		
		Scanner numbers = new Scanner(new File(file_path));
		
		while(numbers.hasNextLine())
		{
			numbersArray.add(Integer.parseInt((numbers.nextLine())));
		}
		
		listOfNumbers.put(file_path, numbersArray);
		
		numbers.close();
	}

	/**
	 * fills the directories list of known directories with text files for sorting
	 */
	private static void fillListOfDirectories()
	{
		directories = new ArrayList<String>();
		directories.add("text_files/small_list/unsorted/");
		directories.add("text_files/small_list/sorted_smallest_largest/");
		directories.add("text_files/small_list/sorted_largest_smallest/");
		
		directories.add("text_files/medium_list/unsorted/");
		directories.add("text_files/medium_list/sorted_smallest_largest/");
		directories.add("text_files/medium_list/sorted_largest_smallest/");
		
		directories.add("text_files/large_list/unsorted/");
		directories.add("text_files/large_list/sorted_smallest_largest/");
		directories.add("text_files/large_list/sorted_largest_smallest/");
	}
}
