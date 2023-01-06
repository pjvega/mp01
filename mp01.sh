#!/bin/sh

./gradlew installDist

cd ./build/install/mp01/bin

./mp01 -Dserver.port=8080 com.distribuida.Servidor
./mp01 -Dserver.port=8081 com.distribuida.Servidor
./mp01 -Dserver.port=8082 com.distribuida.Servidor
./mp01 -Dserver.port=8083 com.distribuida.Servidor