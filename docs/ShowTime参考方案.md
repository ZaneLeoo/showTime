# ShowTime — 数据库参考方案

> **请在阅读本文档之前，先基于业务需求文档尝试自己设计表结构。**
> 这份参考方案是「一种」正确答案，不是「唯一」正确答案。

---

## 一、实体关系图（文字版）

```
categories(分类)
    │
    │ 1:N
    ↓
events(演出) ←── venues(场馆)
    │
    │ 1:N
    ↓
event_sessions(场次)
    │
    │ 1:N
    ↓
seats(座位) ←──   users(用户)
    │                 │
    │ 1:1             │ 1:N
    ↓           N:1   ↓
order_items(票) ──→ orders(订单)
                        │
                        │ 1:1
                        ↓
                   payments(支付)

独立表：
  user_sessions(Memory)    - 用户登录态
  search_logs(MyISAM)      - 搜索记录
  audit_log(MyISAM)        - 审计日志
```

---

## 二、完整建表 SQL

```sql
-- ============================================
-- ShowTime 数据库完整建表脚本
-- MySQL 8.0+
-- ============================================

CREATE DATABASE IF NOT EXISTS showtime 
  DEFAULT CHARSET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

USE showtime;

-- --------------------------------------------
-- 1. 演出分类表
-- --------------------------------------------
CREATE TABLE categories (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL COMMENT '分类名称',
    parent_id   INT DEFAULT NULL COMMENT '父分类ID（自关联，支持二级分类）',
    sort_order  INT DEFAULT 0 COMMENT '排序号',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_parent (parent_id),
    CONSTRAINT fk_cat_parent FOREIGN KEY (parent_id) 
        REFERENCES categories(id) ON DELETE SET NULL
) ENGINE=InnoDB COMMENT '演出分类表';

-- --------------------------------------------
-- 2. 场馆表
-- --------------------------------------------
CREATE TABLE venues (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL COMMENT '场馆名称',
    city        VARCHAR(50) NOT NULL COMMENT '所在城市',
    address     VARCHAR(255) NOT NULL COMMENT '详细地址',
    description TEXT COMMENT '场馆介绍',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE INDEX idx_city_name (city, name)
) ENGINE=InnoDB COMMENT '场馆表';

-- --------------------------------------------
-- 3. 演出表
-- --------------------------------------------
CREATE TABLE events (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    title           VARCHAR(200) NOT NULL COMMENT '演出名称',
    description     TEXT COMMENT '演出简介',
    poster_url      VARCHAR(500) COMMENT '海报图片URL',
    duration        INT COMMENT '演出时长（分钟）',
    category_id     INT NOT NULL COMMENT '所属分类',
    venue_id        INT NOT NULL COMMENT '演出场馆',
    status          TINYINT DEFAULT 0 COMMENT '状态：0=即将开售 1=在售 2=售罄 3=已结束',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_category_status (category_id, status),
    INDEX idx_venue (venue_id),
    INDEX idx_status_time (status, created_at),
    FULLTEXT INDEX ft_title_desc (title, description),
    
    CONSTRAINT fk_event_cat FOREIGN KEY (category_id) 
        REFERENCES categories(id) ON DELETE RESTRICT,
    CONSTRAINT fk_event_venue FOREIGN KEY (venue_id) 
        REFERENCES venues(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '演出表';

-- --------------------------------------------
-- 4. 场次表
-- --------------------------------------------
CREATE TABLE event_sessions (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_id        INT NOT NULL COMMENT '所属演出',
    session_time    DATETIME NOT NULL COMMENT '开演时间',
    status          TINYINT DEFAULT 1 COMMENT '状态：0=取消 1=正常 2=已结束',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_event (event_id),
    INDEX idx_time (session_time),
    
    CONSTRAINT fk_session_event FOREIGN KEY (event_id) 
        REFERENCES events(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT '场次表';

-- --------------------------------------------
-- 5. 座位表（最核心的表！）
-- --------------------------------------------
CREATE TABLE seats (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    session_id      INT NOT NULL COMMENT '所属场次',
    zone_name       VARCHAR(10) NOT NULL COMMENT '座位区域（VIP/A/B/C）',
    seat_row        VARCHAR(5) NOT NULL COMMENT '排号',
    seat_col        INT NOT NULL COMMENT '列号',
    price           DECIMAL(10,2) NOT NULL COMMENT '该座位售价',
    status          ENUM('available','locked','sold') DEFAULT 'available' COMMENT '座位状态',
    lock_time       DATETIME DEFAULT NULL COMMENT '锁定时间（用于超时释放）',
    lock_user_id    INT DEFAULT NULL COMMENT '锁定用户ID',
    
    -- 核心索引
    UNIQUE INDEX idx_session_seat (session_id, seat_row, seat_col),
    INDEX idx_session_status (session_id, status),
    INDEX idx_lock_time (status, lock_time),
    
    CONSTRAINT fk_seat_session FOREIGN KEY (session_id) 
        REFERENCES event_sessions(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT '座位表（行锁核心表）';

-- --------------------------------------------
-- 6. 用户表
-- --------------------------------------------
CREATE TABLE users (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    phone           CHAR(11) NOT NULL COMMENT '手机号',
    email           VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    password_hash   VARCHAR(255) NOT NULL COMMENT '密码哈希',
    nickname        VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    avatar_url      VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
    status          TINYINT DEFAULT 1 COMMENT '状态：0=禁用 1=正常',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE INDEX idx_phone (phone),
    UNIQUE INDEX idx_email (email)
) ENGINE=InnoDB COMMENT '用户表';

-- --------------------------------------------
-- 7. 订单表
-- --------------------------------------------
CREATE TABLE orders (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    order_no        VARCHAR(32) NOT NULL COMMENT '订单号（业务唯一标识）',
    user_id         INT NOT NULL COMMENT '下单用户',
    total_amount    DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status          ENUM('pending','paid','cancelled','expired') DEFAULT 'pending' COMMENT '订单状态',
    ticket_count    INT DEFAULT 0 COMMENT '票数',
    paid_at         DATETIME DEFAULT NULL COMMENT '支付时间',
    cancelled_at    DATETIME DEFAULT NULL COMMENT '取消时间',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE INDEX idx_order_no (order_no),
    INDEX idx_user_time (user_id, created_at),
    INDEX idx_status (status),
    
    CONSTRAINT fk_order_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '订单表';

-- --------------------------------------------
-- 8. 订单明细表（票）
-- --------------------------------------------
CREATE TABLE order_items (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    order_id        INT NOT NULL COMMENT '所属订单',
    seat_id         INT NOT NULL COMMENT '座位ID',
    price           DECIMAL(10,2) NOT NULL COMMENT '实际售价',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_order (order_id),
    UNIQUE INDEX idx_seat (seat_id),  -- 一个座位只能对应一张票
    
    CONSTRAINT fk_item_order FOREIGN KEY (order_id) 
        REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_item_seat FOREIGN KEY (seat_id) 
        REFERENCES seats(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '订单明细表（票）';

-- --------------------------------------------
-- 9. 支付记录表
-- --------------------------------------------
CREATE TABLE payments (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    order_id        INT NOT NULL COMMENT '所属订单',
    pay_method      VARCHAR(20) DEFAULT 'alipay' COMMENT '支付方式',
    amount          DECIMAL(10,2) NOT NULL COMMENT '支付金额',
    status          ENUM('pending','success','failed','refunded') DEFAULT 'pending',
    trade_no        VARCHAR(64) DEFAULT NULL COMMENT '第三方交易号',
    paid_at         DATETIME DEFAULT NULL COMMENT '支付时间',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    UNIQUE INDEX idx_order (order_id),
    INDEX idx_status (status),
    
    CONSTRAINT fk_pay_order FOREIGN KEY (order_id) 
        REFERENCES orders(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '支付记录表';

-- --------------------------------------------
-- 10. 用户会话表（临时数据）
-- --------------------------------------------
CREATE TABLE user_sessions (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NOT NULL,
    token           VARCHAR(255) NOT NULL,
    expires_at      DATETIME NOT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_token (token),
    INDEX idx_expires (expires_at)
) ENGINE=Memory COMMENT '用户登录会话表（重启丢失）';

-- --------------------------------------------
-- 11. 搜索日志表（高频写入）
-- --------------------------------------------
CREATE TABLE search_logs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT DEFAULT NULL COMMENT '用户ID（未登录为NULL）',
    keyword         VARCHAR(200) NOT NULL COMMENT '搜索关键词',
    result_count    INT DEFAULT 0 COMMENT '搜索结果数',
    searched_at     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '搜索时间',
    
    INDEX idx_time (searched_at)
) ENGINE=MyISAM COMMENT '搜索日志表（只追加，无需事务）';

-- --------------------------------------------
-- 12. 审计日志表（触发器自动写入）
-- --------------------------------------------
CREATE TABLE audit_log (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    table_name      VARCHAR(50) NOT NULL COMMENT '操作的表名',
    record_id       INT NOT NULL COMMENT '记录ID',
    field_name      VARCHAR(50) DEFAULT NULL COMMENT '变更字段',
    old_value       VARCHAR(500) DEFAULT NULL COMMENT '旧值',
    new_value       VARCHAR(500) DEFAULT NULL COMMENT '新值',
    changed_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '变更时间',
    
    INDEX idx_table_record (table_name, record_id),
    INDEX idx_time (changed_at)
) ENGINE=MyISAM COMMENT '审计日志表（只追加）';
```

