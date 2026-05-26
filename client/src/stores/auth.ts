import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { UserInfo } from '@/types/common'
import { getUserInfo } from '@/api/user'

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string>(localStorage.getItem('token') || '')
  const user = ref<UserInfo | null>(null)

  const isLoggedIn = computed(() => !!token.value)
  const isAdmin = computed(() => user.value?.role === 'admin')

  function setToken(t: string) {
    token.value = t
    localStorage.setItem('token', t)
  }

  function clearToken() {
    token.value = ''
    user.value = null
    localStorage.removeItem('token')
  }

  async function fetchUser() {
    if (!token.value) return
    try {
      user.value = await getUserInfo()
    } catch {
      // 网络或服务端临时故障不删 token，下次导航重试
    }
  }

  return { token, user, isLoggedIn, isAdmin, setToken, clearToken, fetchUser }
})
