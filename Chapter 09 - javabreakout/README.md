# JavaBreakout
A simple Breakout game written in Java 21

<img src="javaBreakout.png" width="200" align=right />

## To run:

### Run Requirements

 - Java 21 (JRE)

Download [javabreakout.jar](javabreakout.jar)

    java -jar javabreakout.jar

## To build:

### Build Requirements

 - Java 21 (JDK)
 - Maven

The [pom.xml](pom.xml) file can be adjusted to build with earlier versions of Java, but it is not recommended to go below 17.

### Build command

    mvn clean install

### Running the build

    java -jar target/javabreakout-0.0.1-SNAPSHOT-jar-with-dependencies.jar
