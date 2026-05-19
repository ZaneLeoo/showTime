<template>
  <div class="min-h-[calc(100vh-64px)] flex">
    <!-- 左侧装饰区 -->
    <div class="hidden lg:flex w-5/12 bg-gradient-to-br from-primary-400 via-primary-500 to-mint-400 items-center justify-center relative overflow-hidden">
      <div class="absolute inset-0 opacity-10">
        <div class="absolute top-20 left-10 w-60 h-60 rounded-full bg-white" />
        <div class="absolute bottom-20 right-10 w-80 h-80 rounded-full bg-white" />
        <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-40 h-40 rounded-full bg-white" />
      </div>
      <div class="relative z-10 text-white text-center px-12">
        <div class="text-6xl mb-6">🎭</div>
        <h2 class="text-3xl font-bold mb-4">发现精彩演出</h2>
        <p class="text-white/80 text-lg leading-relaxed">
          登录 ShowTime，开始你的演出之旅<br />
          演唱会 · 话剧 · 音乐节 · 更多精彩等你来
        </p>
      </div>
    </div>

    <!-- 右侧表单区 -->
    <div class="flex-1 flex items-center justify-center px-6 py-12">
      <div class="w-full max-w-sm">
        <h1 class="text-2xl font-bold text-surface-dark mb-2">欢迎回来</h1>
        <p class="text-surface-muted mb-8">登录你的 ShowTime 账号</p>

        <form @submit.prevent="handleLogin" class="space-y-5">
          <div>
            <label class="block text-sm font-medium text-surface-dark mb-2">手机号</label>
            <input
              v-model="form.phone"
              type="text"
              maxlength="11"
              class="input-field"
              placeholder="请输入手机号"
              autocomplete="tel"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-surface-dark mb-2">密码</label>
            <input
              v-model="form.password"
              type="password"
              class="input-field"
              placeholder="请输入密码"
              autocomplete="current-password"
            />
          </div>

          <button
            type="submit"
            class="btn-primary w-full"
            :disabled="loading"
          >
            {{ loading ? '登录中...' : '登录' }}
          </button>
        </form>

        <p class="mt-8 text-center text-sm text-surface-muted">
          还没有账号？
          <router-link to="/register" class="text-primary-500 font-medium hover:text-primary-600 transition-colors">
            立即注册
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const router = useRouter()

const form = reactive({ phone: '', password: '' })
const loading = ref(false)
const error = ref('')

async function handleLogin() {
  if (!form.phone || !form.password) {
    error.value = '请填写手机号和密码'
    return
  }
  loading.value = true
  error.value = ''
  try {
    await auth.login(form)
    router.push('/')
  } catch (e: any) {
    error.value = e.message || '登录失败'
  } finally {
    loading.value = false
  }
}
</script>
