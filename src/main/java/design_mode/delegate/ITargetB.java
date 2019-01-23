package design_mode.delegate;

public class ITargetB implements ITarget {
    @Override
    public void doing(String something) {
        System.out.println("B员工完成"+something);
    }
}