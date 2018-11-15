package threads.master_worker;

import java.util.Map;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        //创建五个线程
        Master m = new Master(new PlusWorker(), 50);
        for (int i = 0; i < 100; i++) {
            //创建任务
            m.submit(i);
        }
        m.execute();
        int re = 0;
        Map<String, Object> resultMap = m.getResultMap();
        while(resultMap.size()>0||!m.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key =  null;
            for(String k:keys){
                key=k;
                break;
            }
            Integer i = null;
            if(key != null){
                i = (Integer) resultMap.get(key);
            }
            if(i!=null){
                //并行计算结果集
                re+=i;
            }
            if(key!=null){
                resultMap.remove(key);//将计算完成的结果移除
            }
        }
        System.out.println("运行时间："+(System.currentTimeMillis()-start));
        System.out.println(re);
    }
}