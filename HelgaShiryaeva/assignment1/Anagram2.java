package assignment1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Anagram {
    private static final String PUNCTUATION_MARKS = " ,?!.:";

    /**
     * Checks if each word from the first string is an anagram of one of the words
     * from the second one. Strings are considered to be anagrams this way.
     * Supports punctuation marks : ?!,.:
     * @param first is the first string to be checked if it is an anagram of
     * @param second string
     * @return true if each word from the first string is an anagram of one
     * of the words from the second string
     */
    public boolean areAnagramsByWords(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        StringTokenizer stringTokenizerFirst = new StringTokenizer(first, PUNCTUATION_MARKS );
        StringTokenizer stringTokenizerSecond = new StringTokenizer(second, PUNCTUATION_MARKS);
        if (stringTokenizerFirst.countTokens() != stringTokenizerSecond.countTokens()) {
            return false;
        }
        Map<Character, Integer> characterCountMap = new HashMap<>();
        List<String> firstStringWords = new ArrayList<>();
        List<String> secondStringWords = new ArrayList<>();
        while (stringTokenizerFirst.hasMoreTokens()) {
            firstStringWords.add(stringTokenizerFirst.nextToken());
            secondStringWords.add(stringTokenizerSecond.nextToken());
        }
        return areAnagramsByAtLeastOneWord(firstStringWords, secondStringWords, characterCountMap) &&
                areAnagramsByAtLeastOneWord(secondStringWords, firstStringWords, characterCountMap);
    }

    /**
     * Checks if each word from the second string is an anagram for at least one
     * word from the first string
     * @param firstStringWords is a list of tokens (words) from the first string
     * @param secondStringWords is a list of tokens (words) from the seconds string
     * @return true if each words from the second string is an anagram of any word from 
     * the first string
     */
    private boolean areAnagramsByAtLeastOneWord(List<String> firstStringWords, List<String> secondStringWords,
                                                Map<Character, Integer> characterCountMap) {
        boolean isAnagram;
        for (String wordFromFirstString : firstStringWords) {
            isAnagram = false;
            for (String wordFromSecondString : secondStringWords) {
                if (areWordsAnagrams(wordFromSecondString, wordFromFirstString, characterCountMap)) {
                    isAnagram = true;
                    break;
                }
            }
            if (!isAnagram) {
                return false;
            }
        }
        return true;
    }

    private boolean areWordsAnagrams(String first, String second, Map<Character, Integer> characterCountMap) {
        characterCountMap.clear();
        for (char c : first.toCharArray()) {
            Integer count = characterCountMap.getOrDefault(c, 0);
            characterCountMap.put(c, count + 1);
        }
        for (char c : second.toCharArray()) {
            Integer count = characterCountMap.get(c);
            if (count == null || count == 0) {
                return false;
            }
            characterCountMap.put(c, characterCountMap.get(c) - 1);
        }
        for (Integer value : characterCountMap.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
