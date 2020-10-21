public class GoodDog {
    private int size;

    public int getSize() { //геттеры
        return size;
    }
    public void setSize(int s) {
        size = s;
    } //сеттер

    void bark() {
        if (size > 60) {
            System.out.println("Gav Gav");
        }
        if (size > 14) {
            System.out.println("Vuf Vuf");
        } else {
            System.out.println("Tyaf Tyaf");
        }

    }
}
