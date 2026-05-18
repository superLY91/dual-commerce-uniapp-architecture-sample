package com.dualcommerce.android.core.bridge.handlers

import com.dualcommerce.android.core.bridge.BridgeCommand
import com.dualcommerce.android.core.bridge.BridgeError
import com.dualcommerce.android.core.bridge.BridgeErrorCode
import com.dualcommerce.android.core.bridge.BridgeHandler
import com.dualcommerce.android.core.bridge.BridgeResult
import com.dualcommerce.android.core.monitor.BusinessEventTracker

/**
 * Handles payment placeholder commands.
 *
 * `pay.startPayment` never starts real payment. It records a mock business
 * event and returns a placeholder result to preserve the architecture boundary.
 */
class PayBridgeHandler(
    private val businessEventTracker: BusinessEventTracker
) : BridgeHandler {
    override fun canHandle(command: BridgeCommand): Boolean = command.name == "pay.startPayment"

    override fun handle(command: BridgeCommand): BridgeResult {
        val orderId = command.params["orderId"]
            ?: return BridgeResult.Failure(
                BridgeError(BridgeErrorCode.INVALID_PARAMS, "Missing orderId param.", recoverable = true),
                command.traceId
            )
        businessEventTracker.track("payment_placeholder_started", mapOf("orderId" to orderId))
        return BridgeResult.Success(
            data = mapOf(
                "orderId" to orderId,
                "paymentStatus" to "STARTED_PLACEHOLDER",
                "note" to "No real payment SDK is integrated."
            ),
            traceId = command.traceId
        )
    }
}
