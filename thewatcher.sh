#!/bin/sh

mkdir -p classes

echo "Compiling User.java ..."
javac -classpath lib/jade.jar -d classes src/main/java/jade/User.java

echo "Compiling Administrator.java ..."
javac -classpath lib/jade.jar -d classes src/main/java/jade/Administrator.java
