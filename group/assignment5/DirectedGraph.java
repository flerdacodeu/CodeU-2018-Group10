package com.shaya.CodeUAss5Package;

import java.util.*;

public class DirectedGraph<T> {

    private HashMap<T,Vertex<T>> vertices;

    public DirectedGraph() {
        vertices = new HashMap<>();
    }

    public Vertex<T> addVertex(T value) {
        if(!vertices.containsKey(value)) {
            vertices.put(value,new Vertex<>(value));
        }
        return vertices.get(value);
    }

    public void addEdge(T from, T to) {
        // a vertex cant have edge to itself
        if(from.equals(to)) {
            return;
        }

        Vertex<T> fromVertex = addVertex(from);
        Vertex<T> toVertex = addVertex(to);

        fromVertex.out.add(toVertex);
        toVertex.in.add(fromVertex);
    }


    /**
     * Use a variation of DFS algorithm to find the topological order
     * of the vertices the graph contains
     * @return -  possible topological order of vertices value
     */
    public ArrayList getTopologicalSortedKeys() {
        Stack<T> stack = new Stack<>();

        for (Vertex<T> current : vertices.values()) {
            if(!current.visited) {
                getTopologicalSortedKeysHelper(current,stack);
            }
        }

        ArrayList<T> topologicallySortedKeys = new ArrayList<>();
        while (!stack.empty()){
            topologicallySortedKeys.add(stack.pop());
        }
        return topologicallySortedKeys;
    }
    private void getTopologicalSortedKeysHelper(Vertex<T> currentVertex, Stack<T> stack) {
        currentVertex.visited = true;

        for (Vertex<T> outVertex : currentVertex.out) {
            if (!outVertex.visited) {
                getTopologicalSortedKeysHelper(outVertex, stack);
            }
        }

        stack.push(currentVertex.value);
    }

    private class Vertex<T>{
        private HashSet<Vertex<T>> in;
        private HashSet<Vertex<T>> out;
        private T value;
        private boolean visited;

        public Vertex(T value) {
            this.in = new HashSet<>();
            this.out = new HashSet<>();
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vertex<T> vertex = (Vertex<T>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
