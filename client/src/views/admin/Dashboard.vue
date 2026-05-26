<template>
  <div>
    <h2 class="font-display text-xl font-bold mb-6">仪表盘</h2>

    <LoadingSpinner v-if="loading" />

    <template v-else-if="data">
      <!-- 统计卡片 -->
      <div class="grid grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日订单" :value="data.todayOrderCount" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日收入" :value="data.todayRevenue">
            <template #prefix>¥</template>
          </n-statistic>
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日新用户" :value="data.todayNewUsers" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="在售演出" :value="data.activeEventCount" />
        </n-card>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- 近期订单 -->
        <n-card title="近期订单" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-data-table
            :columns="orderColumns"
            :data="data.recentOrders"
            :bordered="false"
            size="small"
          />
        </n-card>

        <!-- 演出排行 -->
        <n-card title="演出销量排行 TOP 5" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-data-table
            :columns="rankColumns"
            :data="data.topEvents"
            :bordered="false"
            size="small"
          />
        </n-card>
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, h } from 'vue'
import { NTag } from 'naive-ui'
import { getDashboard } from '@/api/admin'
import type { AdminDashboard } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'

const loading = ref(true)
const data = ref<AdminDashboard | null>(null)

const statusMap: Record<string, { label: string; type: 'success' | 'warning' | 'default' }> = {
  pending: { label: '待支付', type: 'warning' },
  paid: { label: '已支付', type: 'success' },
  cancelled: { label: '已取消', type: 'default' },
}

const orderColumns = [
  { title: '订单号', key: 'orderNo', width: 140 },
  { title: '金额', key: 'amount', width: 80 },
  {
    title: '状态', key: 'status', width: 80,
    render: (row: any) => {
      const s = statusMap[row.status] || { label: row.status, type: 'default' as const }
      return h(NTag, { size: 'small', type: s.type }, { default: () => s.label })
    },
  },
  { title: '时间', key: 'createdAt', width: 140 },
]

const rankColumns = [
  { title: '演出', key: 'eventTitle', ellipsis: true },
  { title: '售票数', key: 'ticketCount', width: 80 },
  { title: '收入', key: 'revenue', width: 100, render: (row: any) => `¥${row.revenue}` },
]

onMounted(async () => {
  try {
    data.value = await getDashboard()
  } finally {
    loading.value = false
  }
})
</script>
