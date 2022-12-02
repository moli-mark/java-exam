package com.zzy.exam.eight;

import java.math.BigInteger;
import java.util.concurrent.*;

public class Caculate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer[] nums = new Integer[10000];
        for(int i=0;i<10000;i++){
            nums[i]=i+1;
        }
        BigInteger ans = new BigInteger(String.valueOf(0));
        ExecutorService executor = Executors.newFixedThreadPool(4);
        MyCallable r1 = new MyCallable(1,1250);
        MyCallable r2 = new MyCallable(1251,2500);
        MyCallable r3 = new MyCallable(2501,3750);
        MyCallable r4 = new MyCallable(3751,5000);
        MyCallable r5 = new MyCallable(5001,6250);
        MyCallable r6 = new MyCallable(6251,7500);
        MyCallable r7 = new MyCallable(7501,8750);
        MyCallable r8 = new MyCallable(8751,10000);
        ans = ans.add((BigInteger) executor.submit(r1).get());
        ans = ans.add((BigInteger) executor.submit(r2).get());
        ans = ans.add((BigInteger) executor.submit(r3).get());
        ans = ans.add((BigInteger) executor.submit(r4).get());
        ans = ans.add((BigInteger) executor.submit(r5).get());
        ans = ans.add((BigInteger) executor.submit(r6).get());
        ans = ans.add((BigInteger) executor.submit(r7).get());
        ans = ans.add((BigInteger) executor.submit(r8).get());
        System.out.println(ans.longValue());
    }
}

class MyCallable implements Callable {
    Integer start;
    Integer end;

    public MyCallable(Integer start,Integer end){
        this.start = start;
        this.end = end;
    }

    @Override
    public Object call() throws Exception {
        BigInteger ans = new BigInteger(String.valueOf(0));
        for(int i=start;i<=end;i++){
            boolean flag = false;
            for(int j=2;j<i;j++){
                if((i%j)==0)flag = true;
            }
            if(flag==false) {
                ans = ans.add(new BigInteger(String.valueOf(i)));
            }
        }
        return ans;
    }
}
