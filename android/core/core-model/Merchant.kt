package com.dualcommerce.android.core.model

/**
 * Shared merchant-side model.
 *
 * Merchant workbench and light product editing can live in Uniapp, but the
 * model keeps merchant status and operation scope consistent with Native.
 */
data class Merchant(
    val id: String,
    val name: String,
    val status: MerchantStatus,
    val permissions: Set<MerchantPermission>
)

enum class MerchantStatus {
    ACTIVE,
    SUSPENDED,
    PENDING_REVIEW
}

enum class MerchantPermission {
    VIEW_WORKBENCH,
    EDIT_PRODUCT_LIGHT,
    FILTER_ORDERS,
    GENERATE_AI_COPY
}
