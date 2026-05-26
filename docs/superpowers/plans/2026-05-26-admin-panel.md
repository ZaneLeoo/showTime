# Admin Panel Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a complete admin panel (Naive UI + Spring Boot) for managing events, sessions, venues, and viewing sales reports.

**Architecture:** Backend adds `/api/admin/*` controllers behind a role interceptor. Frontend adds `/admin/*` routes with Naive UI layout (sidebar + content). Reuses existing mappers and entities.

**Tech Stack:** Spring Boot + MyBatis-Plus (backend), Vue 3 + Naive UI + Tailwind (frontend), MySQL

---

### Task 1: AdminInterceptor + WebMvcConfig

**Files:**
- Create: `server/src/main/java/com/showtime/interceptor/AdminInterceptor.java`
- Create: `server/src/main/java/com/showtime/config/WebMvcConfig.java`

- [ ] **Step 1: Create AdminInterceptor**

```java
package com.showtime.interceptor;

import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;

    public AdminInterceptor(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        var user = userMapper.selectById(userId);
        if (user == null || !"admin".equals(user.getRole())) {
            throw new BizException(ResultCode.FORBIDDEN, "需要管理员权限");
        }
        return true;
    }
}
```

- [ ] **Step 2: Create WebMvcConfig to register interceptor**

```java
package com.showtime.config;

import com.showtime.interceptor.AdminInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
    }
}
```

- [ ] **Step 3: Verify existing auth interceptor sets request userId attribute**

The existing login interceptor must set `request.setAttribute("userId", user.getId())`. Check `SessionService.requireLogin()` — if it doesn't set this attribute, we need it to.

Run: `grep -rn "requireLogin\|setAttribute.*userId" server/src/main/java/com/showtime/`

If requireLogin doesn't set request attribute, add `.setAttribute("userId", ...)` to its implementation.

- [ ] **Step 4: Commit**

```bash
git add server/src/main/java/com/showtime/interceptor/AdminInterceptor.java \
        server/src/main/java/com/showtime/config/WebMvcConfig.java
git commit -m "feat: AdminInterceptor + WebMvcConfig — /api/admin/* 角色拦截"
```

---

### Task 2: AdminVenueController + Service

**Files:**
- Create: `server/src/main/java/com/showtime/controller/AdminVenueController.java`
- Create: `server/src/main/java/com/showtime/service/AdminVenueService.java`
- Create: `server/src/main/java/com/showtime/dto/AdminVenueRequest.java`

- [ ] **Step 1: Create AdminVenueRequest DTO**

```java
package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "场馆创建/编辑请求")
@Data
public class AdminVenueRequest {

    @Schema(description = "场馆名称", example = "北京鸟巢")
    @NotBlank
    private String name;

    @Schema(description = "所在城市", example = "北京")
    @NotBlank
    private String city;

    @Schema(description = "详细地址")
    @NotBlank
    private String address;

    @Schema(description = "场馆介绍")
    private String description;
}
```

- [ ] **Step 2: Create AdminVenueService**

```java
package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showtime.common.BizException;
import com.showtime.common.PageResult;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminVenueRequest;
import com.showtime.entity.Venue;
import com.showtime.mapper.VenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminVenueService {

    private final VenueMapper venueMapper;

    public PageResult<Venue> list(String keyword, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Venue> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.and(w -> w.like(Venue::getName, keyword)
                              .or().like(Venue::getCity, keyword));
        }
        wrapper.orderByDesc(Venue::getCreatedAt);
        Page<Venue> result = venueMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 10), wrapper);
        return PageResult.of(result.getRecords(), result.getTotal(),
            (int) result.getCurrent(), (int) result.getSize());
    }

    public Venue create(AdminVenueRequest req) {
        Venue venue = new Venue();
        venue.setName(req.getName());
        venue.setCity(req.getCity());
        venue.setAddress(req.getAddress());
        venue.setDescription(req.getDescription());
        venueMapper.insert(venue);
        return venue;
    }

    public Venue update(Integer id, AdminVenueRequest req) {
        Venue venue = venueMapper.selectById(id);
        if (venue == null) throw new BizException(ResultCode.NOT_FOUND, "场馆不存在");
        venue.setName(req.getName());
        venue.setCity(req.getCity());
        venue.setAddress(req.getAddress());
        venue.setDescription(req.getDescription());
        venueMapper.updateById(venue);
        return venue;
    }

    public void delete(Integer id) {
        venueMapper.deleteById(id);
    }
}
```

- [ ] **Step 3: Create AdminVenueController**

```java
package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.AdminVenueRequest;
import com.showtime.entity.Venue;
import com.showtime.service.AdminVenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台-场馆", description = "场馆CRUD")
@RestController
@RequestMapping("/api/admin/venue")
@RequiredArgsConstructor
public class AdminVenueController {

    private final AdminVenueService adminVenueService;

    @Operation(summary = "场馆列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/list")
    public Result<PageResult<Venue>> list(
            @Parameter(description = "搜索关键词") @RequestParam(required = false) String keyword,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.ok(adminVenueService.list(keyword, page, pageSize));
    }

    @Operation(summary = "创建场馆")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public Result<Venue> create(@Valid @RequestBody AdminVenueRequest req) {
        return Result.ok(adminVenueService.create(req));
    }

    @Operation(summary = "编辑场馆")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public Result<Venue> update(@Parameter(description = "场馆ID") @PathVariable Integer id,
                                @Valid @RequestBody AdminVenueRequest req) {
        return Result.ok(adminVenueService.update(id, req));
    }

    @Operation(summary = "删除场馆")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "场馆ID") @PathVariable Integer id) {
        adminVenueService.delete(id);
        return Result.ok();
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add server/src/main/java/com/showtime/controller/AdminVenueController.java \
        server/src/main/java/com/showtime/service/AdminVenueService.java \
        server/src/main/java/com/showtime/dto/AdminVenueRequest.java
git commit -m "feat: 场馆管理 CRUD — AdminVenueController + Service"
```

---

### Task 3: AdminEventController + Service

**Files:**
- Create: `server/src/main/java/com/showtime/controller/AdminEventController.java`
- Create: `server/src/main/java/com/showtime/service/AdminEventService.java`
- Create: `server/src/main/java/com/showtime/dto/AdminEventRequest.java`

- [ ] **Step 1: Create AdminEventRequest DTO**

