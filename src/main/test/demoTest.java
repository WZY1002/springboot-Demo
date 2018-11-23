import RPC.my_socket_rpc.model.UserVO;
import com.google.gson.Gson;
import com.myproject.MainApplication;
import commom.DemoBO;
import commom.GsonUtils;
import innerclass.Goods;
import log.LogDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class demoTest {

//    @Autowired
//    private LogDemo logDemo;

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

    @Test
    public void demo2(){
        long start=System.currentTimeMillis();
        System.out.println(start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List list=new ArrayList();
                for (int i=0;i<1000;i++){
                    list.add(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List list2=new ArrayList();
                for (int i=0;i<1000;i++){
                    list2.add(i);
                }
            }
        }).start();
        long end=System.currentTimeMillis();
        System.out.println(end);
        System.out.println("分线程耗时："  +  (end-start ));
    }


    @Test
    public void demo3(){
        long start=System.currentTimeMillis();
        System.out.println(start);
        List list=new ArrayList();
        for (int i=0;i<10000;i++){
        list.add(i);
        }
        List list2=new ArrayList();
        for (int i=0;i<10000;i++){
            list2.add(i);
        }
        long end=System.currentTimeMillis();
        System.out.println(end);
        System.out.println("普通耗时："  +  (end-start ));
    }


    @Test
    public void demo45(){
        LogDemo logDemo=new LogDemo();
        logDemo.printLog("log测试");
    }

}
