package RPC.my_socket_rpc.rpcclient;


import RPC.my_socket_rpc.IUserService;
import RPC.my_socket_rpc.model.UserVO;
import RPC.my_socket_rpc.rpcclient.proxy.ServiceProxy;

public class ClientApp {
    public static void main(String[] args) {
        ServiceProxy serviceProxy=new ServiceProxy("localhost",8088);
        IUserService userService = (IUserService)serviceProxy.getService(IUserService.class);

        userService.sayHello("wzy");

        UserVO user = new UserVO("www", 18, "man");
        userService.sayHello(user);

        UserVO rpcUser = userService.getUser(1);

        System.out.println(rpcUser);
    }
}
