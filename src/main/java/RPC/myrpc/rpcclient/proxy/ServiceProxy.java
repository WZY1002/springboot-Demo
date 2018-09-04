package RPC.myrpc.rpcclient.proxy;

import java.lang.reflect.Proxy;

public class ServiceProxy {
    private String host;
    private Integer port;

    public ServiceProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Object getService(Class serviceInterface) {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { serviceInterface }, new RPCClientHandler(host, port, serviceInterface));
        return proxy;
    }
}
