package assignment5;

import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a directed graph data structure.
 * A graph can be described as a set of vertecies. In case graph
 * is directed each vertex has its indegree and outdegree vertecies.
 * This class helps you to construct a directed graph and operate on it
 * easily.
 */
public class DirectedGraph {
    private Map<Character, Vertex> vertecies;

    public DirectedGraph() {
        vertecies = new HashMap<>();
    }

    /**
     * This method adds a new edge between two vertecies in a graph.
     * If a vertex with a character-value passed as method parameter doesn't
     * exist in a computed graph than it will be added automatically
     * @param parent is a parent vertex value
     * @param child is a child vertex value
     */
    public void addEdge(char parent, char child) {
        Vertex parentVertex;
        Vertex childVertex;
        if (vertecies.containsKey(parent)) {
            parentVertex = vertecies.get(parent);
        } else {
            parentVertex = new Vertex(parent);
            vertecies.put(parent, parentVertex);
        }
        if (vertecies.containsKey(child)) {
            childVertex = vertecies.get(child);
        } else {
            childVertex = new Vertex(child);
            vertecies.put(child, childVertex);
        }
        parentVertex.addOutdegreeVertex(childVertex);
        childVertex.addIndegreeVertex(parentVertex);
    }

    /**
     * This method adds a new vertex in a directed graph.
     * @param value is a character-value of a new vertex
     */
    public void addVertex(char value) {
        if(vertecies.containsKey(value)) {
            throw new IllegalArgumentException("Vertex " + value + " is already in a graph");
        }
        vertecies.put(value, new Vertex(value));
    }

    public Map<Character, Vertex> getVertecies() {
        return vertecies;
    }
}
