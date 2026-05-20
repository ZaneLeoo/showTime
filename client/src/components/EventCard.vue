<template>
  <router-link
    :to="`/event/${event.id}`"
    class="group block relative overflow-hidden rounded-2xl
           shadow-lg shadow-black/40
           transition-all duration-500 ease-out
           hover:scale-[1.03] hover:-translate-y-1
           hover:shadow-2xl hover:shadow-brand-900/20"
  >
    <!-- 海报 -->
    <div class="aspect-[3/4] w-full bg-surface-900">
      <img
        :src="event.posterUrl || '/img/placeholder.svg'"
        :alt="event.title"
        class="w-full h-full object-cover transition-transform duration-700 ease-out
               group-hover:scale-110"
        @error="onImgError"
      />
    </div>

    <!-- 顶部渐变 (徽章衬底) -->
    <div class="absolute top-0 inset-x-0 h-20 bg-gradient-to-b from-black/50 to-transparent pointer-events-none"></div>

    <!-- 底部渐变 (文字衬底) -->
    <div class="absolute bottom-0 inset-x-0 h-2/5 bg-gradient-to-t from-black/95 via-black/40 to-transparent pointer-events-none"></div>

    <!-- 顶部徽章 -->
    <div class="absolute top-3 left-3 right-3 flex items-center gap-2">
      <span class="px-2.5 py-1 text-[11px] font-medium rounded-full
                   bg-black/50 backdrop-blur-sm border border-white/10 text-white/80 truncate">
        {{ event.categoryName }}
      </span>
      <span :class="statusBadgeClass"
            class="shrink-0 px-2.5 py-1 text-[11px] font-medium rounded-full backdrop-blur-sm border">
        {{ statusText }}
      </span>
    </div>

    <!-- 底部信息 -->
    <div class="absolute bottom-0 left-0 right-0 p-4 pt-10">
      <h3 class="font-display text-base font-semibold text-white/95 line-clamp-1
                 group-hover:text-white transition-colors">
        {{ event.title }}
      </h3>
      <div class="flex items-end justify-between mt-2">
        <div class="flex items-baseline gap-0.5">
          <span class="text-xs text-white/40">¥</span>
          <span class="text-lg font-bold text-accent-400">{{ event.minPrice }}</span>
          <span class="text-xs text-white/40 ml-0.5">起</span>
        </div>
        <span class="text-xs text-white/45">{{ formatTime(event.earliestSessionTime) }}</span>
      </div>
    </div>
  </router-link>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import type { EventCard as EventCardType } from '@/types/common'

const props = defineProps<{ event: EventCardType }>()

const statusMap: Record<number, { text: string; class: string }> = {
  0: { text: '即将开售', class: 'bg-blue-500/20 border-blue-400/30 text-blue-300' },
  1: { text: '在售中',   class: 'bg-accent-500/20 border-accent-400/30 text-accent-300' },
  2: { text: '已售罄',   class: 'bg-brand-500/20 border-brand-400/30 text-brand-300' },
  3: { text: '已结束',   class: 'bg-white/5 border-white/10 text-white/40' },
}

const statusText = computed(() => statusMap[props.event.status]?.text || '未知')
const statusBadgeClass = computed(() => statusMap[props.event.status]?.class || '')

function onImgError(e: Event) {
  const img = e.target as HTMLImageElement
  img.src = 'data:image/svg+xml,' + encodeURIComponent(`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 400">
    <rect fill="#1a1510" width="300" height="400"/>
    <text fill="#ffffff12" font-size="14" text-anchor="middle" x="150" y="200">暂无图片</text>
  </svg>`)
}

function formatTime(time: string) {
  if (!time) return ''
  const d = new Date(time)
  return `${d.getMonth() + 1}月${d.getDate()}日`
}
</script>
