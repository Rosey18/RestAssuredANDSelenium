package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtons { //в данный момент главный класс GUI не реализует интерфес ActionListener
    JFrame frame;
    JLabel label;

    public static void main (String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }
    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("change label");
        labelButton.addActionListener(new LabelListener()); //вместо того чтобы предавать(this) методу регистрации слушателя кнопки, мы передаем ему экземпляр соответсвующего класса слушателя
        //метод addActionListener() принимает класс, который реализует интерфейс ActionListener

        JButton colorButton = new JButton("change circle");
        colorButton.addActionListener(new ColorListener()); //вместо того чтобы предавать(this) методу регистрации слушателя кнопки, мы передаем ему экземпляр соответсвующего класса слушателя
        //метод addActionListener() принимает класс, который реализует интерфейс ActionListener
        
        label = new JLabel("I'm a LABEL");
        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.add(BorderLayout.SOUTH, colorButton);
        frame.add(BorderLayout.CENTER, drawPanel);
        frame.add(BorderLayout.EAST, labelButton);
        frame.add(BorderLayout.WEST, label);

        frame.setSize(350,350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Our GUI");
    }

    class LabelListener implements ActionListener { //теперь у нас два ActionListener, в данным случае первый
        public void actionPerformed(ActionEvent event) {
            label.setText("Ouch!"); //внутренний класс знает о label
        }
    }

    class ColorListener implements ActionListener { //теперь у нас два ActionListener, в данном случае второй
        public void actionPerformed(ActionEvent event) {
            frame.repaint(); //внутренний класс использует переменую экземпляра frame без явной ссылки на объект внешнего класса
        }
    }
}
