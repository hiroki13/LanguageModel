/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author hiroki
 */
public class Reader {
/*    
    public static ArrayList<Sentence> read(final String fn) throws Exception
    {
        int i = 0;
        final String delimiter = "\t";
        String line;
        ArrayList<Sentence> sentenceList = new ArrayList<>();
        Sentence sentence = new Sentence(i++);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)))){
            while((line=br.readLine()) != null){
                
                if (line.isEmpty()) {
                    sentenceList.add(sentence);
                    sentence = new Sentence(i++);
                }
                else {               
                    String[] split = line.split(delimiter);
                    sentence.add(new Token(split));
                }
            }
        }
        
        return sentenceList;
    }
*/
    public static ArrayList<Sentence> read(final String fn) throws Exception
    {
        int i = 0;
        final String delimiter = "\t";
        String line;
        ArrayList<Sentence> sentenceList = new ArrayList<>();
        Sentence sentence = new Sentence(i++);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)))){
            while((line=br.readLine()) != null){
                
                if (!line.isEmpty()) {               
                    String[] split = line.split(delimiter);
                    sentence.add(new Token(split));
                }
            }
        }
        
        sentenceList.add(sentence);
        
        return sentenceList;
    }

}
