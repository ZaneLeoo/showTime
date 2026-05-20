import axios from 'axios'
import type { Result } from '@/types/common'

const http = axios.create({
  baseURL: '/api',
  timeout: 15000,
})

// 请求拦截 — 自动带 token
http.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 响应拦截 — 解包 Result
http.interceptors.response.use(
  (response) => {
    const res = response.data as Result<unknown>
    if (res.code === 200) {
      return res.data as any
    }
    if (res.code === 4001) {
      localStorage.removeItem('token')
      window.location.href = '/login'
    }
    return Promise.reject(new Error(res.message || '请求失败'))
  },
  (error) => {
    const msg = error.response?.data?.message || error.message || '网络异常'
    return Promise.reject(new Error(msg))
  },
)

export function get<T>(url: string, params?: any): Promise<T> {
  return http.get(url, { params }) as any
}

export function post<T>(url: string, data?: any, config?: any): Promise<T> {
  return http.post(url, data, config) as any
}

export function put<T>(url: string, data?: any): Promise<T> {
  return http.put(url, data) as any
}

export function del<T>(url: string): Promise<T> {
  return http.delete(url) as any
}
