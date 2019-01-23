package design_mode.strategy_pay.service;


import org.springframework.stereotype.Component;
import design_mode.strategy_pay.Strategy;
import design_mode.strategy_pay.StrategyFactory;

/**
 * 根据Context上下文，根据传入的支付类获取具体的支付实现类
 * @author  wzy
 * @create  2018/6/1 14:01
 **/
@Component
public class Context {

    private Strategy strategy;

    public Integer calRecharge(Integer channelId,Integer goodsId) throws  Exception{
        //根据工厂去创建具体的实例对象
        strategy= StrategyFactory.getFactory().creator(channelId);
        System.out.println("-----输出金额："+strategy.calRecharge(channelId, goodsId));
        return strategy.calRecharge(channelId, goodsId);
    }
}
