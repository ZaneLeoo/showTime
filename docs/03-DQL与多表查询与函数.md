# 03 — DQL · 多表查询 · 函数

> 对应学习文档：第2章 SQL（DQL 部分）、第3章 函数、第5章 多表查询

---

## 1. 知识清单

### DQL 基础

| 技能 | 语法 |
|------|------|
| 基本查询 | `SELECT col1, col2 FROM table` |
| 条件筛选 | `WHERE col = val AND/OR col2 > val2` |
| 模糊匹配 | `WHERE name LIKE '%关键词%'` |
| 范围查询 | `WHERE price BETWEEN 100 AND 500` |
| 枚举查询 | `WHERE category_id IN (1, 3, 5)` |
| 空值判断 | `WHERE col IS NULL / IS NOT NULL` |
| 去重 | `SELECT DISTINCT col FROM table` |
| 排序 | `ORDER BY col ASC/DESC` |
| 分页 | `LIMIT offset, count` |
| 别名 | `SELECT col AS alias FROM table t` |

### 聚合与分组

| 技能 | 语法 |
|------|------|
| 计数 | `COUNT(*)`, `COUNT(col)`, `COUNT(DISTINCT col)` |
| 求和/平均/最大/最小 | `SUM()`, `AVG()`, `MAX()`, `MIN()` |
| 分组 | `GROUP BY col` |
| 分组后筛选 | `HAVING COUNT(*) > 5`（**WHERE 在 GROUP 前，HAVING 在 GROUP 后**） |

### 函数

| 类型 | 常用函数 |
|------|---------|
| 字符串 | `CONCAT`, `SUBSTRING`, `REPLACE`, `UPPER`, `LOWER`, `TRIM`, `LPAD` |
| 数值 | `ROUND`, `CEIL`, `FLOOR`, `MOD`, `RAND` |
| 日期 | `NOW`, `DATE_FORMAT`, `DATEDIFF`, `DATE_ADD`, `YEAR`, `MONTH` |
| 流程控制 | `IF(condition, val1, val2)`, `CASE WHEN ... THEN ... ELSE ... END` |
| 聚合 | `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`, `GROUP_CONCAT` |

### 多表查询

| 类型 | 说明 |
|------|------|
| INNER JOIN | 两个表都有匹配才返回 |
| LEFT JOIN | 左表全部返回，右表无匹配为 NULL |
| RIGHT JOIN | 右表全部返回（很少用，用 LEFT 就行） |
| 自连接 | 表自己 JOIN 自己（分类树、上下级） |
| 子查询-标量 | 返回单个值：`WHERE price > (SELECT AVG(price) FROM ...)` |
| 子查询-列 | 返回一列：`WHERE id IN (SELECT ...)` |
| 子查询-行 | 返回一行：`WHERE (col1,col2) = (SELECT ...)` |
| 子查询-表 | 返回一张表：`FROM (SELECT ...) AS sub` |
| EXISTS | 判断子查询有无结果：`WHERE EXISTS (SELECT 1 FROM ...)` |
| UNION | 合并两个查询结果（列数+类型一致） |
| UNION ALL | 同上但不去重（更快） |

---

## 2. SQL 执行顺序（重要！）

```sql
SELECT    ...            -- 5. 最后输出
FROM      ...            -- 1. 先确定数据来源
JOIN      ... ON ...     -- 2. 关联表
WHERE     ...            -- 3. 过滤行
GROUP BY  ...            -- 4. 分组
HAVING    ...            -- 5. 过滤分组
ORDER BY  ...            -- 6. 排序
LIMIT     ...            -- 7. 分页
```

**理解这个顺序，写 SQL 才不容易出错。** 比如 WHERE 里不能用 SELECT 里的别名（因为 WHERE 执行比 SELECT 早），ORDER BY 可以。

---

## 3. ShowTime 业务场景

### 首页 — 演出列表

```
用户看到：演出海报、名称、场馆、时间、最低票价
```

