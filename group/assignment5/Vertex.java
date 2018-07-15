package assignment5;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a vertex of a graph.
 * Each vertex can be described by its character-value,
 * indegree vertecies (list of vertecies that can direct you to the current vertex)
 * and outdegree vertecies (list of vertecies that can be achieved from the current one)
 * Vertex label is a special label to track the state of vertex during depth first search
 * in a graph.
 */
public class Vertex {
    private char value;
    private List<Vertex> indegrees;
    private List<Vertex> outdegrees;
    private VertexLabel label;

    public Vertex(char value) {
        this.value = value;
        this.indegrees = new ArrayList<>();
        this.outdegrees = new ArrayList<>();
        this.label = VertexLabel.WHITE;
    }

    public void addIndegreeVertex(Vertex vertex) {
        indegrees.add(vertex);
    }

    public void addOutdegreeVertex(Vertex vertex) {
        outdegrees.add(vertex);
    }

    public void setLabel(VertexLabel label) {
        this.label = label;
    }

    public char getValue() {
        return value;
    }

    public List<Vertex> getIndegrees() {
        return indegrees;
    }

    public List<Vertex> getOutdegrees() {
        return outdegrees;
    }

    public VertexLabel getLabel() {
        return label;
    }
}
