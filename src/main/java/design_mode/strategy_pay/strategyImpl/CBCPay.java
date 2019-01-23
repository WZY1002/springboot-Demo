package design_mode.strategy_pay.strategyImpl;

import design_mode.strategy_pay.Pay;
import design_mode.strategy_pay.Strategy;

@Pay(5)
public class CBCPay implements Strategy {
    @Override
    public Integer calRecharge(Integer channelId, Integer goodsId) {
        return channelId+goodsId+500;
    }
}
