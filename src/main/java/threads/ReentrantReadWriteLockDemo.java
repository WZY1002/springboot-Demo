package threads;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author wzy
 * @since 2019/3/22 11:14
 **/
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
//        ExecutorService executorService= Executors.newCachedThreadPool();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        long start =System.currentTimeMillis();
        //读操作
        for (int i=0;i<100;i++){
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                        doRead(finalI);
                    doReadWithOutLoc(finalI);
                        }
                }
            );
        }
        //写操作
        for (int i=0;i<20;i++){
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                        doWrite(finalI);
                    doWriteWithOutLock(finalI);
                        }
                }
            );
        }
        System.out.println("耗时：" + (System.currentTimeMillis()-start));
    }

    //实例化读写锁
    private static ReentrantReadWriteLock readWriteLock =new ReentrantReadWriteLock();
    //抽取读写锁
    private static Lock readLock=readWriteLock.readLock();
    private static Lock writeLock=readWriteLock.writeLock();
    private static int count=0;

    private static void doWrite(int finalI) {
            readLock.lock();
            try {
                count+=1;
                System.out.println("写操作 第"+ finalI +"次 count="+count);
            }finally {
                readLock.unlock();
            }
}
    private static void doRead(int finalI) {
        readLock.lock();
        try {
            System.out.println("读操作 第"+ finalI +"次 count="+count);
        }finally {
            readLock.unlock();
        }
    }

    private static void doWriteWithOutLock(int finalI) {
        count+=1;
        System.out.println("写操作 第"+ finalI +"次 count="+count);
    }
    private static void doReadWithOutLoc(int finalI) {
        System.out.println("读操作 第"+ finalI +"次 count="+count);
    }
}
