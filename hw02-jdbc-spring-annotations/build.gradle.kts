plugins {
    application
}

application {
    mainClass = "ru.otus.Hw2"
}

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:7.0.1"))
    testImplementation(platform("org.junit:junit-bom:6.0.1"))
    testImplementation(platform("org.testcontainers:testcontainers-bom:2.0.2"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-tx")

    implementation("ch.qos.logback:logback-classic:1.5.21")
    implementation("org.flywaydb:flyway-core:11.19.0")
    implementation("org.flywaydb:flyway-database-postgresql:11.19.0")
    implementation("org.postgresql:postgresql:42.7.8")

    testImplementation("org.springframework:spring-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    testImplementation("org.assertj:assertj-core:3.27.6")

    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.21.4")

    sonarLint {
        ignoredPaths.add("src/test/**")
    }
}
