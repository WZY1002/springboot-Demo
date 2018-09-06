package RPC.my_socket_rpc.rpcclient.proxy;

import RPC.my_socket_rpc.model.PRCTanslator;
import RPC.my_socket_rpc.rpcserver.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RPCClientHandler implements InvocationHandler {
    private String host;
    private int port;
    private Class serviceClass;

    public RPCClientHandler(String host, int port, Class serviceClass) {
        this.host = host;
        this.port = port;
        this.serviceClass = serviceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            Socket socket = new Socket(host, port);
            PRCTanslator tanslator = new PRCTanslator();
            //tanslator.setServiceClass(serviceClass);
            //传入存根的函数路径或者全类名都可以，服务端反射获取对象
            tanslator.setServiceClass(UserServiceImpl.class);
            tanslator.setMethodName(method.getName());
            tanslator.setParamsValue(args);

            Class[] paramsTypes = new Class[args.length];
            for(int i = 0; i < args.length; i++){
                Class clazz = args[i].getClass();
                paramsTypes[i] = clazz;
            }
            tanslator.setParamsTypes(paramsTypes);

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(tanslator);

            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            Object result = ois.readObject();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
