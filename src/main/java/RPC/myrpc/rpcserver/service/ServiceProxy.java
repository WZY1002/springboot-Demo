package RPC.myrpc.rpcserver.service;

import RPC.myrpc.rpcserver.task.RPCTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceProxy {
    ExecutorService exec= Executors.newFixedThreadPool(5);

    public void publish(int port) {
        ServerSocket server=null;
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行线程（execute没有返回值，而submit有返回值。）
        exec.submit(new RPCTask(server));
    }
}
