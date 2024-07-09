package ua.ecommerce.configurablediscounts.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ua.ecommerce.configurablediscounts.config.DiscountsConfig
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.math.BigDecimal

class DiscountServiceTest {

    private val productService = mockk<ProductService>()
    private val discountConfig = mockk<DiscountsConfig>()

    private val instance = DiscountService(productService, discountConfig)

    @Test
    fun `should return calculated discount`() {
        // given
        val product = buildProduct()
        every { productService.getProduct(any()) } returns product
        every { discountConfig.calculateDiscount(any(), any()) } returns BigDecimal.ONE

        // when
        val result = instance.calculateDiscount(product.id, 10)

        // then
        assertThat(result).isEqualTo(BigDecimal.ONE)
        verify(exactly = 1) { productService.getProduct(product.id) }
        verify(exactly = 1) { discountConfig.calculateDiscount(product, 10) }
    }
}