plugins {
    kotlin("jvm") version "1.9.21"
    idea
}

group = "nl.pindab0ter"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.7.3")
    implementation("com.github.kittinunf.fuel", "fuel", "2.3.1")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
