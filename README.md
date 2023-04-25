# Quarkus 3.0 / Hibernate 6 dirty entities not written to DB

To reproduce:

```bash
mvn clean verify
```

To go back to Quarkus 2.x:

```bash
./downgrade.sh
mvn clean verify # This still won't work!
```
