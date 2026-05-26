<template>
  <n-layout class="!h-screen !bg-transparent">
    <!-- 侧边栏 -->
    <n-layout-sider
      bordered
      collapse-mode="width"
      :collapsed-width="64"
      :width="200"
      :native-scrollbar="false"
      class="!bg-black/40"
    >
      <div class="flex items-center gap-2 px-5 h-16 border-b border-white/[0.06]">
        <span class="font-display text-lg font-bold bg-gradient-to-r from-brand-400 to-accent-400 bg-clip-text text-transparent">
          ShowTime
        </span>
      </div>
      <n-menu
        :value="activeKey"
        :options="menuOptions"
        :root-indent="20"
        :indent="12"
        @update:value="onMenuChange"
      />
    </n-layout-sider>

    <!-- 主区域 -->
    <n-layout class="!bg-transparent">
      <n-layout-header bordered class="!bg-transparent h-16 flex items-center justify-end px-6 gap-3">
        <router-link to="/" class="text-sm text-white/40 hover:text-white/70 transition-colors">
          返回前台
        </router-link>
        <n-tag type="warning" size="small" round>管理员</n-tag>
        <span class="text-sm text-white/50">{{ auth.user?.nickname || auth.user?.phone }}</span>
        <n-button quaternary size="small" @click="logout">退出</n-button>
      </n-layout-header>

      <n-layout-content class="!p-6">
        <router-view />
      </n-layout-content>
    </n-layout>
  </n-layout>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const route = useRoute()
const auth = useAuthStore()

const activeKey = computed(() => route.path)

const menuOptions = [
  { label: '仪表盘', key: '/admin/dashboard' },
  { label: '演出管理', key: '/admin/events' },
  { label: '场馆管理', key: '/admin/venues' },
  { label: '销售报表', key: '/admin/reports' },
]

function onMenuChange(key: string) {
  router.push(key)
}

function logout() {
  auth.clearToken()
  router.push('/login')
}
</script>
