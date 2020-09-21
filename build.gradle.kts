import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
}

group = "me.sander"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:2.3.3")
    implementation("com.sun.xml.ws:jaxws-ri:2.3.3")
    implementation("commons-codec:commons-codec:1.15")

    testImplementation(kotlin("test-junit5"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}