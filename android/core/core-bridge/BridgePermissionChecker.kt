package com.dualcommerce.android.core.bridge

import com.dualcommerce.android.core.compliance.PermissionGate
import com.dualcommerce.android.core.compliance.PrivacyManager

/**
 * Central permission gate for every Bridge command.
 *
 * Uniapp cannot bypass this checker because `NativeBridge.dispatch` calls it
 * before selecting a handler.
 */
class BridgePermissionChecker(
    private val privacyManager: PrivacyManager,
    private val permissionGate: PermissionGate
) {
    fun check(command: BridgeCommand): BridgeResult? {
        return when (effectivePermission(command)) {
            BridgePermission.NONE -> null
            BridgePermission.PRIVACY_ACCEPTED -> requirePrivacyAccepted(command)
            BridgePermission.TRACKING -> {
                if (privacyManager.canTrack()) null else denied(command, "Tracking is blocked before privacy consent.")
            }
            BridgePermission.PAYMENT_PLACEHOLDER -> {
                val allowed = privacyManager.isPrivacyAccepted() && permissionGate.isBusinessActionAllowed("payment-placeholder")
                if (allowed) null else denied(command, "Payment placeholder requires privacy consent and business permission.")
            }
        }
    }

    private fun effectivePermission(command: BridgeCommand): BridgePermission {
        val requiredByCommandName = when (command.name) {
            "router.openNativePage",
            "router.openUniPage" -> BridgePermission.PRIVACY_ACCEPTED
            "pay.startPayment" -> BridgePermission.PAYMENT_PLACEHOLDER
            "track.sendEvent" -> BridgePermission.TRACKING
            else -> BridgePermission.NONE
        }
        return strongest(command.requiredPermission, requiredByCommandName)
    }

    private fun strongest(left: BridgePermission, right: BridgePermission): BridgePermission {
        return if (rank(left) >= rank(right)) left else right
    }

    private fun rank(permission: BridgePermission): Int {
        return when (permission) {
            BridgePermission.NONE -> 0
            BridgePermission.PRIVACY_ACCEPTED -> 1
            BridgePermission.TRACKING -> 2
            BridgePermission.PAYMENT_PLACEHOLDER -> 3
        }
    }

    private fun requirePrivacyAccepted(command: BridgeCommand): BridgeResult? {
        return if (privacyManager.isPrivacyAccepted()) null else denied(command, "Privacy agreement is not accepted.")
    }

    private fun denied(command: BridgeCommand, message: String): BridgeResult.Failure {
        return BridgeResult.Failure(
            error = BridgeError(
                code = BridgeErrorCode.PERMISSION_DENIED,
                message = message,
                recoverable = true
            ),
            traceId = command.traceId
        )
    }
}
