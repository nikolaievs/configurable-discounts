package ua.ecommerce.configurablediscounts

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import ua.ecommerce.configurablediscounts.env.postgres.PostgresqlContainer
import ua.ecommerce.configurablediscounts.repository.ProductRepository

@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = ["test"])
abstract class IntegrationTest {

    @Autowired protected lateinit var testRestTemplate: TestRestTemplate

    @Autowired protected lateinit var productRepository: ProductRepository

    @AfterEach
    fun cleanUpDb() {
        productRepository.deleteAll()
    }

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { PostgresqlContainer.jdbcUrl + "?currentSchema=discounts" }
        }
    }
}