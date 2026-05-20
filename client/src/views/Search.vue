<template>
  <div class="page-enter-active mx-auto max-w-7xl px-6 py-8">
    <!-- 搜索栏 -->
    <div class="mb-8">
      <div class="relative max-w-xl">
        <svg class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-white/25" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
        <input
          v-model="keyword"
          type="text"
          placeholder="搜索演出名称、场馆..."
          class="w-full rounded-2xl bg-white/[0.04] border border-white/[0.08] pl-12 pr-4 py-3.5
                 text-white placeholder-white/25 text-lg
                 focus:outline-none focus:border-brand-400/30 focus:bg-white/[0.07]
                 transition-all duration-200"
          @keyup.enter="doSearch"
        />
      </div>
    </div>

    <LoadingSpinner v-if="loading" />

    <template v-else-if="list.length > 0">
      <p class="mb-6 text-sm text-white/30">
        搜索 "{{ route.query.keyword }}" 共找到 {{ total }} 个结果
      </p>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-5">
        <div v-for="(event, idx) in list" :key="event.id" :style="{ animationDelay: `${idx * 60}ms` }" class="stagger-item">
          <EventCard :event="event" />
        </div>
      </div>
    </template>

    <EmptyState v-else text="未找到相关演出" sub-text="试试其他关键词" />
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'
import { getEventList } from '@/api/event'
import type { EventCard as EventCardType } from '@/types/common'
import EventCard from '@/components/EventCard.vue'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const route = useRoute()
const keyword = ref((route.query.keyword as string) || '')
const list = ref<EventCardType[]>([])
const total = ref(0)
const loading = ref(false)

watch(() => route.query.keyword, (val) => {
  keyword.value = (val as string) || ''
  doSearch()
}, { immediate: true })

function doSearch() {
  const kw = keyword.value.trim()
  if (!kw) return
  loading.value = true
  getEventList({ keyword: kw, page: 1, pageSize: 20 })
    .then(data => { list.value = data.records; total.value = data.total })
    .catch(() => { list.value = []; total.value = 0 })
    .finally(() => { loading.value = false })
}
</script>
