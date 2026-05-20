<template>
  <div class="flex items-center justify-center py-20">
    <div class="flex flex-col items-center gap-4">
      <div class="relative w-10 h-10">
        <div class="absolute inset-0 rounded-full border-2 border-white/[0.06]"></div>
        <div class="absolute inset-0 rounded-full border-2 border-transparent border-t-brand-400 animate-spin"></div>
      </div>
      <span class="text-sm text-white/30">{{ text }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
withDefaults(defineProps<{ text?: string }>(), { text: '加载中...' })
</script>
