package com.headfirstjava.projects.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
//обычный удаленный RMI - интерфейс
public interface ServiceServer extends Remote {
    Object[] getServiceList() throws RemoteException;
    Service getService(Object serviceKey) throws RemoteException;
}
