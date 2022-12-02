package com.zzy.exam.four;

import java.util.Date;
import java.util.HashMap;

/**
 * @author molimark<br />
 * @date: 2022/12/2 15:08<br/>
 * @description: <br/>
 */
class A {
    String a;
    HashMap<Integer, B> b;
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
