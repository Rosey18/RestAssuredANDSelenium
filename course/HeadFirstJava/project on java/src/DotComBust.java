/*import java.util.*;

public class DotComBust {

    private GameHelper helper = new GameHelper(); //объявляем и инициализируем переменные, которые нам понадобятся
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>(); //объявляем и инициализируем переменные, которые нам понадобятся
    private int numOfGuesses = 0; //ход пользователя, пока равен 0

    private void setUpGame() { //метод для подготовки игры
        DotCom one = new DotCom(); //создаем три объекта, даем им имена и помещаем в ArrayList
        one.setName("pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("go2.com");
        dotComsList.add(one); //помещаем в ArrayList
        dotComsList.add(two); //помещаем в ArrayList
        dotComsList.add(three); //помещаем в ArrayList

        System.out.println("Your mission destroy 3 "sites"."); //выводим краткие инструкции для пользователя
        System.out.println("Pets.com, eToys.com, Go2.com"); //выводим краткие инструкции для пользователя
        System.out.println("Try to destroy our sites in minimum steps"); //выводим краткие инструкции для пользователя

        for (DotCom dotComToSet : dotComsList) { //повтрояем с каждым объектом DotCom в списке //DotCom - это переменная, а dotComList - имя той переменной, и по этому имени будм его вызывать
            ArrayList<String> newLocation = helper.placeDotCom(3); //запрашиваем у вспомогательного объекта адрес "сайта"
            dotComToSet.setLocationCells(newLocation); //вызываем сеттер из объекта DotCom чтобы передать ему местоположение, который только что получил от вспомогательного объекта
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) { //до тех пор, пока список объектов DotCom не станет пустым
            String userGuess = helper.getUserInput("Enter number"); //получаем пользовательский ввод
            checkUserGuess(userGuess); //вызываем метод checkYourSelf
        }
        finishGame(); //вызываем метод finishGame
    }

    private void checkUserGuess (String userGuess){
        numOfGuesses++; //инкерментируем количество попыток, которые сделал пользователь
        String result = "Miss"; //подразумеваем промах

        for (DotCom dotComToTest : dotComsList) { //повторяем это для всех объектов DotCom в списке //DotCom - это переменная, а dotComList - имя той переменной, и по этому имени будм его вызывать
            result = dotComToTest.checkYourSelf(userGuess); //просим DotCom проверить ход пользователя, ищем попадания или потопления
            if (result.equals("Yep")) {
                break; //выбираемся из цикла раньше времени, нет смысла проверять другие "сайты"
            }
            if (result.equals("Destroyed")) {
                dotComsList.remove(result); //ему пришел конец так что удаляем из списка "сайтов" и выходим из цикла
                break;
            }
        }
        System.out.println(result); //выводим польщователю результат
    }

    private void finishGame() {
        System.out.println("All "sites" destroyed! Your stocks are worthless"); //выводим сообщение о том как пользователь прошел игру
        if (numOfGuesses <= 18) {
            System.out.println("it took you only " + numOfGuesses + " attempts"); //выводим сообщение о том как пользователь прошел игру
            System.out.println("u managed to get out before your investments destroyed."); //выводим сообщение о том как пользователь прошел игру
        } else {
            System.out.println("it took you quite while " + numOfGuesses + " attempts"); //выводим сообщение о том как пользователь прошел игру
            System.out.println("fish dance around ur enclosures"); //выводим сообщение о том как пользователь прошел игру
        }
    }

    public static void main (String[] args) {
        DotComBust game = new DotComBust(); //создаем игровой объект
        game.setUpGame(); //говорим игровому объекту подготовить игру
        game.startPlaying(); //говорим игровому объекту начать главный игровой цикл (продолжаем запрашивать пользовательский ввод и проверять полученные данные
    }
}
*/