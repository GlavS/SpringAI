import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    application
    id("com.diffplug.spotless") version "6.25.0"
    id("com.gradleup.shadow") version "8.3.9"
}

application {
    mainClass = "ru.otus.Hw1"
}

group = "ru.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:7.0.1")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.5.21")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

spotless {
    java {
        palantirJavaFormat("2.74.0")

        // Эти две опции многие команды используют всегда
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    // На всякий случай, чтобы файлы из META-INF не выкидывались
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    // Общее слияние service-файлов (META-INF/services/**)
    mergeServiceFiles()

    // Конкретно для Spring — склеить текст этих файлов
    append("META-INF/spring.handlers")
    append("META-INF/spring.schemas")
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}
