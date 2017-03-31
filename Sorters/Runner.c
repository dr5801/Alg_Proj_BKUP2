/*
 * Runner.c
 *
 *  Created on: Mar 30, 2017
 *      Author: drew
 */
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

#define FILES_PER_DIR 30

typedef char* String;

typedef struct NumbersToSort {
	String file_path;
	int *listOfNumbers;
	struct NumbersToSort * next;
	struct NumbersToSort * previous;
} NumbersToSort;

typedef struct Small_List {
	String file_path;
	struct Small_List * next;
	struct Small_List * previous;
} Small_List;

typedef struct Medium_List {
	String file_path;
	struct Medium_List * next;
	struct Medium_List * previous;
} Medium_List;

typedef struct Large_List {
	String file_path;
	struct Large_List * next;
	struct Large_List * previous;
} Large_List;

typedef struct Lists {
	struct Small_List * topOfSmallList;
	struct Small_List * bottomOfSmallList;

	struct Medium_List * topOfMediumList;
	struct Medium_List * bottomOfMediumList;

	struct Large_List * topOfLargeList;
	struct Large_List * bottomOfLargeList;

	struct NumbersToSort * topOfNumbersList;
	struct NumbersToSort * bottomOfNumbersList;
} Lists;

void intializeLists(Lists *list);

void fillListsOfFilePaths(Lists *list);

void addFilePathsToLists(String filePath, Lists *list);

int main(void)
{
	Lists list;
	initializeLists(&list);
	fillListsOfFilePaths(&list);

	return 0;

}

void addFilePathsToLists(String filePath, Lists *list)
{
	list->topOfNumbersList;
	if(list->topOfNumbersList == NULL && list->bottomOfNumbersList == NULL)
	{
		NumbersToSort *numbers = malloc(sizeof(*numbers));

		if(numbers == NULL)
		{
			printf("\n\nError: Out of Memory");
		}
		else
		{
			numbers->file_path = (char *)malloc((strlen(filePath) + 1) * sizeof(char));
			strcpy(numbers->file_path, filePath);
			numbers->previous = numbers->next = NULL;

			list->topOfNumbersList = list->bottomOfNumbersList = numbers;
		}
	}
	else
	{
		NumbersToSort *numbers = malloc(sizeof(*numbers));
		numbers->file_path = (char *)malloc((strlen(filePath) + 1) * sizeof(char));
		strcpy(numbers->file_path, filePath);
		numbers->previous = numbers->next = NULL;

		list->bottomOfNumbersList->next = numbers;
		numbers->previous = list->bottomOfNumbersList;
		list->bottomOfNumbersList = numbers;
	}
}

void fillListsOfFilePaths(Lists *list)
{
	String txtSuffix = ".txt";
	String filePaths[9] =
	{
			"text_files/small_list/unsorted/unsorted_",
			"text_files/small_list/sorted_smallest_largest/sorted_",
			"text_files/small_list/sorted_largest_smallest/sorted_",
			"text_files/medium_list/unsorted/unsorted_",
			"text_files/medium_list/sorted_smallest_largest/sorted_",
			"text_files/medium_list/sorted_largest_smallest/sorted_",
			"text_files/large_list/unsorted/unsorted_",
			"text_files/large_list/sorted_smallest_largest/sorted_",
			"text_files/large_list/sorted_largest_smallest/sorted_",
	};

	int i;
	for(i = 0; i < (sizeof(filePaths)/sizeof(filePaths[0])); i++)
	{
		int fileNumber;
		for(fileNumber = 1; fileNumber <= FILES_PER_DIR; fileNumber++)
		{
			String snum = realloc(snum, (String)((int)((ceil)(log10(fileNumber))+3)*sizeof(char)));
			sprintf(snum, "%d", fileNumber);
			String numberTxt = realloc(numberTxt, (strlen(snum) + strlen(txtSuffix) + 1));
			strcpy(numberTxt, snum);
			strcat(numberTxt, ".txt");

			String file = realloc(file, (strlen(filePaths[i]) + strlen(numberTxt) + 1));
			strcpy(file, filePaths[i]);
			strcat(file, numberTxt);

			printf("%s\n", file);

			free(snum);
			free(numberTxt);
			free(file);
			snum = NULL;
			numberTxt = NULL;
			file = NULL;
		}
	}
}

void initializeLists(Lists *list)
{
	list->topOfSmallList = list->bottomOfSmallList = NULL;
	list->topOfMediumList = list->bottomOfMediumList = NULL;
	list->topOfLargeList = list->bottomOfLargeList = NULL;
	list->topOfNumbersList = list->bottomOfNumbersList = NULL;
}
