package com.dualcommerce.android.core.bridge

/**
 * Command object sent from Uniapp to Native.
 *
 * The command mode keeps Bridge calls explicit: module, action, version,
 * params, permission expectation, and traceId travel together.
 */
data class BridgeCommand(
    val module: String,
    val action: String,
    val version: Int = 1,
    val params: Map<String, String> = emptyMap(),
    val requiredPermission: BridgePermission = BridgePermission.NONE,
    val traceId: String
) {
    val name: String = "$module.$action"

    companion object {
        fun authGetToken(traceId: String): BridgeCommand {
            return BridgeCommand(module = "auth", action = "getToken", traceId = traceId)
        }

        fun openNativePage(page: String, traceId: String): BridgeCommand {
            return BridgeCommand(
                module = "router",
                action = "openNativePage",
                params = mapOf("page" to page),
                requiredPermission = BridgePermission.PRIVACY_ACCEPTED,
                traceId = traceId
            )
        }

        fun openUniPage(page: String, traceId: String): BridgeCommand {
            return BridgeCommand(
                module = "router",
                action = "openUniPage",
                params = mapOf("page" to page),
                requiredPermission = BridgePermission.PRIVACY_ACCEPTED,
                traceId = traceId
            )
        }

        fun startPayment(orderId: String, traceId: String): BridgeCommand {
            return BridgeCommand(
                module = "pay",
                action = "startPayment",
                params = mapOf("orderId" to orderId),
                requiredPermission = BridgePermission.PAYMENT_PLACEHOLDER,
                traceId = traceId
            )
        }

        fun sendEvent(eventName: String, params: Map<String, String>, traceId: String): BridgeCommand {
            return BridgeCommand(
                module = "track",
                action = "sendEvent",
                params = params + ("eventName" to eventName),
                requiredPermission = BridgePermission.TRACKING,
                traceId = traceId
            )
        }
    }
}

enum class BridgePermission {
    NONE,
    PRIVACY_ACCEPTED,
    TRACKING,
    PAYMENT_PLACEHOLDER
}
