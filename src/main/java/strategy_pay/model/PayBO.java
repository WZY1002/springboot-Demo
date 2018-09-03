package strategy_pay.model;

/**
 * @author:wzy
 * @date:2018/6/13
 **/
public class PayBO {
    private  Integer channelId;
    private Integer goodsId;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "PayBO{" +
                "channelId=" + channelId +
                ", goodsId=" + goodsId +
                '}';
    }
}
