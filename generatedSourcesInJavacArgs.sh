#!/bin/bash

cd "$(dirname "$0")"

# Clean cache and setup
mvn clean compile

# Modify the generated file
touch code/target/generated-sources/annotations/bar/MyGeneratedClass.java
touch code/src/main/java/bar/MyAnnotatedClass.java

echo -e '\n\n\n\n'

mvn -X compile
