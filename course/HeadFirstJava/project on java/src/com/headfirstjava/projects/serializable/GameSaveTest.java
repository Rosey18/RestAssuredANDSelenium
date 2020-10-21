package com.headfirstjava.projects.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaveTest {
    public static void main (String[] args) {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"arrow", "knife", "kastet"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"big strong arm", "big topor"});
        GameCharacter three = new GameCharacter(120, "Magician", new String[]{"zaklinaniya", "nevidimost"});

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser")); //FileOutputStream - записывает байты в файл, ObjectOutputStream - преобразует объекты в данные
            os.writeObject(three); //в каком порядке мы запишем в том порядке они будут прочитаны, обрати внимание, что сперва мы записываем третьий объект
            os.writeObject(one); //когда мы вызываем метод writeObject из объекта ObjectOutputStream, объект подключается к потоку и переходит к FileOutputStream, где в результате записывается в файл в виде байтов
            os.writeObject(two);
            os.close(); //закрывая верхний поток мы закрываем и находящиеся ниже, так что FileOutputStream закроется автоматически
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        one = null;
        two = null;
        three = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser")); //ObjectInputStream позволяет прочитать объекты, но не может напрямую соединиться с файлом. Ему ужно подключится к потоку соединения, т.е. к FileInputStream
            GameCharacter oneRestore = (GameCharacter) is.readObject(); //когда мы вызываем метод readObject(), мы получаем следуюший объект в потоке. В итоге мы будем прочитывать их в том же порядке, в котором они были записанны
            GameCharacter twoRestore = (GameCharacter) is.readObject(); //когда мы используем readObject, нам он возвращает Object, поэтому мы возвращаем нашего com.headfirstjava.projects.serializable.GameCharacter и нам соответственно нужно это закастить(twoRestore)
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("First type " + oneRestore.getType());
            System.out.println("Second type " + twoRestore.getType());
            System.out.println("Third type " + threeRestore.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}