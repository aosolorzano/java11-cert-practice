# java11-cert-practice

# Compiling and Running a Modular Program
If we used Maven before, we need to clean teh project:
```
mvn clean
```
## Compiling Module
We use the target/classes directory inside chapters module to store compiled classes. This because we need to follow the standards for tools like Maven or Gradle to generate directories: 
```
javac -d chapters/target/classes chapters/src/main/java/com/hiperium/java/cert/prep/chapter/*/*.java chapters/src/main/java/module-info.java
```
## Running Module
We can run the Task class inside the module to verify that the compilation was successful:
```
java --module-path chapters/target/classes --module java.cert.practice.chapters/com.hiperium.java.cert.prep.chapter._11.Task
```
## Packaging Module
We can package the compiled project modules in a JAR file:
```
jar -cvf mods/java.cert.practice.chapters.jar -C chapters/target/classes .
```
## Running Module on JAR file
Now we can run the Task class again but this time using the generated JAR file:
```
java -p mods -m java.cert.practice.chapters/com.hiperium.java.cert.prep.chapter._11.Task
```