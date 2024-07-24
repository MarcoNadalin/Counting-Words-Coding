package org.csg.filters;

/**
 * The {@code Filter} interface represents a filter that can be applied to a word.
 * Implementations of this interface define specific criteria to
 * determine if a word matches the filter.
 */
public interface Filter {

    /**
     * Applies the filter to the given word.
     *
     * @param word the word to be passed through the filter
     * @return {@code true} if the word matches the filter criteria
     */
    boolean applyFilter(String word);

    /**
     * Returns a string representation of the filter.
     * This is useful for debugging and logging purposes to
     * understand what kind of filter is being used.
     *
     * @return a string description of the filter
     */
    @Override
    String toString();
}
