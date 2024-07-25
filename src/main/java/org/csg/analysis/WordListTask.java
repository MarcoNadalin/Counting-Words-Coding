package org.csg.analysis;

import org.csg.filters.Filter;

import java.util.LinkedList;

/**
 * The {@code WordListTask} class implements the {@code WordAnalysisTask} interface to
 * create and maintain a list of words that match a specified filter.
 */
public class WordListTask implements WordAnalysisTask {

    private LinkedList<String> filteredWordsList;
    private Filter filter;

    /**
     * Constructs a {@code WordListTask} with the specified filter.
     *
     * @param filter the {@code Filter} used to determine which words to add to the list
     */
    public WordListTask(Filter filter) {
        this.filter = filter;
        this.filteredWordsList = new LinkedList<>();
    }

    /**
     * Processes the given word and adds it to the list if it matches the filter.
     *
     * @param word the word to be processed
     */
    @Override
    public void processWord(String word) {
        if (filter.applyFilter(word)) {
            filteredWordsList.add(word);
        }
    }

    /**
     * Prints the result of the word list analysis.
     */
    @Override
    public void printResult() {
        System.out.println("The " + filter.toString() + ": ");
        for (String word : filteredWordsList) {
            System.out.println(word);
        }
    }

    /**
     * Returns the list of words that match the filter.
     *
     * @return the {@code LinkedList} of filtered words
     */
    public LinkedList<String> getFilteredWordsList() {
        return this.filteredWordsList;
    }
}