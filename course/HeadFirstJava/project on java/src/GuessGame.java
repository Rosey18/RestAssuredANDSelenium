public class GuessGame {
    Player p1; //три пемеременные для трёх объктов
    Player p2;
    Player p3;
    public void startGame() { //Создаем метод startGame который в последствии будем использовать в классе с main методом
        Player p1 = new Player(); //создаем три объекта Player и присваиваем их трем переменным экземпляра
        Player p2 = p1;
        Player p3 = p2;

        int guessp1;
        guessp1 = 0; //объявляем три переменных для храниния рандомных чисел от каждого игрока,
        int guessp2 = guessp1; //в дальнейшим к этой переменной мы привяжим, переменную number из объкта Player
        int guessp3 = 0; //потому что number тоже генерирует числа, затем сравним число каждого игрока с targetNumber

        boolean p1isRight;
        p1isRight = false; //объявляем три переменных для хранения правильности или неправильности рандомных чисел игроков
        boolean p2isRight = p1isRight; //в дальнейшим если один игрок угадал загаданное число, то меняем значение на true
        boolean p3isRight = false;

        int targetNumber = (int) (Math.random() * 10); //Генерируем рандомное число которое игроки должны угадать
        System.out.println("Я загадываю число от 0 до 9 ...");

        while(true) {
            System.out.println("Число, которое нужно угадать, - " + targetNumber);
            p1.guess(); //вызывем метод guess() из объекта Player
            p2.guess(); //пока не знаю зачем это нужно(
            p3.guess();

            guessp1 = p1.number;
            System.out.println("Первый игрок думает, что это " + guessp1); //извлекаем варианты каждого игрока(результаты работы их методов guess())
            guessp2 = p2.number;                                           // получая доступ к их переменным number
            System.out.println("Второй игрок думает, что это " + guessp2);
            guessp3 = p3.number;
            System.out.println("Третьий игрок думает, что это " + guessp3);

            if (guessp1 == targetNumber) { //Проверяем варианты каждого из игроков на соответсвие загаданному числу.
                p1isRight = true;          //Если игрок угадал, то присваиваем соответсвующей переменной значение true.
            }
            if (guessp2 == targetNumber) {
                p2isRight = true;
            }
            if (guessp3 == targetNumber) {
                p3isRight = true;
            }
            if (p1isRight || p2isRight || p3isRight) { //Если первый игрок ИЛИ второй игрок ИЛИ третий игрок угадал (опертор || означает ИЛИ)
                System.out.println("У нас есть побидитель!");
                System.out.println("Первый игрок угадал?" + p1isRight);
                System.out.println("Второй игрок угадал?" + p2isRight);
                System.out.println("Третьий игрок угадал?" + p3isRight);
                System.out.println("Конец игры.");
                break;
            } else {
                System.out.println("Игроки должны попробывать еще раз."); // Иначе остаемся в цикле и просим игроков сделать еще одну попытку
            }
        }
    }
}