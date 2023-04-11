#!/bin/bash

cd "$(dirname "$0")"

# jakarta --> javax

find ./src -name '*.java' -exec sed -i 's/jakarta/javax/g' {} \;

sed -i 's/<quarkus.platform.version>.*<\/quarkus.platform.version>/<quarkus.platform.version>2.16.6.Final<\/quarkus.platform.version>/g' pom.xml
