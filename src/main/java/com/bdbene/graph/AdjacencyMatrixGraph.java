package com.bdbene.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * A graph structure that uses an adjacency matrix to represent a weighted, undirected graph.
 * Only one edge between any two vertices is allowed. Vertices are allowed to have edges to themselves.
 * Negative weights are allowed, though weights of zero are assumed to mean an edge doesn't exist.
 */
public class AdjacencyMatrixGraph implements Graph {
    private final int[][] matrix;

    // Creates a new Graph initialized to the given size;
    public AdjacencyMatrixGraph(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Graph must be initialized to at least 1 vertex, requested: " + size);
        }

        matrix = new int[size][size];
    }

    // Creates a new graph from an existing adjacency matrix. 
    public AdjacencyMatrixGraph(int[][] adjacencyMatrix) {
        int height = adjacencyMatrix.length;
        matrix = new int[height][height];

        for (int i = 0; i < height; ++i) {
            if (matrix[i].length != height) {
                throw new IllegalArgumentException("Matrix has to be square.");
            }

            System.arraycopy(adjacencyMatrix[i], 0, matrix[i], 0, height);
        }
    }

    // Adds an edge between two existing vertices. If an edge already exists, it is overwritten.
    public void addEdge(Edge edge) {
        addEdge(edge.getFirst(), edge.getSecond(), edge.getWeight());
    }

    // Adds an edge between two existing vertices. If an edge already exists, it is overwritten.
    public void addEdge(int vertexA, int vertexB, int weight) {
        checkEdgeValidity(vertexA, vertexB);

        matrix[vertexA][vertexB] = weight;
        matrix[vertexB][vertexA] = weight;
    }

    // Removes an edge from the graph. If no edge exists, nothing happens.
    public void removeEdge(Edge edge) {
        removeEdge(edge.getFirst(), edge.getSecond());
    }

    // Removes an edge from the graph. If no edge exists, nothing happens.
    public void removeEdge(int vertexA, int vertexB) {
        checkEdgeValidity(vertexA, vertexB);

        matrix[vertexA][vertexB] = 0;
        matrix[vertexB][vertexA] = 0;
    }

    // Returns the amount of vertices. 
    public int vertexCount() {
        return matrix.length;
    }

    // Returns the list of edges of a vertex.
    public List<Edge> getEdges(int vertex) {
        int height = matrix.length;

        checkVertexValidity(vertex);

        List<Edge> ret = new ArrayList<>(height);

        for (int i = 0; i < height; i++) {
            int weight = matrix[vertex][i];

            if (weight != 0) {
                ret.add(new Edge(vertex, i, weight));
            }
        }

        return ret;
    }

    // Check if an edge between two vertices exists.
    public boolean edgeExists(Edge edge) {
        return edgeExists(edge.getFirst(), edge.getSecond());
    }

    // Check if an edge between two vertices exists.
    public boolean edgeExists(int vertexA, int vertexB) {
        checkEdgeValidity(vertexA, vertexB);

        return matrix[vertexA][vertexB] != 0;
    }

    // Get the weight of an edge.
    public int edgeWeight(Edge edge) {
        return edgeWeight(edge.getFirst(), edge.getSecond());
    }

    // Get the weight of an edge.
    public int edgeWeight(int vertexA, int vertexB) {
        checkEdgeValidity(vertexA, vertexB);

        return matrix[vertexA][vertexB];
    }

    // Returns the degree of a vertex, which is the amount of edges the vertex has.
    public int degree(int vertex) {
        int height = matrix.length;
        int degree = 0;
        
        for (int i = 0; i < height; i++) {
            if (edgeExists(vertex, i)) {
                degree++;
                
                if (vertex == i) {
                    degree++;
                }
            }
        }

        return degree;
    }

    private void checkVertexValidity(int x) {
        if (x >= matrix.length) {
            throw new IndexOutOfBoundsException("Vertex does not exist.");
        }
    }

    private void checkEdgeValidity(int x, int y) {
        int height = matrix.length;

        if (x >= height || y >= height) {
            throw new IndexOutOfBoundsException("Edge must be between two existing vertices.");
        }
    }
}