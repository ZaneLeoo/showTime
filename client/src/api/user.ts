import { post, get } from './request'
import type { LoginReq, LoginRes, RegisterReq, UserInfo } from '@/types/common'

/** 登录 */
export function login(data: LoginReq): Promise<LoginRes> {
  return post<LoginRes>('/user/login', data)
}

/** 注册 */
export function register(data: RegisterReq): Promise<void> {
  return post<void>('/user/register', data)
}

/** 获取当前用户信息 */
export function getUserInfo(): Promise<UserInfo> {
  return get<UserInfo>('/user/info')
}
