package ua.ecommerce.configurablediscounts.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ua.ecommerce.configurablediscounts.IntegrationTest
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.math.BigDecimal

class ProductServiceIntegrationTests(
    @Autowired private val productService: ProductService
) : IntegrationTest() {

    @Test
    fun `should get a product`() {
        // given
        val product = buildProduct(name = "test product", price = BigDecimal.TEN.setScale(2))
        productRepository.save(product)

        // when
        val result = productService.getProduct(product.id)

        // then
        assertThat(result).isEqualTo(product)
    }
}