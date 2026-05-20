<template>
  <div class="min-h-[calc(100vh-4rem)] flex items-center justify-center px-4">
    <div class="w-full max-w-md">
      <!-- 装饰光晕 -->
      <div class="relative">
        <div class="absolute -top-20 left-1/2 -translate-x-1/2 w-64 h-64 bg-brand-500/10 rounded-full blur-3xl"></div>
      </div>

      <div class="relative glass-panel p-8 md:p-10">
        <!-- 标题 -->
        <div class="text-center mb-8">
          <h1 class="font-display text-3xl font-bold">欢迎回来</h1>
          <p class="mt-2 text-sm text-white/35">登录你的 ShowTime 账户</p>
        </div>

        <!-- 表单 -->
        <form @submit.prevent="handleLogin" class="space-y-5">
          <div>
            <label class="block text-sm text-white/50 mb-2">手机号</label>
            <input
              v-model="form.phone"
              type="text"
              class="input-field"
              placeholder="输入手机号"
              maxlength="11"
            />
          </div>

          <div>
            <label class="block text-sm text-white/50 mb-2">密码</label>
            <input
              v-model="form.password"
              type="password"
              class="input-field"
              placeholder="输入密码"
            />
          </div>

          <p v-if="error" class="text-sm text-red-400">{{ error }}</p>

          <button
            type="submit"
            :disabled="loading"
            class="btn-brand w-full !py-3.5 text-base"
          >
            {{ loading ? '登录中...' : '登录' }}
          </button>
        </form>

        <!-- 注册入口 -->
        <p class="mt-6 text-center text-sm text-white/35">
          还没有账号？
          <router-link to="/register" class="text-brand-400 hover:text-brand-300 transition-colors">
            立即注册
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
