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
            // Строгость — только для "боевых" конфигураций
            val relaxed = name.startsWith("test", ignoreCase = true) ||
                    name.startsWith("spotless", ignoreCase = true) ||
                    name.contains("sonarlint", ignoreCase = true)

            if (!relaxed) {
                failOnVersionConflict()
            }

            // Spotless-пин (как у тебя было)
            if (name.startsWith("spotless", ignoreCase = true)) {
                force("com.google.guava:guava:33.4.8-jre")
            }
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


    tasks.withType<JavaExec> { //Adequate console system input
        standardInput = System.`in`
    }

    tasks.withType<ShadowJar> { // XML Spring configuration for shadowJar
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        mergeServiceFiles()
        append("META-INF/spring.handlers")
        append("META-INF/spring.schemas")
    }

    plugins.withId("java") { // Mockito warnings elimination
        tasks.withType<Test>().configureEach {
            doFirst {
                val mockitoCoreJar = classpath.files
                    .firstOrNull { it.name.startsWith("mockito-core-") && it.name.endsWith(".jar") }

                if (mockitoCoreJar != null) {
                    // Добавляем агент прямо перед запуском JVM тестов
                    jvmArgs("-javaagent:${mockitoCoreJar.absolutePath}")
                }
            }
        }
    }
}
