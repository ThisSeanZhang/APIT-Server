# APIT-Server(还在开发中)

> 此项目是“[调试API的过程中就可以同步的更新文档](https://github.com/ThisSeanZhang/APIT-Client)”项目的服务端

### 这里下载

* [Releases](https://github.com/ThisSeanZhang/APIT-Server/releases)

### 操作方式
#### 设置服务器

> 先准备Java的运行环境，我是使用[OpenJdk11.0.2](https://jdk.java.net/11/)进行开发

> 配置相应的配置文件(application-prod.yml)和打包好的jar放在[Releases](https://github.com/ThisSeanZhang/APIT-Server/releases)下载的压缩包里

> 目前是直接贴了Spring Boot的配置,后面会改进下的
```
server:
  # 服务器端口
  port: 80
spring:
  datasource:
    # 数据库地址
    url: jdbc:mysql://apit_mysql_prod:3306/apit_server?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&serverTimezone=GMT&jdbcCompliantTruncation=false
    # 用户名
    username: root
    # 密码
    password: root
```
#### 然后运行
```$xslt
$ java -jar -Dspring.profiles.active=prod ./APIT-Server-v0.0.2-beta.jar
```
> 或者使用Docker

```dockerfile
#基础镜像使用 openjdk:11.0.2-jdk
FROM openjdk:11.0.2-jdk

#作者
MAINTAINER ThisSeanZhang <thisseanzhang@gmail.com>

#将运行文件拷贝至root
ENV RUN_DIR /root
WORKDIR /root

#定义初始化sql文件
ENV SERVER_JAR APIT-Server-0.0.2-beta.jar
ENV SERVER_CONF application-prod.yml

#拷贝文件
COPY ./$SERVER_JAR $RUN_DIR/
COPY ./$SERVER_CONF $RUN_DIR/

EXPOSE 80

#运行
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "./APIT-Server-0.0.2-beta.jar"]
```
#### 完(后续再添加吧)

### 技术
* [Spring Boot](https://github.com/SimulatedGREG/electron-vue)
