#!/bin/sh

LIB="../lib/jade.jar"

mkdir -p classes

echo "Compiling User.java ..."
javac -classpath $LIB -d classes ../src/main/java/jade/User.java

if [ -z "$2" ]
then
    # TODO check if the Main-Container is deployed
    echo "Deploying the user to the local machine at port 1099 ..."
    java -cp $LIB:classes jade.Boot -container -agents user:jade.User
else
    echo "Deploying the user to the host ${1} at port ${2} ..."
    java -cp $LIB:classes jade.Boot -container -host $1 -port $2 -agents user:jade.User
fi
