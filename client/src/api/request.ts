import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig } from 'axios'
import type { Result } from '@/types/common'

const instance: AxiosInstance = axios.create({
  baseURL: '/api',
  timeout: 10000,
})

// 请求拦截器 — 携带 token
instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 响应拦截器 — 统一处理错误
instance.interceptors.response.use(
  (response) => {
    const result: Result = response.data
    if (result.code !== 200) {
      // 未登录 → 跳转登录页
      if (result.code === 4001) {
        localStorage.removeItem('token')
        window.location.href = '/login'
      }
      return Promise.reject(new Error(result.message || '请求失败'))
    }
    return response
  },
  (error) => {
    return Promise.reject(error)
  },
)

/**
 * 封装请求方法，返回 Result.data 的类型
 */
export async function get<T>(url: string, params?: any): Promise<T> {
  const res = await instance.get<Result<T>>(url, { params })
  return res.data.data
}

export async function post<T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> {
  const res = await instance.post<Result<T>>(url, data, config)
  return res.data.data
}

export async function put<T>(url: string, data?: any): Promise<T> {
  const res = await instance.put<Result<T>>(url, data)
  return res.data.data
}

export async function del<T>(url: string): Promise<T> {
  const res = await instance.delete<Result<T>>(url)
  return res.data.data
}

export default instance
