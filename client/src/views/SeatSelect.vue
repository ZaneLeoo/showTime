<template>
  <div class="page-enter-active mx-auto max-w-7xl px-6 py-8">
    <!-- 顶部信息栏 -->
    <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-8">
      <div>
        <router-link :to="`/event/${eventId}`" class="text-sm text-white/40 hover:text-white/70 transition-colors">
          &larr; 返回演出详情
        </router-link>
        <h1 class="font-display text-2xl font-bold mt-1">选择座位</h1>
      </div>

      <!-- 已选 & 倒计时 -->
      <div v-if="selectedSeats.length > 0" class="flex items-center gap-4">
        <div class="text-right">
          <div class="text-xs text-white/40">已选 {{ selectedSeats.length }} 座</div>
          <div class="text-lg font-bold text-accent-400">¥{{ totalPrice }}</div>
        </div>
        <div v-if="lockExpireAt" class="text-right">
          <div class="text-xs text-white/40">剩余支付时间</div>
          <div class="text-lg font-bold text-amber-400 font-mono">{{ remainText }}</div>
        </div>
        <button
          class="btn-brand !py-2.5 !px-6"
          @click="confirmSelection"
        >
          确认选座
        </button>
      </div>
    </div>

    <!-- 座位图 -->
    <LoadingSpinner v-if="loading" />

    <template v-else-if="zones.length > 0">
      <div class="glass-panel p-6 md:p-8 overflow-x-auto">
        <SeatMap :zones="zones" @toggle="onToggleSeat" />
      </div>
    </template>

    <EmptyState v-else text="该场次暂无座位数据" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getSeatMap, lockSeats, releaseSeats } from '@/api/event'
import type { SeatInfo, SeatZone } from '@/types/common'
import SeatMap from '@/components/SeatMap.vue'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const route = useRoute()
const router = useRouter()

const eventId = computed(() => Number(route.params.id))
const sessionId = computed(() => Number(route.query.sessionId))

const zones = ref<SeatZone[]>([])
const selectedSeats = ref<SeatInfo[]>([])
const lockExpireAt = ref<string | null>(null)
const loading = ref(false)
const lockTimer = ref<ReturnType<typeof setInterval> | null>(null)

const totalPrice = computed(() =>
  selectedSeats.value.reduce((sum, s) => sum + s.price, 0)
)

const remainText = ref('')

function updateRemain() {
  if (!lockExpireAt.value) { remainText.value = ''; return }
  const diff = new Date(lockExpireAt.value).getTime() - Date.now()
  if (diff <= 0) {
    remainText.value = '已超时'
    clearSelection()
    return
  }
  const m = Math.floor(diff / 60000)
  const s = Math.floor((diff % 60000) / 1000)
  remainText.value = `${m}:${String(s).padStart(2, '0')}`
}

onMounted(() => fetchSeatMap())

onUnmounted(() => {
  if (lockTimer.value) clearInterval(lockTimer.value)
  // 离开页面释放座位
  if (selectedSeats.value.length > 0) {
    releaseSeats(sessionId.value, selectedSeats.value.map(s => s.id)).catch(() => {})
  }
})

async function fetchSeatMap() {
  loading.value = true
  try {
    zones.value = await getSeatMap(sessionId.value)
  } catch {
    zones.value = getMockZones()
  } finally {
    loading.value = false
  }
}

async function onToggleSeat(seat: SeatInfo) {
  if (seat.status === 'sold' || seat.status === 'locked') return

  if (seat.status === 'selected') {
    // 取消选中
    selectedSeats.value = selectedSeats.value.filter(s => s.id !== seat.id)
    seat.status = 'available'
    try { await releaseSeats(sessionId.value, [seat.id]) } catch {}
    return
  }

  if (selectedSeats.value.length >= 6) {
    alert('最多选择6个座位')
    return
  }

  // 锁定座位
  try {
    const res = await lockSeats(sessionId.value, [seat.id])
    seat.status = 'selected'
    selectedSeats.value.push(seat)
    lockExpireAt.value = res.lockExpireAt
    if (lockTimer.value) clearInterval(lockTimer.value)
    lockTimer.value = setInterval(updateRemain, 1000)
  } catch (e: any) {
    alert(e.message || '座位锁定失败')
  }
}

function confirmSelection() {
  if (selectedSeats.value.length === 0) return
  router.push({
    name: 'order-confirm',
    query: {
      sessionId: sessionId.value,
      seatIds: selectedSeats.value.map(s => s.id).join(','),
    },
  })
}

function clearSelection() {
  selectedSeats.value = []
  lockExpireAt.value = null
  remainText.value = ''
  if (lockTimer.value) { clearInterval(lockTimer.value); lockTimer.value = null }
}

function getMockZones(): SeatZone[] {
  const zones: SeatZone[] = []
  const configs = [
    { name: 'VIP区', price: 1680, color: '#f59e0b', rows: 3, cols: 14 },
    { name: 'A区', price: 1280, color: '#a855f7', rows: 5, cols: 18 },
    { name: 'B区', price: 880, color: '#3b82f6', rows: 6, cols: 20 },
    { name: 'C区', price: 580, color: '#22c55e', rows: 5, cols: 16 },
  ]
  const rowLabels = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']

  for (const cfg of configs) {
    const seats: SeatInfo[] = []
    for (let r = 0; r < cfg.rows; r++) {
      for (let c = 1; c <= cfg.cols; c++) {
        const statuses: SeatInfo['status'][] = ['available', 'available', 'available', 'available', 'sold', 'locked']
        seats.push({
          id: seats.length + 1,
          zoneName: cfg.name,
          seatRow: rowLabels[r],
          seatCol: c,
          price: cfg.price,
          status: statuses[Math.floor(Math.random() * statuses.length)],
        })
      }
    }
    zones.push({ name: cfg.name, price: cfg.price, color: cfg.color, seats })
  }
  return zones
}
</script>
