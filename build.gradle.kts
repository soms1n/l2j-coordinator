plugins {
    application
    java
}

group = "ru.somsin.l2j.coordiantor"
version = "1.0-SNAPSHOT"
description = "L2J Coordinator - Rectangle Building Library"

application {
    mainClass.set("ru.somsin.l2j.coordinator.Application")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.27.4")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    test {
        useJUnitPlatform()
    }
}