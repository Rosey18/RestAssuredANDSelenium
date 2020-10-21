package com.headfirstjava.projects.threadClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServerA {
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream()); //InputStreamReader - это мост, соединяющий низкоуровневый поток байтов(получаемый из сокета) и высокуровневый символьный поток(например BufferedReader)
                reader = new BufferedReader(isReader); //подключаем BufferedReader к InputStreamReader
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read" + message);
                    tellEveryone(message);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleChatServerA().go();
    }

    public void go() {
        clientOutputStreams = new ArrayList();
        try{
            ServerSocket serverSock = new ServerSocket(5000);

            while(true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        }catch (Exception ex) {
        }
    }

    public void tellEveryone(String message) {
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()) {
            try{
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
