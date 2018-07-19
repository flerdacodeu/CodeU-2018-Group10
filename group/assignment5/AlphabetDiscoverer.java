package assignment5;

import java.util.List;
import java.util.Set;

/**
 * This class is to operate easily on a dictionary of
 * lexicographically ordered words. With the help of this class
 * an alphabet or all possible alphabets based on the order of words
 * can be constructed.
 */
public class AlphabetDiscoverer {
    private Graph<Character> graph;

    public AlphabetDiscoverer() {
    }

    private void buildGraphFromDictionary(List<String> dictionary) {
        if(dictionary.isEmpty()) {
            throw new IllegalArgumentException("Dictionary cannot be empty");
        }
        graph = new Graph<>();
        addAllCharsInDictionaryToGraph(dictionary);
        addConstraints(dictionary);
    }

    private void addAllCharsInDictionaryToGraph(List<String> dictionary) {
        for (String s : dictionary) {
            for (int i = 0 ; i < s.length() ; i++) {
                Character vertex = s.charAt(i);
                if (!graph.containsVertex(vertex)) {
                    graph.addVertex(vertex);
                }
            }
        }
    }

    /**
     *  Check every two adjacent words, and eliminate their biggest common prefix
     *  to find constraints, which will be added to the graph in the form of edges.
     *  If vertex U have out edge into vertex V, than U comes before V alphabetically
     *  @param dictionary - List of all the words in lexicographic order
     */
    private void addConstraints(List<String> dictionary) {
        for(int i = 0 ; i < dictionary.size()-1 ; i++) {
            String firstWord = dictionary.get(i);
            String secondWord = dictionary.get(i+1);
            int prefixLength = findMaxCommonPrefixLength(firstWord, secondWord);
            if(firstWord.length()<= prefixLength || secondWord.length()<= prefixLength) {
                continue;
            }
            else{
                char c1 = firstWord.charAt(prefixLength);
                char c2 = secondWord.charAt(prefixLength);
                graph.addEdge(c1,c2);
            }
        }
    }

    private int findMaxCommonPrefixLength(String s1, String s2) {
        int minLength = s1.length() <= s2.length() ? s1.length() : s2.length();
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return minLength;
    }

    public List<Character> getAlphabet(List<String> dictionary) {
        buildGraphFromDictionary(dictionary);
        return graph.getTopologicalOrder();
    }

    public List<List<Character>> getAllPossibleAlphabets(List<String> dictionary) {
        buildGraphFromDictionary(dictionary);
        return graph.getAllTopologicalOrders();
    }

    public Set<Constraint<Character>> getIllegalConstraints(List<String> dictionary) {
        buildGraphFromDictionary(dictionary);
        return graph.findCircuits();
    }


}
