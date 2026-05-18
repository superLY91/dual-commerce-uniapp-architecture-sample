package com.dualcommerce.android.feature.cart

import com.dualcommerce.android.core.model.CartItem
import com.dualcommerce.android.core.network.ApiResult

/**
 * Cart data boundary.
 *
 * Cart remains part of the Native core trade flow; this repository isolates
 * mock data from cart use cases.
 */
interface CartRepository {
    fun getCart(): ApiResult<List<CartItem>>

    fun updateQuantity(cartItemId: String, quantity: Int): ApiResult<List<CartItem>>
}
