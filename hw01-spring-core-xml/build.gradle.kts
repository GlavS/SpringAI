plugins {
    application
}

application {
    mainClass = "ru.otus.Hw1"
}

dependencies {
    implementation("org.springframework:spring-context:7.0.1")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.5.21")
    implementation("com.opencsv:opencsv:5.12.0")
}

sonarLint {
    ignoredPaths.add("src/test/java/Runner.java")
}
