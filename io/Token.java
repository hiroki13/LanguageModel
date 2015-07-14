/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 *
 * @author hiroki
 */
public final class Token {
    final public int id, head, phead;
    final public String form, lemma, plemma, pos, ppos, feat, pfeat, deprel, pdeprel, fillpred;
    final public double[] vec;
    
    
    public Token(final String[] line)
    {
        id = Integer.parseInt(line[0]);
        form = line[1];
        lemma = line[2];
        plemma = line[3];
        pos = line[4];
        ppos = line[5];
        feat = line[6];
        pfeat = line[7];
        head = Integer.parseInt(line[8]);
        phead = Integer.parseInt(line[9]);
        deprel = line[10];
        pdeprel = line[11];
        fillpred = line[12];
        
        vec = LookupTable.get(form);
    }
}
