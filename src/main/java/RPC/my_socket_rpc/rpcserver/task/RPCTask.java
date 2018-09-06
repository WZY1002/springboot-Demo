package RPC.my_socket_rpc.rpcserver.task;

import RPC.my_socket_rpc.model.PRCTanslator;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCTask implements Runnable{
    private ServerSocket server;

    public RPCTask(ServerSocket server) {
        this.server=server;
    }

    @Override
    public void run() {
        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException e) {
                continue;
            }
            InputStream is = null;
            ObjectInputStream ois = null;
            OutputStream os = null;
            ObjectOutputStream oos = null;
            try {
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                PRCTanslator tanslator = (PRCTanslator) ois.readObject();

                Class serviceClass = tanslator.getServiceClass();
                //此处通过serviceClass获取实现类省略（dubbo是通过zookeeper服务注册实现，我这里直接new）
                //UserServiceImpl userService = new UserServiceImpl();

                //通过反射直接获取对象，也可以通过存根的全类名反射获取对象
                Object userService=serviceClass.newInstance();

                String methodName = tanslator.getMethodName();
                Class[] paramsTypes = tanslator.getParamsTypes();
                Object[] paramsValue = tanslator.getParamsValue();
                //根据方法名，参数名获取方法
                Method method = userService.getClass().getMethod(methodName, paramsTypes);
                //反射执行方法
                Object result = method.invoke(userService, paramsValue);
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(result);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