```java
package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "演出创建/编辑请求")
@Data
public class AdminEventRequest {

    @Schema(description = "演出名称", example = "周杰伦2026巡回演唱会")
    @NotBlank
    private String title;

    @Schema(description = "演出简介")
    private String description;

    @Schema(description = "海报图片URL")
    private String posterUrl;

    @Schema(description = "演出时长（分钟）", example = "120")
    private Integer duration;

    @Schema(description = "所属分类ID")
    @NotNull
    private Integer categoryId;

    @Schema(description = "演出场馆ID")
    @NotNull
    private Integer venueId;

    @Schema(description = "状态：0=即将开售 1=在售 2=售罄 3=已结束")
    private Integer status;
}
```

- [ ] **Step 2: Create AdminEventService**

```java
package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showtime.common.BizException;
import com.showtime.common.PageResult;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminEventRequest;
import com.showtime.entity.Category;
import com.showtime.entity.Event;
import com.showtime.entity.Venue;
import com.showtime.mapper.CategoryMapper;
import com.showtime.mapper.EventMapper;
import com.showtime.mapper.VenueMapper;
import com.showtime.vo.AdminEventVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminEventService {

    private final EventMapper eventMapper;
    private final CategoryMapper categoryMapper;
    private final VenueMapper venueMapper;

    public PageResult<AdminEventVO> list(String keyword, Integer status, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Event> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like(Event::getTitle, keyword);
        }
        if (status != null) {
            wrapper.eq(Event::getStatus, status);
        }
        wrapper.orderByDesc(Event::getCreatedAt);
        Page<Event> result = eventMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 10), wrapper);

        var vos = result.getRecords().stream().map(e -> {
            AdminEventVO vo = new AdminEventVO();
            vo.setId(e.getId());
            vo.setTitle(e.getTitle());
            vo.setPosterUrl(e.getPosterUrl());
            vo.setDuration(e.getDuration());
            vo.setStatus(e.getStatus());
            vo.setCreatedAt(e.getCreatedAt());
            Category cat = categoryMapper.selectById(e.getCategoryId());
            if (cat != null) vo.setCategoryName(cat.getName());
            Venue venue = venueMapper.selectById(e.getVenueId());
            if (venue != null) vo.setVenueName(venue.getName());
            return vo;
        }).toList();

        return PageResult.of(vos, result.getTotal(), (int) result.getCurrent(), (int) result.getSize());
    }

    public Event create(AdminEventRequest req) {
        Event event = new Event();
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setPosterUrl(req.getPosterUrl());
        event.setDuration(req.getDuration());
        event.setCategoryId(req.getCategoryId());
        event.setVenueId(req.getVenueId());
        event.setStatus(req.getStatus() != null ? req.getStatus() : 0);
        eventMapper.insert(event);
        return event;
    }

    public Event update(Integer id, AdminEventRequest req) {
        Event event = eventMapper.selectById(id);
        if (event == null) throw new BizException(ResultCode.NOT_FOUND, "演出不存在");
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setPosterUrl(req.getPosterUrl());
        event.setDuration(req.getDuration());
        event.setCategoryId(req.getCategoryId());
        event.setVenueId(req.getVenueId());
        event.setStatus(req.getStatus());
        eventMapper.updateById(event);
        return event;
    }

    public void delete(Integer id) {
        eventMapper.deleteById(id);
    }
}
```

- [ ] **Step 3: Create AdminEventVO**

```java
package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "管理后台演出列表VO")
@Data
public class AdminEventVO {
    @Schema(description = "演出ID")
    private Integer id;
    @Schema(description = "演出名称")
    private String title;
    @Schema(description = "海报URL")
    private String posterUrl;
    @Schema(description = "演出时长")
    private Integer duration;
    @Schema(description = "分类名称")
    private String categoryName;
    @Schema(description = "场馆名称")
    private String venueName;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
```

- [ ] **Step 4: Create AdminEventController**

```java
package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.AdminEventRequest;
import com.showtime.entity.Event;
import com.showtime.service.AdminEventService;
import com.showtime.vo.AdminEventVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台-演出", description = "演出CRUD")
@RestController
@RequestMapping("/api/admin/event")
@RequiredArgsConstructor
public class AdminEventController {

    private final AdminEventService adminEventService;

    @Operation(summary = "演出列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/list")
    public Result<PageResult<AdminEventVO>> list(
            @Parameter(description = "搜索关键词") @RequestParam(required = false) String keyword,
            @Parameter(description = "状态筛选") @RequestParam(required = false) Integer status,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.ok(adminEventService.list(keyword, status, page, pageSize));
    }

    @Operation(summary = "创建演出")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public Result<Event> create(@Valid @RequestBody AdminEventRequest req) {
        return Result.ok(adminEventService.create(req));
    }

    @Operation(summary = "编辑演出")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public Result<Event> update(@Parameter(description = "演出ID") @PathVariable Integer id,
                                @Valid @RequestBody AdminEventRequest req) {
        return Result.ok(adminEventService.update(id, req));
    }

    @Operation(summary = "删除演出")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "演出ID") @PathVariable Integer id) {
        adminEventService.delete(id);
        return Result.ok();
    }
}
```

- [ ] **Step 5: Commit**

```bash
git add server/src/main/java/com/showtime/controller/AdminEventController.java \
        server/src/main/java/com/showtime/service/AdminEventService.java \
        server/src/main/java/com/showtime/dto/AdminEventRequest.java \
        server/src/main/java/com/showtime/vo/AdminEventVO.java
git commit -m "feat: 演出管理 CRUD — AdminEventController + Service"
```

---

### Task 4: AdminSessionController + Service

**Files:**
- Create: `server/src/main/java/com/showtime/controller/AdminSessionController.java`
- Create: `server/src/main/java/com/showtime/service/AdminSessionService.java`
- Create: `server/src/main/java/com/showtime/dto/AdminSessionRequest.java`

- [ ] **Step 1: Create AdminSessionRequest DTO**

```java
package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "场次创建/编辑请求")
@Data
public class AdminSessionRequest {

    @Schema(description = "开演时间")
    @NotNull
    private LocalDateTime sessionTime;

    @Schema(description = "状态：0=取消 1=正常 2=已结束")
    private Integer status;

    @Schema(description = "票档+座位配置")
    @NotEmpty
    private List<ZoneConfig> zones;

    @Data
    @Schema(description = "票档配置")
    public static class ZoneConfig {
        @Schema(description = "区域名称", example = "VIP区")
        @NotEmpty
        private String name;

        @Schema(description = "票价", example = "1680")
        @NotNull
        private BigDecimal price;

        @Schema(description = "排数", example = "3")
        @NotNull
        private Integer rows;

        @Schema(description = "每排座位数", example = "14")
        @NotNull
        private Integer cols;
    }
}
```

