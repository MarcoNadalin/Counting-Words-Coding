package org.csg;

import org.csg.analysis.WordAnalysisTask;
import org.csg.analysis.WordCountTask;
import org.csg.analysis.WordListTask;
import org.csg.filters.Filter;
import org.csg.filters.MatchesFilter;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        /* Process command line arguments */
        String sourceDirectory = args[0];
        String wordDelimiter = args[1];

        FileProcessor fileProcessor = new FileProcessor(sourceDirectory, wordDelimiter);

        WordAnalyser wordAnalyser = new WordAnalyser();

        /* CREATING FILTERS */
        Filter startsWithMFilter = new MatchesFilter(
                "^[Mm].*",
                "words that start with m or M"
        );
        Filter longerThan5Chars = new MatchesFilter(
                "^.{6,}$",
                "words that are greater than 5 characters"
        );

        /* CREATING LIST OF TASKS */
        WordAnalysisTask countTask = new WordCountTask(startsWithMFilter);
        WordAnalysisTask listTask = new WordListTask(longerThan5Chars);

        LinkedList<WordAnalysisTask> tasks = new LinkedList<>();
        tasks.add(countTask);
        tasks.add(listTask);

        /* Process and analyse file */
        String currentWord;
        while((currentWord = fileProcessor.getNextWord()) != null) {
            wordAnalyser.analyse(tasks, currentWord);
        }

        /* Display results */
        wordAnalyser.printResults(tasks);
    }
}