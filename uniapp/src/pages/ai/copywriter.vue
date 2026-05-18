<template>
  <view class="page">
    <text class="page-title">AI Copywriter</text>
    <input v-model="productTitle" placeholder="Product title" />
    <textarea v-model="sellingPoint" placeholder="Selling points" />
    <button @click="generate">Generate Mock Copy</button>
    <view v-for="candidate in candidates" :key="candidate" class="candidate">
      <text>{{ candidate }}</text>
    </view>
    <text class="risk">AI output requires human confirmation before publishing.</text>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { generateCopywriting } from '../../api/aiApi'
import { trackEvent } from '../../utils/track'

const productTitle = ref('Campaign Product')
const sellingPoint = ref('Fast delivery, seasonal promotion')
const candidates = ref<string[]>([])

async function generate() {
  const result = await generateCopywriting({
    productTitle: productTitle.value,
    sellingPoints: sellingPoint.value.split(',').map((item) => item.trim()),
    scenario: 'MARKETING_COPY'
  })
  candidates.value = result.data?.candidates ?? []
  await trackEvent('uni_ai_copy_generated', { scenario: 'MARKETING_COPY' })
}
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

.candidate {
  padding: 12px;
  border: 1px solid #d0d5dd;
}

.risk {
  color: #b42318;
}
</style>
