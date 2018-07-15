import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DirectedGraphTest {
  @Test
  public void canPerformBasicTopologicalSort() {
    DirectedGraph graph = new DirectedGraph();
    graph.addVertices(Arrays.asList('A', 'R', 'T', 'C'));
    graph.addEdge('A', 'R');
    graph.addEdge('R', 'C');
    graph.addEdge('T', 'R');
    List<Character> resultAlphabet = graph.topologicalSort();
    for (char letter : resultAlphabet) {
      System.out.print(letter + " ");
    }
  }
}
