package com.headfirstjava.projects.RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//РЕАЗЛИЗАЦИЯ УДАЛЕННОГО ИНТЕРФЕЙСА
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote { //наследование UnicastRemoteObject - самый простой способ создать удаленный объект
    public MyRemoteImpl() throws RemoteException { }

    @Override
    public String sayHello() throws RemoteException {
        return "Server saying: 'Hello'";
    }

    public static void main(String[] args) {
        try { //содаем удаленный объект, а затем помещаем его в реестр, используя статический метод Naming.rebind. Указанное имя будет использоваться клиетнами для поиска объекта в реестре RMI
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("Remote hello", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
