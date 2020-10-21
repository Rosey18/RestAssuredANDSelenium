public class exercise {
    public static void main (String[] args) {
        String[] wordLisOne = {"круглосуточный", "трех-звенный", "взаимный", "фронтэнд"};
        String[] worListTwo = {"трудный", "центральный", "ориентированный", "фирменный"};
        String[] wordListThree = {"процесс", "талант", "подход", "портал"};

        int oneLength = wordLisOne.length;
        int twoLength = worListTwo.length;
        int threeLength = wordListThree.length;

        int rand1 = (int) (Math.random()*oneLength);
        int rand2 = (int) (Math.random()*twoLength);
        int rand3 = (int) (Math.random()*threeLength);

        String phrase = wordLisOne[rand1] + " " + worListTwo[rand2] + " " + wordListThree[rand3];

        System.out.println("Все, что нужно - это " + phrase);
    }
}


