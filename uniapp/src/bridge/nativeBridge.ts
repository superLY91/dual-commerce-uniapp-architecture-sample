import type { BridgeCommand, BridgePermission, BridgeResult } from './bridgeTypes'
import { createTraceId } from './bridgeTypes'

type NativePostMessage = (command: BridgeCommand) => Promise<BridgeResult>

const nativePostMessage: NativePostMessage = async (command) => {
  // Mock transport only. A real Uniapp container would forward this command to Android Native.
  return {
    ok: true,
    data: {
      command: `${command.module}.${command.action}`,
      mode: 'mock-native-bridge'
    },
    traceId: command.traceId
  }
}

function createCommand(
  module: string,
  action: string,
  params: Record<string, string> = {},
  requiredPermission: BridgePermission = 'NONE'
): BridgeCommand {
  return {
    module,
    action,
    version: 1,
    params,
    requiredPermission,
    traceId: createTraceId(`${module}.${action}`)
  }
}

export const nativeBridge = {
  dispatch(command: BridgeCommand): Promise<BridgeResult> {
    return nativePostMessage(command)
  },

  getToken(): Promise<BridgeResult> {
    return nativePostMessage(createCommand('auth', 'getToken'))
  },

  openNativePage(page: string, params: Record<string, string> = {}): Promise<BridgeResult> {
    return nativePostMessage(
      createCommand('router', 'openNativePage', { ...params, page }, 'PRIVACY_ACCEPTED')
    )
  },

  openUniPage(page: string, params: Record<string, string> = {}): Promise<BridgeResult> {
    return nativePostMessage(
      createCommand('router', 'openUniPage', { ...params, page }, 'PRIVACY_ACCEPTED')
    )
  },

  startPayment(orderId: string): Promise<BridgeResult> {
    return nativePostMessage(
      createCommand('pay', 'startPayment', { orderId }, 'PAYMENT_PLACEHOLDER')
    )
  },

  sendEvent(eventName: string, params: Record<string, string> = {}): Promise<BridgeResult> {
    return nativePostMessage(
      createCommand('track', 'sendEvent', { ...params, eventName }, 'TRACKING')
    )
  }
}
