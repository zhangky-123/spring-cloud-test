#!/bin/bash


mvn clean package -Dmaven.test.skip=true -U

rm ./docker/*.jar

mv ./target/config-server-1.0-SNAPSHOT.jar ./docker

echo '开始构建镜像。。。。'
docker build -t config-server ./docker
echo '完成构建镜像。。。。'

echo '开始启动镜像。。。。'
docker run -d -p 3324:3324 config-server


