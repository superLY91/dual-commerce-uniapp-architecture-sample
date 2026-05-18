package com.dualcommerce.android.core.model

/**
 * Shared order model contract.
 *
 * The sample keeps order submission Native-controlled and marks payment as a
 * placeholder to avoid real payment integration.
 */
data class Order(
    val id: String,
    val items: List<CartItem>,
    val status: OrderStatus,
    val totalCents: Long,
    val payment: PaymentPlaceholder,
    val afterSalesAvailable: Boolean,
    val idempotentToken: String
)

data class PaymentPlaceholder(
    val method: PaymentMethod,
    val status: PaymentStatus,
    val riskNote: String = "Mock payment only. No real payment SDK is integrated."
)

enum class OrderStatus {
    DRAFT,
    SUBMITTING,
    SUBMITTED,
    PAID_PLACEHOLDER,
    CANCELLED,
    AFTER_SALES
}

enum class PaymentMethod {
    MOCK_WALLET,
    MOCK_CARD,
    UNSPECIFIED
}

enum class PaymentStatus {
    NOT_STARTED,
    STARTED,
    SUCCESS_PLACEHOLDER,
    FAILED_PLACEHOLDER
}
