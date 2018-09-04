package RPC.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI提供服务端
 * @author wzy
 * @return
 * @date 2018/8/31
 **/
public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        //System.setProperty("java.rmi.server.hostname", "192.168.2.76");
        String name="RPC.rmi.RMIService";

        //创建服务
        RMIService rmiService=new RMIServiceImpl();

        //创建本机8088端口上的RMI注册表
        Registry registry= LocateRegistry.createRegistry(8089);

        //将服务绑定到注册表
        registry.bind(name,rmiService);
    }
}
