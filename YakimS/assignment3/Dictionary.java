package com.shaya;

import java.util.Set;

public class Dictionary
{
    private Set<String> prefixes, words;

    public Dictionary(Set<String> prefixes, Set<String> words)
    {
        this.prefixes = prefixes;
        this.words = words;
    }

    /**
     *
     * @param s - suspected word in the dictionary
     * @return true if the given string is a valid word in the dictionary
     */
    public boolean isWord(String s)
    {
        return words.contains(s);
    }

    /**
     *
     * @param s - suspected prefix of a word in the dictionary
     * @return true if the given string is a prefix of at least one word in the
     * dictionary. All words in the dictionary are prefixes of themselves
     */
    public boolean isPrefix(String s)
    {
        return prefixes.contains(s);
    }
}
