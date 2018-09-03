package RPC.rmi;

import java.rmi.Remote;

public class RMIServiceImpl implements RMIService  {
    @Override
    public String sayhello(String name) {
        return  "Hello "+name+"";
    }
}
