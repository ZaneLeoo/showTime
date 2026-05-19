<template>
  <div class="min-h-screen">
    <!-- 顶部导航栏 -->
    <header style="position: sticky; top: 0; z-index: 50; background: rgba(255,255,255,0.8); backdrop-filter: blur(12px); box-shadow: 0 1px 0 rgba(0,0,0,0.05);">
      <div class="page-container">
        <div class="flex items-center justify-between h-16">
          <!-- Logo -->
          <router-link to="/" class="flex items-center gap-2 no-underline">
            <div class="w-9 h-9 rounded-xl bg-primary-500 flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M9 19V6l12-3v13M9 19c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zm12-3c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zM9 10l12-3" />
              </svg>
            </div>
            <span style="font-size: 20px; font-weight: 700; color: #2D3436;">ShowTime</span>
          </router-link>

          <!-- 搜索框 -->
          <div class="hidden md:flex flex-1 max-w-md mx-8">
            <div class="relative w-full">
              <svg class="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4" style="color: #636E72;" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
              <input
                type="text"
                v-model="searchKeyword"
                placeholder="搜索演出、场馆..."
                class="input-field !rounded-pill !py-2.5 !pl-11 !pr-5 text-sm"
                @keyup.enter="doSearch"
              />
            </div>
          </div>

          <!-- 右侧操作 -->
          <div class="flex items-center gap-3">
            <template v-if="auth.isLoggedIn()">
              <router-link to="/user/orders" class="text-sm" style="color: #636E72;">
                我的票夹
              </router-link>
              <div class="flex items-center gap-2 cursor-pointer" @click="auth.logout()">
                <div class="w-8 h-8 rounded-full bg-primary-100 text-primary-600 flex items-center justify-center text-sm font-medium">
                  {{ (auth.user?.nickname || 'U')[0] }}
                </div>
              </div>
            </template>
            <template v-else>
              <router-link to="/login" class="btn-outline !px-6 !py-2 text-sm">
                登录
              </router-link>
              <router-link to="/register" class="btn-primary !px-6 !py-2 text-sm">
                注册
              </router-link>
            </template>
          </div>
        </div>
      </div>
    </header>

    <!-- 页面内容 -->
    <main>
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const router = useRouter()
const searchKeyword = ref('')

function doSearch() {
  const kw = searchKeyword.value.trim()
  if (kw) {
    router.push({ path: '/', query: { keyword: kw } })
  }
}
</script>
