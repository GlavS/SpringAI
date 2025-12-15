plugins {
    application
}

application {
    mainClass = "ru.otus.Hw2"
}

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-tx")
    implementation("ch.qos.logback:logback-classic:1.5.21")

    implementation("org.flywaydb:flyway-core:11.19.0") // версию можно твою
    implementation("org.flywaydb:flyway-database-postgresql:11.19.0")
    implementation("org.postgresql:postgresql:42.7.8")
}