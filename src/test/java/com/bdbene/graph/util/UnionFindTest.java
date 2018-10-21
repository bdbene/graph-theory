package com.bdbene.graph.util;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.bdbene.graph.util.UnionFind;

public class UnionFindTest {

    private UnionFind set;
    private int nodeCount;

    @Before
    public void setup() {
        nodeCount = 50;
        set = new UnionFind(nodeCount);

    }
    
    @Test
    public void NewlyBuild_AllNodesTheirOwnParent() {

        for (int i = 0; i < nodeCount; i++) {
            assertEquals(i, set.findParent(i));
        }
    }
    
    @Test
    public void NewlyBuild_NoNodesSpecial() {

        for (int i = 0; i < nodeCount; i++) {
            assertFalse(set.isSpecial(i));
        }
    }
    
    @Test
    public void SetSpecial_OnlySpecificNodeSpecial() {
        int x = 30;
        set.setSpecial(x);

        for (int i = 0; i < nodeCount; i++) {
            if (i != x) {
                assertFalse(set.isSpecial(i));
            } else {
                assertTrue(set.isSpecial(i));
            }
        }
    }

    @Test
    public void UnionAllNodes_OneParentForAll() {
        for (int i = 1; i < nodeCount; i++) {
            set.union(i, 0);
        }

        for (int i = 0; i < nodeCount; i++) {
            assertEquals(0, set.findParent(i));
        }
    }

    @Test
    public void UnionSpecial_ParentSpecial() {
        set.setSpecial(40);

        for (int i = 1; i < nodeCount; i++) {
            set.union(i, 0);
        }

        for (int i = 0; i < nodeCount; i++) {
            assertTrue(set.isSpecial(i));
        }
    }
}