import { nativeBridge } from '../bridge/nativeBridge'

const sensitiveKeys = new Set(['phone', 'address', 'idCard', 'rawOrderNo'])

export async function trackEvent(eventName: string, params: Record<string, string> = {}) {
  const sanitized = Object.fromEntries(
    Object.entries(params).map(([key, value]) => [key, sensitiveKeys.has(key) ? '***' : value])
  )
  return nativeBridge.sendEvent(eventName, sanitized)
}
