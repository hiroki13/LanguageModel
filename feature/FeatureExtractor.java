/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import io.LookupTable;
import io.Sentence;
import io.Token;
import java.util.ArrayList;

/**
 *
 * @author hiroki
 */
public class FeatureExtractor {

    final int weight_size;
    int n, k;
    
    public FeatureExtractor(final int n, final int weight_size)
    {
        this.weight_size = weight_size;
        this.n = n;
    }

    public int[] extractFeature (final Sentence sentence, final int target, final String[] predicted_tags)
    {
        final String[] feature = instantiateFeature(sentence.tokens, target, predicted_tags);
        final int[] encoded_feature = encodeFeature(feature);
        return encoded_feature;
    }
        
    public String[] instantiateFeature (final ArrayList<Token> tokens, final int target, final String[] predicted_labels)
    {
        k = 0;
        final String[] feature = new String[n];

        for(int i=target; i>target-n; --i){
            if (i == target) {
                final Token f = tokens.get(i);
                feature[k++] = i-target + "_" + f.form;
            }
            else if (i < 0) {
                feature[k++] = i-target + "__PAD_";                
            }
            else {
                final String form = predicted_labels[i];
                feature[k++] = i-target + "_" + form;                
            }
        }
        
        return feature;
    }
    
    private int[] encodeFeature (final String[] feature)
    {
        final int[] encoded_feature = new int[k];
        for(int i=0; i<k; ++i) encoded_feature[i] = (feature[i].hashCode() >>> 1) % weight_size;
        return encoded_feature;
    }

    public double[] lookupFeature(final Sentence sentence, final int target)
    {        
        final double[] f_vector = new double[5*weight_size];
        final ArrayList<Token> tokens = sentence.tokens;

        final double[] wp2;
        if (target-2 > 0) wp2 = tokens.get(target-2).vec;
        else wp2 = LookupTable.get("BOS-2");

        final double[] wp1;
        if (target-1 > 0) wp1 = tokens.get(target-1).vec;
        else wp1 = LookupTable.get("BOS-1");

        final double[] wt = tokens.get(target).vec;

        final double[] wn1;
        if (target+1 < sentence.size()-1) wn1 = tokens.get(target+1).vec;
        else wn1 = LookupTable.get("EOS-1");

        final double[] wn2;
        if (target+2 < sentence.size()-1) wn2 = tokens.get(target+2).vec;
        else wn2 = LookupTable.get("EOS-2");
        
        for (int i=0; i<weight_size; ++i) f_vector[i] = wp2[i];
        for (int i=0; i<weight_size; ++i) f_vector[i+weight_size] = wp1[i];
        for (int i=0; i<weight_size; ++i) f_vector[i+weight_size*2] = wt[i];
        for (int i=0; i<weight_size; ++i) f_vector[i+weight_size*3] = wn1[i];
        for (int i=0; i<weight_size; ++i) f_vector[i+weight_size*4] = wn2[i];
        
        return f_vector;
    }
    
}
