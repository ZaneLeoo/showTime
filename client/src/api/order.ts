import { get, post } from './request'
import type { CreateOrderReq, OrderCard, OrderDetail, PageData } from '@/types/common'

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
