package com.headfirstjava.projects.RMI;

import java.rmi.Naming;

public class MyRemoteClient {
    public static void main(String[] args){
        new MyRemoteClient().go();
    }
    public void go() {
        try{
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/Remote hello"); //реестр возвращает занчение класса Объект, поэтому не забудьте привести его к соответсвующему типу
            String s = service.sayHello();                     //нам нужен IP адрес или имя домена
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
