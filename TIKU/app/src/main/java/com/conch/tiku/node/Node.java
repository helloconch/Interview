package com.conch.tiku.node;


public class Node {


    public static boolean hasLoop(LinkedLoop head) {
        //定义一个引用指向头结点
        LinkedLoop p1 = head;
        //定义另一个引用指向结点的下一个结点
        LinkedLoop p2 = head.next;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p2 == null)
                return false;
            //为了防止p2.value出现空指针异常，需要对p2进行判断
            int val1 = p1.value;
            int val2 = p2.value;
            if (val1 == val2)
                return true;
        }

        return false;
    }


    public static class LinkedLoop {
        public int value;
        public LinkedLoop next;

        public LinkedLoop(int v) {
            this.value = v;
        }
    }
}
