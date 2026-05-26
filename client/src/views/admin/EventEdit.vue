<template>
  <div>
    <router-link to="/admin/events" class="text-sm text-white/40 hover:text-white/70">&larr; 返回演出列表</router-link>
    <h2 class="font-display text-xl font-bold mt-2 mb-6">{{ isCreate ? '新建演出' : '编辑演出' }}</h2>

    <!-- 演出表单 -->
    <n-card size="small" title="基本信息" class="!bg-white/[0.02] !border-white/[0.06] mb-6">
      <n-form :model="form" :rules="rules" label-placement="left" label-width="80">
        <n-grid cols="2" x-gap="16">
          <n-form-item-gi label="名称" path="title">
            <n-input v-model:value="form.title" />
          </n-form-item-gi>
          <n-form-item-gi label="分类" path="categoryId">
            <n-select v-model:value="form.categoryId" :options="categoryOptions" placeholder="选择分类" />
          </n-form-item-gi>
          <n-form-item-gi label="场馆" path="venueId">
            <n-select v-model:value="form.venueId" :options="venueOptions" placeholder="选择场馆" />
          </n-form-item-gi>
          <n-form-item-gi label="时长(分钟)">
            <n-input-number v-model:value="form.duration" :min="0" />
          </n-form-item-gi>
          <n-form-item-gi label="海报URL" span="2">
            <n-input v-model:value="form.posterUrl" placeholder="https://..." />
          </n-form-item-gi>
          <n-form-item-gi label="简介" span="2">
            <n-input v-model:value="form.description" type="textarea" :rows="3" />
          </n-form-item-gi>
          <n-form-item-gi label="状态">
            <n-select v-model:value="form.status" :options="statusOptions" />
          </n-form-item-gi>
        </n-grid>
      </n-form>
      <div class="flex justify-end mt-4">
        <n-button type="primary" @click="saveEvent" :loading="saving">保存演出</n-button>
      </div>
    </n-card>

    <!-- 场次管理 (仅编辑模式) -->
    <n-card v-if="!isCreate" size="small" title="场次管理" class="!bg-white/[0.02] !border-white/[0.06]">
      <n-button size="small" type="primary" @click="openSessionModal" class="mb-4">添加场次</n-button>
      <n-data-table :columns="sessionColumns" :data="sessions" :bordered="false" size="small" />

      <n-modal v-model:show="showSessionModal" :title="editingSessionId ? '编辑场次' : '添加场次'" preset="card" style="width:600px">
        <n-form :model="sessionForm">
          <n-form-item label="开演时间">
            <n-date-picker v-model:value="sessionForm.sessionTime" type="datetime" format="yyyy-MM-dd HH:mm" />
          </n-form-item>
          <n-form-item label="状态">
            <n-select v-model:value="sessionForm.status" :options="sessionStatusOptions" />
          </n-form-item>
          <n-form-item label="票档配置">
            <div class="space-y-2 w-full">
              <div v-for="(z, i) in sessionForm.zones" :key="i" class="flex items-center gap-2">
                <n-input v-model:value="z.name" placeholder="区域名" size="small" style="width:80px" />
                <n-input-number v-model:value="z.price" placeholder="票价" size="small" style="width:100px" :min="0" />
                <n-input-number v-model:value="z.rows" placeholder="排数" size="small" style="width:70px" :min="1" />
                <n-input-number v-model:value="z.cols" placeholder="列数" size="small" style="width:70px" :min="1" />
                <n-button size="tiny" type="error" @click="sessionForm.zones.splice(i, 1)">删</n-button>
              </div>
              <n-button size="tiny" @click="sessionForm.zones.push({ name: '', price: 0, rows: 1, cols: 10 })">+ 票档</n-button>
            </div>
          </n-form-item>
        </n-form>
        <template #footer>
          <n-button @click="showSessionModal = false">取消</n-button>
          <n-button type="primary" @click="saveSession" :loading="savingSession">保存</n-button>
        </template>
      </n-modal>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, h } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { NButton, NTag, useMessage } from 'naive-ui'
import { getEventDetail } from '@/api/event'
import { getAdminVenueList } from '@/api/admin'
import {
  createEvent, updateEvent,
  getSessionList, createSession, updateSession as updateSessionApi, deleteSession,
} from '@/api/admin'
import type { AdminEventReq, AdminSessionReq, AdminSession, AdminSession as AdminSessionType, VenueInfo } from '@/types/common'

const route = useRoute()
const router = useRouter()
const message = useMessage()
const saving = ref(false)
const savingSession = ref(false)
const eventId = computed(() => route.params.id === 'new' ? null : Number(route.params.id))
const isCreate = computed(() => route.params.id === 'new')

