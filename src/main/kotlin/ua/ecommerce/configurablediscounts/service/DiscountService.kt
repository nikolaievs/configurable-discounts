package ua.ecommerce.configurablediscounts.service

import org.springframework.stereotype.Service
import ua.ecommerce.configurablediscounts.config.DiscountsConfig
import java.math.BigDecimal
import java.util.UUID

@Service
class DiscountService(
    private val productService: ProductService,
    private val discountsConfig: DiscountsConfig
) {

    fun calculateDiscount(productId: UUID, quantity: Int): BigDecimal {
        val product = productService.getProduct(productId)

        return discountsConfig.calculateDiscount(product, quantity)
    }
}