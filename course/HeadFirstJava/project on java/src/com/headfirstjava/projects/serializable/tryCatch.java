package com.headfirstjava.projects.serializable;

public class tryCatch{

   /* void someMethod() {
            try{
                System.out.println("try");
                Object o = null;
                o.hashCode();
            } catch(NullPointerException e) {
                System.out.println("catch");
                e.printStackTrace();
            } finally {
                System.out.println("finally");
            }
        }*/


        public static void main (String[] args) {
            new tryCatch().anotherMethod();
        }

    void anotherMethod() {
        try {
            System.out.println("try");
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                throw new RuntimeException(); //метод выбрасывает исключение
            } catch (ArrayIndexOutOfBoundsException er) {
                System.out.println("catch");
            }
        }
    }
}