- [ ] **Step 2: Create AdminSessionService**

```java
package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.dto.AdminSessionRequest;
import com.showtime.entity.EventSession;
import com.showtime.entity.Seat;
import com.showtime.mapper.EventSessionMapper;
import com.showtime.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSessionService {

    private final EventSessionMapper sessionMapper;
    private final SeatMapper seatMapper;

    public List<EventSession> listByEvent(Integer eventId) {
        LambdaQueryWrapper<EventSession> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EventSession::getEventId, eventId)
               .orderByAsc(EventSession::getSessionTime);
        return sessionMapper.selectList(wrapper);
    }

    @Transactional
    public EventSession create(Integer eventId, AdminSessionRequest req) {
        EventSession session = new EventSession();
        session.setEventId(eventId);
        session.setSessionTime(req.getSessionTime());
        session.setStatus(req.getStatus() != null ? req.getStatus() : 1);
        sessionMapper.insert(session);

        // 按票档生成座位
        for (var zone : req.getZones()) {
            String[] rowLabels = {"A","B","C","D","E","F","G","H","I","J"};
            for (int r = 0; r < zone.getRows(); r++) {
                for (int c = 1; c <= zone.getCols(); c++) {
                    Seat seat = new Seat();
                    seat.setSessionId(session.getId());
                    seat.setZoneName(zone.getName());
                    seat.setSeatRow(rowLabels[r]);
                    seat.setSeatCol(c);
                    seat.setPrice(zone.getPrice());
                    seat.setStatus("available");
                    seatMapper.insert(seat);
                }
            }
        }
        return session;
    }

    public EventSession update(Integer id, AdminSessionRequest req) {
        EventSession session = sessionMapper.selectById(id);
        if (session == null) throw new BizException(ResultCode.NOT_FOUND, "场次不存在");
        session.setSessionTime(req.getSessionTime());
        session.setStatus(req.getStatus());
        sessionMapper.updateById(session);
        return session;
    }

    @Transactional
    public void delete(Integer id) {
        seatMapper.delete(new LambdaQueryWrapper<Seat>().eq(Seat::getSessionId, id));
        sessionMapper.deleteById(id);
    }
}
```

- [ ] **Step 3: Create AdminSessionController**

```java
package com.showtime.controller;

import com.showtime.common.Result;
import com.showtime.dto.AdminSessionRequest;
import com.showtime.entity.EventSession;
import com.showtime.service.AdminSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理后台-场次", description = "场次CRUD + 座位生成")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminSessionController {

    private final AdminSessionService adminSessionService;

    @Operation(summary = "某演出的场次列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/event/{eventId}/session/list")
    public Result<List<EventSession>> list(@Parameter(description = "演出ID") @PathVariable Integer eventId) {
        return Result.ok(adminSessionService.listByEvent(eventId));
    }

    @Operation(summary = "创建场次（含座位生成）")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/event/{eventId}/session")
    public Result<EventSession> create(@Parameter(description = "演出ID") @PathVariable Integer eventId,
                                       @Valid @RequestBody AdminSessionRequest req) {
        return Result.ok(adminSessionService.create(eventId, req));
    }

    @Operation(summary = "编辑场次")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/session/{id}")
    public Result<EventSession> update(@Parameter(description = "场次ID") @PathVariable Integer id,
                                       @Valid @RequestBody AdminSessionRequest req) {
        return Result.ok(adminSessionService.update(id, req));
    }

    @Operation(summary = "删除场次（级联删除座位）")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/session/{id}")
    public Result<Void> delete(@Parameter(description = "场次ID") @PathVariable Integer id) {
        adminSessionService.delete(id);
        return Result.ok();
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add server/src/main/java/com/showtime/controller/AdminSessionController.java \
        server/src/main/java/com/showtime/service/AdminSessionService.java \
        server/src/main/java/com/showtime/dto/AdminSessionRequest.java
git commit -m "feat: 场次管理 CRUD + 座位生成 — AdminSessionController + Service"
```

---

### Task 5: AdminReportController + Service

**Files:**
- Create: `server/src/main/java/com/showtime/controller/AdminReportController.java`
- Create: `server/src/main/java/com/showtime/service/AdminReportService.java`
- Create: `server/src/main/java/com/showtime/vo/AdminDashboardVO.java`

- [ ] **Step 1: Create AdminDashboardVO**

```java
package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台仪表盘")
@Data
public class AdminDashboardVO {

    @Schema(description = "今日订单数")
    private long todayOrderCount;

    @Schema(description = "今日收入")
    private BigDecimal todayRevenue;

    @Schema(description = "今日新增用户")
    private long todayNewUsers;

    @Schema(description = "在售演出数")
    private long activeEventCount;

    @Schema(description = "近期订单")
    private List<RecentOrder> recentOrders;

    @Schema(description = "演出销量排行")
    private List<EventSalesRank> topEvents;

    @Data
    @Schema(description = "近期订单摘要")
    public static class RecentOrder {
        private String orderNo;
        private String eventTitle;
        private BigDecimal amount;
        private String status;
        private String createdAt;
    }

    @Data
    @Schema(description = "演出销量排行")
    public static class EventSalesRank {
        private String eventTitle;
        private long ticketCount;
        private BigDecimal revenue;
    }
}
```

- [ ] **Step 2: Create AdminReportService**

