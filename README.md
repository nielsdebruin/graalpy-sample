# GraalPy Sample Code

Simple project demonstrating Java-Python interoperability using GraalPy within the GraalVM ecosystem. The project includes example code showing how to execute Python code from Java, pass data between Java and Python, and use Python libraries from Java.

Build configuration is provided for both Gradle and Maven.

## Building Native Image

Native images are standalone executables that include your application, the JVM, and all necessary dependencies pre-compiled into native code.

### Using Gradle

```
./gradlew nativeCompile
```

The generated native image will be located at:
```
build/native/nativeCompile/demo
```

### Using Maven

```
mvn package -Pnative
```

The generated native image will be located at:
```
target/demo
```

## Running the Native Image

After building the native image, you can run it directly:

### Gradle-built native image:
```
./build/native/nativeCompile/demo [command]
```

### Maven-built native image:
```
./target/demo [command]
```
