package com.conch.tiku;

import android.view.ViewGroup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

        map.get(1);
        map.get(3);

        System.out.println("-----------------------------");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }


        Map.Entry toEvict = map.entrySet().iterator().next();

        System.out.println("-----------------------------");
        System.out.println(toEvict.getKey() + "---" + toEvict.getValue());


        List<String> list = new LinkedList<>();
        list.add("list0");
        list.add("list1");
        list.add("list2");
        list.add("list3");
        list.add("list4");
        list.add("list5");

        System.out.println("-----------------------------");

        List<String> strings = list.subList(1, 3);
        strings.remove(0);
        for (String each : strings) {
            System.out.println(each);
        }
        System.out.println("-----------------------------");

        for (String each : list) {
            System.out.println(each);
        }


        Map<String, String> mm = new HashMap<>();
        mm.put("a", "a1");
        mm.put("b", "b1");
        System.out.println("-----------------------------");
        for (String key : mm.keySet()) {
            System.out.println(key);
        }

        System.out.println("-----------------------------");

        for (Map.Entry<String, String> entry : mm.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }


        try {
            String dir = System.getProperty("user.dir");

            System.out.println("dir>>>" + dir);
            Class cls = Class.forName("com.conch.tiku.A");

            All all = (All) cls.newInstance();

            all.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}