```java
package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.showtime.entity.*;
import com.showtime.mapper.*;
import com.showtime.vo.AdminDashboardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminReportService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final PaymentMapper paymentMapper;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final SeatMapper seatMapper;
    private final EventSessionMapper sessionMapper;

    private static final DateTimeFormatter DISP_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AdminDashboardVO dashboard() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        AdminDashboardVO vo = new AdminDashboardVO();

        // 今日订单
        List<Order> todayOrders = orderMapper.selectList(
            new LambdaQueryWrapper<Order>()
                .ge(Order::getCreatedAt, todayStart)
                .lt(Order::getCreatedAt, todayEnd));
        vo.setTodayOrderCount(todayOrders.size());
        vo.setTodayRevenue(todayOrders.stream()
            .filter(o -> "paid".equals(o.getStatus()))
            .map(Order::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add));

        // 今日新用户
        vo.setTodayNewUsers(userMapper.selectCount(
            new LambdaQueryWrapper<User>()
                .ge(User::getCreatedAt, todayStart)
                .lt(User::getCreatedAt, todayEnd)));

        // 在售演出
        vo.setActiveEventCount(eventMapper.selectCount(
            new LambdaQueryWrapper<Event>().eq(Event::getStatus, 1)));

        // 近期订单 (最近10条)
        List<Order> recent = orderMapper.selectList(
            new LambdaQueryWrapper<Order>()
                .orderByDesc(Order::getCreatedAt)
                .last("LIMIT 10"));
        vo.setRecentOrders(recent.stream().map(o -> {
            var ro = new AdminDashboardVO.RecentOrder();
            ro.setOrderNo(o.getOrderNo());
            ro.setAmount(o.getTotalAmount());
            ro.setStatus(o.getStatus());
            ro.setCreatedAt(o.getCreatedAt().format(DISP_FMT));
            return ro;
        }).toList());

        // 演出销量排行 (top 5)
        List<Order> allPaid = orderMapper.selectList(
            new LambdaQueryWrapper<Order>()
                .eq(Order::getStatus, "paid"));
        Map<Integer, List<Order>> byEvent = new HashMap<>();
        for (Order o : allPaid) {
            List<OrderItem> items = orderItemMapper.selectList(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, o.getId()));
            for (OrderItem item : items) {
                Seat seat = seatMapper.selectById(item.getSeatId());
                if (seat != null) {
                    EventSession session = sessionMapper.selectById(seat.getSessionId());
                    if (session != null) {
                        byEvent.computeIfAbsent(session.getEventId(), k -> new ArrayList<>()).add(o);
                        break;
                    }
                }
            }
        }
        vo.setTopEvents(byEvent.entrySet().stream()
            .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
            .limit(5)
            .map(e -> {
                var rank = new AdminDashboardVO.EventSalesRank();
                Event event = eventMapper.selectById(e.getKey());
                rank.setEventTitle(event != null ? event.getTitle() : "未知");
                rank.setTicketCount(e.getValue().size());
                rank.setRevenue(e.getValue().stream()
                    .map(Order::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
                return rank;
            }).toList());

        return vo;
    }
}
```

- [ ] **Step 3: Create AdminReportController**

```java
package com.showtime.controller;

import com.showtime.common.Result;
import com.showtime.service.AdminReportService;
import com.showtime.vo.AdminDashboardVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台-报表", description = "仪表盘 + 销售报表")
@RestController
@RequestMapping("/api/admin/report")
@RequiredArgsConstructor
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Operation(summary = "仪表盘数据")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/dashboard")
    public Result<AdminDashboardVO> dashboard() {
        return Result.ok(adminReportService.dashboard());
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add server/src/main/java/com/showtime/controller/AdminReportController.java \
        server/src/main/java/com/showtime/service/AdminReportService.java \
        server/src/main/java/com/showtime/vo/AdminDashboardVO.java
git commit -m "feat: 仪表盘 + 报表 — AdminReportController + Service"
```

---

### Task 6: Check SessionService requireLogin sets userId attribute

**Files:**
- Modify: `server/src/main/java/com/showtime/service/SessionService.java`

- [ ] **Step 1: Read the current requireLogin implementation**

Run: `grep -A15 "public.*requireLogin" server/src/main/java/com/showtime/service/SessionService.java`

If the method does NOT set `request.setAttribute("userId", user.getId())`, add it.

- [ ] **Step 2: Add setAttribute if missing**

Add this line right after user lookup succeeds (inside requireLogin method, after confirming user exists):
```java
request.setAttribute("userId", user.getId());
```

- [ ] **Step 3: Commit**

```bash
git add server/src/main/java/com/showtime/service/SessionService.java
git commit -m "fix: requireLogin 设置 request userId 属性供 AdminInterceptor 使用"
```

---

### Task 7: Frontend Admin API Layer

**Files:**
- Create: `client/src/api/admin.ts`
- Modify: `client/src/types/common.ts` — add admin types

- [ ] **Step 1: Add admin types to common.ts**

```typescript
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
```

Append this to the end of `client/src/types/common.ts`.

- [ ] **Step 2: Create admin.ts API**

```typescript
import { get, post, put, del } from './request'
import type {
  AdminEventCard, AdminEventReq, AdminSessionReq, AdminSession,
  AdminVenueReq, AdminDashboard, PageData,
} from '@/types/common'
import type { EventDetail, VenueInfo } from '@/types/common'

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
```

- [ ] **Step 3: Commit**

```bash
git add client/src/api/admin.ts client/src/types/common.ts
git commit -m "feat: Admin API 层 + 管理后台类型定义"
```

---

### Task 8: AdminLayout.vue

**Files:**
- Create: `client/src/views/admin/AdminLayout.vue`

- [ ] **Step 1: Create AdminLayout with Naive UI sidebar**

```vue
<template>
  <n-layout class="!h-screen !bg-transparent">
    <!-- 侧边栏 -->
    <n-layout-sider
      bordered
      collapse-mode="width"
      :collapsed-width="64"
      :width="200"
      :native-scrollbar="false"
      class="!bg-black/40"
    >
      <div class="flex items-center gap-2 px-5 h-16 border-b border-white/[0.06]">
        <span class="font-display text-lg font-bold bg-gradient-to-r from-brand-400 to-accent-400 bg-clip-text text-transparent">
          ShowTime
        </span>
      </div>
      <n-menu
        :value="activeKey"
        :options="menuOptions"
        :root-indent="20"
        :indent="12"
        @update:value="onMenuChange"
      />
    </n-layout-sider>

    <!-- 主区域 -->
    <n-layout class="!bg-transparent">
      <n-layout-header bordered class="!bg-transparent h-16 flex items-center justify-end px-6 gap-3">
        <router-link to="/" class="text-sm text-white/40 hover:text-white/70 transition-colors">
          返回前台
        </router-link>
        <n-tag type="warning" size="small" round>管理员</n-tag>
        <span class="text-sm text-white/50">{{ auth.user?.nickname || auth.user?.phone }}</span>
        <n-button quaternary size="small" @click="logout">退出</n-button>
      </n-layout-header>

      <n-layout-content class="!p-6">
        <router-view />
      </n-layout-content>
    </n-layout>
  </n-layout>
</template>

<script setup lang="ts">
import { computed, h } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { NIcon } from 'naive-ui'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const route = useRoute()
const auth = useAuthStore()

const activeKey = computed(() => route.path)

const menuOptions = [
  { label: '仪表盘', key: '/admin/dashboard' },
  { label: '演出管理', key: '/admin/events' },
  { label: '场馆管理', key: '/admin/venues' },
  { label: '销售报表', key: '/admin/reports' },
]

function onMenuChange(key: string) {
  router.push(key)
}

function logout() {
  auth.clearToken()
  router.push('/login')
}
</script>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/AdminLayout.vue
git commit -m "feat: AdminLayout — Naive UI 侧边栏 + 顶栏布局"
```

