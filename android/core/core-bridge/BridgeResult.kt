package com.dualcommerce.android.core.bridge

/**
 * Unified Bridge response contract.
 *
 * Results use ADR-defined error categories so Uniapp can handle failures
 * consistently without knowing Native internals.
 */
sealed class BridgeResult {
    data class Success(
        val data: Map<String, String> = emptyMap(),
        val traceId: String
    ) : BridgeResult()

    data class Failure(
        val error: BridgeError,
        val traceId: String
    ) : BridgeResult()
}

data class BridgeError(
    val code: BridgeErrorCode,
    val message: String,
    val recoverable: Boolean
)

enum class BridgeErrorCode {
    INVALID_PARAMS,
    UNAUTHORIZED,
    PERMISSION_DENIED,
    NOT_SUPPORTED,
    TIMEOUT,
    NATIVE_ERROR
}
