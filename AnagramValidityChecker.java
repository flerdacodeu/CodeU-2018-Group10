package CodeUAss1Package;

import java.util.HashMap;

public class AnagramValidityChecker
{


    /**
     * @return true if two sentences are anagrams of one another.
     *
     *  Sentences are anagram of each other if each word in the resulting
     *  sentence is an anagram of one of the words in the original sentence
     */
    public static boolean isSentenceAnagram(String sentence1, String sentence2, boolean isCaseSensitive)
    {
        // split the string to the words they are containing
        String[] wordsOfSentence1 = sentence1.split(" ");
        String[] wordsOfSentence2 = sentence2.split(" ");

        if(wordsOfSentence1.length != wordsOfSentence2.length)
        {
            return false;
        }

        boolean[] isSent2WordAnagramFound = new boolean[wordsOfSentence2.length];

        for(int i = 0 ; i < wordsOfSentence1.length ; i++)
        {
            for(int j = 0 ; j < wordsOfSentence2.length ; j++)
            {
                // if current word has already found in the other sentence than
                // we should'nt check it again.
                if (isSent2WordAnagramFound[j]) {
                    continue;
                }

                // checks if the current word is anagram of the other, and mark it in
                // isSent2WordAnagramFound in case one is the anagram of the other.
                if(isWordAnagram(wordsOfSentence1[i], wordsOfSentence2[j],isCaseSensitive))
                {
                    isSent2WordAnagramFound[j] = true;
                    break;
                }
            }
        }

        // if all words in the second sentence are found as anagrams of
        // words in the first sentence and both have same amount of words),
        // than the sentences are anagrams of each other, else, they ar'nt.
        for(int i = 0 ; i < isSent2WordAnagramFound.length ; i++)
        {
            if(!isSent2WordAnagramFound[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if one string is an anagram of the other.
     *
     * Two words are anagrams of each other if they are made of the same
     * letters in a different order.
     */
    public static boolean isWordAnagram(String s1, String s2, boolean isCaseSensitive)
    {
        if(s1.length() != s2.length() ) {
            return false;
        }

        if(!isCaseSensitive)
        {
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();
        }


        // store the amount of occurrence of each letter in the first word
        HashMap<Character, Integer> charsCountInWord = new HashMap<>();
        for(int i = 0 ; i < s1.length() ; i++)
        {
            char c = s1.charAt(i);

            if(charsCountInWord.containsKey(c)) {
                charsCountInWord.put(c, charsCountInWord.get(c) + 1);
            }
            else {
                charsCountInWord.put(c, 1);
            }
        }


        // if the second word contains all the letter of the first one,
        // than its an anagram of the first word,
        // else, it is'nt and false will be returned
        for(int i = 0 ; i < s2.length() ; i++)
        {
            char c = s2.charAt(i);
            if (!charsCountInWord.containsKey(c) || charsCountInWord.get(c) == 0) {
                return false;
            }
            else    {
                charsCountInWord.put(c, charsCountInWord.get(c) -1);
            }
        }

        return true;
    }
}
