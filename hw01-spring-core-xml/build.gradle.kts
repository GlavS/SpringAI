plugins {
    application
}

application {
    mainClass = "ru.otus.Hw1"
}

dependencies {
    implementation("org.springframework:spring-context:7.0.1")
    implementation("ch.qos.logback:logback-classic:1.5.21")
    implementation("com.opencsv:opencsv:5.12.0") {
        exclude(group = "org.apache.commons", module = "commons-lang3")
    }
    implementation("org.apache.commons:commons-lang3:3.18.0")
}

sonarLint {
    ignoredPaths.add("src/test/java/Runner.java")
}
