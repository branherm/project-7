package edu.guilford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /**
     * this is the driver class for the program
     * what it does is read in a text file, then sort the words in the file in
     * alphabetical order, then write the sorted words to a new file
     * it also creates a second sequence of objects, each object containing a word
     * and the number of times that word occurs in the file
     * it then sorts the second sequence in alphabetical order and writes the sorted
     * second sequence to a new file
     * it then prompts the user to enter a word, and displays how many times that word
     * occurs in the file
     * 
     * author: Brandon Hermes
     * version: 1.0
     * @param args
     */
    public static void main(String[] args) {
        File textFile = new File("demo/src/main/resources/brandon essay.txt");
        // Read in the text file using a FileReader and BufferedReader
        System.out.println(textFile.getAbsolutePath());
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(textFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // Read in the text file line by line
        String line = null;
        LinkedList<String> words = new LinkedList<String>();
        while (true) {
            try {
                line = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (line == null) { // If the line is null, we have reached the end of the file
                break;
            }
            // Split the line into words
            String[] lineWords = line.split(" ");
            // Add each word to the words ArrayList
            for (String word : lineWords) {
                words.add(word);

            }

        }
        // close the bufferedReader
        try {
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // convert all the words to lowercase
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            word = word.toLowerCase();
            words.set(i, word);
        }

        // remove all special characters
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            words.set(i, word);
        }

        // Sort the words in alphabetical order
        LinkedList<String> outWords = new LinkedList<String>();
        for (String word : words) {
            if (outWords.size() == 0) {
                outWords.add(word);
            } else {
                boolean added = false;
                for (int i = 0; i < outWords.size(); i++) {
                    if (word.compareTo(outWords.get(i)) < 0) {
                        outWords.add(i, word);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    outWords.add(word);
                }
            }
        }

        // print out the words in alphabetical order
        for (String outPutWord : outWords) {
            System.out.println(outPutWord);
        }

        try {
            // Create file
            FileWriter fstream = new FileWriter("output.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            // write the data from the outWords LinkedList to the file
            for (String outPutWord : outWords) {
                out.write(outPutWord);

                out.newLine();
            }
            // Close the output stream
            out.close();
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

//_______________________________________________________________________________________________________________________
//create a second sequence using the secondSequence class
        LinkedList<secondSequence> secondSequence = new LinkedList<secondSequence>();
        for (String word : words) {
            boolean added = false;
            for (secondSequence sequence : secondSequence) {
                if (sequence.getWord().equals(word)) {
                    sequence.setOccurrences(sequence.getOccurrences() + 1);
                    added = true;
                    break;
                }
            }
            if (!added) {
                secondSequence.add(new secondSequence(word, 1));
            }
        }

        // sort the second sequence in alphabetical order
        LinkedList<secondSequence> outSecondSequence = new LinkedList<secondSequence>();
        for (secondSequence sequence : secondSequence) {
            if (outSecondSequence.size() == 0) {
                outSecondSequence.add(sequence);
            } else {
                boolean added = false;
                for (int i = 0; i < outSecondSequence.size(); i++) {
                    if (sequence.getWord().compareTo(outSecondSequence.get(i).getWord()) < 0) {
                        outSecondSequence.add(i, sequence);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    outSecondSequence.add(sequence);
                }
            }
        }

        //convert to lowercase
        for (int i = 0; i < outSecondSequence.size(); i++) {
            String word = outSecondSequence.get(i).getWord();
            word = word.toLowerCase();
            outSecondSequence.get(i).setWord(word);
        }

        // remove all special characters
        for (int i = 0; i < outSecondSequence.size(); i++) {
            String word = outSecondSequence.get(i).getWord();
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            outSecondSequence.get(i).setWord(word);
        }

        // print out the second sequence in alphabetical order into a new file
        try {
            // Create file
            FileWriter fstream = new FileWriter("output2.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            // write the data from the outSecondSequence LinkedList to the file
            for (secondSequence sequence : outSecondSequence) {
                out.write("(" + sequence.getWord() + ") " + sequence.getOccurrences());
                out.newLine();
            }
            // Close the output stream
            out.close();
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
//_______________________________________________________________________________________________________________________



//create a loop that promts the user to enter a word, then displays how many times that word
//occurs in the file
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a word to search for: ");
            String searchWord = scanner.nextLine();
            if (searchWord.equals("exit")) {
                break;
            }
            int occurrences = 0;
            for (secondSequence sequence : outSecondSequence) {
                if (sequence.getWord().equals(searchWord)) {
                    occurrences = sequence.getOccurrences();
                    break;
                }
            }
            System.out.println("The word " + searchWord + " occurs " + occurrences + " times in the file.");
        }



    

}
}