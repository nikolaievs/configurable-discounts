package ua.ecommerce.configurablediscounts.config.builder

import ua.ecommerce.configurablediscounts.config.Discount
import java.math.BigDecimal

fun buildDiscount(
    type: Discount.Type = Discount.Type.COUNT_BASED,
    value: BigDecimal = BigDecimal.ONE,
    productsQuantity: Int? = 10,
) = Discount(type, value, productsQuantity)