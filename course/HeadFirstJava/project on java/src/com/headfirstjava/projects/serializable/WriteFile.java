package com.headfirstjava.projects.serializable;

import java.io.*;

public class WriteFile {


    public static void main(String[] args) {
        try {
            //File file = new File("C:\\Users\\Тигр\\writeFile.txt");
            //FileWriter writer = new FileWriter(file, true);
            FileWriter fileWriter = new FileWriter("C:\\Users\\Тигр\\writeFile.txt", false); //true - дописывает файл, false - перезаписывает файл
            fileWriter.write("If u see it, it's works (Let's go)");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //File file = new File("C:\\Users\\Тигр\\writeFile.txt"); //File - позволяет работать с каталогами\файлами внешних носителей
            //FileReader fr = new FileReader(file)
            //FileReader fReader = FileReader("C:\Users\Тигр\writeFile.txt");
            FileReader fileReader = new FileReader(new File("C:\\Users\\Тигр\\writeFile.txt")); //FileReader - для фактического чтения
            BufferedReader bufferedReader = new BufferedReader(fileReader); //BufferedReader - счтитывает текст из потока, буферизует(преобразовывает байты в символы, кажется) символы, для эффективного чтения.
            String line = null;
            while ((line = bufferedReader.readLine()) != null) { //это озночает "пока эта переменная(line) не пуста, считывай строки и выводи их на экран"
                System.out.println(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
