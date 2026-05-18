package com.dualcommerce.android.feature.cart

import com.dualcommerce.android.core.model.CartItem
import com.dualcommerce.android.core.network.ApiResult
import com.dualcommerce.android.core.network.MockApiClient

/**
 * Mock cart repository.
 *
 * It shows request validation and mock state flow without implementing a full
 * cart engine or real backend synchronization.
 */
class MockCartRepository(
    private val apiClient: MockApiClient
) : CartRepository {
    override fun getCart(): ApiResult<List<CartItem>> = apiClient.fetchCart()

    override fun updateQuantity(cartItemId: String, quantity: Int): ApiResult<List<CartItem>> {
        if (quantity <= 0) {
            return ApiResult.BusinessError("INVALID_QUANTITY", "Quantity must be greater than zero.", "mock-trace-cart")
        }
        return apiClient.fetchCart()
    }
}
