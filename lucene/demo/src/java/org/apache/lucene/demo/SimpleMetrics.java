package org.apache.lucene.demo;


import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public final class SimpleMetrics {
    public static long doc_freq;
    public static long total_term_freq;

   //function takes a query as input and return the document frequencies with the total term frequencies
    public SimpleMetrics(Term query) {
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
            doc_freq = reader.docFreq(query);
            total_term_freq = reader.totalTermFreq(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //take user input and return the frequencies of the term
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter query");
        String query = myObj.nextLine();
        SimpleMetrics simplemetrics = new SimpleMetrics(new Term("contents",query));
        System.out.println("Term:" + query);
        System.out.println("The number of documents containing the term: " + simplemetrics.doc_freq);
        System.out.println("The total number of occurrences of the term across all documents  "+ simplemetrics.total_term_freq);
    }

}