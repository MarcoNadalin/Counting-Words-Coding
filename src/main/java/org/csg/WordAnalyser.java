package org.csg;

import org.csg.analysis.WordAnalysisTask;

import java.util.LinkedList;

/**
 * The {@code WordAnalyser} class provides methods for processing words through a list of
 * {@code WordAnalysisTask} objects and printing the results.
 */
public class WordAnalyser {

    /**
     * Default constructor.
     */
    public WordAnalyser() {
    }

    /**
     * Processes the given word through each {@code WordAnalysisTask} in the provided list.
     *
     * @param tasks the list of {@code WordAnalysisTask} objects that will process the word
     * @param word  the word to be processed by the tasks
     */
    public void analyse(LinkedList<WordAnalysisTask> tasks, String word) {
        for (WordAnalysisTask task : tasks) {
            task.processWord(word);
        }
    }

    /**
     * Prints the results of all {@code WordAnalysisTask} objects in the provided list.
     * Each task's result is printed, followed by a separator line for clarity.
     *
     * @param tasks the list of {@code WordAnalysisTask} objects whose results will be printed
     */
    public void printResults(LinkedList<WordAnalysisTask> tasks) {
        for (WordAnalysisTask task : tasks) {
            task.printResult();
            System.out.println("=======================");
        }
    }
}
