package com.headfirstjava.projects.threadClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DailyAdviceClient {
    public void go() {
        try {
            Socket s = new Socket("localhost", 4242); //сокет - это объект, представляющий сетевое соединение между двумя компьютерами. Чтобы создать соединение через сокет нужно знать IP - адрес, и номер порта

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream()); //InputStreamReader(пробразует байты в символы) - это мост, соединяющий низкоуровненвый поток байтов(скажем из сокета) и выскоуровневый символьный поток(BufferedReader). getInputStream() - нужно лишь запросить у сокета входящий поток. Он низкоуровненвый, но мы просто подсоединим его к объекту, который лучше умеет рабоать с текстом
            BufferedReader reader = new BufferedReader(streamReader); //подключаем BufferedReader(буферизированные символы) к InputStreamReader

            String advice = reader.readLine(); //метод readLine() работает точно так же, как если бы BufferedReader был подключен к файлу, т.е. раюотая методом BufferedReader вы не знаете откуда пришли символы
            System.out.println("Today u gonna: " + advice);

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public static void main(String[] args){
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
        }
}
