package design_mode.delegate;

/**
 * 委派模式  boss分配任务
 * @author wzy
 * @date 2018/11/16
 **/
public class Boss {
    public static void main(String[] args) {
        new Leader().dispatch("销售");
    }
}
