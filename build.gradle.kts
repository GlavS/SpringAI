import com.diffplug.gradle.spotless.SpotlessExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.diffplug.spotless") version "6.25.0" apply false
    id("com.gradleup.shadow") version "8.3.9" apply false
    id("name.remal.sonarlint") version "7.0.0" apply false
}




group = "ru.otus"
version = "1.0-SNAPSHOT"


subprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "name.remal.sonarlint")

    extensions.configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    }

    configurations.configureEach {
        resolutionStrategy {
            failOnVersionConflict()
            if (name.startsWith("spotless")) {
                force("com.google.guava:guava:33.4.8-jre")
            }                                                                              // for palantir-java-format
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    extensions.configure<SpotlessExtension> {
        java {
            palantirJavaFormat("2.74.0")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }


    tasks.withType<JavaExec> {
        standardInput = System.`in`
    }

    tasks.withType<ShadowJar> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        mergeServiceFiles()
        append("META-INF/spring.handlers")
        append("META-INF/spring.schemas")
    }
}
