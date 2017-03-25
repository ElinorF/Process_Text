
package com.mycompany.processtext;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;

public class Standard {
    
    public Standard(){
        
    }
    
    public ArrayList<String> preProcess_Standard(String corpusFileName, ArrayList<String> functionWords){
        ArrayList<String> corpus = new ArrayList<String>();
        try {
            FileInputStream in = new FileInputStream(corpusFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            
            while ((line = br.readLine()) != null) {
                    line = removeLinks(line);
                    line = htmlToText(line);
                    line = upperToLowerCase(line);
                    line = removeNonLetters(line);
                    line = removeFunctionWords(line, functionWords);
                    line = removeNonLetters(line);
                    line = line.replaceAll("\\s+", " ").trim();
                    if(!line.isEmpty())
                        corpus.add(line);

                }

        } catch(Exception e){
            
                System.out.println(e);
        }
        return corpus;
    }
    
     public static String removeLinks(String comment) {
        return comment.replaceAll("(http://)(.*?)(\\s+)","$1$3").replaceAll("http://", "");
    }
   
    public static String htmlToText(String comment) { 
        return Jsoup.parse(comment).text();
    }
    
    public static String upperToLowerCase(String comment) throws IOException {
        return comment.toLowerCase();
    }
    
     public static String removeNonLetters(String comment) throws IOException {
            return comment.replaceAll("[^a-zA-Z\\s]", "");
    }

    public static String removeFunctionWords(String comment, ArrayList<String> FunctionWordArray) throws IOException {       
        List<String> listOfWords = new ArrayList<String>(Arrays.asList(comment.split(" ")));
        listOfWords.removeAll(FunctionWordArray);
        return listOfWords.toString();
        
    }
    
}
