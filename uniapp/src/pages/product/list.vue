<template>
  <view class="page">
    <text class="page-title">Product Campaign List</text>
    <ProductCard v-for="product in products" :key="product.id" :product="product" />
    <EmptyView v-if="products.length === 0" title="No products" description="Mock product data is empty." />
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import ProductCard from '../../components/ProductCard.vue'
import EmptyView from '../../components/EmptyView.vue'
import type { ProductSummary } from '../../api/productApi'
import { fetchProductList } from '../../api/productApi'
import { trackEvent } from '../../utils/track'

const products = ref<ProductSummary[]>([])

async function loadProducts() {
  const result = await fetchProductList()
  products.value = result.data ?? []
  await trackEvent('uni_product_list_viewed', { source: 'campaign-list' })
}

loadProducts()
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
