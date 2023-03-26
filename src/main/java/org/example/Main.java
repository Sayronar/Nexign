package org.example;

import org.example.cdr.CdrReader;
import org.example.cdr.CdrSorter;

import java.io.IOException;

public class Main {
    private static final String INPUT_FILE_PATH = "src\\main\\resources\\cdr.txt";
    private static final String OUTPUT_DIRECTORY = "reports\\";
    public static void main(String[] args) throws IOException {
        CdrReader cdrReader = new CdrReader();
        CdrSorter cdrSorter = new CdrSorter();
        cdrSorter.generatePhoneNumberReports(cdrReader, INPUT_FILE_PATH, OUTPUT_DIRECTORY);
    }
}