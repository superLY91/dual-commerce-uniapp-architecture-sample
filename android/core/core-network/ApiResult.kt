package com.dualcommerce.android.core.network

/**
 * Unified mock API result contract.
 *
 * It separates success, business errors, permission errors, degraded responses,
 * and unknown failures so Native and Uniapp can discuss errors consistently.
 */
sealed class ApiResult<out T> {
    data class Success<T>(val data: T, val traceId: String) : ApiResult<T>()
    data class BusinessError(val code: String, val message: String, val traceId: String) : ApiResult<Nothing>()
    data class PermissionError(val message: String, val traceId: String) : ApiResult<Nothing>()
    data class Degraded<T>(val data: T, val reason: String, val traceId: String) : ApiResult<T>()
    data class UnknownError(val throwableMessage: String, val traceId: String) : ApiResult<Nothing>()
}
