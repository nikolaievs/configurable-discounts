package ua.ecommerce.configurablediscounts.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ua.ecommerce.configurablediscounts.config.builder.buildDiscount
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.math.BigDecimal
import java.util.stream.Stream

class DiscountsConfigTest {
    private val discounts = listOf(
        buildDiscount(type = Discount.Type.COUNT_BASED, productsQuantity = 10),
        buildDiscount(type = Discount.Type.PERCENTAGE_BASED, value = 5.toBigDecimal(), productsQuantity = null),
        buildDiscount(type = Discount.Type.COUNT_BASED, value = 50.toBigDecimal(), productsQuantity = 100),
    )

    private val instance = DiscountsConfig(discounts)

    @ParameterizedTest
    @MethodSource("discountsArgumentsProvider")
    fun `should calculate discounts`(quantity: Int, expected: BigDecimal) {
        // given
        val product = buildProduct(price = 10.toBigDecimal())

        // when
        val result = instance.calculateDiscount(product, quantity)

        // then
        assertThat(result).isEqualByComparingTo(expected)
    }

    companion object {
        @JvmStatic
        fun discountsArgumentsProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(5, 2.5.toBigDecimal()),
            Arguments.of(11, 6.5.toBigDecimal()),
            Arguments.of(15, 8.5.toBigDecimal()),
            Arguments.of(100, 51.toBigDecimal()),
            Arguments.of(101, 101.5.toBigDecimal()),
            Arguments.of(150, 126.toBigDecimal())
        )
    }
}