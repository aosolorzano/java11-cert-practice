# java11-cert-practice

# Compiling and running a modular program for Chapter 11 Tests
If you are using Maven, you need to clean the project and create the output modules directory:
```
mvn clean
mkdir mods
```

## Compiling DAO Module
We use the target/classes en each module to store compiled classes. This because we need to follow the standards for tools like Maven or Gradle.
Compiling in the same order that show below:
```
javac -d chapter11/dao/target/classes chapter11/dao/src/main/java/com/hiperium/java/cert/prep/chapter/_11/dao/*.java \
    chapter11/dao/src/main/java/module-info.java
```

## Packaging Module
We can package the compiled project modules in a JAR file:
```
jar -cvf mods/java11-cert-practice-chapter11-dao.jar -C chapter11/dao/target/classes .
```

## Compiling and packaging the rest of the modules (BO, API and CHAPTERS)
Now we need to apply the same set of commands for the rest of the modules. Also, we must include the --module-path (or
-p) parameter to reference the compiled and packaged ones.

### Module BO:
```
javac -p mods -d chapter11/bo/target/classes chapter11/bo/src/main/java/com/hiperium/java/cert/prep/chapter/_11/bo/*.java \
    chapter11/bo/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapter11-bo.jar -C chapter11/bo/target/classes .
```

### Module API:
```
javac -p mods -d chapter11/api/target/classes chapter11/api/src/main/java/com/hiperium/java/cert/prep/chapter/_11/api/*.java \
    chapter11/api/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapter11-api.jar -C chapter11/api/target/classes .
```

### Module CHAPTERS:
```
javac -p mods -d chapters/target/classes chapters/src/main/java/com/hiperium/java/cert/prep/chapter/**/*.java \
    chapters/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapters.jar -C chapters/target/classes .
```

## Running Test module with JAR utility
Now we can run the test class using the generated CHAPTERS JAR:
```
java -p mods -m com.hiperium.java.cert.prep.chapters/com.hiperium.java.cert.prep.chapter._11.Actors
```

