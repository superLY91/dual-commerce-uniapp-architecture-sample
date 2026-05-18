import type { MerchantDashboard } from '../api/merchantApi'
import { fetchMerchantDashboard } from '../api/merchantApi'

export interface MerchantState {
  dashboard?: MerchantDashboard
  loading: boolean
}

export const merchantStore: MerchantState = {
  dashboard: undefined,
  loading: false
}

export async function loadMerchantDashboard() {
  merchantStore.loading = true
  const result = await fetchMerchantDashboard()
  merchantStore.dashboard = result.data
  merchantStore.loading = false
  return merchantStore
}
