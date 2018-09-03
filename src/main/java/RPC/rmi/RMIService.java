package RPC.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService  extends Remote {
    String  sayhello(String wzy) throws RemoteException;
}
