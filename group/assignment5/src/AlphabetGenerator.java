
import java.util.ArrayList;
import java.util.List;

public class AlphabetGenerator{

    private DirectedGraph<Character> dirGraph;

    public AlphabetGenerator(List<String> dictionary) {
            dirGraph = new DirectedGraph();
            addAllCharsInDicToGraph(dictionary);
            createConstrainsGraph(dictionary);
    }

    public ArrayList getAlphabetOrder()
    {
        return dirGraph.getTopologicalSortedKeys();
    }


    private void addAllCharsInDicToGraph(List<String> dictionary) {
        for (String s : dictionary) {
            for(int i = 0 ; i < s.length() ; i++) {
                dirGraph.addVertex(s.charAt(i));
            }
        }
    }

    /**
     *  Check every 2 adjecent words, and eliminte their max common prefix
     *  to find constrains, which will be added to the graph in
     *  the form of edges -
     *  If vertx X have out edge into vertex Y, than X comes before Y alphabetically
     * @param dictionary - List of all the words in lexicographic order
     */
    private void createConstrainsGraph(List<String> dictionary) {

        for(int i = 0 ; i < dictionary.size()-1 ; i++) {
            String word1 = dictionary.get(i);
            String word2 = dictionary.get(i+1);

            int prefixLength = findMaxCommonPrefixLength(word1, word2);

            if(word1.length()<= prefixLength || word2.length()<= prefixLength) {
                continue;
            }
            else{
                    char c1 = word1.charAt(prefixLength);
                    char c2 = word2.charAt(prefixLength);
                    dirGraph.addEdge(c1,c2);
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

}
