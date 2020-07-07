package Registry;

import Sensor.SensorServices;

import java.rmi.RemoteException;

public interface RegisterServices extends RMIServices {
    void registerSensor(SensorServices sensor) throws RemoteException;
    void unregisterSensor(SensorServices sensor) throws RemoteException;
}
