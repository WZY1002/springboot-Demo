package threads;

import java.util.concurrent.CountDownLatch;

public class MyThreads {

    /**
     * 工具类，线程发令枪，倒计数器
     */
    private static CountDownLatch countDownLatch=new CountDownLatch(1);

    private static int num=0;

    private static  int isRunning=0;

    public static synchronized void printNum(String tag) {
        try {
            if(tag.equals("a")){
                num=100;
                System.out.println("tag a,num set over");
                    Thread.sleep(1000);
                 }else if(tag.equals("b")){
                    num=200;
                    System.out.println("tag b,num set over");
            }
            System.out.println("tag="+tag+", num="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        main1();
        main2();
    }

    private static void main2() {

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning=isRunning+1;
                System.out.println(isRunning);
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning=isRunning+1;
                System.out.println(isRunning);
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning=isRunning+1;
                System.out.println(isRunning);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main1() {
        final MyThreads myThreads1=new MyThreads();
        final MyThreads myThreads2=new MyThreads();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                myThreads1.printNum("a");
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                myThreads2.printNum("b");
            }
        });
        thread1.start();
        thread2.start();
    }


}
