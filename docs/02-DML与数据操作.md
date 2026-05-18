# 02 — DML · 数据操作

> 对应学习文档：第2章 SQL（DML 部分）、进阶第3章 SQL优化（批量插入部分）

---

## 1. 知识清单

| 技能 | 语法 |
|------|------|
| 插入单行 | `INSERT INTO table (col1, col2) VALUES (v1, v2)` |
| 插入多行 | `INSERT INTO table VALUES (v1,v2), (v3,v4), ...` |
| 更新数据 | `UPDATE table SET col = val WHERE condition` |
| 删除数据 | `DELETE FROM table WHERE condition` |
| 清空表 | `TRUNCATE TABLE`（DDL，不可回滚，比 DELETE 快） |

---

## 2. 核心要点

### INSERT 性能对比

| 方式 | 1万条耗时 | 适用场景 |
|------|----------|---------|
| 逐条 INSERT + 自动提交 | ~30s | 别用 |
| 批量 VALUES | ~0.5s | 日常导入 |
| 手动事务 + 批量 VALUES | ~0.2s | **推荐** |
| LOAD DATA INFILE | ~0.05s | 百万级数据导入 |

**结论**：批量插入时一定要关掉自动提交，手动控制事务。

### UPDATE / DELETE 的大忌

```sql
-- ❌ 千万别这样写（忘了 WHERE 就是灾难）
UPDATE orders SET status = 'cancelled';
DELETE FROM seats;

-- ✅ 先 SELECT 再操作
SELECT * FROM orders WHERE order_id = 123;
-- 确认无误
DELETE FROM orders WHERE order_id = 123;
```

### 外键约束对 DML 的影响

```sql
-- 如果 events 表有外键指向 categories：
DELETE FROM categories WHERE id = 1;
-- ON DELETE RESTRICT → 报错，不允许删
-- ON DELETE CASCADE  → 自动删除关联的 events（危险！）
-- ON DELETE SET NULL → category_id 被设为 NULL
```

---

## 3. ShowTime 业务场景

### 这些操作会对应到哪些 DML？

| 业务操作 | SQL | 注意点 |
|---------|-----|-------|
| 用户注册 | INSERT INTO users | phone/email 唯一，违反报错 |
| 管理员添加演出 | INSERT INTO events | category_id 必须是 categories 中存在的 |
| 用户选座锁座 | UPDATE seats SET status='locked' | WHERE 条件要精确到行+列 |
| 支付成功 | UPDATE seats SET status='sold' | 要在事务中执行 |
| 取消订单 | UPDATE orders + UPDATE seats | 两张表要一起改，需要事务 |
| 过期订单取消 | UPDATE orders SET status='expired' | 批量更新注意行数 |
| 批量导入座位 | INSERT INTO seats (多行) | 用批量 VALUES + 手动事务 |

### 数据生成：演出系统需要多少数据？

- 场馆：20 个
- 演出分类：10 个
- 演出：200 场
- 场次：每演出 1-5 场，共 600 场次
- 座位：每场次约 2000 个 → 120 万行 ← **批量插入的关键场景**
- 用户：100 万 ← **数据生成脚本批量造**

---

## 4. 动手练习

### 练习 1：批量插入对比

```sql
-- 目标：插入 10 万条座位数据

-- 方法A：逐条（先计时，预估要多久？）
-- 方法B：1000 条一个批次 + 手动事务
-- 方法C：LOAD DATA（如果文件准备好了）

-- 记录每种方法的耗时：
```

### 练习 2：外键级联测试

```sql
-- 1. 创建一个分类 '摇滚'，id=10
-- 2. 创建一场演出，category_id=10
-- 3. 尝试 DELETE FROM categories WHERE id=10
--    观察结果 → 取决于你的 ON DELETE 设置
-- 4. 如果被阻止，思考：这个设置合理吗？
```

### 练习 3：安全的更新操作

```sql
-- 场景：把所有「已锁定超过15分钟」的座位释放
-- 先写出 SELECT 验证：
SELECT seat_id, status, lock_time 
FROM seats 
WHERE status = 'locked' 
  AND lock_time < DATE_SUB(NOW(), INTERVAL 15 MINUTE);

-- 确认无误后，写出 UPDATE：
```

---

## 5. 关键提醒

- **生产环境写 UPDATE/DELETE 前必须 SELECT 确认**，养成肌肉记忆
- **TRUNCATE vs DELETE**：TRUNCATE 是 DDL（不能回滚、重置自增ID），DELETE 是 DML（可回滚）
- **批量插入关自动提交**：`SET autocommit=0; ...; COMMIT;`
- **外键默认是 RESTRICT**：不会悄无声息地删数据，相对安全
