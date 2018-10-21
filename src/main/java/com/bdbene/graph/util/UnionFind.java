package com.bdbene.graph.util;

import com.bdbene.graph.util.UnionNode;

public class UnionFind {
    private UnionNode[] data;

    public UnionFind(int nodeCount) {
        data = new UnionNode[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            data[i] = new UnionNode(i);
        }
    }

    public int nodeCount() {
        return data.length;
    }

    public void setSpecial(int i) {
        checkVertexValidity(i);
        int x = findParent(i);

        data[x].setSpecial(true);
    }

    public boolean isSpecial(int i) {
        checkVertexValidity(i);
        int x = findParent(i);

        return data[x].isSpecial();
    }

    public void union(int a, int b) {
        checkVertexValidity(a);
        checkVertexValidity(b);

        UnionNode nodeA = data[a];
        UnionNode nodeB = data[b];
        int parentA = nodeA.getParent();
        int parentB = nodeB.getParent();
        boolean unionSpecial = nodeA.isSpecial() || nodeB.isSpecial();

        if (parentA != a || parentB != b) {
            union(parentA, parentB);
            return;
        }
        
        int rankA = nodeA.getRank();
        int rankB = nodeB.getRank();

        if (rankA < rankB) {
            nodeA.setParent(b);
            nodeB.setSpecial(unionSpecial);
        } else if (rankA == rankB) {
            nodeA.setParent(b);
            nodeB.setRank(rankB + 1);
            nodeB.setSpecial(unionSpecial);
        } else {
            nodeB.setParent(a);
            nodeA.setSpecial(unionSpecial);
        }
    }

    public int findParent(int vertex) {
        checkVertexValidity(vertex);

        return findParentHelper(vertex);
    }

    private int findParentHelper(int vertex) {
        UnionNode current = data[vertex];
        int parent = current.getParent();

        if (parent == vertex) {
            return parent;
        }

        int result = findParentHelper(parent);
        current.setParent(result);

        return result;
    }

    private void checkVertexValidity(int vertex) {
        if (vertex >= data.length) {
            throw new IndexOutOfBoundsException("No vertex with label: " + vertex);
        }
    }
}