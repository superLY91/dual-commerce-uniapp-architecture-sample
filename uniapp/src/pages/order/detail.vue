<template>
  <view class="page">
    <text class="page-title">{{ order?.id ?? 'Order Detail' }}</text>
    <text class="meta">Status: {{ order?.status }}</text>
    <text class="note">Order submission and payment are Native-owned.</text>
    <button @click="openNativeOrder">Open Native Order</button>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { OrderSummary } from '../../api/orderApi'
import { fetchOrderDetail } from '../../api/orderApi'
import { nativeBridge } from '../../bridge/nativeBridge'

const order = ref<OrderSummary>()

async function loadOrder() {
  const result = await fetchOrderDetail('order-9001')
  order.value = result.data
}

function openNativeOrder() {
  return nativeBridge.openNativePage('ORDER_DETAIL', { orderId: order.value?.id ?? 'order-9001' })
}

loadOrder()
</script>

<style scoped>
.page {
  padding: 16px;
}

.page-title,
.meta,
.note {
  display: block;
  margin-bottom: 12px;
}
</style>
