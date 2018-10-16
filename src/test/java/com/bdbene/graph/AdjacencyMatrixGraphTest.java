package com.bdbene.graph;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.bdbene.graph.AdjacencyMatrixGraph;

public class AdjacencyMatrixGraphTest {

    @Test
    public void TestConstructor_CorrectVertexCount() {
        int vertices = 10;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(vertices);

        assertEquals(graph.vertexCount(), vertices);
    }

    @Test
    public void TestConstructor_CompleteForestCreated() {
        int vertices = 10;

        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(vertices);

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                assertFalse(graph.edgeExists(i, j));
            }
        }
    }

    @Test
    public void TestCopyAdjacencyMatrix_GraphIsACopy() {
        int n = 100;
        int[][] adjacencyMatrix = new int[n][n];
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                adjacencyMatrix[i][j] = count;
                adjacencyMatrix[j][i] = count;

                count++;
            }
        }

        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(adjacencyMatrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                assertEquals(adjacencyMatrix[i][j], graph.edgeWeight(i, j));
            }
        }
    }

    @Test
    public void TestAddEdge_UndirectedEdgeWithWeightAdded() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        
        graph.addEdge(1, 4, -32);

        assertTrue(graph.edgeExists(1, 4));
        assertTrue(graph.edgeExists(4, 1));
        assertEquals(-32, graph.edgeWeight(1, 4));
        assertEquals(-32, graph.edgeWeight(4, 1));
    }

    @Test
    public void TestRemoveEdge_EdgeRemoved() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        graph.addEdge(1, 4, 32);
        
        graph.removeEdge(4, 1);

        assertFalse(graph.edgeExists(1, 4));
        assertFalse(graph.edgeExists(4, 1));
        assertEquals(0, graph.edgeWeight(1, 4));
        assertEquals(0, graph.edgeWeight(4, 1));
    }

    @Test
    public void TestGetEdgeList_AllEdgesForVertexRetreived() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        graph.addEdge(2, 6, 20);
        graph.addEdge(3, 8, -15);
        graph.addEdge(2, 4, -2);
        graph.addEdge(2, 9, 1);
        graph.addEdge(2, 2, 6);
        graph.addEdge(2, 1, 9);
        graph.removeEdge(2, 1);

        List<Integer> edges = graph.getEdges(2);

        assertEquals(4, edges.size());
        assertTrue(edges.contains(6));
        assertTrue(edges.contains(4));
        assertTrue(edges.contains(9));
        assertTrue(edges.contains(2));
    }
}