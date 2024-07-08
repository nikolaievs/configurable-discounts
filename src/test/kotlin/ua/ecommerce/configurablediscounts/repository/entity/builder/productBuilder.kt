package ua.ecommerce.configurablediscounts.repository.entity.builder

import ua.ecommerce.configurablediscounts.repository.entity.Product
import java.math.BigDecimal
import java.util.UUID

fun buildProduct(
    id: UUID = UUID.randomUUID(),
    name: String = "test product name",
    price: BigDecimal = BigDecimal.ONE,
) = Product(id, name, price)