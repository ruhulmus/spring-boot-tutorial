# Spring boot tutorial

## Spring boot Docker Example

### Maven Folder Structure :
We have created `Dockerfile` at the root of the project.
We only need this Dockerfile text file to dockerize the Spring Boot application.


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

```javapackage com.ruhulmus;

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

### **Asynchronous Loggers**
Now Run the below commands :

It will build a jar `log4j2-1.0.0.jar` file under the `target` Location
```cmd
$ mvn clean package
```


Run another commend with Log4j 2 configuration in `debug` status.
```cmd
$ java -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -jar target/log4j2-1.0.0.jar
```
The Output should be l0ok like this in your terminal :
```cmd
2022-02-21 16:55:31,637 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09
2022-02-21 16:55:32,638 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=AsyncLoggerRingBuffer
2022-02-21 16:55:32,638 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=StatusLogger
2022-02-21 16:55:32,638 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=ContextSelector
2022-02-21 16:55:32,638 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=Loggers,name=
2022-02-21 16:55:32,639 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=Appenders,name=LogToConsole
2022-02-21 16:55:32,640 main DEBUG Registering MBean org.apache.logging.log4j2:type=AsyncContext@464bee09,component=Appenders,name=LogToRollingFile
//...
2022-02-21 16:55:33,020 pool-1-thread-1 DEBUG Stopped LoggerContext[name=AsyncContext@464bee09, org.apache.logging.log4j.core.async.AsyncLoggerContext@45fd9a4d] with status true 
```

So we are done with configuring `asynchronous` logging in Log4j 2 using the `Log4jContextSelector` system property.
You can optimize the performance of your Java by using The option for asynchronous in Log4J 2 tools.

