package threads.master_worker;

/**
 * 要执行的工作(计算立方)
 * @author wzy
 * @date 2018/11/15
 **/
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i*i*i;
    }

}
