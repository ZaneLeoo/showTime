<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h2 class="font-display text-xl font-bold">场馆管理</h2>
      <n-button type="primary" @click="openCreate">新建场馆</n-button>
    </div>

    <n-data-table
      :columns="columns"
      :data="list"
      :loading="loading"
      :pagination="pagination"
      :bordered="false"
      @update:page="onPageChange"
    />

    <!-- 创建/编辑 Modal -->
    <n-modal v-model:show="showModal" title="场馆信息" preset="card" style="width:480px">
      <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" label-width="80">
        <n-form-item label="名称" path="name">
          <n-input v-model:value="form.name" />
        </n-form-item>
        <n-form-item label="城市" path="city">
          <n-input v-model:value="form.city" />
        </n-form-item>
        <n-form-item label="地址" path="address">
          <n-input v-model:value="form.address" />
        </n-form-item>
        <n-form-item label="介绍" path="description">
          <n-input v-model:value="form.description" type="textarea" />
        </n-form-item>
      </n-form>
      <template #footer>
        <div class="flex justify-end gap-2">
          <n-button @click="showModal = false">取消</n-button>
          <n-button type="primary" @click="submitForm" :loading="submitting">保存</n-button>
        </div>
      </template>
    </n-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, h, computed } from 'vue'
import { NButton, useMessage } from 'naive-ui'
import { getAdminVenueList, createVenue, updateVenue, deleteVenue } from '@/api/admin'
import type { VenueInfo, AdminVenueReq } from '@/types/common'

const message = useMessage()
const list = ref<VenueInfo[]>([])
const loading = ref(false)
const showModal = ref(false)
const submitting = ref(false)
const editingId = ref<number | null>(null)
const page = ref(1)
const total = ref(0)
const pageSize = 10

const pagination = computed(() => ({
  page: page.value,
  pageSize,
  itemCount: total.value,
}))

const form = ref<AdminVenueReq>({ name: '', city: '', address: '', description: '' })
const rules = {
  name: { required: true, message: '请输入场馆名称', trigger: 'blur' },
  city: { required: true, message: '请输入城市', trigger: 'blur' },
  address: { required: true, message: '请输入地址', trigger: 'blur' },
}

const columns = [
  { title: 'ID', key: 'id', width: 60 },
  { title: '名称', key: 'name', ellipsis: true },
  { title: '城市', key: 'city', width: 100 },
  { title: '地址', key: 'address', ellipsis: true },
  {
    title: '操作', key: 'actions', width: 140,
    render: (row: VenueInfo) => h('div', { class: 'flex gap-2' }, [
      h(NButton, { size: 'small', onClick: () => openEdit(row) }, { default: () => '编辑' }),
      h(NButton, { size: 'small', type: 'error', onClick: () => handleDelete(row.id) }, { default: () => '删除' }),
    ]),
  },
]

async function fetchList() {
  loading.value = true
  try {
    const res = await getAdminVenueList({ page: page.value, pageSize })
    list.value = res.records
    total.value = res.total
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editingId.value = null
  form.value = { name: '', city: '', address: '', description: '' }
  showModal.value = true
}

function openEdit(row: VenueInfo) {
  editingId.value = row.id
  form.value = { name: row.name, city: row.city, address: row.address, description: row.description }
  showModal.value = true
}

async function submitForm() {
  submitting.value = true
  try {
    if (editingId.value) {
      await updateVenue(editingId.value, form.value)
      message.success('更新成功')
    } else {
      await createVenue(form.value)
      message.success('创建成功')
    }
    showModal.value = false
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  } finally {
    submitting.value = false
  }
}

async function handleDelete(id: number) {
  if (!confirm('确认删除？')) return
  try {
    await deleteVenue(id)
    message.success('已删除')
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  }
}

function onPageChange(p: number) {
  page.value = p
  fetchList()
}

onMounted(fetchList)
</script>
