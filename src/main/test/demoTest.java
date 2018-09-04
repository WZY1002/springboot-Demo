import Http.HttpClintPost;
import Http.HttpPost;
import Http.Httpspost;
import impldemo.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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


}
