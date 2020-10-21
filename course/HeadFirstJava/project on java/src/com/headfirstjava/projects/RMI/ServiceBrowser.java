package com.headfirstjava.projects.RMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class ServiceBrowser {
    JPanel mainPanel;
    JComboBox serviceList; //раскрывающийся список
    ServiceServer server; //удаленный интерфейс

    public void buildGUI() {
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        Object[] service = getServiceList(); //этот метод выполняет поиск по реестру RMI, получает "заглушку" и вызывает метод getServiceList()
        serviceList = new JComboBox(service); //добавляем сервисы(массив элементов Object) в виджет JComboBox
        frame.getContentPane().add(BorderLayout.NORTH, serviceList);
        serviceList.addActionListener(new MyListListener());
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void loadService(Object serviceSelection) {
        try{                                                    //здесь мы добавляем на панель настоящий сервис, полсе того как пользователь выберет его в списке
            Service svc = server.getService(serviceSelection);  //мы вызываем getService() из удаленного сревера ("заглушку" для ServiceServer) и передаем ему строку, отображающуюся в списке(строка котрую мы получили при вызове метода getServiceList)
            mainPanel.removeAll();                              //сервер возвращает сериализованный объект сервиса, который благодаря RMI авт.десериализуется
            mainPanel.add(svc.getGuiPanel());                   //полсе чего просто вызываем метод getGuiPanel()
            mainPanel.validate();
            mainPanel.repaint();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    class MyListListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object selection = serviceList.getSelectedItem(); //пользователь сделал выбор элемента из списка JComboBox. Значит мы берем выбранный элемент и загружаем соответсвующий сервис
            loadService(selection); //и передаем в метод loadService
        }
    }

    Object[] getServiceList() {
        Object obj = null;
        Object[] services = null;
        try{
            obj = Naming.lookup("rmi://192.168.43.140/ServiceServer"); //выполняем поиск по реестру RMI и получаем "заглушку"
        }catch (Exception e) {
            e.printStackTrace();
        }
        server = (ServiceServer) obj; //приводим тип "заглушки" к типу удаленного интерфейса, чтобы в дальнейшем вызывать из него метод getServiceList()
        try{
            services = server.getServiceList(); //метод возвращает массив с жлементами типа Object. Мы можем вывести их в списке JComboBox, с помощью которого пользователь будет делать выбор
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return services;
    }

    public static void main(String[] args) {
        new ServiceBrowser().buildGUI();
    }
}
