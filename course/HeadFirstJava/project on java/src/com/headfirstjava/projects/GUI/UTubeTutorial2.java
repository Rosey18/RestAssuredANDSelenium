package com.headfirstjava.projects.GUI;

import javax.swing.*;

public class UTubeTutorial2 {
   public static void main (String[] args) {
       UTubeTutorial1 U = new UTubeTutorial1("My Frame");
       U.setVisible(true);
       U.setSize(255, 200);
       U.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       U.setResizable(false); //чтобы не изменять размер окна
       U.setLocationRelativeTo(null); //это кажется чтобы окно появлялась по центру
   }
}
