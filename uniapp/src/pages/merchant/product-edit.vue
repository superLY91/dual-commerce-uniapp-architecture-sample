<template>
  <view class="page">
    <text class="page-title">Light Product Edit</text>
    <input v-model="title" placeholder="Mock product title" />
    <textarea v-model="sellingPoint" placeholder="Mock selling point" />
    <text class="note">Save is a mock draft action. Core product trade data stays Native-controlled.</text>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { fetchMerchantProductDraft } from '../../api/merchantApi'

const title = ref('')
const sellingPoint = ref('')

async function loadDraft() {
  const result = await fetchMerchantProductDraft('p-1001')
  title.value = result.data?.title ?? ''
  sellingPoint.value = result.data?.sellingPoint ?? ''
}

loadDraft()
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

.note {
  color: #667085;
}
</style>
