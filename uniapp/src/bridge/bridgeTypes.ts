export type BridgePermission = 'NONE' | 'PRIVACY_ACCEPTED' | 'TRACKING' | 'PAYMENT_PLACEHOLDER'

export interface BridgeCommand {
  module: string
  action: string
  version: number
  params: Record<string, string>
  requiredPermission: BridgePermission
  traceId: string
}

export type BridgeErrorCode =
  | 'INVALID_PARAMS'
  | 'UNAUTHORIZED'
  | 'PERMISSION_DENIED'
  | 'NOT_SUPPORTED'
  | 'TIMEOUT'
  | 'NATIVE_ERROR'

export interface BridgeError {
  code: BridgeErrorCode
  message: string
  recoverable: boolean
}

export type BridgeResult =
  | {
      ok: true
      data: Record<string, string>
      traceId: string
    }
  | {
      ok: false
      error: BridgeError
      traceId: string
    }

export function createTraceId(prefix = 'uni'): string {
  return `${prefix}-${Date.now()}-${Math.floor(Math.random() * 1000)}`
}
