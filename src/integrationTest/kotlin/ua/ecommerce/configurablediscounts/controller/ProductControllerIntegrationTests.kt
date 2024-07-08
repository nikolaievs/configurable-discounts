package ua.ecommerce.configurablediscounts.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import ua.ecommerce.configurablediscounts.IntegrationTest
import ua.ecommerce.configurablediscounts.controller.model.ErrorResponse
import ua.ecommerce.configurablediscounts.controller.model.GetProductResponse
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.math.BigDecimal
import java.util.UUID

class ProductControllerIntegrationTests : IntegrationTest() {

    @Test
    fun `should return product by id`() {
        // given
        val product = buildProduct(name = "integration test product", price = BigDecimal.TEN)
        productRepository.save(product)

        // when
        val response = testRestTemplate.getForEntity<GetProductResponse>("/v1/products/${product.id}")

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull
        with(response.body!!) {
            assertThat(id).isEqualTo(product.id)
            assertThat(name).isEqualTo(product.name)
            assertThat(price).isEqualByComparingTo(product.price)
        }
    }

    @Test
    fun `should return 404 response if product not found`() {
        // given
        val productId = UUID.randomUUID()

        // when
        val response = testRestTemplate.getForEntity<ErrorResponse>("/v1/products/$productId")

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.body).isNotNull
        with(response.body!!) {
            assertThat(status).isEqualTo(HttpStatus.NOT_FOUND.value())
            assertThat(code).isEqualTo("resource_not_found")
            assertThat(message).isEqualTo("Product with id $productId not found")
        }
    }
}