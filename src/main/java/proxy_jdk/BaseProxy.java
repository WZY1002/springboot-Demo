package proxy_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 * @author wzy
 * @return
 * @date 2018/8/9
 **/
public class BaseProxy implements InvocationHandler {

    private Object tar;

    /**
     * 绑定委托对象，并返回代理类
     * @author wzy
     * @return
     * @param tar 要代理的对象
     * @date 2018/8/9
     **/
    public Object bind(Object tar)
    {
        this.tar = tar;
        //绑定该类实现的所有接口，取得新的代理类
        //1.根据参数loader和interfaces调用方法 getProxyClass(loader, interfaces)创建代理类$Proxy0,$Proxy0类 实现了interfaces的接口,并继承了Proxy类.
        //2.实例化$Proxy0并在构造方法中把BaseProxy传过去,接着$Proxy0调用父类Proxy的构造器,为h赋值
        Object tarclass=Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                this);
        System.out.println(tarclass);
        return tarclass;
    }

    /**
     * 这个方法不是显示的去调用,在继承了Proxy的$Proxy0中
     * 当调用被代理类中的doSomething()方法时,就会执行$Proxy0中的invoke()方法，即这个invoke方法
     * invoke()根据传入的代理对象，方法，参数来决定调用代理的哪个方法
     * @author wzy
     * @return
     * @param proxy 传递代理类的实例
     * @param method 需要执行的方法
     * @param args 方法的参数
     * @date 2018/8/9
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //被代理的类型为Object基类
        //在调用具体函数方法前，执行功能处理
        System.out.println("代理帮忙");
        //执行方法
        result = method.invoke(tar,args);
        System.out.println("代理结束");
        //在调用具体函数方法后，执行功能处理
        return result;
    }
}
