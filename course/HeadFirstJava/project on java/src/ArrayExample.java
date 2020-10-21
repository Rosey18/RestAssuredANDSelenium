class ArrayExample {
    String name;

    public static void main(String[] args) {
        ArrayExample dog1 = new ArrayExample(); //создаем объект, и получаем к нему доступ
        dog1.name = "Rex";
        dog1.bark();

        ArrayExample[] arr = new ArrayExample[3]; //создаем массив типа ArrayExample
        arr[0] = new ArrayExample(); //и поместим в него несколько элементов
        arr[1] = new ArrayExample();
        arr[2] = dog1;

        //Теперь получаем доступ к объектом ArrayExample
        //с помощью ссылок из массива
        arr[0].name = "Alfa";
        arr[1].name = "Gerda";

        System.out.print("Имя последней собаки - ");
        System.out.println(arr[2].name);

        //теперь переберем все элементы массива и вызовем для каждого метод bark()
        int x = 0;
        while (x < arr.length) {
            arr[x].bark();
            x = x + 1;
        }
    }
    void bark() {
        System.out.println(name + "said gav-gav");
    }
}