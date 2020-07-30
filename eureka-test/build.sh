#!/bin/bash


mvn clean package -Dmaven.test.skip=true -U

rm ./docker/*.jar

mv ./target/eureka-test-1.0-SNAPSHOT.jar ./docker

echo '开始构建镜像。。。。'
docker build -t eureka-test ./docker
# -t 给镜像起个名字（eureka-test）
# ./docker 使用的Dockerfile文件的位置
echo '完成构建镜像。。。。'

echo '开始启动镜像。。。。'
docker run -d -p 3321:3321 eureka-test
# docker run -d -p 13321:3321 eureka-test
# -d参数是让容器后台运行
# -p 是做端口映射，此时将服务器中的13321端口映射到容器中的3321(项目中端口配置的是3321)端口

