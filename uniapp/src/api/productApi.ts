import { request } from '../utils/request'

export interface ProductSummary {
  id: string
  title: string
  priceCents: number
  inventoryStatus: 'NORMAL' | 'LOW' | 'OUT_OF_STOCK'
  marketingTags: string[]
}

const mockProducts: ProductSummary[] = [
  {
    id: 'p-1001',
    title: 'Native Core Product',
    priceCents: 12900,
    inventoryStatus: 'NORMAL',
    marketingTags: ['campaign', 'mock']
  },
  {
    id: 'p-1002',
    title: 'Campaign Display Product',
    priceCents: 9900,
    inventoryStatus: 'LOW',
    marketingTags: ['marketing']
  }
]

export function fetchProductList() {
  return request<ProductSummary[]>({
    endpoint: '/mock/products',
    mockData: mockProducts,
    retries: 1
  })
}

export function fetchProductDetail(productId: string) {
  return request<ProductSummary | undefined>({
    endpoint: `/mock/products/${productId}`,
    mockData: mockProducts.find((product) => product.id === productId),
    retries: 1
  })
}
