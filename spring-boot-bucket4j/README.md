# Spring boot tutorial

## Spring boot API Rate limiting is a strategy using Bucket4j

### Project Folder Structure :
At first, We have created Maven based Spring boot application. Folder structure is below :

![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-bucket4j/_screenshoot/folder-structure.png)

### Maven `pom.xml` :
Project dependencies. Nothing special here, just some Spring Boot dependencies.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.ruhulmus</groupId>
    <artifactId>spring-boot-bucket4j</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-bucket4j</name>
    <description>Spring boot Bucket4j Example</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>


    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

Add bucker4j + cache Dependancy into pom.xml

```xml
    <dependency>
        <groupId>com.giffing.bucket4j.spring.boot.starter</groupId>
        <artifactId>bucket4j-spring-boot-starter</artifactId>
        <version>0.2.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    <dependency>
        <groupId>org.ehcache</groupId>
        <artifactId>ehcache</artifactId>
    </dependency>
```

 ### `application.properties` file : 
Define application server port `8089`

 ```yml
server.port=8089
 ```
Now Configure bucket4j 
 ```yml
# enable/disable bucket4j support
  bucket4j.enabled=true
  # the name of the cache key
  bucket4j.filters[0].cache-name=buckets
# the json response which should be added to the body
bucket4j.filters[0].http-response-body={ "message": "Too many requests" }
# a regular expression
  bucket4j.filters[0].url=.*
  bucket4j.filters[0].metrics.enabled=true
  #rate limit
  bucket4j.filters[0].rate-limits[0].bandwidths[0].capacity=2
  #rate per seconds
  bucket4j.filters[0].rate-limits[0].bandwidths[0].time=1
  bucket4j.filters[0].rate-limits[0].bandwidths[0].unit=seconds
 ```
Here 
capacity=2 , time=1 and unit=seconds that means maximum 2 api call in 1 second duration

create a file name `ehcache.xml` under `resource` directory and configure accordingly.
Add bucket file location into `application.properties`
```yml
spring.cache.jcache.config=classpath:ehcache.xml
```

`ehcache.xml` File look like :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:jsr107="http://www.ehcache.org/v3/jsr107"
        xmlns='http://www.ehcache.org/v3'
        xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core-3.0.xsd
							http://www.ehcache.org/v3/jsr107 http://www.ehcache.org/schema/ehcache-107-ext-3.0.xsd">
    <cache alias="buckets">
        <expiry>
            <tti unit="seconds">3600</tti>
        </expiry>
        <heap unit="entries">1000000</heap>
        <jsr107:mbeans enable-statistics="true"/>
    </cache>
</config>
```

### Configure `@SpringBootApplication` Class :
`@SpringBootApplication` to start everything.

```java
package com.ruhulmus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCaching
@SpringBootApplication
public class SpringBootBucket4j {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDocker.class, args);
    }
}
```

Create a test controller `HelloController` and a api endpoint for `/testuri` 

```java
package com.ruhulmus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HelloController {
    @RequestMapping({"/testuri"})
    public String hello() {
        return "Hello World !! Rest Controller is working ...";
    }
}
```

### **Springboot Run**
Now Run the below commands :

```cmd
$ cd spring-boot-bucket4j
$ mvn clean install
```
It will build a jar `spring-boot-bucket4j-0.0.1-SNAPSHOT.jar` file under the `target` Location

Now run another command to run our jar file.
```cmd
$ java -jar target/spring-boot-bucket4j-0.0.1-SNAPSHOT.jar
```
It will run the `jar` file into `8089` Port (as we set the `server.port=8089` on `application.properties` file). The Output should be look like in your terminal :
```cmd
2021-04-15 16:32:34.107  INFO 5297 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8089 (http)
2021-04-15 16:32:34.137  INFO 5297 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-04-15 16:32:34.137  INFO 5297 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2021-04-15 16:32:34.287  INFO 5297 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-04-15 16:32:34.287  INFO 5297 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1907 ms
2021-04-15 16:32:34.554  INFO 5297 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-04-15 16:32:34.848  INFO 5297 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8089 (http) with context path ''
2021-04-15 16:32:34.852  INFO 5297 --- [           main] com.ruhulmus.SpringBootBucket4j            : Started SpringBootDocker in 4.027 seconds (JVM running for 5.352)
```

Now we can try to check our rest api. Here we used `postman` as a rest client. You can use your preferred one.

![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-bucket4j/_screenshoot/api-response.png)

**So that means our spring boot application, rest api is working fine with `8089` port.**

## Docker Configuration :
We have created `Dockerfile` at the root of the project.
We only need this Dockerfile text file to `dockerize` the Spring Boot application.

![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-bucket4j/_screenshoot/Dockerfile.png)

### Configure `Dockerfile` file :
A Dockerfile is a text file, contains all the commands to assemble the docker image. let's start configure the docker file
 
we can find more base images from the official [Docker Hub](https://hub.docker.com/u/adoptopenjdk)
Here I use `openjdk8` as base image.

It creates a docker image base on `openjdk:8-jdk-alpine`, an [alpine linux](https://alpinelinux.org/) with `openjdk8` installed.

```cmd
# For Java 8, try this
$ FROM openjdk:8-jdk-alpine
```
Update the working directory:
```cmd
# cd /opt/dockerapp
$ WORKDIR /opt/dockerapp
```
Update the working directory:
```cmd
# cd /opt/dockerapp
$ WORKDIR /opt/dockerapp
```
Declare a variable `JAR_FILE` and add assigned value `target/spring-boot-docker-0.0.1-SNAPSHOT.jar` :
```cmd
$ ARG JAR_FILE=target/spring-boot-docker-0.0.1-SNAPSHOT.jar
```
Copy `target/spring-boot-docker-0.0.1-SNAPSHOT.jar` to `/opt/dockerapp/dockerapp.jar` (renamed the `JAR` file name into Working directory)
```cmd
# cp target/spring-boot-docker-0.0.1-SNAPSHOT.jar /opt/dockerapp/dockerapp.jar
$ COPY ${JAR_FILE} dockerapp.jar
```
Run the jar file with `ENTRYPOINT` like `# java -jar /opt/dockerapp/dockerapp.jar` .

