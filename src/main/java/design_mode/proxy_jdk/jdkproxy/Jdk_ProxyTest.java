package design_mode.proxy_jdk.jdkproxy;

import design_mode.proxy_jdk.jdkproxy.Base;
import design_mode.proxy_jdk.jdkproxy.BaseImplA;
import design_mode.proxy_jdk.jdkproxy.BaseProxy;


public class Jdk_ProxyTest {

        public static void main(String args[])
        {
            //实例化代理类
            BaseProxy proxy = new BaseProxy();
            //代理类绑定该实现类的所有接口
            Base a = (Base) proxy.bind(new BaseImplA());
            a.doSomething();
        }
}
