 public class Dog {
    String name;
    int age;

    void bark (int numOfBarks) { //numOfBarks является параметром его тип int, аргумент 3 присваивается параметру автоматически
        while (numOfBarks > 0) { //используем параметр numOfBarks в качестве переменной внутри метода
            System.out.println("Gav");
            numOfBarks = numOfBarks - 1;
        }
    }
    public static void main (String[] args) {
        Dog d = new Dog();
        d.bark(4); // данная тройка 3 является аргументом

    }



    /*int theSecret = Dog.giveSecret();  //метод возращяемого значения
    int giveSecret() {
        return 42;
    }*/

    void takeTwo(int x, int y) {
        int z = x + y;
        System.out.println("sum = " + z);
    }
    void go() {
        Dog d = new Dog();
        d.takeTwo(12, 32);
    }
}
