package com.dualcommerce.android.feature.order

import com.dualcommerce.android.core.model.Order
import com.dualcommerce.android.core.network.ApiResult

/**
 * Order data boundary.
 *
 * Order submission is Native-owned and requires an idempotent token in this
 * sample to express duplicate-submit protection.
 */
interface OrderRepository {
    fun submitOrder(idempotentToken: String): ApiResult<Order>
}
