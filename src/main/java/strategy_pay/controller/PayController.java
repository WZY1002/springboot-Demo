package strategy_pay.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import strategy_pay.service.Context;
import strategy_pay.model.PayBO;

@RestController
@RequestMapping(value = "/strategy_pay")
public class PayController {

    @Autowired
    private Context context;

    Gson gson=new Gson();

    @RequestMapping("/money")
    public Integer pay(@RequestBody(required = false) String json){
        PayBO payBO=gson.fromJson(json,PayBO.class);
        try {
            return context.calRecharge(payBO.getChannelId(),payBO.getChannelId());
        } catch (Exception e) {
            System.out.println("--------------传入数据有错-----------");
            e.printStackTrace();
            return 0;
        }
    }
}
