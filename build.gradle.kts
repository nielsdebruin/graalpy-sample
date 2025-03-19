plugins {
    id("application")
    id("java")
    alias(libs.plugins.graalvm.native)
    alias(libs.plugins.graalvm.python)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.graalvm.polyglot)
    implementation(libs.graalvm.python)
}

application {
    mainClass = "com.demo.graalpy.App"
}

graalvmNative {
    binaries.all {
        // common options
        verbose.set(true)
        buildArgs.add("-H:+CopyLanguageResources")
    }

    binaries.named("main") {
        imageName.set("demo")
        mainClass.set("com.demo.graalpy.App")
        buildArgs.add("-O3")  // enables additional compiler optimizations
    }

    binaries.named("test") {
        quickBuild.set(true)
        buildArgs.add("-O0")
    }

    // Gradle toolchain support can select a specific GraalVM installation.
    // However, this will only work properly if you have >>ONLY<< GraalVM JDKs installed.
    toolchainDetection = false
}

graalPy {
    packages = setOf( // ①
        // Any packages
    )
}