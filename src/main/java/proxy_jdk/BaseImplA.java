package proxy_jdk;

/**
 * 被代理类A角色
 * @author wzy
 * @return
 * @date 2018/8/9
 **/
public class BaseImplA implements  Base {
    @Override
    public void doSomething() {
        System.out.println("A买一个苹果");
    }
}
