<template>
  <div>
    <h2 class="font-display text-xl font-bold mb-6">销售报表</h2>
    <LoadingSpinner v-if="loading" />
    <template v-else-if="data">
      <div class="grid grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="总订单" :value="data.todayOrderCount" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="总收入" :value="data.todayRevenue"><template #prefix>¥</template></n-statistic>
        </n-card>
      </div>

      <n-card title="演出销量排行" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
        <n-data-table :columns="columns" :data="data.topEvents" :bordered="false" />
      </n-card>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getDashboard } from '@/api/admin'
import type { AdminDashboard } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'

const loading = ref(true)
const data = ref<AdminDashboard | null>(null)

const columns = [
  { title: '演出', key: 'eventTitle', ellipsis: true },
  { title: '售票数', key: 'ticketCount', width: 100 },
  { title: '收入', key: 'revenue', width: 120, render: (row: any) => `¥${row.revenue}` },
]

onMounted(async () => {
  try { data.value = await getDashboard() }
  finally { loading.value = false }
})
</script>
