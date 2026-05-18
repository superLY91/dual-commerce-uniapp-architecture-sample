<template>
  <view class="page">
    <text class="page-title">Merchant Order Filter</text>
    <OrderCard v-for="order in orders" :key="order.id" :order="order" />
    <EmptyView v-if="orders.length === 0" title="No orders" description="Mock order filter returned no data." />
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import OrderCard from '../../components/OrderCard.vue'
import EmptyView from '../../components/EmptyView.vue'
import type { OrderSummary } from '../../api/orderApi'
import { fetchOrderList } from '../../api/orderApi'
import { trackEvent } from '../../utils/track'

const orders = ref<OrderSummary[]>([])

async function loadOrders() {
  const result = await fetchOrderList()
  orders.value = result.data ?? []
  await trackEvent('uni_order_filter_viewed', { role: 'merchant' })
}

loadOrders()
</script>

<style scoped>
.page {
  padding: 16px;
}

.page-title {
  display: block;
  margin-bottom: 12px;
  font-size: 18px;
  font-weight: 700;
}
</style>
