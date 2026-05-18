// ==================== 通用类型 ====================

/** 后端统一返回结构 */
export interface Result<T = any> {
  code: number
  message: string
  data: T
}

/** 分页数据 */
export interface PageData<T> {
  records: T[]
  total: number
  page: number
  pageSize: number
}

// ==================== 用户相关 ====================

export interface LoginReq {
  phone: string
  password: string
}

export interface LoginRes {
  token: string
  userInfo: UserInfo
}

export interface RegisterReq {
  phone: string
  password: string
  email?: string
  nickname?: string
}

export interface UserInfo {
  id: number
  phone: string
  email?: string
  nickname?: string
  avatarUrl?: string
}

// ==================== 演出相关 ====================

export interface EventCard {
  id: number
  title: string
  posterUrl: string
  categoryName: string
  venueName: string
  city: string
  status: number        // 0=即将开售 1=在售 2=售罄 3=已结束
  minPrice: number
  earliestTime: string
}

export interface EventDetail {
  id: number
  title: string
  description: string
  posterUrl: string
  duration: number
  categoryName: string
  venue: {
    id: number
    name: string
    city: string
    address: string
  }
  sessions: SessionItem[]
}

export interface SessionItem {
  id: number
  sessionTime: string
  status: number        // 0=取消 1=正常 2=已结束
  zones: ZoneInfo[]
}

export interface ZoneInfo {
  zoneName: string      // VIP / A / B / C
  price: number
  availableCount: number
  totalCount: number
}

// ==================== 座位相关 ====================

export interface SeatInfo {
  id: number
  zoneName: string
  seatRow: string
  seatCol: number
  price: number
  status: 'available' | 'locked' | 'sold'
}

// ==================== 订单相关 ====================

export interface OrderInfo {
  id: number
  orderNo: string
  status: 'pending' | 'paid' | 'cancelled' | 'expired'
  totalAmount: number
  ticketCount: number
  createdAt: string
  tickets: TicketInfo[]
}

export interface TicketInfo {
  seatId: number
  zoneName: string
  seatRow: string
  seatCol: number
  price: number
  eventTitle: string
  sessionTime: string
  venueName: string
}
