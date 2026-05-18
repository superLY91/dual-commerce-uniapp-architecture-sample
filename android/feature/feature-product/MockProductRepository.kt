package com.dualcommerce.android.feature.product

import com.dualcommerce.android.core.model.Product
import com.dualcommerce.android.core.network.ApiResult
import com.dualcommerce.android.core.network.MockApiClient

/**
 * Mock product repository.
 *
 * It demonstrates the repository pattern without implementing a real product
 * service, search service, or recommendation engine.
 */
class MockProductRepository(
    private val apiClient: MockApiClient
) : ProductRepository {
    override fun getProducts(): ApiResult<List<Product>> = apiClient.fetchProducts()

    override fun getProductDetail(productId: String): ApiResult<Product> {
        return when (val result = apiClient.fetchProducts()) {
            is ApiResult.Success -> {
                val product = result.data.firstOrNull { it.id == productId }
                    ?: return ApiResult.BusinessError("PRODUCT_NOT_FOUND", "Mock product not found.", result.traceId)
                ApiResult.Success(product, result.traceId)
            }
            is ApiResult.BusinessError -> result
            is ApiResult.PermissionError -> result
            is ApiResult.Degraded -> {
                val product = result.data.firstOrNull { it.id == productId }
                    ?: return ApiResult.BusinessError("PRODUCT_NOT_FOUND", "Mock product not found.", result.traceId)
                ApiResult.Degraded(product, result.reason, result.traceId)
            }
            is ApiResult.UnknownError -> result
        }
    }
}
