package com.dualcommerce.android.core.compliance

/**
 * Facade for sensitive SDKs and device APIs.
 *
 * Business code should use this guard instead of directly touching location,
 * camera, clipboard, notification, or SDK initialization APIs.
 */
class SensitiveApiGuard(
    private val privacyManager: PrivacyManager
) {
    private val initializedSdks = mutableListOf<String>()

    fun initializeSensitiveSdk(sdkName: String): SensitiveApiResult {
        if (!privacyManager.canInitializeSensitiveSdk()) {
            return SensitiveApiResult.Blocked("Privacy consent is required before initializing $sdkName.")
        }
        initializedSdks += sdkName
        return SensitiveApiResult.Allowed("$sdkName initialized in mock mode.")
    }

    fun initializedSdkSnapshot(): List<String> = initializedSdks.toList()
}

sealed class SensitiveApiResult {
    data class Allowed(val message: String) : SensitiveApiResult()
    data class Blocked(val reason: String) : SensitiveApiResult()
}
