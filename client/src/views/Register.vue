<template>
  <div class="min-h-[calc(100vh-64px)] flex relative"
       style="background: url('/img/login&register_image.png') center/cover no-repeat;">
    <!-- 左侧叠加层 -->
    <div class="hidden lg:flex w-5/12 items-center justify-center relative overflow-hidden"
         style="background: linear-gradient(135deg, rgba(78,205,196,0.85), rgba(255,107,107,0.8)); backdrop-filter: blur(0.5px);">
      <div class="absolute inset-0 opacity-10">
        <div class="absolute top-20 right-10 w-60 h-60 rounded-full bg-white" />
        <div class="absolute bottom-20 left-10 w-80 h-80 rounded-full bg-white" />
        <div class="absolute top-1/3 left-1/3 w-40 h-40 rounded-full bg-white" />
      </div>
      <div class="relative z-10 text-white text-center px-12">
        <div class="text-6xl mb-6">✨</div>
        <h2 class="text-3xl font-bold mb-4">加入 ShowTime</h2>
        <p class="text-white/80 text-lg leading-relaxed">
          注册即可在线选座购票<br />
          热门演出不再错过
        </p>
      </div>
    </div>

    <!-- 右侧表单区 -->
    <div class="flex-1 flex items-center justify-center px-6 py-12">
      <div class="w-full max-w-sm bg-white/90 backdrop-blur-xl rounded-card shadow-lg p-8">
        <h1 class="text-2xl font-bold text-[#2D3436] mb-2">创建账号</h1>
        <p class="text-[#636E72] mb-8">注册 ShowTime，开启购票之旅</p>

        <form @submit.prevent="handleRegister" class="space-y-5">
          <div>
            <label class="block text-sm font-medium text-[#2D3436] mb-2">手机号 *</label>
            <input
              v-model="form.phone"
              type="text"
              maxlength="11"
              class="input-field"
              placeholder="请输入手机号"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-[#2D3436] mb-2">密码 *</label>
            <input
              v-model="form.password"
              type="password"
              class="input-field"
              placeholder="请设置密码"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-[#2D3436] mb-2">昵称</label>
            <input
              v-model="form.nickname"
              type="text"
              class="input-field"
              placeholder="给自己取个昵称（选填）"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-[#2D3436] mb-2">邮箱</label>
            <input
              v-model="form.email"
              type="email"
              class="input-field"
              placeholder="请输入邮箱（选填）"
            />
          </div>

          <div v-if="error" class="text-sm text-red-500 bg-red-50 rounded-card px-4 py-3">
            {{ error }}
          </div>

          <button
            type="submit"
            class="btn-primary w-full"
            :disabled="loading"
          >
            {{ loading ? '注册中...' : '注册' }}
          </button>
        </form>

        <p class="mt-8 text-center text-sm text-[#636E72]">
          已有账号？
          <router-link to="/login" class="text-primary-500 font-medium hover:text-primary-600 transition-colors">
            立即登录
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '@/api/user'

const router = useRouter()

const form = reactive({ phone: '', password: '', nickname: '', email: '' })
const loading = ref(false)
const error = ref('')

async function handleRegister() {
  if (!form.phone || !form.password) {
    error.value = '请填写手机号和密码'
    return
  }
  loading.value = true
  error.value = ''
  try {
    await register({
      phone: form.phone,
      password: form.password,
      nickname: form.nickname || undefined,
      email: form.email || undefined,
    })
    router.push('/login')
  } catch (e: any) {
    error.value = e.message || '注册失败'
  } finally {
    loading.value = false
  }
}
</script>
