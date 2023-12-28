import java.net.URI

plugins {
    kotlin("jvm") version "1.9.21"
    id("dev.clojurephant.clojure") version "0.7.0"
    idea
}

group = "nl.pindab0ter"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        name = "clojars"
        url = URI("https://repo.clojars.org")
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.7.3")
    implementation("com.github.kittinunf.fuel", "fuel", "2.3.1")
    implementation("com.github.ajalt.mordant", "mordant", "2.2.0")

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.8.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.8.1")

    testImplementation(kotlin("test"))

    // Clojure
    implementation("org.clojure", "clojure", "1.11.1")
    implementation("org.clojure", "tools.namespace", "1.3.0")
    testRuntimeOnly("org.ajoberstar", "jovial", "0.3.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

// Disabling the checkClojure task fixes Kotlin builds, while not appearing to break Clojure builds
project.gradle.startParameter.excludedTaskNames.add("checkClojure")
