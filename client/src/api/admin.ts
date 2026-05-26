import { get, post, put, del } from './request'
import type {
  AdminEventCard, AdminEventReq, AdminSessionReq, AdminSession,
  AdminVenueReq, AdminDashboard, PageData,
  EventDetail, VenueInfo,
} from '@/types/common'

// ==================== 演出管理 ====================

export function getAdminEventList(params: {
  keyword?: string
  status?: number
  page?: number
  pageSize?: number
}): Promise<PageData<AdminEventCard>> {
  return get<PageData<AdminEventCard>>('/admin/event/list', params)
}

export function createEvent(data: AdminEventReq): Promise<{ id: number }> {
  return post<{ id: number }>('/admin/event', data)
}

export function updateEvent(id: number, data: AdminEventReq): Promise<void> {
  return put<void>(`/admin/event/${id}`, data)
}

export function deleteEvent(id: number): Promise<void> {
  return del<void>(`/admin/event/${id}`)
}

// ==================== 场次管理 ====================

export function getSessionList(eventId: number): Promise<AdminSession[]> {
  return get<AdminSession[]>(`/admin/event/${eventId}/session/list`)
}

export function createSession(eventId: number, data: AdminSessionReq): Promise<{ id: number }> {
  return post<{ id: number }>(`/admin/event/${eventId}/session`, data)
}

export function updateSession(id: number, data: AdminSessionReq): Promise<void> {
  return put<void>(`/admin/session/${id}`, data)
}

export function deleteSession(id: number): Promise<void> {
  return del<void>(`/admin/session/${id}`)
}

// ==================== 场馆管理 ====================

export function getAdminVenueList(params: {
  keyword?: string
  page?: number
  pageSize?: number
}): Promise<PageData<VenueInfo>> {
  return get<PageData<VenueInfo>>('/admin/venue/list', params)
}

export function createVenue(data: AdminVenueReq): Promise<{ id: number }> {
  return post<{ id: number }>('/admin/venue', data)
}

export function updateVenue(id: number, data: AdminVenueReq): Promise<void> {
  return put<void>(`/admin/venue/${id}`, data)
}

export function deleteVenue(id: number): Promise<void> {
  return del<void>(`/admin/venue/${id}`)
}

// ==================== 报表 ====================

export function getDashboard(): Promise<AdminDashboard> {
  return get<AdminDashboard>('/admin/report/dashboard')
}
