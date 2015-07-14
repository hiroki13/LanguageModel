/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import main.Mode;


/**
 *
 * @author hiroki
 */
public class ParameterChecker {

    final Mode mode;
    final OptionParser optionparser;
    
    public ParameterChecker(Mode mode)
    {
        this.mode = mode;
        this.optionparser = mode.optionparser;
    }
    
    public void check()
    {
        setTrainFile();
    }
    
    public void setTrainFile()
    {
        mode.trainfile = optionparser.getString("train");
    }
}
