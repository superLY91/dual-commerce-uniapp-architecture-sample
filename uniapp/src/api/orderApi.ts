import { request } from '../utils/request'

export interface OrderSummary {
  id: string
  status: 'SUBMITTED' | 'PAID_PLACEHOLDER' | 'AFTER_SALES'
  totalCents: number
  itemCount: number
  afterSalesAvailable: boolean
}

const mockOrders: OrderSummary[] = [
  {
    id: 'order-9001',
    status: 'SUBMITTED',
    totalCents: 12900,
    itemCount: 1,
    afterSalesAvailable: true
  },
  {
    id: 'order-9002',
    status: 'AFTER_SALES',
    totalCents: 9900,
    itemCount: 2,
    afterSalesAvailable: false
  }
]

export function fetchOrderList(status?: OrderSummary['status']) {
  return request<OrderSummary[]>({
    endpoint: '/mock/orders',
    mockData: status ? mockOrders.filter((order) => order.status === status) : mockOrders,
    retries: 1
  })
}

export function fetchOrderDetail(orderId: string) {
  return request<OrderSummary | undefined>({
    endpoint: `/mock/orders/${orderId}`,
    mockData: mockOrders.find((order) => order.id === orderId),
    retries: 1
  })
}
