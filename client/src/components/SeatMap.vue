<template>
  <div class="seat-map">
    <!-- 舞台指示 -->
    <div class="text-center mb-10">
      <div class="inline-flex flex-col items-center">
        <div class="w-64 h-8 rounded-t-full bg-gradient-to-b from-brand-400/30 to-transparent
                    flex items-center justify-center text-xs text-brand-300/70 tracking-widest">
          S T A G E
        </div>
      </div>
    </div>

    <!-- 座位区域 -->
    <div v-for="zone in zones" :key="zone.name" class="mb-8">
      <div class="flex items-center gap-3 mb-3 px-4">
        <div class="w-3 h-3 rounded-sm" :style="{ backgroundColor: zone.color }"></div>
        <span class="text-sm font-medium text-white/80">{{ zone.name }}</span>
        <span class="text-xs text-white/35">¥{{ zone.price }}</span>
      </div>

      <!-- 座位网格 -->
      <div class="overflow-x-auto pb-2">
        <div class="inline-flex flex-col items-center gap-1.5 min-w-full px-4">
          <div
            v-for="(row, rowIdx) in getRows(zone.seats)"
            :key="`${zone.name}-${rowIdx}`"
            class="flex items-center gap-1.5"
          >
            <span class="w-6 text-right text-xs text-white/25 mr-1">{{ row[0]?.seatRow }}</span>
            <button
              v-for="seat in row"
              :key="seat.id"
              :class="seatCellClass(seat)"
              :disabled="seat.status === 'sold'"
              @click="$emit('toggle', seat)"
            >
              {{ seat.seatCol }}
            </button>
            <span class="w-6 text-left text-xs text-white/25 ml-1">{{ row[0]?.seatRow }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 图例 -->
    <div class="flex items-center justify-center gap-6 mt-8 pt-6 border-t border-white/[0.06]">
      <div class="flex items-center gap-2 text-xs text-white/50">
        <div class="w-4 h-4 rounded bg-white/[0.08] border border-white/10"></div>
        可选
      </div>
      <div class="flex items-center gap-2 text-xs text-white/50">
        <div class="w-4 h-4 rounded bg-brand-500/50 border border-brand-400"></div>
        已选
      </div>
      <div class="flex items-center gap-2 text-xs text-white/50">
        <div class="w-4 h-4 rounded bg-red-500/30 border border-red-400/30"></div>
        已锁定
      </div>
      <div class="flex items-center gap-2 text-xs text-white/50">
        <div class="w-4 h-4 rounded bg-white/[0.03] border border-white/[0.05]"></div>
        已售
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { SeatInfo, SeatZone } from '@/types/common'

defineProps<{ zones: SeatZone[] }>()
defineEmits<{ toggle: [seat: SeatInfo] }>()

function getRows(seats: SeatInfo[]): SeatInfo[][] {
  const groups: Record<string, SeatInfo[]> = {}
  for (const s of seats) {
    if (!groups[s.seatRow]) groups[s.seatRow] = []
    groups[s.seatRow].push(s)
  }
  return Object.values(groups).map(row => row.sort((a, b) => a.seatCol - b.seatCol))
}

function seatCellClass(seat: SeatInfo): string {
  const base = 'w-7 h-7 rounded text-[11px] font-medium transition-all duration-200 flex items-center justify-center'
  if (seat.status === 'selected') return `${base} bg-brand-500/50 border border-brand-400 text-white shadow-glow`
  if (seat.status === 'locked') return `${base} bg-red-500/30 border border-red-400/30 text-red-300 cursor-not-allowed`
  if (seat.status === 'sold') return `${base} bg-white/[0.03] border border-white/[0.05] text-white/15 cursor-not-allowed`
  return `${base} bg-white/[0.05] border border-white/10 text-white/60 hover:bg-white/[0.12] hover:border-brand-400/30 hover:text-white`
}
</script>
