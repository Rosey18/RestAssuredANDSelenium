public class Player {
    int number = 0; //Здесь будет хранится число которое сгенерировалась для игрока
    public void guess() {
        number = (int) (Math.random() * 10); //Метод для угадывание чисел
        System.out.println("Думаю, это число " + number);
    }
}
