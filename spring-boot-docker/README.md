# Spring boot tutorial

## Spring boot Docker Example

### Maven Folder Structure :
We have created `Dockerfile` at the root of the project.
We only need this Dockerfile text file to `dockerize` the Spring Boot application.

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
    <artifactId>spring-boot-docker</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-docker</name>
    <description>Spring boot Docker Example</description>

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
 
*We can change the status to `trace`, `debug`, `info`, `warn`,  and `fatal` to enable the internal Log4j events.*

### Configure `@SpringBootApplication` Class :
@SpringBootApplication to start everything, and also a controller to return a page.

```java
package com.ruhulmus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDocker {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDocker.class, args);
    }
}
```



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

### Configure `Log4J2AsyncLoggerException.java` Class :
Create another logger class `Log4J2AsyncLoggerException.java` to create and check exceptions.

```java
package org.apache.logging.log4j.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2AsyncLoggerException {

    private static final Logger logger = LogManager.getLogger(Log4J2AsyncLoggerException.class);

    public static void main(String[] args) {

        try {
            System.out.println(getException());
        } catch (IllegalArgumentException e) {
            logger.error("{}", e);
        }
    }

    static int getException() throws IllegalArgumentException {
        throw new IllegalArgumentException("Hello, Something Went Wrong. Exception Occured!!");
    }

}
```

To **Enable** all loggers to asynchronous, we need 2 things :

1. Need to be present `disruptor` in project class path.
2. Set system property `log4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector`

### **Springboot Run**
Now Run the below commands :

```cmd
$ cd spring-boot-docker
$ mvn clean install
```
It will build a jar `/spring-boot-docker-0.0.1-SNAPSHOT.jar` file under the `target` Location

Now run another commend to run our build jar.
```cmd
$ java -jar target/spring-boot-docker-0.0.1-SNAPSHOT.jar
```
It will run the jar file into `8089` Port (as we set the `server.port=8089` on `application.properties` file). The Output should be look like this in your terminal :
```cmd
2022-03-16 15:52:34.107  INFO 5297 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8089 (http)
2022-03-16 15:52:34.137  INFO 5297 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-03-16 15:52:34.137  INFO 5297 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2022-03-16 15:52:34.287  INFO 5297 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-03-16 15:52:34.287  INFO 5297 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1907 ms
2022-03-16 15:52:34.554  INFO 5297 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2022-03-16 15:52:34.848  INFO 5297 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8089 (http) with context path ''
2022-03-16 15:52:34.852  INFO 5297 --- [           main] com.ruhulmus.SpringBootDocker            : Started SpringBootDocker in 4.027 seconds (JVM running for 5.352)
```

So we are done with configuring `docker` with our `springboot` applications.