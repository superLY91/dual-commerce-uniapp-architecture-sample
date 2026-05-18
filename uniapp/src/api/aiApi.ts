import { request } from '../utils/request'

export interface AiCopyRequest {
  productTitle: string
  sellingPoints: string[]
  scenario: 'PRODUCT_TITLE' | 'MARKETING_COPY' | 'AFTER_SALES_REPLY'
}

export interface AiCopyResult {
  candidates: string[]
  requiresHumanConfirm: boolean
  riskNote: string
}

export function generateCopywriting(input: AiCopyRequest) {
  return request<AiCopyResult>({
    endpoint: '/mock/ai/copywriter',
    mockData: {
      candidates: [
        `${input.productTitle} - ${input.sellingPoints.join(' / ')}`,
        `Campaign ready: ${input.productTitle}`
      ],
      requiresHumanConfirm: true,
      riskNote: 'Mock AI output. Human confirmation is required before publishing.'
    },
    retries: 0
  })
}
