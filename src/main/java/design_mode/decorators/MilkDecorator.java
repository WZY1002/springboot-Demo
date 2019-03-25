package design_mode.decorators;

/**
 * 牛奶装饰器 (具体组件)
 * @author wzy
 * @since 2019/3/25 13:30
 **/
public class MilkDecorator extends AbstrctBeverage{

    public MilkDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        System.out.println("牛奶装饰器加工");
        return 1+beverage.cost();
    }
}
