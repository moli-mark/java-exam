package nine;

/**
 * @author molimark<br />
 * @date: 2022/12/2 15:13<br/>
 * @description: <br/>
 */
public class Caculate {
    public boolean judge(Integer year){
        if(year<0){
            System.out.println("年份不合法!");
            throw new RuntimeException();
        }
        if((year%4)==0&&((year%100)!=0))return true;
        if((year%400)==0)return true;
        return false;
    }
}
