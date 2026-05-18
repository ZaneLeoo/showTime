# 01 — DDL · 约束 · 存储引擎

> 对应学习文档：第2章 SQL（DDL 部分）、第4章 约束、进阶第1章 存储引擎

---

## 1. 知识清单：你能动手做什么

| 技能 | 具体操作 |
|------|---------|
| 创建/删除/切换数据库 | `CREATE DATABASE`, `DROP DATABASE`, `USE` |
| 创建表 | `CREATE TABLE`，指定字段、类型、注释 |
| 修改表结构 | `ALTER TABLE`（加字段、改类型、删字段） |
| 删除表 | `DROP TABLE` |
| 查看表结构 | `DESC`, `SHOW CREATE TABLE` |
| 数据类型选择 | INT/BIGINT, VARCHAR/TEXT, DECIMAL, DATE/DATETIME, TINYINT |
| PRIMARY KEY | 主键约束，一张表只能有一个 |
| AUTO_INCREMENT | 自增主键 |
| FOREIGN KEY | 外键 + `ON DELETE/UPDATE CASCADE/SET NULL/RESTRICT` |
| UNIQUE | 唯一约束（可多个字段） |
| NOT NULL | 非空约束 |
| DEFAULT | 默认值 |
| CHECK | 检查约束（MySQL 8.0+ 强制） |
| 指定存储引擎 | `ENGINE = InnoDB / MyISAM / Memory` |
| 查看支持的引擎 | `SHOW ENGINES` |

---

## 2. 三种存储引擎：什么时候选哪个

| | InnoDB | MyISAM | Memory |
|----|--------|--------|--------|
| 事务 | 支持 | 不支持 | 不支持 |
| 锁粒度 | 行锁 | 表锁 | 表锁 |
| 外键 | 支持 | 不支持 | 不支持 |
| 数据存储 | 磁盘（.ibd） | 磁盘（.MYD + .MYI） | 内存 |
| 重启后 | 数据保留 | 数据保留 | **数据丢失** |
| 适用场景 | 核心业务数据 | 日志、只读归档 | 临时缓存、会话 |

**面试常问**：InnoDB 和 MyISAM 的区别？
1. InnoDB 支持事务，MyISAM 不支持
2. InnoDB 支持行锁，MyISAM 仅支持表锁
3. InnoDB 支持外键，MyISAM 不支持

---

## 3. ShowTime 业务场景关联

### 你需要设计这些表（先别急，看完后面的文档自己试试）

ShowTime 演出订票系统有哪些数据？

- **场馆**：北京工体、上海梅奔……每个场馆有自己的座位布局
- **演出**：周杰伦演唱会、话剧《雷雨》……属于某个分类
- **场次**：同一演出可能有多场（比如连开三天），每场有不同时间
- **座位**：每个场次下具体可售的座位，分行列、分区域（VIP/A/B/C）、有价格
- **用户**：注册用户信息
- **订单**：一次下单买 N 张票
- **票**：订单里的每一张票，对应一个具体座位
- **支付**：支付记录

### 这张表选什么引擎？

| 表 | 你会怎么选？| 思考要点 |
|----|-----------|---------|
| 核心业务表（用户/订单/票） | ？ | 需要事务吗？有并发修改吗？ |
| 日志表（搜索记录/操作日志） | ？ | 需要事务吗？怎么写入最快？ |
| 临时数据（购物车/登录态） | ？ | 数据丢了能接受吗？ |

---

## 4. 动手练习

### 练习 1：建库建表

```sql
-- 创建数据库，指定字符集
CREATE DATABASE showtime DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建一张场馆表，包含以下字段：
-- id (主键自增), name (场馆名称, 非空), city (城市), address (地址),
-- total_seats (总座位数, 默认0), create_time (创建时间, 默认当前时间)
--
-- 选 InnoDB 引擎
--
-- 把答案写出来，然后跟我给的参考方案对照
```

### 练习 2：加约束

```sql
-- 在上面场馆表基础上：
-- 1. 给 name + city 加联合唯一约束（同一个城市不能有同名场馆）
-- 2. 给 total_seats 加 CHECK 约束（>= 0）
-- 3. 再加一张演出表 events，包含外键指向 categories 表
--
-- 提示：外键字段名建议用 category_id，关联到 categories(id)
-- 思考：ON DELETE 选 RESTRICT 还是 CASCADE？为什么？
```

### 练习 3：引擎对比

```sql
-- 创建三张表，结构相同，引擎不同：
CREATE TABLE test_innodb (id INT, name VARCHAR(20)) ENGINE=InnoDB;
CREATE TABLE test_myisam (id INT, name VARCHAR(20)) ENGINE=MyISAM;
CREATE TABLE test_memory (id INT, name VARCHAR(20)) ENGINE=Memory;

-- 分别插入一条数据
-- 然后重启 MySQL 服务
-- 再查询三张表 → 哪张表的数据丢了？
```

---

## 5. 关键提醒

- **外键不是必须的**：有些公司禁止在生产用外键（性能、分库分表困难），靠应用层保证一致性。但你学习阶段要会用，理解它的作用。
- **DECIMAL 存金额**：永远不要用 FLOAT/DOUBLE 存价格，用 `DECIMAL(10,2)`。
- **字段类型宁小勿大**：状态用 TINYINT 不用 INT，手机号用 CHAR(11) 不用 VARCHAR(255)。
- **UTF8MB4**：建库默认用这个字符集，MySQL 的 `utf8` 其实是阉割版，不支持 emoji。
