import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    System.out.println();
    assertTrue(resultAlphabet.containsAll(Arrays.asList('A', 'R', 'T', 'C')));
    assertTrue(resultAlphabet.indexOf('A') < resultAlphabet.indexOf('R'));
    assertTrue(resultAlphabet.indexOf('R') < resultAlphabet.indexOf('C'));
    assertTrue(resultAlphabet.indexOf('T') < resultAlphabet.indexOf('R'));
  }

  @Test
  public void topologicalSortWithTwoPossibleFirstLetters() {
    DirectedGraph graph = new DirectedGraph();
    graph.addVertices(Arrays.asList('A', 'B', 'C'));
    graph.addEdge('A', 'C');
    graph.addEdge('B', 'C');
    List<Character> resultAlphabet = graph.topologicalSort();
    for (char letter : resultAlphabet) {
      System.out.print(letter + " ");
    }
    System.out.println();
    assertTrue(resultAlphabet.containsAll(Arrays.asList('A', 'B', 'C')));
    assertTrue(resultAlphabet.indexOf('A') < resultAlphabet.indexOf('C'));
    assertTrue(resultAlphabet.indexOf('B') < resultAlphabet.indexOf('C'));
  }

  @Test
  public void canPerformMoreComplexTopologicalSort() {
    DirectedGraph graph = new DirectedGraph();
    graph.addVertices(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));
    graph.addEdge('A', 'C');
    graph.addEdge('B', 'C');
    graph.addEdge('B', 'D');
    graph.addEdge('C', 'E');
    graph.addEdge('D', 'F');
    graph.addEdge('E', 'F');
    graph.addEdge('E', 'H');
    graph.addEdge('F', 'G');
    List<Character> resultAlphabet = graph.topologicalSort();
    for (char letter : resultAlphabet) {
      System.out.print(letter + " ");
    }
    System.out.println();
    assertTrue(resultAlphabet.containsAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')));
    assertTrue(resultAlphabet.indexOf('A') < resultAlphabet.indexOf('C'));
    assertTrue(resultAlphabet.indexOf('B') < resultAlphabet.indexOf('C'));
    assertTrue(resultAlphabet.indexOf('B') < resultAlphabet.indexOf('D'));
    assertTrue(resultAlphabet.indexOf('C') < resultAlphabet.indexOf('E'));
    assertTrue(resultAlphabet.indexOf('D') < resultAlphabet.indexOf('F'));
    assertTrue(resultAlphabet.indexOf('E') < resultAlphabet.indexOf('F'));
    assertTrue(resultAlphabet.indexOf('E') < resultAlphabet.indexOf('H'));
    assertTrue(resultAlphabet.indexOf('F') < resultAlphabet.indexOf('G'));
  }


}
