public class GoodDogTestDrive {
    public static void main(String[] args) {
        GoodDog one = new GoodDog();
        one.setSize(70);
        GoodDog two = new GoodDog();
        two.setSize(8);
        //System.out.println("first dog - " + one.setSize());
        //System.out.println("second dog - " + two.setSize());
        one.bark();
        two.bark();

    }
}
