import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login as loginApi, getUserInfo } from '@/api/user'
import type { UserInfo, LoginReq } from '@/types/common'

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string>(localStorage.getItem('token') || '')
  const user = ref<UserInfo | null>(null)

  /** 是否已登录 */
  const isLoggedIn = () => !!token.value

  /** 登录 */
  async function login(data: LoginReq) {
    const res = await loginApi(data)
    token.value = res.token
    user.value = res.userInfo
    localStorage.setItem('token', res.token)
  }

  /** 获取用户信息 */
  async function fetchUser() {
    if (!token.value) return
    try {
      user.value = await getUserInfo()
    } catch {
      // token 失效
      logout()
    }
  }

  /** 退出 */
  function logout() {
    token.value = ''
    user.value = null
    localStorage.removeItem('token')
  }

  return { token, user, isLoggedIn, login, fetchUser, logout }
})
