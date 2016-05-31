#!/bin/sh

LIB="../lib/jade.jar"

mkdir -p classes

echo "Compiling Administrator.java ..."
javac -classpath $LIB -d classes ../src/main/java/jade/Administrator.java

if [ -z $2 ]
then
    echo "Running the Main Container with the Administrator Agent in the local machine at port 1099 ..."
    java -cp $LIB:classes jade.Boot -agents admin:jade.Administrator &
else
    echo "Running the Main Container with the Administrator Agent in ${1} at port ${2} ..."
    java -cp $LIB:classes jade.Boot -local-host $1 -local-port $2 -agents admin:jade.Administrator &
fi


