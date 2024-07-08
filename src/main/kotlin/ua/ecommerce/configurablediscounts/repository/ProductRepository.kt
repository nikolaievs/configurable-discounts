package ua.ecommerce.configurablediscounts.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.ecommerce.configurablediscounts.repository.entity.Product
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID>