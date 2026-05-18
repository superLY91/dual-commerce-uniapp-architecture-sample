import { nativeBridge } from '../bridge/nativeBridge'

export interface UserState {
  token: string
  userType: 'GUEST' | 'MOCK_CONSUMER' | 'MOCK_MERCHANT'
}

export const userStore: UserState = {
  token: '',
  userType: 'GUEST'
}

export async function loadMockSession() {
  const result = await nativeBridge.getToken()
  if (result.ok) {
    userStore.token = result.data.token ?? 'mock-uniapp-token'
    userStore.userType = (result.data.userType as UserState['userType']) ?? 'GUEST'
  }
  return userStore
}
