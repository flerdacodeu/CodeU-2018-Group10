import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Collections;

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
     */
    public void addEdge(T parent, T child) {
        if(parent.compareTo(child) == 0) {
            return;
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
        parentVertex.addOutdegreeVertex(childVertex);
        childVertex.addIndegreeVertex(parentVertex);
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


    /**
     * This method is to find all possible orders of topological
     * of vertices of the graph
     * @return all topological orders of vertices of a graph
     */
    public List<List<T>> getAllTopologicalOrders() {
        List<List<T>> allOrderedVerticesVal = new ArrayList<>();
        List<T> orderedVerticesVal = new ArrayList<>();
        dfsForAllTopologicalOrders(orderedVerticesVal, allOrderedVerticesVal);
        vertexByValue.values().forEach(e -> e.setDFSState(VertexDFSState.UNVISITED));
        return allOrderedVerticesVal;
    }

    private void dfsForAllTopologicalOrders(List<T> orderedVerticesVal, List<List<T>> allOrderedVerticesVal) {
        if (orderedVerticesVal.size() == vertexByValue.size()) {
            allOrderedVerticesVal.add(new ArrayList<>(orderedVerticesVal));
        }
        for (Vertex vertex : vertexByValue.values()) {
            if (vertex.getDFSState() == VertexDFSState.UNVISITED && vertex.getIndegrees().size() == 0) {
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().remove(vertex));
                orderedVerticesVal.add(vertex.getValue());
                vertex.setDFSState(VertexDFSState.VISITED);
                dfsForAllTopologicalOrders(orderedVerticesVal, allOrderedVerticesVal);
                vertex.setDFSState(VertexDFSState.UNVISITED);
                orderedVerticesVal.remove(orderedVerticesVal.size() - 1);
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().add(vertex));
            }
        }
    }

    /**
     * This method is to find one of possible topological
     * orders (if a graph has more than one) of a graph vertices
     * @return topological ordered list of vertices of a graph
     */
    public List<T> getTopologicalOrder() {
        List<T> orderedVerticesVal = new ArrayList<>();
        for (T value : vertexByValue.keySet()) {
            Vertex vertex = vertexByValue.get(value);
            if (vertex.getDFSState() == VertexDFSState.UNVISITED) {
                vertex.setDFSState(VertexDFSState.VISITING);
                dfsForTopologicalOrder(vertexByValue.get(value), orderedVerticesVal);
                orderedVerticesVal.add(value);
                vertex.setDFSState(VertexDFSState.VISITED);
            }
        }
        Collections.reverse(orderedVerticesVal);
        vertexByValue.values().forEach(e -> e.setDFSState(VertexDFSState.UNVISITED));
        return orderedVerticesVal;
    }

    private void dfsForTopologicalOrder(Vertex currentVertex, List<T> orderedVerticesVal) {
        for (Vertex childVertex : vertexByValue.get(currentVertex.getValue()).getOutdegrees()) {
            if (childVertex.getDFSState() == VertexDFSState.VISITING) {
                throw new IllegalArgumentException("Dictionary is inconsistent");
            } else if (childVertex.getDFSState() == VertexDFSState.UNVISITED) {
                childVertex.setDFSState(VertexDFSState.VISITING);
                dfsForTopologicalOrder(childVertex, orderedVerticesVal);
                orderedVerticesVal.add(childVertex.getValue());
                childVertex.setDFSState(VertexDFSState.VISITED);
            }
        }
    }


    public Set<Constraint<T>> findCircuits() {
        Set<Constraint<T>> constraints = new HashSet<>();
        for (Vertex vertex : vertexByValue.values()) {
            if (vertex.DFSState == VertexDFSState.UNVISITED) {
                vertex.DFSState = VertexDFSState.VISITING;
                dfsForFindCircuits(vertex, null, constraints);
                vertex.DFSState = VertexDFSState.VISITED;
            }
        }
        vertexByValue.values().forEach(e -> e.setDFSState(VertexDFSState.UNVISITED));
        return constraints;
    }

    private void dfsForFindCircuits(Vertex current, Vertex parent, Set<Constraint<T>> constraints) {
        for (Vertex childVertex : vertexByValue.get(current.getValue()).getOutdegrees()) {
            if (childVertex.DFSState == VertexDFSState.VISITING) {
                 constraints.add(new Constraint<>(parent.value, current.value));
                 childVertex.DFSState = VertexDFSState.VISITED;
            } else if (childVertex.DFSState == VertexDFSState.UNVISITED) {
                childVertex.DFSState = VertexDFSState.VISITING;
                dfsForFindCircuits(childVertex, current, constraints);
                childVertex.DFSState = VertexDFSState.VISITED;
            }
        }
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
        private Set<Vertex> indegrees;
        private Set<Vertex> outdegrees;
        private VertexDFSState DFSState;

        public Vertex(T value) {
            this.value = value;
            this.indegrees = new HashSet<>();
            this.outdegrees = new HashSet<>();
            this.DFSState = VertexDFSState.UNVISITED;
        }

        public void addIndegreeVertex(Vertex vertex) {
            indegrees.add(vertex);
        }

        public void addOutdegreeVertex(Vertex vertex) {
            outdegrees.add(vertex);
        }

        public void setDFSState(VertexDFSState DFSState) {
            this.DFSState = DFSState;
        }

        public T getValue() {
            return value;
        }

        public Set<Vertex> getIndegrees() {
            return indegrees;
        }

        public Set<Vertex> getOutdegrees() {
            return outdegrees;
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
