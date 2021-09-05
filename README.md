# java11-cert-practice

# Compiling and running a modular program

If we used Maven before, we need to clean the project:

```
mvn clean
```

## Compiling Modules

We use the target/classes directory inside chapters module to store compiled classes. This because we need to follow the
standards for tools like Maven or Gradle to generate directories:

```
javac -d chapters/target/classes chapters/src/main/java/com/hiperium/java/cert/prep/chapter/*/*.java chapters/src/main/java/module-info.java
```

## Running Module

We can run the compiled Task class to verify that the last step was successful:

```
java --module-path chapters/target/classes --module com.hiperium.java.cert.prep.chapter/com.hiperium.java.cert.prep.chapter._11.Actors
```

## Packaging Module

We can package the compiled project modules in a JAR file:

```
jar -cvf mods/java11-cert-practice-chapters.jar -C chapters/target/classes .
```

## Running Module on JAR file

Now we can run some classes but this time using the generated JAR file:

```
java -p mods -m com.hiperium.java.cert.prep.chapter/com.hiperium.java.cert.prep.chapter._11.Actors
```

## Compiling and packaging the rest of modules

Now we need to apply the same set of commands for the rest of the modules. Also, we must include the --module-path (or
-p) parameter.

### Module 1:

```
javac -p mods -d chapter11/module1/target/classes chapter11/module1/src/main/java/com/hiperium/java/cert/prep/care/*/*.java chapter11/module1/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapter11-module1.jar -C chapter11/module1/target/classes .
```

### Module 2:

```
javac -p mods -d chapter11/module2/target/classes chapter11/module2/src/main/java/com/hiperium/java/cert/prep/talks/*/*.java chapter11/module2/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapter11-module2.jar -C chapter11/module2/target/classes .
```

### Module 3:

```
javac -p mods -d chapter11/module3/target/classes chapter11/module3/src/main/java/com/hiperium/java/cert/prep/staff/*.java chapter11/module3/src/main/java/module-info.java
jar -cvf mods/java11-cert-practice-chapter11-module3.jar -C chapter11/module3/target/classes .
```