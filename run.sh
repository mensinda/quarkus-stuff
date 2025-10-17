#!/bin/bash

if (( $# != 1 )); then
  echo "USAGE: $0 <jdkVersion>"
  exit 1
fi

cd "$(dirname "$0")"

pushd libTest

mvn clean install -DjdkVersion=$1

popd
pushd appTest

mvn clean verify -DjdkVersion=$1
