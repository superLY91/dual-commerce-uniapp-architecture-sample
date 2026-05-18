package com.dualcommerce.android.feature.order

import com.dualcommerce.android.core.model.Order
import com.dualcommerce.android.core.network.ApiResult
import com.dualcommerce.android.core.network.MockApiClient

/**
 * Mock order repository.
 *
 * It keeps order submission deterministic and local, while preserving the
 * idempotent-token contract expected by the architecture.
 */
class MockOrderRepository(
    private val apiClient: MockApiClient
) : OrderRepository {
    override fun submitOrder(idempotentToken: String): ApiResult<Order> {
        return apiClient.submitOrder(idempotentToken)
    }
}
