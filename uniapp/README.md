# Uniapp Business Layer Skeleton

This directory shows how Uniapp pages are organized in the dual-commerce architecture sample. It is not a complete Uniapp app and does not connect to a real backend.

## Suitable For Uniapp

- Campaign and marketing pages that change frequently.
- Merchant dashboard and light product management pages.
- Order filtering and merchant-side operational views.
- AI copywriting pages where generated content requires human confirmation.
- Configurable pages that benefit from fast iteration.

## Not Suitable For Uniapp

- Core cart settlement.
- Order submission.
- Real payment.
- Real login.
- Native SDK access.
- Privacy-sensitive device APIs.

## Boundary

Uniapp uses mock API modules under `src/api` and can only call Native capabilities through `src/bridge/nativeBridge.ts`. The Bridge command structure mirrors Android `BridgeCommand`: `module`, `action`, `version`, `params`, `requiredPermission`, and `traceId`.

Core transaction decisions remain in Android Native. Uniapp demonstrates page orchestration, mock state, controlled Bridge calls, and fast-changing business surfaces.
