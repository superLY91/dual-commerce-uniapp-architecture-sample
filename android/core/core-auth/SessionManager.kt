package com.dualcommerce.android.core.auth

import com.dualcommerce.android.core.model.User
import com.dualcommerce.android.core.model.UserType

/**
 * Owns mock session state.
 *
 * The project does not implement real login. Native and Bridge code can query
 * this manager to distinguish guest and mock authenticated states.
 */
class SessionManager(
    private val tokenRepository: TokenRepository
) {
    private var currentUser: User = User(id = "guest", displayName = "Guest", type = UserType.GUEST)

    fun startGuestSession(): User {
        currentUser = User(id = "guest", displayName = "Guest", type = UserType.GUEST)
        tokenRepository.save("mock-guest-token")
        return currentUser
    }

    fun startMockMerchantSession(): User {
        currentUser = User(id = "merchant-user-1", displayName = "Mock Merchant", type = UserType.MOCK_MERCHANT, merchantId = "merchant-1")
        tokenRepository.save("mock-merchant-token")
        return currentUser
    }

    fun currentUser(): User = currentUser

    fun currentToken(): String? = tokenRepository.get()
}
