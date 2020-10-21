import java.util.ArrayList;

public class DotCom {
    
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> locs) { //объявляем строковый массив ArrayList, чтобы хранить объекты String
        locationCells = locs;
    }

    public void setName(String n) {
        name = n;
    }



    public String checkYourSelf(String userInput) { //новый и усовершественное имя аргумента
        String result = "Miss";
        int index = locationCells.indexOf(userInput); //Проверяем содержит ли загаданная пользователем ячейка внутри ArrayList, запрашивая ее индекс. Если нет в списке то indexOf() возвращает -1
        if (index >= 0) { //если индекс больше или равен нулю, то загаданная пользователем ячейка определенно находится в списке
            locationCells.remove(index); //поэтому удаляем ее.

            if (locationCells.isEmpty()) { //если список пустой, значит, это было попадание
                result = "Destroyed";
            } else {
                result = "Yep";
            }
        }
        return result;
    }
}


  /*  public static void main(String[] args) {
        int numOfGuesses = 0; //создаем переменную чтобы следить за количеством ходов пользователя

        GameHelper helper = new GameHelper(); //Это специальный класс, который содержит метод для приема пользовательского ввода. Пока сделаем вид что это часть Джавы

        DotCom theDotCom = new DotCom(); //создаем объект "сайт"
        int randomNum = (int) (Math.random() * 5); //генерируем случайное число для первой ячейки и используем его для формирования массива ячеек.

        int[] locations = {randomNum, randomNum + 1, randomNum + 2}; //генерируем случайное число для первой ячейки и используем его для формирования массива ячеек.
        theDotCom.setLocationCells(locations); //передаем "сайту" местоположение его ячеек(массив) (т.е. аргумент locations передается в параметр сеттера, а именно в locs который в свою очередь равен(присваивает) к locationCells
        boolean isAlive = true; //создаем булеву переменную, чтобы проверять в цикле не закончилась ли игра

        while (isAlive == true) {
            String guess = helper.getUserInput("Enter number"); //получаем строку вводимую пользователям
            String result = theDotCom.checkYourSelf(guess); //просим "сайт" проверить полученные данные: сохроняем возвращенный результат в переменную типа Стринг
            numOfGuesses++; //инкерементируем количество попыток
            if (result.equals("Destroyed")) { //потполен ли "сайт"? Если да =, то присваиваем isAlive значение false(т.к. не хотим продолжить цикл) и выводим на жкран количество поппыток
                isAlive = false;
                System.out.println("You have " + numOfGuesses + " chance");
            }
        }
    }
}*/

