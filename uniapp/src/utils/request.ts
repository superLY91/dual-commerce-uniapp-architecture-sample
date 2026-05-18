import { nativeBridge } from '../bridge/nativeBridge'

export type ApiErrorCode = 'NETWORK_ERROR' | 'BUSINESS_ERROR' | 'PERMISSION_ERROR' | 'TIMEOUT' | 'UNKNOWN'

export interface ApiResponse<T> {
  ok: boolean
  data?: T
  errorCode?: ApiErrorCode
  message?: string
  traceId: string
}

interface RequestOptions<T> {
  endpoint: string
  timeoutMs?: number
  retries?: number
  mockLatencyMs?: number
  mockData: T
  shouldFail?: boolean
}

export async function request<T>(options: RequestOptions<T>): Promise<ApiResponse<T>> {
  const timeoutMs = options.timeoutMs ?? 800
  const retries = options.retries ?? 1
  const traceId = `api-${Date.now()}`
  const tokenResult = await nativeBridge.getToken()
  const token = tokenResult.ok ? tokenResult.data.token ?? 'mock-uniapp-token' : 'mock-uniapp-token'
  const headers = {
    Authorization: `Bearer ${token}`,
    'X-Trace-Id': traceId
  }

  for (let attempt = 0; attempt <= retries; attempt += 1) {
    const result = await mockNetworkCall(options, timeoutMs, headers, traceId)
    if (result.ok || result.errorCode !== 'TIMEOUT' || attempt === retries) {
      return result
    }
  }

  return {
    ok: false,
    errorCode: 'UNKNOWN',
    message: 'Unexpected mock request state.',
    traceId
  }
}

async function mockNetworkCall<T>(
  options: RequestOptions<T>,
  timeoutMs: number,
  headers: Record<string, string>,
  traceId: string
): Promise<ApiResponse<T>> {
  const latencyMs = options.mockLatencyMs ?? 40

  if (!headers.Authorization.startsWith('Bearer ')) {
    return {
      ok: false,
      errorCode: 'PERMISSION_ERROR',
      message: 'Missing mock token.',
      traceId
    }
  }

  if (latencyMs > timeoutMs) {
    return {
      ok: false,
      errorCode: 'TIMEOUT',
      message: `Mock timeout from ${options.endpoint}.`,
      traceId
    }
  }

  if (options.shouldFail) {
    return {
      ok: false,
      errorCode: 'BUSINESS_ERROR',
      message: `Mock business error from ${options.endpoint}`,
      traceId
    }
  }

  return {
    ok: true,
    data: options.mockData,
    traceId
  }
}
