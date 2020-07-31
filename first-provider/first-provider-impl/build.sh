#!/bin/bash

version=$1

if [ $version = dev ] || [ $version = test ] || [ $version = pre ] || [ $version = prod ]
then
# 只打包
mvn clean package -Dmaven.test.skip=true -U
# 删除之前打的包
rm ./docker/*.jar
#把target文件下的包移动到docker文件下
mv ./target/first-provider-impl-1.0-SNAPSHOT.jar ./docker

echo '开始构建镜像。。。。'
docker build -t first-provider-impl ./docker
# -t 给镜像起个名字（eureka-test）
# ./docker 使用的Dockerfile文件的位置
echo '完成构建镜像。。。。'

echo '开始启动镜像。。。。'
docker run -e "JAVA_OPTS=--spring.profiles.active=${version}" -d -p 3322:3322 first-provider-impl
# docker run -d -p 13321:3321 eureka-test
# -d参数是让容器后台运行
# -p 是做端口映射，此时将服务器中的13321端口映射到容器中的3321(项目中端口配置的是3321)端口
else
	echo '输入参数不正确！'
fi
