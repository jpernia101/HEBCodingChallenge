import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class HEBProblem {

    public static HashMap<String, Integer> getWordFrequency(String file) throws FileNotFoundException {
            FileReader textFileReader = new FileReader(file);
            HashMap<String, Integer> wordCountMap = new HashMap<>();
            //Creating scanner instance to read File in Java
            Scanner scanner = new Scanner(textFileReader);
            //Regular expression used to only accept letters and ' character in input.txt
            scanner.useDelimiter("[^A-Za-z']+");

            //Reading each word in the file 
            while(scanner.hasNext()){
                String word = scanner.next().toLowerCase(); 
                //if word isnt in map insert it with count of 1, else get the current count and add 1.
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);             
            }
            scanner.close();
            return wordCountMap;           
    }

    public static LinkedList<Entry<String, Integer>> orderHashMapByWordCount(HashMap<String, Integer> wordCountMap) {
        //LinkedList takes entrySet so we can sort it based on its value which is the frequency of the words 
        LinkedList<Entry<String,Integer>> orderedWordFrequencyList = new LinkedList<>(wordCountMap.entrySet());

        //this function sorts the LinkedList in a descending order based on the entries value 
        Collections.sort(orderedWordFrequencyList, 
            (Entry<String, Integer> entry1, Entry<String, Integer> entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        
        return orderedWordFrequencyList;
    }

    //get the number of "=" needed for the current word
    public static String getEqualSigns(int frequency) {
        return "=".repeat(frequency);
    }

    public static LinkedList<Entry<String, Integer>> getOrderedWordCount(String inputFile) throws FileNotFoundException {
        //This function returns a HashMap with the words and their frequency
        HashMap<String,Integer> wordFrequencyMap = getWordFrequency(inputFile);
        //orderHashMapByWordCount returns a LinkedList of entries<String,Integer> in descending order 
        return orderHashMapByWordCount(wordFrequencyMap);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        try {
            LinkedList<Entry<String, Integer>> orderedWordCountList = getOrderedWordCount("input.txt");
            //Create a file to output text
            FileWriter fileWriter = new FileWriter("output.txt");
            //loop through each word in LinkedList
            for(Entry<String, Integer> curEntry : orderedWordCountList) {
                //Preparing each line for the output.txt file, per the directions.
                String curWordCount = curEntry.getKey() + " | " + 
                    getEqualSigns(curEntry.getValue())+ " (" + curEntry.getValue() + ")";

                fileWriter.write(curWordCount+'\n');                  
            }
            fileWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}