---

### Task 9: Dashboard.vue

**Files:**
- Create: `client/src/views/admin/Dashboard.vue`

- [ ] **Step 1: Create Dashboard page**

```vue
<template>
  <div>
    <h2 class="font-display text-xl font-bold mb-6">仪表盘</h2>

    <LoadingSpinner v-if="loading" />

    <template v-else-if="data">
      <!-- 统计卡片 -->
      <div class="grid grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日订单" :value="data.todayOrderCount" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日收入" :value="data.todayRevenue">
            <template #prefix>¥</template>
          </n-statistic>
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="今日新用户" :value="data.todayNewUsers" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="在售演出" :value="data.activeEventCount" />
        </n-card>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- 近期订单 -->
        <n-card title="近期订单" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-data-table
            :columns="orderColumns"
            :data="data.recentOrders"
            :bordered="false"
            size="small"
          />
        </n-card>

        <!-- 演出排行 -->
        <n-card title="演出销量排行 TOP 5" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-data-table
            :columns="rankColumns"
            :data="data.topEvents"
            :bordered="false"
            size="small"
          />
        </n-card>
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { NTag } from 'naive-ui'
import { getDashboard } from '@/api/admin'
import type { AdminDashboard } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'

const loading = ref(true)
const data = ref<AdminDashboard | null>(null)

const orderColumns = [
  { title: '订单号', key: 'orderNo', width: 140 },
  { title: '金额', key: 'amount', width: 80 },
  { title: '状态', key: 'status', width: 80, render: (row: any) => {
    const m: Record<string, string> = { pending: '待支付', paid: '已支付', cancelled: '已取消' }
    return h(NTag, { size: 'small', type: row.status === 'paid' ? 'success' : row.status === 'pending' ? 'warning' : 'default' }, { default: () => m[row.status] || row.status })
  }},
  { title: '时间', key: 'createdAt', width: 140 },
]

const rankColumns = [
  { title: '演出', key: 'eventTitle', ellipsis: true },
  { title: '售票数', key: 'ticketCount', width: 80 },
  { title: '收入', key: 'revenue', width: 100, render: (row: any) => `¥${row.revenue}` },
]

onMounted(async () => {
  try {
    data.value = await getDashboard()
  } finally {
    loading.value = false
  }
})
</script>
```

> **Note:** If `<template #prefix>` syntax errors, use `h` render function for the prefix: `render: (row: any) => '¥' + row.revenue`.

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/Dashboard.vue
git commit -m "feat: Dashboard — 统计卡片 + 近期订单 + 演出排行"
```

---

### Task 10: VenueList.vue

**Files:**
- Create: `client/src/views/admin/VenueList.vue`

- [ ] **Step 1: Create VenueList with table + modal form**

```vue
<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h2 class="font-display text-xl font-bold">场馆管理</h2>
      <n-button type="primary" @click="openCreate">新建场馆</n-button>
    </div>

    <n-data-table
      :columns="columns"
      :data="list"
      :loading="loading"
      :pagination="pagination"
      :bordered="false"
      @update:page="onPageChange"
    />

    <!-- 创建/编辑 Modal -->
    <n-modal v-model:show="showModal" title="场馆信息" preset="card" style="width:480px">
      <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" label-width="80">
        <n-form-item label="名称" path="name">
          <n-input v-model:value="form.name" />
        </n-form-item>
        <n-form-item label="城市" path="city">
          <n-input v-model:value="form.city" />
        </n-form-item>
        <n-form-item label="地址" path="address">
          <n-input v-model:value="form.address" />
        </n-form-item>
        <n-form-item label="介绍" path="description">
          <n-input v-model:value="form.description" type="textarea" />
        </n-form-item>
      </n-form>
      <template #footer>
        <div class="flex justify-end gap-2">
          <n-button @click="showModal = false">取消</n-button>
          <n-button type="primary" @click="submitForm" :loading="submitting">保存</n-button>
        </div>
      </template>
    </n-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, h } from 'vue'
import { NButton, useMessage } from 'naive-ui'
import { getAdminVenueList, createVenue, updateVenue, deleteVenue } from '@/api/admin'
import type { VenueInfo, AdminVenueReq } from '@/types/common'

const message = useMessage()
const list = ref<VenueInfo[]>([])
const loading = ref(false)
const showModal = ref(false)
const submitting = ref(false)
const editingId = ref<number | null>(null)
const page = ref(1)
const total = ref(0)
const pageSize = 10

const pagination = computed(() => ({
  page: page.value,
  pageSize,
  itemCount: total.value,
}))

const form = ref<AdminVenueReq>({ name: '', city: '', address: '', description: '' })
const rules = {
  name: { required: true, message: '请输入场馆名称', trigger: 'blur' },
  city: { required: true, message: '请输入城市', trigger: 'blur' },
  address: { required: true, message: '请输入地址', trigger: 'blur' },
}

const columns = [
  { title: 'ID', key: 'id', width: 60 },
  { title: '名称', key: 'name', ellipsis: true },
  { title: '城市', key: 'city', width: 100 },
  { title: '地址', key: 'address', ellipsis: true },
  {
    title: '操作', key: 'actions', width: 140,
    render: (row: VenueInfo) => h('div', { class: 'flex gap-2' }, [
      h(NButton, { size: 'small', onClick: () => openEdit(row) }, { default: () => '编辑' }),
      h(NButton, { size: 'small', type: 'error', onClick: () => handleDelete(row.id) }, { default: () => '删除' }),
    ]),
  },
]

async function fetchList() {
  loading.value = true
  try {
    const res = await getAdminVenueList({ page: page.value, pageSize })
    list.value = res.records
    total.value = res.total
  } finally {
    loading.value = false
  }
}

