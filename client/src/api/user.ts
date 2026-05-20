import { get, post, put } from './request'
import type { LoginReq, LoginRes, RegisterReq, UpdateProfileReq, UserInfo } from '@/types/common'

export function login(data: LoginReq): Promise<LoginRes> {
  return post<LoginRes>('/user/login', data)
}

export function register(data: RegisterReq): Promise<void> {
  return post<void>('/user/register', data)
}

export function getUserInfo(): Promise<UserInfo> {
  return get<UserInfo>('/user/info')
}

export function updateProfile(data: UpdateProfileReq): Promise<void> {
  return put<void>('/user/profile', data)
}
