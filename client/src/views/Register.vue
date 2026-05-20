<template>
  <div class="min-h-[calc(100vh-4rem)] flex items-center justify-center px-4">
    <div class="w-full max-w-md">
      <div class="relative">
        <div class="absolute -top-20 left-1/2 -translate-x-1/2 w-64 h-64 bg-accent-500/10 rounded-full blur-3xl"></div>
      </div>

      <div class="relative glass-panel p-8 md:p-10">
        <!-- 标题 -->
        <div class="text-center mb-8">
          <h1 class="font-display text-3xl font-bold">创建账户</h1>
          <p class="mt-2 text-sm text-white/35">加入 ShowTime，发现更多演出</p>
        </div>

        <!-- 表单 -->
        <form @submit.prevent="handleRegister" class="space-y-5">
          <div>
            <label class="block text-sm text-white/50 mb-2">手机号 <span class="text-red-400">*</span></label>
            <input
              v-model="form.phone"
              type="text"
              class="input-field"
              placeholder="输入手机号"
              maxlength="11"
            />
          </div>

          <div>
            <label class="block text-sm text-white/50 mb-2">邮箱</label>
            <input
              v-model="form.email"
              type="email"
              class="input-field"
              placeholder="选填"
            />
          </div>

          <div>
            <label class="block text-sm text-white/50 mb-2">昵称</label>
            <input
              v-model="form.nickname"
              type="text"
              class="input-field"
              placeholder="选填"
            />
          </div>

          <div>
            <label class="block text-sm text-white/50 mb-2">密码 <span class="text-red-400">*</span></label>
            <input
              v-model="form.password"
              type="password"
              class="input-field"
              placeholder="至少6位密码"
            />
          </div>

          <p v-if="error" class="text-sm text-red-400">{{ error }}</p>

          <button
            type="submit"
            :disabled="loading"
            class="btn-brand w-full !py-3.5 text-base"
          >
            {{ loading ? '注册中...' : '注册' }}
          </button>
        </form>

        <!-- 登录入口 -->
        <p class="mt-6 text-center text-sm text-white/35">
          已有账号？
          <router-link to="/login" class="text-brand-400 hover:text-brand-300 transition-colors">
            立即登录
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
    error.value = '密码至少6位'
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