function openCreate() {
  editingId.value = null
  form.value = { name: '', city: '', address: '', description: '' }
  showModal.value = true
}

function openEdit(row: VenueInfo) {
  editingId.value = row.id
  form.value = { name: row.name, city: row.city, address: row.address, description: row.description }
  showModal.value = true
}

async function submitForm() {
  submitting.value = true
  try {
    if (editingId.value) {
      await updateVenue(editingId.value, form.value)
      message.success('更新成功')
    } else {
      await createVenue(form.value)
      message.success('创建成功')
    }
    showModal.value = false
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  } finally {
    submitting.value = false
  }
}

async function handleDelete(id: number) {
  if (!confirm('确认删除？')) return
  try {
    await deleteVenue(id)
    message.success('已删除')
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  }
}

function onPageChange(p: number) {
  page.value = p
  fetchList()
}

onMounted(fetchList)
</script>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/VenueList.vue
git commit -m "feat: VenueList — 场馆管理 CRUD 页面"
```

---

### Task 11: EventList.vue

**Files:**
- Create: `client/src/views/admin/EventList.vue`

- [ ] **Step 1: Create EventList page**

```vue
<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h2 class="font-display text-xl font-bold">演出管理</h2>
      <n-button type="primary" @click="$router.push('/admin/events/new')">新建演出</n-button>
    </div>

    <div class="flex items-center gap-3 mb-4">
      <n-input v-model:value="keyword" placeholder="搜索演出名称" clearable style="width:200px" @keyup.enter="search" />
      <n-select v-model:value="statusFilter" :options="statusOptions" placeholder="状态" clearable style="width:120px" @update:value="search" />
      <n-button secondary @click="search">搜索</n-button>
    </div>

    <n-data-table
      :columns="columns"
      :data="list"
      :loading="loading"
      :pagination="pagination"
      :bordered="false"
      @update:page="onPageChange"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, h, computed } from 'vue'
import { useRouter } from 'vue-router'
import { NButton, NTag, NImage, useMessage } from 'naive-ui'
import { getAdminEventList, deleteEvent } from '@/api/admin'
import type { AdminEventCard } from '@/types/common'

const router = useRouter()
const message = useMessage()
const list = ref<AdminEventCard[]>([])
const loading = ref(false)
const keyword = ref('')
const statusFilter = ref<number | null>(null)
const page = ref(1)
const total = ref(0)
const pageSize = 10

const statusOptions = [
  { label: '即将开售', value: 0 },
  { label: '在售中', value: 1 },
  { label: '已售罄', value: 2 },
  { label: '已结束', value: 3 },
]

const pagination = computed(() => ({ page: page.value, pageSize, itemCount: total.value }))

const columns = [
  {
    title: '海报', key: 'posterUrl', width: 60,
    render: (row: AdminEventCard) => h(NImage, { width: 40, height: 50, src: row.posterUrl || '', fallbackSrc: 'data:image/svg+xml,' + encodeURIComponent('<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 40 50"><rect fill="#1a1f2e" width="40" height="50"/></svg>'), style: { borderRadius: '4px' } }),
  },
  { title: '名称', key: 'title', ellipsis: true },
  { title: '分类', key: 'categoryName', width: 80 },
  { title: '场馆', key: 'venueName', width: 100 },
  {
    title: '状态', key: 'status', width: 80,
    render: (row: AdminEventCard) => {
      const m: Record<number, { label: string; type: string }> = {
        0: { label: '即将开售', type: 'info' },
        1: { label: '在售中', type: 'success' },
        2: { label: '已售罄', type: 'error' },
        3: { label: '已结束', type: 'default' },
      }
      const s = m[row.status] || { label: '未知', type: 'default' }
      return h(NTag, { size: 'small', type: s.type as any }, { default: () => s.label })
    },
  },
  {
    title: '操作', key: 'actions', width: 140,
    render: (row: AdminEventCard) => h('div', { class: 'flex gap-2' }, [
      h(NButton, { size: 'small', onClick: () => router.push(`/admin/events/${row.id}`) }, { default: () => '编辑' }),
      h(NButton, { size: 'small', type: 'error', onClick: () => handleDelete(row.id) }, { default: () => '删除' }),
    ]),
  },
]

async function fetchList() {
  loading.value = true
  try {
    const res = await getAdminEventList({ keyword: keyword.value || undefined, status: statusFilter.value ?? undefined, page: page.value, pageSize })
    list.value = res.records
    total.value = res.total
  } finally {
    loading.value = false
  }
}

function search() { page.value = 1; fetchList() }
function onPageChange(p: number) { page.value = p; fetchList() }

async function handleDelete(id: number) {
  if (!confirm('确认删除？')) return
  try {
    await deleteEvent(id)
    message.success('已删除')
    fetchList()
  } catch (e: any) {
    message.error(e.message)
  }
}

