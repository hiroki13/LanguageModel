/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import feature.FeatureExtractor;
import io.LookupTable;
import io.Sentence;
import io.Token;
import java.util.ArrayList;
import learning.Classifier;
import learning.LogLinear;

/**
 *
 * @author hiroki
 */
public class Ngram {
    
    final int n, weight_size;
    final Classifier classifier;
    final FeatureExtractor feature_extractor;
    
    public Ngram(final int n, final int weight_size)
    {
        this.n = n;
        this.weight_size = weight_size;
        this.classifier = new LogLinear(LookupTable.token_dict.size(), weight_size);
        this.feature_extractor = new FeatureExtractor(n, weight_size);
    }
    
    public Ngram(final int n, final int weight_size, final Sentence sentence)
    {
        this.n = n;
        this.weight_size = weight_size;
        this.classifier = new LogLinear(LookupTable.token_dict.size(), weight_size);
        this.feature_extractor = new FeatureExtractor(n, weight_size, sentence);
    }
    
    public void train(final ArrayList<Sentence> sentencelist)
    {
        for (int i=0; i<sentencelist.size(); ++i) train(sentencelist.get(i));
    }
    
    private void train(final Sentence sentence)
    {
        float correct = 0.0f;
        float total = 0.0f;
        
        final String[] predicted_labels = new String[sentence.size()];

        for (int i=0; i<sentence.size(); ++i) {
            final Token o_word = sentence.tokens.get(i);
            
            final int[] feature = feature_extractor.extractFeature(sentence, i, predicted_labels);
            final double[] scores = classifier.scores(feature);
            final String best_w = LookupTable.token_array.get(predict(scores));
            final String oracle_w = o_word.form;
            
            if (best_w.equals(oracle_w)) correct += 1.0;
            total += 1.0;
            
            predicted_labels[i] = o_word.form;
            
            classifier.updateWeights(LookupTable.token_array.indexOf(o_word.form), feature, scores);            

            if (i % 1000 == 0 && i != 0) System.out.print(i + " ");        
        }
        
        System.out.println("\nAccuracy: " + correct/total);
    }

    private int predict(final double[] scores)
    {
        int best = -1;
        double best_score = -1000000.0f;

        for (int i=0; i<scores.length; ++i) {
            final double tmp_score = scores[i];
            if (tmp_score > best_score) {
                best = i;
                best_score = tmp_score;
            }
        }
        
        return best;
    }

}
