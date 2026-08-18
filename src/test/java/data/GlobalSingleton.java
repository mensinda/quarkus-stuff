package data;

public class GlobalSingleton {

    private static final GlobalSingleton INSTANCE = new GlobalSingleton();
    private int counter = 0;

    private GlobalSingleton() {
        System.err.println("CONSTRUCTOR!");
    }

    public static GlobalSingleton get() {
        return INSTANCE;
    }

    public void doStuff() {
        counter++;
        System.out.printf("counter=%d -- %s\n", counter, this);
    }

}
