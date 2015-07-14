/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.ArrayList;

/**
 *
 * @author hiroki
 */
public class Sentence {
    final public int index;
    final public ArrayList<Token> tokens;
    
    public Sentence(int index)
    {
        this.index = index;
        this.tokens = new ArrayList<>();
    }

    public int size()
    {
        return tokens.size();
    }

    public void add(Token token)
    {
        this.tokens.add(token);
    }
    
}
