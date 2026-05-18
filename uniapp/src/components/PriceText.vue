<template>
  <text class="price">{{ formattedPrice }}</text>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
  cents: number
}>()

const formattedPrice = computed(() => `$${(props.cents / 100).toFixed(2)}`)
</script>

<style scoped>
.price {
  color: #b42318;
  font-weight: 600;
}
</style>
