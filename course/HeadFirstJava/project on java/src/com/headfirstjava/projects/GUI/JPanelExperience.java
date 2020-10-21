package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelExperience implements ActionListener { //its means we have to take all the method from ActionListener
    int count = 0;
    int click = 0;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    private JLabel label;
    private JLabel label4;
    private JTextField text;
    private JPasswordField textP;
    private JButton button2;
    private JLabel label2;
    private JLabel label3;

    public JPanelExperience() {
        panel = new JPanel();
        label = new JLabel("Number of clicks: 0");
        button = new JButton("Click me");
        frame = new JFrame();

        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1)); //мы пишем new GridLayout, чтобы установить этот диспетчер, потому что у panel по умолчанию стоит FlowLayout
        panel.add(button);
        panel.add(label);
        panel.setBackground(Color.lightGray); //panel Color
        button.addActionListener(this); //addActionListener() принимает класс, который реализует интерфейс ActionListener, в данном случае наш класс реализует интерфейс ActionListener поэтому говорим this якобы говоря что это класс
        label2 = new JLabel("User");
        label2.setBounds( 10, 20, 50, 25 ); //why it doesnt work?
        panel.add(label2);

        text = new JTextField();
        text.setBounds(100, 20, 165, 25);
        panel.add(text);



        label3 = new JLabel("Password");
        label3.setBounds(10, 50, 50, 25);
        panel.add(label3);

        textP = new JPasswordField();
        text.setBounds(100, 20, 165, 25);
        panel.add(textP);

        button2 = new JButton("Login");
        button2.setBounds(10, 80, 80, 25);
        panel.add(button2);
        button2.addActionListener(new Listener());
        button2.setToolTipText("Look at the terminal after clicking this button");


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.setVisible(true);
        frame.pack();
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);

        label4 = new JLabel("");
        label4.setBounds(100, 110, 300, 25);
        panel.add(label4);
    }

    public static void main (String[] args) {
    JPanelExperience panelE =  new JPanelExperience();
    }

    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks: " + count);
    }

    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            click++;
            System.out.println("Button clicked " + click + " times");
            String user = text.getText();
            String password = textP.getText();
            System.out.println(user + ", " + password);

            if(user.equals(text.getText())) {
                System.out.println("Login successful");
            }
        }
    }
}