onMounted(fetchList)
</script>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/EventList.vue
git commit -m "feat: EventList — 演出管理列表页面"
```

---

### Task 12: EventEdit.vue

**Files:**
- Create: `client/src/views/admin/EventEdit.vue`

- [ ] **Step 1: Create EventEdit (event form + sessions sub-table)**

```vue
<template>
  <div>
    <router-link to="/admin/events" class="text-sm text-white/40 hover:text-white/70">&larr; 返回演出列表</router-link>
    <h2 class="font-display text-xl font-bold mt-2 mb-6">{{ isCreate ? '新建演出' : '编辑演出' }}</h2>

    <!-- 演出表单 -->
    <n-card size="small" title="基本信息" class="!bg-white/[0.02] !border-white/[0.06] mb-6">
      <n-form :model="form" :rules="rules" label-placement="left" label-width="80">
        <n-grid cols="2" x-gap="16">
          <n-form-item-gi label="名称" path="title">
            <n-input v-model:value="form.title" />
          </n-form-item-gi>
          <n-form-item-gi label="分类" path="categoryId">
            <n-select v-model:value="form.categoryId" :options="categoryOptions" placeholder="选择分类" />
          </n-form-item-gi>
          <n-form-item-gi label="场馆" path="venueId">
            <n-select v-model:value="form.venueId" :options="venueOptions" placeholder="选择场馆" />
          </n-form-item-gi>
          <n-form-item-gi label="时长(分钟)">
            <n-input-number v-model:value="form.duration" :min="0" />
          </n-form-item-gi>
          <n-form-item-gi label="海报URL" span="2">
            <n-input v-model:value="form.posterUrl" placeholder="https://..." />
          </n-form-item-gi>
          <n-form-item-gi label="简介" span="2">
            <n-input v-model:value="form.description" type="textarea" :rows="3" />
          </n-form-item-gi>
          <n-form-item-gi label="状态">
            <n-select v-model:value="form.status" :options="statusOptions" />
          </n-form-item-gi>
        </n-grid>
      </n-form>
      <div class="flex justify-end mt-4">
        <n-button type="primary" @click="saveEvent" :loading="saving">保存演出</n-button>
      </div>
    </n-card>

    <!-- 场次管理 (仅编辑模式) -->
    <n-card v-if="!isCreate" size="small" title="场次管理" class="!bg-white/[0.02] !border-white/[0.06]">
      <n-button size="small" type="primary" @click="openSessionModal" class="mb-4">添加场次</n-button>
      <n-data-table :columns="sessionColumns" :data="sessions" :bordered="false" size="small" />

      <n-modal v-model:show="showSessionModal" :title="editingSessionId ? '编辑场次' : '添加场次'" preset="card" style="width:600px">
        <n-form :model="sessionForm">
          <n-form-item label="开演时间">
            <n-date-picker v-model:value="sessionForm.sessionTime" type="datetime" format="yyyy-MM-dd HH:mm" />
          </n-form-item>
          <n-form-item label="状态">
            <n-select v-model:value="sessionForm.status" :options="sessionStatusOptions" />
          </n-form-item>
          <n-form-item label="票档配置">
            <div class="space-y-2 w-full">
              <div v-for="(z, i) in sessionForm.zones" :key="i" class="flex items-center gap-2">
                <n-input v-model:value="z.name" placeholder="区域名" size="small" style="width:80px" />
                <n-input-number v-model:value="z.price" placeholder="票价" size="small" style="width:100px" :min="0" />
                <n-input-number v-model:value="z.rows" placeholder="排数" size="small" style="width:70px" :min="1" />
                <n-input-number v-model:value="z.cols" placeholder="列数" size="small" style="width:70px" :min="1" />
                <n-button size="tiny" type="error" @click="sessionForm.zones.splice(i, 1)">删</n-button>
              </div>
              <n-button size="tiny" @click="sessionForm.zones.push({ name: '', price: 0, rows: 1, cols: 10 })">+ 票档</n-button>
            </div>
          </n-form-item>
        </n-form>
        <template #footer>
          <n-button @click="showSessionModal = false">取消</n-button>
          <n-button type="primary" @click="saveSession" :loading="savingSession">保存</n-button>
        </template>
      </n-modal>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, h } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { NButton, NTag, useMessage } from 'naive-ui'
import { getEventDetail } from '@/api/event'
import { getCategories } from '@/api/event'  // if exists, else use hardcoded
import { getAdminVenueList } from '@/api/admin'
import {
  createEvent, updateEvent,
  getSessionList, createSession, updateSession, deleteSession,
} from '@/api/admin'
import type { AdminEventReq, AdminSessionReq, AdminSession, ZoneConfig, VenueInfo } from '@/types/common'

const route = useRoute()
const router = useRouter()
const message = useMessage()
const saving = ref(false)
const savingSession = ref(false)
const eventId = computed(() => route.params.id === 'new' ? null : Number(route.params.id))
const isCreate = computed(() => route.params.id === 'new')

// Event form
const form = ref<AdminEventReq>({ title: '', categoryId: 0, venueId: 0, duration: 0, status: 1, posterUrl: '', description: '' })
const rules = {
  title: { required: true, message: '请输入演出名称', trigger: 'blur' },
  categoryId: { required: true, type: 'number' as any, message: '请选择分类', trigger: 'change' },
  venueId: { required: true, type: 'number' as any, message: '请选择场馆', trigger: 'change' },
}
const statusOptions = [
  { label: '即将开售', value: 0 }, { label: '在售中', value: 1 }, { label: '已售罄', value: 2 }, { label: '已结束', value: 3 },
]
const sessionStatusOptions = [
  { label: '取消', value: 0 }, { label: '正常', value: 1 }, { label: '已结束', value: 2 },
]

// TODO: fetch real categories; hardcode for now
const categoryOptions = ref<Array<{ label: string; value: number }>>([])
const venueOptions = ref<Array<{ label: string; value: number }>>([])

// Sessions
const sessions = ref<AdminSession[]>([])
const showSessionModal = ref(false)
const editingSessionId = ref<number | null>(null)
const sessionForm = ref<AdminSessionReq>({
  sessionTime: Date.now() + '',
  status: 1,
  zones: [{ name: 'A区', price: 0, rows: 3, cols: 14 }],
})

const sessionColumns = [
  { title: '时间', key: 'sessionTime', width: 160 },
  { title: '状态', key: 'status', width: 80, render: (row: any) => {
    const m: Record<number, string> = { 0: '取消', 1: '正常', 2: '已结束' }
    return h(NTag, { size: 'small' }, { default: () => m[row.status] || '-' })
  }},
  { title: '操作', key: 'actions', width: 140, render: (row: AdminSession) =>
    h('div', { class: 'flex gap-2' }, [
      h(NButton, { size: 'small', onClick: () => openEditSession(row) }, { default: () => '编辑' }),
      h(NButton, { size: 'small', type: 'error', onClick: () => handleDeleteSession(row.id) }, { default: () => '删除' }),
    ]),
  },
]

onMounted(async () => {
  // Load categories/venues
  try {
    const venueRes = await getAdminVenueList({ pageSize: 100 })
    venueOptions.value = venueRes.records.map((v: VenueInfo) => ({ label: v.name, value: v.id }))
  } catch {}
  // Load event data if editing
  if (!isCreate.value) {
    try {
      const detail = await getEventDetail(eventId.value!)
      form.value = {
        title: detail.title,
        categoryId: detail.categoryId,
        venueId: detail.venue.id,
        duration: detail.duration,
        status: detail.sessions[0]?.status ?? 1,
        posterUrl: detail.posterUrl,
        description: detail.description,
      }
      categoryOptions.value = [{ label: detail.categoryName, value: detail.categoryId }]
      sessions.value = await getSessionList(eventId.value!)
    } catch (e: any) {
      message.error(e.message)
    }
  }
})

