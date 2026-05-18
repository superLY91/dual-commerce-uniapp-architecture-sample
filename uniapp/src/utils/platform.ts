export type RuntimePlatform = 'android-native-container' | 'h5-preview' | 'unknown'

export function getRuntimePlatform(): RuntimePlatform {
  // Mock platform detection. Real Uniapp runtime checks should stay behind this helper.
  if (typeof window !== 'undefined') {
    return 'h5-preview'
  }
  return 'android-native-container'
}

export function isNativeContainer(): boolean {
  return getRuntimePlatform() === 'android-native-container'
}
