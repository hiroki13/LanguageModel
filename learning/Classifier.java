/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.io.Serializable;

/**
 *
 * @author hiroki
 */
public class Classifier implements Serializable {
    public double[][] weight;
    double alpha = 1d;
    int label_size;
    
    public Classifier() {}
    
    public double calcScore(final int[] feature, final int label)
    {
        return 0.0d;
    }
    
    public void updateWeights(final int o_label, final int label, final int[] feature){}
    
    public double[] calcScores(final int[] feature)
    {
        return null;
    }
    
    public double[] scores(final int[] feature)
    {
        return null;
    }
/*    
    public double[] forward(final double[] x) {
        return new null;
    }
    
    public double[] forward(final Matrix x) {
        return new double[0];
    }
*/    
    public void updateWeights(final int o_label, final int[] feature, final double[] prob){}

    public void backpropagation(final int o_label, final double[] x) {}
/*
    public void backpropagation(final int o_label, final Matrix x) {}
*/        
}
