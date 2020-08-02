/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.demo;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Filters {@link ClassicTokenizer} with {@link ClassicFilter}, {@link
 * LowerCaseFilter} and {@link StopFilter}, using a list of
 * English stop words.
 *
 * CMPT456Analyzer was named StandardAnalyzer in Lucene versions prior to 3.1.
 * As of 3.1, {@link StandardAnalyzer} implements Unicode text segmentation,
 * as specified by UAX#29.
 */
public final class CMPT456Analyzer extends StopwordAnalyzerBase {

    /** Default maximum allowed token length */
    public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

    private int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;

    /** An unmodifiable set containing some common English words that are usually not
     useful for searching. */
    public static final CharArraySet STOP_WORDS_SET = StopAnalyzer.ENGLISH_STOP_WORDS_SET;

    /** Builds an analyzer with the given stop words.
     * @param stopWords stop words */
    public CMPT456Analyzer(CharArraySet stopWords) {
        super(stopWords);
    }

    /** Builds an analyzer with the default stop words ({@link
     * #STOP_WORDS_SET}).
     */
    public CMPT456Analyzer() {
        this(STOP_WORDS_SET);
    }

    /** Builds an analyzer with the stop words from the given reader.
     * @see WordlistLoader#getWordSet(Reader)
     * @param stopwords Reader to read stop words from */
    public CMPT456Analyzer(Reader stopwords) throws IOException {
        this(loadStopwordSet(stopwords));
    }

    /**
     * Set maximum allowed token length.  If a token is seen
     * that exceeds this length then it is discarded.  This
     * setting only takes effect the next time tokenStream or
     * tokenStream is called.
     */
    public void setMaxTokenLength(int length) {
        maxTokenLength = length;
    }

    /**
     * @see #setMaxTokenLength
     */
    public int getMaxTokenLength() {
        return maxTokenLength;
    }

    @Override
    protected TokenStreamComponents createComponents(final String fieldName) {
        final ClassicTokenizer src = new ClassicTokenizer();
        src.setMaxTokenLength(maxTokenLength);
        TokenStream tok = new ClassicFilter(src);
        tok = new LowerCaseFilter(tok);
        ArrayList<String> list=new ArrayList<String>();//creating new generic arraylist
        try {
            File textfile = new File("lucene/demo/src/java/org/apache/lucene/demo/stopwords.txt");
            Scanner content = new Scanner(textfile);
            //add each line to list
            while (content.hasNextLine()) {
                String data = content.nextLine();
                list.add(data);

            }
            //file cloq
            // se
            content.close();
        //Error case
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        CharArraySet custom_stopset = new CharArraySet(list, false);//creating new chararrayset
        tok = new StopFilter(tok, custom_stopset);
        tok = new PorterStemFilter(tok);
        return new TokenStreamComponents(src, tok) {
            @Override
            protected void setReader(final Reader reader) {
                src.setMaxTokenLength(CMPT456Analyzer.this.maxTokenLength);
                super.setReader(reader);
            }
        };
    }

    @Override
    protected TokenStream normalize(String fieldName, TokenStream in) {
        return new LowerCaseFilter(in);
    }
}