对应的 SQL 思路：
- `events` JOIN `venues`（拿场馆名）
- JOIN `event_sessions`（拿场次时间）
- JOIN `seats` 或子查询（算最低票价）
- WHERE 加筛选：分类、城市、日期范围
- GROUP BY 去重
- ORDER BY + LIMIT 分页

### 演出详情

```
用户看到：演出全部信息 + 所有场次 + 各区域票价 + 剩余座位数
```

对应的 SQL 思路：
- 主查询：`events` + `venues`
- 场次列表：`event_sessions` WHERE event_id=?
- 票价：`seat_categories` + `seats` 按区域分组求最低/最高价
- 剩余：COUNT seats WHERE status='available' GROUP BY session_id

### 我的票夹

```
用户看到：我买了哪些票、订单状态、演出信息
```

对应的 SQL 思路：
- `orders` JOIN `order_tickets` JOIN `seats` JOIN `event_sessions` JOIN `events`
- 或者从 `order_tickets` 出发 LEFT JOIN 一路关联
- 按订单分组 `GROUP_CONCAT` 拼接票信息

### 票房统计报表

```
管理员看到：按天/按月销售额、上座率、热门演出 TOP10
```

对应的 SQL 思路：
- `payments` JOIN `orders` JOIN `order_tickets`，GROUP BY DATE(pay_time)
- 上座率：已售座位 / 总座位，按场次或演出分组
- TOP10：按 SUM(amount) 排序

---

## 4. 动手练习

### 练习 1：写演出搜索查询

```sql
-- 需求：查询「上海」地区、「演唱会」分类、时间在 2025-06-01 到 2025-07-31 之间的演出
-- 要求显示：演出名、场馆名、城市、最早场次时间、最低票价
-- 按最低票价升序排列，分页取前 20 条

-- 提示：需要 events + venues + event_sessions + seats（或seat_categories）
-- 先画一下 JOIN 关系，再写 SQL
```

### 练习 2：写我的票夹查询

```sql
-- 需求：查询用户 id=1 的所有订单
-- 要求显示：订单号、下单时间、订单状态、票数、总金额
-- 额外：每个订单用 GROUP_CONCAT 列出「演出名-场次-座位号」

-- 提示：可能需要子查询先算出每个订单的汇总信息
```

### 练习 3：日期函数 + CASE WHEN

```sql
-- 需求：统计过去 30 天每天的订单数和销售额
-- 如果当天没有订单也要显示 0（难度较高，先尝试有订单的天数）
-- 额外：用 CASE WHEN 把星期几转成中文
--   DAYOFWEEK(date) 返回 1=周日, 2=周一...
```

### 练习 4：EXISTS vs IN

```sql
-- 查询「有已售出票的演出」
-- 方法A：用 IN 子查询
-- 方法B：用 EXISTS
-- 思考：哪种写法更好？什么时候选 IN，什么时候选 EXISTS？
```

### 练习 5：UNION 跨表搜索

```sql
-- 需求：用户输入关键词，同时搜索演出名称和场馆名称
-- 要求：返回统一格式的结果列表（标题、类型标签「演出」或「场馆」）
```

---

## 5. 关键提醒

- **WHERE vs HAVING**：WHERE 过滤行（分组前），HAVING 过滤分组（分组后）。`HAVING` 里只能用聚合函数或 GROUP BY 的字段。
- **JOIN 条件 vs WHERE 条件**：LEFT JOIN 时，右表的条件写在 ON 里和写在 WHERE 里效果完全不同。
- **深分页问题**：`LIMIT 100000, 20` 会扫描前 100020 行再丢弃前 100000 行。后续索引章节会讲怎么优化。
- **子查询 vs JOIN**：大多数情况 JOIN 更快，但 EXISTS 子查询在「是否存在」场景下很好用。
- **COUNT(*) vs COUNT(col)**：`COUNT(*)` 统计所有行，`COUNT(col)` 不统计 NULL。
