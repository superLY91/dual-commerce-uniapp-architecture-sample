<template>
  <view class="order-card">
    <view>
      <text class="title">{{ order.id }}</text>
      <text class="meta">{{ order.status }} · {{ order.itemCount }} items</text>
    </view>
    <PriceText :cents="order.totalCents" />
  </view>
</template>

<script setup lang="ts">
import type { OrderSummary } from '../api/orderApi'
import PriceText from './PriceText.vue'

defineProps<{
  order: OrderSummary
}>()
</script>

<style scoped>
.order-card {
  display: flex;
  justify-content: space-between;
  gap: 12px;
  padding: 12px;
  border-bottom: 1px solid #eaecf0;
}

.title {
  display: block;
  font-weight: 600;
}

.meta {
  display: block;
  margin-top: 4px;
  color: #667085;
  font-size: 12px;
}
</style>
