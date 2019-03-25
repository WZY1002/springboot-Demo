package design_mode.decorators;

/**
 * 抹茶装饰器 (具体组件)
 * @author wzy
 * @since 2019/3/25 13:31
 **/
public class MochaDecorator extends AbstrctBeverage{

    public MochaDecorator(Beverage beverage) {
        this.beverage=beverage;
    }

    @Override
    public double cost() {
        System.out.println("抹茶装饰器加工");
        return 1+ beverage.cost();
    }
}
