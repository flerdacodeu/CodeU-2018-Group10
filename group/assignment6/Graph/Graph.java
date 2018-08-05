package assignment6.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents an undirected graph data structure.
 */
public class Graph<T> {
  private Map<T, Vertex> vertexByValue;

  public Graph() {
    vertexByValue = new HashMap<>();
  }

  /**
   * Adds a new edge between two vertices in a graph.
   * If a vertex with the value passed doesn't
   * exist in the graph it will be added.
   * @param parent parent vertex value
   * @param child child vertex value
   * @return true if a new edge has been added
   */
  public boolean addEdge(T parent, T child) {
    if (parent.equals(child)) {
      return false;
    }
    
    Vertex parentVertex;
    Vertex childVertex;
    if (vertexByValue.containsKey(parent)) {
      parentVertex = vertexByValue.get(parent);
    } else {
      parentVertex = addVertex(parent);
    }
    if (vertexByValue.containsKey(child)) {
      childVertex = vertexByValue.get(child);
    } else {
      childVertex = addVertex(child);
    }

    boolean isNewEdgeForChild = childVertex.addAdjacentVertex(parentVertex);
    boolean isNewEdgeForParent = parentVertex.addAdjacentVertex(childVertex);
    return  isNewEdgeForChild && isNewEdgeForParent;
  }

  /**
   * Adds a new vertex to a graph.
   * Graph vertices are unique so only one vertex can be created for each T value.
   * @param value value of the new vertex
   */
  public Vertex addVertex(T value) {
    Vertex vertex = new Vertex(value);
    vertexByValue.put(value, vertex);
    return vertex;
  }

  /**
   * Returns true if the graph contains the vertex with the given value.
   * @param vertexValue value of the vertex
   */
  public boolean containsVertex(T vertexValue) {
    return vertexByValue.containsKey(vertexValue);
  }

  /**
   * Represents a vertex in a graph.
   */
  private class Vertex {
    private T value;
    // Adjacent vertices
    private Set<Vertex> neighbours;
    // The state of the vertex during depth first search in a graph.
    private DFSState DFSState;

    public Vertex(T value) {
      this.value = value;
      this.neighbours = new HashSet<>();
      this.DFSState = DFSState.UNVISITED;
    }

    public boolean addAdjacentVertex(Vertex vertex) {
      return neighbours.add(vertex);
    }
  }
  
  /**
   * Represents whether a vertex is unvisited, being visited or has been visited
   * during depth first search.
   * Unvisited means the vertex hasn't been explored yet.
   * Visiting means search from this vertex has started but hasn't finished yet,
   * so the search will backtrack to it.
   * Visited means the search that started from this vertex has finished.
   */
  private enum DFSState {
    UNVISITED, VISITING, VISITED
  }
}