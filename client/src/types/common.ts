// ==================== 通用类型 ====================

export interface Result<T> {
  code: number
  message: string
  data: T
}

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

export interface UpdateProfileReq {
  nickname?: string
  email?: string
  avatarUrl?: string
}

export interface UserInfo {
  id: number
  phone: string
  email?: string
  nickname?: string
  avatarUrl?: string
  role: string
}

// ==================== 分类相关 ====================

export interface Category {
  id: number
  name: string
  parentId: number | null
  sortOrder: number
}

// ==================== 演出相关 ====================

export interface EventCard {
  id: number
  title: string
  posterUrl: string
  categoryName: string
  venueName: string
  city: string
  status: number          // 0=即将开售 1=在售中 2=已售罄 3=已结束
  minPrice: number
  earliestSessionTime: string
}

export interface EventDetail {
  id: number
  title: string
  description: string
  posterUrl: string
  duration: number
  categoryId: number
  categoryName: string
  venue: VenueInfo
  sessions: SessionInfo[]
}

export interface VenueInfo {
  id: number
  name: string
  city: string
  address: string
}

export interface SessionInfo {
  id: number
  sessionTime: string
  status: number
  zones: ZoneInfo[]
}

export interface ZoneInfo {
  name: string
  price: number
  totalSeats: number
  availableSeats: number
}

// ==================== 座位相关 ====================

export interface SeatInfo {
  id: number
  zoneName: string
  seatRow: string
  seatCol: number
  price: number
  status: 'available' | 'sold' | 'locked' | 'selected'
}

export interface SeatZone {
  name: string
  price: number
  color: string
  seats: SeatInfo[]
}

// ==================== 订单相关 ====================

export interface OrderCard {
  id: number
  orderNo: string
  eventTitle: string
  posterUrl: string
  sessionTime: string
  totalAmount: number
  ticketCount: number
  status: string         // pending / paid / cancelled
  createdAt: string
}

export interface OrderDetail {
  id: number
  orderNo: string
  status: string
  totalAmount: number
  ticketCount: number
  paidAt: string | null
  cancelledAt: string | null
  createdAt: string
  event: {
    id: number
    title: string
    posterUrl: string
    venueName: string
    sessionTime: string
  }
  tickets: TicketInfo[]
  payment: PaymentInfo | null
}

export interface TicketInfo {
  zoneName: string
  seatRow: string
  seatCol: number
  price: number
}

export interface PaymentInfo {
  payMethod: string
  amount: number
  status: string
  tradeNo: string
  paidAt: string | null
}

export interface CreateOrderReq {
  sessionId: number
  seatIds: number[]
}

export interface OrderPreview {
  eventInfo: {
    title: string
    posterUrl: string
    sessionTime: string
    venueName: string
  }
  seats: Array<{
    id: number
    zoneName: string
    seatRow: string
    seatCol: number
    price: number
  }>
  totalPrice: number
  seatCount: number
}

// ==================== Admin 管理后台 ====================

export interface AdminEventCard {
  id: number
  title: string
  posterUrl: string
  duration: number
  categoryName: string
  venueName: string
  status: number
  createdAt: string
}

export interface AdminEventReq {
  title: string
  description?: string
  posterUrl?: string
  duration?: number
  categoryId: number
  venueId: number
  status?: number
}

export interface ZoneConfig {
  name: string
  price: number
  rows: number
  cols: number
}

export interface AdminSessionReq {
  sessionTime: string
  status?: number
  zones: ZoneConfig[]
}

export interface AdminSession {
  id: number
  eventId: number
  sessionTime: string
  status: number
  createdAt: string
}

export interface AdminVenueReq {
  name: string
  city: string
  address: string
  description?: string
}

export interface AdminDashboard {
  todayOrderCount: number
  todayRevenue: number
  todayNewUsers: number
  activeEventCount: number
  recentOrders: Array<{
    orderNo: string
    eventTitle: string
    amount: number
    status: string
    createdAt: string
  }>
  topEvents: Array<{
    eventTitle: string
    ticketCount: number
    revenue: number
  }>
}
