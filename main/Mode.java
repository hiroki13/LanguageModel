/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import io.OptionParser;
import io.ParameterChecker;
import io.Reader;
import io.Sentence;
import model.Ngram;

/**
 *
 * @author hiroki
 */
public class Mode {
    
    public OptionParser optionparser;
    public String trainfile;
    public int iteration, weight_size, prune;
    public ArrayList<Sentence> trainsentence;
    
    
    Mode(String[] args) throws Exception
    {
        optionparser = new OptionParser(args);
    }

    public void setParameter()
    {
        weight_size = optionparser.getInt("weight", 1000);
        iteration = optionparser.getInt("iter", 10);
        prune = optionparser.getInt("prune", 100000);
    }    
    
    public void execute() throws Exception
    {
        setParameter();
        ParameterChecker p_checker = new ParameterChecker(this);
        p_checker.check();
        System.out.println("\nLanguage Model Building START");        
            
        trainsentence = Reader.read(trainfile);
            
        System.out.println(String.format("Train Sents: %d\n", trainsentence.size()));
        
        final Ngram ngram = new Ngram(3, weight_size);
        iteration = 20;
        for (int i=0; i<iteration; ++i) ngram.train(trainsentence);                        
    }
        
}
