package org.apache.lucene.demo;

import org.apache.lucene.search.similarities.ClassicSimilarity;

public final class CMPT456Similarity extends ClassicSimilarity {
    //custom tf
    @Override
    public float tf(float freq) { return (float)Math.sqrt(1+freq);}
    //custom idf
    @Override
    public float idf(long docFreq, long docCount) {
        return (float)(Math.log((docCount+2)/(double)(docFreq+2)) + 1.0);
    }
}