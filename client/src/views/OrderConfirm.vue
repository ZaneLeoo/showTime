<template>
  <div class="page-enter-active mx-auto max-w-2xl px-6 py-8">
    <h1 class="font-display text-2xl font-bold mb-8">确认订单</h1>

    <LoadingSpinner v-if="loading" />

    <template v-else>
      <!-- 订单信息卡片 -->
      <div class="glass-panel p-6 md:p-8 space-y-6">
        <!-- 演出信息 -->
        <div class="flex gap-4 pb-6 border-b border-white/[0.06]">
          <div class="w-16 h-20 rounded-lg overflow-hidden bg-white/[0.03] shrink-0">
            <img :src="eventInfo.posterUrl || ''" class="w-full h-full object-cover" @error="onImgError" />
          </div>
          <div>
            <h3 class="font-semibold">{{ eventInfo.title }}</h3>
            <p class="text-sm text-white/40 mt-1">{{ eventInfo.sessionTime }}</p>
            <p class="text-sm text-white/30">{{ eventInfo.venueName }}</p>
          </div>
        </div>

        <!-- 座位明细 -->
        <div>
          <h4 class="text-sm text-white/40 mb-3">座位明细</h4>
          <div class="space-y-2">
            <div
              v-for="seat in seats"
              :key="seat.id"
              class="flex items-center justify-between py-2 px-3 rounded-lg bg-white/[0.02]"
            >
              <div class="flex items-center gap-3">
                <span class="px-2 py-0.5 text-xs rounded bg-brand-500/15 text-brand-300 border border-brand-400/20">
                  {{ seat.zoneName }}
                </span>
                <span class="text-sm">{{ seat.seatRow }}排{{ seat.seatCol }}座</span>
              </div>
              <span class="text-sm font-semibold text-accent-400">¥{{ seat.price }}</span>
            </div>
          </div>
        </div>

        <!-- 价格合计 -->
        <div class="pt-6 border-t border-white/[0.06]">
          <div class="flex items-center justify-between">
            <span class="text-white/50">合计</span>
            <div class="text-right">
              <span class="text-xs text-white/40">共{{ seats.length }}张</span>
              <div class="text-2xl font-bold text-accent-400">¥{{ totalPrice }}</div>
            </div>
          </div>
        </div>

        <!-- 提交 -->
        <div class="flex gap-3 pt-4">
          <button class="btn-ghost flex-1 !py-3" @click="$router.back()">返回修改</button>
          <button
            class="btn-brand flex-1 !py-3"
            :disabled="submitting"
            @click="submitOrder"
          >
            {{ submitting ? '提交中...' : '确认支付' }}
          </button>
        </div>

        <p class="text-xs text-white/25 text-center">
          请在 15 分钟内完成支付，超时座位将自动释放
        </p>
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { createOrder, payOrder } from '@/api/order'
import LoadingSpinner from '@/components/LoadingSpinner.vue'

const route = useRoute()
const router = useRouter()

const sessionId = Number(route.query.sessionId)
const seatIdList = (route.query.seatIds as string || '').split(',').map(Number)

const loading = ref(false)
const submitting = ref(false)

// 模拟数据 — 后续从接口获取座位详情
const totalPrice = computed(() => seats.value.reduce((s, seat) => s + seat.price, 0))

const eventInfo = ref({
  title: '周杰伦2026巡回演唱会·北京站',
  posterUrl: '/img/concertImage/5e953e3f51aa65c5d81cbb542a790ac0.jpg',
  sessionTime: '2026-06-20 19:30',
  venueName: '鸟巢文化中心',
})

const seats = ref<Array<{ id: number; zoneName: string; seatRow: string; seatCol: number; price: number }>>([
  { id: seatIdList[0] || 1, zoneName: 'VIP区', seatRow: 'A', seatCol: 5, price: 1680 },
  { id: seatIdList[1] || 2, zoneName: 'VIP区', seatRow: 'A', seatCol: 6, price: 1680 },
])

async function submitOrder() {
  submitting.value = true
  try {
    const order = await createOrder({
      sessionId,
      seatIds: seats.value.map(s => s.id),
    })
    await payOrder(order.orderId)
    router.replace({ name: 'order-detail', params: { id: order.orderId } })
  } catch (e: any) {
    alert(e.message || '下单失败')
  } finally {
    submitting.value = false
  }
}

function onImgError(e: Event) {
  (e.target as HTMLImageElement).src = 'data:image/svg+xml,' + encodeURIComponent(
    '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 80"><rect fill="#1a1f2e" width="64" height="80"/></svg>'
  )
}
</script>
