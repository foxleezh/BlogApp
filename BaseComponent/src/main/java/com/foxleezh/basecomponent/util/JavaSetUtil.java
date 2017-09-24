package com.foxleezh.basecomponent.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;


/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 该工具用于Java中常见的集合处理
 */

public class JavaSetUtil {

    /**
     * 将LinkedHashMap的value转换为ArrayList
     * @param map LinkedHashMap
     * @param <T> 泛型
     * @return
     */
    public static <T> ArrayList<T> mapToList(LinkedHashMap<String,T> map){
        ArrayList<T> list=new ArrayList<>();
        ListIterator<Map.Entry<String, T>> i = new ArrayList<>(map.entrySet()).listIterator(0);
        while (i.hasNext()) {
            Map.Entry<String, T> entry = i.next();
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * 将LinkedHashMap的key转换为ArrayList
     * @param map LinkedHashMap
     * @param <T> 泛型
     * @return
     */
    public static <T> ArrayList<String> mapToKeys(LinkedHashMap<String,T> map){
        ArrayList<String> list=new ArrayList<>();
        ListIterator<Map.Entry<String, T>> i = new ArrayList<>(map.entrySet()).listIterator(0);
        while (i.hasNext()) {
            Map.Entry<String, T> entry = i.next();
            list.add(entry.getKey());
        }
        return list;
    }


    /**
     * 将LinkedHashMap的key转换为String ,以，号分割开
     * @param map LinkedHashMap
     * @param <T> 泛型
     * @return
     */
    public static <T> String mapToString(LinkedHashMap<String,T> map){
        ListIterator<Map.Entry<String, T>> i =
                new ArrayList<>(map.entrySet()).listIterator(0);
        StringBuilder stringBuilder=new StringBuilder();
        int j=0;
        while (i.hasNext()) {
            Map.Entry<String, T> entry = i.next();
            if(j!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(entry.getKey());
            j++;
        }
        return stringBuilder.toString();
    }


    /**
     * 将ArrayList的key转换为String ,以，号分割开
     * @param list ArrayList
     * @return
     */
    public static String listToString(ArrayList<String> list){
        int size = list.size();
        String module;
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < size; i++) {
            module = list.get(i);
            if(i!=0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(module);
        }
        return stringBuilder.toString();
    }

}
