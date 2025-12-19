plugins {
    application
}

application {
    mainClass = "ru.otus.Hw1"
}

dependencies {
    // Spring BOM (теперь живёт в модуле)
    implementation(platform("org.springframework:spring-framework-bom:7.0.1"))
    implementation("org.springframework:spring-context")

    implementation("ch.qos.logback:logback-classic:1.5.21")

    implementation("com.opencsv:opencsv:5.12.0") {
        exclude(group = "org.apache.commons", module = "commons-lang3")
    }
    implementation("org.apache.commons:commons-lang3:3.18.0")

    // Тестовый стек (теперь живёт в модуле)
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    testImplementation("org.assertj:assertj-core:3.27.6")
}

sonarLint {
    ignoredPaths.add("src/test/java/Runner.java")
}
