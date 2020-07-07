package Sensor;

import Registry.RMIServices;

import java.rmi.RemoteException;


public interface SensorServices extends RMIServices {
    String getRMIName() throws RemoteException;
}
