/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import static java.lang.Math.exp;

/**
 *
 * @author hiroki
 */
public class LogLinear extends Classifier{
    
    public LogLinear(final int label_size, final int weight_size)
    {
        this.weight = new double[label_size][weight_size];
        this.label_size = label_size;
    }
    
    @Override
    final public double calcScore(final int[] feature, final int label)
    {
        float score = 0.0f;
        final double[] label_weight = weight[label];
        for(int i=0; i<feature.length; ++i) score += label_weight[feature[i]];
        return exp(score);
    }
    
    @Override
    final public double[] calcScores(final int[] feature)
    {
        double[] scores = new double[label_size];        
        for (int i=0; i<label_size; ++i) scores[i] = calcScore(feature, i);
        return scores;
    }
    
    final public double z(final double[] scores)
    {
        double z = 0.0d;
        for (int i=0; i<label_size; ++i) z += scores[i];
        return z;
    }
    
    @Override
    final public double[] scores(final int[] feature)
    {
        final double[] scores = calcScores(feature);
        final double z = z(scores);
        
        final double[] prob = new double[label_size];
        for (int i=0; i<label_size; ++i) prob[i] = scores[i] / z;
        return prob;
    }
            
    @Override
    final public void updateWeights(final int o_label, final int[] feature, final double[] prob)
    {
        double[] tmp_o_weight = this.weight[o_label];        

        for (int i=0; i<feature.length; ++i) {
            final int phi_id = feature[i];
            tmp_o_weight[phi_id] += alpha * 1.0d;
        }
        
        for (int label=0; label<label_size; ++label) {
            final double[] tmp_weight = this.weight[label];
            final double p = prob[label];
            
            for (int j=0; j<feature.length; ++j) {
                final int phi_id = feature[j];
                tmp_weight[phi_id] -= alpha * p;
            }
        }
    }
        
    
}
