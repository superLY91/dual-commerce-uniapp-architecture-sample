package com.dualcommerce.android.core.compliance

/**
 * Owns privacy agreement state.
 *
 * Sensitive SDK initialization, tracking, and sensitive API access must check
 * this manager first. Before consent, sensitive SDKs remain blocked.
 */
class PrivacyManager {
    private var status: PrivacyStatus = PrivacyStatus.UNKNOWN

    fun acceptPrivacyAgreement() {
        status = PrivacyStatus.ACCEPTED
    }

    fun rejectPrivacyAgreement() {
        status = PrivacyStatus.REJECTED
    }

    fun revokePrivacyAgreement() {
        status = PrivacyStatus.REVOKED
    }

    fun currentStatus(): PrivacyStatus = status

    fun isPrivacyAccepted(): Boolean = status == PrivacyStatus.ACCEPTED

    fun canInitializeSensitiveSdk(): Boolean = isPrivacyAccepted()

    fun canTrack(): Boolean = isPrivacyAccepted()
}

enum class PrivacyStatus {
    UNKNOWN,
    ACCEPTED,
    REJECTED,
    REVOKED
}
