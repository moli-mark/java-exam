package com.zzy.exam;

import java.math.BigInteger;

/**
 * @author molimark<br />
 * @date: 2022/12/2 12:26<br/>
 * @description: <br/>
 */
public class tte {
    public static void main(String[] args) {
        System.out.println(String.valueOf(123));
        BigInteger bigInteger = new BigInteger("33");
        BigInteger bigInteger1 = new BigInteger("123");
        bigInteger = bigInteger.add(bigInteger1);
        System.out.println(bigInteger.abs());
    }
}
