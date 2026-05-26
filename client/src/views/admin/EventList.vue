<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h2 class="font-display text-xl font-bold">演出管理</h2>
      <n-button type="primary" @click="$router.push('/admin/events/new')">新建演出</n-button>
    </div>

    <div class="flex items-center gap-3 mb-4">
      <n-input v-model:value="keyword" placeholder="搜索演出名称" clearable style="width:200px" @keyup.enter="search" />
      <n-select v-model:value="statusFilter" :options="statusOptions" placeholder="状态" clearable style="width:120px" @update:value="search" />
      <n-button secondary @click="search">搜索</n-button>
    </div>

    <n-data-table
      :columns="columns"
      :data="list"
      :loading="loading"
      :pagination="pagination"
      :bordered="false"
      @update:page="onPageChange"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, h, computed } from 'vue'
import { useRouter } from 'vue-router'
import { NButton, NTag, NImage, useMessage } from 'naive-ui'
import { getAdminEventList, deleteEvent } from '@/api/admin'
import type { AdminEventCard } from '@/types/common'

const router = useRouter()
const message = useMessage()
const list = ref<AdminEventCard[]>([])
const loading = ref(false)
const keyword = ref('')
const statusFilter = ref<number | null>(null)
const page = ref(1)
const total = ref(0)
const pageSize = 10

const statusOptions = [
  { label: '即将开售', value: 0 },
  { label: '在售中', value: 1 },
  { label: '已售罄', value: 2 },
  { label: '已结束', value: 3 },
]

const pagination = computed(() => ({ page: page.value, pageSize, itemCount: total.value }))

const statusTagMap: Record<number, { label: string; type: 'info' | 'success' | 'error' | 'default' }> = {
  0: { label: '即将开售', type: 'info' },
  1: { label: '在售中', type: 'success' },
  2: { label: '已售罄', type: 'error' },
  3: { label: '已结束', type: 'default' },
}

const columns = [
  {
    title: '海报', key: 'posterUrl', width: 60,
    render: (row: AdminEventCard) => h(NImage, { width: 40, height: 50, src: row.posterUrl || '', fallbackSrc: 'data:image/svg+xml,' + encodeURIComponent('<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 40 50"><rect fill="#1a1f2e" width="40" height="50"/></svg>'), style: { borderRadius: '4px' } }),
  },
  { title: '名称', key: 'title', ellipsis: true },
  { title: '分类', key: 'categoryName', width: 80 },
  { title: '场馆', key: 'venueName', width: 100 },
  {
    title: '状态', key: 'status', width: 80,
    render: (row: AdminEventCard) => {
      const s = statusTagMap[row.status] || { label: '未知', type: 'default' as const }
      return h(NTag, { size: 'small', type: s.type }, { default: () => s.label })
    },
  },
  {
    title: '操作', key: 'actions', width: 140,
    render: (row: AdminEventCard) => h('div', { class: 'flex gap-2' }, [
      h(NButton, { size: 'small', onClick: () => router.push(`/admin/events/${row.id}`) }, { default: () => '编辑' }),
      h(NButton, { size: 'small', type: 'error', onClick: () => handleDelete(row.id) }, { default: () => '删除' }),
    ]),
  },
]

async function fetchList() {
  loading.value = true
  try {
    const res = await getAdminEventList({ keyword: keyword.value || undefined, status: statusFilter.value ?? undefined, page: page.value, pageSize })
    list.value = res.records
    total.value = res.total
  } finally {
    loading.value = false
  }
}

function search() { page.value = 1; fetchList() }
function onPageChange(p: number) { page.value = p; fetchList() }

async function handleDelete(id: number) {
  if (!confirm('确认删除？')) return
  try {
    await deleteEvent(id)
    message.success('已删除')
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  }
}

onMounted(fetchList)
</script>
