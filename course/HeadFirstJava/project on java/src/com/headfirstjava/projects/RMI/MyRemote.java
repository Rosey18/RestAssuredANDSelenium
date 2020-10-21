package com.headfirstjava.projects.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote { //интерфес должен быть унаследован от java.rmi.Remote
    public String sayHello() throws RemoteException; //все удаленные методы должны содержать объявление RemoteException

}
