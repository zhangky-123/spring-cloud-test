#!/bin/bash

version=$1

if [ $version = dev ] || [ $version = test ] || [ $version = pre ] || [ $version = prod ]
then
    mvn clean package -Dmaven.test.skip=true -U

    rm ./docker/*.jar

    mv ./target/first-consumer-1.0-SNAPSHOT.jar ./docker

    echo '开始构建镜像。。。。'
    docker build -t first-consumer ./docker
    echo '完成构建镜像。。。。'

    echo '开始启动镜像。。。。'
    docker run -e "JAVA_OPTS=--spring.profiles.active=${version}" -e "EUREKA_HOST=--EUREKA_HOST=eureka-test" -d -p 3323:3323 first-consumer
else
	echo '输入参数不正确！'
fi