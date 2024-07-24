package org.csg.analysis;

import org.csg.filters.Filter;

/**
 * The {@code WordCountTask} class implements the {@code WordAnalysisTask} interface to
 * count the number of words that match a specified filter.
 */
public class WordCountTask implements WordAnalysisTask {

    private int count = 0;
    private Filter filter;

    /**
     * Constructs a {@code WordCountTask} with the specified filter.
     *
     * @param filter the {@code Filter} to be used for determining which words to count
     */
    public WordCountTask(Filter filter) {
        this.filter = filter;
    }

    /**
     * Processes the given word and increments the count if the word matches the filter.
     *
     * @param word the word to be processed
     */
    @Override
    public void processWord(String word) {
        if (filter.applyFilter(word)) {
            count++;
        }
    }

    /**
     * Prints the resultant word count.
     */
    @Override
    public void printResult() {
        System.out.println("The number of " + filter.toString() + ": " + count);
    }

    /**
     * Returns the current count of words that match the filter.
     *
     * @return the count of matching words
     */
    public int getCount() {
        return this.count;
    }
}