---

## 三、设计决策说明

### 为什么 seats 表是核心？

整个系统的性能瓶颈在 seats 表：
- **数据量最大**：百万级
- **并发最密集**：热门演出开售时，大量用户同时抢座位
- **行锁目标**：`SELECT FOR UPDATE` 锁定的就是 seats 的行
- **索引最关键**：`(session_id, status)` 联合索引决定了选座页加载速度

### 为什么 seat_row 用 VARCHAR(5)？

现实中排号可能是 '1', '2', ..., '10A', 'VIP-1' 等非纯数字。用 VARCHAR 更灵活。

### 为什么订单和支付分开？

一个订单可能有多次支付尝试（第一次失败，第二次成功）。分开记录更清晰。

### 为什么 order_items 的 seat_id 加 UNIQUE 索引？

一个座位只能属于一张票。在数据库层面用唯一约束兜底，防止应用层 bug 导致一票多卖。

### 为什么 order_items 不存 event_id/session_id？

这些都可以通过 seat_id → seats → event_sessions → events 查到。遵守规范化原则，避免冗余。

### 引擎选择总结

| 表 | 引擎 | 核心理由 |
|----|------|---------|
| categories, venues, events, event_sessions | InnoDB | 需事务 + 外键 |
| **seats** | **InnoDB** | **需要行锁！这是选 InnoDB 最关键的理由** |
| users | InnoDB | 核心数据需事务 |
| orders, order_items, payments | InnoDB | 强事务一致性 |
| user_sessions | **Memory** | 临时数据，重启丢失可接受，查询快 |
| search_logs | **MyISAM** | 只追加不修改，无需事务，写入快 |
| audit_log | **MyISAM** | 触发器写入，只追加，无需事务 |

