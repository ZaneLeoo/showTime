import { get } from './request'
import type { EventCard, EventDetail, PageData } from '@/types/common'

/** 演出列表（支持筛选/搜索/排序） */
export function getEventList(params: {
  categoryId?: number
  city?: string
  keyword?: string
  sortBy?: string
  page?: number
  pageSize?: number
}): Promise<PageData<EventCard>> {
  return get<PageData<EventCard>>('/event/list', params)
}

/** 演出详情 */
export function getEventDetail(id: number): Promise<EventDetail> {
  return get<EventDetail>(`/event/${id}`)
}
