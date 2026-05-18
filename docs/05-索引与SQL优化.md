# 05 — 索引 · SQL 优化

> 对应学习文档：进阶第2章 索引、进阶第3章 SQL优化

---

## 1. 知识清单

### 索引类型

| 类型 | 关键字 | 特点 |
|------|--------|------|
| 主键索引 | PRIMARY KEY | 自动创建，唯一，一个表一个 |
| 唯一索引 | UNIQUE INDEX | 值不能重复，可以有多个 |
| 常规索引 | INDEX | 加速查询，值可重复 |
| 全文索引 | FULLTEXT INDEX | 文本搜索，InnoDB 5.6+ 支持 |
| 联合索引 | INDEX(col1, col2, ...) | 多列组合，遵循最左前缀原则 |

### 索引操作

```sql
-- 创建
CREATE [UNIQUE|FULLTEXT] INDEX idx_name ON table(col1, col2);
-- 查看
SHOW INDEX FROM table;
-- 删除
DROP INDEX idx_name ON table;
```

### EXPLAIN：分析查询执行计划

```sql
EXPLAIN SELECT * FROM seats WHERE session_id = 10 AND status = 'available';
```

| 关键字段 | 含义 | 理想值 |
|---------|------|--------|
| type | 访问类型 | `const` > `eq_ref` > `ref` > `range` > `index` > `ALL` |
| possible_keys | 可能用到的索引 | 有值 |
| key | 实际用的索引 | 有值，且是最优的 |
| rows | 预估扫描行数 | 越小越好 |
| Extra | 额外信息 | 避免 `Using filesort`、`Using temporary` |

### type 从好到差

```
NULL (不需要查表)
  > system > const (主键/唯一键等值查)
  > eq_ref (JOIN 用主键关联) 
  > ref (索引等值查)
  > range (索引范围查)
  > index (全索引扫描)
  > ALL (全表扫描 💀)
```

**目标**：核心查询至少到 `range`，最好到 `ref`。

---

## 2. 索引核心原理（你只需要记住这几条）

### 最左前缀原则

```sql
INDEX idx_a_b_c (a, b, c)

WHERE a = 1                      -- ✅ 命中
WHERE a = 1 AND b = 2            -- ✅ 命中
WHERE a = 1 AND b = 2 AND c = 3  -- ✅ 命中
WHERE a = 1 AND c = 3            -- ✅ 命中 a（c 不行）
WHERE b = 2                      -- ❌ 不命中（跳过了 a）
WHERE b = 2 AND c = 3            -- ❌ 不命中
```

**联合索引把区分度最高的字段放最左边。**

### 覆盖索引 vs 回表

```sql
-- 假设有 INDEX idx_status_time (status, create_time)

-- 覆盖索引：查询的列都在索引里，不需要回表
SELECT status, create_time FROM orders WHERE status = 'paid';
-- Extra: Using index ← 高效！

-- 回表查询：索引里没有 user_id，要通过主键去聚簇索引查
SELECT * FROM orders WHERE status = 'paid';
-- Extra: NULL 或 Using where ← 多一次磁盘IO
```

### 索引失效场景（面试高频）

| 场景 | 示例 | 原因 |
|------|------|------|
| 左模糊/全模糊 | `LIKE '%杰伦'` / `LIKE '%杰伦%'` | B+Tree 只能前缀匹配 |
| 函数包裹索引列 | `WHERE YEAR(create_time)=2025` | 索引存的是原值 |
| 类型隐式转换 | `WHERE phone = 13800138000`（phone是varchar） | 字符串和数字比较会转换 |
| OR 条件不全有索引 | `WHERE a=1 OR b=2`（b没索引） | 全表扫描 |
| 不满足最左前缀 | `WHERE b=2`（联合索引(a,b)） | 跳过了 a |

---

## 3. 慢查询日志

```sql
-- 查看是否开启
SHOW VARIABLES LIKE 'slow_query_log%';
-- 查看慢查询阈值
SHOW VARIABLES LIKE 'long_query_time';

-- 开启（需在配置文件中设置）
-- /etc/my.cnf:
-- slow_query_log = 1
-- long_query_time = 2
-- slow_query_log_file = /var/log/mysql/slow.log
```

