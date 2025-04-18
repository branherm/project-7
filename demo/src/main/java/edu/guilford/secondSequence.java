package edu.guilford;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class secondSequence {

        //Each item in this second sequence should be an object with two attributes: the word and the number of occurrences

        
        /**
         * this class is used to create objects that contain a word and the number of times that word occurs in the file
         * 
         */

         /** 
          * the word attribute is a string that contains the word
          */
        private String word;

/**
 * the occurrences attribute is an integer that contains the number of times the word occurs in the file
 */
        private int occurrences;


        //constructor
        public secondSequence(String word, int occurrences) {
            this.word = word;
            this.occurrences = occurrences;
        }

/**
 * getter and setter methods for the word and occurrences attributes
 * @return word
 * @return occurrences
 * @param none
 * 
 */
        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getOccurrences() {
            return occurrences;
        }

        public void setOccurrences(int occurrences) {
            this.occurrences = occurrences;
        }

        //


        
        



    }
