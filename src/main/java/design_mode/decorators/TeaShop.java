package design_mode.decorators;

/**
 * 装饰器模式
 * 生产奶茶
 * @author wzy
 * @since 2019/3/25 13:34
 **/
public class TeaShop {

    public static void main(String[] args) {
        //创建水对象
        Beverage beverage=new Water();
        System.out.println("白开水价格：  "+beverage.cost()+ " 元");
        //牛奶装饰器，加工装饰水组件
        beverage=new MilkDecorator(beverage);
        //抹茶装饰器，加工装饰牛奶组件
        beverage=new MochaDecorator(beverage);
        System.out.println("牛奶抹茶价格：  "+beverage.cost()+ " 元");
    }

}
