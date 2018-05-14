package com.conch.tiku.test;


public class MyLru<K, V> {
    public void print() {
        System.out.println("print method");
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }
}
