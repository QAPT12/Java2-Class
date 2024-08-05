package ca.nl.cna.quintin.java2.InClassPractices.NetworkExamples;

import java.io.*;

public class QuoteServer {
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start();
    }
}