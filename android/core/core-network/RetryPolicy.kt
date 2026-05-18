package com.dualcommerce.android.core.network

/**
 * Describes lightweight retry rules for mock APIs.
 *
 * Read requests may retry a small number of times; write requests should rely
 * on idempotent tokens and avoid blind retries.
 */
data class RetryPolicy(
    val maxAttempts: Int,
    val retryOnTimeout: Boolean,
    val requiresIdempotentToken: Boolean
) {
    companion object {
        val READ = RetryPolicy(maxAttempts = 2, retryOnTimeout = true, requiresIdempotentToken = false)
        val WRITE = RetryPolicy(maxAttempts = 1, retryOnTimeout = false, requiresIdempotentToken = true)
    }
}
