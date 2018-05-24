package com.conch.tiku.arithmetic;

/**
 * 单向链表
 */

public class LinkData {
    Node first;
    Node last;

    /**
     * 打印数据
     */
    public void print() {
        while (first != null) {
            System.out.println("链表数据>>>name:" + first.name + "---age:" + first.age);
            first = first.next;
        }

    }

    /**
     * 添加数据
     *
     * @param age
     * @param name
     */
    public void insert(int age, String name) {
        Node node = new Node(age, name);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    /**
     * 链表反转
     */
    public void revert() {
        //使用三个指针，解决链表反转问题
        Node current = first;
        Node before = null;
        while (current != null) {
            last = before;
            before = current;
            current = current.next;
            before.next = last;
        }

        current = before;
        while (current != null) {
            System.out.println("链表反转数据>>>name:" + current.name + "---age:" + current.age);
            current = current.next;
        }

    }

}
