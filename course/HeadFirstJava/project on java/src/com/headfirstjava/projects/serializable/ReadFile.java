package com.headfirstjava.projects.serializable;

import java.io.*;

public class ReadFile {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Тигр\\readFile.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




/* import java.io.FileInputStream; //почему выводит какие-то цифры, а не то что в текстовом файле
import java.io.IOException;
import java.io.InputStreamReader;

public class com.headfirstjava.projects.serializable.ReadFile {
    public static void main(String[] args) {
        FileInputStream file = null;
        InputStreamReader isr = null;
        int a= 0;

        try{
            file = new FileInputStream("C:\\Users\\Тигр\\readFile.txt");
            isr = new InputStreamReader(file, "UTF-8");

            while ((a = isr.read()) !=-1) {
                System.out.println(a);
            }
            isr.close();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}*/