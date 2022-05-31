# Demonstration of a Quarkus class loader bug with package private

This is a minimal project to reproduce a bug with Quarkus.

## To reproduce

```bash
mvn clean verify
mvn clean install
cd stub
mvn clean verify
```
