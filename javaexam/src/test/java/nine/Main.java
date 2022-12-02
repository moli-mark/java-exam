package nine;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author molimark<br />
 * @date: 2022/12/2 11:18<br/>
 * @description: <br/>
 */
public class Main {
    public void m(double a,double b,double c) {
        if(b*b-4*a*c>0){
            System.out.println("1");
        }else if(b*b-4*a*c==0){
            System.out.println("2");
        }else{
            System.out.println("3");
        }
    }
}
