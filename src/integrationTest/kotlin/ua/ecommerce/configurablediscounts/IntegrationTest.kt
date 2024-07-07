package ua.ecommerce.configurablediscounts

import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import ua.ecommerce.configurablediscounts.env.postgres.PostgresqlContainer

@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = ["test"])
interface IntegrationTest {

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { PostgresqlContainer.jdbcUrl + "?currentSchema=discounts" }
        }
    }
}