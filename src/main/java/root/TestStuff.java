package root;

import root.foo.Foo;

import java.util.*;
import java.util.concurrent.*;

public class TestStuff {

    public static void main(String[] args) {
        Foo foo = new Foo();

        List<String> l1 = new ArrayList<>();
        List<String> l2 = new LinkedList<>();
        Set<String> l3 = new HashSet<>();
        Set<String> l4 = new LinkedHashSet<>();
        List<String> l5 = new Vector<>();
        Map<String, String> l6 = new HashMap<>();
        Map<String, String> l7 = new LinkedHashMap<>();
        ConcurrentMap<String, String> l8 = new ConcurrentHashMap<>();
        Map<String, String> l9 = new ConcurrentSkipListMap<>();
        Map<String, String> l10 = new Hashtable<>();

        System.out.println(foo);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l5);
        System.out.println(l6);
        System.out.println(l7);
        System.out.println(l8);
        System.out.println(l9);
        System.out.println(l10);
    }

}
