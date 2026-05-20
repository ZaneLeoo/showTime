<template>
  <div class="min-h-[calc(100vh-4rem)] flex items-center justify-center px-4 relative overflow-hidden">
    <!-- 氛围光晕 -->
    <div class="absolute inset-0 pointer-events-none">
      <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2
                  w-[600px] h-[600px] bg-brand-700/[0.07] rounded-full blur-[120px]"></div>
      <div class="absolute top-1/3 right-1/3 w-72 h-72 bg-accent-600/[0.04] rounded-full blur-[100px]"></div>
      <div class="absolute bottom-1/4 left-1/4 w-48 h-48 bg-brand-500/[0.04] rounded-full blur-[80px]"></div>
    </div>

    <div class="relative w-full max-w-md">
      <!-- 表单卡片 -->
      <div class="glass-panel p-8 md:p-10 relative overflow-hidden">
        <!-- 金色装饰线 -->
        <div class="w-16 h-0.5 bg-gradient-to-r from-brand-500 via-accent-500 to-transparent rounded-full mx-auto mb-8"></div>

        <!-- 标题 -->
        <div class="text-center mb-8">
          <p class="text-xs tracking-[0.3em] text-white/20 uppercase mb-3">ShowTime</p>
          <h1 class="font-display text-3xl md:text-4xl font-bold tracking-tight">欢迎回来</h1>
          <p class="mt-3 text-sm text-white/30">登录你的账户，继续探索演出</p>
        </div>

        <!-- 表单 -->
        <form @submit.prevent="handleLogin" class="space-y-4">
          <!-- 手机号 -->
          <div class="relative">
            <div class="absolute left-4 top-1/2 -translate-y-1/2 pointer-events-none">
              <svg class="w-4 h-4 text-white/20" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                  d="M10.5 1.5H8.25A2.25 2.25 0 006 3.75v16.5a2.25 2.25 0 002.25 2.25h7.5A2.25 2.25 0 0018 20.25V3.75a2.25 2.25 0 00-2.25-2.25H13.5m-3 0V3h3V1.5m-3 0h3m-3 18.75h3" />
              </svg>
            </div>
            <input
              v-model="form.phone"
              type="text"
              class="input-field !pl-11"
              placeholder="手机号"
              maxlength="11"
            />
          </div>

          <!-- 密码 -->
          <div class="relative">
            <div class="absolute left-4 top-1/2 -translate-y-1/2 pointer-events-none">
              <svg class="w-4 h-4 text-white/20" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                  d="M16.5 10.5V6.75a4.5 4.5 0 10-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 002.25-2.25v-6.75a2.25 2.25 0 00-2.25-2.25H6.75a2.25 2.25 0 00-2.25 2.25v6.75a2.25 2.25 0 002.25 2.25z" />
              </svg>
            </div>
            <input
              v-model="form.password"
              type="password"
              class="input-field !pl-11"
              placeholder="密码"
            />
          </div>

          <!-- 错误提示 -->
          <p v-if="error" class="text-sm text-red-400 flex items-center gap-1.5">
            <svg class="w-4 h-4 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                d="M12 9v3.75m9-.75a9 9 0 11-18 0 9 9 0 0118 0zm-9 3.75h.008v.008H12v-.008z" />
            </svg>
            {{ error }}
          </p>

          <!-- 提交按钮 -->
          <button
            type="submit"
            :disabled="loading"
            class="btn-brand w-full !py-3.5 text-base !mt-6"
          >
            <span v-if="!loading">登录</span>
            <span v-else class="flex items-center justify-center gap-2">
              <svg class="w-4 h-4 animate-spin" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4z"></path>
              </svg>
              登录中...
            </span>
          </button>
        </form>

        <!-- 底部注册入口 -->
        <p class="mt-8 text-center text-sm text-white/25">
          还没有账号？
          <router-link
            to="/register"
            class="text-brand-400 hover:text-brand-300 transition-colors font-medium"
          >
            立即注册 &rarr;
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { login } from '@/api/user'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const route = useRoute()
const auth = useAuthStore()

const form = reactive({ phone: '', password: '' })
const error = ref('')
const loading = ref(false)

async function handleLogin() {
  error.value = ''
  if (!form.phone || !form.password) {
    error.value = '请填写手机号和密码'
    return
  }

  loading.value = true
  try {
    const res = await login({ phone: form.phone, password: form.password })
    auth.setToken(res.token)
    await auth.fetchUser()
    const redirect = (route.query.redirect as string) || '/'
    router.push(redirect)
  } catch (e: any) {
    error.value = e.message || '登录失败'
  } finally {
    loading.value = false
  }
}
</script>
