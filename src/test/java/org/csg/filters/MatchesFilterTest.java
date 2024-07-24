package org.csg.filters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchesFilterTest {

    @Test
    public void testApplyFilterWithMatchingPattern() {
        MatchesFilter filter = new MatchesFilter("\\d+", "digits");
        assertTrue(filter.applyFilter("123"), "Filter should match digits.");
        assertTrue(filter.applyFilter("456"), "Filter should match digits.");
    }

    @Test
    public void testApplyFilterWithNonMatchingPattern() {
        MatchesFilter filter = new MatchesFilter("\\d+", "digits");
        assertFalse(filter.applyFilter("abc"), "Filter should not match non-digits.");
        assertFalse(filter.applyFilter("123abc"), "Filter should not match alphanumeric strings.");
    }

    @Test
    public void testApplyFilterWithEmptyPattern() {
        MatchesFilter filter = new MatchesFilter("", "empty");
        assertFalse(filter.applyFilter("word"), "Filter should not match any word with an empty pattern.");
    }
}