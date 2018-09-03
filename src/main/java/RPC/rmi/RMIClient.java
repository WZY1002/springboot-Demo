package RPC.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI客户端
 * @author wzy
 * @return
 * @date 2018/8/31
 **/
public class RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String name="RPC.rmi.RMIService";

        //获取某地址的注册
        Registry registry= LocateRegistry.getRegistry("localhost",8089);

        //查找相应的服务
        RMIService rmiService=(RMIService)registry.lookup(name);

        //调用服务
        System.out.println(rmiService.sayhello("wzy"));
    }
}
