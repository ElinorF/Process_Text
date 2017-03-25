
package com.mycompany.processtext;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;

public class Lemmatize {
    
    public Lemmatize(){
        
    }
    
    public ArrayList<String> preProcess_lemmatize(String corpusFileName){
        ArrayList<String> corpus = new ArrayList<String>();
        try {
            FileInputStream in = new FileInputStream(corpusFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            
            while ((line = br.readLine()) != null) {
                line = lemmatize(line);
                line = line.replaceAll("\\s+", " ").trim();
                if(!line.isEmpty())
                    corpus.add(line);
            }
            
        } catch(Exception e){
            System.out.println(e);
        }
        
        
        return corpus;
    }
    
    
    public static String lemmatize(String comment) throws IOException { 
        PrintStream err = System.err;

        System.setErr(new PrintStream(new OutputStream() {
            public void write(int b) {
            }
        })); 

        Properties props = new Properties(); 
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props); 
        Annotation document = pipeline.process(comment); 
        comment = "";

        for(CoreMap sentence: document.get(CoreAnnotations.SentencesAnnotation.class)) { 
            for(CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) { 
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class); 
                comment = comment + lemma + " ";
            }  
        } 

        System.setErr(err); 
        
        return comment;
    }
}