// Event form
const form = ref<AdminEventReq>({ title: '', categoryId: 0, venueId: 0, duration: 0, status: 1, posterUrl: '', description: '' })
const rules = {
  title: { required: true, message: '请输入演出名称', trigger: 'blur' },
  categoryId: { required: true, type: 'number' as any, message: '请选择分类', trigger: 'change' },
  venueId: { required: true, type: 'number' as any, message: '请选择场馆', trigger: 'change' },
}
const statusOptions = [
  { label: '即将开售', value: 0 }, { label: '在售中', value: 1 }, { label: '已售罄', value: 2 }, { label: '已结束', value: 3 },
]
const sessionStatusOptions = [
  { label: '取消', value: 0 }, { label: '正常', value: 1 }, { label: '已结束', value: 2 },
]

const categoryOptions = ref<Array<{ label: string; value: number }>>([])
const venueOptions = ref<Array<{ label: string; value: number }>>([])

// Sessions
const sessions = ref<AdminSessionType[]>([])
const showSessionModal = ref(false)
const editingSessionId = ref<number | null>(null)
const sessionForm = ref<AdminSessionReq>({
  sessionTime: Date.now() + '',
  status: 1,
  zones: [{ name: 'A区', price: 0, rows: 3, cols: 14 }],
})

const sessionColumns = [
  { title: '时间', key: 'sessionTime', width: 160 },
  {
    title: '状态', key: 'status', width: 80,
    render: (row: AdminSessionType) => {
      const m: Record<number, string> = { 0: '取消', 1: '正常', 2: '已结束' }
      return h(NTag, { size: 'small' }, { default: () => m[row.status] || '-' })
    },
  },
  {
    title: '操作', key: 'actions', width: 140,
    render: (row: AdminSessionType) =>
      h('div', { class: 'flex gap-2' }, [
        h(NButton, { size: 'small', onClick: () => openEditSession(row) }, { default: () => '编辑' }),
        h(NButton, { size: 'small', type: 'error', onClick: () => handleDeleteSession(row.id) }, { default: () => '删除' }),
      ]),
  },
]

onMounted(async () => {
  // Load venues
  try {
    const venueRes = await getAdminVenueList({ pageSize: 100 })
    venueOptions.value = venueRes.records.map((v: VenueInfo) => ({ label: v.name, value: v.id }))
  } catch {}

  // Load event data if editing
  if (!isCreate.value) {
    try {
      const detail = await getEventDetail(eventId.value!)
      form.value = {
        title: detail.title,
        categoryId: detail.categoryId,
        venueId: detail.venue.id,
        duration: detail.duration,
        status: detail.sessions[0]?.status ?? 1,
        posterUrl: detail.posterUrl,
        description: detail.description,
      }
      categoryOptions.value = [{ label: detail.categoryName, value: detail.categoryId }]
      sessions.value = await getSessionList(eventId.value!)
    } catch (e: any) {
      message.error(e.message)
    }
  }
})

async function saveEvent() {
  saving.value = true
  try {
    if (isCreate.value) {
      const res = await createEvent(form.value)
      message.success('创建成功')
      router.replace(`/admin/events/${res.id}`)
    } else {
      await updateEvent(eventId.value!, form.value)
      message.success('保存成功')
    }
  } catch (e: any) { message.error(e.message) }
  finally { saving.value = false }
}

function openSessionModal() {
  editingSessionId.value = null
  sessionForm.value = { sessionTime: Date.now() + '', status: 1, zones: [{ name: 'A区', price: 0, rows: 3, cols: 14 }] }
  showSessionModal.value = true
}

function openEditSession(row: AdminSessionType) {
  editingSessionId.value = row.id
  sessionForm.value = { sessionTime: row.sessionTime, status: row.status, zones: [] }
  showSessionModal.value = true
}

async function saveSession() {
  savingSession.value = true
  try {
    if (editingSessionId.value) {
      await updateSessionApi(editingSessionId.value, sessionForm.value)
      message.success('更新成功')
    } else {
      await createSession(eventId.value!, sessionForm.value)
      message.success('创建成功')
    }
    showSessionModal.value = false
    sessions.value = await getSessionList(eventId.value!)
  } catch (e: any) { message.error(e.message) }
  finally { savingSession.value = false }
}

async function handleDeleteSession(id: number) {
  if (!confirm('确认删除？将同时删除所有座位。')) return
  try {
    await deleteSession(id)
    message.success('已删除')
    sessions.value = await getSessionList(eventId.value!)
  } catch (e: any) { message.error(e.message) }
}
</script>
