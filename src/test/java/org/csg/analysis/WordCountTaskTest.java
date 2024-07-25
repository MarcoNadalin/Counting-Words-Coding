package org.csg.analysis;

import org.csg.filters.Filter;
import org.csg.filters.MatchesFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordCountTaskTest {

    private Filter filter;
    private WordCountTask wordCountTask;

    @BeforeEach
    public void setUp() {
        // Create a concrete Filter implementation for testing
        filter = new MatchesFilter("\\d+", "digits");
        wordCountTask = new WordCountTask(filter);
    }

    @Test
    public void testProcessWordIncreasesCount() {
        // Process words and verify count
        wordCountTask.processWord("123");
        wordCountTask.processWord("456");
        wordCountTask.processWord("abc");

        // Expected count should be 2 because "123" and "456" match the pattern "\\d+"
        assertEquals(2, wordCountTask.getCount(), "Count should be 2 for two matching words.");
    }

    @Test
    public void testProcessWordDoesNotIncreaseCountForNonMatchingWords() {
        // Process words and verify count
        wordCountTask.processWord("abc");
        wordCountTask.processWord("def");

        // Expected count should be 0 because none of the words match the pattern "\\d+"
        assertEquals(0, wordCountTask.getCount(), "Count should be 0 for non-matching words.");
    }
}