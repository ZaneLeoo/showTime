-- ============================================
-- ShowTime 数据库建表脚本（无索引版）
-- MySQL 8.0+
-- 说明：所有索引已注释，仅保留 PRIMARY KEY
--       后续优化阶段逐步添加索引，对比性能差异
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
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP

    -- INDEX idx_parent (parent_id),
    -- CONSTRAINT fk_cat_parent FOREIGN KEY (parent_id)
    --     REFERENCES categories(id) ON DELETE SET NULL
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
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    -- UNIQUE INDEX idx_city_name (city, name)
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
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    -- INDEX idx_category_status (category_id, status),
    -- INDEX idx_venue (venue_id),
    -- INDEX idx_status_time (status, created_at),
    -- FULLTEXT INDEX ft_title_desc (title, description),
    -- CONSTRAINT fk_event_cat FOREIGN KEY (category_id)
    --     REFERENCES categories(id) ON DELETE RESTRICT,
    -- CONSTRAINT fk_event_venue FOREIGN KEY (venue_id)
    --     REFERENCES venues(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '演出表';

-- --------------------------------------------
-- 4. 场次表
-- --------------------------------------------
CREATE TABLE event_sessions (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_id        INT NOT NULL COMMENT '所属演出',
    session_time    DATETIME NOT NULL COMMENT '开演时间',
    status          TINYINT DEFAULT 1 COMMENT '状态：0=取消 1=正常 2=已结束',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP

    -- INDEX idx_event (event_id),
    -- INDEX idx_time (session_time),
    -- CONSTRAINT fk_session_event FOREIGN KEY (event_id)
    --     REFERENCES events(id) ON DELETE CASCADE
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
    lock_user_id    INT DEFAULT NULL COMMENT '锁定用户ID'

    -- UNIQUE INDEX idx_session_seat (session_id, seat_row, seat_col),
    -- INDEX idx_session_status (session_id, status),
    -- INDEX idx_lock_time (status, lock_time),
    -- CONSTRAINT fk_seat_session FOREIGN KEY (session_id)
    --     REFERENCES event_sessions(id) ON DELETE CASCADE
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
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    -- UNIQUE INDEX idx_phone (phone),
    -- UNIQUE INDEX idx_email (email)
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
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    -- UNIQUE INDEX idx_order_no (order_no),
    -- INDEX idx_user_time (user_id, created_at),
    -- INDEX idx_status (status),
    -- CONSTRAINT fk_order_user FOREIGN KEY (user_id)
    --     REFERENCES users(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '订单表';

-- --------------------------------------------
-- 8. 订单明细表（票）
-- --------------------------------------------
CREATE TABLE order_items (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    order_id        INT NOT NULL COMMENT '所属订单',
    seat_id         INT NOT NULL COMMENT '座位ID',
    price           DECIMAL(10,2) NOT NULL COMMENT '实际售价',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP

    -- INDEX idx_order (order_id),
    -- UNIQUE INDEX idx_seat (seat_id),  -- 一个座位只能对应一张票
    -- CONSTRAINT fk_item_order FOREIGN KEY (order_id)
    --     REFERENCES orders(id) ON DELETE CASCADE,
    -- CONSTRAINT fk_item_seat FOREIGN KEY (seat_id)
    --     REFERENCES seats(id) ON DELETE RESTRICT
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
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP

    -- UNIQUE INDEX idx_order (order_id),
    -- INDEX idx_status (status),
    -- CONSTRAINT fk_pay_order FOREIGN KEY (order_id)
    --     REFERENCES orders(id) ON DELETE RESTRICT
) ENGINE=InnoDB COMMENT '支付记录表';

-- --------------------------------------------
-- 10. 用户会话表（临时数据）
-- --------------------------------------------
CREATE TABLE user_sessions (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NOT NULL,
    token           VARCHAR(255) NOT NULL,
    expires_at      DATETIME NOT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP

    -- INDEX idx_token (token),
    -- INDEX idx_expires (expires_at)
) ENGINE=Memory COMMENT '用户登录会话表（重启丢失）';

-- --------------------------------------------
-- 11. 搜索日志表（高频写入）
-- --------------------------------------------
CREATE TABLE search_logs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT DEFAULT NULL COMMENT '用户ID（未登录为NULL）',
    keyword         VARCHAR(200) NOT NULL COMMENT '搜索关键词',
    result_count    INT DEFAULT 0 COMMENT '搜索结果数',
    searched_at     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '搜索时间'

    -- INDEX idx_time (searched_at)
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
    changed_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '变更时间'

    -- INDEX idx_table_record (table_name, record_id),
    -- INDEX idx_time (changed_at)
) ENGINE=MyISAM COMMENT '审计日志表（只追加）';
