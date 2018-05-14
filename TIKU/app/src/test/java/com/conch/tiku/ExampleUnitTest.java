package com.conch.tiku;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

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
    }
}