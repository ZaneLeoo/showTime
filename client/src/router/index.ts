import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/Home.vue'),
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('@/views/Search.vue'),
    },
    {
      path: '/event/:id',
      name: 'event-detail',
      component: () => import('@/views/EventDetail.vue'),
    },
    {
      path: '/event/:id/seat',
      name: 'seat-select',
      component: () => import('@/views/SeatSelect.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/order/confirm',
      name: 'order-confirm',
      component: () => import('@/views/OrderConfirm.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/order/:id',
      name: 'order-detail',
      component: () => import('@/views/OrderDetail.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/user/orders',
      name: 'my-orders',
      component: () => import('@/views/MyOrders.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login.vue'),
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/Register.vue'),
    },
    {
      path: '/admin',
      component: () => import('@/views/admin/AdminLayout.vue'),
      meta: { requiresAuth: true, requiresAdmin: true },
      children: [
        { path: '', redirect: '/admin/dashboard' },
        { path: 'dashboard', name: 'admin-dashboard', component: () => import('@/views/admin/Dashboard.vue') },
        { path: 'events', name: 'admin-events', component: () => import('@/views/admin/EventList.vue') },
        { path: 'events/:id', name: 'admin-event-edit', component: () => import('@/views/admin/EventEdit.vue') },
        { path: 'venues', name: 'admin-venues', component: () => import('@/views/admin/VenueList.vue') },
        { path: 'reports', name: 'admin-reports', component: () => import('@/views/admin/Report.vue') },
      ],
    },
  ],
})

router.beforeEach((to, _from, next) => {
  // Admin 路由诊断日志
  if (to.path.startsWith('/admin')) {
    console.log('[guard] to:', to.path, 'meta:', to.meta)
    const t = localStorage.getItem('token')
    console.log('[guard] token:', t ? t.substring(0, 20) + '...' : null)
    try {
      const auth = useAuthStore()
      console.log('[guard] user:', auth.user, 'isAdmin:', auth.isAdmin)
    } catch (e) {
      console.error('[guard] useAuthStore failed:', e)
    }
  }

  if (to.meta.requiresAuth || to.meta.requiresAdmin) {
    const token = localStorage.getItem('token')
    if (!token) {
      console.log('[guard] no token → login')
      next({ name: 'login', query: { redirect: to.fullPath } })
      return
    }
  }
  if (to.meta.requiresAdmin) {
    const auth = useAuthStore()
    if (!auth.isAdmin) {
      console.log('[guard] not admin → home')
      next({ name: 'home' })
      return
    }
  }
  next()
})

export default router
