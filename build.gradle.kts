import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    application
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
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
    api("org.springframework.boot:spring-boot-starter-web") // Declared as api for testing suites
    api("org.springframework.boot:spring-boot-starter-data-jpa") // Declared as api for testing suites
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

testing {
    suites {
        withType<JvmTestSuite> {
            dependencies {
                implementation(project())
                implementation("org.jetbrains.kotlin:kotlin-test-junit5")
                implementation("org.testcontainers:junit-jupiter")
                implementation("org.junit.platform:junit-platform-launcher")
                implementation("org.assertj:assertj-core:3.26.0")
            }
            useJUnitJupiter()
        }

        val test by getting(JvmTestSuite::class) {
            dependencies {
                implementation("io.mockk:mockk:1.13.11")

            }
        }

        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(test.sources.output)
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