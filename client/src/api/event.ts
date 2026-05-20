import { get, post } from './request'
import type { EventCard, EventDetail, PageData, SeatZone } from '@/types/common'

export function getEventList(params: {
  categoryId?: number
  city?: string
  keyword?: string
  dateStart?: string
  dateEnd?: string
  sortBy?: string
  page?: number
  pageSize?: number
}): Promise<PageData<EventCard>> {
  return get<PageData<EventCard>>('/event/list', params)
}

export function getEventDetail(id: number): Promise<EventDetail> {
  return get<EventDetail>(`/event/${id}`)
}

export function getSeatMap(sessionId: number): Promise<SeatZone[]> {
  return get<SeatZone[]>(`/event/session/${sessionId}/seats`)
}

export function lockSeats(sessionId: number, seatIds: number[]): Promise<{ lockExpireAt: string }> {
  return post<{ lockExpireAt: string }>('/event/seat/lock', { sessionId, seatIds })
}

export function releaseSeats(sessionId: number, seatIds: number[]): Promise<void> {
  return post<void>('/event/seat/release', { sessionId, seatIds })
}
