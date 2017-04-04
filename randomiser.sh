#!/bin/bash

GREEN='\e[1;31m%s\e[0m\n'

NUM_FILES=30
MIN_VALUE=0
MAX_VALUE=9999
SMALL_AMOUNT=10000
MEDIUM_AMOUNT=100000
LARGE_AMOUNT=1000000

TEXT_DIR="./Sorters/text_files"

#first run and set up the folders if they don't already exist
./file_checker.sh

#list of unsorted directories for the random data to be put into 
declare -a unsorted_dirs=("$TEXT_DIR/small_list/unsorted/" 
                        "$TEXT_DIR/medium_list/unsorted/" 
                        "$TEXT_DIR/large_list/unsorted/")

#generate the files
function generateFiles() {

    for dir in "${unsorted_dirs[@]}"
    do
        if [[ $dir == *"small_list"* ]]; then
            printf "Generating random numbers for small_list  ... "
            generateRandomNumbers $dir $SMALL_AMOUNT
            printf "$GREEN" "DONE"

        elif [[ $dir == *"medium_list"* ]]; then
            printf "Generating random numbers for medium_list  ... "
            generateRandomNumbers $dir $MEDIUM_AMOUNT
            printf "$GREEN" "DONE"

        elif [[ $dir == *"large_list"* ]]; then
            printf "Generating random numbers for large_list ... "
            generateRandomNumbers $dir $LARGE_AMOUNT 
            printf "$GREEN" "DONE"
        fi 
    done

}

#generates random numbers
function generateRandomNumbers() {
    dir=$1
    amountToGenerate=$2
    filePath="${dir}unsorted_"

    #run for how many text files there are
    for (( fileNum=1; fileNum<=$NUM_FILES; fileNum++ ))
    do

        #run for how many random numbers there are supposed to be for each file
        printf "$(shuf -i 0-9999 -n $amountToGenerate)\n" > ${filePath}${fileNum}.txt
    done 

}

#sorts the unsorted random data and puts them into their respective directory
function sortRandomData(){
    declare -a smallestToLargest=("$TEXT_DIR/small_list/sorted_smallest_largest"
                                "$TEXT_DIR/medium_list/sorted_smallest_largest"
                                "$TEXT_DIR/large_list/sorted_smallest_largest")

    declare -a largestToSmallest=("$TEXT_DIR/small_list/sorted_largest_smallest"
                                "$TEXT_DIR/medium_list/sorted_largest_smallest"
                                "$TEXT_DIR/large_list/sorted_largest_smallest")

    printf "\nSORTING SMALL_LIST MEDIUM_LIST LARGE_LIST ... "

    for unsorted_dir in ${unsorted_dirs[@]}
    do
        for file in "$unsorted_dir"*.txt
        do
            fileName=$(basename $file)
            fileNumber=${fileName//[^0-9]/}
            #start sorting
            if [[ $unsorted_dir =~ "small_list" ]]; then
                sort -n $file > ${smallestToLargest[0]}/sorted_$fileNumber.txt
                sort -nr $file > ${largestToSmallest[0]}/sorted_$fileNumber.txt
            elif [[ $unsorted_dir =~ "medium_list" ]]; then
                sort -n $file > ${smallestToLargest[1]}/sorted_$fileNumber.txt
                sort -nr $file > ${largestToSmallest[1]}/sorted_$fileNumber.txt
            elif [[ $unsorted_dir =~ "large_list" ]]; then
                sort -n $file > ${smallestToLargest[2]}/sorted_$fileNumber.txt
                sort -nr $file > ${largestToSmallest[2]}/sorted_$fileNumber.txt
            fi
        done
    done

    printf "$GREEN" " DONE "
}

generateFiles
sortRandomData
