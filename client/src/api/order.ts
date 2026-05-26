import { get, post } from './request'
import type { CreateOrderReq, OrderCard, OrderDetail, OrderPreview, PageData } from '@/types/common'

export function getOrderPreview(sessionId: number, seatIds: number[]): Promise<OrderPreview> {
  return get<OrderPreview>('/order/preview', { sessionId, seatIds: seatIds.join(',') })
}

export function createOrder(data: CreateOrderReq): Promise<{ orderId: number; orderNo: string }> {
  return post<{ orderId: number; orderNo: string }>('/order/create', data)
}

export function payOrder(orderId: number): Promise<void> {
  return post<void>(`/order/${orderId}/pay`)
}

export function cancelOrder(orderId: number): Promise<void> {
  return post<void>(`/order/${orderId}/cancel`)
}

export function getOrderList(params: {
  status?: string
  page?: number
  pageSize?: number
}): Promise<PageData<OrderCard>> {
  return get<PageData<OrderCard>>('/order/list', params)
}

export function getOrderDetail(orderId: number): Promise<OrderDetail> {
  return get<OrderDetail>(`/order/${orderId}`)
}
