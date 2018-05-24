package com.conch.tiku.arithmetic;

/**
 * 生成二叉树
 */
public class LinkTreeData {

    //二叉树根节点
    NodeTree rootTree;

    public void create(int[] datas) {

        for (int i = 0; i < datas.length; i++) {
            add(datas[i]);
        }

    }

    public void add(int value) {
        NodeTree currentNode = rootTree;
        // 建立树根
        if (rootTree == null) {
            rootTree = new NodeTree(value);
            return;
        }

        while (true) {

            if (value < currentNode.value) {
                if (currentNode.leftTree == null) {
                    currentNode.leftTree = new NodeTree(value);
                    return;
                } else {
                    currentNode = currentNode.leftTree;
                }

            } else {
                if (currentNode.rightTree == null) {
                    currentNode.rightTree = new NodeTree(value);
                    return;
                } else {
                    currentNode = currentNode.rightTree;
                }

            }

        }
    }

}
