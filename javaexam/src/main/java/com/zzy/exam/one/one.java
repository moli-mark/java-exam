package com.zzy.exam.one;

import java.io.File;
import java.util.*;

/**
 * @author molimark<br />
 * @date: 2022/12/1 10:43<br/>
 * @description: <br/>
 */
/**
 给定一个目录，统计这个目录下所有的图片文件
（包括子目录，格式限定jpg,png,大小必须大于1M），
 按照文件大小降序输出文件全路径
 **/
public class one {
    public static Map<String,String> result = new HashMap<>();

    public static Comparator<Map.Entry<String, String>> comparator = new Comparator<Map.Entry<String, String>>() {
        @Override
        public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    public static void main(String[] args) {
        getFile("D:/examtest");
        List<Map.Entry<String, String>> mapList = new ArrayList<>();
        mapList.addAll(result.entrySet());
        Collections.sort(mapList,comparator);
        for(Map.Entry<String,String> entry : mapList){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
        }
    }

    public static void getFile(String path){
        File file = new File(path);
        searchFile(file);
    }

    public static void searchFile(File file){
        File[] files = file.listFiles();
        for(File f : files){
            if(f.isFile()){
                if(f.getName().contains("png")||f.getName().contains("jpg")){
                    if(f.length()>10485760){
                        result.put(""+f.length(),f.getAbsolutePath());
                    }
                }
            }else{
                searchFile(f);
            }
        }
    }
}
