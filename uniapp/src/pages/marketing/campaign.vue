<template>
  <view class="page">
    <text class="page-title">Campaign Page</text>
    <text class="note">Configurable marketing content with mock products.</text>
    <ProductCard v-for="product in products" :key="product.id" :product="product" />
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import ProductCard from '../../components/ProductCard.vue'
import type { ProductSummary } from '../../api/productApi'
import { fetchProductList } from '../../api/productApi'
import { trackEvent } from '../../utils/track'

const products = ref<ProductSummary[]>([])

async function loadCampaign() {
  const result = await fetchProductList()
  products.value = result.data ?? []
  await trackEvent('uni_campaign_viewed', { campaignId: 'mock-campaign-1' })
}

loadCampaign()
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

.page-title {
  font-size: 18px;
  font-weight: 700;
}
</style>
