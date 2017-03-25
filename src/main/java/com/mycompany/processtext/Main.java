
package com.mycompany.processtext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException{
                
        ArrayList<String> functionWords_before;
        ArrayList<String> functionWords;
        
        functionWords_before = new ArrayList<String>(Arrays.asList(fileToString("Data/FunctionWords.txt").split(" "))); 
        functionWords = new ArrayList<String>();
        for(String word : functionWords_before){
            word = word.replaceAll("\\s+", "");
            functionWords.add(word);
        } 


        Standard standard = new Standard();
        ArrayList<String> corpusStandard = standard.preProcess_Standard("Data/Corpus.txt", functionWords);
        writeArrayListToFile(corpusStandard, "Data/Standard.txt");
        
        Lemmatize lemmatize = new Lemmatize();
        ArrayList<String>  corpusLemmatize = lemmatize.preProcess_lemmatize("Data/Standard.txt");
        writeArrayListToFile(corpusLemmatize, "Data/Lemmatized.txt");
        
           

    }

    
    public static void writeArrayListToFile(ArrayList<String> list, String fileName) throws IOException{

        FileWriter writer = new FileWriter(fileName); 
        for(String str: list) {
          writer.write(str+"\n");
        }
        writer.close();


    }
    
    public static String fileToString(String file) throws IOException {   
        BufferedReader br = new BufferedReader(new FileReader(file));

        String part_str;
        String str = "";
        while( (part_str = br.readLine()) != null) {
           str = str + part_str; 
           str = str + " ";
        }     
        br.close();
        return str;
    }
    
}

