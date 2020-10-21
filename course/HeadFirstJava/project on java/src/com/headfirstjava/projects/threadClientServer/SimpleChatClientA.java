package com.headfirstjava.projects.threadClientServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClientA {
    JTextArea incoming; //создается окно для ввода текста, которая может включать в себя несколько строк текста.
    JTextField outgoing; //тоже окно для ввода, но с маленьким окошком. К пример его можно использовать для ввода логина или пароля, или имени
    BufferedReader reader; //BufferedReader символьный поток, чтобы считать данные из сокета
    PrintWriter writer; //PrintWriter ведет себя как мост между символьными данными и байтами, которые получает из низкоуровневого исходящего потока, предоставляемого сокетом. Подключив PrintWriter к исходящему потоку, мы можем записывать строки в сокет
    Socket socket; //для связывание с сервером

    public void go() {
        JFrame frame = new JFrame("Simple Chat Client"); //создаем окно
        JPanel mainPanel = new JPanel(); //создаем панель для окна
        incoming = new JTextArea(15,50); //15 значит 15 строк(изначально при пояления данной окошки она будет иметь размер вместимости на 15 строк сразу)
        incoming.setLineWrap(true); //включаем перенос строки
        incoming.setWrapStyleWord(true); //устанавливает стиль обтекания, используемый, если текстовая область представляет собой перенос строк. Если установлено значение true, строки будут перенесены на границы слов, если они слишком длинные, чтобы уместиться в пределах выделенной ширины. Если установлено значение false, строки будут переноситься по грнаицам символов. По умолчанию это свойство имеет значение false
        incoming.setEditable(false); //устанавливает конкретное логическок значение, чтобы указать, должен ли этот текстовый компонент быть редактируемым
        JScrollPane qScroller = new JScrollPane(incoming); //добавляем прокручиваемость
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        mainPanel.add(qScroller);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();
        frame.setVisible(true);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

    private void setUpNetworking() {
        try{
            socket = new Socket("127.0.0.1", 5000); //соединяемся с сокетом
            writer = new PrintWriter(socket.getOutputStream()); //сокет дает нам низкоуровневый(байтовый) поток. Мы передаем его в конструктор PrintWriter, содавая цепочку
            System.out.println("networking established");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoing.getText()); //выводим на экран, то что получаем из JTextField
                writer.flush(); //моментально
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }

    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) { //пока есть что считывать с помощью метода readLine() объекта BufferedReader, то и показываем на экран
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    new SimpleChatClientA().go();
    }
}
