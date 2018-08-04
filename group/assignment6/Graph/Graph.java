package assignment6.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a directed graph data structure.
 * A graph can be described as a set of vertices. In case graph
 * is directed each vertex has its indegree and outdegree vertices.
 */
public class Graph<T extends Comparable<T>> {
    private Map<T, Vertex> vertexByValue;

    public Graph() {
        vertexByValue = new HashMap<>();
    }

    /**
     * This method adds a new edge between two vertices in a graph.
     * If a vertex with a value passed as method parameter doesn't
     * exist in a computed graph than it will be added automatically
     * @param parent is a parent vertex value
     * @param child is a child vertex value
     * @return true if a new edge has been added
     */
    public boolean addEdge(T parent, T child) {
        if(parent.compareTo(child) == 0) {
            return false;
        }
        Vertex parentVertex;
        Vertex childVertex;
        if (vertexByValue.containsKey(parent)) {
            parentVertex = vertexByValue.get(parent);
        } else {
            parentVertex = new Vertex(parent);
            vertexByValue.put(parent, parentVertex);
        }
        if (vertexByValue.containsKey(child)) {
            childVertex = vertexByValue.get(child);
        } else {
            childVertex = new Vertex(child);
            vertexByValue.put(child, childVertex);
        }
        ;
        return childVertex.addDegreeVertex(parentVertex) && parentVertex.addDegreeVertex(childVertex);
    }

    /**
     * This method adds a new vertex in a directed graph.
     *
     * Graph vertices are unique - only one vertex can be created for each T value
     * @param value is a value of a new vertex
     */
    public void addVertex(T value) {
        vertexByValue.put(value, new Vertex(value));
    }

    public boolean containsVertex(T vertexValue)
    {
        return vertexByValue.containsKey(vertexValue);
    }
    /**
     * This class represents a vertex of a graph.
     * Each vertex can be described by its character-value,
     * indegree vertices (set of vertices that can direct you to the current vertex)
     * and outdegree vertices (set of vertices that can be achieved from the current one).
     * DFSState tracks the DFS state of vertex during depth first search
     * in a graph.
     */
    private class Vertex {
        private T value;
        private Set<Vertex> degrees;
        private VertexDFSState DFSState;

        public Vertex(T value) {
            this.value = value;
            this.degrees = new HashSet<>();
            this.DFSState = VertexDFSState.UNVISITED;
        }

        public boolean addDegreeVertex(Vertex vertex) {
            return degrees.add(vertex);
        }

        public void setDFSState(VertexDFSState DFSState) {
            this.DFSState = DFSState;
        }

        public T getValue() {
            return value;
        }

        public Set<Vertex> geDegrees() {
            return degrees;
        }


        public VertexDFSState getDFSState() {
            return DFSState;
        }


    }
    /**
     * This is an enum of variety of labels that
     * graph vertices may have. It can save time and memory during
     * depth first search on the graph.
     * Unvisited state means that the vertex hasn't been explored yet.
     * Visiting state means that the search from this vertex has already been started
     * but hasn't been finished yet. This means that we have already visited this vertex
     * but we are to backtrack to it.
     * Visited state means that the search started in this vertex has already been finished.
     *
     * This enum can also be used to mark vertices simply as non-visited or
     * visited if there is no need to track the time when the search from this
     * vertex has been started and finished which can be done using Visiting state
     */
    private enum VertexDFSState {
        UNVISITED, VISITING, VISITED
    }

}