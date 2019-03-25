package design_mode.decorators;

/**
 * 水(具体组件)
 * @author wzy
 * @since 2019/3/25 13:40
 **/
public class Water implements Beverage{
    @Override
    public double cost() {
        return 0.5;
    }
}
