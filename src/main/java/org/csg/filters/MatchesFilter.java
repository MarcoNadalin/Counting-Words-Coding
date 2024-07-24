package org.csg.filters;

/**
 * The {@code MatchesFilter} class implements the {@code Filter} interface to filter words
 * based on a regular expression pattern.
 */
public class MatchesFilter implements Filter {

    private String pattern;
    private String filterName;

    /**
     * Constructs a {@code MatchesFilter} with the specified pattern and filter name.
     *
     * @param pattern the regular expression pattern used to find matching words
     * @param filterName a descriptive name for the filter, used in output and logging
     */
    public MatchesFilter(String pattern, String filterName) {
        this.pattern = pattern;
        this.filterName = filterName;
    }

    /**
     * Applies the filter to the given word by checking if it matches the regular expression pattern.
     *
     * @param word the word to be filtered
     * @return {@code true} if the word matches the pattern, {@code false} otherwise
     */
    @Override
    public boolean applyFilter(String word) {
        return word.matches(pattern);
    }

    /**
     * Returns a string representation of this filter, describing the pattern it matches.
     *
     * @return a string describing the filter, including the pattern name
     */
    @Override
    public String toString() {
        return "words that match the pattern [" + filterName + "]";
    }
}
