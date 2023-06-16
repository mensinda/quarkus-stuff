#!/bin/bash

# Clean cache and setup
mvn clean compile

# Modify the generated file
touch code/target/generated-sources/annotations/bar/MyGeneratedClass.java

echo -e '\n\n\n\n'

mvn -X compile
