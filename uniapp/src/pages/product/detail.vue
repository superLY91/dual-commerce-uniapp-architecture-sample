<template>
  <view class="page">
    <text class="page-title">{{ product?.title ?? 'Product Detail' }}</text>
    <PriceText v-if="product" :cents="product.priceCents" />
    <text class="note">Core buy actions stay in Android Native.</text>
    <button @click="openNativeDetail">Open Native Detail</button>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import PriceText from '../../components/PriceText.vue'
import type { ProductSummary } from '../../api/productApi'
import { fetchProductDetail } from '../../api/productApi'
import { nativeBridge } from '../../bridge/nativeBridge'

const product = ref<ProductSummary>()

async function loadDetail() {
  const result = await fetchProductDetail('p-1001')
  product.value = result.data
}

function openNativeDetail() {
  return nativeBridge.openNativePage('PRODUCT_DETAIL', { productId: product.value?.id ?? 'p-1001' })
}

loadDetail()
</script>

<style scoped>
.page {
  padding: 16px;
}

.page-title,
.note {
  display: block;
  margin-bottom: 12px;
}
</style>
