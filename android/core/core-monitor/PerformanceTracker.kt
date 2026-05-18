package com.dualcommerce.android.core.monitor

/**
 * Performance metric facade.
 *
 * The sample records startup, page, API, and Bridge costs as in-memory events
 * instead of sending them to a real APM SDK.
 */
class PerformanceTracker {
    private val metrics = mutableListOf<PerformanceMetric>()
    private var startupStartedAt: Long = 0L

    fun trackStartupStart() {
        startupStartedAt = System.currentTimeMillis()
        metrics += PerformanceMetric("startup_start", 0, mapOf("stage" to "start"))
    }

    fun trackStartupEnd() {
        val elapsed = if (startupStartedAt == 0L) 0L else System.currentTimeMillis() - startupStartedAt
        metrics += PerformanceMetric("startup_end", elapsed, mapOf("stage" to "end"))
    }

    fun trackPageOpen(pageName: String, container: String) {
        metrics += PerformanceMetric("page_open", 0, mapOf("page" to pageName, "container" to container))
    }

    fun trackApiCost(apiName: String, elapsedMs: Long) {
        metrics += PerformanceMetric("api_cost", elapsedMs, mapOf("api" to apiName))
    }

    fun trackBridgeCost(commandName: String, elapsedMs: Long) {
        metrics += PerformanceMetric("bridge_cost", elapsedMs, mapOf("command" to commandName))
    }

    fun snapshot(): List<PerformanceMetric> = metrics.toList()
}

data class PerformanceMetric(
    val name: String,
    val elapsedMs: Long,
    val dimensions: Map<String, String>
)
