# Demonstration of a JPA bug in either Quarkus or Hibernate

This is a minimal project to reproduce a bug with Quarkus / Hibernate.

## To reproduce

Just run `mvn clean verify` and observe the output

## This used to work with 3.3.3

This used to work with Quarkus 3.3.3. To see that this worked run the following line and run `mvn clean verify` again.

```
sed -i 's/<quarkus.platform.version>3.4.1<\/quarkus.platform.version>/<quarkus.platform.version>3.3.3<\/quarkus.platform.version>/g' pom.xml
```
