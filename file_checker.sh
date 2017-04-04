#sorts the random unsorted files 
#!bin/bash

RED='\e[1;32m%s\e[0m\n'
GREEN='\e[1;31m%s\e[0m\n'

filename="text_files_dirs.txt"

function checkTextFilesDirectory() {
    #first check to make sure text_files directory exists
    TEXT_DIR="./Sorters/text_files"
    initialCheckIfExist $TEXT_DIR
    
    while IFS='' read -r subdir || [[ -n "$subdir" ]]; do
        initialCheckIfExist "$TEXT_DIR/$subdir"
    done < "$filename"
}

#checks to see if the directory exists
function initialCheckIfExist() {
    directory=$1
    printf "Checking $directory/ ... "
    
    if [ -d $directory/ ]
    then 
        printf "$GREEN\n" " Exists " 
    else 
        printf "$RED" " Doesn't exist "
        createDirectory $directory
    fi 
}

#creates the directory if it doesn't exist
function createDirectory() {
    printf "Creating $1/ ... "
    
    #make the directory
    mkdir "$1/"

    #check to see if the folder has been created 
    if [ -d $directory/ ]
    then 
        printf "$GREEN\n" " OK "
    else 
        printf "$RED\n" " THERE WAS AN ERROR CREATING DIRECTORY "
    fi
}

checkTextFilesDirectory
