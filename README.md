# Locking created entities

To reproduce:

```bash
./run.sh 25
```

or manually:

```bash
pushd libTest
mvn clean install
popd
pushd appTest
mvn clean verify
popd
```
