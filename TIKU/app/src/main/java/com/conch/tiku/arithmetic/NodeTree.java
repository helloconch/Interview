package com.conch.tiku.arithmetic;


public class NodeTree {
    int value;
    NodeTree leftTree;
    NodeTree rightTree;

    public NodeTree(int mValue) {
        this.value = mValue;
        this.leftTree = null;
        this.rightTree = null;
    }
}
