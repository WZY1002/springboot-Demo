package RPC.myrpc.rpcserver;

import RPC.myrpc.rpcserver.service.ServiceProxy;

public class ServerApp {
    public static void main(String[] args) {
        new ServiceProxy().publish(8088);
    }
}
