package threads.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable {
    //任务队列
    protected Queue<Object> workQueue;
    //子任务结果集
    protected Map<String,Object> resultMap = new HashMap<String, Object>();


    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }
    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //处理任务
    public Object handle(Object input){
        return input;
    }

    //处理子任务并将结果存到结果集合
    @Override
    public void run() {
        while(true){
            Object input = workQueue.poll();

            if(null==input) break;
            //处理子任务
            Object re = handle(input);
            resultMap.put(Integer.toString(input.hashCode()),re);
            //System.out.println(re.toString());
        }
    }

}
