package ua.ecommerce.configurablediscounts.service

import org.springframework.stereotype.Service
import ua.ecommerce.configurablediscounts.exception.NotFoundException
import ua.ecommerce.configurablediscounts.repository.ProductRepository
import ua.ecommerce.configurablediscounts.repository.entity.Product
import java.util.UUID
import kotlin.jvm.optionals.getOrElse

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getProduct(id: UUID): Product = productRepository.findById(id).getOrElse {
        throw NotFoundException("Product with id $id not found")
    }
}