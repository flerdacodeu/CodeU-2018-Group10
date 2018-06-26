package assignment3;


import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a dictionary which is formed from a given list
 * of words.
 * This class helps to store the words efficiently and helps to get the
 * information about whether the given prefix or word are in the dictionary.
 * All the words in a dictionary are stored in a tree. If two words
 * have common prefix it means that they will have common branch.
 * E. g. if two words have only first letter in common it means that they will
 * have same parent.
 */
public class Dictionary {
    private TrieNode root;

    /**
     * Constructs a tree/dictionary from a given list of words.
     * All capital letters in words from a list will be converted to lover case ones.
     * @param words is a list of words that will be stored in a tree.
     */
    public Dictionary(List<String> words) {
        root = new TrieNode();
        for (String string : words) {
            TrieNode current = root;
            char[] word = string.toLowerCase().toCharArray();
            StringBuilder prefix = new StringBuilder("");
            for (int i = 0; i < word.length; i++) {
                char c = word[i];
                prefix.append(c);
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                } else {
                    TrieNode node = new TrieNode(prefix.toString());
                    current.children.put(c, node);
                    current = node;
                }
            }
            current.isWord = true;
        }
    }

    /**
     * This methods helps to efficiently detect whether the given prefix
     * is a prefix of at least one word in a dictionary.
     * @param prefix is a prefix that we want to check
     * @return true if a given prefix is a prefix of at least one word
     * from the dictionary
     */
    public boolean isPrefix(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * This method  helps to efficiently detect whether the given
     * word is in a dictionary.
     * @param word is a word that needs to be checked
     * @return true if a given word is in a dictionary
     */
    public boolean isWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return false;
            }
        }
        return current.isWord;
    }

    /**
     * This method is to get all words from a dictionary
     * @return a list of all words from a dictionary
     */
    public Set<String> getWords() {
        Set<String> words = new HashSet<>();
        TrieNode current = root;
        getWordsRecursive(current, words);
        return words;
    }

    /**
     * This method is a Depth First Search for all the words from a dictionary.
     * @param current is a node of a tree/dictionary from which the search starts from
     * @param words is a list of words where all founded words in a tree will be added
     */
    private void getWordsRecursive(TrieNode current, Set<String> words) {
        if (!current.children.isEmpty()) {
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                TrieNode node = entry.getValue();
                if (node.isWord) {
                    words.add(node.prefix);
                }
                getWordsRecursive(node, words);
            }
        }
    }

    /**
     * This method is to get all prefixes from a dictionary
     * @return a list of all prefixes of words from a dictionary
     */
    public Set<String> getPrefixes() {
        Set<String> prefixes = new HashSet<>();
        getPrefixesRecursive(root, prefixes);
        return prefixes;
    }

    /**
     * This method is a Depth First Search for all the prefixes from a dictionary.
     * @param current is a node of a tree/dictionary from which the search starts from
     * @param prefixes is a list of prefixes of all words from a dictionary
     * where all founded prefixes in a tree will be added
     */
    private void getPrefixesRecursive(TrieNode current, Set<String> prefixes) {
        if (!current.children.isEmpty()) {
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                TrieNode node = entry.getValue();
                prefixes.add(node.prefix);
                getPrefixesRecursive(node, prefixes);
            }
        }
    }

    /**
     * This class represents a node of a tree that will be constructed
     * from the list of words i order to make a dictionary.
     * Each node might have up to 26 children if only lower case
     * english letter are allowed and more than 26 if not.
     * Each node stores information about the current prefix
     * in a dictionary tree. It also stores an information whether
     * this prefix is a word in a dictionary
     */
    class TrieNode{
        private Map<Character, TrieNode> children;
        private String prefix;
        private boolean isWord;

        TrieNode() {
            this.children = new HashMap<>();
            this.prefix = "";
        }

        TrieNode(String prefix) {
            this.children = new HashMap<>();
            this.prefix = prefix;
        }
    }
}
