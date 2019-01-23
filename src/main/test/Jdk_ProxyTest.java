import design_mode.proxy_jdk.Base;
import design_mode.proxy_jdk.BaseImplA;
import design_mode.proxy_jdk.BaseProxy;


public class Jdk_ProxyTest {

        public static void main(String args[])
        {
            BaseProxy proxy = new BaseProxy();
            //绑定该类实现的所有接口
            Base a = (Base) proxy.bind(new BaseImplA());
            a.doSomething();
        }
}
