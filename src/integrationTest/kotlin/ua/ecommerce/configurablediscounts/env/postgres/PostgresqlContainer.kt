package ua.ecommerce.configurablediscounts.env.postgres

import org.testcontainers.containers.PostgreSQLContainer

object PostgresqlContainer : PostgreSQLContainer<PostgresqlContainer>("postgres:latest") {

    init {
        withInitScript("init-test.sql")
        addExposedPort(5432)
        withDatabaseName("postgres")
        start()
    }

    override fun stop() {
        // JVM handles shut down
    }
}