package com.headfirstjava.projects.generics;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SortMountains {
LinkedList<Mountain> mtn = new LinkedList<Mountain>();

class NameCompare implements Comparator<Mountain> {
    public int compare(Mountain one, Mountain two) {
        return one.name.compareTo(two.name);
    }
}
class HeightCompare implements Comparator<Mountain> {
    public int compare(Mountain one, Mountain two) {
        return (two.height - one.height);
    }
}
public static void main(String[] args) {
    new SortMountains().go();
}

public void go() {
    mtn.add(new Mountain("long-Reidge", 14255));
    mtn.add(new Mountain("Albert", 14433));
    mtn.add(new Mountain("Marune", 14156));
    mtn.add(new Mountain("Kasle", 14265));
    System.out.println("Vporyadke dobavleniya:\n" + mtn);

    NameCompare nc = new NameCompare();
    Collections.sort(mtn, nc);
    System.out.println("Po Nazvaniu:\n" + mtn);

    HeightCompare hc = new HeightCompare();
    Collections.sort(mtn, hc);
    System.out.println("Po Vysote:\n" + mtn);
    }
}

class Mountain {
    String name;
    int height;

    Mountain (String n, int h) {
        name = n;
        height = h;
    }
    public String toString() {
        return name + " " + height;
    }
}
