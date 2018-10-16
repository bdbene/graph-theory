package com.bdbene.graph;

import java.util.ArrayList;

/*
 * A graph structure that uses an adjacency matrix to represent a weighted, undirected graph.
 * Only one edge between any two vertices. Vertices are allowed to have edges to themselves.
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
    public void addEdge(int vertexA, int vertexB, int weight) {
        checkVertexExistance(vertexA, vertexB);

        matrix[vertexA][vertexB] = weight;
        matrix[vertexB][vertexA] = weight;
    }

    // Removes an edge from the graph. If no edge exists, nothing happens.
    public void removeEdge(int vertexA, int vertexB) {
        checkVertexExistance(vertexA, vertexB);

        matrix[vertexA][vertexB] = 0;
        matrix[vertexB][vertexA] = 0;
    }

    public int vertexCount() {
        return matrix.length;
    }

    public ArrayList<Integer> getEdges(int vertex) {
        int height = matrix.length;

        if (vertex >= height) {
            throw new IndexOutOfBoundsException("Vertex must be in the graph.");
        }

        ArrayList<Integer> ret = new ArrayList<>(height);

        for (int i = 0; i < height; i++) {
            Integer val = matrix[vertex][i];

            if (val != 0) {
                ret.add(i);
            }
        }

        return ret;
    }

    public boolean edgeExists(int vertexA, int vertexB) {
        checkVertexExistance(vertexA, vertexB);

        return matrix[vertexA][vertexB] != 0;
    }

    public int edgeWeight(int vertexA, int vertexB) {
        checkVertexExistance(vertexA, vertexB);

        return matrix[vertexA][vertexB];
    }

    private void checkVertexExistance(int x, int y) {
        int height = matrix.length;

        if (x >= height || y >= height) {
            throw new IndexOutOfBoundsException("Cannot remove edge if both vertices are not in the graph.");
        }
    }
}