package com.dualcommerce.android.core.model

/**
 * Shared product model contract.
 *
 * Native owns product detail core actions, while Uniapp may reuse this contract
 * for campaign shelves or merchant light editing without redefining semantics.
 */
data class Product(
    val id: String,
    val title: String,
    val priceCents: Long,
    val inventory: Inventory,
    val status: ProductStatus,
    val marketingTags: List<String> = emptyList(),
    val merchantId: String
)

data class Inventory(
    val availableQuantity: Int,
    val warningLevel: InventoryWarningLevel,
    val lockedQuantity: Int = 0
)

enum class ProductStatus {
    ON_SHELF,
    OFF_SHELF,
    SOLD_OUT
}

enum class InventoryWarningLevel {
    NORMAL,
    LOW,
    OUT_OF_STOCK
}
