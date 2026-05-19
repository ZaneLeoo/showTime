<template>
  <div class="page-container py-8">
    <!-- 分类标签 -->
    <div class="flex flex-wrap gap-3 mb-10">
      <button
        v-for="cat in categories"
        :key="cat.id"
        @click="activeCategory = cat.id"
        class="px-5 py-2 rounded-pill text-sm font-medium transition-all duration-200"
        :class="activeCategory === cat.id
          ? 'bg-primary-500 text-white shadow-lg shadow-primary-200'
          : 'bg-white text-surface-muted hover:text-surface-dark hover:bg-surface-light border border-surface-border'"
      >
        {{ cat.name }}
      </button>
    </div>

    <!-- 热门推荐标题 -->
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-xl font-bold text-surface-dark">
        {{ activeCategoryLabel }} 热门推荐
      </h2>
      <span class="text-sm text-surface-muted">{{ events.length }} 个演出</span>
    </div>

    <!-- 演出卡片网格 -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
      <article
        v-for="event in events"
        :key="event.id"
        class="card cursor-pointer group"
      >
        <!-- 海报 -->
        <div class="relative aspect-[3/4] rounded-t-card overflow-hidden bg-surface-light">
          <img
            :src="event.posterUrl"
            :alt="event.title"
            class="w-full h-full object-cover transition-transform duration-500 group-hover:scale-105"
          />
          <!-- 状态标签 -->
          <span
            class="absolute top-3 left-3 px-3 py-1 rounded-pill text-xs font-medium"
            :class="statusClass(event.status)"
          >
            {{ statusText(event.status) }}
          </span>
          <!-- 票价浮层 -->
          <div class="absolute bottom-0 inset-x-0 bg-gradient-to-t from-black/60 to-transparent p-4">
            <span class="text-white font-bold text-lg">¥{{ event.minPrice }}<span class="text-sm font-normal text-white/70"> 起</span></span>
          </div>
        </div>

        <!-- 信息 -->
        <div class="p-4">
          <h3 class="font-semibold text-surface-dark line-clamp-2 leading-snug mb-2">
            {{ event.title }}
          </h3>
          <p class="text-sm text-surface-muted mb-1">{{ event.venueName }} · {{ event.city }}</p>
          <p class="text-xs text-surface-muted">{{ event.earliestTime }}</p>
        </div>
      </article>
    </div>

    <!-- 空状态 -->
    <div v-if="events.length === 0" class="text-center py-20">
      <div class="text-5xl mb-4">🎬</div>
      <p class="text-surface-muted">暂无演出</p>
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

const statusClass = (s: number) => {
  switch (s) {
    case 1: return 'bg-mint-400 text-white'
    case 2: return 'bg-surface-muted text-white'
    case 3: return 'bg-surface-muted/80 text-white'
    default: return 'bg-primary-400 text-white'
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
