package org.csg;

import java.io.*;
import java.util.Scanner;

/**
 * The {@code FileProcessor} class handles reading words from a file using a specified delimiter.
 */
public class FileProcessor {

    private Scanner scanner;
    private String wordDelimiter;
    private String sourceFileDir;

    /**
     * Constructs a {@code FileProcessor} for the specified file and delimiter.
     *
     * @param sourceFileDir the path to the source file
     * @param wordDelimiter the delimiter used to define word boundaries
     */
    public FileProcessor(String sourceFileDir, String wordDelimiter) {
        this.sourceFileDir = sourceFileDir;
        this.wordDelimiter = wordDelimiter;
        this.openFile();
    }

    /**
     * Opens the source file and sets the delimiter to define what separates each word.
     * If the file is not found, a {@code RuntimeException} is thrown.
     */
    public void openFile() {
        try {
            scanner = new Scanner(new File(sourceFileDir));
            scanner.useDelimiter(wordDelimiter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File at: [" + sourceFileDir + "] not found. " + e);
        }
    }

    /**
     * Retrieves the next word from the file.
     * If there are no more words, the scanner is closed and {@code null} is returned.
     *
     * @return the next word from the file, or {@code null} if no more words are available
     */
    public String getNextWord() {
        try {
            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                scanner.close();
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
