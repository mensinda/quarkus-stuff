package bar;

public class MyMain {
    public static void main(String... args) {
        int num = MyLib.oracle();
        System.out.println("The oracle says: " + num);
    }
}
