package threads;

import java.util.concurrent.*;

public class Future {
    //准备包子
     static Thread BumThread=new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000*3);
                System.out.println("包子准备完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

     //准备凉菜
    static Thread ColdDishThread=new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000*3);
                System.out.println("凉菜准备完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {
        runSimple();
        runFuture();
        executor();
    }

    /**
     * Future+普通线程
     * 多线程执行
     * @author wzy
     * @date 2018/11/15
     **/
    private static void runFuture() {
        long start = System.currentTimeMillis();
        // 等凉菜
        Callable ca1 = new Callable(){
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "凉菜准备完毕";
            }
        };
        FutureTask<String> ft1 = new FutureTask<String>(ca1);
        new Thread(ft1).start();

        // 等包子
        Callable ca2 = new Callable(){
            @Override
            public Object call() throws Exception {
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "包子准备完毕";
            }
        };
        FutureTask<String> ft2 = new FutureTask<String>(ca2);
        new Thread(ft2).start();

        try {
            System.out.println(ft1.get());
            System.out.println(ft2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间："+(end-start));

    }

    /**
     * 普通线程模式
     * 多线程串行执行
     * @author wzy
     * @date 2018/11/15
     **/
    private static void runSimple() {
        long start = System.currentTimeMillis();

        // 等凉菜 -- 必须要等待返回的结果，所以要调用join方法
        BumThread.start();
        try {
            //让父线程等待子线程结束之后才能继续运行。
            BumThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 等包子 -- 必须要等待返回的结果，所以要调用join方法
        ColdDishThread.start();
        try {
            ColdDishThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间："+(end-start));
    }

    /**
     * 线程池+FutureTask
     * 多线程执行
     * @author wzy
     * @date 2018/11/15
     **/
    private  static void executor(){
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newCachedThreadPool();
        Callable callable= (Callable<String>) () -> {
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "包子准备完毕";
        };
        Callable callable2= () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "凉菜准备完毕";
        };
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
        executor.submit(futureTask);
        executor.submit(futureTask2);
        executor.shutdown();
        //如果任务结束，无论正常结束、中断、异常结束都返回true
        futureTask.isDone();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间："+(end-start));
    }

}
