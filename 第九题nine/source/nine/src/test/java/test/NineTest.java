package test;

import nine.Caculate;
import org.junit.Test;

/**
 * @author molimark<br />
 * @date: 2022/12/2 15:13<br/>
 * @description: <br/>
 */
public class NineTest {
    Caculate caculate = new Caculate();

    @Test
    public void Test1(){
        System.out.println(caculate.judge(-1900));
    }

    @Test
    public void Test2(){
        System.out.println(caculate.judge(1900));
    }

    @Test
    public void Test3(){
        System.out.println(caculate.judge(2000));
    }

    @Test
    public void Test4(){
        System.out.println(caculate.judge(1999));
    }

    @Test
    public void Test5(){
        System.out.println(caculate.judge(8000));
    }
}
