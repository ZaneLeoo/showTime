<template>
  <div class="min-h-[calc(100vh-4rem)] flex items-center justify-center px-4 relative overflow-hidden">
    <!-- 氛围光晕 -->
    <div class="absolute inset-0 pointer-events-none">
      <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2
                  w-[600px] h-[600px] bg-accent-600/[0.05] rounded-full blur-[120px]"></div>
      <div class="absolute top-1/3 right-1/3 w-72 h-72 bg-brand-700/[0.05] rounded-full blur-[100px]"></div>
      <div class="absolute bottom-1/4 left-1/4 w-48 h-48 bg-accent-500/[0.03] rounded-full blur-[80px]"></div>
    </div>

    <div class="relative w-full max-w-md">
      <!-- 表单卡片 -->
      <div class="glass-panel p-8 md:p-10 relative overflow-hidden">
        <!-- 金色装饰线 -->
        <div class="w-16 h-0.5 bg-gradient-to-r from-accent-500 via-brand-500 to-transparent rounded-full mx-auto mb-8"></div>

        <!-- 标题 -->
        <div class="text-center mb-8">
          <p class="text-xs tracking-[0.3em] text-white/20 uppercase mb-3">ShowTime</p>
          <h1 class="font-display text-3xl md:text-4xl font-bold tracking-tight">创建账户</h1>
          <p class="mt-3 text-sm text-white/30">加入 ShowTime，发现更多精彩演出</p>
        </div>

        <!-- 表单 -->
        <form @submit.prevent="handleRegister" class="space-y-4">
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
            <span class="absolute right-4 top-1/2 -translate-y-1/2 text-xs text-red-400/70">*</span>
          </div>

          <!-- 邮箱 -->
          <div class="relative">
            <div class="absolute left-4 top-1/2 -translate-y-1/2 pointer-events-none">
              <svg class="w-4 h-4 text-white/20" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                  d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75" />
              </svg>
            </div>
            <input
              v-model="form.email"
              type="email"
              class="input-field !pl-11"
              placeholder="邮箱（选填）"
            />
          </div>

          <!-- 昵称 -->
          <div class="relative">
            <div class="absolute left-4 top-1/2 -translate-y-1/2 pointer-events-none">
              <svg class="w-4 h-4 text-white/20" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5"
                  d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
              </svg>
            </div>
            <input
              v-model="form.nickname"
              type="text"
              class="input-field !pl-11"
              placeholder="昵称（选填）"
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
              placeholder="密码（至少 6 位）"
            />
            <span class="absolute right-4 top-1/2 -translate-y-1/2 text-xs text-red-400/70">*</span>
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
            <span v-if="!loading">注册</span>
            <span v-else class="flex items-center justify-center gap-2">
              <svg class="w-4 h-4 animate-spin" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4z"></path>
              </svg>
              注册中...
            </span>
          </button>
        </form>

        <!-- 底部登录入口 -->
        <p class="mt-8 text-center text-sm text-white/25">
          已有账号？
          <router-link
            to="/login"
            class="text-brand-400 hover:text-brand-300 transition-colors font-medium"
          >
            立即登录 &rarr;
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '@/api/user'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const auth = useAuthStore()

const form = reactive({ phone: '', email: '', nickname: '', password: '' })
const error = ref('')
const loading = ref(false)

async function handleRegister() {
  error.value = ''
  if (!form.phone || !form.password) {
    error.value = '请填写手机号和密码'
    return
  }
  if (form.password.length < 6) {
    error.value = '密码至少 6 位'
    return
  }

  loading.value = true
  try {
    await register({
      phone: form.phone,
      password: form.password,
      email: form.email || undefined,
      nickname: form.nickname || undefined,
    })
    // 注册成功后自动登录
    const { login } = await import('@/api/user')
    const res = await login({ phone: form.phone, password: form.password })
    auth.setToken(res.token)
    await auth.fetchUser()
    router.push('/')
  } catch (e: any) {
    error.value = e.message || '注册失败'
  } finally {
    loading.value = false
  }
}
</script>
