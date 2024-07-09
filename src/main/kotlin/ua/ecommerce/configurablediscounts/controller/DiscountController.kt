package ua.ecommerce.configurablediscounts.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.ecommerce.configurablediscounts.controller.model.DiscountResponse
import ua.ecommerce.configurablediscounts.service.DiscountService
import java.util.UUID

@RestController
@RequestMapping("/v1/discounts")
class DiscountController(
    private val discountService: DiscountService
) {

    @GetMapping
    fun calculateDiscount(
        @RequestParam(name = "product_id") productId: UUID,
        @RequestParam(name = "product_quantity") quantity: Int
    ): DiscountResponse = DiscountResponse(discountService.calculateDiscount(productId, quantity))
}