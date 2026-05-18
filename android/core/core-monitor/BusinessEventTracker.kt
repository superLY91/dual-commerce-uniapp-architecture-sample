package com.dualcommerce.android.core.monitor

/**
 * Business event tracker facade.
 *
 * Events are stored in memory to show where sanitized tracking would occur
 * without integrating a real analytics SDK.
 */
class BusinessEventTracker {
    private val events = mutableListOf<BusinessEvent>()

    fun track(name: String, params: Map<String, String> = emptyMap()) {
        events += BusinessEvent(name = name, params = sanitize(params))
    }

    fun snapshot(): List<BusinessEvent> = events.toList()

    private fun sanitize(params: Map<String, String>): Map<String, String> {
        val sensitiveKeys = setOf("phone", "address", "idCard", "rawOrderNo")
        return params.mapValues { (key, value) ->
            if (key in sensitiveKeys) "***" else value
        }
    }
}

data class BusinessEvent(
    val name: String,
    val params: Map<String, String>
)
