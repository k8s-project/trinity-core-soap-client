import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI;

plugins {
    kotlin("jvm") version "1.4.10"
    `maven-publish`
    signing
}

group = "org.kubinity"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:2.3.3")
    implementation("com.sun.xml.ws:jaxws-ri:2.3.3")
    implementation("commons-codec:commons-codec:1.15")

    testImplementation(kotlin("test-junit5"))
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn.add(tasks.javadoc)
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks {
    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

publishing {
    repositories {
        maven {
            val releaseRepo = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotRepo = uri("https://oss.sonatype.org/content/repositories/snapshots")

            credentials {
                username = property("ossrhUsername") as String
                password = property("ossrhPassword") as String
            }

            url = if (version.toString().endsWith("SNAPSHOT")) snapshotRepo else releaseRepo
        }
    }

    publications {
        create<MavenPublication>("kubinitySoapClient") {
            groupId = project.group.toString()
            artifactId = rootProject.name
            version = project.version.toString()

            pom.withXml {
                asNode().apply {
                    appendNode("description", "Kubinity SOAP Client to communicate with TrinityCore SOAP Server")
                    appendNode("name", rootProject.name)
                    appendNode("url", "https://kubinity.org")
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", "MIT")
                        appendNode("url", "https://opensource.org/licenses/mit-license.php")
                        appendNode("distribution", "repo")
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", "Archcry")
                        appendNode("name", "Sander Koenders")
                    }
                    appendNode("scm").apply {
                        appendNode("url", "https://github.com/kubinity/soap-client")
                    }
                }
            }

            artifact(sourcesJar)
            artifact(javadocJar)

            from(components["java"])
        }
    }
}
