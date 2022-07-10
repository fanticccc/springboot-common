package com.example.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/4/20 14:36
 * @Version 1.0
 * com.example.test
 */
public class Demo3 {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        Map<String, String> map1 = Collections.synchronizedMap(map);
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        Set<String> set = new HashSet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
