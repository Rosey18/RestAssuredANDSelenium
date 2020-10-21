package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UTubeTutorial1 extends JFrame {
    JButton b1, b2;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2;
    String a, b;
    int i, k;

    eHandler handler = new eHandler();

    public UTubeTutorial1(String s) {
        super(s);
        setLayout(new FlowLayout());//компоненты расплогаются слева направо
        b1 = new JButton("Clear");
        b2 = new JButton("Count");
        l1 = new JLabel("Enter first number:");
        l2 = new JLabel("Enter second number:");
        l3 = new JLabel("");
        l4 = new JLabel("");
        t1 = new JTextField(10);
        t2 = new JTextField(10);
        add(b1);
        add(b2);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(l4);
        b2.addActionListener(handler);
        b1.addActionListener(handler);
        b2.setToolTipText("This button is gonna increment your first/second entering number");
        b1.setToolTipText("This button is gonna clear your entering numbers");
    }
    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == b2) { //ели источником события является кнопка(подсчитать), то сделай это
                    i = Integer.parseInt(t1.getText()); //преобразование String в int
                    k = Integer.parseInt(t2.getText());
                    i++;
                    k++;

                    a = "Ur first number equal: " + i;
                    b = "Ur second number equal: " + k;

                    l3.setText(a);
                    l4.setText(b);

                }
                if (e.getSource() == b1) { //ели источником события является кнопка(очистить), то сделай это
                    t1.setText(null); //очищаем поля ввода/вывода
                    t2.setText(null); //очищаем поля ввода/вывода
                    l3.setText(null); //очищаем лабел
                    l4.setText(null); //очищаем лебел
                }
            } catch (Exception ex) { //этот блок будет перехватывать все исключения потомка класса Exception, поэтому вы не сможете автоматически узнать, что именно пошло не так
                JOptionPane.showMessageDialog(null, "Please enter only integer type!"); //null means - MessageDialog position will be right in the middle of a screen

            }
        }

    }
}
