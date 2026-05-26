# Admin Panel Design

> ShowTime 管理后台完整设计
> 2026-05-26

## Overview

- **UI Framework**: Naive UI (native dark theme, tree-shaking, TypeScript-first)
- **Auth**: Reuse users table, add `role` field (user/admin)
- **Scope**: Event CRUD, Session CRUD, Venue CRUD, Sales Reports

---

## 1. Database

```
ALTER TABLE users ADD COLUMN role ENUM('user','admin') DEFAULT 'user' COMMENT '角色';
```

No other schema changes. Existing tables (events, event_sessions, venues, seats, orders, payments) already support required CRUD.

---

## 2. Backend

### 2.1 Role Interceptor

All `/api/admin/**` endpoints require `role == admin`. Implement via Spring `HandlerInterceptor`.

### 2.2 Controllers

**AdminEventController** — `/api/admin/event`
- `GET /list` — paginated event list (search, status filter)
- `POST /` — create event
- `PUT /{id}` — update event
- `DELETE /{id}` — delete (soft: set status=3)

**AdminSessionController** — `/api/admin`
- `GET /event/{eventId}/session/list` — sessions for an event
- `POST /event/{eventId}/session` — create session
- `PUT /session/{id}` — update session
- `DELETE /session/{id}` — delete session

**AdminVenueController** — `/api/admin/venue`
- `GET /list` — paginated venue list
- `POST /` — create venue
- `PUT /{id}` — update venue
- `DELETE /{id}` — delete venue

**AdminReportController** — `/api/admin/report`
- `GET /dashboard` — today's orders, revenue, top events
- `GET /sales` — sales by date range (daily/monthly aggregation)

### 2.3 Services

- `AdminEventService` — CRUD with poster upload handling
- `AdminSessionService` — CRUD with seat batch generation
- `AdminVenueService` — simple CRUD
- `AdminReportService` — aggregation queries on orders/payments

### 2.4 DTOs

- `AdminEventRequest` — title, description, posterUrl, duration, categoryId, venueId, status
- `AdminSessionRequest` — eventId, sessionTime, status, zones (name, price, seatRows, seatCols)
- `AdminVenueRequest` — name, city, address, description

---

## 3. Frontend

### 3.1 Routes

```
/admin              redirect → /admin/dashboard
/admin/dashboard    Dashboard with stats
/admin/events       Event management list
/admin/events/:id   Edit event + nested session management
/admin/venues       Venue management list
/admin/reports      Sales reports with charts
```

### 3.2 Layout

```
┌──────────┬──────────────────────────┐
│ Sidebar  │  Header (user info,      │
│          │         back to C端,     │
│ 仪表盘   │         logout)          │
│ 演出管理 │──────────────────────────│
│ 场馆管理 │                          │
│ 销售报表 │     <router-view>        │
│          │                          │
│ 返回前台 │                          │
└──────────┴──────────────────────────┘
```

### 3.3 Naive UI Components Used

- `n-layout`, `n-layout-sider`, `n-layout-content`, `n-layout-header`
- `n-menu` — sidebar with active state
- `n-data-table` — paginated + sortable tables
- `n-form`, `n-input`, `n-select`, `n-upload`, `n-date-picker` — forms
- `n-card`, `n-statistic` — dashboard cards
- `n-modal` — venue CRUD dialogs
- `n-message` — toast feedback
- `n-tag` — status badges
- `n-button` — actions

### 3.4 Route Guard

`meta.requiresAdmin` checks `authStore.isAdmin`. Non-admin users redirected to home.

### 3.5 Pages

| Page | Description |
|------|-------------|
| Dashboard | 4 stat cards (today orders, revenue, users, active events) + recent orders table + top events chart |
| Event List | DataTable with search, status filter, poster thumbnail, edit/delete actions, create button |
| Event Edit | Full page: event form + embedded sessions table with create/edit/delete |
| Venue List | DataTable + create/edit via Modal form |
| Reports | Date range picker + aggregation toggle (daily/monthly) + revenue table |

---

## 4. Files to Create/Modify

### Create (Backend)
- `server/.../controller/AdminEventController.java`
- `server/.../controller/AdminSessionController.java`
- `server/.../controller/AdminVenueController.java`
- `server/.../controller/AdminReportController.java`
- `server/.../service/AdminEventService.java`
- `server/.../service/AdminSessionService.java`
- `server/.../service/AdminVenueService.java`
- `server/.../service/AdminReportService.java`
- `server/.../dto/AdminEventRequest.java`
- `server/.../dto/AdminSessionRequest.java`
- `server/.../dto/AdminVenueRequest.java`
- `server/.../config/AdminInterceptor.java`
- `server/.../vo/AdminDashboardVO.java`

### Create (Frontend)
- `client/src/views/admin/AdminLayout.vue`
- `client/src/views/admin/Dashboard.vue`
- `client/src/views/admin/EventList.vue`
- `client/src/views/admin/EventEdit.vue`
- `client/src/views/admin/VenueList.vue`
- `client/src/views/admin/Report.vue`
- `client/src/api/admin.ts`

### Modify
- `server/.../entity/User.java` — add `role` field ✅ done
- `server/.../vo/UserInfoVO.java` — add `role` field ✅ done
- `server/.../config/` — register AdminInterceptor
- `client/src/types/common.ts` — add `role` to UserInfo ✅ done
- `client/src/stores/auth.ts` — add `isAdmin` computed ✅ done
- `client/src/router/index.ts` — add admin routes + guard
- `sql/init.sql` — add `role` column ✅ done

---

## 5. Non-goals

- No image upload server (poster URL input field only, reuse mock image workflow)
- No RBAC beyond user/admin distinction
- No real payment integration in admin (view-only reports)
