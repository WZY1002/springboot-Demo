package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadsTalk {
    private volatile static List list=new ArrayList();

    /**
     * 工具类，线程发令枪，倒计数器
     */
    private static CountDownLatch countDownLatch=new CountDownLatch(1);

    public void add(){
        list.add("wzy");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
//        volatileDemo();
        waitAndNotify();
    }

    private static void waitAndNotify() {
        ThreadsTalk threadsTalk=new ThreadsTalk();
        Object lock=new Object();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        for (int i=0;i<10;i++){
                    synchronized (lock){
                            threadsTalk.add();
                            System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素");
                            Thread.sleep(500);
                            if(threadsTalk.size()==5){
                                System.out.println("已经发出通知。。");
                                lock.notify();
//                                countDownLatch.countDown();
                            }
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    if(threadsTalk.size()!=5){
                        try {
                            System.out.println("t2已进入。。。");
                            //等待，会释放锁
                            lock.wait();
//                        countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程："+Thread.currentThread().getName()+"收到通知线程停止...");
                    throw new RuntimeException();
                }
            }
        },"t2");
        t2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

    private static void volatileDemo() {
        ThreadsTalk threadsTalk=new ThreadsTalk();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    try {
                        threadsTalk.add();
                        System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(threadsTalk.size()==5){
                        System.out.println("当前线程收到通知："+Thread.currentThread().getName()+" 线程停止  list.szie=5 ");
                        throw new RuntimeException();
                    }
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
