package ua.ecommerce.configurablediscounts.config

import ua.ecommerce.configurablediscounts.repository.entity.Product
import java.math.BigDecimal

data class Discount(
    val type: Type,
    private val value: BigDecimal,
    private val productsQuantity: Int?,
) {

    fun calculate(
        product: Product,
        quantity: Int
    ): BigDecimal = type.calculate(
        product.price.multiply(quantity.toBigDecimal()),
        quantity,
        value,
        productsQuantity
    )

    enum class Type {
        COUNT_BASED {
            override fun calculate(
                totalPrice: BigDecimal,
                realQuantity: Int,
                value: BigDecimal,
                configuredProductsQuantity: Int?
            ): BigDecimal = if (configuredProductsQuantity!! < realQuantity) value else BigDecimal.ZERO
        },

        PERCENTAGE_BASED {
            override fun calculate(
                totalPrice: BigDecimal,
                realQuantity: Int,
                value: BigDecimal,
                configuredProductsQuantity: Int?
            ): BigDecimal = totalPrice.multiply(value).scaleByPowerOfTen(-2)
        };

        abstract fun calculate(
            totalPrice: BigDecimal,
            realQuantity: Int,
            value: BigDecimal,
            configuredProductsQuantity: Int?
        ): BigDecimal
    }
}