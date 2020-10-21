package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.event.*; //новый оператор импорта для пакета в котором хранятся ActionListener и ActionEvent

public class SimpleGui1 implements ActionListener //реализуем интерфейс. Код говорит: "Экземпляр com.headfirstjava.projects.GUI.SimpleGui1 реализует интерфейс ActionListener"
{                                                 //кнопка будет предавать события только тем, кто реализует ActionListener
    JButton button;
    public static void main(String[] args){
        SimpleGui1 gui = new SimpleGui1(); //создаем экземпляр класса, значит реализуем ActionListener
        gui.go();
    }
    public void go(){
        JFrame frame = new JFrame(); //создаем окно
        button = new JButton("click me"); //создаем кнопку

        button.addActionListener(this); //региструем наше заинтересованность в кнопке. Код говорит кнопке: "Добавь меня к своему списку слушателей"
                                           //передаваемый аргумент ДОЛЖЕН быть объектом класса, реализуемого ActionListener
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) { //реализуем метод actionPerformed() интрефеса ActionListener. Это практически метод отработки событий
        button.setText("I've been clicked");         //кнопка вызывает это метод, чтобы известить о наступлении события. Она отправляет объект ActionEvent как аргумент, но он нам не нужен. Достаточно знать, что события произошло
    }   //Когда происходит событие действия, вызывается метод actionPerformed
}
