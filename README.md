# Style is ignored for RemoveUnusedImports and OrderImports

This repository demonstrates that styles are ignored when using the
maven plugin `<configLocation>`.

## To reproduce

Run the following command and observe the result of the [TestStuff][TestStuff.java] class:

```bash
mvn -Poptimize-imports validate
```

## Expected result

```java
package root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import root.foo.Foo;

public class TestStuff {

    public static void main(String[] args) {
        // Content main
    }

}
```

## Actual result

```java
package root;

import root.foo.Foo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class TestStuff {

    public static void main(String[] args) {
        // Content main
    }

}
```

[TestStuff.java]: src/main/java/root/TestStuff.java
