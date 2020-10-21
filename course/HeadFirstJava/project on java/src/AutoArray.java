public class AutoArray {
    double area;
    int height;
    int length;

    public static void main(String[] args) {
        int x = 0;
        AutoArray[] arr = new AutoArray[4];
        while(x<4) {
            x = x + 1;
            arr[x] = new AutoArray();
            arr[x].height = (x + 1) * 2;
            arr[x].length = x + 4;
            arr[x].setArea();

        }
        int y = x;
        x = 27;
        AutoArray a5 = arr[2];
        arr[2].area = 343;
        System.out.print(" y = " + y);
        System.out.println(" , zona a5 = " + a5.area);
    }

    void setArea() {
        area = (height / length) * 2;
    }

}


/*class AutoArray {
    String title;
    String author;

    public static void main (String[] args) {
        int x = 0;
        AutoArray[] aa1 = new AutoArray[3];
        aa1[0] = new AutoArray();
        aa1[1] = new AutoArray();
        aa1[2] = new AutoArray();
        aa1[0].title = "JavaRush";
        aa1[1].title = "Java folosophia";
        aa1[2].title = "Java for jun";
        aa1[0].author = "Kun Ju Phie";
        aa1[1].author = "Alex Murcer";
        aa1[2].author = "Lana Suslik";

        while(x<3) {
            System.out.print(aa1[x].title);
            System.out.print(" by ");
            System.out.println(aa1[x].author);
        }





    }
}*/
