export interface CartState {
  selectedCount: number
  estimatedTotalCents: number
  checkoutOwnedBy: 'ANDROID_NATIVE'
}

export const cartStore: CartState = {
  selectedCount: 0,
  estimatedTotalCents: 0,
  checkoutOwnedBy: 'ANDROID_NATIVE'
}

export function updateCartPreview(selectedCount: number, estimatedTotalCents: number) {
  cartStore.selectedCount = selectedCount
  cartStore.estimatedTotalCents = estimatedTotalCents
}
