plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks {
    /*sourceSets {
        main {
            java.srcDirs("src")
        }
    }*/

    wrapper {
        gradleVersion = "7.6"
    }
}

tasks.test {
    useJUnitPlatform()
}