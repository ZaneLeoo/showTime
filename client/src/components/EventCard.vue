<template>
  <router-link :to="`/event/${event.id}`" class="card group block overflow-hidden">
    <!-- 海报 -->
    <div class="relative aspect-[4/3] overflow-hidden">
      <img
        :src="event.posterUrl || '/img/placeholder.svg'"
        :alt="event.title"
        class="w-full h-full object-cover transition-transform duration-500 group-hover:scale-110"
        @error="onImgError"
      />
      <!-- 遮罩渐变 -->
      <div class="absolute inset-0 bg-gradient-to-t from-surface-900/90 via-transparent to-transparent"></div>

      <!-- 分类标签 -->
      <div class="absolute top-3 left-3">
        <span class="px-2.5 py-1 text-xs font-medium rounded-full
                     bg-white/10 backdrop-blur-sm border border-white/10 text-white/80">
          {{ event.categoryName }}
        </span>
      </div>

      <!-- 状态标签 -->
      <div class="absolute top-3 right-3">
        <span :class="statusBadgeClass" class="px-2.5 py-1 text-xs font-medium rounded-full backdrop-blur-sm border">
          {{ statusText }}
        </span>
      </div>

      <!-- 底部信息 -->
      <div class="absolute bottom-3 left-3 right-3">
        <div class="flex items-center gap-2 text-xs text-white/70">
          <svg class="w-3.5 h-3.5 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
          </svg>
          <span>{{ event.venueName }}</span>
          <span class="text-white/30">·</span>
          <span>{{ event.city }}</span>
        </div>
      </div>
    </div>

    <!-- 信息区 -->
    <div class="p-4">
      <h3 class="text-base font-semibold text-white/90 line-clamp-1 group-hover:text-white transition-colors">
        {{ event.title }}
      </h3>

      <div class="mt-3 flex items-end justify-between">
        <div class="flex items-baseline gap-1">
          <span class="text-xs text-white/40">¥</span>
          <span class="text-lg font-bold text-accent-400">{{ event.minPrice }}</span>
          <span class="text-xs text-white/40 ml-1">起</span>
        </div>
        <div class="text-xs text-white/35">
          {{ formatTime(event.earliestSessionTime) }}
        </div>
      </div>
    </div>
  </router-link>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import type { EventCard as EventCardType } from '@/types/common'

const props = defineProps<{ event: EventCardType }>()
const imgFailed = ref(false)

const statusMap: Record<number, { text: string; class: string }> = {
  0: { text: '即将开售', class: 'bg-blue-500/10 border-blue-400/20 text-blue-300' },
  1: { text: '在售中', class: 'bg-green-500/10 border-green-400/20 text-green-300' },
  2: { text: '已售罄', class: 'bg-red-500/10 border-red-400/20 text-red-300' },
  3: { text: '已结束', class: 'bg-white/5 border-white/10 text-white/40' },
}

const statusText = computed(() => statusMap[props.event.status]?.text || '未知')
const statusBadgeClass = computed(() => statusMap[props.event.status]?.class || '')

function onImgError(e: Event) {
  const img = e.target as HTMLImageElement
  img.src = 'data:image/svg+xml,' + encodeURIComponent(`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 400 300">
    <rect fill="#1a1f2e" width="400" height="300"/>
    <text fill="#ffffff20" font-size="14" text-anchor="middle" x="200" y="150">暂无图片</text>
  </svg>`)
}

function formatTime(time: string) {
  if (!time) return ''
  const d = new Date(time)
  return `${d.getMonth() + 1}月${d.getDate()}日`
}
</script>
