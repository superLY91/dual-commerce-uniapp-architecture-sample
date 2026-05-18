<template>
  <view class="empty">
    <text class="title">{{ title }}</text>
    <text class="description">{{ description }}</text>
  </view>
</template>

<script setup lang="ts">
defineProps<{
  title: string
  description: string
}>()
</script>

<style scoped>
.empty {
  padding: 24px;
  text-align: center;
}

.title {
  display: block;
  font-weight: 600;
}

.description {
  display: block;
  margin-top: 8px;
  color: #667085;
}
</style>
