package com.headfirstjava.projects.generics;

class Song implements Comparable<Song>{ //мы указываем тип, с которым может сравниваться класс, реализующий Comparable. Как правило, они совпадают. Получается, что при сортировке объекты com.headfirstjava.projects.generics.Song могут сравниваться с другими объектами com.headfirstjava.projects.generics.Song
    String title;
    String artist;
    String rating;
    String bmp;

    public boolean equals(Object aSong) { //HashSet (или кто-нибудь еще, кто вызывает этот метод) передает в параметры объект com.headfirstjava.projects.generics.Song
        Song s = (Song) aSong;
        return getTitle().equals(s.getTitle()); //title - это строка, а у строки есть переопределенный метод equals(). Нужно лишь спросить у переменной title совпадает ли ее значение с названием переденной песни
    }

    public int hashCode() {
        return title.hashCode(); //title - это строка, а у строки есть переопределенный метод hashCode(), поэтому можно просто вызвать его из переменной title и вернуть результат
    }

    Song(String t, String a, String r, String b) {
        title = t;
        artist = a;
        rating = r;
        bmp = b;
    }

    public String getTitle() {
        return  title;
    }
    public String getArtist() {
        return  artist;
    }
    public String getRating() {
        return  rating;
    }
    public String getBmp() {
        return  bmp;
    }

    public String toString() {
        return title;
    }

    public int compareTo(Song s) { //метод sort() передает объект com.headfirstjava.projects.generics.Song в compareTo(), чтобы увидеть, как тот соотносится с экземпляром com.headfirstjava.projects.generics.Song, из которого вызван метод
        return title.compareTo(s.getTitle()); //здесь мы перекладываем всю работу на объекты String, представляющий собой переменные title, т.к. знаем, что у String есть метод compareTo
           //сравнивает переменные title
    }
}
