<template>
  <view class="product-card">
    <view>
      <text class="title">{{ product.title }}</text>
      <text class="tags">{{ product.marketingTags.join(' / ') }}</text>
    </view>
    <PriceText :cents="product.priceCents" />
  </view>
</template>

<script setup lang="ts">
import type { ProductSummary } from '../api/productApi'
import PriceText from './PriceText.vue'

defineProps<{
  product: ProductSummary
}>()
</script>

<style scoped>
.product-card {
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

.tags {
  display: block;
  margin-top: 4px;
  color: #667085;
  font-size: 12px;
}
</style>
