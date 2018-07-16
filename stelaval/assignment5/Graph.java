package assignment5;

import java.util.LinkedList;
import java.util.Stack;

public class Graph {

  private final LinkedList<Integer>[] graph;

  Graph(int numberOfVertices) {
    graph = new LinkedList[numberOfVertices];
    for (int i = 0; i < numberOfVertices; i++) {
      graph[i] = new LinkedList<>();
    }
  }

  void addEdge(int start, int end) {
    graph[start].add(end);
  }

  private void topologicalSort(int current, boolean[] visited, Stack<Integer> result) {
    visited[current] = true;

    for (int adjacent : graph[current]) {
      if (!visited[adjacent]) {
        topologicalSort(adjacent, visited, result);
      }
    }
    result.push(current);
  }

  Stack<Integer> sort() {
    Stack<Integer> result = new Stack<>();

    boolean[] visited = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!visited[i]) {
        topologicalSort(i, visited, result);
      }
    }
    return result;
  }

}
