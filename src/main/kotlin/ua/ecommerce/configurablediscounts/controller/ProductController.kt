package ua.ecommerce.configurablediscounts.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.ecommerce.configurablediscounts.controller.model.GetProductResponse
import ua.ecommerce.configurablediscounts.service.ProductService
import java.util.UUID

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/{productId}")
    fun getProductById(
        @PathVariable("productId") productId: UUID
    ): GetProductResponse = GetProductResponse.from(productService.getProduct(productId))
}