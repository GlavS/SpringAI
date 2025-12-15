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
}