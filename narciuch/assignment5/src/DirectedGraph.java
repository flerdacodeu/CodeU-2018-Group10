import java.util.*;

public class DirectedGraph<T> {

  //Map from the vertex's value to a list of it's adjacent vertices
  private final Map<T, List<T>> vertices;

  public DirectedGraph() {
    this.vertices = new HashMap<>();
  }


  public void addVertex(T value) {
    vertices.put(value, new ArrayList<>());
  }

  public void addVertices(List<T> values) {
    for (T value : values) {
      addVertex(value);
    }
  }

  public void addEdge(T from, T to) {
    vertices.get(from).add(to);
  }

  private void topologicalSortHelper(T vertex, Deque<T> stack, Map<T, Boolean> visited) {
    if (visited.get(vertex)) {
      return;
    }
    visited.put(vertex, true); //Mark vertex as visited

    //Recursively visit all children
    for(T adjacentVertex : vertices.get(vertex)) {
      topologicalSortHelper(adjacentVertex, stack, visited);
    }

    stack.push(vertex);
  }

  public List<T> topologicalSort() {
    Deque<T> stack = new ArrayDeque<>();
    Map<T, Boolean> visited = new HashMap<>();

    //Ensure all vertices are initially not visited
    for(T vertex : vertices.keySet()) {
      visited.put(vertex, false);
    }

    //Do recursive sort
    for(T vertex : vertices.keySet()) {
      topologicalSortHelper(vertex, stack, visited);
    }

    List<T> alphabet = new ArrayList<>();
    while (!stack.isEmpty()) {
      alphabet.add(stack.pop());
    }
    return alphabet;
  }
}
