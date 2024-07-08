package ua.ecommerce.configurablediscounts.service

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ua.ecommerce.configurablediscounts.exception.NotFoundException
import ua.ecommerce.configurablediscounts.repository.ProductRepository
import ua.ecommerce.configurablediscounts.repository.entity.builder.buildProduct
import java.util.Optional
import java.util.UUID

class ProductServiceTests {

    private val repository = mockk<ProductRepository>()

    private val instance = ProductService(repository)

    @BeforeEach
    fun cleanup() {
        clearAllMocks()
    }

    @Test
    fun `should get product by id`() {
        // given
        val id = UUID.randomUUID()
        val product = buildProduct()
        every { repository.findById(any()) } returns Optional.of(product)

        // when
        val result = instance.getProduct(id)

        // then
        assertThat(result).isEqualTo(product)
        verify(exactly = 1) { repository.findById(id) }
    }

    @Test
    fun `should return not found exception if product not found`() {
        // given
        val id = UUID.randomUUID()
        every { repository.findById(any()) } returns Optional.empty()

        // when and then
        assertThatThrownBy { instance.getProduct(id) }
            .isInstanceOf(NotFoundException::class.java)
            .hasMessage("Product with id $id not found")
        verify(exactly = 1) { repository.findById(id)  }
    }
}