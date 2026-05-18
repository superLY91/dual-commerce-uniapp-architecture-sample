package com.dualcommerce.android.core.bridge.handlers

import com.dualcommerce.android.app.AppRouter
import com.dualcommerce.android.core.bridge.BridgeCommand
import com.dualcommerce.android.core.bridge.BridgeError
import com.dualcommerce.android.core.bridge.BridgeErrorCode
import com.dualcommerce.android.core.bridge.BridgeHandler
import com.dualcommerce.android.core.bridge.BridgeResult

/**
 * Handles route Bridge commands.
 *
 * Native and Uniapp routes are separate to keep core trade pages Native-owned
 * while still allowing Uniapp marketing pages to open through a controlled path.
 */
class RouterBridgeHandler(
    private val appRouter: AppRouter
) : BridgeHandler {
    override fun canHandle(command: BridgeCommand): Boolean {
        return command.name == "router.openNativePage" || command.name == "router.openUniPage"
    }

    override fun handle(command: BridgeCommand): BridgeResult {
        val page = command.params["page"]
            ?: return invalid(command, "Missing page param.")

        return when (command.name) {
            "router.openNativePage" -> openNative(command, page)
            "router.openUniPage" -> openUni(command, page)
            else -> invalid(command, "Unsupported route command.")
        }
    }

    private fun openNative(command: BridgeCommand, page: String): BridgeResult {
        val nativePage = AppRouter.NativePage.values().firstOrNull { it.name == page }
            ?: return invalid(command, "Unknown native page: $page")
        val result = appRouter.openNativePage(nativePage, command.params)
        return BridgeResult.Success(mapOf("container" to result.container, "page" to result.page), command.traceId)
    }

    private fun openUni(command: BridgeCommand, page: String): BridgeResult {
        val uniPage = AppRouter.UniPage.values().firstOrNull { it.name == page }
            ?: return invalid(command, "Unknown Uniapp page: $page")
        val result = appRouter.openUniPage(uniPage, command.params)
        return BridgeResult.Success(mapOf("container" to result.container, "page" to result.page), command.traceId)
    }

    private fun invalid(command: BridgeCommand, message: String): BridgeResult.Failure {
        return BridgeResult.Failure(
            BridgeError(BridgeErrorCode.INVALID_PARAMS, message, recoverable = true),
            command.traceId
        )
    }
}
