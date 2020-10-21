package com.headfirstjava.projects.generics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Jukebox1 {
    ArrayList<Song> songList = new ArrayList<Song>();
    public static void main(String[] args) {
        new Jukebox1().go();
    }

    class ArtistCompare implements Comparator<Song> { //создаем новый вложенный класс, реализующий Comparator(обратите внимание, что тип параметра сопадает с типом, который мы собираемся сранивать; в данном случае это com.headfirstjava.projects.generics.Song)
        public int compare(Song one, Song two) {
            return one.getArtist().compareTo(two.getArtist()); //сортируем по исполнителям //спрашиваем у перменной artist(String, getArtist), совпадает ли ее значение с именем исполнителя песни
                 //это становится строкой    //перекладываем всю работу по сравнению на строковые объекты, так как они уже умеют сортироваться в алфавитном порядке
        }
    }
    public void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList); //сортирует с помощью compareTo(); метод sort() может принимать только списки объектов Comparable
        System.out.println(songList);

        HashSet<Song> songSet = new HashSet<Song>(); //здесь мы создаем множество HashSet предназначенное для хранения объектов com.headfirstjava.projects.generics.Song; для HashSet мы должны переопределить методы hashCode(), equals(), т.е. с помощью этих методов он распознает дубликаты
        songSet.addAll(songList); //addAll() - простой метод, который принимает другую и использует ее, чтобы заполнить HashSet. Результат такой же, как при добавлении каждой песни отдельно
        System.out.println(songSet);

        TreeSet<Song> treeSet = new TreeSet<Song>(); //элементы TreeSet<> должны реализовать интерфейс Comparable; Если объект TreeSet<> не принимает аргумент, т.е. аргумент() пустой, значит для сортировки будет использоваться метод compareTo(); но вместо этого вы можете передать Comparator в конструктор TreeSet<>
        treeSet.addAll(songList);
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare(); //сохдаем экземпляр вложенного класса Comparator
        Collections.sort(songList, artistCompare); //вызываем метод sort(),передаем ему список и ссылку на новый объект Comparator; если у метода sort() два аргумента, то первый реализует Comparable, а второй обязательно реализует интерфейс Comparator; в этом случае будет задействован метод compare() из интерфейса Comparator
    }
    void getSongs() {
        try {
            File file = new File("C:\\Users\\Тигр\\SongList.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                addSong(line);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    void addSong(String lineToParse) {
        String[] tokens = lineToParse.split("/");

        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}
