package strategy_pay;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 工厂模式,根据注解的参数生成对应实现类
 * @author  wzy
 * @create  2018/6/3 20:11
 **/
public class StrategyFactory {
    private  static  StrategyFactory factory=new StrategyFactory();

    private static Map<Integer,String> source_map=new HashMap<>();

    static {
            //反射工具包，指明扫描路径
        Reflections reflections=new Reflections("strategy_pay.strategyImpl");
            //获取带pay注解的类
        Set<Class<?>>  classList= reflections.getTypesAnnotatedWith(Pay.class);
            //根据注解的值，把类名放到map中
        classList.stream().forEach(aClass -> {
            Pay p=aClass.getAnnotation(Pay.class);
                //获取传入注解的值和带注解的类的全名
            source_map.put(p.value(),aClass.getCanonicalName());
        });
    }

    /**
     * 具体生成对象的方法
     * @author  wzy
     * @create  2018/6/1 14:19
     **/
    public Strategy creator(int type) throws  Exception{
        String clazz=source_map.get(type);
        //通过全类名获取类的对象
        Class clazz_=Class.forName(clazz);
        //返回生成的对象(newInstance()就是生成新的对象)
        return (Strategy) clazz_.newInstance();
    }

    public static StrategyFactory getFactory() {
        return factory;
    }
}
