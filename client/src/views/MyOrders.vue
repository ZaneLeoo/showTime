<template>
  <div class="page-enter-active mx-auto max-w-4xl px-6 py-8">
    <h1 class="font-display text-2xl font-bold mb-6">我的票夹</h1>

    <!-- 状态筛选 -->
    <div class="flex items-center gap-2 mb-8">
      <button
        v-for="tab in tabs"
        :key="tab.key"
        @click="activeTab = tab.key; page = 1; fetchList()"
        :class="[
          'px-4 py-2 rounded-lg text-sm transition-all duration-200',
          activeTab === tab.key
            ? 'bg-brand-500/20 text-brand-300 border border-brand-400/30'
            : 'bg-white/[0.03] text-white/50 border border-white/[0.05] hover:text-white/80'
        ]"
      >
        {{ tab.label }}
      </button>
    </div>

    <LoadingSpinner v-if="loading" />

    <template v-else-if="list.length > 0">
      <!-- 订单卡片列表 -->
      <div class="space-y-4">
        <div
          v-for="order in list"
          :key="order.id"
          class="card block"
        >
          <router-link :to="`/order/${order.id}`" class="p-5 flex gap-4">
            <!-- 海报缩略图 -->
            <div class="w-16 h-20 rounded-lg overflow-hidden bg-white/[0.03] shrink-0">
              <img
                :src="order.posterUrl || ''"
                class="w-full h-full object-cover"
                @error="onImgError"
              />
            </div>

            <!-- 信息 -->
            <div class="flex-1 min-w-0">
              <div class="flex items-start justify-between gap-2">
                <h3 class="font-semibold text-sm truncate">{{ order.eventTitle }}</h3>
                <span :class="orderStatusClass(order.status)" class="shrink-0 px-2 py-0.5 text-xs rounded-full border">
                  {{ orderStatusText(order.status) }}
                </span>
              </div>
              <p class="text-xs text-white/35 mt-1">{{ order.sessionTime }}</p>
              <div class="flex items-center justify-between mt-3">
                <span class="text-xs text-white/25">{{ order.orderNo }}</span>
                <div class="text-right">
                  <span class="text-xs text-white/40">{{ order.ticketCount }}张</span>
                  <span class="ml-2 font-semibold text-accent-400">¥{{ order.totalAmount }}</span>
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </div>

      <!-- 分页 -->
      <div v-if="total > pageSize" class="mt-8 flex justify-center gap-2">
        <button @click="page--; fetchList()" :disabled="page <= 1" class="btn-ghost text-sm !px-4 !py-2 disabled:opacity-30">上一页</button>
        <span class="flex items-center px-4 text-sm text-white/30">{{ page }} / {{ Math.ceil(total / pageSize) }}</span>
        <button @click="page++; fetchList()" :disabled="page >= Math.ceil(total / pageSize)" class="btn-ghost text-sm !px-4 !py-2 disabled:opacity-30">下一页</button>
      </div>
    </template>

    <EmptyState v-else text="暂无订单" :sub-text="activeTab === 'all' ? '去首页看看吧' : ''" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { getOrderList } from '@/api/order'
import type { OrderCard } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const tabs = [
  { key: 'all', label: '全部' },
  { key: 'pending', label: '待支付' },
  { key: 'paid', label: '已支付' },
  { key: 'cancelled', label: '已取消' },
]

const activeTab = ref('all')
const list = ref<OrderCard[]>([])
const page = ref(1)
const total = ref(0)
const pageSize = 10
const loading = ref(false)

function orderStatusText(status: string) {
  const m: Record<string, string> = { pending: '待支付', paid: '已支付', cancelled: '已取消' }
  return m[status] || status
}

function orderStatusClass(status: string) {
  const m: Record<string, string> = {
    pending: 'bg-amber-500/10 border-amber-400/20 text-amber-300',
    paid: 'bg-green-500/10 border-green-400/20 text-green-300',
    cancelled: 'bg-white/5 border-white/10 text-white/40',
  }
  return m[status] || ''
}

async function fetchList() {
  loading.value = true
  try {
    const data = await getOrderList({
      status: activeTab.value === 'all' ? undefined : activeTab.value,
      page: page.value,
      pageSize,
    })
    list.value = data.records
    total.value = data.total
  } catch {
    list.value = []
  } finally {
    loading.value = false
  }
}

fetchList()

function onImgError(e: Event) {
  (e.target as HTMLImageElement).src = 'data:image/svg+xml,' + encodeURIComponent(
    '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 80"><rect fill="#1a1f2e" width="64" height="80"/></svg>'
  )
}
</script>
