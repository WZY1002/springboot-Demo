//import config.RedisClient;
import com.myproject.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import impldemo.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class redistest {
//    @Autowired
//    @Qualifier("RedisClientSingle")
//    private RedisClient redisClientSingle;
//
//    @Test
//    public void testJedis() {
//        redisClientSingle.set("BIGET#:wzy", "ddddd");
//        System.out.println(redisClientSingle.get("BIGET#:wzy"));
//    }

    @Autowired
    private aaa aaa;

    @Autowired
    private bbb bbb;

    @Autowired
    private ccc ccc;

    @Test
    public void dotest(){
//        aaa.aa();
//        bbb.aa();
//        bbb.bb();
//        ccc.sout("cc");
        List<String> list=new ArrayList<>();
        list.add("1");
        dojia(list);
        System.out.println("-----------"+list+"-----------");
    }

    private void dojia(List<String> a){
         a.add("2");
//        return a;
    }
}
