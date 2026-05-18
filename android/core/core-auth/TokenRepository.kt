package com.dualcommerce.android.core.auth

/**
 * Stores a mock token for architecture demonstration.
 *
 * This is not real authentication storage. It exists to keep token access
 * behind a repository boundary for Bridge and Native shell examples.
 */
class TokenRepository {
    private var token: String? = null

    fun save(token: String) {
        this.token = token
    }

    fun get(): String? = token

    fun clear() {
        token = null
    }
}
