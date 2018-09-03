import impldemo.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import strategy_pay.service.Context;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class PayTest {
    @Test
    public void doAnnocation() throws  Exception{
        Context context=new Context();
        int CBC=context.calRecharge(1,1);
        int ICBC=context.calRecharge(5,5);
        System.out.printf("CBC="+CBC+"-------------");
        System.out.printf("ICBC="+ICBC+"-------------");
    }
}
