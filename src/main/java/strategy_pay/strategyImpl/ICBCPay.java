package strategy_pay.strategyImpl;

import strategy_pay.Pay;
import strategy_pay.Strategy;

@Pay(1)
public class ICBCPay implements Strategy {
    @Override
    public Integer calRecharge(Integer channelId, Integer goodsId) {
        //根据渠道id，去数据库查询支付渠道的消息，包括折扣之类
        //根据商品ID,获取商品价格
        return 100+channelId+goodsId;
    }
}
