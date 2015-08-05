#!/usr/bin/python

# Molly Calhoun
# Systems Level Programming
# File to be parsed must be provided as a command line argument

import re
import sys

class TextParser(object):
    def __init__(self):
        self.number_dict = {"0":"zero", "1":"one", "2":"two", "3":"three",
                            "4":"four", "5":"five", "6":"six", "7":"seven",
                            "8":"eight", "9":"nine"}
        self.roman_numerals = ["nulla", "I", "II", "III", "IV", "V",
                               "VI", "VII", "VIII", "IX"]
        #self.filename = raw_input("\nWhat file do you want to parse?\n> ")
        #self.example_file = open(self.filename, 'r')
        self.example_file = open(sys.argv[1], 'r')
        self.file_string = self.example_file.read()
        print("\nOriginal file:\n==============\n%s" % self.file_string)
        self.example_file.seek(0)

    def parse_numeral(self):
        print("\nSingle digits parsed to words:\n" + "="*30)
        for line in self.example_file:
            for key, value in self.number_dict.iteritems():
                line = re.sub(key, value, line)
            print line,
        print
        self.example_file.seek(0)


    def parse_number(self):
        print("\nWritten numbers parsed to digits:\n" + "="*33)
        for line in self.example_file:
            for key, value in self.number_dict.iteritems():
                value = r"\b%s\b" % value
                line = re.sub(value, key, line)
            print line,
        print
        self.example_file.seek(0)

    def parse_roman(self):
        print("\nWritten numbers and digits\n" +
              " parsed to roman numerals:\n" + "="*26)
        for line in self.example_file:
            for key, value in self.number_dict.iteritems():
                current_numeral = self.roman_numerals[int(key)]
                value = r"\b%s\b" % value
                line = re.sub(key, current_numeral, line)
                line = re.sub(value, current_numeral, line)
            print line,
        print
        self.example_file.seek(0)


if __name__ == "__main__":
    textparse = TextParser()
    raw_input("\nPress enter to continue.")
    textparse.parse_numeral()
    raw_input("\nPress enter to continue.")
    textparse.parse_number()
    raw_input("\nPress enter to continue.")
    textparse.parse_roman()
    raw_input("\nPress enter to exit.")
    print
    textparse.example_file.close()