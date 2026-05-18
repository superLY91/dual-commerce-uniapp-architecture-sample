package com.dualcommerce.android.app

import com.dualcommerce.android.core.compliance.PrivacyManager
import com.dualcommerce.android.core.compliance.SensitiveApiGuard
import com.dualcommerce.android.core.monitor.PerformanceTracker

/**
 * Coordinates Native shell startup.
 *
 * The initializer makes the privacy boundary visible: sensitive SDKs are not
 * initialized until the privacy agreement is accepted.
 */
class AppInitializer(
    private val privacyManager: PrivacyManager,
    private val sensitiveApiGuard: SensitiveApiGuard,
    private val performanceTracker: PerformanceTracker
) {
    fun initializeShell(): StartupState {
        performanceTracker.trackApiCost("local.startup.config", elapsedMs = 8)
        val sensitiveSdkState = if (privacyManager.canInitializeSensitiveSdk()) {
            sensitiveApiGuard.initializeSensitiveSdk("mock-analytics-sdk")
            "sensitive_sdk_initialized"
        } else {
            "sensitive_sdk_blocked_before_privacy_consent"
        }
        return StartupState(
            configLoaded = true,
            privacyStatus = privacyManager.currentStatus().name,
            sensitiveSdkState = sensitiveSdkState
        )
    }

    data class StartupState(
        val configLoaded: Boolean,
        val privacyStatus: String,
        val sensitiveSdkState: String
    )
}
