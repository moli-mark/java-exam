package test;

import nine.Main;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author molimark<br />
 * @date: 2022/12/2 11:29<br/>
 * @description: <br/>
 */
public class TTest {
    @Test
    public void test(){
        Main main = new Main();
        main.m(1,2,1);
        main.m(2,2,2);
        main.m(2,9,2);
    }
}