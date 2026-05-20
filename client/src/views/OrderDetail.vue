<template>
  <div class="page-enter-active mx-auto max-w-2xl px-6 py-8">
    <router-link to="/user/orders" class="text-sm text-white/40 hover:text-white/70 transition-colors">
      &larr; 返回票夹
    </router-link>

    <LoadingSpinner v-if="loading" />

    <template v-else-if="order">
      <!-- 状态头 -->
      <div class="mt-4 mb-8 text-center">
        <div :class="statusIconClass" class="w-16 h-16 mx-auto rounded-full flex items-center justify-center mb-4">
          <svg v-if="order.status === 'paid'" class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M5 13l4 4L19 7" />
          </svg>
          <svg v-else-if="order.status === 'pending'" class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <svg v-else class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </div>
        <h1 class="font-display text-2xl font-bold">{{ statusTitle }}</h1>
        <p class="text-sm text-white/40 mt-1">{{ statusSubtitle }}</p>
      </div>

      <!-- 订单信息 -->
      <div class="glass-panel p-6 md:p-8 space-y-5">
        <div class="flex items-center justify-between pb-4 border-b border-white/[0.06]">
          <span class="text-white/40 text-sm">订单号</span>
          <span class="text-sm font-mono text-white/70">{{ order.orderNo }}</span>
        </div>

        <!-- 演出信息 -->
        <div class="flex gap-4 pb-4 border-b border-white/[0.06]">
          <div class="w-14 h-18 rounded-lg overflow-hidden bg-white/[0.03] shrink-0">
            <img :src="order.event.posterUrl || ''" class="w-full h-full object-cover" @error="onImgError" />
          </div>
          <div>
            <h3 class="font-semibold">{{ order.event.title }}</h3>
            <p class="text-sm text-white/40 mt-0.5">{{ order.event.sessionTime }}</p>
            <p class="text-sm text-white/30">{{ order.event.venueName }}</p>
          </div>
        </div>

        <!-- 票务明细 -->
        <div>
          <h4 class="text-sm text-white/40 mb-3">票务明细（共{{ order.tickets.length }}张）</h4>
          <div class="space-y-2">
            <div
              v-for="(ticket, idx) in order.tickets"
              :key="idx"
              class="flex items-center justify-between py-2.5 px-3 rounded-lg bg-white/[0.02]"
            >
              <div class="flex items-center gap-3">
                <span class="px-2 py-0.5 text-xs rounded bg-brand-500/15 text-brand-300 border border-brand-400/20">
                  {{ ticket.zoneName }}
                </span>
                <span class="text-sm">{{ ticket.seatRow }}排{{ ticket.seatCol }}座</span>
              </div>
              <span class="text-sm font-semibold text-accent-400">¥{{ ticket.price }}</span>
            </div>
          </div>
        </div>

        <!-- 支付信息 -->
        <div v-if="order.payment" class="pt-4 border-t border-white/[0.06]">
          <div class="flex items-center justify-between py-1">
            <span class="text-sm text-white/35">支付方式</span>
            <span class="text-sm">{{ order.payment.payMethod }}</span>
          </div>
          <div class="flex items-center justify-between py-1">
            <span class="text-sm text-white/35">支付时间</span>
            <span class="text-sm text-white/60">{{ order.payment.paidAt || '—' }}</span>
          </div>
        </div>

        <!-- 合计 -->
        <div class="pt-4 border-t border-white/[0.06] flex items-center justify-between">
          <span class="text-white/50">实付金额</span>
          <span class="text-xl font-bold text-accent-400">¥{{ order.totalAmount }}</span>
        </div>

        <!-- 时间 -->
        <div class="pt-2 text-xs text-white/25 space-y-1">
          <p>下单时间：{{ order.createdAt }}</p>
          <p v-if="order.paidAt">支付时间：{{ order.paidAt }}</p>
        </div>
      </div>
    </template>

    <EmptyState v-else text="订单不存在" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { getOrderDetail } from '@/api/order'
import type { OrderDetail as OrderDetailType } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const route = useRoute()
const order = ref<OrderDetailType | null>(null)
const loading = ref(true)

const statusTitle = computed(() => {
  const m: Record<string, string> = { pending: '待支付', paid: '支付成功', cancelled: '已取消' }
  return m[order.value?.status || ''] || '未知'
})

const statusSubtitle = computed(() => {
  const m: Record<string, string> = { pending: '请在15分钟内完成支付', paid: '请按时到场观看', cancelled: '座位已释放' }
  return m[order.value?.status || ''] || ''
})

const statusIconClass = computed(() => {
  const m: Record<string, string> = {
    pending: 'bg-amber-500/10 border border-amber-400/20 text-amber-400',
    paid: 'bg-green-500/10 border border-green-400/20 text-green-400',
    cancelled: 'bg-red-500/10 border border-red-400/20 text-red-400',
  }
  return m[order.value?.status || ''] || ''
})

async function fetchDetail() {
  const id = Number(route.params.id)
  loading.value = true
  try {
    order.value = await getOrderDetail(id)
  } catch {
    order.value = null
  } finally {
    loading.value = false
  }
}

fetchDetail()

function onImgError(e: Event) {
  (e.target as HTMLImageElement).src = 'data:image/svg+xml,' + encodeURIComponent(
    '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 56 72"><rect fill="#1a1f2e" width="56" height="72"/></svg>'
  )
}
</script>
