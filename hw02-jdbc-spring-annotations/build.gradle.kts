plugins {
    application
}

application {
    mainClass = "ru.otus.Hw2"
}

dependencies {
    // Spring BOM (теперь живёт в модуле)
    implementation(platform("org.springframework:spring-framework-bom:7.0.1"))
    // Тестовый стек (теперь живёт в модуле)
    testImplementation(platform("org.junit:junit-bom:5.13.4"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-tx")
    implementation("ch.qos.logback:logback-classic:1.5.21")

    implementation("org.flywaydb:flyway-core:11.19.0")
    implementation("org.flywaydb:flyway-database-postgresql:11.19.0")
    implementation("org.postgresql:postgresql:42.7.8")


    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    testImplementation("org.assertj:assertj-core:3.27.6")
}
