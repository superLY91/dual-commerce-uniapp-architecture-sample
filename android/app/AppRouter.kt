package com.dualcommerce.android.app

import com.dualcommerce.android.core.monitor.BusinessEventTracker
import com.dualcommerce.android.core.monitor.PerformanceTracker

/**
 * Owns Native shell route declarations.
 *
 * Core trade routes are represented as Native pages, while dynamic marketing
 * routes are represented as Uniapp pages. The router is intentionally a mock
 * boundary and does not start real Android Activities.
 */
class AppRouter(
    private val performanceTracker: PerformanceTracker,
    private val businessEventTracker: BusinessEventTracker
) {
    enum class NativePage {
        PRODUCT_HOME,
        PRODUCT_DETAIL,
        CART,
        ORDER_CONFIRM,
        ORDER_DETAIL,
        PAYMENT_PLACEHOLDER
    }

    enum class UniPage {
        CAMPAIGN,
        MARKETING,
        MERCHANT_WORKBENCH,
        PRODUCT_LIGHT_EDIT,
        ORDER_FILTER,
        AI_COPYWRITING,
        CONFIGURABLE_PAGE
    }

    fun openNativePage(page: NativePage, params: Map<String, String> = emptyMap()): RouteResult {
        performanceTracker.trackPageOpen(page.name, container = "native")
        businessEventTracker.track("native_route_opened", params + ("page" to page.name))
        return RouteResult(opened = true, container = "native", page = page.name)
    }

    fun openUniPage(page: UniPage, params: Map<String, String> = emptyMap()): RouteResult {
        performanceTracker.trackPageOpen(page.name, container = "uniapp")
        businessEventTracker.track("uniapp_route_opened", params + ("page" to page.name))
        return RouteResult(opened = true, container = "uniapp", page = page.name)
    }

    data class RouteResult(
        val opened: Boolean,
        val container: String,
        val page: String
    )
}
