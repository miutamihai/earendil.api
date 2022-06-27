#!/bin/sh

./gradlew build

mkdir -p target

cp ./build/libs/*.jar ./target
rm ./target/*-plain.jar
find ./target -name '*jar' -exec mv {}  ./target/application.jar  \;