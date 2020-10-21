package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class MoreJPanel {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JLabel label2;
    JLabel label3;
    JPanel panel2;
    JPanel panel3;
    JButton button;
   // JButton button2;

    public static void main(String[] args) {
        MoreJPanel more = new MoreJPanel();
        more.go();
    }
     public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(200,200);
        JButton button = new JButton("click me");
        frame.add(BorderLayout.NORTH, button);
        Font bigFont = new Font("serif", Font.BOLD, 24);
        button.setFont(bigFont);
        panel = new JPanel();
        panel.setBackground(Color.green);
        frame.add(BorderLayout.EAST, panel);
        panel.add(new JButton("Button"));
        panel.add(new JButton("Button2"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
     }
}
