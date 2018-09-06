package RPC.my_socket_rpc.rpcserver;

import RPC.my_socket_rpc.rpcserver.service.ServiceProxy;

public class ServerApp {
    public static void main(String[] args) {
        new ServiceProxy().publish(8088);
    }
}
