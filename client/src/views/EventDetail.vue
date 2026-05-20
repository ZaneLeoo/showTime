<template>
  <div class="page-enter-active">
    <LoadingSpinner v-if="loading" />

    <template v-else-if="event">
      <!-- 顶部横幅 -->
      <section class="relative overflow-hidden">
        <div class="mx-auto max-w-7xl px-6 py-10 md:py-16">
          <div class="flex flex-col md:flex-row gap-8 md:gap-12">
            <!-- 海报 -->
            <div class="shrink-0">
              <div class="w-48 md:w-64 aspect-[3/4] rounded-2xl overflow-hidden shadow-glow-lg
                          border border-white/[0.08]">
                <img
                  :src="event.posterUrl || '/img/placeholder.svg'"
                  :alt="event.title"
                  class="w-full h-full object-cover"
                  @error="onImgError"
                />
              </div>
            </div>

            <!-- 信息 -->
            <div class="flex flex-col justify-center min-w-0">
              <div class="flex items-center gap-2 mb-3">
                <span class="px-2.5 py-1 text-xs rounded-full bg-brand-500/15 text-brand-300
                             border border-brand-400/20">
                  {{ event.categoryName }}
                </span>
                <span :class="statusBadgeClass" class="px-2.5 py-1 text-xs rounded-full border">
                  {{ statusText }}
                </span>
              </div>

              <h1 class="font-display text-3xl md:text-4xl font-bold">{{ event.title }}</h1>

              <p class="mt-4 text-white/45 leading-relaxed max-w-xl line-clamp-3">
                {{ event.description || '暂无简介' }}
              </p>

              <div class="mt-6 grid grid-cols-2 sm:grid-cols-4 gap-4">
                <div class="glass-panel p-3 text-center">
                  <div class="text-xs text-white/35 mb-1">演出时长</div>
                  <div class="text-sm font-semibold">{{ event.duration }}分钟</div>
                </div>
                <div class="glass-panel p-3 text-center">
                  <div class="text-xs text-white/35 mb-1">场馆</div>
                  <div class="text-sm font-semibold">{{ event.venue.name }}</div>
                </div>
                <div class="glass-panel p-3 text-center">
                  <div class="text-xs text-white/35 mb-1">城市</div>
                  <div class="text-sm font-semibold">{{ event.venue.city }}</div>
                </div>
                <div class="glass-panel p-3 text-center">
                  <div class="text-xs text-white/35 mb-1">地址</div>
                  <div class="text-sm font-semibold truncate">{{ event.venue.address }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 背景装饰 -->
        <div class="absolute top-0 right-0 w-96 h-96 bg-brand-500/[0.04] rounded-full blur-3xl pointer-events-none"></div>
      </section>

      <!-- 场次 & 票档 -->
      <section class="mx-auto max-w-7xl px-6 pb-16">
        <h2 class="font-display text-2xl font-semibold mb-6">选择场次</h2>

        <div class="space-y-6">
          <div
            v-for="session in event.sessions"
            :key="session.id"
            class="card overflow-hidden"
          >
            <div class="p-6">
              <!-- 场次时间 -->
              <div class="flex items-center justify-between mb-4">
                <div class="flex items-center gap-3">
                  <div class="w-10 h-10 rounded-xl bg-brand-500/10 flex items-center justify-center">
                    <svg class="w-5 h-5 text-brand-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                        d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                    </svg>
                  </div>
                  <div>
                    <div class="font-semibold">{{ formatDateTime(session.sessionTime) }}</div>
                    <div class="text-xs text-white/35">{{ dayOfWeek(session.sessionTime) }}</div>
                  </div>
                </div>
                <span v-if="session.status === 1" class="text-xs text-green-400">可购</span>
                <span v-else class="text-xs text-white/30">已结束</span>
              </div>

              <!-- 票档列表 -->
              <div v-if="session.zones && session.zones.length > 0" class="grid grid-cols-2 md:grid-cols-4 gap-3">
                <div
                  v-for="zone in session.zones"
                  :key="zone.name"
                  class="relative p-3 rounded-xl border border-white/[0.06] bg-white/[0.02]
                         hover:border-brand-400/20 hover:bg-white/[0.04] transition-all duration-200"
                >
                  <div class="text-xs text-white/40 mb-1">{{ zone.name }}</div>
                  <div class="text-lg font-bold text-accent-400">
                    ¥{{ zone.price }}
                  </div>
                  <div class="text-xs text-white/25 mt-1">
                    {{ zone.availableSeats }}/{{ zone.totalSeats }} 座可选
                  </div>
                  <div v-if="zone.availableSeats === 0" class="absolute inset-0 bg-surface-950/50 rounded-xl
                              flex items-center justify-center text-xs text-white/30">
                    已售罄
                  </div>
                </div>
              </div>

              <!-- 操作 -->
              <div class="mt-5 flex justify-end">
                <router-link
                  v-if="session.status === 1"
                  :to="`/event/${event.id}/seat?sessionId=${session.id}`"
                  class="btn-brand text-sm !px-6 !py-2.5"
                >
                  去选座
                </router-link>
                <span v-else class="text-sm text-white/25">该场次已结束</span>
              </div>
            </div>
          </div>
        </div>
      </section>
    </template>

    <EmptyState v-else text="演出不存在" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { getEventDetail } from '@/api/event'
import type { EventDetail as EventDetailType } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const route = useRoute()
const event = ref<EventDetailType | null>(null)
const loading = ref(true)

const statusMap: Record<number, { text: string; class: string }> = {
  0: { text: '即将开售', class: 'bg-blue-500/10 border-blue-400/20 text-blue-300' },
  1: { text: '在售中', class: 'bg-green-500/10 border-green-400/20 text-green-300' },
  2: { text: '已售罄', class: 'bg-red-500/10 border-red-400/20 text-red-300' },
  3: { text: '已结束', class: 'bg-white/5 border-white/10 text-white/40' },
}

const statusText = computed(() => statusMap[event.value?.categoryId ? 1 : 0]?.text || '')
const statusBadgeClass = computed(() => statusMap[event.value?.categoryId ? 1 : 0]?.class || '')

async function fetchDetail() {
  loading.value = true
  const id = Number(route.params.id)
  try {
    event.value = await getEventDetail(id)
  } catch {
    event.value = getMockDetail(id)
  } finally {
    loading.value = false
  }
}

fetchDetail()

function formatDateTime(time: string) {
  if (!time) return ''
  const d = new Date(time)
  return `${d.getFullYear()}年${d.getMonth() + 1}月${d.getDate()}日 ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}`
}

function dayOfWeek(time: string) {
  const days = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
  return days[new Date(time).getDay()]
}

function onImgError(e: Event) {
  (e.target as HTMLImageElement).src = 'data:image/svg+xml,' + encodeURIComponent(
    `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 400"><rect fill="#1a1f2e" width="300" height="400"/><text fill="#ffffff20" font-size="14" text-anchor="middle" x="150" y="200">暂无图片</text></svg>`
  )
}

function getMockDetail(id: number): EventDetailType {
  return {
    id,
    title: '周杰伦2026巡回演唱会·北京站',
    description: '华语乐坛天王周杰伦2026年巡回演唱会北京站重磅来袭！经典曲目全新编曲，震撼舞台效果，带你重温那些年我们一起听过的周杰伦。',
    posterUrl: '',
    duration: 150,
    categoryId: 1,
    categoryName: '演唱会',
    venue: {
      id: 1,
      name: '鸟巢文化中心',
      city: '北京',
      address: '朝阳区国家体育场南路1号',
    },
    sessions: [
      {
        id: 1,
        sessionTime: new Date(Date.now() + 86400000 * 7).toISOString(),
        status: 1,
        zones: [
          { name: 'VIP区', price: 1680, totalSeats: 120, availableSeats: 45 },
          { name: 'A区', price: 1280, totalSeats: 200, availableSeats: 89 },
          { name: 'B区', price: 880, totalSeats: 300, availableSeats: 156 },
          { name: 'C区', price: 580, totalSeats: 280, availableSeats: 0 },
        ],
      },
      {
        id: 2,
        sessionTime: new Date(Date.now() + 86400000 * 8).toISOString(),
        status: 1,
        zones: [
          { name: 'VIP区', price: 1880, totalSeats: 120, availableSeats: 12 },
          { name: 'A区', price: 1380, totalSeats: 200, availableSeats: 67 },
          { name: 'B区', price: 980, totalSeats: 300, availableSeats: 134 },
          { name: 'C区', price: 680, totalSeats: 280, availableSeats: 200 },
        ],
      },
    ],
  }
}
</script>
