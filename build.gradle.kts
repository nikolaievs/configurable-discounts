plugins {
    application
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    `jvm-test-suite`
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "ua.ecommerce"
version = "0.0.1-SNAPSHOT"

application {
    mainClass.set("ua.ecommerce.configurablediscounts.ApplicationKt")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

fun JvmComponentDependencies.commonTestDependencies() {
    implementation(project())
    implementation("org.jetbrains.kotlin:kotlin-test-junit5")
    implementation("org.testcontainers:junit-jupiter")
    implementation("org.junit.platform:junit-platform-launcher")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            dependencies {
                commonTestDependencies()
            }
            useJUnitJupiter()
        }

        register<JvmTestSuite>("integrationTest") {
            dependencies {
                commonTestDependencies()
                implementation("org.testcontainers:postgresql")
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("org.springframework.boot:spring-boot-testcontainers")
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}