package com.dualcommerce.android.feature.product

import com.dualcommerce.android.core.model.Product
import com.dualcommerce.android.core.network.ApiResult

/**
 * Product data boundary.
 *
 * Feature code depends on this repository instead of a concrete mock data
 * source, making future backend replacement explicit.
 */
interface ProductRepository {
    fun getProducts(): ApiResult<List<Product>>

    fun getProductDetail(productId: String): ApiResult<Product>
}
