package design_mode.strategy_pay;

import com.myproject.MainApplication;
import design_mode.strategy_pay.service.Context;


public class PayTest {

    public static void main(String[] args) throws Exception {
        Context context=new Context();
        int CBC=context.calRecharge(1,1);
        int ICBC=context.calRecharge(5,5);
        System.out.printf("CBC="+CBC+"-------------");
        System.out.printf("ICBC="+ICBC+"-------------");
    }

}
