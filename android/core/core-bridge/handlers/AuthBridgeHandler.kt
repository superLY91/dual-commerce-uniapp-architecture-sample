package com.dualcommerce.android.core.bridge.handlers

import com.dualcommerce.android.core.auth.SessionManager
import com.dualcommerce.android.core.bridge.BridgeCommand
import com.dualcommerce.android.core.bridge.BridgeError
import com.dualcommerce.android.core.bridge.BridgeErrorCode
import com.dualcommerce.android.core.bridge.BridgeHandler
import com.dualcommerce.android.core.bridge.BridgeResult

/**
 * Handles auth Bridge commands.
 *
 * `auth.getToken` returns a mock token only. It demonstrates session boundary
 * access without implementing real login.
 */
class AuthBridgeHandler(
    private val sessionManager: SessionManager
) : BridgeHandler {
    override fun canHandle(command: BridgeCommand): Boolean = command.name == "auth.getToken"

    override fun handle(command: BridgeCommand): BridgeResult {
        val token = sessionManager.currentToken()
            ?: return BridgeResult.Failure(
                BridgeError(BridgeErrorCode.UNAUTHORIZED, "No mock session token.", recoverable = true),
                command.traceId
            )
        return BridgeResult.Success(
            data = mapOf(
                "token" to token,
                "userType" to sessionManager.currentUser().type.name
            ),
            traceId = command.traceId
        )
    }
}
