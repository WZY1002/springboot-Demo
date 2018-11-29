import RPC.my_socket_rpc.model.UserVO;
import algorithm.model.SortBO;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Test
    public void demo6(){
        List<Integer> list=new ArrayList<>();
        list.set(0,1);
    }

    @Test
    public void mapAndHashMap(){
        Gson gson=GsonUtils.gson;
        Map<String,Object> innerMap=new LinkedTreeMap<String, Object>();
        innerMap.put("user","小明");
        Map<String,Object> inMap=new LinkedTreeMap<String, Object>();
        inMap.put("data",innerMap);
        String json=gson.toJson(inMap);
        System.out.println(json);
        Map<String,Object> map1=gson.fromJson(json,Map.class);
        Map<String,Object> map2=gson.fromJson(gson.toJson(map1.get("data")),Map.class);

        HashMap<String,Object> map3=gson.fromJson(json,HashMap.class);
        HashMap<String,Object> map4=gson.fromJson(gson.toJson(map3.get("data")),HashMap.class);
        System.out.println("mp1:"+map1);
        System.out.println("mp2:"+map2);
        System.out.println("mp3:"+map3);
        System.out.println("mp4:"+map4);
    }

    private static SortBO sortBO=new SortBO();
    static {
        sortBO.setNum(1);
    }

    @Test
    public void newdemo(){
        SortBO sortBO=new SortBO(){{
            setNum(1);
        }};
        System.out.println(sortBO);
        DemoBO demoBO=new DemoBO();
        //new DemoBO(){}定义了一个继承于DemoBO的匿名内部类
        DemoBO demoBO2=new DemoBO(){{
            setDemo("demo21");
            setBlock("demo21");
        }};
        DemoBO demoBO3=new DemoBO("demo31","demo31");
        System.out.println(demoBO);
        System.out.println(demoBO2);
        System.out.println(demoBO3);
    }

    @Test
    public void  innerTest(){
        List innerList=new DemoBO().getInnerField();
        List normalList=new DemoBO().getNormalField();
        Field field = null;
        Field field1 = null;
        try {
            field = innerList.getClass().getDeclaredField("this$0");
            field.setAccessible(true);
            System.out.println(field.get(innerList).getClass());
            field1 = normalList.getClass().getDeclaredField("this$0");
            field1.setAccessible(true);
            System.out.println(field1.get(normalList).getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
