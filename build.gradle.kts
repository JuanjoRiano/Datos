plugins {
    kotlin("jvm") version "1.8.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // CSV reader (JVM platform)
    implementation("com.jsoizo:kotlin-csv-jvm:1.10.0")
    // Smile ML core (incluye smile.regression.OLS)
    implementation("com.github.haifengl:smile-core:2.6.0")
    // Opcional: logging
    implementation("io.github.microutils:kotlin-logging:3.0.4")
    implementation("org.slf4j:slf4j-simple:2.0.7")

    // Framework de tests
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("cli.MainKt")
}

kotlin {
    jvmToolchain(11)
}
