package com.headfirstjava.projects.RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
    HashMap serverList;

    public ServiceServerImpl() throws RemoteException{
        setUpServices();
    }
    private void setUpServices() {
        serverList = new HashMap();
        serverList.put("Dice Rolling Service", new DiceService()); //ключ (как правило - строка), значение (любой объект)
        serverList.put("Day of the Week Service", new DayOfTheWeekService()); //создаем сервисы (их настоящие объекты) и помещаем в HashMap вместе со строковым именем(ключом)
        //serverList.put("Visual Music Service", new MiniMusicService());
    }

    @Override
    public Object[] getServiceList() throws RemoteException {
        System.out.println("in remote");
        return serverList.keySet().toArray(); //мы отправляем массив типа Object(внутри содержит строки), который сосотоит только из ключей, хранящихся внутри HashMap. Мы не будем отправлять сам вервис пока клиент сам этого не попросит(вызвав метод getService())
    }

    public Service getService(Object serviceKey) throws RemoteException {
        Service theService = (Service) serverList.get(serviceKey); //клиент вызывает этот метод после того как выберет сервис в раскрывающим списке. Код использует ключ(строку), чтобы получить из ХэшМап соответсвующий сервис
        return theService;
    }
    public static void main (String[] args) {
        try{
            Naming.rebind("ServiceServer", new ServiceServerImpl()); //объект ServiceServerImpl(), помещаем в реестр, используя статический метод Naming.rebind. Указанное имя будет использоваться клиетнами для поиска объекта в реестре RMI
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Remote service is running");
    }

}
