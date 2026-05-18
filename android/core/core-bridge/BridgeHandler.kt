package com.dualcommerce.android.core.bridge

/**
 * Handles a single group of Bridge commands.
 *
 * Handlers keep capability ownership explicit. `NativeBridge` performs
 * permission checks before dispatching to a matching handler.
 */
interface BridgeHandler {
    fun canHandle(command: BridgeCommand): Boolean

    fun handle(command: BridgeCommand): BridgeResult
}
