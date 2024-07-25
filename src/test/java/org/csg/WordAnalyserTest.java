package org.csg;

import org.csg.analysis.WordAnalysisTask;
import org.csg.analysis.WordCountTask;
import org.csg.analysis.WordListTask;
import org.csg.filters.Filter;
import org.csg.filters.MatchesFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class WordAnalyserTest {

    private WordAnalyser wordAnalyser;
    private WordCountTask wordCountTask;
    private WordListTask wordListTask;
    private LinkedList<WordAnalysisTask> tasks;

    @BeforeEach
    public void setUp() {
        wordAnalyser = new WordAnalyser();

        Filter filter = new MatchesFilter("\\d+", "digits");

        wordCountTask = new WordCountTask(filter);
        wordListTask = new WordListTask(filter);

        tasks = new LinkedList<>();
        tasks.add(wordCountTask);
        tasks.add(wordListTask);
    }

    @Test
    public void testAnalyseProcessesWords() {
        // Analyse some words
        wordAnalyser.analyse(tasks, "123");
        wordAnalyser.analyse(tasks, "456");
        wordAnalyser.analyse(tasks, "abc");

        // Verify results
        assertEquals(2, wordCountTask.getCount(), "Count should be 2 for two matching words.");
        LinkedList<String> expectedList = new LinkedList<>();
        expectedList.add("123");
        expectedList.add("456");
        assertEquals(expectedList, wordListTask.getFilteredWordsList(), "List should contain only words that match the pattern.");
    }
}