#!/usr/bin/python

import collections as c
import re
import string
import sys

class WordCruncher(object):
    def __init__(self):
        self.file_name = sys.argv[1]
        self.text_file = open(self.file_name, 'r')
        self.words_file = ""
        self.longest = {}
        self.most_frequent = {}

    def break_and_write(self):
        print("\nBreaking...")
        words_file = open("words.txt", 'w')
        for line in self.text_file:
            words = line.split();
            for word in words:
                word = word.lower().strip(string.punctuation)
                words_file.write(word + "\n")
        print("File 'words.txt' created.")
        words_file.close()
        self.words_file = open("words.txt", 'r')
        self.text_file.close()

    def find_longest(self):
        for word in self.words_file:
            word = word.strip("\n")
            if word not in self.longest:
                self.longest[word] = len(word)
        # I got the OrderedDict method here from the Python documentation:
        # https://docs.python.org/2/library/collections.html#collections.OrderedDict
        sorted_dict = c.OrderedDict(sorted(self.longest.items(),
                                    key = lambda k: k[1]))
        menu_title = "The longest words are:"
        print("\n%s\n%s" % (menu_title, "="*len(menu_title)))
        for i in range(1, 11):
            item = sorted_dict.popitem()
            if i < 10:
                print(""),
            print("%d: %s - %d characters" % (i, item[0], item[1]))
        self.words_file.seek(0)

    def find_most_frequent(self):
        for word in self.words_file:
            word = word.strip("\n")
            if word not in self.most_frequent:
                self.most_frequent[word] = 1
            else:
                self.most_frequent[word] += 1
        # I got the OrderedDict method here from the Python documentation:
        # https://docs.python.org/2/library/collections.html#collections.OrderedDict
        sorted_dict = c.OrderedDict(sorted(self.most_frequent.items(),
                                    key = lambda k: k[1]))
        menu_title = "The most frequent words are:"
        print("\n%s\n%s" % (menu_title, "="*len(menu_title)))
        for i in range(1, 101):
            item = sorted_dict.popitem()
            # Align for readability
            if i < 10:
                print(" "),
            elif i >= 10 and i < 100:
                print(""),
            print("%d: %s - %d instances" % (i, item[0], item[1]))
        self.words_file.seek(0)


if __name__ == "__main__":
    crunch = WordCruncher()
    crunch.break_and_write()
    raw_input("\nPress enter to continue.")
    crunch.find_longest()
    raw_input("\nPress enter to continue.")
    crunch.find_most_frequent()
    raw_input("\nPress enter to exit.")
    crunch.words_file.close()
    sys.exit(0)