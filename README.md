# Demonstration of a Quarkus / JPA / Hibernate bug

This is a minimal project to reproduce a bug with Quarkus / JPA / Hibernate bug.

## The problem

### The setup:

```
@MappedSuperclass
public abstract class TupAbstractEntity {
    @Id
    private String oid;
}

@Entity
public class DummyEntity extends TupAbstractEntity {
}

public class CompositeIdClass {
    private String oid;
    private String myId;
}

@Entity
@IdClass(CompositeIdClass.class)
public class TestEntity extends TupAbstractEntity {
    @Id
    private String myId;
}
```

So there are two Entities, both extend a common `MappedSuperclass` with an `@Id` field.
One of the entities has an additional `@Id` field and uses the `@IdClass`.

This used to work in Quarkus `3.15.1` but fails since `3.15.2` (including `3.17.8`). You
can check the different quarkus versions by changing the `quarkus.platform.versio`
property in the `pom.xml`.

### The error:

```
java.lang.RuntimeException: java.lang.RuntimeException: Failed to start quarkus
Caused by: java.lang.RuntimeException: Failed to start quarkus
Caused by: jakarta.persistence.PersistenceException: [PersistenceUnit: <default>] Unable to build Hibernate SessionFactory
Caused by: java.lang.IllegalArgumentException: expecting IdClass mapping
```

## To reproduce

Just run `mvn clean verify` and observe the output.
