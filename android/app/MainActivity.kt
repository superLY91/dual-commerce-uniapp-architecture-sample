package com.dualcommerce.android.app

import com.dualcommerce.android.core.bridge.NativeBridge
import com.dualcommerce.android.core.bridge.handlers.AuthBridgeHandler
import com.dualcommerce.android.core.bridge.handlers.PayBridgeHandler
import com.dualcommerce.android.core.bridge.handlers.RouterBridgeHandler
import com.dualcommerce.android.core.bridge.handlers.TrackBridgeHandler
import com.dualcommerce.android.core.compliance.PermissionGate
import com.dualcommerce.android.core.compliance.PrivacyManager
import com.dualcommerce.android.core.compliance.SensitiveApiGuard
import com.dualcommerce.android.core.auth.SessionManager
import com.dualcommerce.android.core.auth.TokenRepository
import com.dualcommerce.android.core.monitor.BusinessEventTracker
import com.dualcommerce.android.core.monitor.PerformanceTracker

/**
 * Native shell entry point.
 *
 * This is a plain Kotlin placeholder for an Android Activity. It wires startup,
 * privacy, routing, mock session, monitoring, and Bridge boundaries without
 * depending on a runnable Android framework.
 */
class MainActivity {
    private val privacyManager = PrivacyManager()
    private val permissionGate = PermissionGate(privacyManager)
    private val sensitiveApiGuard = SensitiveApiGuard(privacyManager)
    private val performanceTracker = PerformanceTracker()
    private val businessEventTracker = BusinessEventTracker()
    private val tokenRepository = TokenRepository()
    private val sessionManager = SessionManager(tokenRepository)
    private val appRouter = AppRouter(performanceTracker, businessEventTracker)
    private val appInitializer = AppInitializer(
        privacyManager = privacyManager,
        sensitiveApiGuard = sensitiveApiGuard,
        performanceTracker = performanceTracker
    )

    private val nativeBridge = NativeBridge(
        permissionChecker = com.dualcommerce.android.core.bridge.BridgePermissionChecker(
            privacyManager = privacyManager,
            permissionGate = permissionGate
        ),
        handlers = listOf(
            AuthBridgeHandler(sessionManager),
            RouterBridgeHandler(appRouter),
            PayBridgeHandler(businessEventTracker),
            TrackBridgeHandler(businessEventTracker)
        ),
        performanceTracker = performanceTracker
    )

    /**
     * Simulates Activity.onCreate in the architecture sample.
     */
    fun onCreate() {
        performanceTracker.trackStartupStart()
        appInitializer.initializeShell()
        sessionManager.startGuestSession()
        appRouter.openNativePage(AppRouter.NativePage.PRODUCT_HOME)
        performanceTracker.trackStartupEnd()
    }

    /**
     * Exposes the single Native Bridge entry that Uniapp is allowed to call.
     */
    fun bridge(): NativeBridge = nativeBridge
}
