package com.dualcommerce.android.core.compliance

/**
 * Checks business and system permission preconditions.
 *
 * This mock gate avoids real runtime permission APIs while preserving the
 * architecture decision that permissions are centralized.
 */
class PermissionGate(
    private val privacyManager: PrivacyManager
) {
    private val grantedPermissions = mutableSetOf<String>()

    fun grant(permission: String) {
        grantedPermissions += permission
    }

    fun revoke(permission: String) {
        grantedPermissions -= permission
    }

    fun canAccessSensitiveApi(permission: String): Boolean {
        return privacyManager.isPrivacyAccepted() && permission in grantedPermissions
    }

    fun isBusinessActionAllowed(action: String): Boolean {
        return privacyManager.isPrivacyAccepted() && action.isNotBlank()
    }
}
