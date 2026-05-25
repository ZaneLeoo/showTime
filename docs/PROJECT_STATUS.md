# ShowTime 项目进度

> 最后更新时间：2026/05/25
> 当前阶段：演出模块后端完成 + Mock数据就绪，即将进入订单模块开发

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
| 演出模块（后端） | ✅ 完成（list/detail） |
| 选座模块（后端） | ✅ 完成（seatMap/lock/release） |
| 订单模块（后端） | ⬜ 下一步 |
| Mock 数据 | ✅ 完成（12演出/33场次/6814座位） |
| 海报图片 | ✅ 完成（12张600x800） |
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

## 当前后端 API

### 用户模块
| 端点 | 说明 |
|------|------|
| `POST /api/user/register` | 注册 |
| `POST /api/user/login` | 登录 |
| `GET /api/user/info` | 获取当前用户信息 |
| `PUT /api/user/profile` | 更新个人信息 |

### 演出模块
| 端点 | 说明 |
|------|------|
| `GET /api/event/list` | 演出列表（分页+筛选+排序） |
| `GET /api/event/{id}` | 演出详情（含场地+场次+票档） |
| `GET /api/event/session/{sessionId}/seats` | 座位图（按区域分组） |
| `POST /api/event/seat/lock` | 锁定座位（需登录，15分钟过期） |
| `POST /api/event/seat/release` | 释放座位（需登录） |

## Mock 数据

| 表 | 记录数 |
|----|--------|
| categories | 8（演唱会/音乐会/话剧/音乐剧/舞蹈/戏曲/儿童剧/脱口秀） |
| venues | 6（北京鸟巢/上海梅奔/广州大剧院/深圳湾/成都金融城/蜂巢剧场） |
| events | 12（9在售/2售罄/2即将开售） |
| event_sessions | 33 |
| seats | 6814（VIP/A/B/C 四区） |

海报图片：`client/public/images/posters/*.jpg`（12张，600×800，Unsplash）

## 开发环境

- WSL2: Java 17 + Maven 3.8.7（编译/测试用）
- Windows: MySQL 8.0 + 后端运行
- WSL2 → Windows MySQL: `mysql -h DESKTOP-IPV3AHQ.local`
- Git SSH: 已配置 `~/.ssh/config` 走 443 端口

## 下一步计划

1. **订单模块后端** Service + Controller（下单/支付/取消/票夹）—— 优先
2. 前后端联调，接口对接
3. 逐步添加索引，EXPLAIN 对比性能
