package com.headfirstjava.projects.threadClientServer;

public class ThreadTest {
    public static void main(String[] args) {
        Runnable theJob = new MyRunnable();
        Thread t = new Thread(theJob);
        t.start();
        System.out.println("Back to the main");
    }
}
