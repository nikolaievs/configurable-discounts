package ua.ecommerce.configurablediscounts.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import ua.ecommerce.configurablediscounts.IntegrationTest
import ua.ecommerce.configurablediscounts.controller.model.DiscountResponse
import ua.ecommerce.configurablediscounts.controller.model.ErrorResponse
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.math.BigDecimal
import java.util.UUID

class DiscountControllerIntegrationTests : IntegrationTest() {

    @Test
    fun `should calculate discount`() {
        // given
        val product = buildProduct(name = "product for discount", price = BigDecimal.valueOf(25L))
        productRepository.save(product)

        // when
        val response = testRestTemplate.getForEntity<DiscountResponse>(
            "/v1/discounts?product_id={product_id}&product_quantity={product_quantity}",
            mapOf("product_id" to product.id, "product_quantity" to 25)
        )

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull
        assertThat(response.body!!.discountAmount).isEqualTo(36.35.toBigDecimal())
    }

    @Test
    fun `should return 404 response if product not found`() {
        // given
        val productId = UUID.randomUUID()

        // when
        val response = testRestTemplate.getForEntity<ErrorResponse>(
            "/v1/discounts?product_id={product_id}&product_quantity={product_quantity}",
            mapOf("product_id" to productId, "product_quantity" to 25)
        )

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