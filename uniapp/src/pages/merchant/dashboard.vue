<template>
  <view class="page">
    <text class="page-title">Merchant Dashboard</text>
    <text>Pending orders: {{ dashboard?.pendingOrders ?? 0 }}</text>
    <text>Low inventory: {{ dashboard?.lowInventoryProducts ?? 0 }}</text>
    <text>Campaign drafts: {{ dashboard?.campaignDrafts ?? 0 }}</text>
  </view>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { loadMerchantDashboard, merchantStore } from '../../stores/merchantStore'
import { trackEvent } from '../../utils/track'

const dashboard = computed(() => merchantStore.dashboard)

async function load() {
  await loadMerchantDashboard()
  await trackEvent('uni_merchant_dashboard_viewed', { merchantId: dashboard.value?.merchantId ?? 'unknown' })
}

load()
</script>

<style scoped>
.page {
  display: grid;
  gap: 12px;
  padding: 16px;
}

.page-title {
  font-size: 18px;
  font-weight: 700;
}
</style>
