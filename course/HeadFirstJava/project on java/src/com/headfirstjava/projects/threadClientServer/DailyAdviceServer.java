package com.headfirstjava.projects.threadClientServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    String[] adviceList = {"Ешьте меньшими порциями", "Два слово: не годится", "Будьте честны хотя бы сегодня", "Возможно вам стоит подобрать другую прическу", "Нет, эти джинсы вас не полнят", "Я тебе че советчик?", "Не забудь купить корм для собак", "Поглать рабочую рубашку"};

    public void go() {
        try{
            ServerSocket serverSock = new ServerSocket(4242); //благодаря ServerSocket приложение отслеживает клиентские запросы на порту 4242 на том же компьютере, где выпоняется данный код

            while(true)  { //сервер входит в постоянный цикл ,ожидая клиентских подключений (и обслуживая их)

                Socket sock = serverSock.accept(); //метод accept() блокирует приложение до тех пор, пока не поступит запрос, после чего возвращает сокет для взаимодействия с клиентом

                //чтобы записать данные в сокет нужно использовать PrintWriter
                PrintWriter writer = new PrintWriter(sock.getOutputStream()); //создаем PrintWriter и связываем его с низкоуровневым исходящим потоком, полученных из сокета. PrintWriter - это мост между символным данными и байтами. Подключив PrintWriter к исходящему потоку, мы можем записывать строки в сокет
                String advice = getAdvice();
                writer.println(advice); //теперь мы используем соеденение объекта Socket с клиентом для создания экземпляра PrintWriter, после чего отправляем с помощью println() строку с ответом
                writer.close();
                System.out.println(advice);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }
    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
