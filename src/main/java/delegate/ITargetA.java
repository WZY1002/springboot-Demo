package delegate;

public class ITargetA implements ITarget{
    @Override
    public void doing(String something) {
        System.out.println("A员工完成" + something);
    }
}
