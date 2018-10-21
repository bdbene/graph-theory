package com.bdbene.graph;

import java.util.List;

public interface Graph {
    public void addEdge(Edge edge);
    public void addEdge(int x, int y, int weight);
    public void removeEdge(Edge edge);
    public void removeEdge(int x, int y);
    public int vertexCount();
    public List<Edge> getEdges(int x);
    public boolean edgeExists(Edge edge);
    public boolean edgeExists(int x, int y);
    public int edgeWeight(Edge edge);
    public int edgeWeight(int x, int y);
    public int degree(int x);
}