async function saveEvent() {
  saving.value = true
  try {
    if (isCreate.value) {
      const res = await createEvent(form.value)
      message.success('创建成功')
      router.replace(`/admin/events/${res.id}`)
    } else {
      await updateEvent(eventId.value!, form.value)
      message.success('保存成功')
    }
  } catch (e: any) { message.error(e.message) }
  finally { saving.value = false }
}

function openSessionModal() {
  editingSessionId.value = null
  sessionForm.value = { sessionTime: Date.now() + '', status: 1, zones: [{ name: 'A区', price: 0, rows: 3, cols: 14 }] }
  showSessionModal.value = true
}

function openEditSession(row: AdminSession) {
  editingSessionId.value = row.id
  sessionForm.value = { sessionTime: row.sessionTime, status: row.status, zones: [] }
  showSessionModal.value = true
}

async function saveSession() {
  savingSession.value = true
  try {
    if (editingSessionId.value) {
      await updateSession(editingSessionId.value, sessionForm.value)
      message.success('更新成功')
    } else {
      await createSession(eventId.value!, sessionForm.value)
      message.success('创建成功')
    }
    showSessionModal.value = false
    sessions.value = await getSessionList(eventId.value!)
  } catch (e: any) { message.error(e.message) }
  finally { savingSession.value = false }
}

async function handleDeleteSession(id: number) {
  if (!confirm('确认删除？将同时删除所有座位。')) return
  try {
    await deleteSession(id)
    message.success('已删除')
    sessions.value = await getSessionList(eventId.value!)
  } catch (e: any) { message.error(e.message) }
}
</script>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/EventEdit.vue
git commit -m "feat: EventEdit — 演出编辑 + 场次管理页面"
```

---

### Task 13: Report.vue

**Files:**
- Create: `client/src/views/admin/Report.vue`

- [ ] **Step 1: Create Report page (reuse dashboard data for now)**

```vue
<template>
  <div>
    <h2 class="font-display text-xl font-bold mb-6">销售报表</h2>
    <LoadingSpinner v-if="loading" />
    <template v-else-if="data">
      <div class="grid grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="总订单" :value="data.todayOrderCount" />
        </n-card>
        <n-card size="small" class="!bg-white/[0.02] !border-white/[0.06]">
          <n-statistic label="总收入" :value="data.todayRevenue"><template #prefix>¥</template></n-statistic>
        </n-card>
      </div>

      <n-card title="演出销量排行" size="small" class="!bg-white/[0.02] !border-white/[0.06]">
        <n-data-table :columns="columns" :data="data.topEvents" :bordered="false" />
      </n-card>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getDashboard } from '@/api/admin'
import type { AdminDashboard } from '@/types/common'
import LoadingSpinner from '@/components/LoadingSpinner.vue'

const loading = ref(true)
const data = ref<AdminDashboard | null>(null)

const columns = [
  { title: '演出', key: 'eventTitle', ellipsis: true },
  { title: '售票数', key: 'ticketCount', width: 100 },
  { title: '收入', key: 'revenue', width: 120, render: (row: any) => `¥${row.revenue}` },
]

onMounted(async () => {
  try { data.value = await getDashboard() }
  finally { loading.value = false }
})
</script>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/views/admin/Report.vue
git commit -m "feat: Report — 销售报表页面"
```

---

### Task 14: Router Admin Routes + Guard

**Files:**
- Modify: `client/src/router/index.ts`

- [ ] **Step 1: Add admin routes**

Insert after the existing routes:

```typescript
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
```

- [ ] **Step 2: Add admin role check to beforeEach guard**

Update the `router.beforeEach` to check `requiresAdmin`:

```typescript
router.beforeEach((to, _from, next) => {
  if (to.meta.requiresAuth || to.meta.requiresAdmin) {
    const token = localStorage.getItem('token')
    if (!token) {
      next({ name: 'login', query: { redirect: to.fullPath } })
      return
    }
    // For admin routes, verify admin role
    if (to.meta.requiresAdmin) {
      // role is fetched async; need to check after user is loaded
      // For now, perform an inline check via the user object
    }
  }
  next()
})
```

Refined guard:

```typescript
router.beforeEach(async (to, _from, next) => {
  if (to.meta.requiresAuth || to.meta.requiresAdmin) {
    const token = localStorage.getItem('token')
    if (!token) {
      next({ name: 'login', query: { redirect: to.fullPath } })
      return
    }
    if (to.meta.requiresAdmin) {
      try {
        const { useAuthStore } = await import('@/stores/auth')
        const auth = useAuthStore()
        if (!auth.user) await auth.fetchUser()
        if (!auth.isAdmin) {
          next({ name: 'home' })
          return
        }
      } catch {
        next({ name: 'login' })
        return
      }
    }
  }
  next()
})
```

- [ ] **Step 3: Commit**

```bash
git add client/src/router/index.ts
git commit -m "feat: Admin 路由 + 管理员角色守卫"
```

---

### Task 15: NavBar Admin Entry

**Files:**
- Modify: `client/src/components/NavBar.vue`

- [ ] **Step 1: Add admin entry to user dropdown**

Find the user dropdown menu items and add an "管理后台" entry visible only to admins:

```vue
<!-- Inside the user dropdown menu, before "退出登录" -->
<div v-if="auth.isAdmin" class="border-t border-white/[0.06] pt-1 mt-1">
  <router-link to="/admin/dashboard" class="block px-4 py-2 text-sm text-white/60 hover:text-white/90 hover:bg-white/[0.04] transition-colors">
    管理后台
  </router-link>
</div>
```

- [ ] **Step 2: Commit**

```bash
git add client/src/components/NavBar.vue
git commit -m "feat: NavBar 添加管理后台入口（仅管理员可见）"
```

---

### Task 16: Set Yourself as Admin

- [ ] **Step 1: Update your user role in DB**

```bash
mysql -h DESKTOP-IPV3AHQ.local -u root -pyjxsz2001 showtime -e "UPDATE users SET role='admin' WHERE phone='你的手机号';"
```

- [ ] **Step 2: Verify full flow**

1. Login as admin user
2. NavBar should show "管理后台" link
3. Navigate to /admin/dashboard
4. Test: create venue → create event → add session with zones → seats auto-generated
5. Test: delete session → delete event

- [ ] **Step 3: Commit if any fixes needed**

```bash
git add -A
git commit -m "fix: admin flow end-to-end fixes"
git push
```
