package com.zzy.exam.four;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

/**
 * @author molimark<br />
 * @date: 2022/12/2 14:01<br/>
 * @description: <br/>
 */
public class Parse {
    public static void main(String[] args) {
        HashMap<Integer,B> mmap = new HashMap<>();
        B b1 = new B(2,"123");
        B b2 = new B(3,"456");
        mmap.put(1,b1);
        mmap.put(2,b2);
        A a = new A("aaa",mmap,new Date());
        System.out.println(parseJSON(a));
    }

    public static JSONObject parseJSON(A a){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ldtStr = sdf.format(a.c);
        System.out.println(ldtStr);
        JSONArray array = new JSONArray();
        HashMap<Integer, B> b = a.b;
        for(Map.Entry<Integer,B> entry : b.entrySet()){
            array.add(JSONObject.toJSON(new HashItem(entry.getKey(), entry.getValue())));
        }
        String data = sdf.format(a.c);
        Out out = new Out(a.a,array,data);
        return (JSONObject) JSONObject.toJSON(out);
    }
}

class HashItem{
    public Integer key;
    public B value;


    public HashItem(Integer key, B value) {
        this.key = key;
        this.value = value;
    }
}

class Out{
    public String a;
    public JSONArray b;
    public String c;

    public Out(String a, JSONArray b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class A{
    String a;
    HashMap<Integer,B> b;
    Date c;

    public A(String a, HashMap<Integer, B> b, Date c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public HashMap<Integer, B> getB() {
        return b;
    }

    public void setB(HashMap<Integer, B> b) {
        this.b = b;
    }

    public Date getC() {
        return c;
    }

    public void setC(Date c) {
        this.c = c;
    }
}

class B{
    int d;
    String e;

    public B(int d, String e) {
        this.d = d;
        this.e = e;
    }
}
