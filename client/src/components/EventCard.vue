<template>
  <router-link
    :to="`/event/${event.id}`"
    class="group relative block"
  >
    <!-- 卡片 -->
    <div
      class="relative overflow-hidden rounded-[30px]
             bg-[#0f1014]
             border border-white/10
             shadow-[0_12px_50px_rgba(0,0,0,0.45)]
             transition-all duration-700 ease-out
             hover:-translate-y-2
             hover:scale-[1.015]
             hover:shadow-[0_30px_90px_rgba(168,50,74,0.2)]"
    >

      <!-- 海报 -->
      <div class="relative aspect-[0.68] overflow-hidden">

        <!-- 图片 -->
        <img
          :src="event.posterUrl || '/img/placeholder.svg'"
          :alt="event.title"
          class="w-full h-full object-cover
                 transition-all duration-[1400ms] ease-out
                 group-hover:scale-110
                 group-hover:rotate-[0.5deg]"
          @error="onImgError"
        />

        <!-- 顶部渐变 -->
        <div
          class="absolute inset-x-0 top-0 h-36
                 bg-gradient-to-b
                 from-black/85
                 via-black/30
                 to-transparent"
        />

        <!-- 底部渐变 -->
        <div
          class="absolute inset-x-0 bottom-0 h-[45%]
                 bg-gradient-to-t
                 from-black
                 via-black/65
                 to-transparent"
        />

        <!-- 氛围光 -->
        <div
          class="absolute -bottom-16 left-1/2 -translate-x-1/2
                 w-64 h-64 rounded-full
                 blur-3xl
                 opacity-70
                 transition-all duration-700
                 group-hover:scale-125
                 group-hover:opacity-100"
          :class="glowClass"
        />

        <!-- 扫光 -->
        <div class="absolute inset-0 overflow-hidden pointer-events-none">
          <div
            class="absolute top-0 -left-[130%]
                   h-full w-[70%]
                   skew-x-[-20deg]
                   bg-gradient-to-r
                   from-transparent
                   via-white/10
                   to-transparent
                   transition-all duration-1000
                   group-hover:left-[140%]"
          />
        </div>

        <!-- 微纹理 -->
        <div
          class="absolute inset-0 opacity-[0.03]
                 mix-blend-soft-light
                 bg-[radial-gradient(circle_at_1px_1px,white_1px,transparent_0)]
                 bg-[size:18px_18px]"
        />

        <!-- 内描边 -->
        <div
          class="absolute inset-[1px]
                 rounded-[29px]
                 border border-white/5
                 pointer-events-none"
        />

        <!-- 顶部信息 -->
        <div
          class="absolute top-4 left-4 right-4
                 flex items-start justify-between"
        >

          <div class="flex items-center gap-2 min-w-0">

            <!-- 分类 -->
            <span
              class="truncate px-3 py-1.5
                     rounded-full
                     border border-white/10
                     bg-black/35
                     backdrop-blur-xl
                     text-[11px]
                     font-medium
                     tracking-wide
                     text-white/80"
            >
              {{ event.categoryName }}
            </span>

            <!-- 状态 -->
            <span
              :class="statusBadgeClass"
              class="shrink-0 px-3 py-1.5
                     rounded-full
                     border
                     backdrop-blur-xl
                     text-[11px]
                     font-semibold
                     tracking-wide"
            >
              {{ statusText }}
            </span>

          </div>

          <!-- LIVE 标签 -->
          <div
            class="text-[10px]
                   uppercase
                   tracking-[0.35em]
                   text-white/35
                   mt-1"
          >
            LIVE TOUR
          </div>

        </div>

        <!-- 底部信息 -->
        <div
          class="absolute inset-x-0 bottom-0
                 px-5 pb-5 pt-16"
        >

          <div
            class="relative z-10
                   transition-all duration-500
                   opacity-95
                   translate-y-1
                   group-hover:opacity-100
                   group-hover:translate-y-0"
          >

            <!-- 标题 -->
            <h3
              class="font-display
                     text-[22px]
                     leading-tight
                     font-black
                     tracking-[0.01em]
                     text-white/92
                     line-clamp-1"
            >
              {{ event.title }}
            </h3>

            <!-- 副信息 -->
            <div
              class="mt-4
                     flex items-end justify-between"
            >

              <!-- 价格 -->
              <div class="flex items-end gap-1">

                <span
                  class="text-xs
                         text-white/45
                         mb-[4px]"
                >
                  ¥
                </span>

                <span
                  class="text-[34px]
                         leading-none
                         font-black
                         tracking-tight
                         text-accent-400"
                >
                  {{ event.minPrice }}
                </span>

                <span
                  class="text-sm
                         text-white/35
                         mb-[3px]"
                >
                  起
                </span>

              </div>

              <!-- 时间 -->
              <div class="text-right">

                <div
                  class="text-[10px]
                         uppercase
                         tracking-[0.32em]
                         text-white/25"
                >
                  SHOW TIME
                </div>

                <div
                  class="mt-1
                         text-sm
                         font-medium
                         text-white/72"
                >
                  {{ formatTime(event.earliestSessionTime) }}
                </div>

              </div>

            </div>

          </div>

        </div>

      </div>
    </div>
  </router-link>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import type { EventCard as EventCardType } from '@/types/common'

const props = defineProps<{
  event: EventCardType
}>()

const statusMap: Record<number, { text: string; class: string }> = {
  0: {
    text: '即将开售',
    class: 'bg-blue-500/15 border-blue-400/30 text-blue-200'
  },
  1: {
    text: '热售中',
    class: 'bg-accent-500/15 border-accent-400/30 text-accent-200'
  },
  2: {
    text: '已售罄',
    class: 'bg-brand-500/15 border-brand-400/30 text-brand-200'
  },
  3: {
    text: '已结束',
    class: 'bg-white/5 border-white/10 text-white/40'
  }
}

const statusText = computed(() => {
  return statusMap[props.event.status]?.text || '未知'
})

const statusBadgeClass = computed(() => {
  return statusMap[props.event.status]?.class || ''
})

const glowClass = computed(() => {
  if (props.event.status === 2) return 'bg-brand-500/15'
  if (props.event.status === 3) return 'bg-white/[0.02]'
  return 'bg-accent-500/20'
})

function onImgError(e: Event) {
  const img = e.target as HTMLImageElement

  img.src =
    'data:image/svg+xml,' +
    encodeURIComponent(`
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 400">
        <defs>
          <linearGradient id="g" x1="0" x2="1" y1="0" y2="1">
            <stop stop-color="#111214"/>
            <stop offset="1" stop-color="#1a1b20"/>
          </linearGradient>
        </defs>

        <rect fill="url(#g)" width="300" height="400"/>

        <text
          fill="rgba(255,255,255,0.16)"
          font-size="16"
          font-family="Arial"
          text-anchor="middle"
          x="150"
          y="200"
          letter-spacing="4"
        >
          LIVE EVENT
        </text>
      </svg>
    `)
}

function formatTime(time: string) {
  if (!time) return ''

  const d = new Date(time)

  return `${d.getMonth() + 1}月${d.getDate()}日`
}
</script>
