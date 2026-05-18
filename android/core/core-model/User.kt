package com.dualcommerce.android.core.model

/**
 * Mock user/session model.
 *
 * The project does not implement real login. This model only describes guest
 * and mock-user states used by the Native shell and Bridge examples.
 */
data class User(
    val id: String,
    val displayName: String,
    val type: UserType,
    val merchantId: String? = null
)

enum class UserType {
    GUEST,
    MOCK_CONSUMER,
    MOCK_MERCHANT
}
