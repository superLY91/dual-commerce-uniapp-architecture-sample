package com.dualcommerce.android.core.model

/**
 * Shared cart item model.
 *
 * Cart is part of the Native core trade flow, so this contract keeps price
 * snapshot, selected state, quantity, and invalid-state semantics explicit.
 */
data class CartItem(
    val id: String,
    val product: Product,
    val quantity: Int,
    val selected: Boolean,
    val priceSnapshotCents: Long,
    val status: CartItemStatus
)

enum class CartItemStatus {
    VALID,
    PRICE_CHANGED,
    OUT_OF_STOCK,
    OFF_SHELF
}
