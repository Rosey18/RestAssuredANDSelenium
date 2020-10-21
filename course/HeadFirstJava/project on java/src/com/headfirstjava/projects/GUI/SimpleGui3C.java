package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui3C implements ActionListener{
    JFrame frame;

    public static void main(String[] args) {
        SimpleGui3C gui = new SimpleGui3C();
        gui.go();
    }
    public void go() {
        frame = new JFrame(); //создаем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрываем его при нажатия на крестик, чтобы он закрывался, а то он юудет висеть вечно

        JButton button = new JButton("change color"); //создаем кнопку, и даем ей названия(change color)
        button.addActionListener(this); //добавляем слушателя(this) к кнопке

        MyDrawPanel drawPanel = new MyDrawPanel(); //экземпляр класса, который генерирует градиентовый круг

        frame.getContentPane().add(BorderLayout.SOUTH, button); //добавляем два виджета(в данном случае кнопку) в южной области фрейма
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel); //добавляем два элемента(в данном случае круг) по центру фрейма
        frame.setSize(300,300); //размер нашего создамаемого окна
        frame.setVisible(true); //делаем его видимым
    }
    public void actionPerformed (ActionEvent event) {
        frame.repaint(); //когда пользователь нажимает кнопку, вызываем для фрейма метод repaint(). Это значит, что метод paintComponent() вызывается для каждого метода во фрейме
    }
}
