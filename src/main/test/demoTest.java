import RPC.my_socket_rpc.model.UserVO;
import com.myproject.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class demoTest {

    @Test
    public void doPost(){

        double a = 1.66728D;
        double b = 1.33333D;
        double c = 1.00000D;
        BigDecimal aa = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal cc = new BigDecimal(c);
//        System.out.println(aa.setScale(2, BigDecimal.ROUND_UP));
//        System.out.println(aa.setScale(2, BigDecimal.ROUND_DOWN));
//        System.out.println(bb.setScale(2, BigDecimal.ROUND_UP));
//        System.out.println(bb.setScale(2, BigDecimal.ROUND_DOWN));
//        System.out.println(cc.setScale(2, BigDecimal.ROUND_UP));
//        System.out.println(cc.setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println(aa.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(aa.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bb.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bb.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(cc.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(cc.setScale(2, BigDecimal.ROUND_HALF_DOWN));
    }

    @Test
    public void demo1(){
        int a=1;
        int b=1;
        List<Integer> list=new ArrayList<>();
        list.add(5);
        dodemo1(a,b,list);
        UserVO userVO;
        if(true){
            userVO=(UserVO)dodemo1(0,0,new ArrayList<>());
        }
//        System.out.println(a+b);
//        System.out.println(userVO);
//        RestTemplate restTemplate=new RestTemplate();
        String furl="asda//aaas//aa/saasa/";
        if(furl.contains("//")){
            furl=furl.replaceAll("(////)(.*?)(\\1)(.*?)", "/");
        }
        System.out.println(furl);
    }

    public Object dodemo1(int a, int b, List<Integer> list){
//        list.add(5);
//        a=a+1;
        List<UserVO> list1=new ArrayList<>();
        UserVO userVO=new UserVO("",0,"");
        list1.add(userVO);
        return  list1.get(0);
//        return  a+b;
    }

}