---

## 4. ShowTime 中的索引设计

### 你需要给哪些查询建索引？

| 查询场景 | SQL 特征 | 建什么索引 |
|---------|---------|----------|
| 演出列表筛选 | WHERE category_id=? AND status=? ORDER BY start_time | 联合索引 (category_id, status, start_time) |
| 按城市搜演出 | WHERE venue_id IN (SELECT id FROM venues WHERE city=?) | venues.city 建索引 |
| 模糊搜演出名 | WHERE title LIKE '%关键词%' | 全文索引 FULLTEXT(title, description) |
| 按场次查可用座位 | WHERE session_id=? AND status='available' | 联合索引 (session_id, status) |
| 座位唯一性校验 | WHERE session_id=? AND seat_row=? AND seat_col=? | 唯一联合索引 (session_id, seat_row, seat_col) |
| 用户订单列表 | WHERE user_id=? ORDER BY create_time DESC | 联合索引 (user_id, create_time) |
| 按订单号查 | WHERE order_no=? | 唯一索引 (order_no) |
| 过期座位释放 | WHERE status='locked' AND lock_time < ? | 联合索引 (status, lock_time) |

### 哪个索引最重要？

**seats 表的 (session_id, status)**——首页和选座页都大量查询，而且 seats 表有百万行数据，不建索引会慢到不可接受。

---

## 5. 动手练习

### 练习 1：EXPLAIN 分析

```sql
-- 针对 ShowTime 的核心查询做 EXPLAIN：
-- 1. 查某场次的所有可用座位
-- 2. 查某用户的订单列表 + 关联票信息
-- 3. 按分类+时间筛选演出
-- 4. 统计某月每天的销售额

-- 记录每个查询的 type、rows、Extra
-- 加索引前后对比
```

### 练习 2：验证索引失效

```sql
-- 建一个 name 索引
CREATE INDEX idx_name ON events(name);

-- 以下哪些会用索引？用 EXPLAIN 验证
SELECT * FROM events WHERE name LIKE '周杰伦%';   -- ？
SELECT * FROM events WHERE name LIKE '%演唱会';   -- ？
SELECT * FROM events WHERE LEFT(name, 3) = '周杰伦'; -- ？
SELECT * FROM events WHERE name = 123;            -- ？（name 是 varchar）

-- 四个都跑一遍 EXPLAIN，看哪些 type=ALL
```

### 练习 3：有索引 vs 无索引性能对比

```sql
-- 前提：seats 表有 100 万+ 数据
-- 先不用索引：
DROP INDEX idx_session_status ON seats;
SELECT * FROM seats WHERE session_id = 50 AND status = 'available';
-- 记录耗时：___

-- 再建索引：
CREATE INDEX idx_session_status ON seats(session_id, status);
-- 同样查询：
SELECT * FROM seats WHERE session_id = 50 AND status = 'available';
-- 记录耗时：___

-- 算一下提升了多少倍？
```

### 练习 4：覆盖索引收益验证

```sql
-- 有索引 idx_user_time (user_id, create_time)
-- 覆盖索引写法（只查索引中的列）
EXPLAIN SELECT user_id, create_time FROM orders WHERE user_id = 1;
-- 回表写法
EXPLAIN SELECT * FROM orders WHERE user_id = 1;
-- 对比 Extra 字段
```

---

## 6. 关键提醒

- **索引不是越多越好**：索引占用空间、拖慢 INSERT/UPDATE/DELETE
- **建了索引不代表会用**：用 EXPLAIN 验证是关键习惯
- **区分度低的列不适合单独建索引**：比如 gender（只有男/女），过滤效果差
- **大数据量下才看得出差距**：几百条数据有无索引几乎一样快，百万级差 1000 倍
- **联合索引建对了，单列索引可以删**：(a,b) 索引可以替代单独的 (a) 索引
