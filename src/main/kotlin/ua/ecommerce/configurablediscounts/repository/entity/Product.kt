package ua.ecommerce.configurablediscounts.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity(name = "Product")
@Table(name = "product", schema = "discounts")
data class Product(
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "name", columnDefinition = "varchar(50) NOT NULL", nullable = false)
    val name: String,

    @Column(name = "price", columnDefinition = "number(14,2)", nullable = false)
    val price: BigDecimal
)