import java.util.*;

public class AlphabetFinder<T> {
  List<List<T>> dictionary;

  public AlphabetFinder() {
    this.dictionary = new ArrayList<>();
  }

  public void addWord(List<T> word) {
    dictionary.add(word);
  }

  public List<T> getAlphabet() {
    DirectedGraph graph = new DirectedGraph();
    Set<T> unorderedAlphabet = new HashSet<>();
    for (List<T> word : dictionary) {
      for (T letter : word) {
        unorderedAlphabet.add(letter);
      }
    }
    graph.addVertices(new ArrayList(unorderedAlphabet));
    for(int word = 0; word < dictionary.size() - 1; word++) {
      List<T> word1 = dictionary.get(word);
      List<T> word2 = dictionary.get(word + 1);
      for (int letter = 0; letter < Math.min(word1.size(), word2.size()); letter++) {
        T letter1 = word1.get(letter);
        T letter2 = word2.get(letter);
        if (!letter1.equals(letter2)) {
          graph.addEdge(letter1, letter2);
        }
      }
    }
    return graph.topologicalSort();
  }
}
