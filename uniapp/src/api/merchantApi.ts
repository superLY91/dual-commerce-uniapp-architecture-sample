import { request } from '../utils/request'

export interface MerchantDashboard {
  merchantId: string
  pendingOrders: number
  lowInventoryProducts: number
  campaignDrafts: number
}

export interface MerchantProductDraft {
  productId: string
  title: string
  sellingPoint: string
  status: 'DRAFT' | 'READY_FOR_NATIVE_REVIEW'
}

export function fetchMerchantDashboard() {
  return request<MerchantDashboard>({
    endpoint: '/mock/merchant/dashboard',
    mockData: {
      merchantId: 'merchant-1',
      pendingOrders: 8,
      lowInventoryProducts: 3,
      campaignDrafts: 2
    },
    retries: 1
  })
}

export function fetchMerchantProductDraft(productId: string) {
  return request<MerchantProductDraft>({
    endpoint: `/mock/merchant/products/${productId}`,
    mockData: {
      productId,
      title: 'Editable Campaign Product',
      sellingPoint: 'Light edit only. Core trade data stays Native controlled.',
      status: 'DRAFT'
    },
    retries: 1
  })
}
