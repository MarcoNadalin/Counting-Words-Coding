package org.csg.analysis;

/**
 * The {@code WordAnalysisTask} interface represents a task that processes words for analysis.
 * Implementations of this interface define how to process words upon filtering.
 */
public interface WordAnalysisTask {

    /**
     * Processes the given word as part of the analysis task.
     * The implementation should define how the word is analyzed or counted based on
     * the specific requirements of the task.
     *
     * @param word the word to be processed
     */
    void processWord(String word);

    /**
     * Prints the result of the analysis performed by this task.
     * The implementation should define how to output the results, such as printing
     * counts, lists, or other relevant information.
     */
    void printResult();
}
