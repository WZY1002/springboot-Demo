package strategy_pay.strategyImpl;

import strategy_pay.Pay;
import strategy_pay.Strategy;

@Pay(5)
public class CBCPay implements Strategy {
    @Override
    public Integer calRecharge(Integer channelId, Integer goodsId) {
        return channelId+goodsId+500;
    }
}
