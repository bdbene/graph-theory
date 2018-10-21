package com.bdbene.graph;

import lombok.Getter;
import lombok.Setter;

// Representation of a weighted, undirected edge between two vertices. 
class Edge {
    @Getter
    @Setter
    private int first;

    @Getter
    @Setter
    private int second;

    @Getter
    @Setter
    private int weight;

    public Edge(int first, int second, int weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        int vertexA = edge.getFirst();
        int vertexB = edge.getSecond();
        int otherWeight = edge.getWeight();

        if (vertexA == first && vertexB == second) {
            return weight == otherWeight;
        } else if (vertexB == first && vertexA == second) {
            return weight == otherWeight;
        } else {
            return false;
        }
    }
}