---

## 四、关键业务 SQL

### 4.1 查询可用座位（选座页）

```sql
SELECT id, zone_name, seat_row, seat_col, price, status
FROM seats
WHERE session_id = ? 
  AND status = 'available'
ORDER BY 
  FIELD(zone_name, 'VIP', 'A', 'B', 'C'),  -- 好位置排前面
  seat_row, seat_col;
```

### 4.2 锁定座位（下单第一步：SELECT FOR UPDATE）

```sql
START TRANSACTION;

SELECT id, status, price
FROM seats
WHERE session_id = ? AND seat_row = ? AND seat_col = ?
FOR UPDATE;  -- 行锁

-- 在应用层检查 status == 'available'
-- 然后：
UPDATE seats 
SET status = 'locked', lock_time = NOW(), lock_user_id = ?
WHERE id = ?;

COMMIT;
```

### 4.3 释放超时锁定的座位

```sql
-- 可以用定时任务执行，也可以用存储过程
UPDATE seats 
SET status = 'available', lock_time = NULL, lock_user_id = NULL
WHERE status = 'locked' 
  AND lock_time < DATE_SUB(NOW(), INTERVAL 15 MINUTE);
```

### 4.4 演出搜索

```sql
SELECT e.id, e.title, e.poster_url, v.name AS venue_name, v.city,
       MIN(es.session_time) AS earliest_time,
       MIN(s.price) AS min_price
FROM events e
JOIN venues v ON e.venue_id = v.id
JOIN event_sessions es ON e.id = es.event_id AND es.status = 1
LEFT JOIN seats s ON es.id = s.session_id AND s.status = 'available'
WHERE e.status = 1
  AND (e.category_id = ? OR ? IS NULL)
  AND (v.city = ? OR ? IS NULL)
  AND MATCH(e.title, e.description) AGAINST(? IN BOOLEAN MODE)
GROUP BY e.id
ORDER BY earliest_time ASC
LIMIT ?, ?;
```

