<template>
  <div class="page-container py-8">
    <!-- 分类标签 -->
    <div class="flex flex-wrap gap-3 mb-10">
      <button
        v-for="cat in categories"
        :key="cat.id"
        @click="activeCategory = cat.id"
        :style="activeCategory === cat.id
          ? 'background: #FF6B6B; color: #fff; box-shadow: 0 4px 12px rgba(255,107,107,0.3);'
          : 'background: #fff; color: #636E72; border: 1px solid #E8ECF0;'"
        class="px-5 py-2 rounded-pill text-sm font-medium transition-all duration-200 hover:-translate-y-0.5"
      >
        {{ cat.name }}
      </button>
    </div>

    <!-- 热门推荐标题 -->
    <div class="flex items-center justify-between mb-6">
      <h2 style="font-size: 20px; font-weight: 700; color: #2D3436;">
        {{ activeCategoryLabel }} 热门推荐
      </h2>
      <span style="color: #636E72; font-size: 14px;">{{ events.length }} 个演出</span>
    </div>

    <!-- 演出卡片网格 -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
      <article
        v-for="event in events"
        :key="event.id"
        class="card cursor-pointer group"
      >
        <!-- 海报 -->
        <div class="relative aspect-[3/4] rounded-t-card overflow-hidden" style="background-color: #F7F9FC;">
          <img
            :src="event.posterUrl"
            :alt="event.title"
            class="w-full h-full object-cover transition-transform duration-500 group-hover:scale-105"
          />
          <!-- 状态标签 -->
          <span
            class="absolute top-3 left-3 px-3 py-1 rounded-pill text-xs font-medium text-white"
            :style="{ backgroundColor: statusColor(event.status) }"
          >
            {{ statusText(event.status) }}
          </span>
          <!-- 票价浮层 -->
          <div class="absolute bottom-0 inset-x-0 p-4" style="background: linear-gradient(to top, rgba(0,0,0,0.6), transparent);">
            <span style="color: #fff; font-size: 18px; font-weight: 700;">
              ¥{{ event.minPrice }}<span style="font-size: 14px; font-weight: 400; color: rgba(255,255,255,0.7);"> 起</span>
            </span>
          </div>
        </div>

        <!-- 信息 -->
        <div class="p-4">
          <h3 style="font-weight: 600; color: #2D3436; line-height: 1.4;" class="line-clamp-2 mb-2">
            {{ event.title }}
          </h3>
          <p style="color: #636E72; font-size: 14px; margin-bottom: 2px;">{{ event.venueName }} · {{ event.city }}</p>
          <p style="color: #636E72; font-size: 12px;">{{ event.earliestTime }}</p>
        </div>
      </article>
    </div>

    <!-- 空状态 -->
    <div v-if="events.length === 0" class="text-center py-20">
      <div class="text-5xl mb-4">🎬</div>
      <p style="color: #636E72;">暂无演出</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { getEventList } from '@/api/event'
import type { EventCard } from '@/types/common'
import { onMounted } from 'vue'

const categories = [
  { id: 0, name: '全部' },
  { id: 1, name: '演唱会' },
  { id: 2, name: '音乐节' },
  { id: 3, name: '话剧' },
  { id: 4, name: '体育赛事' },
  { id: 5, name: '相声' },
  { id: 6, name: '展览' },
]

const activeCategory = ref(0)
const events = ref<EventCard[]>([])

const activeCategoryLabel = computed(() => {
  const cat = categories.find(c => c.id === activeCategory.value)
  return cat ? cat.name : '全部'
})

const statusColor = (s: number) => {
  switch (s) {
    case 1: return '#4ECDC4'
    case 2: return '#636E72'
    case 3: return '#999'
    default: return '#FF6B6B'
  }
}
const statusText = (s: number) => {
  switch (s) {
    case 0: return '即将开售'
    case 1: return '在售中'
    case 2: return '已售罄'
    case 3: return '已结束'
    default: return ''
  }
}

async function fetchEvents() {
  try {
    const data = await getEventList({
      categoryId: activeCategory.value || undefined,
      page: 1,
      pageSize: 20,
    })
    events.value = data.records || []
  } catch {
    events.value = []
  }
}

onMounted(fetchEvents)
</script>
