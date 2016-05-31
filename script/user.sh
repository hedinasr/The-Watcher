#!/bin/sh

LIB="../lib/jade.jar"
# check if Administrator Agent is running on port 1099
LSOF=$(lsof -i TCP:1099)

usage() {
    echo 'Usage : ./user.sh <ip>'
    exit
}

# Test an IP address for validity:
# Usage:
#      valid_ip IP_ADDRESS
#      if [[ $? -eq 0 ]]; then echo good; else echo bad; fi
#   OR
#      if valid_ip IP_ADDRESS; then echo good; else echo bad; fi
#
function valid_ip() {
    local  ip=$1
    local  stat=1

    if [[ $ip =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
        OIFS=$IFS
        IFS='.'
        ip=($ip)
        IFS=$OIFS
        [[ ${ip[0]} -le 255 && ${ip[1]} -le 255 \
                 && ${ip[2]} -le 255 && ${ip[3]} -le 255 ]]
        stat=$?
    fi
    return $stat
}

mkdir -p classes


if [ "$#" -eq 0 ]
then
    # TODO check if the Main-Container is deployed
    echo "Deploying the user to the local machine ..."
    if [ -z $LSOF ]
    then
        echo "There is no Administrator Agent ! Exit !"
    else
        echo "Compiling User.java ..."
        javac -classpath $LIB -d classes ../src/main/java/jade/User.java
        java -cp $LIB:classes jade.Boot -container -agents user:jade.User
        echo "Done !"
    fi
else
    if [ "$#" -eq 1 ]
    then
        if valid_ip $1
        then
            echo "Deploying the user to the host ${1} ..."
            echo "Compiling User.java ..."
            javac -classpath $LIB -d classes ../src/main/java/jade/User.java
            # TODO check Administrator Agent if the remote host
            java -cp $LIB:classes jade.Boot -container -host $1 -agents user:jade.User
        else
            echo "Bad IP address"
        fi
    else
        usage
    fi
fi
