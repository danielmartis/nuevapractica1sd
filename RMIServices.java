package Registry;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIServices extends Remote {
    int volumen() throws RemoteException;
    String fecha() throws RemoteException;
    String ultimafecha() throws RemoteException;
    int luz() throws RemoteException;
    void setluz(int value) throws RemoteException;
}
