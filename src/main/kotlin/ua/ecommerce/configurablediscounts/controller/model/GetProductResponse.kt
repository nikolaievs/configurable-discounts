package ua.ecommerce.configurablediscounts.controller.model

import ua.ecommerce.configurablediscounts.repository.entity.Product
import java.math.BigDecimal
import java.util.UUID

data class GetProductResponse(
    val id: UUID,
    val name: String,
    val price: BigDecimal
) {
    companion object {
        fun from(product: Product) = GetProductResponse(product.id, product.name, product.price)
    }
}