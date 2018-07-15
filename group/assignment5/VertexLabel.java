package assignment5;

/**
 * This is an enum of variety of labels that
 * graph vertecies may have. It can save time and memory during
 * depth first search on the graph.
 * White label means that the vertex hasn't been explored yet.
 * Gray label means that the search from this vertex has already been started
 * but hasn't been finished yet. This means that we have already visited this vertex
 * but we are to backtrack to it.
 * Black label means that the search started in this vertex has already been finished.
 *
 * This enum can also be used to mark vertecies as non-visited (white) or
 * visited (black) if there is no need to track the time when the search from this
 * vertex has been started and finished
 */
public enum VertexLabel {
    WHITE, GRAY, BLACK
}
