import org.gradle.jvm.tasks.Jar

group = "com.github.jntakpe"
version = "0.1.0"

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:")
    }
}

plugins {
    kotlin("jvm") version "1.1.51"
}

dependencies {
    compile(kotlin("stdlib-jre8", "1.1.51"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}