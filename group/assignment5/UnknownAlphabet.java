package assignment5;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

public class UnknownAlphabet {
    private Graph graph;

    public UnknownAlphabet() {
    }

    private void buildGraphFromDictionary(List<String> dictionary) {
        if(dictionary.isEmpty()) {
            throw new IllegalArgumentException("Dictionary cannot be empty");
        }
        graph = new Graph();
        Set<Character> setOfChars = new HashSet<>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                setOfChars.add(c);
            }
        }
        if(setOfChars.isEmpty()) {
            throw new IllegalArgumentException("There are no characters in the dictionary");
        }
        setOfChars.forEach(e -> graph.addVertex(e));
        addConstraints(dictionary);
    }

    private void addConstraints(List<String> dictionary) {
        for (int i = 0; i < dictionary.size() - 1; i++) {
            String firstWord = dictionary.get(i);
            String secondWord = dictionary.get(i + 1);
            for (int j = 0; j < Math.min(firstWord.length(), secondWord.length()); j++) {
                char firstWordChar = firstWord.charAt(j);
                char secondWordChar = secondWord.charAt(j);
                if(firstWordChar != secondWordChar) {
                    graph.addEdge(firstWordChar, secondWordChar);
                    break;
                }
            }
        }
    }

    public List<Character> getAlphabet(List<String> dictionary) {
        buildGraphFromDictionary(dictionary);
        return topologicalSort();
    }

    public List<List<Character>> getAllPossibleAlphabets(List<String> dictionary) {
        buildGraphFromDictionary(dictionary);
        return allTopologicalSorts();
    }

    private List<Character> topologicalSort() {
        List<Character> alphabet = new ArrayList<>();
        dfs(alphabet);
        Collections.reverse(alphabet);
        return alphabet;
    }

    private List<List<Character>> allTopologicalSorts() {
        List<List<Character>> alphabets = new ArrayList<>();
        List<Character> alphabet = new ArrayList<>();
        dfsForAllAlphabets(alphabet, alphabets);
        return alphabets;
    }

    private void dfs(List<Character> alphabet) {
        Map<Character, Vertex> vertecies = graph.getVertecies();
        for (char value : vertecies.keySet()) {
            Vertex vertex = vertecies.get(value);
            if (vertex.getLabel() == VertexLabel.WHITE) {
                vertex.setLabel(VertexLabel.GRAY);
                dfsRecursive(vertecies.get(value), alphabet);
                alphabet.add(value);
                vertex.setLabel(VertexLabel.BLACK);
            }
        }
    }

    private void dfsRecursive(Vertex currentVertex, List<Character> alphabet) {
        Map<Character, Vertex> vertecies = graph.getVertecies();
        for (Vertex childVertex : vertecies.get(currentVertex.getValue()).getOutdegrees()) {
            if (childVertex.getLabel() == VertexLabel.GRAY) {
                throw new IllegalArgumentException("Dictionary is inconsistent");
            } else if (childVertex.getLabel() == VertexLabel.WHITE) {
                childVertex.setLabel(VertexLabel.GRAY);
                dfsRecursive(childVertex, alphabet);
                alphabet.add(childVertex.getValue());
                childVertex.setLabel(VertexLabel.BLACK);
            }
        }
    }

    private void dfsForAllAlphabets(List<Character> alphabet, List<List<Character>> alphabets) {
        if (alphabet.size() == graph.getVertecies().size()) {
            alphabets.add(new ArrayList<>(alphabet));
        }
        Map<Character, Vertex> vertecies = graph.getVertecies();
        for (Vertex vertex : vertecies.values()) {
            if (vertex.getLabel() == VertexLabel.WHITE && vertex.getIndegrees().size() == 0) {
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().remove(vertex));
                alphabet.add(vertex.getValue());
                vertex.setLabel(VertexLabel.BLACK);
                dfsForAllAlphabets(alphabet, alphabets);
                vertex.setLabel(VertexLabel.WHITE);
                alphabet.remove(alphabet.size() - 1);
                vertex.getOutdegrees().forEach(child -> child.getIndegrees().add(vertex));
            }
        }
    }
}
