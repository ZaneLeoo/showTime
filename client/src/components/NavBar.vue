<template>
  <nav class="fixed top-0 left-0 right-0 z-50 border-b border-white/[0.06] bg-surface-950/70 backdrop-blur-xl">
    <div class="mx-auto max-w-7xl px-6 h-16 flex items-center justify-between">
      <!-- Logo -->
      <router-link to="/" class="flex items-center gap-3 group">
        <div class="w-9 h-9 rounded-lg bg-brand-gradient flex items-center justify-center
                    shadow-glow group-hover:shadow-glow-lg transition-shadow duration-300">
          <span class="text-white font-bold text-sm">ST</span>
        </div>
        <span class="text-xl font-display font-semibold tracking-wide">
          Show<span class="text-gradient">Time</span>
        </span>
      </router-link>

      <!-- 中间搜索条(大屏) -->
      <div class="hidden md:flex items-center flex-1 max-w-md mx-8">
        <div class="relative w-full">
          <svg class="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-white/25" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
          </svg>
          <input
            v-model="keyword"
            type="text"
            placeholder="搜索演出、场馆..."
            class="w-full rounded-xl bg-white/[0.05] border border-white/[0.08] pl-10 pr-4 py-2 text-sm text-white
                   placeholder-white/25 focus:outline-none focus:border-brand-400/30 focus:bg-white/[0.08]
                   transition-all duration-200"
            @keyup.enter="doSearch"
          />
        </div>
      </div>

      <!-- 右侧操作 -->
      <div class="flex items-center gap-3">
        <template v-if="auth.isLoggedIn">
          <router-link to="/user/orders" class="text-sm text-white/60 hover:text-white transition-colors">
            我的票夹
          </router-link>
          <div class="flex items-center gap-2 pl-3 border-l border-white/10">
            <div class="w-7 h-7 rounded-full bg-brand-gradient flex items-center justify-center text-xs font-bold">
              {{ auth.user?.nickname?.charAt(0) || auth.user?.phone?.charAt(0) || 'U' }}
            </div>
            <span class="text-sm text-white/70 hidden sm:block">
              {{ auth.user?.nickname || auth.user?.phone || '用户' }}
            </span>
          </div>
        </template>
        <template v-else>
          <router-link to="/login" class="btn-ghost text-sm !px-4 !py-2">登录</router-link>
          <router-link to="/register" class="btn-brand text-sm !px-4 !py-2">注册</router-link>
        </template>
      </div>
    </div>
  </nav>
  <!-- 占位 -->
  <div class="h-16"></div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const router = useRouter()
const keyword = ref('')

function doSearch() {
  const kw = keyword.value.trim()
  if (kw) {
    router.push({ name: 'search', query: { keyword: kw } })
  }
}
</script>
