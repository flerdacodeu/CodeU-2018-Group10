
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;


/**
 * This class represents a directed graph data structure.
 * A graph can be described as a set of vertecies. In case graph
 * is directed each vertex has its indegree and outdegree vertecies.
 * This class helps you to construct a directed graph and operate on it
 * easily.
 */
public class Graph<T extends Comparable<T>> {
    private Map<T, Vertex> vertecies;

    public Graph() {
        vertecies = new HashMap<>();
    }

    /**
     * This method adds a new edge between two vertecies in a graph.
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

    public void removeEdge(T parent, T child) {
        Vertex childVertex = vertecies.get(child);
        Vertex parentVertex = vertecies.get(parent);
        parentVertex.outdegrees.remove(childVertex);
        childVertex.indegrees.remove(parentVertex);
    }

    /**
     * This method adds a new vertex in a directed graph.
     * @param value is a value of a new vertex
     */
    public void addVertex(T value) {
        if(vertecies.containsKey(value)) {
            throw new IllegalArgumentException("Vertex " + value + " is already in a graph");
        }
        vertecies.put(value, new Vertex(value));
    }

    public Map<T, Vertex> getVertecies() {
        return vertecies;
    }

    public boolean containsVertex(T value) {
        return vertecies.containsKey(value);
    }

    /**
     * This method is to find one of possible topological
     * orders (if a graph has more than one) of a graph vertecies
     * @return topological ordered list of vertecies of a graph
     */
    public List<T> getTopologicalOrder() {
        List<T> alphabet = new ArrayList<>();
        dfs(alphabet);
        Collections.reverse(alphabet);
        vertecies.values().forEach(e -> e.setState(VertexState.UNVISITED));
        return alphabet;
    }

    /**
     * This method is to find all possible orders of topological
     * of vertecies of the graph
     * @return all topological orders of vertecies of a graph
     */
    public List<List<T>> getAllTopologicalOrders() {
        List<List<T>> alphabets = new ArrayList<>();
        List<T> alphabet = new ArrayList<>();
        dfsForAllTopologicalOrders(alphabet, alphabets);
        vertecies.values().forEach(e -> e.setState(VertexState.UNVISITED));
        return alphabets;
    }

    private void dfs(List<T> alphabet) {
        for (T value : vertecies.keySet()) {
            Vertex vertex = vertecies.get(value);
            if (vertex.getState() == VertexState.UNVISITED) {
                vertex.setState(VertexState.VISITING);
                dfsRecursive(vertecies.get(value), alphabet);
                alphabet.add(value);
                vertex.setState(VertexState.VISITED);
            }
        }
    }

    private void dfsRecursive(Vertex currentVertex, List<T> alphabet) {
        for (Vertex childVertex : vertecies.get(currentVertex.getValue()).getOutdegrees()) {
            if (childVertex.getState() == VertexState.VISITING) {
                throw new IllegalArgumentException("Dictionary is inconsistent");
            } else if (childVertex.getState() == VertexState.UNVISITED) {
                childVertex.setState(VertexState.VISITING);
                dfsRecursive(childVertex, alphabet);
                alphabet.add(childVertex.getValue());
                childVertex.setState(VertexState.VISITED);
            }
        }
    }

    private void dfsForAllTopologicalOrders(List<T> alphabet, List<List<T>> alphabets) {
        if (alphabet.size() == vertecies.size()) {
            alphabets.add(new ArrayList<>(alphabet));
        }
        for (Vertex vertex : vertecies.values()) {
            if (vertex.getState() == VertexState.UNVISITED && vertex.getIndegrees().size() == 0) {
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().remove(vertex));
                alphabet.add(vertex.getValue());
                vertex.setState(VertexState.VISITED);
                dfsForAllTopologicalOrders(alphabet, alphabets);
                vertex.setState(VertexState.UNVISITED);
                alphabet.remove(alphabet.size() - 1);
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().add(vertex));
            }
        }
    }

    public Set<Constraint<T>> breakCircuitsAndReturnConstraints() {
        Set<Constraint<T>> constraints = new HashSet<>();
        for (Vertex vertex : vertecies.values()) {
            if (vertex.state == VertexState.UNVISITED) {
                vertex.state = VertexState.VISITING;
                dfsWithCircuitBreakRecursive(vertex, null, constraints);
                vertex.state = VertexState.VISITED;
            }
        }
        vertecies.values().forEach(e -> e.setState(VertexState.UNVISITED));
        return constraints;
    }

    private void  dfsWithCircuitBreakRecursive(Vertex current, Vertex parent, Set<Constraint<T>> constraints) {
        for (Vertex childVertex : vertecies.get(current.getValue()).getOutdegrees()) {
            if (childVertex.state == VertexState.VISITING) {
                 constraints.add(new Constraint<>(parent.value, current.value));
            } else if (childVertex.state == VertexState.UNVISITED) {
                childVertex.state = VertexState.VISITING;
                dfsWithCircuitBreakRecursive(childVertex, current, constraints);
                childVertex.state = VertexState.VISITED;
            }
        }
    }

    /**
     * This class represents a vertex of a graph.
     * Each vertex can be described by its character-value,
     * indegree vertecies (list of vertecies that can direct you to the current vertex)
     * and outdegree vertecies (list of vertecies that can be achieved from the current one)
     * Vertex state is a special state to track the state of vertex during depth first search
     * in a graph.
     */
    public class Vertex {
        private T value;
        private List<Vertex> indegrees;
        private List<Vertex> outdegrees;
        private VertexState state;

        public Vertex(T value) {
            this.value = value;
            this.indegrees = new ArrayList<>();
            this.outdegrees = new ArrayList<>();
            this.state = VertexState.UNVISITED;
        }

        public void addIndegreeVertex(Vertex vertex) {
            indegrees.add(vertex);
        }

        public void addOutdegreeVertex(Vertex vertex) {
            outdegrees.add(vertex);
        }

        public void setState(VertexState state) {
            this.state = state;
        }

        public T getValue() {
            return value;
        }

        public List<Vertex> getIndegrees() {
            return indegrees;
        }

        public List<Vertex> getOutdegrees() {
            return outdegrees;
        }

        public VertexState getState() {
            return state;
        }
    }

}
