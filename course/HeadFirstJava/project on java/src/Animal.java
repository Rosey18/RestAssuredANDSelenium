public abstract class Animal {
    private String name;
    public int age = 13;

    public String getName() {
        return name;
    }

    public Animal (String theName, int a) {
        name= theName;
        this.age = a;
    }

}
