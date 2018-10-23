package com.bdbene.graph.algo;

import java.util.PriorityQueue;

import com.bdbene.graph.AdjacencyMatrixGraph;
import com.bdbene.graph.Graph;
import com.bdbene.graph.util.UnionFind;
import com.bdbene.graph.Edge;

public class Kruskals {
    public static Graph MinimalSpanningTree(Graph graph) {
        int vertexCount = graph.vertexCount();
        Graph tree = new AdjacencyMatrixGraph(vertexCount);
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(graph.getAllEdges());
        UnionFind forest = new UnionFind(vertexCount);

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.remove();
            int vertexA = edge.getFirst();
            int vertexB = edge.getSecond();
            int parentA = forest.findParent(vertexA);
            int parentB = forest.findParent(vertexB);

            if (parentA != parentB) {
                forest.union(parentA, parentB);
                tree.addEdge(edge);
            }
        }

        return tree;
    }
}