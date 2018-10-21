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
        Edge edge1 = new Edge(2, 6, 20);
        Edge edge2 = new Edge(3, 8, -15);
        Edge edge3 = new Edge(2, 4, -2);
        Edge edge4 = new Edge(2, 9, 1);
        Edge edge5 = new Edge(2, 2, 6);
        Edge edge6 = new Edge(2, 1, 9);


        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.removeEdge(edge6);

        List<Edge> edges = graph.getEdges(2);

        assertEquals(4, edges.size());
        assertTrue(edges.contains(edge1));
        assertTrue(edges.contains(edge3));
        assertTrue(edges.contains(edge4));
        assertTrue(edges.contains(edge5));
    }

    @Test
    public void TestGetVertexDegree_CorrectDegreeReturned() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(20);
        graph.addEdge(5, 7, 20);
        graph.addEdge(5, 19, -3);
        graph.addEdge(4, 5, 3);
        graph.addEdge(0, 5, 2);
        graph.addEdge(3, 4, 20);
        graph.removeEdge(0, 3);

        int degree = graph.degree(5);

        assertEquals(4, degree);
    }

    public void TestGetVertexDegreeConnectedToSelf_EdgeCountedTwice() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(20);
        graph.addEdge(5, 5, 20);

        int degree = graph.degree(5);

        assertEquals(2, degree);
    }
}