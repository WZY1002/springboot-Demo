package strategy_pay;


public interface Strategy {
        /**
         * 计算支付金额，根据支付渠道和商品id
         * @author  wzy
         * @create  2018/6/1 13:37
         **/
        Integer calRecharge(Integer channelId,Integer goodsId);
}
