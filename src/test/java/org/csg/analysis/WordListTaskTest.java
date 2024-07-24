package org.csg.analysis;

import org.csg.filters.Filter;
import org.csg.filters.MatchesFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class WordListTaskTest {

    private Filter filter;
    private WordListTask wordListTask;

    @BeforeEach
    public void setUp() {
        filter = new MatchesFilter("\\d+", "digits");
        wordListTask = new WordListTask(filter);
    }

    @Test
    public void testProcessWordAddsMatchingWordsToList() {
        // Process words and verify list content
        wordListTask.processWord("123");
        wordListTask.processWord("456");
        wordListTask.processWord("abc");

        LinkedList<String> expectedList = new LinkedList<>();
        expectedList.add("123");
        expectedList.add("456");

        assertEquals(expectedList, wordListTask.getFilteredWordsList(), "List should contain only words that match the pattern.");
    }

    @Test
    public void testProcessWordDoesNotAddNonMatchingWords() {
        // Process words and verify list content
        wordListTask.processWord("abc");
        wordListTask.processWord("def");

        LinkedList<String> expectedList = new LinkedList<>();

        assertEquals(expectedList, wordListTask.getFilteredWordsList(), "List should be empty for non-matching words.");
    }

}