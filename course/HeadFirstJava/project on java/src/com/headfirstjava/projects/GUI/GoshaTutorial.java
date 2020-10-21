package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoshaTutorial extends JFrame{
    private JButton button = new JButton("Press");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Input:");
    private JLabel label2 = new JLabel();
    private JRadioButton radio1 = new JRadioButton("Select this");
    private JRadioButton radio2 = new JRadioButton("Select that");
    private JCheckBox check = new JCheckBox("check", false);
    private int count = 0;


    public GoshaTutorial() {
        super("The title bar");
        this.setBounds(100,100,250,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,3,2,2));
        container.add(label);
        container.add(label2);
        container.add(input);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        container.add(check);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
        button.addActionListener(new CountPress());


    }
    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        String message = "";
        message += "Button was pressed\n";
        message += "Text is " + input.getText() + "\n";
        message += (radio1.isSelected() ? "Radio #1" : "Radio #2") + " is selected\n";
        message += "Checkbox is " + ((check.isSelected()) ? "checked\n" : "unchecked\n");
        message += count + " - numbers of press\n";
        JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);

        }
    }
    class CountPress implements ActionListener {
        public void actionPerformed (ActionEvent a) {
            count++;

        }

    }
}
