#!/bin/sh

./gradlew build

cp ./build/libs/*.jar ./target
rm ./target/*-plain.jar
find ./target -name '*jar' -exec mv {}  ./target/application.jar  \;