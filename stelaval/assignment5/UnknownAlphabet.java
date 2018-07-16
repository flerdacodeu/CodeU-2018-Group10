package assignment5;

public class UnknownAlphabet {

  private static void printAlphabet(String[] words, int letters) {
    Graph graph = new Graph(letters);

    for (int i = 0; i < words.length; i++) {
      String first = words[i];
      String second = words[i + 1];
      for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
        if (first.charAt(j) != second.charAt(j)) {
          graph.addEdge(first.charAt(j) - 'a', second.charAt(j) - 'a');
          break;
        }
      }
    }
    graph.sort();
  }

  public static void main(String[] args) {
    String[] words = {"art", "rat", "cat", "car"};
    printAlphabet(words, 4);
  }
}
