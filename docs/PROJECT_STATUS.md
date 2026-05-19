# ShowTime 项目进度

> 最后更新时间：2026/05/19
> 当前阶段：数据层完成，准备编写业务代码

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
| 建库建表（无索引版） | ⏳ 用户自行执行 |
| 后端骨架 | ✅ 完成 |
| 前端骨架 | ✅ 完成 |
| 后端开发规范 | ✅ 完成 |
| 前端开发规范 | ✅ 完成 |
| 实体类（12张表） | ✅ 完成 |
| Mapper 接口（12个） | ✅ 完成 |

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
│       ├── controller/
│       ├── service/
│       ├── mapper/   (CategoryMapper, EventMapper, SeatMapper 等12个)
│       ├── entity/   (Category, Event, Seat, Order 等12个)
│       ├── dto/
│       └── vo/
└── client/                         # Vue 3 前端
    ├── package.json
    ├── vite.config.ts
    └── src/
        ├── api/     (request.ts, user.ts, event.ts)
        ├── router/  (index.ts)
        ├── stores/  (auth.ts)
        ├── types/   (common.ts)
        ├── views/   (8个占位页面)
        └── styles/  (main.css + Tailwind)
```

---

## 下一步计划

1. 用户执行 `sql/init.sql` 建库建表
2. 编写 DTO 类（登录请求、注册请求、演出搜索条件等）
3. 实现用户模块 Service + Controller（注册/登录）
4. 实现演出模块 Service + Controller（列表/搜索/详情）