```cmd
$ ENTRYPOINT ["java","-jar","dockerapp.jar"]
```

### A complete `Dockerfile` example.

```cmd
# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

# For Java 8, try this
$ FROM openjdk:8-jdk-alpine

# Refer to Maven build -> finalName
$ ARG JAR_FILE=target/spring-boot-docker-0.0.1-SNAPSHOT.jar

# cd /opt/app
$ WORKDIR /opt/app

# cp target/spring-boot-docker-0.0.1-SNAPSHOT.jar /opt/app/dockerapp.jar
$ COPY ${JAR_FILE} dockerapp.jar

# java -jar /opt/app/dockerapp.jar
$ ENTRYPOINT ["java","-jar","dockerapp.jar"]
```

Complete Dockerfile Screenshot: 

![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-docker/_screenshoot/complete-dockerfile.png)

### Docker Build, Run :

For Docker image Build we need to use below command.
```cmd
#sudo docker build -t name:tag .
$ sudo docker build -t spring-boot-docker:1.0 .
```
Here `spring-boot-docker` is name and `1.0` is a tag name.

Output should be look like : 
```cmd
$ docker build -t spring-boot-docker:1.0 .
[+] Building 2.4s (8/8) FINISHED                                                                                                                              
 => [internal] load build definition from Dockerfile                                                                                                     0.0s
 => => transferring dockerfile: 37B                                                                                                                      0.0s
 => [internal] load .dockerignore                                                                                                                        0.0s
 => => transferring context: 2B                                                                                                                          0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                  1.4s
 => [1/3] FROM docker.io/library/openjdk:8-jdk-alpine@sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3                            0.0s
 => [internal] load build context                                                                                                                        0.6s
 => => transferring context: 17.57MB                                                                                                                     0.6s
 => CACHED [2/3] WORKDIR /opt/dockerapp                                                                                                                  0.0s
 => [3/3] COPY target/spring-boot-docker-0.0.1-SNAPSHOT.jar dockerapp.jar                                                                                0.1s
 => exporting to image                                                                                                                                   0.1s
 => => exporting layers                                                                                                                                  0.1s
 => => writing image sha256:410f40671c59364ebc49de60b01ad4e452a1b36221cf945fcaed47fef6da8c0d                                                             0.0s
 => => naming to docker.io/library/spring-boot-docker:1.0
```
Run the container in the background. use below command.

```cmd
$ sudo docker run -d -p 9000:8089 -t spring-boot-docker:1.0
```
Now container run in `9000` port and it's mapping with application port `8089`.
Here `run -d` to start the container in detach mode.
Here `run -p` to map ports between docker container and application.

![view](https://github.com/ruhulmus/spring-boot-tutorial/blob/main/spring-boot-docker/_screenshoot/api-response-docker.png)

**So we are done with configuring `docker` with our `springboot` application.**

### Few Basic Command for Docker :

To see List of all containers:
```cmd
$ sudo docker ps
```
output :
```cmd
$ sudo docker ps
CONTAINER ID   IMAGE                    COMMAND                  CREATED         STATUS         PORTS                    NAMES
96c5adab1759   spring-boot-docker:1.0   "java -jar dockerapp…"   8 minutes ago   Up 8 minutes   0.0.0.0:9000->8089/tcp   cranky_kapitsa
```
Stop a container by container id :
```cmd
$ sudo docker stop 96c5adab1759
```
