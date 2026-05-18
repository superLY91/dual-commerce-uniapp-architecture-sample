package com.dualcommerce.android.core.bridge

import com.dualcommerce.android.core.monitor.PerformanceTracker

/**
 * Single Native entry point for Uniapp commands.
 *
 * The dispatch flow is: validate command shape, check permission, find handler,
 * execute handler, and track Bridge cost. This keeps Native capability exposure
 * centralized and auditable.
 */
class NativeBridge(
    private val permissionChecker: BridgePermissionChecker,
    private val handlers: List<BridgeHandler>,
    private val performanceTracker: PerformanceTracker
) {
    fun dispatch(command: BridgeCommand): BridgeResult {
        val startedAt = System.currentTimeMillis()
        try {
            if (command.module.isBlank() || command.action.isBlank()) {
                return failure(command, BridgeErrorCode.INVALID_PARAMS, "Bridge module and action are required.")
            }

            permissionChecker.check(command)?.let { return it }

            val handler = handlers.firstOrNull { it.canHandle(command) }
                ?: return failure(command, BridgeErrorCode.NOT_SUPPORTED, "Unsupported Bridge command: ${command.name}")

            return handler.handle(command)
        } catch (error: Throwable) {
            return failure(command, BridgeErrorCode.NATIVE_ERROR, error.message ?: "Unknown Native Bridge error.")
        } finally {
            performanceTracker.trackBridgeCost(command.name, System.currentTimeMillis() - startedAt)
        }
    }

    private fun failure(command: BridgeCommand, code: BridgeErrorCode, message: String): BridgeResult.Failure {
        return BridgeResult.Failure(
            error = BridgeError(code = code, message = message, recoverable = code != BridgeErrorCode.NATIVE_ERROR),
            traceId = command.traceId
        )
    }
}
