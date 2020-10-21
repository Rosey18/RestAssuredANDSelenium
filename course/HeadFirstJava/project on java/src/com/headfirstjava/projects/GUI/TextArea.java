package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextArea implements ActionListener {
    JTextArea text;

    public static void main(String[] args) {
        TextArea gui = new TextArea();
        gui.go();
    }
    public void go () {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("Just click me");
        button.addActionListener(this);
        text = new JTextArea(10,20); //10 значит 10 строк(изначально при пояления данной окошки она будет иметь размер вместимости на 10 строк сразу), 20 - это 20 столбцов
        text.setLineWrap(true); //включаем перенос строки
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scroller = new JScrollPane(text); //создаем объект JScrollPane и присваеиваем ему текстовую область, которую он будет прокручивать
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //указывем панели прокрутки использовать только вертикальную прокрутку
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroller);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setSize(350, 300);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        text.append("button clicked \n");
    }
}
