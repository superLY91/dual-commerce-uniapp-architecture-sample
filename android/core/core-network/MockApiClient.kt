package com.dualcommerce.android.core.network

import com.dualcommerce.android.core.model.CartItem
import com.dualcommerce.android.core.model.CartItemStatus
import com.dualcommerce.android.core.model.Inventory
import com.dualcommerce.android.core.model.InventoryWarningLevel
import com.dualcommerce.android.core.model.Merchant
import com.dualcommerce.android.core.model.MerchantPermission
import com.dualcommerce.android.core.model.MerchantStatus
import com.dualcommerce.android.core.model.Order
import com.dualcommerce.android.core.model.OrderStatus
import com.dualcommerce.android.core.model.PaymentMethod
import com.dualcommerce.android.core.model.PaymentPlaceholder
import com.dualcommerce.android.core.model.PaymentStatus
import com.dualcommerce.android.core.model.Product
import com.dualcommerce.android.core.model.ProductStatus

/**
 * Mock API client for architecture demonstration.
 *
 * It never talks to a backend. Feature repositories use this client to show how
 * data sources can be isolated behind repository boundaries.
 */
class MockApiClient {
    private val traceId = "mock-trace-001"

    fun fetchProducts(): ApiResult<List<Product>> {
        return ApiResult.Success(
            data = listOf(mockProduct("p-1001", "Native Core Product")),
            traceId = traceId
        )
    }

    fun fetchCart(): ApiResult<List<CartItem>> {
        val product = mockProduct("p-1001", "Native Core Product")
        return ApiResult.Success(
            data = listOf(
                CartItem(
                    id = "cart-1",
                    product = product,
                    quantity = 1,
                    selected = true,
                    priceSnapshotCents = product.priceCents,
                    status = CartItemStatus.VALID
                )
            ),
            traceId = traceId
        )
    }

    fun submitOrder(idempotentToken: String): ApiResult<Order> {
        if (idempotentToken.isBlank()) {
            return ApiResult.BusinessError("MISSING_IDEMPOTENT_TOKEN", "Order submission requires an idempotent token.", traceId)
        }
        val product = mockProduct("p-1001", "Native Core Product")
        val item = CartItem("cart-1", product, quantity = 1, selected = true, priceSnapshotCents = product.priceCents, status = CartItemStatus.VALID)
        return ApiResult.Success(
            data = Order(
                id = "order-9001",
                items = listOf(item),
                status = OrderStatus.SUBMITTED,
                totalCents = product.priceCents,
                payment = PaymentPlaceholder(PaymentMethod.UNSPECIFIED, PaymentStatus.NOT_STARTED),
                afterSalesAvailable = true,
                idempotentToken = idempotentToken
            ),
            traceId = traceId
        )
    }

    fun fetchMerchant(): ApiResult<Merchant> {
        return ApiResult.Success(
            data = Merchant(
                id = "merchant-1",
                name = "Mock Merchant",
                status = MerchantStatus.ACTIVE,
                permissions = setOf(
                    MerchantPermission.VIEW_WORKBENCH,
                    MerchantPermission.EDIT_PRODUCT_LIGHT,
                    MerchantPermission.FILTER_ORDERS,
                    MerchantPermission.GENERATE_AI_COPY
                )
            ),
            traceId = traceId
        )
    }

    private fun mockProduct(id: String, title: String): Product {
        return Product(
            id = id,
            title = title,
            priceCents = 129_00,
            inventory = Inventory(
                availableQuantity = 12,
                warningLevel = InventoryWarningLevel.NORMAL
            ),
            status = ProductStatus.ON_SHELF,
            marketingTags = listOf("mock", "native-core"),
            merchantId = "merchant-1"
        )
    }
}