### 4.5 用户票夹

```sql
SELECT o.order_no, o.status, o.total_amount, o.created_at,
       oi.price AS ticket_price,
       s.zone_name, s.seat_row, s.seat_col,
       e.title AS event_title, e.poster_url,
       es.session_time,
       v.name AS venue_name
FROM orders o
JOIN order_items oi ON o.id = oi.order_id
JOIN seats s ON oi.seat_id = s.id
JOIN event_sessions es ON s.session_id = es.id
JOIN events e ON es.event_id = e.id
JOIN venues v ON e.venue_id = v.id
WHERE o.user_id = ?
ORDER BY o.created_at DESC;
```

---

## 五、索引优化检查清单

对照 `05-索引与SQL优化.md`，检查 ShowTime 的核心查询是否都有索引覆盖：

| 查询 | SQL | 命中索引 | type |
|------|-----|---------|------|
| 用户登录 | WHERE phone=? | idx_phone (UNIQUE) | const |
| 演出列表 | WHERE category_id=? AND status=? | idx_category_status | ref |
| 可用座位 | WHERE session_id=? AND status=? | idx_session_status | ref |
| 订单列表 | WHERE user_id=? ORDER BY created_at | idx_user_time | ref |
| 锁定座位 | WHERE session_id=? AND seat_row=? AND seat_col=? | idx_session_seat (UNIQUE) | const |
| 过期释放 | WHERE status=? AND lock_time<? | idx_lock_time | range |
| 全文搜索 | MATCH(title,description) AGAINST(?) | ft_title_desc (FULLTEXT) | fulltext |

---

## 六、可能的设计变体（和你的方案对比）

### 变体 1：座位模板化

有些设计会单独建一张 `venue_seat_templates` 表存储场馆的标准座位布局，然后在创建场次时复制。这样做的好处是场馆座位布局可复用，缺点是多一张表多一层复杂度。

我的方案选择直接在 seats 表按场次存储，更直观。

### 变体 2：票档价格表独立

有些设计会把价格从 seats 中抽出来，建一张 `session_pricing(session_id, zone_name, price)` 表。这样做的好处是价格统一管理，缺点是需要多 JOIN 一次。

我的方案选择把 price 冗余在 seats 表里，牺牲一点规范化换取查询性能。

### 变体 3：软删除

所有表加 `is_deleted` 字段而不是真正 DELETE。这样做的好处是数据可恢复，缺点是查询时永远要加 `WHERE is_deleted=0`。

我的方案没有加——从学习角度，物理删除和逻辑删除都要理解，你可以自行决定哪些表需要软删除。

---

## 七、下一步

1. 对照你的方案和这份参考方案，找出差异点
2. 思考每个差异：你的方案有什么优势？参考方案有什么优势？
3. 把你认可的差异合并到你的最终方案中
4. 用这份最终方案去建库建表，开始写代码
