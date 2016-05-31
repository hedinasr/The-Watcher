#!/bin/sh

LIB="../lib/jade.jar"
# check if Administrator Agent is running on port 1099
LSOF=$(lsof -i TCP:1099)

usage() {
    echo 'Usage : ${0} -h <host> -p <port>'
    exit
}

mkdir -p classes

echo "Compiling User.java ..."
javac -classpath $LIB -d classes ../src/main/java/jade/User.java

if [ "$#" -eq 0 ]
then
    # TODO check if the Main-Container is deployed
    echo "Deploying the user to the local machine ..."
    if [ -z $LSOF ]
    then
        echo "There is no Administrator Agent ! Exit !"
    else
        java -cp $LIB:classes jade.Boot -container -agents user:jade.User
        echo "Done !"
    fi
else
    echo "Deploying the user to the host ${1} at port ${2} ..."
    # TODO check Administrator Agent if the remote host
    java -cp $LIB:classes jade.Boot -container -host $1 -port $2 -agents user:jade.User
fi
