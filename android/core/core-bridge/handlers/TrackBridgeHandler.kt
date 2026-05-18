package com.dualcommerce.android.core.bridge.handlers

import com.dualcommerce.android.core.bridge.BridgeCommand
import com.dualcommerce.android.core.bridge.BridgeError
import com.dualcommerce.android.core.bridge.BridgeErrorCode
import com.dualcommerce.android.core.bridge.BridgeHandler
import com.dualcommerce.android.core.bridge.BridgeResult
import com.dualcommerce.android.core.monitor.BusinessEventTracker

/**
 * Handles tracking commands from Uniapp.
 *
 * The tracker only accepts sanitized key-value params. Privacy permission is
 * checked before this handler runs.
 */
class TrackBridgeHandler(
    private val businessEventTracker: BusinessEventTracker
) : BridgeHandler {
    override fun canHandle(command: BridgeCommand): Boolean = command.name == "track.sendEvent"

    override fun handle(command: BridgeCommand): BridgeResult {
        val eventName = command.params["eventName"]
            ?: return BridgeResult.Failure(
                BridgeError(BridgeErrorCode.INVALID_PARAMS, "Missing eventName param.", recoverable = true),
                command.traceId
            )
        businessEventTracker.track(eventName, command.params - "eventName")
        return BridgeResult.Success(mapOf("tracked" to "true", "eventName" to eventName), command.traceId)
    }
}
