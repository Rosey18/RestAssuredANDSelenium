package com.headfirstjava.projects.GUI;

import javax.swing.*;
import java.awt.*;

//здесь мы просто создаем градиентовый круг
public class MyDrawPanel extends JPanel //создаем наследника JPanel - виджет который вы сможете добавить во фрейм как любой другой. Только это будет ваш личный пользовательский метод
{   public void paintComponent (Graphics g) { //весь ваш графический код распологается внутри метода paintComponent()
    Graphics2D g2d = (Graphics2D) g; //указываем его, чтобы иметь возможность вызвать нечто такое, что есть у Graphics2D но нету у Graphics
    int red = (int)(Math.random() * 255); //значение 1,0 или 255 означает, что цвет является полностью непрозрачным
    int green = (int)(Math.random() * 255);
    int blue = (int)(Math.random() * 255);
    Color startColor = new Color (red, green, blue); //класс Color используется для инкапсуляции цветов в цветовом пространстве sRGB по умолчанию или цветов в произвольных цветовых пространствах

     red = (int)(Math.random() * 255);
     green = (int)(Math.random() * 255);
     blue = (int)(Math.random() * 255);
    Color endColor = new Color(red, green, blue);

    GradientPaint gradient = new GradientPaint(70,70,startColor, 150,150,endColor); //Класс GradientPaint обеспечивает способ заполнения фигуры с помощью линейного цветового градиента
    g2d.setPaint(gradient); //здесь мы назначаем для виртуальной кисти градиент вместо сплошного цвета
    g2d.fillOval(70,70,100,100); //здесь мы создаем фигуру, в данном случае круг
                   //первая 70 - это 70 пикселов от левого края, вторая 70 - это 70 пикселов от верхнего края
}


    //СОЗДАЕМ ВИДЖЕТ ДЛЯ РИСОВАНИЯ
    /*public void paintComponent (Graphics g) //это большой важный графический метод. Вы никогда не будете вызывать его сами.
    {                                       // Система вызывает его и говорит: "Вот прексрасная новая поверхность для рисования, принадлежащая типу Graphics, и ты можешь на ней рисовать

        g.setColor(Color.orange); //представьте, что g - это машина для рисования. Вы сообщаете каким цветом и какую фигуру нарисовать(с координатами местоположения и размером)
        g.fillRect(20,50,100,100);
    }*/

    //ПОМЕЩАЕМ ИЗОБРОЖЕНИЯ В ВИДЖЕТ
   /* public void paintComponent(Graphics g) {
        Image image = new ImageIcon("filename").getImage();
        g.drawImage(image, 3, 4, this);
    }*/

    //РИСУЕМ НА ЧЕРНОМ ФОНЕ КРУГ ПРОИЗВОЛЬНОГО ЦВЕТА
    /*public void paintComponent (Graphics g) {
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); //два первых аргумента определяют координаты верхнего левого угла по отношению к панели, где начнется рисовниая.
        //здесь 0б 0 означает: "начни с 0 пикселов от левого края и 0 пикселов от верзнего". Жва жругиз аргумента говорят: "Сделай щирину прямоугольника как у панели this.getWidth(), и высоту как у панели this.getHeight()
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);

        Color randomColor = new Color(red, green, blue);
        g.setColor(randomColor);
        g.fillOval(70, 70, 100, 100); //начинаем рисовние с 10 пикселов слева и 10 пикселов сверху, а также задаем ширину и высоту по 100 пикселов
    }*/
}

