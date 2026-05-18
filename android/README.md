# Android Architecture Skeleton

This directory is a Kotlin-only architecture skeleton for the interview sample. It is intentionally not a complete Android project and does not include Gradle, real backend calls, real login, real payment, push SDKs, or third-party SDK integrations.

## Boundary

- `app`: Native shell entry, startup orchestration, and route declarations.
- `core/core-model`: Shared model contract for Native and Uniapp-facing mock APIs.
- `core/core-network`: Mock API result, retry policy, and fake data client.
- `core/core-auth`: Mock session and token boundary.
- `core/core-bridge`: Command-based Native-Uniapp Bridge contract and handlers.
- `core/core-monitor`: Performance and business event tracking facade.
- `core/core-compliance`: Privacy consent, permission gate, and sensitive API guard.
- `feature`: Repository boundaries for product, cart, and order features.

## Interview Intent

The structure shows why core trade flows stay in Native while Uniapp can call controlled Native capabilities through `NativeBridge`. Every Bridge command is checked by `BridgePermissionChecker` before dispatch. Sensitive SDK initialization is blocked until `PrivacyManager` marks consent as accepted.
