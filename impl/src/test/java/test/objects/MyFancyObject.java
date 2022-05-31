package test.objects;

import foo.bar.api.AbstractFancyObj;

public class MyFancyObject extends AbstractFancyObj {
    @Override
    public void doStuff() {
        System.out.println("Hello World!");
    }
}
