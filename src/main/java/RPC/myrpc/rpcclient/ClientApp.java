package RPC.myrpc.rpcclient;


import RPC.myrpc.IUserService;
import RPC.myrpc.model.UserVO;
import RPC.myrpc.rpcclient.proxy.ServiceProxy;

public class ClientApp {
    public static void main(String[] args) {
        ServiceProxy serviceProxy=new ServiceProxy("localhost",8088);
        IUserService userService = (IUserService)serviceProxy.getService(IUserService.class);

        userService.sayHello("kevin");
        UserVO user = new UserVO("kevinliu", 28, "f");
        userService.sayHello(user);

        UserVO rpcUser = userService.getUser(1);

        //System.out.println(rpcUser);
    }
}
