package ua.ecommerce.configurablediscounts.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import ua.ecommerce.configurablediscounts.repository.entity.Product
import ua.ecommerce.configurablediscounts.support.factory.YamlPropertySourceFactory
import java.math.BigDecimal

@Configuration
@ConfigurationProperties
@PropertySource(value = ["classpath:discounts.yaml"], factory = YamlPropertySourceFactory::class)
data class DiscountsConfig(
    val discounts: List<Discount>
) {

    fun calculateDiscount(product: Product, quantity: Int): BigDecimal = discounts.sumOf {
        it.calculate(product, quantity)
    }.setScale(2)
}