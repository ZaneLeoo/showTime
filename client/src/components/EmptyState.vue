<template>
  <div class="flex flex-col items-center justify-center py-20 text-center">
    <div class="w-20 h-20 rounded-full bg-white/[0.03] border border-white/[0.05] flex items-center justify-center mb-5">
      <svg class="w-8 h-8 text-white/15" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
          d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4" />
      </svg>
    </div>
    <p class="text-white/30 text-sm">{{ text }}</p>
    <p v-if="subText" class="text-white/15 text-xs mt-1">{{ subText }}</p>
  </div>
</template>

<script setup lang="ts">
defineProps<{ text: string; subText?: string }>()
</script>
