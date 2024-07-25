package org.csg;

import org.csg.analysis.WordAnalysisTask;
import org.csg.analysis.WordCountTask;
import org.csg.analysis.WordListTask;
import org.csg.filters.Filter;
import org.csg.filters.MatchesFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordAnalyserIntegrationTest {

    private static final String SOURCE_FILE_DIR = "test.txt";
    private static final String WORD_DELIMITER = "\\s+";

    @AfterEach
    public void cleanUp() {
        File file = new File(SOURCE_FILE_DIR);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testCountWordsStartingWithM() {
        // Write sample text to the file for testing
        try (PrintWriter out = new PrintWriter(SOURCE_FILE_DIR)) {
            out.println("Marco Nadalin likes to watch Seinfeld and also play guitar in his free time. \n" +
                    "marco's favourite band is the Arctic Monkeys.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileProcessor fileProcessor = new FileProcessor(SOURCE_FILE_DIR, WORD_DELIMITER);
        WordAnalyser wordAnalyser = new WordAnalyser();

        // Create Filters
        Filter startsWithMFilter = new MatchesFilter(
                "^[Mm].*",
                "words that start with m or M"
        );

        // Create List of Tasks
        WordCountTask countTask = new WordCountTask(startsWithMFilter);
        LinkedList<WordAnalysisTask> tasks = new LinkedList<>();
        tasks.add(countTask);

        // Process and analyse file
        String currentWord;
        while((currentWord = fileProcessor.getNextWord()) != null) {
            wordAnalyser.analyse(tasks, currentWord);
        }

        // Check result
        assertEquals(3, countTask.getCount()); // Expecting 4 words starting with M or m
    }

    @Test
    public void testListWordsLongerThan5Chars() {
        // Write sample text to the file for testing
        try (PrintWriter out = new PrintWriter(SOURCE_FILE_DIR)) {
            out.println("Marco Nadalin likes to watch Seinfeld and also play guitar in his free time. \n" +
                    "marco's favourite band is the Arctic Monkeys.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileProcessor fileProcessor = new FileProcessor(SOURCE_FILE_DIR, WORD_DELIMITER);
        WordAnalyser wordAnalyser = new WordAnalyser();

        // Create Filters
        Filter longerThan5Chars = new MatchesFilter(
                "^.{6,}$",
                "words that are greater than 5 characters"
        );

        // Create List of Tasks
        WordListTask listTask = new WordListTask(longerThan5Chars);
        LinkedList<WordAnalysisTask> tasks = new LinkedList<>();
        tasks.add(listTask);

        // Process and analyse file
        String currentWord;
        while((currentWord = fileProcessor.getNextWord()) != null) {
            wordAnalyser.analyse(tasks, currentWord);
        }

        // Check result
        LinkedList<String> filteredWords = listTask.getFilteredWordsList();
        assertEquals(7, filteredWords.size()); // Expecting 7 words longer than 5 characters
        assertTrue(filteredWords.contains("Nadalin"));
        assertTrue(filteredWords.contains("Seinfeld"));
        assertTrue(filteredWords.contains("guitar"));
        assertTrue(filteredWords.contains("marco's"));
        assertTrue(filteredWords.contains("favourite"));
        assertTrue(filteredWords.contains("Arctic"));
        assertTrue(filteredWords.contains("Monkeys."));
    }
}