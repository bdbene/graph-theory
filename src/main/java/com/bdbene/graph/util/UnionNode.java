package com.bdbene.graph.util;

import lombok.Getter;
import lombok.Setter;

public class UnionNode {
    @Getter
    @Setter
    private int rank;

    @Getter
    @Setter
    private int parent;

    @Getter
    @Setter
    private boolean special;

    public UnionNode(int index) {
        this.rank = 0;
        this.parent = index;
        this.special = false;
    }
}