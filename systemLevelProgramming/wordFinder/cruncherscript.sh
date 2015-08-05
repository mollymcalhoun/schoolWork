#!/bin/bash
./WordCruncher.py "$1"
sort words.txt | uniq > uniquewords.txt
echo
echo "uniquewords.txt created"
echo
echo "Do you want to display the unique words? (y/n)"
read choice
echo
if [ "$choice" == "y" ]
then
    less uniquewords.txt
elif [ "$choice" == "n" ]
then
    echo "Goodbye."
    echo
    exit 1
else
    echo "You typed something wrong. Begone."
    echo
    exit 1
fi