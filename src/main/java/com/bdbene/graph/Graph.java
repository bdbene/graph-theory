package com.bdbene.graph;

import java.util.List;

public interface Graph {
    public void addEdge(int x, int y, int weight);
    public void removeEdge(int x, int y);
    public int vertexCount();
    public List<Integer> getEdges(int x);
    public boolean edgeExists(int x, int y);
    public int edgeWeight(int x, int y);
}