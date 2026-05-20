# ShowTime 项目进度

> 最后更新时间：2026/05/20
> 当前阶段：前端视觉打磨完成，即将进入后端演出/选座/订单模块开发

## 项目背景

**ShowTime** 是一个 MySQL 体系化学习项目，以演出订票系统为实践载体。

---

## 当前进度

### 理论学习（docs/ 章节）

| 章节 | 主题 | 状态 |
|------|------|------|
| 01 | DDL与约束与存储引擎 | ✅ 完成 |
| 02 | DML与数据操作 | ✅ 完成 |
| 03 | DQL与多表查询与函数 | ✅ 完成 |
| 04 | 事务与锁 | ✅ 完成 |
| 05 | 索引与SQL优化 | ✅ 完成 |
| 06 | 数据库编程-视图-存储过程-触发器 | ✅ 完成 |
| 07 | 运维管理-备份-主从-读写分离 | ✅ 完成 |

### ShowTime 项目开发

| 模块 | 状态 |
|------|------|
| 需求文档 | ✅ 完成 |
| 参考方案（DDL） | ✅ 完成 |
| 建库建表（无索引版） | ✅ 完成 |
| 后端骨架 | ✅ 完成 |
| 前端骨架 | ✅ 完成 |
| 后端开发规范 | ✅ 完成 |
| 前端开发规范 | ✅ 完成 |
| 实体类（12张表） | ✅ 完成 |
| Mapper 接口（12个） | ✅ 完成 |
| 用户模块（后端） | ✅ 完成 |
| 用户模块（前端） | ✅ 完成 |
| C端前端界面（全部页面） | ✅ 完成 |
| 前端视觉打磨 | ✅ 完成 |
| 演出模块（后端） | ⬜ 下一步 |
| 选座模块（后端） | ⬜ 待开发 |
| 订单模块（后端） | ⬜ 待开发 |
| 前后端联调 | ⬜ 待开发 |

---

## 项目文件

```
MySQL-p/
├── sql/
│   └── init.sql                    # 建库建表脚本（索引已注释）
├── docs/
│   ├── ShowTime业务需求文档.md
│   ├── ShowTime参考方案.md
│   ├── 技术架构文档.md
│   ├── 后端开发规范.md
│   ├── 前端开发规范.md
│   └── PROJECT_STATUS.md
├── server/                         # Spring Boot 后端
│   ├── pom.xml
│   └── src/main/java/com/showtime/
│       ├── common/   (Result, ResultCode, PageResult, BizException, Handler)
│       ├── config/   (MyBatisPlusConfig)
│       ├── controller/ (UserController)
│       ├── service/    (UserService, SessionService)
│       ├── mapper/   (CategoryMapper, EventMapper, SeatMapper 等12个)
│       ├── entity/   (Category, Event, Seat, Order 等12个)
│       ├── dto/      (LoginRequest, RegisterRequest 等)
│       └── vo/       (UserInfoVO)
└── client/                         # Vue 3 前端
    ├── package.json
    ├── vite.config.ts
    ├── tailwind.config.js
    └── src/
        ├── main.ts
        ├── App.vue                  # 根组件（噪声纹理+网格+光晕背景）
        ├── api/
        │   ├── request.ts           # Axios 实例 + 拦截器
        │   ├── user.ts              # 用户API（注册/登录/信息/更新）
        │   ├── event.ts             # 演出API（列表/详情/座位图/锁定释放）
        │   └── order.ts             # 订单API（创建/支付/取消/列表/详情）
        ├── router/index.ts          # 9条路由 + 登录守卫
        ├── stores/auth.ts           # Pinia 认证状态
        ├── types/common.ts          # 全部 TS 类型定义
        ├── styles/main.css          # Tailwind + 暗色主题全局样式
        ├── components/
        │   ├── NavBar.vue           # 导航栏（搜索+登录态+用户下拉菜单）
        │   ├── EventCard.vue        # Netflix风格演出卡片（全幅海报+扫光+氛围光）
        │   ├── SeatMap.vue          # 座位图（彩色区域+图例）
        │   ├── LoadingSpinner.vue   # 加载动画
        │   └── EmptyState.vue       # 空状态
        └── views/
            ├── Home.vue             # 首页（Hero+分类筛选+Headless UI排序下拉+演出网格）
            ├── Search.vue           # 搜索页
            ├── Login.vue            # 登录（氛围光晕+金色装饰线+图标输入框+加载态）
            ├── Register.vue         # 注册（同设计语言+必填标记+注册后自动登录）
            ├── EventDetail.vue      # 演出详情（场次+票档+选座入口）
            ├── SeatSelect.vue       # 选座页（可视化座位图+锁定倒计时）
            ├── OrderConfirm.vue     # 订单确认（座位明细+支付）
            ├── OrderDetail.vue      # 订单详情（票务明细+支付信息）
            └── MyOrders.vue         # 我的票夹（状态筛选+订单列表）
```

---

## 设计主题

- **"Midnight Stage"** — 暗色沉浸式主题
- 品牌色：酒红 #8b1a2b（按钮/售罄/glow） + 旧金 #c9a84c（价格/在售/渐变）
- 字体：Playfair Display（标题） + DM Sans / 思源黑体（正文）
- 背景：SVG 噪声纹理 + 网格纹理 + 顶部酒红光晕
- 按钮：纯色酒红（无渐变），hover 亮酒红 #a8324a
- 卡片：Netflix 风格全幅海报 3:4，渐变遮罩文字，hover 扫光 + 氛围光
- Logo/文字品牌标识：酒红→金渐变保留

---

## 前端近期改动

- 品牌色：紫→粉 改为 酒红 #8b1a2b + 旧金 #c9a84c
- 按钮：渐变改为纯色酒红，hover 亮酒红
- EventCard：Netflix 风格重设计，3:4 全幅海报 + 扫光动画 + 氛围光按状态变色
- 登录/注册：氛围光晕 + 金色装饰线 + 内嵌图标 + 加载 spinner
- 排序下拉：原生 select → Headless UI Menu 组件
- 用户菜单：头像下拉（个人中心 + 退出登录）
- 认证：刷新恢复用户信息，路由守卫 requiresAuth，请求拦截器自动带 token
- JDBC：修复 `characterEncoding=utf8mb4` → `UTF-8` 连接错误
- Mock 数据：8 张真实演出海报图

## 下一步计划

1. **演出模块后端** Service + Controller（列表/搜索/详情）—— 优先
2. 选座模块 Service + Controller（座位查询/锁定/释放 + 事务行锁）
3. 订单模块 Service + Controller（下单/支付/票夹）
4. 前后端联调，接口对接
5. 逐步添加索引，EXPLAIN 对比性能
