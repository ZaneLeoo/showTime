#### 名称 全称 简称

#### 数据库 存储数据的仓库，数据是有组织的进行存储 DataBase（DB）

#### 数据库管

#### 理系统 操纵和管理数据库的大型软件

#### DataBase Management

#### System (DBMS)

#### SQL 操作关系型数据库的编程语言，定义了一套操作

#### 关系型数据库统一 标准

#### Structured Query

#### Language (SQL)

# 1. MySQL概述

#### 在这一章节，我们主要介绍两个部分，数据库相关概念及MySQL数据库的介绍、下载、安装、启动及连

#### 接。

## 1.1 数据库相关概念

#### 在这一部分，我们先来讲解三个概念：数据库、数据库管理系统、SQL。

#### 而目前主流的关系型数据库管理系统的市场占有率排名如下：

#### Oracle：大型的收费数据库，Oracle公司产品，价格昂贵。

#### MySQL：开源免费的中小型数据库，后来Sun公司收购了MySQL，而Oracle又收购了Sun公司。

#### 目前Oracle推出了收费版本的MySQL，也提供了免费的社区版本。


#### SQL Server：Microsoft 公司推出的收费的中型数据库，C#、.net等语言常用。

#### PostgreSQL：开源免费的中小型数据库。

#### DB2：IBM公司的大型收费数据库产品。

#### SQLLite：嵌入式的微型数据库。Android内置的数据库采用的就是该数据库。

#### MariaDB：开源免费的中小型数据库。是MySQL数据库的另外一个分支、另外一个衍生产品，与

#### MySQL数据库有很好的兼容性。

#### 而不论我们使用的是上面的哪一个关系型数据库，最终在操作时，都是使用SQL语言来进行统一操作，

#### 因为我们前面讲到SQL语言，是操作关系型数据库的 统一标准 。所以即使我们现在学习的是MySQL，

#### 假如我们以后到了公司，使用的是别的关系型数据库，如：Oracle、DB2、SQLServer，也完全不用

#### 担心，因为操作的方式都是一致的。

## 1.2 MySQL数据库

### 1.2.1 版本

#### 官方： https://www.mysql.com/

#### MySQL官方提供了两种不同的版本：

#### 社区版本（MySQL Community Server）

#### 免费， MySQL不提供任何技术支持

#### 商业版本（MySQL Enterprise Edition）

#### 收费，可以使用 30 天，官方提供技术支持

#### 本课程采用的是MySQL最新的社区版-MySQL Community Server 8.0.

### 1.2.2 下载


#### 下载地址： https://downloads.mysql.com/archives/installer/

#### 也可以使用课程资料中提供的MySQL的安装包：

### 1.2.3 安装

#### 要想使用MySQL，我们首先先得将MySQL安装好，我们可以根据下面的步骤，一步一步的完成MySQL的

#### 安装。

#### 1). 双击官方下来的安装包文件

#### 2). 根据安装提示进行安装



#### 安装MySQL的相关组件，这个过程可能需要耗时几分钟，耐心等待。



#### 输入MySQL中root用户的密码,一定记得记住该密码


#### 3). 配置


#### 安装好MySQL之后，还需要配置环境变量，这样才可以在任何目录下连接MySQL。

#### A. 在此电脑上，右键选择属性

#### B. 点击左侧的 "高级系统设置"，选择环境变量

#### C. 找到 Path 系统变量, 点击 "编辑"


#### D. 选择 "新建" , 将MySQL Server的安装目录下的bin目录添加到环境变量


### 1.2.4 启动停止

#### MySQL安装完成之后，在系统启动时，会自动启动MySQL服务，我们无需手动启动了。

#### 当然，也可以手动的通过指令启动停止，以管理员身份运行cmd，进入命令行执行如下指令：

```
net start mysql
net stop mysql
```
###### 1

###### 2


#### 注意 ： 上述的 mysql80 是我们在安装MySQL时，默认指定的mysql的系统服务名，不是固

#### 定的，如果未改动，默认就是mysql80。

### 1.2.5 客户端连接

#### 1). 方式一：使用MySQL提供的客户端命令行工具


#### 2). 方式二：使用系统自带的命令行工具执行指令

#### []内为可选参数，如果需要连接远程的MySQL，需要加上这两个参数来指定远程主机IP、端口，如果

#### 连接本地的MySQL，则无需指定这两个参数。

#### 注意： 使用这种方式进行连接时，需要安装完毕后配置PATH环境变量。

### 1.2.6 数据模型

#### 1). 关系型数据库（RDBMS）

#### 概念：建立在关系模型基础上，由多张相互连接的二维表组成的数据库。

#### 而所谓二维表，指的是由行和列组成的表，如下图（就类似于Excel表格数据，有表头、有列、有行，

#### 还可以通过一列关联另外一个表格中的某一列数据）。我们之前提到的MySQL、Oracle、DB2、

#### SQLServer这些都是属于关系型数据库，里面都是基于二维表存储数据的。简单说，基于二维表存储

#### 数据的数据库就成为关系型数据库，不是基于二维表存储数据的数据库，就是非关系型数据库。

```
mysql [-h 127.0.0.1] [-P 3306] -u root -p
```
```
参数：
-h : MySQL服务所在的主机IP
-P : MySQL服务端口号， 默认 3306
-u : MySQL数据库用户名
-p ： MySQL数据库用户名对应的密码
```
###### 1 2 3 4 5 6 7


#### 特点：

#### A. 使用表存储数据，格式统一，便于维护。

#### B. 使用SQL语言操作，标准统一，使用方便。

#### 2). 数据模型

#### MySQL是关系型数据库，是基于二维表进行数据存储的，具体的结构图下:

#### 我们可以通过MySQL客户端连接数据库管理系统DBMS，然后通过DBMS操作数据库。

#### 可以使用SQL语句，通过数据库管理系统操作数据库，以及操作数据库中的表结构及数据。

#### 一个数据库服务器中可以创建多个数据库，一个数据库中也可以包含多张表，而一张表中又可以包

#### 含多行记录。

# 2. SQL

#### 全称 Structured Query Language，结构化查询语言。操作关系型数据库的编程语言，定义了

#### 一套操作关系型数据库统一 标准 。


#### 分

#### 类 全称 说明

#### DDL Data Definition

#### Language

#### 数据定义语言，用来定义数据库对象(数据库，表，

#### 字段)

#### DML Data Manipulation

#### Language

#### 数据操作语言，用来对数据库表中的数据进行增删改

#### DQL Data Query Language 数据查询语言，用来查询数据库中表的记录

#### DCL Data Control Language

#### 数据控制语言，用来创建数据库用户、控制数据库的

#### 访问权限

## 2.1 SQL通用语法

#### 在学习具体的SQL语句之前，先来了解一下SQL语言的同于语法。

#### 1). SQL语句可以单行或多行书写，以分号结尾。

#### 2). SQL语句可以使用空格/缩进来增强语句的可读性。

#### 3). MySQL数据库的SQL语句不区分大小写，关键字建议使用大写。

#### 4). 注释：

#### 单行注释：-- 注释内容 或 # 注释内容

#### 多行注释：/* 注释内容 */

## 2.2 SQL分类

#### SQL语句，根据其功能，主要分为四类：DDL、DML、DQL、DCL。

## 2.3 DDL

#### Data Definition Language，数据定义语言，用来定义数据库对象(数据库，表，字段) 。

### 2.3.1 数据库操作

#### 1). 查询所有数据库

```
1 show databases ;
```

#### 2). 查询当前数据库

#### 3). 创建数据库

#### 案例：

#### A. 创建一个itcast数据库, 使用数据库默认的字符集。

#### 在同一个数据库服务器中，不能创建两个名称相同的数据库，否则将会报错。

#### 可以通过if not exists 参数来解决这个问题，数据库不存在, 则创建该数据库，如果存在，则不

#### 创建。

```
1 select database() ;
```
```
create database [ if not exists ] 数据库名 [ default charset 字符集 ] [ collate 排序
规则 ] ;
```
###### 1

```
1 create database itcast;
```
```
1 create database if not extists itcast;
```

#### B. 创建一个itheima数据库，并且指定字符集

#### 4). 删除数据库

#### 如果删除一个不存在的数据库，将会报错。此时，可以加上参数 if exists ，如果数据库存在，再

#### 执行删除，否则不执行删除。

#### 5). 切换数据库

#### 我们要操作某一个数据库下的表时，就需要通过该指令，切换到对应的数据库下，否则是不能操作的。

#### 比如，切换到itcast数据，执行如下SQL：

```
1 create database itheima default charset utf8mb4;
```
```
1 drop database [ if exists ] 数据库名 ;
```
```
1 use 数据库名 ;
```
```
1 use itcast;
```

### 2.3.2 表操作

#### 2.3.2.1 表操作-查询创建

#### 1). 查询当前数据库所有表

#### 比如,我们可以切换到sys这个系统数据库,并查看系统数据库中的所有表结构。

#### 2). 查看指定表结构

#### 通过这条指令，我们可以查看到指定表的字段，字段的类型、是否可以为NULL，是否存在默认值等信

#### 息。

#### 3). 查询指定表的建表语句

```
1 show tables;
```
```
use sys;
show tables;
```
###### 1

###### 2

```
1 desc 表名 ;
```

#### 通过这条指令，主要是用来查看建表语句的，而有部分参数我们在创建表的时候，并未指定也会查询

#### 到，因为这部分是数据库的默认值，如：存储引擎、字符集等。

#### 4). 创建表结构

#### 注意: [...] 内为可选参数，最后一个字段后面没有逗号

#### 比如，我们创建一张表 tb_user ，对应的结构如下，那么建表语句为：

```
1 show create table 表名 ;
```
###### CREATE TABLE 表名(

###### 字段 1 字段 1 类型 [ COMMENT 字段 1 注释 ],

###### 字段 2 字段 2 类型 [COMMENT 字段 2 注释 ],

###### 字段 3 字段 3 类型 [COMMENT 字段 3 注释 ],

###### ......

```
字段n 字段n类型 [COMMENT 字段n注释 ]
) [ COMMENT 表注释 ] ;
```
###### 1 2 3 4 5 6 7


#### 2.3.2.2 表操作-数据类型

#### 在上述的建表语句中，我们在指定字段的数据类型时，用到了int ，varchar，那么在MySQL中除了

#### 以上的数据类型，还有哪些常见的数据类型呢？ 接下来,我们就来详细介绍一下MySQL的数据类型。

#### MySQL中的数据类型有很多，主要分为三类：数值类型、字符串类型、日期时间类型。

#### 1). 数值类型

```
create table tb_user(
id int comment '编号',
name varchar( 50 ) comment '姓名',
age int comment '年龄',
gender varchar( 1 ) comment '性别'
) comment '用户表';
```
###### 1 2 3 4 5 6


##### 类型 大小 有符号(SIGNED)范围 无符号(UNSIGNED)范围 描述

##### TINYINT 1byte (-128，127) (0，255) 小整

##### 数值

##### SMALLINT 2bytes (-32768，32767) (0，65535) 大整

##### 数值

##### MEDIUMINT 3bytes (-8388608，8388607) (0，16777215)

##### 大整

##### 数值

##### INT/INTEGER 4bytes

##### (-2147483648，

##### 2147483647) (0，4294967295)

##### 大整

##### 数值

##### BIGINT 8bytes (-2^63，2^63-1) (0，2^64-1)

##### 极大

##### 整数

##### 值

##### FLOAT 4bytes (-3.402823466 E+38，

##### 3.402823466351 E+38)

##### 0 和 (1.175494351 E-

##### 38 ，3.402823466 E+38)

##### 单精

##### 度浮

##### 点数

##### 值

##### DOUBLE 8bytes

##### (-1.7976931348623157

##### E+308，

##### 1.7976931348623157

##### E+308)

##### 0 和

##### (2.2250738585072014

##### E-308，

##### 1.7976931348623157

##### E+308)

##### 双精

##### 度浮

##### 点数

##### 值

##### DECIMAL 依赖于M(精度)和D(标度)

##### 的值

##### 依赖于M(精度)和D(标度)的

##### 值

##### 小数

##### 值(精

##### 确定

##### 点数)

###### 如:

###### 1). 年龄字段 -- 不会出现负数, 而且人的年龄不会太大

```
age tinyint unsigned
```
```
2). 分数 -- 总分 100 分, 最多出现一位小数
score double(4,1)
```
###### 1 2 3 4 5 6


#### 类型 大小 描述

#### CHAR 0-255 bytes 定长字符串(需要指定长度)

#### VARCHAR 0-65535 bytes 变长字符串(需要指定长度)

#### TINYBLOB 0-255 bytes 不超过 255 个字符的二进制数据

#### TINYTEXT 0-255 bytes 短文本字符串

#### BLOB 0-65 535 bytes 二进制形式的长文本数据

#### TEXT 0-65 535 bytes 长文本数据

#### MEDIUMBLOB 0-16 777 215 bytes 二进制形式的中等长度文本数据

#### MEDIUMTEXT 0-16 777 215 bytes 中等长度文本数据

#### LONGBLOB 0-4 294 967 295 bytes 二进制形式的极大文本数据

#### LONGTEXT 0-4 294 967 295 bytes 极大文本数据

#### 2). 字符串类型

#### char 与 varchar 都可以描述字符串，char是定长字符串，指定长度多长，就占用多少个字符，和

#### 字段值的长度无关 。而varchar是变长字符串，指定的长度为最大占用长度 。相对来说，char的性

#### 能会更高些。

#### 3). 日期时间类型

###### 如：

```
1). 用户名 username ------> 长度不定, 最长不会超过 50
username varchar(50)
```
```
2). 性别 gender ---------> 存储值, 不是男,就是女
gender char(1)
```
```
3). 手机号 phone --------> 固定长度为 11
phone char(11)
```
###### 1 2 3 4 5 6 7 8 9


#### 类型

#### 大

#### 小 范围 格式 描述

#### DATE 3 1000-01-01 至 9999-12-31 YYYY-MM-DD 日期值

#### TIME 3 -838:59:59 至 838:59:59 HH:MM:SS 时间值或持续

#### 时间

#### YEAR 1 1901 至 2155 YYYY 年份值

#### DATETIME 8

#### 1000-01-01 00:00:00 至

#### 9999-12-31 23:59:59

#### YYYY-MM-DD

#### HH:MM:SS

#### 混合日期和时

#### 间值

#### TIMESTAMP 4 1970-01-01 00:00:01 至

#### 2038-01-19 03:14:07

#### YYYY-MM-DD

#### HH:MM:SS

#### 混合日期和时

#### 间值，时间戳

#### 2.3.2.3 表操作-案例

#### 设计一张员工信息表，要求如下：

#### 1. 编号（纯数字）

#### 2. 员工工号 (字符串类型，长度不超过 10 位)

#### 3. 员工姓名（字符串类型，长度不超过 10 位）

#### 4. 性别（男/女，存储一个汉字）

#### 5. 年龄（正常人年龄，不可能存储负数）

#### 6. 身份证号（二代身份证号均为 18 位，身份证中有X这样的字符）

#### 7. 入职时间（取值年月日即可）

#### 对应的建表语句如下:

###### 如:

```
1). 生日字段 birthday
birthday date
```
```
2). 创建时间 createtime
createtime datetime
```
###### 1 2 3 4 5 6


#### SQL语句编写完毕之后，就可以在MySQL的命令行中执行SQL，然后也可以通过 desc 指令查询表结构

#### 信息：

#### 表结构创建好了，里面的name字段是varchar类型，最大长度为 10 ，也就意味着如果超过 10 将会报

#### 错，如果我们想修改这个字段的类型 或 修改字段的长度该如何操作呢？接下来再来讲解DDL语句中，

#### 如何操作表字段。

#### 2.3.2.4 表操作-修改

#### 1). 添加字段

#### 案例:

#### 为emp表增加一个新的字段”昵称”为nickname，类型为varchar(20)

#### 2). 修改数据类型

```
create table emp(
id int comment '编号',
workno varchar( 10 ) comment '工号',
name varchar( 10 ) comment '姓名',
gender char( 1 ) comment '性别',
age tinyint unsigned comment '年龄',
idcard char( 18 ) comment '身份证号',
entrydate date comment '入职时间'
) comment '员工表';
```
###### 1 2 3 4 5 6 7 8 9

###### 1 ALTER TABLE 表名 ADD 字段名 类型 (长度) [ COMMENT 注释 ] [ 约束 ];

```
1 ALTER TABLE emp ADD nickname varchar( 20 ) COMMENT '昵称';
```
###### 1 ALTER TABLE 表名 MODIFY 字段名 新数据类型 (长度);


#### 3). 修改字段名和字段类型

#### 案例:

#### 将emp表的nickname字段修改为username，类型为varchar(30)

#### 4). 删除字段

#### 案例:

#### 将emp表的字段username删除

#### 5). 修改表名

#### 案例:

#### 将emp表的表名修改为 employee

#### 2.3.2.5 表操作-删除

#### 1). 删除表

#### 可选项 IF EXISTS 代表，只有表名存在时才会删除该表，表名不存在，则不执行删除操作(如果不

#### 加该参数项，删除一张不存在的表，执行将会报错)。

#### 案例:

#### 如果tb_user表存在，则删除tb_user表

###### 1 ALTER TABLE 表名 CHANGE 旧字段名 新字段名 类型 (长度) [ COMMENT 注释 ] [ 约束 ];

```
1 ALTER TABLE emp CHANGE nickname username varchar( 30 ) COMMENT '昵称';
```
###### 1 ALTER TABLE 表名 DROP 字段名;

```
1 ALTER TABLE emp DROP username;
```
###### 1 ALTER TABLE 表名 RENAME TO 新表名;

```
1 ALTER TABLE emp RENAME TO employee;
```
###### 1 DROP TABLE [ IF EXISTS ] 表名;


#### 2). 删除指定表, 并重新创建表

#### 注意: 在删除表的时候，表中的全部数据也都会被删除。

## 2.4 图形化界面工具

#### 上述，我们已经讲解了通过DDL语句，如何操作数据库、操作表、操作表中的字段，而通过DDL语句执

#### 行在命令进行操作，主要存在以下两点问题：

#### 1).会影响开发效率 ;

#### 2). 使用起来，并不直观，并不方便 ；

#### 所以呢，我们在日常的开发中，会借助于MySQL的图形化界面，来简化开发，提高开发效率。而目前

#### mysql主流的图形化界面工具，有以下几种：

#### 而本次课程中，选择最后一种DataGrip，这种图形化界面工具，功能更加强大，界面提示更加友好，

#### 是我们使用MySQL的不二之选。接下来，我们来介绍一下DataGrip该如何安装、使用。

### 2.4.1 安装

#### 1). 找到资料中准备好的安装包，双击开始安装

```
1 DROP TABLE IF EXISTS tb_user;
```
###### 1 TRUNCATE TABLE 表名;


#### 2). 点击next，一步一步的完成安装

#### 选择DataGrip的安装目录，然后选择下一步


#### 下一步，执行安装


### 2.4.2 使用

#### 1). 添加数据源

#### 参考图示, 一步步操作即可

#### 配置以及驱动jar包下载完毕之后，就可以点击 "Test Connection" 就可以测试，是否可以连接

#### MySQL，如果出现 "Successed"，就表名连接成功了 。


#### 2). 展示所有数据库

#### 连接上了MySQL服务之后，并未展示出所有的数据库，此时，我们需要设置，展示所有的数据库，具体

#### 操作如下：

#### 3). 创建数据库


#### 注意:

#### 以下两种方式都可以创建数据库：

#### A. create database db01;

#### B. create schema db01;

#### 4). 创建表

#### 在指定的数据库上面右键，选择new --> Table


#### 5). 修改表结构

#### 在需要修改的表上，右键选择 "Modify Table..."


#### 如果想增加字段，直接点击+号，录入字段信息，然后点击Execute即可。

#### 如果想删除字段，直接点击-号，就可以删除字段，然后点击Execute即可。

#### 如果想修改字段，双击对应的字段，修改字段信息，然后点击Execute即可。

#### 如果要修改表名，或表的注释，直接在输入框修改，然后点击Execute即可。


#### 6). 在DataGrip中执行SQL语句

#### 在指定的数据库上，右键，选择 New --> Query Console

#### 然后就可以在打开的Query Console控制台，并在控制台中编写SQL，执行SQL。

## 2.5 DML

#### DML英文全称是Data Manipulation Language(数据操作语言)，用来对数据库中表的数据记录进

#### 行增、删、改操作。

#### 添加数据（INSERT）

#### 修改数据（UPDATE）

#### 删除数据（DELETE）

### 2.5.1 添加数据

#### 1). 给指定字段添加数据

###### 1 INSERT INTO 表名 (字段名1, 字段名2, ...) VALUES (值1, 值2, ...);


#### 案例: 给employee表所有的字段添加数据 ；

#### 插入数据完成之后，我们有两种方式，查询数据库的数据：

#### A. 方式一

#### 在左侧的表名上双击，就可以查看这张表的数据。

#### B. 方式二

#### 可以直接一条查询数据的SQL语句, 语句如下:

#### 案例: 给employee表所有的字段添加数据

#### 执行如下SQL，添加的年龄字段值为-1。

#### 执行上述的SQL语句时，报错了，具体的错误信息如下：

#### 因为 employee 表的age字段类型为 tinyint，而且还是无符号的 unsigned ，所以取值只能在

#### 0-255 之间。

```
insert into employee(id,workno,name,gender,age,idcard,entrydate)
values( 1 ,'1','Itcast','男', 10 ,'123456789012345678','2000-01-01');
```
###### 1

```
1 select * from employee;
```
```
insert into employee(id,workno,name,gender,age,idcard,entrydate)
values( 1 ,'1','Itcast','男',- 1 ,'123456789012345678','2000-01-01');
```
###### 1


#### 2). 给全部字段添加数据

#### 案例：插入数据到employee表，具体的SQL如下：

#### 3). 批量添加数据

#### 案例：批量插入数据到employee表，具体的SQL如下：

#### 注意事项:

- 插入数据时，指定的字段顺序需要与值的顺序是一一对应的。

###### 1 INSERT INTO 表名 VALUES (值1, 值2, ...);

```
insert into employee values( 2 ,'2','张无忌','男', 18 ,'123456789012345670','2005-01-
01');
```
###### 1

###### INSERT INTO 表名 (字段名1, 字段名2, ...) VALUES (值1, 值2, ...), (值1, 值2, ...), (值

###### 1, 值2, ...) ;

###### 1

###### 1 INSERT INTO 表名 VALUES (值1, 值2, ...), (值1, 值2, ...), (值1, 值2, ...) ;

```
insert into employee values( 3 ,'3','韦一笑','男', 38 ,'123456789012345670','2005-01-
01'),( 4 ,'4','赵敏','女', 18 ,'123456789012345670','2005-01-01');
```
###### 1


- 字符串和日期型数据应该包含在引号中。
- 插入的数据大小，应该在字段的规定范围内。

### 2.5.2 修改数据

#### 修改数据的具体语法为:

#### 案例:

#### A. 修改id为 1 的数据，将name修改为itheima

#### B. 修改id为 1 的数据, 将name修改为小昭, gender修改为 女

#### C. 将所有的员工入职日期修改为 2008-01-01

#### 注意事项:

#### 修改语句的条件可以有，也可以没有，如果没有条件，则会修改整张表的所有数据。

### 2.5.3 删除数据

#### 删除数据的具体语法为：

#### 案例:

#### A. 删除gender为女的员工

###### 1 UPDATE 表名 SET 字段名1 = 值1 , 字段名2 = 值2 , .... [ WHERE 条件 ] ;

```
1 update employee set name = 'itheima' where id = 1 ;
```
```
1 update employee set name = '小昭' , gender = '女' where id = 1 ;
```
```
1 update employee set entrydate = '2008-01-01';
```
###### 1 DELETE FROM 表名 [ WHERE 条件 ] ;


#### B. 删除所有员工

#### 注意事项:

- DELETE 语句的条件可以有，也可以没有，如果没有条件，则会删除整张表的所有数

#### 据。

- DELETE 语句不能删除某一个字段的值(可以使用UPDATE，将该字段值置为NULL即

#### 可)。

- 当进行删除全部数据操作时，datagrip会提示我们，询问是否确认删除，我们直接点击

#### Execute即可。

## 2.6 DQL

#### DQL英文全称是Data Query Language(数据查询语言)，数据查询语言，用来查询数据库中表的记

#### 录。

#### 查询关键字: SELECT

#### 在一个正常的业务系统中，查询操作的频次是要远高于增删改的，当我们去访问企业官网、电商网站，

#### 在这些网站中我们所看到的数据，实际都是需要从数据库中查询并展示的。而且在查询的过程中，可能

#### 还会涉及到条件、排序、分页等操作。

```
1 delete from employee where gender = '女';
```
```
1 delete from employee;
```

#### 那么，本小节我们主要学习的就是如何进行数据的查询操作。 我们先来完成如下数据准备工作:

```
drop table if exists employee;
```
```
create table emp(
id int comment '编号',
workno varchar( 10 ) comment '工号',
name varchar( 10 ) comment '姓名',
gender char( 1 ) comment '性别',
age tinyint unsigned comment '年龄',
idcard char( 18 ) comment '身份证号',
workaddress varchar( 50 ) comment '工作地址',
entrydate date comment '入职时间'
)comment '员工表';
```
```
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 1 , '00001', '柳岩666', '女', 20 , '123456789012345678', '北京', '2000-01-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 2 , '00002', '张无忌', '男', 18 , '123456789012345670', '北京', '2005-09-
01');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15


```
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 3 , '00003', '韦一笑', '男', 38 , '123456789712345670', '上海', '2005-08-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 4 , '00004', '赵敏', '女', 18 , '123456757123845670', '北京', '2009-12-01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 5 , '00005', '小昭', '女', 16 , '123456769012345678', '上海', '2007-07-01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 6 , '00006', '杨逍', '男', 28 , '12345678931234567X', '北京', '2006-01-01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 7 , '00007', '范瑶', '男', 40 , '123456789212345670', '北京', '2005-05-01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 8 , '00008', '黛绮丝', '女', 38 , '123456157123645670', '天津', '2015-05-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 9 , '00009', '范凉凉', '女', 45 , '123156789012345678', '北京', '2010-04-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 10 , '00010', '陈友谅', '男', 53 , '123456789012345670', '上海', '2011-01-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 11 , '00011', '张士诚', '男', 55 , '123567897123465670', '江苏', '2015-05-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 12 , '00012', '常遇春', '男', 32 , '123446757152345670', '北京', '2004-02-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 13 , '00013', '张三丰', '男', 88 , '123656789012345678', '江苏', '2020-11-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 14 , '00014', '灭绝', '女', 65 , '123456719012345670', '西安', '2019-05-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 15 , '00015', '胡青牛', '男', 70 , '12345674971234567X', '西安', '2018-04-
01');
INSERT INTO emp (id, workno, name, gender, age, idcard, workaddress, entrydate)
VALUES ( 16 , '00016', '周芷若', '女', 18 , null, '北京', '2012-06-01');
```
###### 16

###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23

###### 24

###### 25

###### 26

###### 27

###### 28

###### 29


#### 准备完毕后，我们就可以看到emp表中准备的 16 条数据。接下来，我们再来完成DQL语法的学习。

### 2.6.1 基本语法

#### DQL 查询语句，语法结构如下：

#### 我们在讲解这部分内容的时候，会将上面的完整语法进行拆分，分为以下几个部分：

#### 基本查询（不带任何条件）

#### 条件查询（WHERE）

#### 聚合函数（count、max、min、avg、sum）

#### 分组查询（group by）

#### 排序查询（order by）

#### 分页查询（limit）

### 2.6.2 基础查询

#### 在基本查询的DQL语句中，不带任何的查询条件，查询的语法如下：

#### 1). 查询多个字段

###### SELECT

###### 字段列表

###### FROM

###### 表名列表

###### WHERE

###### 条件列表

###### GROUP BY

###### 分组字段列表

###### HAVING

###### 分组后条件列表

###### ORDER BY

###### 排序字段列表

###### LIMIT

###### 分页参数

###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 1 SELECT 字段1, 字段2, 字段3 ... FROM 表名 ;

###### 1 SELECT * FROM 表名 ;


#### 注意 : * 号代表查询所有字段，在实际开发中尽量少用（不直观、影响效率）。

#### 2). 字段设置别名

#### 3). 去除重复记录

#### 案例：

#### A. 查询指定字段 name, workno, age并返回

#### B. 查询返回所有字段

#### C. 查询所有员工的工作地址,起别名

#### D. 查询公司员工的上班地址有哪些(不要重复)

### 2.6.3 条件查询

#### 1). 语法

###### 1 SELECT 字段1 [ AS 别名1 ] , 字段2 [ AS 别名2 ] ... FROM 表名;

###### 1 SELECT 字段1 [ 别名1 ] , 字段2 [ 别名2 ] ... FROM 表名;

###### 1 SELECT DISTINCT 字段列表 FROM 表名;

```
1 select name,workno,age from emp;
```
```
1 select id ,workno,name,gender,age,idcard,workaddress,entrydate from emp;
```
```
1 select * from emp;
```
```
1 select workaddress as '工作地址' from emp;
```
```
-- as可以省略
select workaddress '工作地址' from emp;
```
###### 1

###### 2

```
1 select distinct workaddress '工作地址' from emp;
```

#### 比较运算符 功能

#### > 大于

#### >= 大于等于

#### < 小于

#### <= 小于等于

#### = 等于

#### <> 或 != 不等于

#### BETWEEN ... AND ... 在某个范围之内(含最小、最大值)

#### IN(...) 在in之后的列表中的值，多选一

#### LIKE 占位符 模糊匹配(_匹配单个字符, %匹配任意个字符)

#### IS NULL 是NULL

#### 逻辑运算符 功能

#### AND 或 && 并且 (多个条件同时成立)

#### OR 或 || 或者 (多个条件任意一个成立)

#### NOT 或! 非 , 不是

#### 2). 条件

#### 常用的比较运算符如下:

#### 常用的逻辑运算符如下:

#### 案例:

#### A. 查询年龄等于 88 的员工

#### B. 查询年龄小于 20 的员工信息

###### 1 SELECT 字段列表 FROM 表名 WHERE 条件列表 ;

```
1 select * from emp where age = 88 ;
```

#### C. 查询年龄小于等于 20 的员工信息

#### D. 查询没有身份证号的员工信息

#### E. 查询有身份证号的员工信息

#### F. 查询年龄不等于 88 的员工信息

#### G. 查询年龄在 15 岁(包含) 到 20 岁(包含)之间的员工信息

#### H. 查询性别为 女 且年龄小于 25 岁的员工信息

#### I. 查询年龄等于 18 或 20 或 40 的员工信息

#### J. 查询姓名为两个字的员工信息 _ %

#### K. 查询身份证号最后一位是X的员工信息

```
1 select * from emp where age < 20 ;
```
```
1 select * from emp where age <= 20 ;
```
```
1 select * from emp where idcard is null;
```
```
1 select * from emp where idcard is not null;
```
```
select * from emp where age != 88 ;
select * from emp where age <> 88 ;
```
###### 1

###### 2

```
select * from emp where age >= 15 && age <= 20 ;
select * from emp where age >= 15 and age <= 20 ;
select * from emp where age between 15 and 20 ;
```
###### 1

###### 2

###### 3

```
1 select * from emp where gender = '女' and age < 25 ;
```
```
select * from emp where age = 18 or age = 20 or age = 40 ;
select * from emp where age in( 18 , 20 , 40 );
```
###### 1

###### 2

```
1 select * from emp where name like '__';
```
```
select * from emp where idcard like '%X';
select * from emp where idcard like '_________________X';
```
###### 1

###### 2


#### 函数 功能

#### count 统计数量

#### max 最大值

#### min 最小值

#### avg 平均值

#### sum 求和

### 2.6.4 聚合函数

#### 1). 介绍

#### 将一列数据作为一个整体，进行纵向计算 。

#### 2). 常见的聚合函数

#### 3). 语法

#### 注意 : NULL值是不参与所有聚合函数运算的。

#### 案例：

#### A. 统计该企业员工数量

#### 对于count聚合函数，统计符合条件的总记录数，还可以通过 count(数字/字符串)的形式进行统计

#### 查询，比如：

#### 对于count(*) 、count(字段)、 count(1) 的具体原理，我们在进阶篇中SQL优化部分会详

#### 细讲解，此处大家只需要知道如何使用即可。

#### B. 统计该企业员工的平均年龄

###### 1 SELECT 聚合函数(字段列表) FROM 表名 ;

```
select count(*) from emp; -- 统计的是总记录数
select count(idcard) from emp; -- 统计的是idcard字段不为null的记录数
```
###### 1

###### 2

```
1 select count( 1 ) from emp;
```
```
1 select avg(age) from emp;
```

#### C. 统计该企业员工的最大年龄

#### D. 统计该企业员工的最小年龄

#### E. 统计西安地区员工的年龄之和

### 2.6.5 分组查询

#### 1). 语法

#### 2). where与having区别

#### 执行时机不同：where是分组之前进行过滤，不满足where条件，不参与分组；而having是分组

#### 之后对结果进行过滤。

#### 判断条件不同：where不能对聚合函数进行判断，而having可以。

#### 注意事项:

- 分组之后，查询的字段一般为聚合函数和分组字段，查询其他字段无任何意义。
- 执行顺序: where > 聚合函数 > having 。
- 支持多字段分组, 具体语法为 : group by columnA,columnB

#### 案例:

#### A. 根据性别分组 , 统计男性员工 和 女性员工的数量

#### B. 根据性别分组 , 统计男性员工 和 女性员工的平均年龄

```
1 select max(age) from emp;
```
```
1 select min(age) from emp;
```
```
1 select sum(age) from emp where workaddress = '西安';
```
###### SELECT 字段列表 FROM 表名 [ WHERE 条件 ] GROUP BY 分组字段名 [ HAVING 分组

###### 后过滤条件 ];

###### 1

```
1 select gender, count(*) from emp group by gender ;
```
```
1 select gender, avg(age) from emp group by gender ;
```

#### C. 查询年龄小于 45 的员工 , 并根据工作地址分组 , 获取员工数量大于等于 3 的工作地址

#### D. 统计各个工作地址上班的男性及女性员工的数量

### 2.6.6 排序查询

#### 排序在日常开发中是非常常见的一个操作，有升序排序，也有降序排序。

#### 1). 语法

#### 2). 排序方式

#### ASC : 升序(默认值)

#### DESC: 降序

#### 注意事项：

- 如果是升序, 可以不指定排序方式ASC ;
- 如果是多字段排序，当第一个字段值相同时，才会根据第二个字段进行排序 ;

#### 案例:

#### A. 根据年龄对公司的员工进行升序排序

#### B. 根据入职时间, 对员工进行降序排序

```
select workaddress, count(*) address_count from emp where age < 45 group by
workaddress having address_count >= 3 ;
```
###### 1

```
select workaddress, gender, count(*) '数量' from emp group by gender , workaddress
;
```
###### 1

###### 1 SELECT 字段列表 FROM 表名 ORDER BY 字段 1 排序方式1 , 字段 2 排序方式2 ;

```
select * from emp order by age asc;
select * from emp order by age;
```
###### 1

###### 2

```
1 select * from emp order by entrydate desc;
```

#### C. 根据年龄对公司的员工进行升序排序 , 年龄相同 , 再按照入职时间进行降序排序

### 2.6.7 分页查询

#### 分页操作在业务系统开发时，也是非常常见的一个功能，我们在网站中看到的各种各样的分页条，后台

#### 都需要借助于数据库的分页操作。

#### 1). 语法

#### 注意事项:

- 起始索引从 0 开始，起始索引 = （查询页码 - 1）* 每页显示记录数。
- 分页查询是数据库的方言，不同的数据库有不同的实现，MySQL中是LIMIT。
- 如果查询的是第一页数据，起始索引可以省略，直接简写为 limit 10 。

#### 案例:

#### A. 查询第 1 页员工数据, 每页展示 10 条记录

#### B. 查询第 2 页员工数据, 每页展示 10 条记录 --------> (页码-1)*页展示记录数

### 2.6.8 案例

#### 1). 查询年龄为20,21,22,23岁的员工信息。

```
1 select * from emp order by age asc , entrydate desc;
```
###### 1 SELECT 字段列表 FROM 表名 LIMIT 起始索引, 查询记录数 ;

```
select * from emp limit 0 , 10 ;
select * from emp limit 10 ;
```
###### 1

###### 2

```
1 select * from emp limit 10 , 10 ;
```
```
1 select * from emp where gender = '女' and age in( 20 , 21 , 22 , 23 );
```

#### 2). 查询性别为 男 ，并且年龄在 20-40 岁(含)以内的姓名为三个字的员工。

#### 3). 统计员工表中, 年龄小于 60 岁的 , 男性员工和女性员工的人数。

#### 4). 查询所有年龄小于等于 35 岁员工的姓名和年龄，并对查询结果按年龄升序排序，如果年龄相同按

#### 入职时间降序排序。

#### 5). 查询性别为男，且年龄在20-40 岁(含)以内的前 5 个员工信息，对查询的结果按年龄升序排序，

#### 年龄相同按入职时间升序排序。

### 2.6.9 执行顺序

#### 在讲解DQL语句的具体语法之前，我们已经讲解了DQL语句的完整语法，及编写顺序，接下来，我们要

#### 来说明的是DQL语句在执行时的执行顺序，也就是先执行那一部分，后执行那一部分。

#### 验证：

#### 查询年龄大于 15 的员工姓名、年龄，并根据年龄进行升序排序。

```
select * from emp where gender = '男' and ( age between 20 and 40 ) and name like
'___';
```
###### 1

```
1 select gender, count(*) from emp where age < 60 group by gender;
```
```
1 select name , age from emp where age <= 35 order by age asc , entrydate desc;
```
```
select * from emp where gender = '男' and age between 20 and 40 order by age asc ,
entrydate asc limit 5 ;
```
###### 1


#### 在查询时，我们给emp表起一个别名 e，然后在select 及 where中使用该别名。

#### 执行上述SQL语句后，我们看到依然可以正常的查询到结果，此时就说明： from 先执行, 然后

#### where 和 select 执行。那 where 和 select 到底哪个先执行呢?

#### 此时，此时我们可以给select后面的字段起别名，然后在 where 中使用这个别名，然后看看是否可

#### 以执行成功。

#### 执行上述SQL报错了:

#### 由此我们可以得出结论: from 先执行，然后执行 where ， 再执行select 。

#### 接下来，我们再执行如下SQL语句，查看执行效果：

#### 结果执行成功。 那么也就验证了: order by 是在select 语句之后执行的。

#### 综上所述，我们可以看到DQL语句的执行顺序为： from ... where ... group by ...

#### having ... select ... order by ... limit ...

## 2.7 DCL

#### DCL英文全称是 Data Control Language (数据控制语言)，用来管理数据库用户、控制数据库的访

#### 问权限。

```
1 select name , age from emp where age > 15 order by age asc;
```
```
1 select e.name , e.age from emp e where e.age > 15 order by age asc;
```
```
1 select e.name ename , e.age eage from emp e where eage > 15 order by age asc;
```
```
1 select e.name ename , e.age eage from emp e where e.age > 15 order by eage asc;
```

### 2.7.1 管理用户

#### 1). 查询用户

#### 查询的结果如下:

#### 其中 Host代表当前用户访问的主机, 如果为localhost, 仅代表只能够在当前本机访问，是不可以

#### 远程访问的。 User代表的是访问该数据库的用户名。在MySQL中需要通过Host和User来唯一标识一

#### 个用户。

#### 2). 创建用户

#### 3). 修改用户密码

#### 4). 删除用户

#### 注意事项:

- 在MySQL中需要通过用户名@主机名的方式，来唯一标识一个用户。

```
1 select * from mysql.user;
```
###### 1 CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';

```
1 ALTER USER '用户名'@'主机名' IDENTIFIED WITH mysql_native_password BY '新密码' ;
```
###### 1 DROP USER '用户名'@'主机名' ;


#### 权限 说明

#### ALL, ALL PRIVILEGES 所有权限

#### SELECT 查询数据

#### INSERT 插入数据

#### UPDATE 修改数据

#### DELETE 删除数据

#### ALTER 修改表

#### DROP 删除数据库/表/视图

#### CREATE 创建数据库/表

- 主机名可以使用 % 通配。
- 这类SQL开发人员操作的比较少，主要是DBA（ Database Administrator 数据库

#### 管理员）使用。

#### 案例：

#### A. 创建用户itcast, 只能够在当前主机localhost访问, 密码123456;

#### B. 创建用户heima, 可以在任意主机访问该数据库, 密码123456;

#### C. 修改用户heima的访问密码为1234;

#### D. 删除 itcast@localhost 用户

### 2.7.2 权限控制

#### MySQL中定义了很多种权限，但是常用的就以下几种：

```
1 create user 'itcast'@'localhost' identified by '123456';
```
```
1 create user 'heima'@'%' identified by '123456';
```
```
1 alter user 'heima'@'%' identified with mysql_native_password by '1234';
```
```
1 drop user 'itcast'@'localhost';
```

#### 上述只是简单罗列了常见的几种权限描述，其他权限描述及含义，可以直接参考 官方文档 。

#### 1). 查询权限

#### 2). 授予权限

#### 3). 撤销权限

#### 注意事项：

- 多个权限之间，使用逗号分隔
- 授权时， 数据库名和表名可以使用 * 进行通配，代表所有。

#### 案例:

#### A. 查询 'heima'@'%' 用户的权限

#### B. 授予 'heima'@'%' 用户itcast数据库所有表的所有操作权限

#### C. 撤销 'heima'@'%' 用户的itcast数据库的所有权限

# 3. 函数

###### 1 SHOW GRANTS FOR '用户名'@'主机名' ;

###### 1 GRANT 权限列表 ON 数据库名.表名 TO '用户名'@'主机名';

###### 1 REVOKE 权限列表 ON 数据库名.表名 FROM '用户名'@'主机名';

```
1 show grants for 'heima'@'%';
```
```
1 grant all on itcast.* to 'heima'@'%';
```
```
1 revoke all on itcast.* from 'heima'@'%';
```

#### 函数 是指一段可以直接被另一段程序调用的程序或代码。 也就意味着，这一段程序或代码在MySQL中

#### 已经给我们提供了，我们要做的就是在合适的业务场景调用对应的函数完成对应的业务需求即可。 那

#### 么，函数到底在哪儿使用呢？

#### 我们先来看两个场景：

#### 1). 在企业的OA或其他的人力系统中，经常会提供的有这样一个功能，每一个员工登录上来之后都能

#### 够看到当前员工入职的天数。 而在数据库中，存储的都是入职日期，如 2000-11-12，那如果快速计

#### 算出天数呢？

#### 2). 在做报表这类的业务需求中,我们要展示出学员的分数等级分布。而在数据库中，存储的是学生的

#### 分数值，如98/75，如何快速判定分数的等级呢？

#### 其实，上述的这一类的需求呢，我们通过MySQL中的函数都可以很方便的实现 。

#### MySQL中的函数主要分为以下四类： 字符串函数、数值函数、日期函数、流程函数。

## 3.1 字符串函数

#### MySQL中内置了很多字符串函数，常用的几个如下：


#### 函数 功能

#### CONCAT(S1,S2,...Sn) 字符串拼接，将S1，S2，... Sn拼接成一个字符串

#### LOWER(str) 将字符串str全部转为小写

#### UPPER(str) 将字符串str全部转为大写

#### LPAD(str,n,pad)

#### 左填充，用字符串pad对str的左边进行填充，达到n个字符

#### 串长度

#### RPAD(str,n,pad) 右填充，用字符串pad对str的右边进行填充，达到n个字符

#### 串长度

#### TRIM(str) 去掉字符串头部和尾部的空格

#### SUBSTRING(str,start,len) 返回从字符串str从start位置起的len个长度的字符串

#### 演示如下：

#### A. concat : 字符串拼接

#### B. lower : 全部转小写

#### C. upper : 全部转大写

#### D. lpad : 左填充

#### E. rpad : 右填充

#### F. trim : 去除空格

#### G. substring : 截取子字符串

```
1 select concat('Hello' , ' MySQL');
```
```
1 select lower('Hello');
```
```
1 select upper('Hello');
```
```
1 select lpad('01', 5 , '-');
```
```
1 select rpad('01', 5 , '-');
```
```
1 select trim(' Hello MySQL ');
```

#### 案例:

#### 由于业务需求变更，企业员工的工号，统一为 5 位数，目前不足 5 位数的全部在前面补 0 。比如： 1 号员

#### 工的工号应该为 00001 。

#### 处理完毕后, 具体的数据为:

## 3.2 数值函数

#### 常见的数值函数如下：

```
1 select substring('Hello MySQL', 1 , 5 );
```
```
1 update emp set workno = lpad(workno, 5 , '0');
```

#### 函数 功能

#### CEIL(x) 向上取整

#### FLOOR(x) 向下取整

#### MOD(x,y) 返回x/y的模

#### RAND() 返回0~1内的随机数

#### ROUND(x,y) 求参数x的四舍五入的值，保留y位小数

#### 演示如下：

#### A. ceil：向上取整

#### B. floor：向下取整

#### C. mod：取模

#### D. rand：获取随机数

#### E. round：四舍五入

#### 案例：

#### 通过数据库的函数，生成一个六位数的随机验证码。

#### 思路： 获取随机数可以通过rand()函数，但是获取出来的随机数是在0-1之间的，所以可以在其基础

#### 上乘以 1000000 ，然后舍弃小数部分，如果长度不足 6 位，补 0

```
1 select ceil(1.1);
```
```
1 select floor(1.9);
```
```
1 select mod( 7 , 4 );
```
```
1 select rand();
```
```
1 select round(2.344, 2 );
```
```
1 select lpad(round(rand()* 1000000 , 0 ), 6 , '0');
```

#### 函数 功能

#### CURDATE() 返回当前日期

#### CURTIME() 返回当前时间

#### NOW() 返回当前日期和时间

#### YEAR(date) 获取指定date的年份

#### MONTH(date) 获取指定date的月份

#### DAY(date) 获取指定date的日期

#### DATE_ADD(date, INTERVAL expr

#### type)

#### 返回一个日期/时间值加上一个时间间隔expr后的

#### 时间值

#### DATEDIFF(date1,date2)

#### 返回起始时间date1 和 结束时间date2之间的天

#### 数

## 3.3 日期函数

#### 常见的日期函数如下：

#### 演示如下：

#### A. curdate：当前日期

#### B. curtime：当前时间

#### C. now：当前日期和时间

#### D. YEAR , MONTH , DAY：当前年、月、日

#### E. date_add：增加指定的时间间隔

```
1 select curdate();
```
```
1 select curtime();
```
```
1 select now();
```
```
select YEAR(now());
select MONTH(now());
select DAY(now());
```
###### 1

###### 2

###### 3


#### 函数 功能

#### IF(value , t , f)

#### 如果value为true，则返回t，否则返回

#### f

#### IFNULL(value1 , value2)

#### 如果value1不为空，返回value1，否则

#### 返回value2

#### CASE WHEN [ val1 ] THEN [res1] ...

#### ELSE [ default ] END

#### 如果val1为true，返回res1，... 否

#### 则返回default默认值

#### CASE [ expr ] WHEN [ val1 ] THEN

#### [res1] ... ELSE [ default ] END

#### 如果expr的值等于val1，返回

#### res1，... 否则返回default默认值

#### F. datediff：获取两个日期相差的天数

#### 案例：

#### 查询所有员工的入职天数，并根据入职天数倒序排序。

#### 思路： 入职天数，就是通过当前日期 - 入职日期，所以需要使用datediff函数来完成。

## 3.4 流程函数

#### 流程函数也是很常用的一类函数，可以在SQL语句中实现条件筛选，从而提高语句的效率。

#### 演示如下：

#### A. if

```
1 select date_add(now(), INTERVAL 70 YEAR );
```
```
1 select datediff('2021-10-01', '2021-12-01');
```
```
select name, datediff(curdate(), entrydate) as 'entrydays' from emp order by
entrydays desc;
```
###### 1

```
1 select if(false, 'Ok', 'Error');
```

#### B. ifnull

#### C. case when then else end

#### 需求: 查询emp表的员工姓名和工作地址 (北京/上海 ----> 一线城市 , 其他 ----> 二线城市)

#### 案例:

#### 具体的SQL语句如下:

```
select ifnull('Ok','Default');
```
```
select ifnull('','Default');
```
```
select ifnull(null,'Default');
```
###### 1

###### 2

###### 3

###### 4

###### 5

```
select
name,
( case workaddress when '北京' then '一线城市' when '上海' then '一线城市' else
'二线城市' end ) as '工作地址'
from emp;
```
###### 1

###### 2

###### 3

###### 4

```
create table score(
id int comment 'ID',
name varchar( 20 ) comment '姓名',
math int comment '数学',
english int comment '英语',
chinese int comment '语文'
) comment '学员成绩表';
insert into score(id, name, math, english, chinese) VALUES ( 1 , 'Tom', 67 , 88 , 95
), ( 2 , 'Rose' , 23 , 66 , 90 ),( 3 , 'Jack', 56 , 98 , 76 );
```
###### 1 2 3 4 5 6 7 8


#### MySQL的常见函数我们学习完了，那接下来，我们就来分析一下，在前面讲到的两个函数的案例场景，

#### 思考一下需要用到什么样的函数来实现?

#### 1). 数据库中，存储的是入职日期，如 2000-01-01，如何快速计算出入职天数呢？ -------->

#### 答案: datediff

#### 2). 数据库中，存储的是学生的分数值，如 98 、 75 ，如何快速判定分数的等级呢？ ---------->

#### 答案: case ... when ...

# 4. 约束

## 4.1 概述

#### 概念：约束是作用于表中字段上的规则，用于限制存储在表中的数据。

#### 目的：保证数据库中数据的正确、有效性和完整性。

#### 分类:

```
select
id,
name,
(case when math >= 85 then '优秀' when math >= 60 then '及格' else '不及格' end )
'数学',
(case when english >= 85 then '优秀' when english >= 60 then '及格' else '不及格'
end ) '英语',
(case when chinese >= 85 then '优秀' when chinese >= 60 then '及格' else '不及格'
end ) '语文'
from score;
```
###### 1 2 3 4 5 6 7


#### 约束 描述 关键字

#### 非空约束 限制该字段的数据不能为null NOT NULL

#### 唯一约束 保证该字段的所有数据都是唯一、不重复的 UNIQUE

#### 主键约束 主键是一行数据的唯一标识，要求非空且唯一

#### PRIMARY

#### KEY

#### 默认约束 保存数据时，如果未指定该字段的值，则采用默认值 DEFAULT

#### 检查约束(8.0.16版本

#### 之后)

#### 保证字段值满足某一个条件 CHECK

#### 外键约束 用来让两张表的数据之间建立连接，保证数据的一致

#### 性和完整性

#### FOREIGN

#### KEY

#### 字段名

#### 字段含

#### 义 字段类型 约束条件 约束关键字

#### id

#### ID唯一

#### 标识 int 主键，并且自动增长

#### PRIMARY KEY,

#### AUTO_INCREMENT

#### name 姓名 varchar(10) 不为空，并且唯一 NOT NULL , UNIQUE

#### age 年龄 int 大于^0 ，并且小于等

#### 于 120

#### CHECK

#### status 状态 char(1)

#### 如果没有指定该值，

#### 默认为 1 DEFAULT

#### gender 性别 char(1) 无

#### 注意：约束是作用于表中字段上的，可以在创建表/修改表的时候添加约束。

## 4.2 约束演示

#### 上面我们介绍了数据库中常见的约束，以及约束涉及到的关键字，那这些约束我们到底如何在创建表、

#### 修改表的时候来指定呢，接下来我们就通过一个案例，来演示一下。

#### 案例需求： 根据需求，完成表结构的创建。需求如下：

#### 对应的建表语句为：


#### 在为字段添加约束时，我们只需要在字段之后加上约束的关键字即可，需要关注其语法。我们执行上面

#### 的SQL把表结构创建完成，然后接下来，就可以通过一组数据进行测试，从而验证一下，约束是否可以

#### 生效。

#### 上面，我们是通过编写SQL语句的形式来完成约束的指定，那加入我们是通过图形化界面来创建表结构

#### 时，又该如何来指定约束呢？ 只需要在创建表的时候，根据我们的需要选择对应的约束即可。

```
CREATE TABLE tb_user(
id int AUTO_INCREMENT PRIMARY KEY COMMENT 'ID唯一标识',
name varchar( 10 ) NOT NULL UNIQUE COMMENT '姓名' ,
age int check (age > 0 && age <= 120 ) COMMENT '年龄' ,
status char( 1 ) default '1' COMMENT '状态',
gender char( 1 ) COMMENT '性别'
);
```
###### 1 2 3 4 5 6 7

```
insert into tb_user(name,age,status,gender) values ('Tom1', 19 ,'1','男'),
('Tom2', 25 ,'0','男');
insert into tb_user(name,age,status,gender) values ('Tom3', 19 ,'1','男');
```
```
insert into tb_user(name,age,status,gender) values (null, 19 ,'1','男');
insert into tb_user(name,age,status,gender) values ('Tom3', 19 ,'1','男');
```
```
insert into tb_user(name,age,status,gender) values ('Tom4', 80 ,'1','男');
insert into tb_user(name,age,status,gender) values ('Tom5',- 1 ,'1','男');
insert into tb_user(name,age,status,gender) values ('Tom5', 121 ,'1','男');
```
```
insert into tb_user(name,age,gender) values ('Tom5', 120 ,'男');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11


## 4.3 外键约束

### 4.3.1 介绍

#### 外键：用来让两张表的数据之间建立连接，从而保证数据的一致性和完整性。

#### 我们来看一个例子：

#### 左侧的emp表是员工表，里面存储员工的基本信息，包含员工的ID、姓名、年龄、职位、薪资、入职日

#### 期、上级主管ID、部门ID，在员工的信息中存储的是部门的ID dept_id，而这个部门的ID是关联的

#### 部门表dept的主键id，那emp表的dept_id就是外键,关联的是另一张表的主键。

#### 注意：目前上述两张表，只是在逻辑上存在这样一层关系；在数据库层面，并未建立外键关联，

#### 所以是无法保证数据的一致性和完整性的。

#### 没有数据库外键关联的情况下，能够保证一致性和完整性呢，我们来测试一下。


#### 准备数据

#### 接下来，我们可以做一个测试，删除id为 1 的部门信息。

```
create table dept(
id int auto_increment comment 'ID' primary key,
name varchar( 50 ) not null comment '部门名称'
)comment '部门表';
INSERT INTO dept (id, name) VALUES ( 1 , '研发部'), ( 2 , '市场部'),( 3 , '财务部'), ( 4 ,
'销售部'), ( 5 , '总经办');
```
```
create table emp(
id int auto_increment comment 'ID' primary key,
name varchar( 50 ) not null comment '姓名',
age int comment '年龄',
job varchar( 20 ) comment '职位',
salary int comment '薪资',
entrydate date comment '入职时间',
managerid int comment '直属领导ID',
dept_id int comment '部门ID'
)comment '员工表';
```
```
INSERT INTO emp (id, name, age, job,salary, entrydate, managerid, dept_id)
VALUES
( 1 , '金庸', 66 , '总裁', 20000 , '2000-01-01', null, 5 ),( 2 , '张无忌', 20 ,
'项目经理', 12500 , '2005-12-05', 1 , 1 ),
( 3 , '杨逍', 33 , '开发', 8400 ,'2000-11-03', 2 , 1 ),( 4 , '韦一笑', 48 , '开
发', 11000 , '2002-02-05', 2 , 1 ),
( 5 , '常遇春', 43 , '开发', 10500 , '2004-09-07', 3 , 1 ),( 6 , '小昭', 19 , '程
序员鼓励师', 6600 , '2004-10-12', 2 , 1 );
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19

###### 20

###### 21


#### 结果，我们看到删除成功，而删除成功之后，部门表不存在id为 1 的部门，而在emp表中还有很多的员

#### 工，关联的为id为 1 的部门，此时就出现了数据的不完整性。 而要想解决这个问题就得通过数据库的

#### 外键约束。

### 4.3.2 语法

#### 1). 添加外键

#### 案例:

#### 为emp表的dept_id字段添加外键约束,关联dept表的主键id。

#### 添加了外键约束之后，我们再到dept表(父表)删除id为 1 的记录，然后看一下会发生什么现象。 此时

#### 将会报错，不能删除或更新父表记录，因为存在外键约束。

###### CREATE TABLE 表名(

###### 字段名 数据类型,

###### ...

###### [CONSTRAINT] [外键名称] FOREIGN KEY (外键字段名) REFERENCES 主表 (主表列名)

###### );

###### 1

###### 2

###### 3

###### 4

###### 5

###### ALTER TABLE 表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段名)

###### REFERENCES 主表 (主表列名) ;

###### 1

```
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references
dept(id);
```
###### 1


#### 行为 说明

#### NO

#### ACTION

#### 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不

#### 允许删除/更新。 (与 RESTRICT 一致) 默认行为

#### RESTRICT

#### 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不

#### 允许删除/更新。 (与 NO ACTION 一致) 默认行为

#### CASCADE 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有，则

#### 也删除/更新外键在子表中的记录。

#### SET NULL 当在父表中删除对应记录时，首先检查该记录是否有对应外键，如果有则设置子表

#### 中该外键值为null（这就要求该外键允许取null）。

#### SET

#### DEFAULT 父表有变更时，子表将外键列设置成一个默认的值 (Innodb不支持)

#### 2). 删除外键

#### 案例：

#### 删除emp表的外键fk_emp_dept_id。

### 4.3.3 删除/更新行为

#### 添加了外键之后，再删除父表数据时产生的约束行为，我们就称为删除/更新行为。具体的删除/更新行

#### 为有以下几种:

#### 具体语法为:

#### 演示如下：

###### 1 ALTER TABLE 表名 DROP FOREIGN KEY 外键名称;

```
1 alter table emp drop foreign key fk_emp_dept_id;
```
###### ALTER TABLE 表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段) REFERENCES

###### 主表名 (主表字段名) ON UPDATE CASCADE ON DELETE CASCADE;

###### 1


#### 由于NO ACTION 是默认行为，我们前面语法演示的时候，已经测试过了，就不再演示了，这里我们再

#### 演示其他的两种行为：CASCADE、SET NULL。

#### 1). CASCADE

#### A. 修改父表id为 1 的记录，将id修改为 6

#### 我们发现，原来在子表中dept_id值为 1 的记录，现在也变为 6 了，这就是cascade级联的效果。

#### 在一般的业务系统中，不会修改一张表的主键值。

#### B. 删除父表id为 6 的记录

#### 我们发现，父表的数据删除成功了，但是子表中关联的记录也被级联删除了。

#### 2). SET NULL

#### 在进行测试之前，我们先需要删除上面建立的外键 fk_emp_dept_id。然后再通过数据脚本，将

#### emp、dept表的数据恢复了。

#### 接下来，我们删除id为 1 的数据，看看会发生什么样的现象。

```
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references
dept(id) on update cascade on delete cascade ;
```
###### 1

```
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references
dept(id) on update set null on delete set null ;
```
###### 1


#### 我们发现父表的记录是可以正常的删除的，父表的数据删除之后，再打开子表 emp，我们发现子表emp

#### 的dept_id字段，原来dept_id为 1 的数据，现在都被置为NULL了。

#### 这就是SET NULL这种删除/更新行为的效果。

# 5. 多表查询

#### 我们之前在讲解SQL语句的时候，讲解了DQL语句，也就是数据查询语句，但是之前讲解的查询都是单

#### 表查询，而本章节我们要学习的则是多表查询操作，主要从以下几个方面进行讲解。

## 5.1 多表关系

#### 项目开发中，在进行数据库表结构设计时，会根据业务需求及业务模块之间的关系，分析并设计表结

#### 构，由于业务之间相互关联，所以各个表结构之间也存在着各种联系，基本上分为三种：

#### 一对多(多对一)

#### 多对多

#### 一对一

## 5.1.1 一对多

#### 案例: 部门 与 员工的关系

#### 关系: 一个部门对应多个员工，一个员工对应一个部门

#### 实现: 在多的一方建立外键，指向一的一方的主键


### 5.1.2 多对多

#### 案例: 学生 与 课程的关系

#### 关系: 一个学生可以选修多门课程，一门课程也可以供多个学生选择

#### 实现: 建立第三张中间表，中间表至少包含两个外键，分别关联两方主键

#### 对应的SQL脚本:

```
create table student(
id int auto_increment primary key comment '主键ID',
name varchar( 10 ) comment '姓名',
no varchar( 10 ) comment '学号'
) comment '学生表';
insert into student values (null, '黛绮丝', '2000100101'),(null, '谢逊',
'2000100102'),(null, '殷天正', '2000100103'),(null, '韦一笑', '2000100104');
```
```
create table course(
id int auto_increment primary key comment '主键ID',
name varchar( 10 ) comment '课程名称'
) comment '课程表';
insert into course values (null, 'Java'), (null, 'PHP'), (null , 'MySQL') ,
(null, 'Hadoop');
```
```
create table student_course(
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16


### 5.1.3 一对一

#### 案例: 用户 与 用户详情的关系

#### 关系: 一对一关系，多用于单表拆分，将一张表的基础字段放在一张表中，其他详情字段放在另

#### 一张表中，以提升操作效率

#### 实现: 在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的(UNIQUE)

#### 对应的SQL脚本:

```
id int auto_increment comment '主键' primary key,
studentid int not null comment '学生ID',
courseid int not null comment '课程ID',
constraint fk_courseid foreign key (courseid) references course (id),
constraint fk_studentid foreign key (studentid) references student (id)
)comment '学生课程中间表';
```
```
insert into student_course values (null, 1 , 1 ),(null, 1 , 2 ),(null, 1 , 3 ),(null, 2 , 2 ),
(null, 2 , 3 ),(null, 3 , 4 );
```
###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23

###### 24

```
create table tb_user(
id int auto_increment primary key comment '主键ID',
name varchar( 10 ) comment '姓名',
age int comment '年龄',
gender char( 1 ) comment '1: 男 , 2: 女',
phone char( 11 ) comment '手机号'
) comment '用户基本信息表';
```
```
create table tb_user_edu(
id int auto_increment primary key comment '主键ID',
degree varchar( 20 ) comment '学历',
major varchar( 50 ) comment '专业',
primaryschool varchar( 50 ) comment '小学',
middleschool varchar( 50 ) comment '中学',
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14


## 5.2 多表查询概述

### 5.2.1 数据准备

#### 1). 删除之前 emp, dept表的测试数据

#### 2). 执行如下脚本，创建emp表与dept表并插入测试数据

```
university varchar( 50 ) comment '大学',
userid int unique comment '用户ID',
constraint fk_userid foreign key (userid) references tb_user(id)
) comment '用户教育信息表';
```
```
insert into tb_user(id, name, age, gender, phone) values
(null,'黄渤', 45 ,'1','18800001111'),
(null,'冰冰', 35 ,'2','18800002222'),
(null,'码云', 55 ,'1','18800008888'),
(null,'李彦宏', 50 ,'1','18800009999');
```
```
insert into tb_user_edu(id, degree, major, primaryschool, middleschool,
university, userid) values
(null,'本科','舞蹈','静安区第一小学','静安区第一中学','北京舞蹈学院', 1 ),
(null,'硕士','表演','朝阳区第一小学','朝阳区第一中学','北京电影学院', 2 ),
(null,'本科','英语','杭州市第一小学','杭州市第一中学','杭州师范大学', 3 ),
(null,'本科','应用数学','阳泉第一小学','阳泉区第一中学','清华大学', 4 );
```
###### 15

###### 16

###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23

###### 24

###### 25

###### 26

###### 27

###### 28

###### 29

###### 30

###### 31

```
-- 创建dept表，并插入数据
create table dept(
id int auto_increment comment 'ID' primary key,
name varchar( 50 ) not null comment '部门名称'
)comment '部门表';
INSERT INTO dept (id, name) VALUES ( 1 , '研发部'), ( 2 , '市场部'),( 3 , '财务部'), ( 4 ,
'销售部'), ( 5 , '总经办'), ( 6 , '人事部');
```
```
-- 创建emp表，并插入数据
create table emp(
id int auto_increment comment 'ID' primary key,
```
###### 1 2 3 4 5 6 7 8 9

###### 10


#### dept表共 6 条记录，emp表共 17 条记录。

### 5.2.2 概述

#### 多表查询就是指从多张表中查询数据。

```
name varchar( 50 ) not null comment '姓名',
age int comment '年龄',
job varchar( 20 ) comment '职位',
salary int comment '薪资',
entrydate date comment '入职时间',
managerid int comment '直属领导ID',
dept_id int comment '部门ID'
)comment '员工表';
-- 添加外键
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references
dept(id);
```
```
INSERT INTO emp (id, name, age, job,salary, entrydate, managerid, dept_id)
VALUES
( 1 , '金庸', 66 , '总裁', 20000 , '2000-01-01', null, 5 ),
( 2 , '张无忌', 20 , '项目经理', 12500 , '2005-12-05', 1 , 1 ),
( 3 , '杨逍', 33 , '开发', 8400 ,'2000-11-03', 2 , 1 ),
( 4 , '韦一笑', 48 , '开发', 11000 , '2002-02-05', 2 , 1 ),
( 5 , '常遇春', 43 , '开发', 10500 , '2004-09-07', 3 , 1 ),
( 6 , '小昭', 19 , '程序员鼓励师', 6600 , '2004-10-12', 2 , 1 ),
( 7 , '灭绝', 60 , '财务总监', 8500 , '2002-09-12', 1 , 3 ),
( 8 , '周芷若', 19 , '会计', 48000 , '2006-06-02', 7 , 3 ),
( 9 , '丁敏君', 23 , '出纳', 5250 , '2009-05-13', 7 , 3 ),
( 10 , '赵敏', 20 , '市场部总监', 12500 , '2004-10-12', 1 , 2 ),
( 11 , '鹿杖客', 56 , '职员', 3750 , '2006-10-03', 10 , 2 ),
( 12 , '鹤笔翁', 19 , '职员', 3750 , '2007-05-09', 10 , 2 ),
( 13 , '方东白', 19 , '职员', 5500 , '2009-02-12', 10 , 2 ),
( 14 , '张三丰', 88 , '销售总监', 14000 , '2004-10-12', 1 , 4 ),
( 15 , '俞莲舟', 38 , '销售', 4600 , '2004-10-12', 14 , 4 ),
( 16 , '宋远桥', 40 , '销售', 4600 , '2004-10-12', 14 , 4 ),
( 17 , '陈友谅', 42 , null, 2000 , '2011-10-12', 1 ,null);
```
###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23

###### 24

###### 25

###### 26

###### 27

###### 28

###### 29

###### 30

###### 31

###### 32

###### 33

###### 34

###### 35

###### 36

###### 37

###### 38

###### 39


#### 原来查询单表数据，执行的SQL形式为：select * from emp;

#### 那么我们要执行多表查询，就只需要使用逗号分隔多张表即可，如： select * from emp , dept

#### ; 具体的执行结果如下:

#### 此时,我们看到查询结果中包含了大量的结果集，总共 102 条记录，而这其实就是员工表emp所有的记录

#### (17) 与 部门表dept所有记录(6) 的所有组合情况，这种现象称之为笛卡尔积。接下来，就来简单

#### 介绍下笛卡尔积。

#### 笛卡尔积: 笛卡尔乘积是指在数学中，两个集合A集合 和 B集合的所有组合情况。

#### 而在多表查询中，我们是需要消除无效的笛卡尔积的，只保留两张表关联部分的数据。

#### 在SQL语句中，如何来去除无效的笛卡尔积呢？ 我们可以给多表查询加上连接查询的条件即可。


#### select * from emp , dept where emp.dept_id = dept.id;

#### 而由于id为 17 的员工，没有dept_id字段值，所以在多表查询时，根据连接查询的条件并没有查询

#### 到。

### 5.2.3 分类

#### 连接查询

#### 内连接：相当于查询A、B交集部分数据

#### 外连接：

#### 左外连接：查询左表所有数据，以及两张表交集部分数据

#### 右外连接：查询右表所有数据，以及两张表交集部分数据

#### 自连接：当前表与自身的连接查询，自连接必须使用表别名

#### 子查询

## 5.3 内连接

#### 内连接查询的是两张表交集部分的数

#### 据。(也就是绿色部分的数据)

#### 内连接的语法分为两种: 隐式内连接、显式内连接。先来学习一下具体的语法结构。


#### 1). 隐式内连接

#### 2). 显式内连接

#### 案例:

#### A. 查询每一个员工的姓名 , 及关联的部门的名称 (隐式内连接实现)

#### 表结构: emp , dept

#### 连接条件: emp.dept_id = dept.id

#### B. 查询每一个员工的姓名 , 及关联的部门的名称 (显式内连接实现) --- INNER JOIN ...

#### ON ...

#### 表结构: emp , dept

#### 连接条件: emp.dept_id = dept.id

#### 表的别名:

#### ①. tablea as 别名1 , tableb as 别名2 ;

#### ②. tablea 别名1 , tableb 别名2 ;

#### 注意事项:

###### 1 SELECT 字段列表 FROM 表1 , 表 2 WHERE 条件 ... ;

###### 1 SELECT 字段列表 FROM 表1 [ INNER ] JOIN 表 2 ON 连接条件 ... ;

```
select emp.name , dept.name from emp , dept where emp.dept_id = dept.id ;
```
```
-- 为每一张表起别名,简化SQL编写
select e.name,d.name from emp e , dept d where e.dept_id = d.id;
```
###### 1

###### 2

###### 3

###### 4

```
select e.name, d.name from emp e inner join dept d on e.dept_id = d.id;
```
```
-- 为每一张表起别名,简化SQL编写
select e.name, d.name from emp e join dept d on e.dept_id = d.id;
```
###### 1

###### 2

###### 3

###### 4


#### 一旦为表起了别名，就不能再使用表名来指定对应的字段了，此时只能够使用别名来指定字

#### 段。

## 5.4 外连接

#### 外连接分为两种，分别是：左外连接 和 右外连接。具体的语法结构为：

#### 1). 左外连接

#### 左外连接相当于查询表1(左表)的所有数据，当然也包含表 1 和表 2 交集部分的数据。

#### 2). 右外连接

#### 右外连接相当于查询表2(右表)的所有数据，当然也包含表 1 和表 2 交集部分的数据。

#### 案例:

#### A. 查询emp表的所有数据, 和对应的部门信息

#### 由于需求中提到，要查询emp的所有数据，所以是不能内连接查询的，需要考虑使用外连接查询。

#### 表结构: emp, dept

#### 连接条件: emp.dept_id = dept.id

#### B. 查询dept表的所有数据, 和对应的员工信息(右外连接)

###### 1 SELECT 字段列表 FROM 表1 LEFT [ OUTER ] JOIN 表 2 ON 条件 ... ;

###### 1 SELECT 字段列表 FROM 表1 RIGHT [ OUTER ] JOIN 表 2 ON 条件 ... ;

```
select e.*, d.name from emp e left outer join dept d on e.dept_id = d.id;
```
```
select e.*, d.name from emp e left join dept d on e.dept_id = d.id;
```
###### 1

###### 2

###### 3


#### 由于需求中提到，要查询dept表的所有数据，所以是不能内连接查询的，需要考虑使用外连接查

#### 询。

#### 表结构: emp, dept

#### 连接条件: emp.dept_id = dept.id

#### 注意事项：

#### 左外连接和右外连接是可以相互替换的，只需要调整在连接查询时SQL中，表结构的先后顺

#### 序就可以了。而我们在日常开发使用时，更偏向于左外连接。

## 5.5 自连接

### 5.5.1 自连接查询

#### 自连接查询，顾名思义，就是自己连接自己，也就是把一张表连接查询多次。我们先来学习一下自连接

#### 的查询语法：

#### 而对于自连接查询，可以是内连接查询，也可以是外连接查询。

#### 案例：

#### A. 查询员工 及其 所属领导的名字

#### 表结构: emp

#### B. 查询所有员工 emp 及其领导的名字 emp , 如果员工没有领导, 也需要查询出来

#### 表结构: emp a , emp b

```
select d.*, e.* from emp e right outer join dept d on e.dept_id = d.id;
```
```
select d.*, e.* from dept d left outer join emp e on e.dept_id = d.id;
```
###### 1

###### 2

###### 3

###### 1 SELECT 字段列表 FROM 表A 别名A JOIN 表A 别名B ON 条件 ... ;

```
1 select a.name , b.name from emp a , emp b where a.managerid = b.id;
```

#### 注意事项:

#### 在自连接查询中，必须要为表起别名，要不然我们不清楚所指定的条件、返回的字段，到底

#### 是哪一张表的字段。

### 5.5.2 联合查询

#### 对于union查询，就是把多次查询的结果合并起来，形成一个新的查询结果集。

#### 对于联合查询的多张表的列数必须保持一致，字段类型也需要保持一致。

#### union all 会将全部的数据直接合并在一起，union 会对合并之后的数据去重。

#### 案例:

#### A. 将薪资低于 5000 的员工 , 和 年龄大于 50 岁的员工全部查询出来.

#### 当前对于这个需求，我们可以直接使用多条件查询，使用逻辑运算符 or 连接即可。 那这里呢，我们

#### 也可以通过union/union all来联合查询.

```
select a.name '员工', b.name '领导' from emp a left join emp b on a.managerid =
b.id;
```
###### 1

###### SELECT 字段列表 FROM 表A ...

###### UNION [ ALL ]

###### SELECT 字段列表 FROM 表B ....;

###### 1

###### 2

###### 3

```
select * from emp where salary < 5000
union all
select * from emp where age > 50 ;
```
###### 1

###### 2

###### 3


#### union all查询出来的结果，仅仅进行简单的合并，并未去重。

#### union 联合查询，会对查询出来的结果进行去重处理。

#### 注意：

#### 如果多条查询语句查询出来的结果，字段数量不一致，在进行union/union all联合查询时，将会报

#### 错。如：

## 5.6 子查询

### 5.6.1 概述

#### 1). 概念

#### SQL语句中嵌套SELECT语句，称为嵌套查询，又称子查询。

#### 子查询外部的语句可以是INSERT / UPDATE / DELETE / SELECT 的任何一个。

#### 2). 分类

```
select * from emp where salary < 5000
union
select * from emp where age > 50 ;
```
###### 1

###### 2

###### 3

```
1 SELECT * FROM t1 WHERE column1 = ( SELECT column1 FROM t2 );
```

#### 根据子查询结果不同，分为：

#### A. 标量子查询（子查询结果为单个值）

#### B. 列子查询(子查询结果为一列)

#### C. 行子查询(子查询结果为一行)

#### D. 表子查询(子查询结果为多行多列)

#### 根据子查询位置，分为：

#### A. WHERE之后

#### B. FROM之后

#### C. SELECT之后

### 5.6.2 标量子查询

#### 子查询返回的结果是单个值（数字、字符串、日期等），最简单的形式，这种子查询称为标量子查询。

#### 常用的操作符：= <> > >= < <=

#### 案例:

#### A. 查询 "销售部" 的所有员工信息

#### 完成这个需求时，我们可以将需求分解为两步：

#### ①. 查询 "销售部" 部门ID

#### ②. 根据 "销售部" 部门ID, 查询员工信息

#### B. 查询在 "方东白" 入职之后的员工信息

#### 完成这个需求时，我们可以将需求分解为两步：

#### ①. 查询 方东白 的入职日期

```
1 select id from dept where name = '销售部';
```
```
1 select * from emp where dept_id = (select id from dept where name = '销售部');
```

#### 操作符 描述

#### IN 在指定的集合范围之内，多选一

#### NOT IN 不在指定的集合范围之内

#### ANY 子查询返回列表中，有任意一个满足即可

#### SOME 与ANY等同，使用SOME的地方都可以使用ANY

#### ALL 子查询返回列表的所有值都必须满足

#### ②. 查询指定入职日期之后入职的员工信息

### 5.6.3 列子查询

#### 子查询返回的结果是一列（可以是多行），这种子查询称为列子查询。

#### 常用的操作符：IN 、NOT IN 、 ANY 、SOME 、 ALL

#### 案例:

#### A. 查询 "销售部" 和 "市场部" 的所有员工信息

#### 分解为以下两步:

#### ①. 查询 "销售部" 和 "市场部" 的部门ID

#### ②. 根据部门ID, 查询员工信息

#### B. 查询比 财务部 所有人工资都高的员工信息

```
1 select entrydate from emp where name = '方东白';
```
```
select * from emp where entrydate > (select entrydate from emp where name = '方东
白');
```
###### 1

```
1 select id from dept where name = '销售部' or name = '市场部';
```
```
select * from emp where dept_id in (select id from dept where name = '销售部' or
name = '市场部');
```
###### 1


#### 分解为以下两步:

#### ①. 查询所有 财务部 人员工资

#### ②. 比 财务部 所有人工资都高的员工信息

#### C. 查询比研发部其中任意一人工资高的员工信息

#### 分解为以下两步:

#### ①. 查询研发部所有人工资

#### ②. 比研发部其中任意一人工资高的员工信息

### 5.6.4 行子查询

#### 子查询返回的结果是一行（可以是多列），这种子查询称为行子查询。

#### 常用的操作符：= 、<> 、IN 、NOT IN

#### 案例:

#### A. 查询与 "张无忌" 的薪资及直属领导相同的员工信息 ;

#### 这个需求同样可以拆解为两步进行:

#### ①. 查询 "张无忌" 的薪资及直属领导

```
select id from dept where name = '财务部';
```
```
select salary from emp where dept_id = (select id from dept where name = '财务部');
```
###### 1

###### 2

###### 3

```
select * from emp where salary > all ( select salary from emp where dept_id =
(select id from dept where name = '财务部') );
```
###### 1

```
1 select salary from emp where dept_id = (select id from dept where name = '研发部');
```
```
select * from emp where salary > any ( select salary from emp where dept_id =
(select id from dept where name = '研发部') );
```
###### 1

```
1 select salary, managerid from emp where name = '张无忌';
```

#### ②. 查询与 "张无忌" 的薪资及直属领导相同的员工信息 ;

### 5.6.5 表子查询

#### 子查询返回的结果是多行多列，这种子查询称为表子查询。

#### 常用的操作符：IN

#### 案例:

#### A. 查询与 "鹿杖客" , "宋远桥" 的职位和薪资相同的员工信息

#### 分解为两步执行:

#### ①. 查询 "鹿杖客" , "宋远桥" 的职位和薪资

#### ②. 查询与 "鹿杖客" , "宋远桥" 的职位和薪资相同的员工信息

#### B. 查询入职日期是 "2006-01-01" 之后的员工信息 , 及其部门信息

#### 分解为两步执行:

#### ①. 入职日期是 "2006-01-01" 之后的员工信息

#### ②. 查询这部分员工, 对应的部门信息;

```
select * from emp where (salary,managerid) = (select salary, managerid from emp
where name = '张无忌');
```
###### 1

```
1 select job, salary from emp where name = '鹿杖客' or name = '宋远桥';
```
```
select * from emp where (job,salary) in ( select job, salary from emp where name =
'鹿杖客' or name = '宋远桥' );
```
###### 1

```
1 select * from emp where entrydate > '2006-01-01';
```
```
select e.*, d.* from (select * from emp where entrydate > '2006-01-01') e left
join dept d on e.dept_id = d.id ;
```
###### 1


## 5.7 多表查询案例

#### 数据环境准备:

#### 在这个案例中，我们主要运用上面所讲解的多表查询的语法，完成以下的 12 个需求即可，而这里主要涉

#### 及到的表就三张：emp员工表、dept部门表、salgrade薪资等级表 。

#### 1). 查询员工的姓名、年龄、职位、部门信息 （隐式内连接）

#### 表: emp , dept

#### 连接条件: emp.dept_id = dept.id

#### 2). 查询年龄小于 30 岁的员工的姓名、年龄、职位、部门信息（显式内连接）

#### 表: emp , dept

#### 连接条件: emp.dept_id = dept.id

```
create table salgrade(
grade int,
losal int,
hisal int
) comment '薪资等级表';
```
```
insert into salgrade values ( 1 , 0 , 3000 );
insert into salgrade values ( 2 , 3001 , 5000 );
insert into salgrade values ( 3 , 5001 , 8000 );
insert into salgrade values ( 4 , 8001 , 10000 );
insert into salgrade values ( 5 , 10001 , 15000 );
insert into salgrade values ( 6 , 15001 , 20000 );
insert into salgrade values ( 7 , 20001 , 25000 );
insert into salgrade values ( 8 , 25001 , 30000 );
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

```
1 select e.name , e.age , e.job , d.name from emp e , dept d where e.dept_id = d.id;
```
```
select e.name , e.age , e.job , d.name from emp e inner join dept d on e.dept_id =
d.id where e.age < 30 ;
```
###### 1


#### 3). 查询拥有员工的部门ID、部门名称

#### 表: emp , dept

#### 连接条件: emp.dept_id = dept.id

#### 4). 查询所有年龄大于 40 岁的员工, 及其归属的部门名称; 如果员工没有分配部门, 也需要展示出

#### 来(外连接)

#### 表: emp , dept

#### 连接条件: emp.dept_id = dept.id

#### 5). 查询所有员工的工资等级

#### 表: emp , salgrade

#### 连接条件 : emp.salary >= salgrade.losal and emp.salary <= salgrade.hisal

#### 6). 查询 "研发部" 所有员工的信息及 工资等级

#### 表: emp , salgrade , dept

#### 连接条件 : emp.salary between salgrade.losal and salgrade.hisal ,

#### emp.dept_id = dept.id

#### 查询条件 : dept.name = '研发部'

```
1 select distinct d.id , d.name from emp e , dept d where e.dept_id = d.id;
```
```
select e.*, d.name from emp e left join dept d on e.dept_id = d.id where e.age >
40 ;
```
###### 1

###### -- 方式一

```
select e.* , s.grade , s.losal, s.hisal from emp e , salgrade s where e.salary >=
s.losal and e.salary <= s.hisal;
-- 方式二
select e.* , s.grade , s.losal, s.hisal from emp e , salgrade s where e.salary
between s.losal and s.hisal;
```
###### 1

###### 2

###### 3

###### 4

```
select e.* , s.grade from emp e , dept d , salgrade s where e.dept_id = d.id and (
e.salary between s.losal and s.hisal ) and d.name = '研发部';
```
###### 1


#### 7). 查询 "研发部" 员工的平均工资

#### 表: emp , dept

#### 连接条件 : emp.dept_id = dept.id

#### 8). 查询工资比 "灭绝" 高的员工信息。

#### ①. 查询 "灭绝" 的薪资

#### ②. 查询比她工资高的员工数据

#### 9). 查询比平均薪资高的员工信息

#### ①. 查询员工的平均薪资

#### ②. 查询比平均薪资高的员工信息

#### 10). 查询低于本部门平均工资的员工信息

#### ①. 查询指定部门平均薪资

#### ②. 查询低于本部门平均工资的员工信息

```
select avg(e.salary) from emp e, dept d where e.dept_id = d.id and d.name = '研发
部';
```
###### 1

```
1 select salary from emp where name = '灭绝';
```
```
1 select * from emp where salary > ( select salary from emp where name = '灭绝' );
```
```
1 select avg(salary) from emp;
```
```
1 select * from emp where salary > ( select avg(salary) from emp );
```
```
select avg(e1.salary) from emp e1 where e1.dept_id = 1 ;
select avg(e1.salary) from emp e1 where e1.dept_id = 2 ;
```
###### 1

###### 2

```
select * from emp e2 where e2.salary < ( select avg(e1.salary) from emp e1 where
e1.dept_id = e2.dept_id );
```
###### 1


#### 11). 查询所有的部门信息, 并统计部门的员工人数

#### 12). 查询所有学生的选课情况, 展示出学生名称, 学号, 课程名称

#### 表: student , course , student_course

#### 连接条件: student.id = student_course.studentid , course.id =

#### student_course.courseid

#### 备注: 以上需求的实现方式可能会很多, SQL写法也有很多，只要能满足我们的需求，查询出符合条

#### 件的记录即可。

# 6. 事务

## 6.1 事务简介

#### 事务 是一组操作的集合，它是一个不可分割的工作单位，事务会把所有的操作作为一个整体一起向系

#### 统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败。

#### 就比如: 张三给李四转账 1000 块钱，张三银行账户的钱减少 1000 ，而李四银行账户的钱要增加

#### 1000 。 这一组操作就必须在一个事务的范围内，要么都成功，要么都失败。

#### 正常情况: 转账这个操作, 需要分为以下这么三步来完成 , 三步完成之后, 张三减少1000, 而李四

#### 增加1000, 转账成功 :

```
select d.id, d.name , ( select count(*) from emp e where e.dept_id = d.id ) '人数'
from dept d;
```
###### 1

```
select s.name , s.no , c.name from student s , student_course sc , course c where
s.id = sc.studentid and sc.courseid = c.id ;
```
###### 1


#### 异常情况: 转账这个操作, 也是分为以下这么三步来完成 , 在执行第三步是报错了, 这样就导致张

#### 三减少 1000 块钱, 而李四的金额没变, 这样就造成了数据的不一致, 就出现问题了。

#### 为了解决上述的问题，就需要通过数据的事务来完成，我们只需要在业务逻辑执行之前开启事务，执行

#### 完毕后提交事务。如果执行过程中报错，则回滚事务，把数据恢复到事务开始之前的状态。

#### 注意： 默认MySQL的事务是自动提交的，也就是说，当执行完一条DML语句时，MySQL会立即隐

#### 式的提交事务。

## 6.2 事务操作

#### 数据准备：


### 6.2.1 未控制事务

#### 1). 测试正常情况

#### 测试完毕之后检查数据的状态, 可以看到数据操作前后是一致的。

#### 2). 测试异常情况

```
drop table if exists account;
```
```
create table account(
id int primary key AUTO_INCREMENT comment 'ID',
name varchar( 10 ) comment '姓名',
money double( 10 , 2 ) comment '余额'
) comment '账户表';
```
```
insert into account(name, money) VALUES ('张三', 2000 ), ('李四', 2000 );
```
###### 1 2 3 4 5 6 7 8 9

###### -- 1. 查询张三余额

```
select * from account where name = '张三';
-- 2. 张三的余额减少 1000
update account set money = money - 1000 where name = '张三';
-- 3. 李四的余额增加 1000
update account set money = money + 1000 where name = '李四';
```
###### 1 2 3 4 5 6

###### -- 1. 查询张三余额

```
select * from account where name = '张三';
-- 2. 张三的余额减少 1000
update account set money = money - 1000 where name = '张三';
出错了....
-- 3. 李四的余额增加 1000
update account set money = money + 1000 where name = '李四';
```
###### 1 2 3 4 5 6 7


#### 我们把数据都恢复到 2000 ， 然后再次一次性执行上述的SQL语句(出错了.... 这句话不符合SQL语

#### 法,执行就会报错)，检查最终的数据情况, 发现数据在操作前后不一致了。

### 6.2.2 控制事务一

#### 1). 查看/设置事务提交方式

#### 2). 提交事务

#### 3). 回滚事务

#### 注意：上述的这种方式，我们是修改了事务的自动提交行为, 把默认的自动提交修改为了手动提

#### 交, 此时我们执行的DML语句都不会提交, 需要手动的执行commit进行提交。

### 6.2.3 控制事务二

#### 1). 开启事务

#### 2). 提交事务

#### 3). 回滚事务

```
SELECT @@autocommit ;
SET @@autocommit = 0 ;
```
###### 1

###### 2

###### 1 COMMIT;

###### 1 ROLLBACK;

###### 1 START TRANSACTION 或 BEGIN ;

###### 1 COMMIT;


#### 转账案例：

## 6.3 事务四大特性

#### 原子性（Atomicity）：事务是不可分割的最小操作单元，要么全部成功，要么全部失败。

#### 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。

#### 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立

#### 环境下运行。

#### 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。

#### 上述就是事务的四大特性，简称ACID。

###### 1 ROLLBACK;

###### -- 开启事务

```
start transaction
```
```
-- 1. 查询张三余额
select * from account where name = '张三';
```
```
-- 2. 张三的余额减少 1000
update account set money = money - 1000 where name = '张三';
```
```
-- 3. 李四的余额增加 1000
update account set money = money + 1000 where name = '李四';
```
```
-- 如果正常执行完毕, 则提交事务
commit;
-- 如果执行过程中报错, 则回滚事务
-- rollback;
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16


## 6.4 并发事务问题

#### 1). 赃读：一个事务读到另外一个事务还没有提交的数据。

#### 比如B读取到了A未提交的数据。

#### 2). 不可重复读：一个事务先后读取同一条记录，但两次读取的数据不同，称之为不可重复读。

#### 事务A两次读取同一条记录，但是读取到的数据却是不一样的。


#### 隔离级别 脏读 不可重复读 幻读

#### Read uncommitted √ √ √

#### Read committed × √ √

#### Repeatable Read(默认) × × √

#### Serializable × × ×

#### 3). 幻读：一个事务按照条件查询数据时，没有对应的数据行，但是在插入数据时，又发现这行数据

#### 已经存在，好像出现了 "幻影"。

## 6.5 事务隔离级别

#### 为了解决并发事务所引发的问题，在数据库中引入了事务隔离级别。主要有以下几种：

#### 1). 查看事务隔离级别

#### 2). 设置事务隔离级别

#### 注意：事务隔离级别越高，数据越安全，但是性能越低。

###### 1 SELECT @@TRANSACTION_ISOLATION;

###### SET [ SESSION | GLOBAL ] TRANSACTION ISOLATION LEVEL { READ UNCOMMITTED |

###### READ COMMITTED | REPEATABLE READ | SERIALIZABLE }

###### 



# 1. 存储引擎

## 1.1 MySQL体系结构

#### 1). 连接层

最上层是一些客户端和链接服务，包含本地sock 通信和大多数基于客户端/服务端工具实现的类似于
TCP/IP的通信。主要完成一些类似于连接处理、授权认证、及相关的安全方案。在该层上引入了线程
池的概念，为通过认证安全接入的客户端提供线程。同样在该层上可以实现基于SSL的安全链接。服务
器也会为安全接入的每个客户端验证它所具有的操作权限。

2). 服务层

第二层架构主要完成大多数的核心服务功能，如SQL接口，并完成缓存的查询，SQL的分析和优化，部
分内置函数的执行。所有跨存储引擎的功能也在这一层实现，如 过程、函数等。在该层，服务器会解
析查询并创建相应的内部解析树，并对其完成相应的优化如确定表的查询的顺序，是否利用索引等，
最后生成相应的执行操作。如果是select语句，服务器还会查询内部的缓存，如果缓存空间足够大，
这样在解决大量读操作的环境中能够很好的提升系统的性能。

3). 引擎层

存储引擎层， 存储引擎真正的负责了MySQL中数据的存储和提取，服务器通过API和存储引擎进行通
信。不同的存储引擎具有不同的功能，这样我们可以根据自己的需要，来选取合适的存储引擎。数据库
中的索引是在存储引擎层实现的。

4). 存储层


数据存储层， 主要是将数据(如: redolog、undolog、数据、索引、二进制日志、错误日志、查询
日志、慢查询日志等)存储在文件系统之上，并完成与存储引擎的交互。

和其他数据库相比，MySQL有点与众不同，它的架构可以在多种不同场景中应用并发挥良好作用。主要
体现在存储引擎上，插件式的存储引擎架构，将查询处理和其他的系统任务以及数据的存储提取分离。
这种架构可以根据业务的需求和实际需要选择合适的存储引擎。

## 1.2 存储引擎介绍

#### 大家可能没有听说过存储引擎，但是一定听过引擎这个词，引擎就是发动机，是一个机器的核心组件。

#### 比如，对于舰载机、直升机、火箭来说，他们都有各自的引擎，是他们最为核心的组件。而我们在选择

#### 引擎的时候，需要在合适的场景，选择合适的存储引擎，就像在直升机上，我们不能选择舰载机的引擎

#### 一样。

而对于存储引擎，也是一样，他是mysql数据库的核心，我们也需要在合适的场景选择合适的存储引
擎。接下来就来介绍一下存储引擎。

存储引擎就是存储数据、建立索引、更新/查询数据等技术的实现方式 。存储引擎是基于表的，而不是
基于库的，所以存储引擎也可被称为表类型。我们可以在创建表的时候，来指定选择的存储引擎，如果
没有指定将自动选择默认的存储引擎。

1). 建表时指定存储引擎

#### 2). 查询当前数据库支持的存储引擎

```
CREATE TABLE 表名(
字段 1 字段 1 类型 [ COMMENT 字段 1 注释 ] ,
......
字段n 字段n类型 [COMMENT 字段n注释 ]
) ENGINE = INNODB [ COMMENT 表注释 ] ;
```
```
1
2
3
4
5
```
```
1 show engines;
```

#### 示例演示:

A. 查询建表语句 --- 默认存储引擎: InnoDB

#### 我们可以看到，创建表时，即使我们没有指定存储疫情，数据库也会自动选择默认的存储引擎。

#### B. 查询当前数据库支持的存储引擎

C. 创建表 my_myisam , 并指定MyISAM存储引擎

D. 创建表 my_memory , 指定Memory存储引擎

## 1.3 存储引擎特点

```
1 show create table account;
```
```
1 show engines ;
```
```
create table my_myisam(
id int,
name varchar( 10 )
) engine = MyISAM ;
```
```
1
2
3
4
```
```
create table my_memory(
id int,
name varchar( 10 )
) engine = Memory ;
```
```
1
2
3
4
```

#### 上面我们介绍了什么是存储引擎，以及如何在建表时如何指定存储引擎，接下来我们就来介绍下来上面

重点提到的三种存储引擎 InnoDB、MyISAM、Memory的特点。

### 1.3.1 InnoDB

#### 1). 介绍

InnoDB是一种兼顾高可靠性和高性能的通用存储引擎，在 MySQL 5.5 之后，InnoDB是默认的
MySQL 存储引擎。

#### 2). 特点

#### DML操作遵循ACID模型，支持事务；

#### 行级锁，提高并发访问性能；

#### 支持外键FOREIGN KEY约束，保证数据的完整性和正确性；

#### 3). 文件

xxx.ibd：xxx代表的是表名，innoDB引擎的每张表都会对应这样一个表空间文件，存储该表的表结
构（frm-早期的 、sdi-新版的）、数据和索引。

参数：innodb_file_per_table

如果该参数开启，代表对于InnoDB引擎的表，每一张表都对应一个ibd文件。 我们直接打开MySQL的
数据存放目录： C:\ProgramData\MySQL\MySQL Server 8.0\Data ， 这个目录下有很多文件
夹，不同的文件夹代表不同的数据库，我们直接打开itcast文件夹。

可以看到里面有很多的ibd文件，每一个ibd文件就对应一张表，比如：我们有一张表 account，就
有这样的一个account.ibd文件，而在这个ibd文件中不仅存放表结构、数据，还会存放该表对应的
索引信息。 而该文件是基于二进制存储的，不能直接基于记事本打开，我们可以使用mysql提供的一
个指令 ibd2sdi ，通过该指令就可以从ibd文件中提取sdi信息，而sdi数据字典信息中就包含该表

```
1 show variables like 'innodb_file_per_table';
```

#### 的表结构。

#### 4). 逻辑存储结构

```
表空间 : InnoDB存储引擎逻辑结构的最高层，ibd文件其实就是表空间文件，在表空间中可以
包含多个Segment段。
段 : 表空间是由各个段组成的， 常见的段有数据段、索引段、回滚段等。InnoDB中对于段的管
理，都是引擎自身完成，不需要人为对其控制，一个段中包含多个区。
区 : 区是表空间的单元结构，每个区的大小为1M。 默认情况下， InnoDB存储引擎页大小为
16K， 即一个区中一共有 64 个连续的页。
页 : 页是组成区的最小单元， 页也是InnoDB 存储引擎磁盘管理的最小单元 ，每个页的大小默
认为 16KB。为了保证页的连续性，InnoDB 存储引擎每次从磁盘申请 4-5 个区。
行 : InnoDB 存储引擎是面向行的，也就是说数据是按行进行存放的，在每一行中除了定义表时
所指定的字段以外，还包含两个隐藏字段(后面会详细介绍)。
```
### 1.3.2 MyISAM

#### 1). 介绍

MyISAM是MySQL早期的默认存储引擎。


#### 2). 特点

#### 不支持事务，不支持外键

#### 支持表锁，不支持行锁

#### 访问速度快

#### 3). 文件

xxx.sdi：存储表结构信息

xxx.MYD: 存储数据

xxx.MYI: 存储索引

### 1.3.3 Memory

#### 1). 介绍

Memory引擎的表数据时存储在内存中的，由于受到硬件问题、或断电问题的影响，只能将这些表作为
临时表或缓存使用。

#### 2). 特点

#### 内存存放

hash索引（默认）

#### 3).文件

xxx.sdi：存储表结构信息

### 1.3.4 区别及特点


```
特点 InnoDB MyISAM Memory
存储限制 64TB 有 有
事务安全 支持 - -
锁机制 行锁 表锁 表锁
B+tree索引 支持 支持 支持
Hash索引 - - 支持
全文索引 支持(5.6版本之后) 支持 -
空间使用 高 低 N/A
内存使用 高 低 中等
批量插入速度 低 高 高
支持外键 支持 - -
```
#### 面试题:

```
InnoDB引擎与MyISAM引擎的区别?
①. InnoDB引擎, 支持事务, 而MyISAM不支持。
②. InnoDB引擎, 支持行锁和表锁, 而MyISAM仅支持表锁, 不支持行锁。
③. InnoDB引擎, 支持外键, 而MyISAM是不支持的。
```
#### 主要是上述三点区别，当然也可以从索引结构、存储限制等方面，更加深入的回答，具体参

#### 考如下官方文档：

```
https://dev.mysql.com/doc/refman/8.0/en/innodb-introduction.html
https://dev.mysql.com/doc/refman/8.0/en/myisam-storage-engine.html
```
## 1.4 存储引擎选择


#### 在选择存储引擎时，应该根据应用系统的特点选择合适的存储引擎。对于复杂的应用系统，还可以根据

#### 实际情况选择多种存储引擎进行组合。

```
InnoDB: 是Mysql的默认存储引擎，支持事务、外键。如果应用对事务的完整性有比较高的要
求，在并发条件下要求数据的一致性，数据操作除了插入和查询之外，还包含很多的更新、删除操
作，那么InnoDB存储引擎是比较合适的选择。
MyISAM ： 如果应用是以读操作和插入操作为主，只有很少的更新和删除操作，并且对事务的完
整性、并发性要求不是很高，那么选择这个存储引擎是非常合适的。
MEMORY：将所有数据保存在内存中，访问速度快，通常用于临时表及缓存。MEMORY的缺陷就是
对表的大小有限制，太大的表无法缓存在内存中，而且无法保障数据的安全性。
```
# 2. 索引

## 2.1 索引概述

## 2.1.1 介绍

索引（index）是帮助MySQL高效获取数据的数据结构(有序)。在数据之外，数据库系统还维护着满足
特定查找算法的数据结构，这些数据结构以某种方式引用（指向）数据， 这样就可以在这些数据结构
上实现高级查找算法，这种数据结构就是索引。

#### 一提到数据结构，大家都会有所担心，担心自己不能理解，跟不上节奏。不过在这里大家完全不用担

#### 心，我们后面在讲解时，会详细介绍。

## 2.2 演示

#### 表结构及其数据如下：


假如我们要执行的SQL语句为 ： select * from user where age = 45;

1). 无索引情况

#### 在无索引情况下，就需要从第一行开始扫描，一直扫描到最后一行，我们称之为 全表扫描，性能很

#### 低。

#### 2). 有索引情况


#### 优势 劣势

#### 提高数据检索的效率，降低数据库

#### 的IO成本

#### 索引列也是要占用空间的。

#### 通过索引列对数据进行排序，降低

#### 数据排序的成本，降低CPU的消

#### 耗。

#### 索引大大提高了查询效率，同时却也降低更新表的速度，

#### 如对表进行INSERT、UPDATE、DELETE时，效率降低。

如果我们针对于这张表建立了索引，假设索引结构就是二叉树，那么也就意味着，会对age这个字段建
立一个二叉树的索引结构。

#### 此时我们在进行查询时，只需要扫描三次就可以找到数据了，极大的提高的查询的效率。

#### 备注： 这里我们只是假设索引的结构是二叉树，介绍一下索引的大概原理，只是一个示意图，并

#### 不是索引的真实结构，索引的真实结构，后面会详细介绍。

### 2.3 特点

## 2.2 索引结构

### 2.2.1 概述

MySQL的索引是在存储引擎层实现的，不同的存储引擎有不同的索引结构，主要包含以下几种：


#### 索引结构 描述

```
B+Tree索引 最常见的索引类型，大部分引擎都支持 B+ 树索引
```
```
Hash索引 底层数据结构是用哈希表实现的, 只有精确匹配索引列的查询才有效, 不
支持范围查询
R-tree(空间索
引）
```
```
空间索引是MyISAM引擎的一个特殊索引类型，主要用于地理空间数据类
型，通常使用较少
Full-text(全文
索引)
```
#### 是一种通过建立倒排索引,快速匹配文档的方式。类似于

```
Lucene,Solr,ES
```
```
索引 InnoDB MyISAM Memory
B+tree索引 支持 支持 支持
Hash 索引 不支持 不支持 支持
R-tree 索引 不支持 支持 不支持
Full-text 5.6版本之后支持 支持 不支持
```
上述是MySQL中所支持的所有的索引结构，接下来，我们再来看看不同的存储引擎对于索引结构的支持
情况。

#### 注意： 我们平常所说的索引，如果没有特别指明，都是指B+树结构组织的索引。

### 2.2.2 二叉树

假如说MySQL的索引结构采用二叉树的数据结构，比较理想的结构如下：


#### 如果主键是顺序插入的，则会形成一个单向链表，结构如下：

#### 所以，如果选择二叉树作为索引结构，会存在以下缺点：

#### 顺序插入时，会形成一个链表，查询性能大大降低。

#### 大数据量情况下，层级较深，检索速度慢。

#### 此时大家可能会想到，我们可以选择红黑树，红黑树是一颗自平衡二叉树，那这样即使是顺序插入数

#### 据，最终形成的数据结构也是一颗平衡的二叉树,结构如下:


#### 但是，即使如此，由于红黑树也是一颗二叉树，所以也会存在一个缺点：

#### 大数据量情况下，层级较深，检索速度慢。

所以，在MySQL的索引结构中，并没有选择二叉树或者红黑树，而选择的是B+Tree，那么什么是
B+Tree呢？在详解B+Tree之前，先来介绍一个B-Tree。

### 2.2.3 B-Tree

B-Tree，B树是一种多叉路衡查找树，相对于二叉树，B树每个节点可以有多个分支，即多叉。

以一颗最大度数（max-degree）为5(5阶)的b-tree为例，那这个B树每个节点最多存储 4 个key， 5
个指针：

#### 知识小贴士: 树的度数指的是一个节点的子节点个数。

我们可以通过一个数据结构可视化的网站来简单演示一下。 **https://www.cs.usfca.edu/~gall
es/visualization/BTree.html**


#### 插入一组数据： 100 65 169 368 900 556 780 35 215 1200 234 888 158 90 1000 88

#### 120 268 250 。然后观察一些数据插入过程中，节点的变化情况。

#### 特点：

```
5 阶的B树，每一个节点最多存储 4 个key，对应 5 个指针。
一旦节点存储的key数量到达 5 ，就会裂变，中间元素向上分裂。
在B树中，非叶子节点和叶子节点都会存放数据。
```
### 2.2.4 B+Tree

B+Tree是B-Tree的变种，我们以一颗最大度数（max-degree）为 4 （ 4 阶）的b+tree为例，来看一
下其结构示意图：

#### 我们可以看到，两部分：

#### 绿色框框起来的部分，是索引部分，仅仅起到索引数据的作用，不存储数据。

#### 红色框框起来的部分，是数据存储部分，在其叶子节点中要存储具体的数据。

我们可以通过一个数据结构可视化的网站来简单演示一下。 **https://www.cs.usfca.edu/~gall
es/visualization/BPlusTree.html**


#### 插入一组数据： 100 65 169 368 900 556 780 35 215 1200 234 888 158 90 1000 88

#### 120 268 250 。然后观察一些数据插入过程中，节点的变化情况。

最终我们看到，B+Tree 与 B-Tree相比，主要有以下三点区别：

```
所有的数据都会出现在叶子节点。
叶子节点形成一个单向链表。
非叶子节点仅仅起到索引数据作用，具体的数据都是在叶子节点存放的。
```
上述我们所看到的结构是标准的B+Tree的数据结构，接下来，我们再来看看MySQL中优化之后的
B+Tree。

MySQL索引数据结构对经典的B+Tree进行了优化。在原B+Tree的基础上，增加一个指向相邻叶子节点
的链表指针，就形成了带有顺序指针的B+Tree，提高区间访问的性能，利于排序。

### 2.2.5 Hash

MySQL中除了支持B+Tree索引，还支持一种索引类型---Hash索引。

1). 结构

哈希索引就是采用一定的hash算法，将键值换算成新的hash值，映射到对应的槽位上，然后存储在
hash表中。


如果两个(或多个)键值，映射到一个相同的槽位上，他们就产生了hash冲突（也称为hash碰撞），可
以通过链表来解决。

#### 2). 特点

A. Hash索引只能用于对等比较(=，in)，不支持范围查询（between，>，< ，...）

B. 无法利用索引完成排序操作

C. 查询效率高，通常(不存在hash冲突的情况)只需要一次检索就可以了，效率通常要高于B+tree索
引

#### 3). 存储引擎支持

在MySQL中，支持hash索引的是Memory存储引擎。 而InnoDB中具有自适应hash功能，hash索引是
InnoDB存储引擎根据B+Tree索引在指定条件下自动构建的。

```
思考题： 为什么InnoDB存储引擎选择使用B+tree索引结构?
```

#### 分类 含义 特点 关键字

#### 主键

#### 索引 针对于表中主键创建的索引

#### 默认自动创建, 只能

#### 有一个 PRIMARY

#### 唯一

#### 索引 避免同一个表中某数据列中的值重复 可以有多个 UNIQUE

#### 常规

#### 索引 快速定位特定数据 可以有多个^

#### 全文

#### 索引

#### 全文索引查找的是文本中的关键词，而不是比

#### 较索引中的值

#### 可以有多个 FULLTEXT

#### 分类 含义 特点

```
聚集索引(Clustered
Index)
```
#### 将数据存储与索引放到了一块，索引结构的叶子

#### 节点保存了行数据

#### 必须有,而且只

#### 有一个

```
二级索引(Secondary
Index)
```
#### 将数据与索引分开存储，索引结构的叶子节点关

#### 联的是对应的主键 可以存在多个

#### A. 相对于二叉树，层级更少，搜索效率高；

```
B. 对于B-tree，无论是叶子节点还是非叶子节点，都会保存数据，这样导致一页中存储
的键值减少，指针跟着减少，要同样保存大量数据，只能增加树的高度，导致性能降低；
C. 相对Hash索引，B+tree支持范围匹配及排序操作；
```
## 2.3 索引分类

### 2.3.1 索引分类

在MySQL数据库，将索引的具体类型主要分为以下几类：主键索引、唯一索引、常规索引、全文索引。

### 2.3.2 聚集索引&二级索引

而在在InnoDB存储引擎中，根据索引的存储形式，又可以分为以下两种：

#### 聚集索引选取规则:

#### 如果存在主键，主键索引就是聚集索引。


#### 如果不存在主键，将使用第一个唯一（UNIQUE）索引作为聚集索引。

```
如果表没有主键，或没有合适的唯一索引，则InnoDB会自动生成一个rowid作为隐藏的聚集索
引。
```
#### 聚集索引和二级索引的具体结构如下：

#### 聚集索引的叶子节点下挂的是这一行的数据 。

#### 二级索引的叶子节点下挂的是该字段值对应的主键值。

#### 接下来，我们来分析一下，当我们执行如下的SQL语句时，具体的查找过程是什么样子的。

#### 具体过程如下:

①. 由于是根据name字段进行查询，所以先根据name='Arm'到name字段的二级索引中进行匹配查
找。但是在二级索引中只能查找到 Arm 对应的主键值 10 。


#### ②. 由于查询返回的数据是*，所以此时，还需要根据主键值 10 ，到聚集索引中查找 10 对应的记录，最

终找到 10 对应的行row。

③. 最终拿到这一行的数据，直接返回即可。

#### 回表查询： 这种先到二级索引中查找数据，找到主键值，然后再到聚集索引中根据主键值，获取

#### 数据的方式，就称之为回表查询。

#### 思考题：

#### 以下两条SQL语句，那个执行效率高? 为什么?

```
A. select * from user where id = 10 ;
B. select * from user where name = 'Arm' ;
备注: id为主键，name字段创建的有索引；
```
#### 解答：

#### A 语句的执行性能要高于B 语句。

```
因为A语句直接走聚集索引，直接返回数据。 而B语句需要先查询name字段的二级索引，然
后再查询聚集索引，也就是需要进行回表查询。
```
#### 思考题：

```
InnoDB主键索引的B+tree高度为多高呢?
```

#### 假设:

```
一行数据大小为1k，一页中可以存储 16 行这样的数据。InnoDB的指针占用 6 个字节的空
间，主键即使为bigint，占用字节数为 8 。
高度为 2 ：
n * 8 + (n + 1) * 6 = 16*1024 , 算出n约为 1170
1171* 16 = 18736
也就是说，如果树的高度为 2 ，则可以存储 18000 多条记录。
高度为 3 ：
1171 * 1171 * 16 = 21939856
也就是说，如果树的高度为 3 ，则可以存储 2200w 左右的记录。
```
## 2.4 索引语法

#### 1). 创建索引

#### 2). 查看索引

#### 3). 删除索引

#### 案例演示:

```
CREATE [ UNIQUE | FULLTEXT ] INDEX index_name ON table_name (
index_col_name,... ) ;
```
```
1
```
```
1 SHOW INDEX FROM table_name ;
```
```
1 DROP INDEX index_name ON table_name ;
```

先来创建一张表 tb_user，并且查询测试数据。

```
create table tb_user(
id int primary key auto_increment comment '主键',
name varchar( 50 ) not null comment '用户名',
phone varchar( 11 ) not null comment '手机号',
email varchar( 100 ) comment '邮箱',
profession varchar( 11 ) comment '专业',
age tinyint unsigned comment '年龄',
gender char( 1 ) comment '性别 , 1: 男, 2: 女',
status char( 1 ) comment '状态',
createtime datetime comment '创建时间'
) comment '系统用户表';
```
```
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('吕布', '17799990000', 'lvbu666@163.com', '软件工程', 23 , '1',
'6', '2001-02-02 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('曹操', '17799990001', 'caocao666@qq.com', '通讯工程', 33 ,
'1', '0', '2001-03-05 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('赵云', '17799990002', '17799990@139.com', '英语', 34 , '1',
'2', '2002-03-02 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('孙悟空', '17799990003', '17799990@sina.com', '工程造价', 54 ,
'1', '0', '2001-07-02 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('花木兰', '17799990004', '19980729@sina.com', '软件工程', 23 ,
'2', '1', '2001-04-22 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('大乔', '17799990005', 'daqiao666@sina.com', '舞蹈', 22 , '2',
'0', '2001-02-07 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('露娜', '17799990006', 'luna_love@sina.com', '应用数学', 24 ,
'2', '0', '2001-02-08 00:00:00');
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
```
```
14
```
```
15
```
```
16
```
```
17
```
```
18
```
```
19
```

```
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('程咬金', '17799990007', 'chengyaojin@163.com', '化工', 38 ,
'1', '5', '2001-05-23 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('项羽', '17799990008', 'xiaoyu666@qq.com', '金属材料', 43 ,
'1', '0', '2001-09-18 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('白起', '17799990009', 'baiqi666@sina.com', '机械工程及其自动
化', 27 , '1', '2', '2001-08-16 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('韩信', '17799990010', 'hanxin520@163.com', '无机非金属材料工
程', 27 , '1', '0', '2001-06-12 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('荆轲', '17799990011', 'jingke123@163.com', '会计', 29 , '1',
'0', '2001-05-11 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('兰陵王', '17799990012', 'lanlinwang666@126.com', '工程造价',
44 , '1', '1', '2001-04-09 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('狂铁', '17799990013', 'kuangtie@sina.com', '应用数学', 43 ,
'1', '2', '2001-04-10 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('貂蝉', '17799990014', '84958948374@qq.com', '软件工程', 40 ,
'2', '3', '2001-02-12 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('妲己', '17799990015', '2783238293@qq.com', '软件工程', 31 ,
'2', '0', '2001-01-30 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('芈月', '17799990016', 'xiaomin2001@sina.com', '工业经济', 35 ,
'2', '0', '2000-05-03 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('嬴政', '17799990017', '8839434342@qq.com', '化工', 38 , '1',
'1', '2001-08-08 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('狄仁杰', '17799990018', 'jujiamlm8166@163.com', '国际贸易',
30 , '1', '0', '2007-03-12 00:00:00');
```
20

21

22

23

24

25

26

27

28

29

30

31


#### 表结构中插入的数据如下：

#### 数据准备好了之后，接下来，我们就来完成如下需求：

A. name字段为姓名字段，该字段的值可能会重复，为该字段创建索引。

B. phone手机号字段的值，是非空，且唯一的，为该字段创建唯一索引。

```
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('安琪拉', '17799990019', 'jdodm1h@126.com', '城市规划', 51 ,
'2', '0', '2001-08-15 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('典韦', '17799990020', 'ycaunanjian@163.com', '城市规划', 52 ,
'1', '2', '2000-04-12 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('廉颇', '17799990021', 'lianpo321@126.com', '土木工程', 19 ,
'1', '3', '2002-07-18 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('后羿', '17799990022', 'altycj2000@139.com', '城市园林', 20 ,
'1', '0', '2002-03-10 00:00:00');
INSERT INTO tb_user (name, phone, email, profession, age, gender, status,
createtime) VALUES ('姜子牙', '17799990023', '37483844@qq.com', '工程造价', 29 ,
'1', '4', '2003-05-26 00:00:00');
```
```
32
```
```
33
```
```
34
```
```
35
```
```
36
```
```
1 CREATE INDEX idx_user_name ON tb_user(name);
```

C. 为profession、age、status创建联合索引。

D. 为email建立合适的索引来提升查询效率。

完成上述的需求之后，我们再查看tb_user表的所有的索引数据。

## 2.5 SQL性能分析

### 2.5.1 SQL执行频率

MySQL 客户端连接成功后，通过 show [session|global] status 命令可以提供服务器状态信
息。通过如下指令，可以查看当前数据库的INSERT、UPDATE、DELETE、SELECT的访问频次：

Com_delete: 删除次数

```
1 CREATE UNIQUE INDEX idx_user_phone ON tb_user(phone);
```
```
1 CREATE INDEX idx_user_pro_age_sta ON tb_user(profession,age,status);
```
```
1 CREATE INDEX idx_email ON tb_user(email);
```
```
1 show index from tb_user;
```
```
-- session 是查看当前会话 ;
-- global 是查询全局数据 ;
SHOW GLOBAL STATUS LIKE 'Com_______';
```
```
1
2
3
```

Com_insert: 插入次数

Com_select: 查询次数

Com_update: 更新次数

我们可以在当前数据库再执行几次查询操作，然后再次查看执行频次，看看 Com_select 参数会不会
变化。

#### 通过上述指令，我们可以查看到当前数据库到底是以查询为主，还是以增删改为主，从而为数据

#### 库优化提供参考依据。 如果是以增删改为主，我们可以考虑不对其进行索引的优化。 如果是以

#### 查询为主，那么就要考虑对数据库的索引进行优化了。

#### 那么通过查询SQL的执行频次，我们就能够知道当前数据库到底是增删改为主，还是查询为主。 那假

#### 如说是以查询为主，我们又该如何定位针对于那些查询语句进行优化呢？ 次数我们可以借助于慢查询

#### 日志。

接下来，我们就来介绍一下MySQL中的慢查询日志。

### 2.5.2 慢查询日志

慢查询日志记录了所有执行时间超过指定参数（long_query_time，单位：秒，默认 10 秒）的所有
SQL语句的日志。

MySQL的慢查询日志默认没有开启，我们可以查看一下系统变量 slow_query_log。

如果要开启慢查询日志，需要在MySQL的配置文件（/etc/my.cnf）中配置如下信息：

```
# 开启MySQL慢日志查询开关
slow_query_log= 1
# 设置慢日志的时间为 2 秒，SQL语句执行时间超过 2 秒，就会视为慢查询，记录慢查询日志
long_query_time= 2
```
```
1
2
3
4
```

配置完毕之后，通过以下指令重新启动MySQL服务器进行测试，查看慢日志文件中记录的信息
/var/lib/mysql/localhost-slow.log。

#### 然后，再次查看开关情况，慢查询日志就已经打开了。

#### 测试：

#### A. 执行如下SQL语句 ：

#### B. 检查慢查询日志 ：

最终我们发现，在慢查询日志中，只会记录执行时间超多我们预设时间（2s）的SQL，执行较快的SQL
是不会记录的。

#### 那这样，通过慢查询日志，就可以定位出执行效率比较低的SQL，从而有针对性的进行优化。

### 2.5.3 profile详情

```
1 systemctl restart mysqld
```
```
select * from tb_user; -- 这条SQL执行效率比较高, 执行耗时 0.00sec
select count(*) from tb_sku; -- 由于tb_sku表中, 预先存入了1000w的记录, count一次,耗时
13.35sec
```
```
1
2
```

show profiles 能够在做SQL优化时帮助我们了解时间都耗费到哪里去了。通过have_profiling
参数，能够看到当前MySQL是否支持profile操作：

可以看到，当前MySQL是支持 profile操作的，但是开关是关闭的。可以通过set语句在
session/global级别开启profiling：

开关已经打开了，接下来，我们所执行的SQL语句，都会被MySQL记录，并记录执行时间消耗到哪儿去
了。 我们直接执行如下的SQL语句：

#### 执行一系列的业务SQL的操作，然后通过如下指令查看指令的执行耗时：

#### 查看每一条SQL的耗时情况:

```
1 SELECT @@have_profiling ;
```
```
1 SET profiling = 1;
```
```
select * from tb_user;
select * from tb_user where id = 1 ;
select * from tb_user where name = '白起';
select count(*) from tb_sku;
```
```
1
2
3
4
```
```
-- 查看每一条SQL的耗时基本情况
show profiles;
```
```
-- 查看指定query_id的SQL语句各个阶段的耗时情况
show profile for query query_id;
```
```
-- 查看指定query_id的SQL语句CPU的使用情况
show profile cpu for query query_id;
```
```
1 2 3 4 5 6 7 8
```

#### 查看指定SQL各个阶段的耗时情况 :

### 2.5.4 explain

EXPLAIN 或者 DESC命令获取 MySQL 如何执行 SELECT 语句的信息，包括在 SELECT 语句执行
过程中表如何连接和连接的顺序。

语法:

Explain 执行计划中各个字段的含义:

```
-- 直接在select语句之前加上关键字 explain / desc
EXPLAIN SELECT 字段列表 FROM 表名 WHERE 条件 ;
```
```
1
2
```

#### 字段 含义

```
id select查询的序列号，表示查询中执行select子句或者是操作表的顺序
(id相同，执行顺序从上到下；id不同，值越大，越先执行)。
```
```
select_type
```
#### 表示 SELECT 的类型，常见的取值有 SIMPLE（简单表，即不使用表连接

#### 或者子查询）、PRIMARY（主查询，即外层的查询）、

#### UNION（UNION 中的第二个或者后面的查询语句）、

#### SUBQUERY（SELECT/WHERE之后包含了子查询）等

```
type
```
```
表示连接类型，性能由好到差的连接类型为NULL、system、const、
eq_ref、ref、range、 index、all 。
possible_key 显示可能应用在这张表上的索引，一个或多个。
key 实际使用的索引，如果为NULL，则没有使用索引。
```
```
key_len 表示索引中使用的字节数，^ 该值为索引字段最大可能长度，并非实际使用长
度，在不损失精确性的前提下， 长度越短越好 。
```
```
rows
```
```
MySQL认为必须要执行查询的行数，在innodb引擎的表中，是一个估计值，
可能并不总是准确的。
filtered 表示返回结果的行数占需读取行数的百分比， filtered 的值越大越好。
```
## 2.6 索引使用

### 2.6.1 验证索引效率

#### 在讲解索引的使用原则之前，先通过一个简单的案例，来验证一下索引，看看是否能够通过索引来提升

数据查询性能。在演示的时候，我们还是使用之前准备的一张表 tb_sku , 在这张表中准备了1000w
的记录。

这张表中id为主键，有主键索引，而其他字段是没有建立索引的。 我们先来查询其中的一条记录，看
看里面的字段情况，执行如下SQL：

```
1 select * from tb_sku where id = 1\G;
```

可以看到即使有1000w的数据,根据id进行数据查询,性能依然很快，因为主键id是有索引的。 那么接
下来，我们再来根据 sn 字段进行查询，执行如下SQL：

我们可以看到根据sn字段进行查询，查询返回了一条数据，结果耗时 20.78sec，就是因为sn没有索
引，而造成查询效率很低。

那么我们可以针对于sn字段，建立一个索引，建立了索引之后，我们再次根据sn进行查询，再来看一
下查询耗时情况。

创建索引：

#### 然后再次执行相同的SQL语句，再次查看SQL的耗时。

```
1 SELECT * FROM tb_sku WHERE sn = '100000003145001';
```
```
1 create index idx_sku_sn on tb_sku(sn) ;
```
```
1 SELECT * FROM tb_sku WHERE sn = '100000003145001';
```

我们明显会看到，sn字段建立了索引之后，查询性能大大提升。建立索引前后，查询耗时都不是一个数
量级的。

### 2.6.2 最左前缀法则

#### 如果索引了多列（联合索引），要遵守最左前缀法则。最左前缀法则指的是查询从索引的最左列开始，

#### 并且不跳过索引中的列。如果跳跃某一列，索引将会部分失效(后面的字段索引失效)。

以 tb_user 表为例，我们先来查看一下之前 tb_user 表所创建的索引。

在 tb_user 表中，有一个联合索引，这个联合索引涉及到三个字段，顺序分别为：profession，
age，status。

对于最左前缀法则指的是，查询时，最左变的列，也就是profession必须存在，否则索引全部失效。
而且中间不能跳过某一列，否则该列后面的字段索引将失效。 接下来，我们来演示几组案例，看一下
具体的执行计划：

```
explain select * from tb_user where profession = '软件工程' and age = 31 and status
= '0';
```
```
1
```
```
1 explain select * from tb_user where profession = '软件工程' and age = 31;
```

以上的这三组测试中，我们发现只要联合索引最左边的字段 profession存在，索引就会生效，只不
过索引的长度不同。 而且由以上三组测试，我们也可以推测出profession字段索引长度为 47 、age
字段索引长度为 2 、status字段索引长度为 5 。

#### 而通过上面的这两组测试，我们也可以看到索引并未生效，原因是因为不满足最左前缀法则，联合索引

最左边的列profession不存在。

上述的SQL查询时，存在profession字段，最左边的列是存在的，索引满足最左前缀法则的基本条
件。但是查询时，跳过了age这个列，所以后面的列索引是不会使用的，也就是索引部分生效，所以索
引的长度就是 47 。

#### 思考题：

```
1 explain select * from tb_user where profession = '软件工程';
```
```
1 explain select * from tb_user where age = 31 and status = '0';
```
```
1 explain select * from tb_user where status = '0';
```
```
1 explain select * from tb_user where profession = '软件工程' and status = '0';
```

```
当执行SQL语句: explain select * from tb_user where age = 31 and
status = '0' and profession = '软件工程'； 时，是否满足最左前缀法则，走不走
上述的联合索引，索引长度？
```
#### 可以看到，是完全满足最左前缀法则的，索引长度 54 ，联合索引是生效的。

#### 注意 ： 最左前缀法则中指的最左边的列，是指在查询时，联合索引的最左边的字段(即是

#### 第一个字段)必须存在，与我们编写SQL时，条件编写的先后顺序无关。

### 2.6.3 范围查询

#### 联合索引中，出现范围查询(>,<)，范围查询右侧的列索引失效。

当范围查询使用> 或 < 时，走联合索引了，但是索引的长度为 49 ，就说明范围查询右边的status字
段是没有走索引的。

#### 当范围查询使用>= 或 <= 时，走联合索引了，但是索引的长度为 54 ，就说明所有的字段都是走索引

#### 的。

#### 所以，在业务允许的情况下，尽可能的使用类似于 >= 或 <= 这类的范围查询，而避免使用 > 或 <

#### 。

```
explain select * from tb_user where profession = '软件工程' and age > 30 and status
= '0';
```
```
1
```
```
explain select * from tb_user where profession = '软件工程' and age >= 30 and
status = '0';
```
```
1
```

### 2.6.4 索引失效情况

#### 2.6.4.1 索引列运算

#### 不要在索引列上进行运算操作， 索引将失效。

在tb_user表中，除了前面介绍的联合索引之外，还有一个索引，是phone字段的单列索引。

A. 当根据phone字段进行等值匹配查询时, 索引生效。

B. 当根据phone字段进行函数运算操作之后，索引失效。

#### 2.6.4.2 字符串不加引号

#### 字符串类型字段使用时，不加引号，索引将失效。

#### 接下来，我们通过两组示例，来看看对于字符串类型的字段，加单引号与不加单引号的区别：

```
1 explain select * from tb_user where phone = '17799990015';
```
```
1 explain select * from tb_user where substring(phone, 10 , 2 ) = '15';
```

#### 经过上面两组示例，我们会明显的发现，如果字符串不加单引号，对于查询结果，没什么影响，但是数

#### 据库存在隐式类型转换，索引将失效。

#### 2.6.4.3 模糊查询

#### 如果仅仅是尾部模糊匹配，索引不会失效。如果是头部模糊匹配，索引失效。

#### 接下来，我们来看一下这三条SQL语句的执行效果，查看一下其执行计划：

由于下面查询语句中，都是根据profession字段查询，符合最左前缀法则，联合索引是可以生效的，
我们主要看一下，模糊查询时，%加在关键字之前，和加在关键字之后的影响。

```
explain select * from tb_user where profession = '软件工程' and age = 31 and status
= '0';
explain select * from tb_user where profession = '软件工程' and age = 31 and status
= 0 ;
```
```
1
```
```
2
```
```
explain select * from tb_user where phone = '17799990015';
explain select * from tb_user where phone = 17799990015;
```
```
1
2
```
```
explain select * from tb_user where profession like '软件%';
explain select * from tb_user where profession like '%工程';
explain select * from tb_user where profession like '%工%';
```
```
1
2
3
```

经过上述的测试，我们发现，在like模糊查询中，在关键字后面加%，索引可以生效。而如果在关键字
前面加了%，索引将会失效。

**3.6.4.4 or连接条件**

用or分割开的条件， 如果or前的条件中的列有索引，而后面的列中没有索引，那么涉及的索引都不会
被用到。

由于age没有索引，所以即使id、phone有索引，索引也会失效。所以需要针对于age也要建立索引。

然后，我们可以对age字段建立索引。

#### 建立了索引之后，我们再次执行上述的SQL语句，看看前后执行计划的变化。

```
explain select * from tb_user where id = 10 or age = 23 ;
explain select * from tb_user where phone = '17799990017' or age = 23 ;
```
```
1
2
```
```
1 create index idx_user_age on tb_user(age);
```

最终，我们发现，当or连接的条件，左右两侧字段都有索引时，索引才会生效。

#### 3.6.4.5 数据分布影响

如果MySQL评估使用索引比全表更慢，则不使用索引。

#### 经过测试我们发现，相同的SQL语句，只是传入的字段值不同，最终的执行计划也完全不一样，这是为

#### 什么呢？

就是因为MySQL在查询时，会评估使用索引的效率与走全表扫描的效率，如果走全表扫描更快，则放弃
索引，走全表扫描。 因为索引是用来索引少量数据的，如果通过索引查询返回大批量的数据，则还不
如走全表扫描来的快，此时索引就会失效。

接下来，我们再来看看 is null 与 is not null 操作是否走索引。

执行如下两条语句 ：

接下来，我们做一个操作将profession字段值全部更新为null。

```
select * from tb_user where phone >= '17799990005';
select * from tb_user where phone >= '17799990015';
```
```
1
2
```
```
explain select * from tb_user where profession is null;
explain select * from tb_user where profession is not null;
```
```
1
2
```

#### 然后，再次执行上述的两条SQL，查看SQL语句的执行计划。

#### 最终我们看到，一模一样的SQL语句，先后执行了两次，结果查询计划是不一样的，为什么会出现这种

现象，这是和数据库的数据分布有关系。查询时MySQL会评估，走索引快，还是全表扫描快，如果全表
扫描更快，则放弃索引走全表扫描。 因此，is null 、is not null是否走索引，得具体情况具体
分析，并不是固定的。

### 2.6.5 SQL提示

目前tb_user表的数据情况如下:

#### 索引情况如下:

把上述的 idx_user_age, idx_email 这两个之前测试使用过的索引直接删除。

```
drop index idx_user_age on tb_user;
drop index idx_email on tb_user;
```
```
1
2
```

A. 执行SQL : explain select * from tb_user where profession = '软件工程';

#### 查询走了联合索引。

B. 执行SQL，创建profession的单列索引：create index idx_user_pro on
tb_user(profession);

#### C. 创建单列索引后，再次执行A中的SQL语句，查看执行计划，看看到底走哪个索引。

测试结果，我们可以看到，possible_keys中 idx_user_pro_age_sta,idx_user_pro 这两个
索引都可能用到，最终MySQL选择了idx_user_pro_age_sta索引。这是MySQL自动选择的结果。

#### 那么，我们能不能在查询的时候，自己来指定使用哪个索引呢？ 答案是肯定的，此时就可以借助于

MySQL的SQL提示来完成。 接下来，介绍一下SQL提示。

#### SQL提示，是优化数据库的一个重要手段，简单来说，就是在SQL语句中加入一些人为的提示来达到优

#### 化操作的目的。

1). use index ： 建议MySQL使用哪一个索引完成此次查询（仅仅是建议，mysql内部还会再次进
行评估）。

2). ignore index ： 忽略指定的索引。

3). force index ： 强制使用索引。

```
explain select * from tb_user use index(idx_user_pro) where profession = '软件工
程';
```
```
1
```
```
explain select * from tb_user ignore index(idx_user_pro) where profession = '软件工
程';
```
```
1
```

#### 示例演示：

A. use index

B. ignore index

C. force index

### 2.6.6 覆盖索引

尽量使用覆盖索引，减少select *。 那么什么是覆盖索引呢？ 覆盖索引是指 查询使用了索引，并
且需要返回的列，在该索引中已经全部能够找到 。

```
explain select * from tb_user force index(idx_user_pro) where profession = '软件工
程';
```
```
1
```
```
explain select * from tb_user use index(idx_user_pro) where profession = '软件工
程';
```
```
1
```
```
explain select * from tb_user ignore index(idx_user_pro) where profession = '软件工
程';
```
```
1
```
```
explain select * from tb_user force index(idx_user_pro_age_sta) where profession =
'软件工程';
```
```
1
```

```
Extra 含义
Using where; Using
Index
```
#### 查找使用了索引，但是需要的数据都在索引列中能找到，所以不需

#### 要回表查询数据

```
Using index
condition 查找使用了索引，但是需要回表查询数据
```
#### 接下来，我们来看一组SQL的执行计划，看看执行计划的差别，然后再来具体做一个解析。

#### 上述这几条SQL的执行结果为:

#### 从上述的执行计划我们可以看到，这四条SQL语句的执行计划前面所有的指标都是一样的，看不出来差

异。但是此时，我们主要关注的是后面的Extra，前面两天SQL的结果为 Using where; Using
Index ; 而后面两条SQL的结果为: Using index condition 。

因为，在tb_user表中有一个联合索引 idx_user_pro_age_sta，该索引关联了三个字段
profession、age、status，而这个索引也是一个二级索引，所以叶子节点下面挂的是这一行的主
键id。 所以当我们查询返回的数据在 id、profession、age、status 之中，则直接走二级索引
直接返回数据了。 如果超出这个范围，就需要拿到主键id，再去扫描聚集索引，再获取额外的数据

```
explain select id, profession from tb_user where profession = '软件工程' and age =
31 and status = '0' ;
explain select id,profession,age, status from tb_user where profession = '软件工程'
and age = 31 and status = '0' ;
explain select id,profession,age, status, name from tb_user where profession = '软
件工程' and age = 31 and status = '0' ;
explain select * from tb_user where profession = '软件工程' and age = 31 and status
= '0';
```
```
1
```
```
2
```
```
3
```
```
4
```

了，这个过程就是回表。 而我们如果一直使用select * 查询返回所有字段值，很容易就会造成回表
查询（除非是根据主键查询，此时只会扫描聚集索引）。

#### 为了大家更清楚的理解，什么是覆盖索引，什么是回表查询，我们一起再来看下面的这组SQL的执行过

#### 程。

#### A. 表结构及索引示意图:

id是主键，是一个聚集索引。 name字段建立了普通索引，是一个二级索引（辅助索引）。

B. 执行SQL : select * from tb_user where id = 2;

根据id查询，直接走聚集索引查询，一次索引扫描，直接返回数据，性能高。

C. 执行SQL：selet id,name from tb_user where name = 'Arm';


虽然是根据name字段查询，查询二级索引，但是由于查询返回在字段为 id，name，在name的二级索
引中，这两个值都是可以直接获取到的，因为覆盖索引，所以不需要回表查询，性能高。

D. 执行SQL：selet id,name,gender from tb_user where name = 'Arm';

由于在name的二级索引中，不包含gender，所以，需要两次索引扫描，也就是需要回表查询，性能相
对较差一点。

#### 思考题：

```
一张表, 有四个字段(id, username, password, status), 由于数据量大, 需要对
以下SQL语句进行优化, 该如何进行才是最优方案:
select id,username,password from tb_user where username =
'itcast';
```
```
答案: 针对于 username, password建立联合索引, sql为: create index
idx_user_name_pass on tb_user(username,password);
这样可以避免上述的SQL语句，在查询的过程中，出现回表查询。
```

### 2.6.7 前缀索引

当字段类型为字符串（varchar，text，longtext等）时，有时候需要索引很长的字符串，这会让
索引变得很大，查询时，浪费大量的磁盘IO， 影响查询效率。此时可以只将字符串的一部分前缀，建
立索引，这样可以大大节约索引空间，从而提高索引效率。

1). 语法

#### 示例:

为tb_user表的email字段，建立长度为 5 的前缀索引。

#### 2). 前缀长度

#### 可以根据索引的选择性来决定，而选择性是指不重复的索引值（基数）和数据表的记录总数的比值，

#### 索引选择性越高则查询效率越高， 唯一索引的选择性是 1 ，这是最好的索引选择性，性能也是最好的。

#### 3). 前缀索引的查询流程

```
1 create index idx_xxxx on table_name(column(n)) ;
```
```
1 create index idx_email_5 on tb_user(email( 5 ));
```
```
select count(distinct email) / count(*) from tb_user ;
select count(distinct substring(email, 1 , 5 )) / count(*) from tb_user ;
```
```
1
2
```

### 2.6.8 单列索引与联合索引

#### 单列索引：即一个索引只包含单个列。

#### 联合索引：即一个索引包含了多个列。

我们先来看看 tb_user 表中目前的索引情况:

#### 在查询出来的索引中，既有单列索引，又有联合索引。

#### 接下来，我们来执行一条SQL语句，看看其执行计划：

通过上述执行计划我们可以看出来，在and连接的两个字段 phone、name上都是有单列索引的，但是
最终mysql只会选择一个索引，也就是说，只能走一个字段的索引，此时是会回表查询的。

紧接着，我们再来创建一个phone和name字段的联合索引来查询一下执行计划。


此时，查询时，就走了联合索引，而在联合索引中包含 phone、name的信息，在叶子节点下挂的是对
应的主键id，所以查询是无需回表查询的。

#### 在业务场景中，如果存在多个查询条件，考虑针对于查询字段建立索引时，建议建立联合索引，

#### 而非单列索引。

#### 如果查询使用的是联合索引，具体的结构示意图如下：

## 2.7 索引设计原则

#### 1). 针对于数据量较大，且查询比较频繁的表建立索引。

2). 针对于常作为查询条件（where）、排序（order by）、分组（group by）操作的字段建立索
引。

3). 尽量选择区分度高的列作为索引，尽量建立唯一索引，区分度越高，使用索引的效率越高。

4). 如果是字符串类型的字段，字段的长度较长，可以针对于字段的特点，建立前缀索引。

5). 尽量使用联合索引，减少单列索引，查询时，联合索引很多时候可以覆盖索引，节省存储空间，
避免回表，提高查询效率。

6). 要控制索引的数量，索引并不是多多益善，索引越多，维护索引结构的代价也就越大，会影响增
删改的效率。

```
1 create unique index idx_user_phone_name on tb_user(phone,name);
```

#### 7). 如果索引列不能存储NULL值，请在创建表时使用NOT NULL约束它。当优化器知道每列是否包含

#### NULL值时，它可以更好地确定哪个索引最有效地用于查询。

# 3. SQL优化

## 3.1 插入数据

## 3.1.1 insert

#### 如果我们需要一次性往数据库表中插入多条记录，可以从以下三个方面进行优化。

#### 1). 优化方案一

#### 批量插入数据

#### 2). 优化方案二

#### 手动控制事务

#### 3). 优化方案三

```
insert into tb_test values( 1 ,'tom');
insert into tb_test values( 2 ,'cat');
insert into tb_test values( 3 ,'jerry');
.....
```
```
1
2
3
4
```
```
1 Insert into tb_test values( 1 ,'Tom'),( 2 ,'Cat'),( 3 ,'Jerry');
```
```
start transaction;
insert into tb_test values( 1 ,'Tom'),( 2 ,'Cat'),( 3 ,'Jerry');
insert into tb_test values( 4 ,'Tom'),( 5 ,'Cat'),( 6 ,'Jerry');
insert into tb_test values( 7 ,'Tom'),( 8 ,'Cat'),( 9 ,'Jerry');
commit;
```
```
1
2
3
4
5
```

#### 主键顺序插入，性能要高于乱序插入。

### 3.1.2 大批量插入数据

如果一次性需要插入大批量数据(比如: 几百万的记录)，使用insert语句插入性能较低，此时可以使
用MySQL数据库提供的load指令进行插入。操作如下：

#### 可以执行如下指令，将数据脚本文件中的数据加载到表结构中：

#### 主键顺序插入性能高于乱序插入

#### 示例演示:

#### A. 创建表结构

```
主键乱序插入 : 8 1 9 21 88 2 4 15 89 5 7 3
主键顺序插入 : 1 2 3 4 5 7 8 9 15 21 88 89
```
```
1
2
```
```
-- 客户端连接服务端时，加上参数 -–local-infile
mysql –-local-infile -u root -p
```
```
-- 设置全局参数local_infile为 1 ，开启从本地加载文件导入数据的开关
set global local_infile = 1 ;
```
```
-- 执行load指令将准备好的数据，加载到表结构中
load data local infile '/root/sql1.log' into table tb_user fields
terminated by ',' lines terminated by '\n' ;
```
```
1 2 3 4 5 6 7 8
```

#### B. 设置参数

C. load加载数据

我们看到，插入100w的记录，17s就完成了，性能很好。

```
在load时，主键顺序插入性能高于乱序插入
```
## 3.2 主键优化

```
CREATE TABLE `tb_user` (
`id` INT( 11 ) NOT NULL AUTO_INCREMENT,
`username` VARCHAR( 50 ) NOT NULL,
`password` VARCHAR( 50 ) NOT NULL,
`name` VARCHAR( 20 ) NOT NULL,
`birthday` DATE DEFAULT NULL,
`sex` CHAR( 1 ) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
```
```
-- 客户端连接服务端时，加上参数 -–local-infile
mysql –-local-infile -u root -p
```
```
-- 设置全局参数local_infile为 1 ，开启从本地加载文件导入数据的开关
set global local_infile = 1 ;
```
```
1
2
3
4
5
```
```
load data local infile '/root/load_user_100w_sort.sql' into table tb_user
fields terminated by ',' lines terminated by '\n' ;
```
```
1
```

#### 在上一小节，我们提到，主键顺序插入的性能是要高于乱序插入的。 这一小节，就来介绍一下具体的

#### 原因，然后再分析一下主键又该如何设计。

#### 1). 数据组织方式

在InnoDB存储引擎中，表数据都是根据主键顺序组织存放的，这种存储方式的表称为索引组织表
(index organized table IOT)。

行数据，都是存储在聚集索引的叶子节点上的。而我们之前也讲解过InnoDB的逻辑结构图：

在InnoDB引擎中，数据行是记录在逻辑结构 page 页中的，而每一个页的大小是固定的，默认16K。
那也就意味着， 一个页中所存储的行也是有限的，如果插入的数据行row在该页存储不小，将会存储
到下一个页中，页与页之间会通过指针连接。

#### 2). 页分裂

#### 页可以为空，也可以填充一半，也可以填充100%。每个页包含了2-N行数据(如果一行数据过大，会行

#### 溢出)，根据主键排列。

#### A. 主键顺序插入效果

#### ①. 从磁盘中申请页， 主键顺序插入


#### ②. 第一个页没有满，继续往第一页插入

#### ③. 当第一个也写满之后，再写入第二个页，页与页之间会通过指针连接

#### ④. 当第二页写满了，再往第三页写入

#### B. 主键乱序插入效果

#### ①. 加入1#,2#页都已经写满了，存放了如图所示的数据

②. 此时再插入id为 50 的记录，我们来看看会发生什么现象

会再次开启一个页，写入新的页中吗？


#### 不会。因为，索引结构的叶子节点是有顺序的。按照顺序，应该存储在 47 之后。

#### 但是 47 所在的1#页，已经写满了，存储不了 50 对应的数据了。 那么此时会开辟一个新的页 3#。

#### 但是并不会直接将 50 存入3#页，而是会将1#页后一半的数据，移动到3#页，然后在3#页，插入 50 。

移动数据，并插入id为 50 的数据之后，那么此时，这三个页之间的数据顺序是有问题的。 1#的下一个
页，应该是3#， 3#的下一个页是2#。 所以，此时，需要重新设置链表指针。

#### 上述的这种现象，称之为 "页分裂"，是比较耗费性能的操作。

#### 3). 页合并

#### 目前表中已有数据的索引结构(叶子节点)如下：


#### 当我们对已有数据进行删除时，具体的效果如下:

当删除一行记录时，实际上记录并没有被物理删除，只是记录被标记（flaged）为删除并且它的空间
变得允许被其他记录声明使用。

#### 当我们继续删除2#的数据记录

当页中删除的记录达到 MERGE_THRESHOLD（默认为页的50%），InnoDB会开始寻找最靠近的页（前
或后）看看是否可以将两个页合并以优化空间使用。

#### 删除数据，并将页合并之后，再次插入新的数据 21 ，则直接插入3#页

#### 这个里面所发生的合并页的这个现象，就称之为 "页合并"。

#### 知识小贴士：

#### MERGE_THRESHOLD：合并页的阈值，可以自己设置，在创建表或者创建索引时指定。

#### 4). 索引设计原则

#### 满足业务需求的情况下，尽量降低主键的长度。

#### 插入数据时，尽量选择顺序插入，选择使用AUTO_INCREMENT自增主键。


#### 尽量不要使用UUID做主键或者是其他自然主键，如身份证号。

#### 业务操作时，避免对主键的修改。

## 3.3 order by优化

MySQL的排序，有两种方式：

Using filesort : 通过表的索引或全表扫描，读取满足条件的数据行，然后在排序缓冲区sort
buffer中完成排序操作，所有不是通过索引直接返回排序结果的排序都叫 FileSort 排序。

Using index : 通过有序索引顺序扫描直接返回有序数据，这种情况即为 using index，不需要
额外排序，操作效率高。

对于以上的两种排序方式，Using index的性能高，而Using filesort的性能低，我们在优化排序
操作时，尽量要优化为 Using index。

#### 接下来，我们来做一个测试：

#### A. 数据准备

把之前测试时，为tb_user表所建立的部分索引直接删除掉


#### B. 执行排序SQL

由于 age, phone 都没有索引，所以此时再排序时，出现Using filesort， 排序性能较低。

#### C. 创建索引

D. 创建索引后，根据age, phone进行升序排序

```
drop index idx_user_phone on tb_user;
drop index idx_user_phone_name on tb_user;
drop index idx_user_name on tb_user;
```
```
1
2
3
```
```
1 explain select id,age,phone from tb_user order by age ;
```
```
1 explain select id,age,phone from tb_user order by age, phone ;
```
```
-- 创建索引
create index idx_user_age_phone_aa on tb_user(age,phone);
```
```
1
2
```
```
1 explain select id,age,phone from tb_user order by age;
```

建立索引之后，再次进行排序查询，就由原来的Using filesort， 变为了 Using index，性能
就是比较高的了。

E. 创建索引后，根据age, phone进行降序排序

也出现 Using index， 但是此时Extra中出现了 Backward index scan，这个代表反向扫描索
引，因为在MySQL中我们创建的索引，默认索引的叶子节点是从小到大排序的，而此时我们查询排序
时，是从大到小，所以，在扫描时，就是反向扫描，就会出现 Backward index scan。 在
MySQL8版本中，支持降序索引，我们也可以创建降序索引。

F. 根据phone，age进行升序排序，phone在前，age在后。

排序时,也需要满足最左前缀法则,否则也会出现 filesort。因为在创建索引的时候， age是第一个
字段，phone是第二个字段，所以排序时，也就该按照这个顺序来，否则就会出现 Using
filesort。

F. 根据age, phone进行降序一个升序，一个降序

```
1 explain select id,age,phone from tb_user order by age , phone;
```
```
1 explain select id,age,phone from tb_user order by age desc , phone desc ;
```
```
1 explain select id,age,phone from tb_user order by phone , age;
```

#### 因为创建索引时，如果未指定顺序，默认都是按照升序排序的，而查询时，一个升序，一个降序，此时

就会出现Using filesort。

为了解决上述的问题，我们可以创建一个索引，这个联合索引中 age 升序排序，phone 倒序排序。

G. 创建联合索引(age 升序排序，phone 倒序排序)

#### H. 然后再次执行如下SQL

#### 升序/降序联合索引结构图示:

```
1 explain select id,age,phone from tb_user order by age asc , phone desc ;
```
```
1 create index idx_user_age_phone_ad on tb_user(age asc ,phone desc);
```
```
1 explain select id,age,phone from tb_user order by age asc , phone desc ;
```

由上述的测试,我们得出order by优化原则:

A. 根据排序字段建立合适的索引，多字段排序时，也遵循最左前缀法则。

B. 尽量使用覆盖索引。

C. 多字段排序, 一个升序一个降序，此时需要注意联合索引在创建时的规则（ASC/DESC）。

D. 如果不可避免的出现filesort，大数据量排序时，可以适当增大排序缓冲区大小
sort_buffer_size(默认256k)。

## 3.4 group by优化

#### 分组操作，我们主要来看看索引对于分组操作的影响。

首先我们先将 tb_user 表的索引全部删除掉 。

#### 接下来，在没有索引的情况下，执行如下SQL，查询执行计划：

然后，我们在针对于 profession ， age， status 创建一个联合索引。

```
drop index idx_user_pro_age_sta on tb_user;
drop index idx_email_5 on tb_user;
drop index idx_user_age_phone_aa on tb_user;
drop index idx_user_age_phone_ad on tb_user;
```
```
1
2
3
4
```
```
1 explain select profession , count(*) from tb_user group by profession ;
```

#### 紧接着，再执行前面相同的SQL查看执行计划。

#### 再执行如下的分组查询SQL，查看执行计划：

我们发现，如果仅仅根据age分组，就会出现 Using temporary ；而如果是 根据
profession,age两个字段同时分组，则不会出现 Using temporary。原因是因为对于分组操作，
在联合索引中，也是符合最左前缀法则的。

#### 所以，在分组操作中，我们需要通过以下两点进行优化，以提升性能：

#### A. 在分组操作时，可以通过索引来提高效率。

#### B. 分组操作时，索引的使用也是满足最左前缀法则的。

## 3.5 limit优化

在数据量比较大时，如果进行limit分页查询，在查询时，越往后，分页查询效率越低。

我们一起来看看执行limit分页查询耗时对比：

```
1 create index idx_user_pro_age_sta on tb_user(profession , age , status);
```
```
1 explain select profession , count(*) from tb_user group by profession ;
```

#### 通过测试我们会看到，越往后，分页查询效率越低，这就是分页查询的问题所在。

因为，当在进行分页查询时，如果执行 limit 2000000,10 ，此时需要MySQL排序前 2000010 记
录，仅仅返回 200000 0 - 2000010 的记录，其他记录丢弃，查询排序的代价非常大 。

#### 优化思路: 一般分页查询时，通过创建 覆盖索引 能够比较好地提高性能，可以通过覆盖索引加子查

#### 询形式进行优化。

## 3.6 count优化

### 3.6.1 概述

在之前的测试中，我们发现，如果数据量很大，在执行count操作时，是非常耗时的。

```
MyISAM 引擎把一个表的总行数存在了磁盘上，因此执行 count(*) 的时候会直接返回这个
数，效率很高； 但是如果是带条件的count，MyISAM也慢。
InnoDB 引擎就麻烦了，它执行 count(*) 的时候，需要把数据一行一行地从引擎里面读出
来，然后累积计数。
```
如果说要大幅度提升InnoDB表的count效率，主要的优化思路：自己计数(可以借助于redis这样的数
据库进行,但是如果是带条件的count又比较麻烦了)。

### 3.6.2 count用法

count() 是一个聚合函数，对于返回的结果集，一行行地判断，如果 count 函数的参数不是
NULL，累计值就加 1 ，否则不加，最后返回累计值。

用法：count（*）、count（主键）、count（字段）、count（数字）

```
explain select * from tb_sku t , (select id from tb_sku order by id
limit 2000000 , 10 ) a where t.id = a.id;
```
```
1
```
```
1 select count(*) from tb_user ;
```

```
count用
法 含义
count(主
键)
```
```
InnoDB 引擎会遍历整张表，把每一行的 主键id 值都取出来，返回给服务层。
服务层拿到主键后，直接按行进行累加(主键不可能为null)
```
```
count(字
段)
```
```
没有not null 约束 : InnoDB 引擎会遍历整张表把每一行的字段值都取出
来，返回给服务层，服务层判断是否为null，不为null，计数累加。
有not null 约束：InnoDB 引擎会遍历整张表把每一行的字段值都取出来，返
回给服务层，直接按行进行累加。
count(数
字)
```
```
InnoDB 引擎遍历整张表，但不取值。服务层对于返回的每一行，放一个数字“1”
进去，直接按行进行累加。
```
```
count(*)
```
```
InnoDB引擎并不会把全部字段取出来，而是专门做了优化，不取值，服务层直接
按行进行累加。
```
```
按照效率排序的话，count(字段) < count(主键 id) < count(1) ≈ count(*)，所以尽
量使用 count(*)。
```
## 3.7 update优化

我们主要需要注意一下update语句执行时的注意事项。

当我们在执行删除的SQL语句时，会锁定id为 1 这一行的数据，然后事务提交之后，行锁释放。

#### 但是当我们在执行如下SQL时。

当我们开启多个事务，在执行上述的SQL时，我们发现行锁升级为了表锁。 导致该update语句的性能
大大降低。

```
1 update course set name = 'javaEE' where id = 1 ;
```
```
1 update course set name = 'SpringBoot' where name = 'PHP' ;
```

```
InnoDB的行锁是针对索引加的锁，不是针对记录加的锁 ,并且该索引不能失效，否则会从行锁
升级为表锁 。
```
# 4. 视图/存储过程/触发器

## 4.1 视图

## 4.1.1 介绍

视图（View）是一种虚拟存在的表。视图中的数据并不在数据库中实际存在，行和列数据来自定义视
图的查询中使用的表，并且是在使用视图时动态生成的。

通俗的讲，视图只保存了查询的SQL逻辑，不保存查询结果。所以我们在创建视图的时候，主要的工作
就落在创建这条SQL查询语句上。

## 4.1.2 语法

#### 1). 创建

#### 2). 查询

#### 3). 修改

#### 4). 删除

```
CREATE [OR REPLACE] VIEW 视图名称[(列名列表)] AS SELECT语句 [ WITH [
CASCADED | LOCAL ] CHECK OPTION ]
```
```
1
```
```
查看创建视图语句：SHOW CREATE VIEW 视图名称;
查看视图数据：SELECT * FROM 视图名称 ...... ;
```
```
1
2
```
```
方式一：CREATE [OR REPLACE] VIEW 视图名称[(列名列表)] AS SELECT语句 [ WITH
[ CASCADED | LOCAL ] CHECK OPTION ]
方式二：ALTER VIEW 视图名称[(列名列表)] AS SELECT语句 [ WITH [ CASCADED |
LOCAL ] CHECK OPTION ]
```
```
1
```
```
2
```
```
1 DROP VIEW [IF EXISTS] 视图名称 [,视图名称] ...
```

#### 演示示例：

#### 上述我们演示了，视图应该如何创建、查询、修改、删除，那么我们能不能通过视图来插入、更新数据

#### 呢？ 接下来，做一个测试。

执行上述的SQL，我们会发现，id为 6 和 17 的数据都是可以成功插入的。 但是我们执行查询，查询出
来的数据，却没有id为 17 的记录。

```
-- 创建视图
create or replace view stu_v_1 as select id,name from student where id <= 10 ;
```
```
-- 查询视图
show create view stu_v_1;
```
```
select * from stu_v_1;
select * from stu_v_1 where id < 3 ;
```
```
-- 修改视图
create or replace view stu_v_1 as select id,name,no from student where id <= 10 ;
```
```
alter view stu_v_1 as select id,name from student where id <= 10 ;
```
```
-- 删除视图
drop view if exists stu_v_1;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
15
16
17
```
```
create or replace view stu_v_1 as select id,name from student where id <= 10 ;
```
```
select * from stu_v_1;
```
```
insert into stu_v_1 values( 6 ,'Tom');
```
```
insert into stu_v_1 values( 17 ,'Tom22');
```
```
1 2 3 4 5 6 7
```

因为我们在创建视图的时候，指定的条件为 id<=10, id为 17 的数据，是不符合条件的，所以没有查
询出来，但是这条数据确实是已经成功的插入到了基表中。

如果我们定义视图时，如果指定了条件，然后我们在插入、修改、删除数据时，是否可以做到必须满足
条件才能操作，否则不能够操作呢？ 答案是可以的，这就需要借助于视图的检查选项了。

### 4.1.3 检查选项

当使用WITH CHECK OPTION子句创建视图时，MySQL会通过视图检查正在更改的每个行，例如 插
入，更新，删除，以使其符合视图的定义。 MySQL允许基于另一个视图创建视图，它还会检查依赖视
图中的规则以保持一致性。为了确定检查的范围，mysql提供了两个选项： CASCADED 和 LOCAL
，默认值为 CASCADED 。

#### 1). CASCADED

#### 级联。

比如，v2视图是基于v1视图的，如果在v2视图创建的时候指定了检查选项为 cascaded，但是v1视图
创建时未指定检查选项。 则在执行检查时，不仅会检查v2，还会级联检查v2的关联视图v1。

#### 2). LOCAL

#### 本地。

比如，v2视图是基于v1视图的，如果在v2视图创建的时候指定了检查选项为 local ，但是v1视图创
建时未指定检查选项。 则在执行检查时，知会检查v2，不会检查v2的关联视图v1。


### 4.1.4 视图的更新

#### 要使视图可更新，视图中的行与基础表中的行之间必须存在一对一的关系。如果视图包含以下任何一

#### 项，则该视图不可更新：

#### A. 聚合函数或窗口函数（SUM()、 MIN()、 MAX()、 COUNT()等）

#### B. DISTINCT

#### C. GROUP BY

#### D. HAVING

#### E. UNION 或者 UNION ALL

#### 示例演示:

#### 上述的视图中，就只有一个单行单列的数据，如果我们对这个视图进行更新或插入的，将会报错。

### 4.1.5 视图作用

#### 1). 简单

#### 视图不仅可以简化用户对数据的理解，也可以简化他们的操作。那些被经常使用的查询可以被定义为视

#### 图，从而使得用户不必为以后的操作每次指定全部的条件。

#### 2). 安全

#### 数据库可以授权，但不能授权到数据库特定行和特定的列上。通过视图用户只能查询和修改他们所能见

#### 到的数据

```
1 create view stu_v_count as select count(*) from student;
```
```
1 insert into stu_v_count values(10);
```

#### 3). 数据独立

#### 视图可帮助用户屏蔽真实表结构变化带来的影响。

### 4.1.6 案例

1). 为了保证数据库表的安全性，开发人员在操作tb_user表时，只能看到的用户的基本字段，屏蔽
手机号和邮箱两个字段。

#### 2). 查询每个学生所选修的课程（三张表联查），这个功能在很多的业务中都有使用到，为了简化操

#### 作，定义一个视图。

## 4.2 存储过程

### 4.2.1 介绍

#### 存储过程是事先经过编译并存储在数据库中的一段 SQL 语句的集合，调用存储过程可以简化应用开发

#### 人员的很多工作，减少数据在数据库和应用服务器之间的传输，对于提高数据处理的效率是有好处的。

#### 存储过程思想上很简单，就是数据库 SQL 语言层面的代码封装与重用。

#### 特点:

```
create view tb_user_view as select id,name,profession,age,gender,status,createtime
from tb_user;
```
```
select * from tb_user_view;
```
```
1
```
```
2
3
```
```
create view tb_stu_course_view as select s.name student_name , s.no student_no ,
c.name course_name from student s, student_course sc , course c where s.id =
sc.studentid and sc.courseid = c.id;
```
```
select * from tb_stu_course_view;
```
```
1
```
```
2
3
```

#### 封装，复用 -----------------------> 可以把某一业务SQL封装在存储过程中，需要用到

#### 的时候直接调用即可。

#### 可以接收参数，也可以返回数据 --------> 再存储过程中，可以传递参数，也可以接收返回

#### 值。

#### 减少网络交互，效率提升 -------------> 如果涉及到多条SQL，每执行一次都是一次网络传

#### 输。 而如果封装在存储过程中，我们只需要网络交互一次可能就可以了。

### 4.2.2 基本语法

#### 1). 创建

#### 2). 调用

#### 3). 查看

#### 4). 删除

#### 注意:

```
在命令行中，执行创建存储过程的SQL时，需要通过关键字 delimiter 指定SQL语句的
结束符。
```
```
CREATE PROCEDURE 存储过程名称 ([ 参数列表 ])
BEGIN
-- SQL语句
END ;
```
```
1
2
3
4
```
```
1 CALL 名称 ([ 参数 ]);
```
```
SELECT * FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_SCHEMA = 'xxx'; -- 查询指
定数据库的存储过程及状态信息
SHOW CREATE PROCEDURE 存储过程名称 ; -- 查询某个存储过程的定义
```
```
1
```
```
2
```
```
1 DROP PROCEDURE [ IF EXISTS ] 存储过程名称 ；
```

#### 演示示例:

### 4.2.3 变量

在MySQL中变量分为三种类型: 系统变量、用户定义变量、局部变量。

**4.2.3.1 系统变量**

系统变量 是MySQL服务器提供，不是用户定义的，属于服务器层面。分为全局变量（GLOBAL）、会话
变量（SESSION）。

1). 查看系统变量

#### 2). 设置系统变量

```
-- 存储过程基本语法
-- 创建
create procedure p1()
begin
select count(*) from student;
end;
```
```
-- 调用
call p1();
```
```
-- 查看
select * from information_schema.ROUTINES where ROUTINE_SCHEMA = 'itcast';
```
```
show create procedure p1;
```
```
-- 删除
drop procedure if exists p1;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
15
16
17
```
```
SHOW [ SESSION | GLOBAL ] VARIABLES ; -- 查看所有系统变量
SHOW [ SESSION | GLOBAL ] VARIABLES LIKE '......'; -- 可以通过LIKE模糊匹配方
式查找变量
SELECT @@[SESSION | GLOBAL] 系统变量名; -- 查看指定变量的值
```
```
1
2
```
```
3
```

#### 注意:

#### 如果没有指定SESSION/GLOBAL，默认是SESSION，会话变量。

#### A. 全局变量(GLOBAL): 全局变量针对于所有的会话。

#### B. 会话变量(SESSION): 会话变量针对于单个会话，在另外一个会话窗口就不生效了。

#### 演示示例:

```
SET [ SESSION | GLOBAL ] 系统变量名 = 值 ;
SET @@[SESSION | GLOBAL]系统变量名 = 值 ;
```
```
1
2
```
```
1 mysql服务重新启动之后，所设置的全局参数会失效，要想不失效，可以在 /etc/my.cnf 中配置。
```
```
-- 查看系统变量
show session variables ;
```
```
show session variables like 'auto%';
show global variables like 'auto%';
```
```
select @@global.autocommit;
select @@session.autocommit;
```
```
-- 设置系统变量
set session autocommit = 1 ;
```
```
insert into course(id, name) VALUES ( 6 , 'ES');
```
```
set global autocommit = 0 ;
```
```
select @@global.autocommit;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
15
16
17
18
```

#### 4.2.3.2 用户定义变量

#### 用户定义变量 是用户根据需要自己定义的变量，用户变量不用提前声明，在用的时候直接用 "@变量

#### 名" 使用就可以。其作用域为当前连接。

#### 1). 赋值

#### 方式一:

#### 赋值时，可以使用 = ，也可以使用 := 。

#### 方式二:

#### 2). 使用

#### 注意: 用户定义的变量无需对其进行声明或初始化，只不过获取到的值为NULL。

#### 演示示例:

```
SET @var_name = expr [, @var_name = expr] ... ;
SET @var_name := expr [, @var_name := expr] ... ;
```
```
1
2
```
```
SELECT @var_name := expr [, @var_name := expr] ... ;
SELECT 字段名 INTO @var_name FROM 表名;
```
```
1
2
```
```
1 SELECT @var_name ;
```
```
-- 赋值
set @myname = 'itcast';
set @myage := 10 ;
set @mygender := '男',@myhobby := 'java';
```
```
select @mycolor := 'red';
select count(*) into @mycount from tb_user;
```
```
-- 使用
select @myname,@myage,@mygender,@myhobby;
```
```
select @mycolor , @mycount;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
```

#### 4.2.3.3 局部变量

#### 局部变量 是根据需要定义的在局部生效的变量，访问之前，需要DECLARE声明。可用作存储过程内的

#### 局部变量和输入参数，局部变量的范围是在其内声明的BEGIN ... END块。

#### 1). 声明

#### 变量类型就是数据库字段类型：INT、BIGINT、CHAR、VARCHAR、DATE、TIME等。

#### 2). 赋值

#### 演示示例:

### 4.2.4 if

#### 1). 介绍

```
select @abc;
```
```
13
14
```
```
1 DECLARE 变量名 变量类型 [DEFAULT ... ] ;
```
```
SET 变量名 = 值 ;
SET 变量名 := 值 ;
SELECT 字段名 INTO 变量名 FROM 表名 ... ;
```
```
1
2
3
```
```
-- 声明局部变量 - declare
-- 赋值
create procedure p2()
begin
declare stu_count int default 0 ;
select count(*) into stu_count from student;
select stu_count;
end;
```
```
call p2();
```
```
1 2 3 4 5 6 7 8 9
```
```
10
```

if 用于做条件判断，具体的语法结构为：

在if条件判断的结构中，ELSE IF 结构可以有多个，也可以没有。 ELSE结构可以有，也可以没有。

#### 2). 案例

根据定义的分数score变量，判定当前分数对应的分数等级。

```
score >= 85分，等级为优秀。
score >= 60分 且 score < 85分，等级为及格。
score < 60分，等级为不及格。
```
```
IF 条件1 THEN
.....
ELSEIF 条件2 THEN -- 可选
.....
ELSE -- 可选
.....
END IF;
```
```
1 2 3 4 5 6 7
```
```
create procedure p3()
begin
declare score int default 58 ;
declare result varchar( 10 );
```
```
if score >= 85 then
set result := '优秀';
elseif score >= 60 then
set result := '及格';
else
set result := '不及格';
end if;
select result;
end;
```
```
call p3();
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
15
16
```

#### 类型 含义 备注

#### IN 该类参数作为输入，也就是需要调用时传入值 默认

#### OUT 该类参数作为输出，也就是该参数可以作为返回值

#### INOUT 既可以作为输入参数，也可以作为输出参数

上述的需求我们虽然已经实现了，但是也存在一些问题，比如：score 分数我们是在存储过程中定义
死的，而且最终计算出来的分数等级，我们也仅仅是最终查询展示出来而已。

那么我们能不能，把score分数动态的传递进来，计算出来的分数等级是否可以作为返回值返回呢？
答案是肯定的，我们可以通过接下来所讲解的 参数 来解决上述的问题。

### 4.2.5 参数

#### 1). 介绍

#### 参数的类型，主要分为以下三种：IN、OUT、INOUT。 具体的含义如下：

#### 用法：

#### 2). 案例一

根据传入参数score，判定当前分数对应的分数等级，并返回。

```
score >= 85分，等级为优秀。
score >= 60分 且 score < 85分，等级为及格。
score < 60分，等级为不及格。
```
```
CREATE PROCEDURE 存储过程名称 ([ IN/OUT/INOUT 参数名 参数类型 ])
BEGIN
-- SQL语句
END ;
```
```
1
2
3
4
```
```
create procedure p4(in score int, out result varchar( 10 ))
begin
if score >= 85 then
set result := '优秀';
elseif score >= 60 then
```
```
1
2
3
4
5
```

#### 3). 案例二

#### 将 传入 的 200 分制的分数，进行换算，换算成百分制，然后 返回 。

### 4.2.6 case

#### 1). 介绍

case结构及作用，和我们在基础篇中所讲解的流程控制函数很类似。有两种语法格式：

语法 1 ：

```
set result := '及格';
else
set result := '不及格';
end if;
end;
```
```
-- 定义用户变量 @result来接收返回的数据, 用户变量可以不用声明
call p4( 18 , @result);
```
```
select @result;
```
```
6
7
8
9
10
11
12
13
14
15
```
```
create procedure p5(inout score double)
begin
set score := score * 0.5;
end;
```
```
set @score = 198 ;
call p5(@score);
```
```
select @score;
```
```
1 2 3 4 5 6 7 8 9
```

#### 语法 2 ：

#### 2). 案例

根据传入的月份，判定月份所属的季节（要求采用case结构）。

```
1-3月份，为第一季度
4-6月份，为第二季度
7-9月份，为第三季度
10-12月份，为第四季度
```
```
-- 含义： 当case_value的值为 when_value1时，执行statement_list1，当值为 when_value2时，
执行statement_list2， 否则就执行 statement_list
CASE case_value
WHEN when_value1 THEN statement_list1
[ WHEN when_value2 THEN statement_list2] ...
[ ELSE statement_list ]
END CASE;
```
```
1 2 3 4 5 6
```
```
-- 含义： 当条件search_condition1成立时，执行statement_list1，当条件search_condition2成
立时，执行statement_list2， 否则就执行 statement_list
CASE
WHEN search_condition1 THEN statement_list1
[WHEN search_condition2 THEN statement_list2] ...
[ELSE statement_list]
END CASE;
```
```
1 2 3 4 5 6
```
```
create procedure p6(in month int)
begin
declare result varchar( 10 );
case
when month >= 1 and month <= 3 then
set result := '第一季度';
when month >= 4 and month <= 6 then
set result := '第二季度';
when month >= 7 and month <= 9 then
set result := '第三季度';
```
```
1 2 3 4 5 6 7 8 9
```
```
10
```

```
注意：如果判定条件有多个，多个条件之间，可以使用 and 或 or 进行连接。
```
### 4.2.7 while

#### 1). 介绍

while 循环是有条件的循环控制语句。满足条件后，再执行循环体中的SQL语句。具体语法为：

#### 2). 案例

计算从 1 累加到n的值，n为传入的参数值。

```
when month >= 10 and month <= 12 then
set result := '第四季度';
else
set result := '非法参数';
end case ;
```
```
select concat('您输入的月份为: ',month, ', 所属的季度为: ',result);
end;
```
```
call p6( 16 );
```
```
11
12
13
14
15
16
17
18
19
20
```
```
-- 先判定条件，如果条件为true，则执行逻辑，否则，不执行逻辑
WHILE 条件 DO
SQL逻辑...
END WHILE;
```
```
1
2
3
4
```
```
-- A. 定义局部变量, 记录累加之后的值;
-- B. 每循环一次, 就会对n进行减1 , 如果n减到0, 则退出循环
```
```
create procedure p7(in n int)
begin
declare total int default 0 ;
```
```
while n> 0 do
```
```
1 2 3 4 5 6 7 8
```

### 4.2.8 repeat

#### 1). 介绍

repeat是有条件的循环控制语句, 当满足until声明的条件的时候，则退出循环 。具体语法为：

#### 2). 案例

计算从 1 累加到n的值，n为传入的参数值。(使用repeat实现)

```
set total := total + n;
set n := n - 1 ;
end while;
```
```
select total;
end;
```
```
call p7( 100 );
```
```
9
10
11
12
13
14
15
16
```
```
-- 先执行一次逻辑，然后判定UNTIL条件是否满足，如果满足，则退出。如果不满足，则继续下一次循环
REPEAT
SQL逻辑...
UNTIL 条件
END REPEAT;
```
```
1
2
3
4
5
```
```
-- A. 定义局部变量, 记录累加之后的值;
-- B. 每循环一次, 就会对n进行-1 , 如果n减到0, 则退出循环
create procedure p8(in n int)
begin
declare total int default 0 ;
```
```
repeat
set total := total + n;
set n := n - 1 ;
until n <= 0
end repeat;
```
```
select total;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
```

### 4.2.9 loop

#### 1). 介绍

#### LOOP 实现简单的循环，如果不在SQL逻辑中增加退出循环的条件，可以用其来实现简单的死循环。

#### LOOP可以配合一下两个语句使用：

#### LEAVE ：配合循环使用，退出循环。

#### ITERATE：必须用在循环中，作用是跳过当前循环剩下的语句，直接进入下一次循环。

上述语法中出现的 begin_label，end_label，label 指的都是我们所自定义的标记。

#### 2). 案例一

计算从 1 累加到n的值，n为传入的参数值。

```
end;
```
```
call p8( 10 );
call p8( 100 );
```
```
14
15
16
17
```
```
[begin_label:] LOOP
SQL逻辑...
END LOOP [end_label];
```
```
1
2
3
```
```
LEAVE label; -- 退出指定标记的循环体
ITERATE label; -- 直接进入下一次循环
```
```
1
2
```
```
-- A. 定义局部变量, 记录累加之后的值;
-- B. 每循环一次, 就会对n进行-1 , 如果n减到0, 则退出循环 ----> leave xx
```
```
create procedure p9(in n int)
begin
declare total int default 0 ;
```
```
sum:loop
if n<= 0 then
leave sum;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
```

#### 3). 案例二

计算从 1 到n之间的偶数累加的值，n为传入的参数值。

```
end if;
```
```
set total := total + n;
set n := n - 1 ;
end loop sum;
```
```
select total;
end;
```
```
call p9( 100 );
```
```
11
12
13
14
15
16
17
18
19
20
```
```
-- A. 定义局部变量, 记录累加之后的值;
-- B. 每循环一次, 就会对n进行-1 , 如果n减到0, 则退出循环 ----> leave xx
-- C. 如果当次累加的数据是奇数, 则直接进入下一次循环. --------> iterate xx
```
```
create procedure p10(in n int)
begin
declare total int default 0 ;
```
```
sum:loop
if n<= 0 then
leave sum;
end if;
```
```
if n% 2 = 1 then
set n := n - 1 ;
iterate sum;
end if;
```
```
set total := total + n;
set n := n - 1 ;
end loop sum;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
15
16
17
18
19
20
21
22
```

### 4.2.10 游标

#### 1). 介绍

#### 游标（CURSOR）是用来存储查询结果集的数据类型 , 在存储过程和函数中可以使用游标对结果集进

#### 行循环的处理。游标的使用包括游标的声明、OPEN、FETCH 和 CLOSE，其语法分别如下。

#### A. 声明游标

#### B. 打开游标

#### C. 获取游标记录

#### D. 关闭游标

#### 2). 案例

根据传入的参数uage，来查询用户表tb_user中，所有的用户年龄小于等于uage的用户姓名
（name）和专业（profession），并将用户的姓名和专业插入到所创建的一张新表
(id,name,profession)中。

```
select total;
end;
```
```
call p10( 100 );
```
```
23
24
25
26
27
```
```
1 DECLARE 游标名称 CURSOR FOR 查询语句 ;
```
```
1 OPEN 游标名称 ;
```
```
1 FETCH 游标名称 INTO 变量 [, 变量 ] ;
```
```
1 CLOSE 游标名称 ;
```
```
-- 逻辑:
-- A. 声明游标, 存储查询结果集
-- B. 准备: 创建表结构
-- C. 开启游标
-- D. 获取游标中的记录
-- E. 插入数据到新表中
```
```
1 2 3 4 5 6
```

上述的存储过程，最终我们在调用的过程中，会报错，之所以报错是因为上面的while循环中，并没有
退出条件。当游标的数据集获取完毕之后，再次获取数据，就会报错，从而终止了程序的执行。

但是此时，tb_user_pro表结构及其数据都已经插入成功了，我们可以直接刷新表结构，检查表结构
中的数据。

```
-- F. 关闭游标
```
```
create procedure p11(in uage int)
begin
declare uname varchar( 100 );
declare upro varchar( 100 );
declare u_cursor cursor for select name,profession from tb_user where age <=
uage;
```
```
drop table if exists tb_user_pro;
create table if not exists tb_user_pro(
id int primary key auto_increment,
name varchar( 100 ),
profession varchar( 100 )
);
```
```
open u_cursor;
while true do
fetch u_cursor into uname,upro;
insert into tb_user_pro values (null, uname, upro);
end while;
close u_cursor;
```
```
end;
```
```
call p11( 30 );
```
```
7
8
9
10
11
12
13
```
```
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
```

#### 上述的功能，虽然我们实现了，但是逻辑并不完善，而且程序执行完毕，获取不到数据，数据库还报

#### 错。 接下来，我们就需要来完成这个存储过程，并且解决这个问题。

要想解决这个问题，就需要通过MySQL中提供的 条件处理程序 Handler 来解决。

### 4.2.11 条件处理程序

#### 1). 介绍

条件处理程序（Handler）可以用来定义在流程控制结构执行过程中遇到问题时相应的处理步骤。具体
语法为：


#### 2). 案例

#### 我们继续来完成在上一小节提出的这个需求，并解决其中的问题。

根据传入的参数uage，来查询用户表tb_user中，所有的用户年龄小于等于uage的用户姓名
（name）和专业（profession），并将用户的姓名和专业插入到所创建的一张新表
(id,name,profession)中。

A. 通过SQLSTATE指定具体的状态码

```
DECLARE handler_action HANDLER FOR condition_value [, condition_value]
... statement ;
```
```
handler_action 的取值：
CONTINUE: 继续执行当前程序
EXIT: 终止执行当前程序
```
```
condition_value 的取值：
SQLSTATE sqlstate_value: 状态码，如 02000
```
```
SQLWARNING: 所有以 01 开头的SQLSTATE代码的简写
NOT FOUND: 所有以 02 开头的SQLSTATE代码的简写
SQLEXCEPTION: 所有没有被SQLWARNING 或 NOT FOUND捕获的SQLSTATE代码的简写
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
```
```
-- 逻辑:
-- A. 声明游标, 存储查询结果集
-- B. 准备: 创建表结构
-- C. 开启游标
-- D. 获取游标中的记录
-- E. 插入数据到新表中
-- F. 关闭游标
```
```
create procedure p11(in uage int)
begin
declare uname varchar( 100 );
declare upro varchar( 100 );
declare u_cursor cursor for select name,profession from tb_user where age <=
uage;
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
```

#### B. 通过SQLSTATE的代码简写方式 NOT FOUND

#### 02 开头的状态码，代码简写为 NOT FOUND

```
-- 声明条件处理程序 ： 当SQL语句执行抛出的状态码为 02000 时，将关闭游标u_cursor，并退出
declare exit handler for SQLSTATE '02000' close u_cursor;
```
```
drop table if exists tb_user_pro;
create table if not exists tb_user_pro(
id int primary key auto_increment,
name varchar( 100 ),
profession varchar( 100 )
);
```
```
open u_cursor;
while true do
fetch u_cursor into uname,upro;
insert into tb_user_pro values (null, uname, upro);
end while;
close u_cursor;
```
```
end;
```
```
call p11( 30 );
```
```
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
```
```
create procedure p12(in uage int)
begin
declare uname varchar( 100 );
declare upro varchar( 100 );
declare u_cursor cursor for select name,profession from tb_user where age <=
uage;
-- 声明条件处理程序 ： 当SQL语句执行抛出的状态码为 02 开头时，将关闭游标u_cursor，并退出
declare exit handler for not found close u_cursor;
```
```
drop table if exists tb_user_pro;
create table if not exists tb_user_pro(
id int primary key auto_increment,
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
```

#### 具体的错误状态码，可以参考官方文档：

```
https://dev.mysql.com/doc/refman/8.0/en/declare-handler.html
https://dev.mysql.com/doc/mysql-errors/8.0/en/server-error-reference.html
```
## 4.3 存储函数

#### 1). 介绍

#### 存储函数是有返回值的存储过程，存储函数的参数只能是IN类型的。具体语法如下：

characteristic说明：

```
DETERMINISTIC：相同的输入参数总是产生相同的结果
```
```
name varchar( 100 ),
profession varchar( 100 )
);
```
```
open u_cursor;
while true do
fetch u_cursor into uname,upro;
insert into tb_user_pro values (null, uname, upro);
end while;
close u_cursor;
```
```
end;
```
```
call p12( 30 );
```
```
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
```
```
CREATE FUNCTION 存储函数名称 ([ 参数列表 ])
RETURNS type [characteristic ...]
BEGIN
-- SQL语句
RETURN ...;
END ;
```
```
1 2 3 4 5 6
```

#### NO SQL ：不包含 SQL 语句。

#### READS SQL DATA：包含读取数据的语句，但不包含写入数据的语句。

#### 2). 案例

计算从 1 累加到n的值，n为传入的参数值。

在mysql8.0版本中binlog默认是开启的，一旦开启了，mysql就要求在定义存储过程时，需要指定
characteristic特性，否则就会报如下错误：

## 4.4 触发器

### 4.4.1 介绍

触发器是与表有关的数据库对象，指在insert/update/delete之前(BEFORE)或之后(AFTER)，触
发并执行触发器中定义的SQL语句集合。触发器的这种特性可以协助应用在数据库端确保数据的完整性
, 日志记录 , 数据校验等操作 。

使用别名OLD和NEW来引用触发器中发生变化的记录内容，这与其他的数据库是相似的。现在触发器还
只支持行级触发，不支持语句级触发。

```
create function fun1(n int)
returns int deterministic
begin
declare total int default 0 ;
```
```
while n> 0 do
set total := total + n;
set n := n - 1 ;
end while;
```
```
return total;
end;
```
```
select fun1( 50 );
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
14
```

#### 触发器类型 NEW 和 OLD

#### INSERT 型触发器 NEW 表示将要或者已经新增的数据

#### UPDATE 型触发器 OLD 表示修改之前的数据 , NEW 表示将要或已经修改后的数据

#### DELETE 型触发器 OLD 表示将要或者已经删除的数据

### 4.4.2 语法

#### 1). 创建

#### 2). 查看

#### 3). 删除

### 4.4.3 案例

通过触发器记录 tb_user 表的数据变更日志，将变更日志插入到日志表user_logs中, 包含增加,
修改 , 删除 ;

表结构准备:

```
CREATE TRIGGER trigger_name
BEFORE/AFTER INSERT/UPDATE/DELETE
ON tbl_name FOR EACH ROW -- 行级触发器
BEGIN
trigger_stmt ;
END;
```
```
1 2 3 4 5 6
```
```
1 SHOW TRIGGERS ;
```
```
DROP TRIGGER [schema_name.]trigger_name ; -- 如果没有指定 schema_name，默认为当前数
据库 。
```
```
1
```

#### A. 插入数据触发器

#### 测试:

#### 测试完毕之后，检查日志表中的数据是否可以正常插入，以及插入数据的正确性。

#### B. 修改数据触发器

```
-- 准备工作 : 日志表 user_logs
create table user_logs(
id int( 11 ) not null auto_increment,
operation varchar( 20 ) not null comment '操作类型, insert/update/delete',
operate_time datetime not null comment '操作时间',
operate_id int( 11 ) not null comment '操作的ID',
operate_params varchar( 500 ) comment '操作参数',
primary key(`id`)
)engine=innodb default charset=utf8;
```
```
1 2 3 4 5 6 7 8 9
```
```
create trigger tb_user_insert_trigger
after insert on tb_user for each row
begin
insert into user_logs(id, operation, operate_time, operate_id, operate_params)
VALUES
(null, 'insert', now(), new.id, concat('插入的数据内容为:
id=',new.id,',name=',new.name, ', phone=', NEW.phone, ', email=', NEW.email, ',
profession=', NEW.profession));
end;
```
```
1 2 3 4 5 6
```
```
-- 查看
show triggers ;
```
```
-- 插入数据到tb_user
insert into tb_user(id, name, phone, email, profession, age, gender, status,
createtime) VALUES ( 26 ,'三皇子','18809091212','erhuangzi@163.com','软件工
程', 23 ,'1','1',now());
```
```
1
2
3
4
5
```

#### 测试:

#### 测试完毕之后，检查日志表中的数据是否可以正常插入，以及插入数据的正确性。

#### C. 删除数据触发器

```
create trigger tb_user_update_trigger
after update on tb_user for each row
begin
insert into user_logs(id, operation, operate_time, operate_id, operate_params)
VALUES
(null, 'update', now(), new.id,
concat('更新之前的数据: id=',old.id,',name=',old.name, ', phone=',
old.phone, ', email=', old.email, ', profession=', old.profession,
' | 更新之后的数据: id=',new.id,',name=',new.name, ', phone=',
NEW.phone, ', email=', NEW.email, ', profession=', NEW.profession));
end;
```
```
1 2 3 4 5 6 7 8
```
```
-- 查看
show triggers ;
```
```
-- 更新
update tb_user set profession = '会计' where id = 23 ;
update tb_user set profession = '会计' where id <= 5 ;
```
```
1 2 3 4 5 6
```
```
create trigger tb_user_delete_trigger
after delete on tb_user for each row
begin
insert into user_logs(id, operation, operate_time, operate_id, operate_params)
VALUES
(null, 'delete', now(), old.id,
concat('删除之前的数据: id=',old.id,',name=',old.name, ', phone=',
old.phone, ', email=', old.email, ', profession=', old.profession));
end;
```
```
1 2 3 4 5 6 7
```

#### 测试:

#### 测试完毕之后，检查日志表中的数据是否可以正常插入，以及插入数据的正确性。

# 5. 锁

## 5.1 概述

#### 锁是计算机协调多个进程或线程并发访问某一资源的机制。在数据库中，除传统的计算资源（CPU、

#### RAM、I/O）的争用以外，数据也是一种供许多用户共享的资源。如何保证数据并发访问的一致性、有

#### 效性是所有数据库必须解决的一个问题，锁冲突也是影响数据库并发访问性能的一个重要因素。从这个

#### 角度来说，锁对数据库而言显得尤其重要，也更加复杂。

MySQL中的锁，按照锁的粒度分，分为以下三类：

```
全局锁：锁定数据库中的所有表。
表级锁：每次操作锁住整张表。
行级锁：每次操作锁住对应的行数据。
```
## 5.2 全局锁

## 5.2.1 介绍

#### 全局锁就是对整个数据库实例加锁，加锁后整个实例就处于只读状态，后续的DML的写语句，DDL语

#### 句，已经更新操作的事务提交语句都将被阻塞。

#### 其典型的使用场景是做全库的逻辑备份，对所有的表进行锁定，从而获取一致性视图，保证数据的完整

#### 性。

#### 为什么全库逻辑备份，就需要加全就锁呢？

```
-- 查看
show triggers ;
```
```
-- 删除数据
delete from tb_user where id = 26 ;
```
```
1
2
3
4
5
```

#### A. 我们一起先来分析一下不加全局锁，可能存在的问题。

假设在数据库中存在这样三张表: tb_stock 库存表，tb_order 订单表，tb_orderlog 订单日
志表。

```
在进行数据备份时，先备份了tb_stock库存表。
然后接下来，在业务系统中，执行了下单操作，扣减库存，生成订单（更新tb_stock表，插入
tb_order表）。
然后再执行备份 tb_order表的逻辑。
业务中执行插入订单日志操作。
最后，又备份了tb_orderlog表。
```
此时备份出来的数据，是存在问题的。因为备份出来的数据，tb_stock表与tb_order表的数据不一
致(有最新操作的订单信息,但是库存数没减)。

那如何来规避这种问题呢? 此时就可以借助于MySQL的全局锁来解决。

#### B. 再来分析一下加了全局锁后的情况

#### 对数据库进行进行逻辑备份之前，先对整个数据库加上全局锁，一旦加了全局锁之后，其他的DDL、

#### DML全部都处于阻塞状态，但是可以执行DQL语句，也就是处于只读状态，而数据备份就是查询操作。

#### 那么数据在进行逻辑备份的过程中，数据库中的数据就是不会发生变化的，这样就保证了数据的一致性

#### 和完整性。


### 5.2.2 语法

#### 1). 加全局锁

#### 2). 数据备份

数据备份的相关指令, 在后面MySQL管理章节, 还会详细讲解.

3). 释放锁

### 5.2.3 特点

#### 数据库中加全局锁，是一个比较重的操作，存在以下问题：

#### 如果在主库上备份，那么在备份期间都不能执行更新，业务基本上就得停摆。

```
如果在从库上备份，那么在备份期间从库不能执行主库同步过来的二进制日志（binlog），会导
致主从延迟。
```
在InnoDB引擎中，我们可以在备份时加上参数 --single-transaction 参数来完成不加锁的一致
性数据备份。

## 5.3 表级锁

### 5.3.1 介绍

表级锁，每次操作锁住整张表。锁定粒度大，发生锁冲突的概率最高，并发度最低。应用在MyISAM、
InnoDB、BDB等存储引擎中。

对于表级锁，主要分为以下三类：

```
表锁
元数据锁（meta data lock，MDL）
意向锁
```
```
1 flush tables with read lock ;
```
```
1 mysqldump -uroot –p1234 itcast > itcast.sql
```
```
1 unlock tables ;
```
```
1 mysqldump --single-transaction -uroot –p123456 itcast > itcast.sql
```

### 5.3.2 表锁

#### 对于表锁，分为两类：

```
表共享读锁（read lock）
表独占写锁（write lock）
```
#### 语法：

```
加锁：lock tables 表名... read/write。
释放锁：unlock tables / 客户端断开连接 。
```
#### 特点:

#### A. 读锁

#### 左侧为客户端一，对指定表加了读锁，不会影响右侧客户端二的读，但是会阻塞右侧客户端的写。

#### 测试:

#### B. 写锁


#### 左侧为客户端一，对指定表加了写锁，会阻塞右侧客户端的读和写。

#### 测试:

#### 结论: 读锁不会阻塞其他客户端的读，但是会阻塞写。写锁既会阻塞其他客户端的读，又会阻塞

#### 其他客户端的写。

### 5.3.3 元数据锁

meta data lock , 元数据锁，简写MDL。

MDL加锁过程是系统自动控制，无需显式使用，在访问一张表的时候会自动加上。MDL锁主要作用是维
护表元数据的数据一致性，在表上有活动事务的时候，不可以对元数据进行写入操作。 **为了避免DML与
DDL冲突，保证读写的正确性。**

这里的元数据，大家可以简单理解为就是一张表的表结构。 也就是说，某一张表涉及到未提交的事务
时，是不能够修改这张表的表结构的。

在MySQL5.5中引入了MDL，当对一张表进行增删改查的时候，加MDL读锁(共享)；当对表结构进行变
更操作的时候，加MDL写锁(排他)。

常见的SQL操作时，所添加的元数据锁：


#### 对应SQL 锁类型 说明

```
lock tables xxx read /
write
```
#### SHARED_READ_ONLY /

#### SHARED_NO_READ_WRITE

```
select 、select ...
lock in share mode SHARED_READ
```
#### 与SHARED_READ、

#### SHARED_WRITE兼容，与

#### EXCLUSIVE互斥

```
insert 、update、
delete、select ... for
update
```
#### SHARED_WRITE

#### 与SHARED_READ、

#### SHARED_WRITE兼容，与

#### EXCLUSIVE互斥

```
alter table ... EXCLUSIVE 与其他的MDL都互斥
```
#### 演示：

#### 当执行SELECT、INSERT、UPDATE、DELETE等语句时，添加的是元数据共享锁（SHARED_READ /

#### SHARED_WRITE），之间是兼容的。

#### 当执行SELECT语句时，添加的是元数据共享锁（SHARED_READ），会阻塞元数据排他锁

#### （EXCLUSIVE），之间是互斥的。

#### 我们可以通过下面的SQL，来查看数据库中的元数据锁的情况：


#### 我们在操作过程中，可以通过上述的SQL语句，来查看元数据锁的加锁情况。

### 5.3.4 意向锁

#### 1). 介绍

为了避免DML在执行时，加的行锁与表锁的冲突，在InnoDB中引入了意向锁，使得表锁不用检查每行
数据是否加锁，使用意向锁来减少表锁的检查。

#### 假如没有意向锁，客户端一对表加了行锁后，客户端二如何给表加表锁呢，来通过示意图简单分析一

#### 下：

#### 首先客户端一，开启一个事务，然后执行DML操作，在执行DML语句时，会对涉及到的行加行锁。

#### 当客户端二，想对这张表加表锁时，会检查当前表是否有对应的行锁，如果没有，则添加表锁，此时就

#### 会从第一行数据，检查到最后一行数据，效率较低。

```
select object_type,object_schema,object_name,lock_type,lock_duration from
performance_schema.metadata_locks ;
```
```
1
```

#### 有了意向锁之后 :

#### 客户端一，在执行DML操作时，会对涉及的行加行锁，同时也会对该表加上意向锁。

#### 而其他客户端，在对这张表加表锁的时候，会根据该表上所加的意向锁来判定是否可以成功加表锁，而

#### 不用逐行判断行锁情况了。

#### 2). 分类


```
意向共享锁(IS): 由语句select ... lock in share mode添加 。 与 表锁共享锁
(read)兼容，与表锁排他锁(write)互斥。
意向排他锁(IX): 由insert、update、delete、select...for update添加 。与表锁共
享锁(read)及排他锁(write)都互斥，意向锁之间不会互斥。
```
```
一旦事务提交了，意向共享锁、意向排他锁，都会自动释放。
```
#### 可以通过以下SQL，查看意向锁及行锁的加锁情况：

#### 演示：

#### A. 意向共享锁与表读锁是兼容的

#### B. 意向排他锁与表读锁、写锁都是互斥的

## 5.4 行级锁

### 5.4.1 介绍

```
select object_schema,object_name,index_name,lock_type,lock_mode,lock_data from
performance_schema.data_locks;
```
```
1
```

#### 行级锁，每次操作锁住对应的行数据。锁定粒度最小，发生锁冲突的概率最低，并发度最高。应用在

InnoDB存储引擎中。

InnoDB的数据是基于索引组织的，行锁是通过对索引上的索引项加锁来实现的，而不是对记录加的
锁。对于行级锁，主要分为以下三类：

```
行锁（Record Lock）：锁定单个行记录的锁，防止其他事务对此行进行update和delete。在
RC、RR隔离级别下都支持。
```
```
间隙锁（Gap Lock）：锁定索引记录间隙（不含该记录），确保索引记录间隙不变，防止其他事
务在这个间隙进行insert，产生幻读。在RR隔离级别下都支持。
```
```
临键锁（Next-Key Lock）：行锁和间隙锁组合，同时锁住数据，并锁住数据前面的间隙Gap。
在RR隔离级别下支持。
```
### 5.4.2 行锁

#### 1). 介绍

InnoDB实现了以下两种类型的行锁：

```
共享锁（S）：允许一个事务去读一行，阻止其他事务获得相同数据集的排它锁。
排他锁（X）：允许获取排他锁的事务更新数据，阻止其他事务获得相同数据集的共享锁和排他
锁。
```
#### 两种行锁的兼容情况如下:


#### SQL 行锁类型 说明

#### INSERT ... 排他锁 自动加锁

#### UPDATE ... 排他锁 自动加锁

#### DELETE ... 排他锁 自动加锁

#### SELECT（正常）

#### 不加任何

#### 锁^

#### SELECT ... LOCK IN SHARE

#### MODE

#### 共享锁 需要手动在SELECT之后加LOCK IN SHARE

#### MODE

#### SELECT ... FOR UPDATE 排他锁 需要手动在SELECT之后加FOR UPDATE

#### 常见的SQL语句，在执行时，所加的行锁如下：

#### 2). 演示

默认情况下，InnoDB在 REPEATABLE READ事务隔离级别运行，InnoDB使用 next-key 锁进行搜
索和索引扫描，以防止幻读。

```
针对唯一索引进行检索时，对已存在的记录进行等值匹配时，将会自动优化为行锁。
InnoDB的行锁是针对于索引加的锁，不通过索引条件检索数据，那么InnoDB将对表中的所有记
录加锁，此时 就会升级为表锁。
```
#### 可以通过以下SQL，查看意向锁及行锁的加锁情况：

#### 示例演示

#### 数据准备:

```
select object_schema,object_name,index_name,lock_type,lock_mode,lock_data from
performance_schema.data_locks;
```
```
1
```

#### 演示行锁的时候，我们就通过上面这张表来演示一下。

A. 普通的select语句，执行时，不会加锁。

B. select...lock in share mode，加共享锁，共享锁与共享锁之间兼容。

```
CREATE TABLE `stu` (
`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` varchar( 255 ) DEFAULT NULL,
`age` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4;
```
```
INSERT INTO `stu` VALUES ( 1 , 'tom', 1 );
INSERT INTO `stu` VALUES ( 3 , 'cat', 3 );
INSERT INTO `stu` VALUES ( 8 , 'rose', 8 );
INSERT INTO `stu` VALUES ( 11 , 'jetty', 11 );
INSERT INTO `stu` VALUES ( 19 , 'lily', 19 );
INSERT INTO `stu` VALUES ( 25 , 'luci', 25 );
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
```

#### 共享锁与排他锁之间互斥。

客户端一获取的是id为 1 这行的共享锁，客户端二是可以获取id为 3 这行的排它锁的，因为不是同一行
数据。 而如果客户端二想获取id为 1 这行的排他锁，会处于阻塞状态，以为共享锁与排他锁之间互
斥。

#### C. 排它锁与排他锁之间互斥


当客户端一，执行update语句，会为id为 1 的记录加排他锁； 客户端二，如果也执行update语句更
新id为 1 的数据，也要为id为 1 的数据加排他锁，但是客户端二会处于阻塞状态，因为排他锁之间是互
斥的。 直到客户端一，把事务提交了，才会把这一行的行锁释放，此时客户端二，解除阻塞。

#### D. 无索引行锁升级为表锁

stu表中数据如下:

#### 我们在两个客户端中执行如下操作:

在客户端一中，开启事务，并执行update语句，更新name为Lily的数据，也就是id为 19 的记录 。
然后在客户端二中更新id为 3 的记录，却不能直接执行，会处于阻塞状态，为什么呢？

原因就是因为此时，客户端一，根据name字段进行更新时，name字段是没有索引的，如果没有索引，
此时行锁会升级为表锁(因为行锁是对索引项加的锁，而name没有索引)。

接下来，我们再针对name字段建立索引，索引建立之后，再次做一个测试：


此时我们可以看到，客户端一，开启事务，然后依然是根据name进行更新。而客户端二，在更新id为 3
的数据时，更新成功，并未进入阻塞状态。 这样就说明，我们根据索引字段进行更新操作，就可以避
免行锁升级为表锁的情况。

### 5.4.3 间隙锁&临键锁

默认情况下，InnoDB在 REPEATABLE READ事务隔离级别运行，InnoDB使用 next-key 锁进行搜
索和索引扫描，以防止幻读。

```
索引上的等值查询(唯一索引)，给不存在的记录加锁时, 优化为间隙锁 。
索引上的等值查询(非唯一普通索引)，向右遍历时最后一个值不满足查询需求时，next-key
lock 退化为间隙锁。
索引上的范围查询(唯一索引)--会访问到不满足条件的第一个值为止。
```
#### 注意：间隙锁唯一目的是防止其他事务插入间隙。间隙锁可以共存，一个事务采用的间隙锁不会

#### 阻止另一个事务在同一间隙上采用间隙锁。

#### 示例演示

#### A. 索引上的等值查询(唯一索引)，给不存在的记录加锁时, 优化为间隙锁 。


B. 索引上的等值查询(非唯一普通索引)，向右遍历时最后一个值不满足查询需求时，next-key
lock 退化为间隙锁。

介绍分析一下：

我们知道InnoDB的B+树索引，叶子节点是有序的双向链表。 假如，我们要根据这个二级索引查询值
为 18 的数据，并加上共享锁，我们是只锁定 18 这一行就可以了吗？ 并不是，因为是非唯一索引，这个
结构中可能有多个 18 的存在，所以，在加锁时会继续往后找，找到一个不满足条件的值（当前案例中也
就是 29 ）。此时会对 18 加临键锁，并对 29 之前的间隙加锁。

#### C. 索引上的范围查询(唯一索引)--会访问到不满足条件的第一个值为止。


查询的条件为id>=19，并添加共享锁。 此时我们可以根据数据库表中现有的数据，将数据分为三个部
分：

[19]

(19,25]

(25,+∞]

所以数据库数据在加锁是，就是将 19 加了行锁， 25 的临键锁（包含 25 及 25 之前的间隙），正无穷的临
键锁(正无穷及之前的间隙)。

# 6. InnoDB引擎

## 6.1 逻辑存储结构

InnoDB的逻辑存储结构如下图所示:

#### 1). 表空间


表空间是InnoDB存储引擎逻辑结构的最高层， 如果用户启用了参数 innodb_file_per_table(在
8.0版本中默认开启) ，则每张表都会有一个表空间（xxx.ibd），一个mysql实例可以对应多个表空
间，用于存储记录、索引等数据。

2). 段

段，分为数据段（Leaf node segment）、索引段（Non-leaf node segment）、回滚段
（Rollback segment），InnoDB是索引组织表，数据段就是B+树的叶子节点， 索引段即为B+树的
非叶子节点。段用来管理多个Extent（区）。

3). 区

区，表空间的单元结构，每个区的大小为1M。 默认情况下， InnoDB存储引擎页大小为16K， 即一
个区中一共有 64 个连续的页。

4). 页

页，是InnoDB 存储引擎磁盘管理的最小单元，每个页的大小默认为 16KB。为了保证页的连续性，
InnoDB 存储引擎每次从磁盘申请 4-5 个区。

5). 行

行，InnoDB 存储引擎数据是按行进行存放的。

在行中，默认有两个隐藏字段：

```
Trx_id：每次对某条记录进行改动时，都会把对应的事务id赋值给trx_id隐藏列。
Roll_pointer：每次对某条引记录进行改动时，都会把旧的版本写入到undo日志中，然后这个
隐藏列就相当于一个指针，可以通过它来找到该记录修改前的信息。
```
## 6.2 架构

### 6.2.1 概述

MySQL5.5 版本开始，默认使用InnoDB存储引擎，它擅长事务处理，具有崩溃恢复特性，在日常开发
中使用非常广泛。下面是InnoDB架构图，左侧为内存结构，右侧为磁盘结构。


### 6.2.2 内存结构

在左侧的内存结构中，主要分为这么四大块儿： Buffer Pool、Change Buffer、Adaptive
Hash Index、Log Buffer。 接下来介绍一下这四个部分。


1). Buffer Pool

InnoDB存储引擎基于磁盘文件存储，访问物理硬盘和在内存中进行访问，速度相差很大，为了尽可能
弥补这两者之间的I/O效率的差值，就需要把经常使用的数据加载到缓冲池中，避免每次访问都进行磁
盘I/O。

在InnoDB的缓冲池中不仅缓存了索引页和数据页，还包含了undo页、插入缓存、自适应哈希索引以及
InnoDB的锁信息等等。

缓冲池 Buffer Pool，是主内存中的一个区域，里面可以缓存磁盘上经常操作的真实数据，在执行增
删改查操作时，先操作缓冲池中的数据（若缓冲池没有数据，则从磁盘加载并缓存），然后再以一定频
率刷新到磁盘，从而减少磁盘IO，加快处理速度。

缓冲池以Page页为单位，底层采用链表数据结构管理Page。根据状态，将Page分为三种类型：

- free page：空闲page，未被使用。
- clean page：被使用page，数据没有被修改过。
- dirty page：脏页，被使用page，数据被修改过，也中数据与磁盘的数据产生了不一致。

在专用服务器上，通常将多达 80 ％的物理内存分配给缓冲池 。参数设置： show variables
like 'innodb_buffer_pool_size';

2). Change Buffer

Change Buffer，更改缓冲区（针对于非唯一二级索引页），在执行DML语句时，如果这些数据Page
没有在Buffer Pool中，不会直接操作磁盘，而会将数据变更存在更改缓冲区 Change Buffer
中，在未来数据被读取时，再将数据合并恢复到Buffer Pool中，再将合并后的数据刷新到磁盘中。

Change Buffer的意义是什么呢?

先来看一幅图，这个是二级索引的结构图：


#### 与聚集索引不同，二级索引通常是非唯一的，并且以相对随机的顺序插入二级索引。同样，删除和更新

#### 可能会影响索引树中不相邻的二级索引页，如果每一次都操作磁盘，会造成大量的磁盘IO。有了

ChangeBuffer之后，我们可以在缓冲池中进行合并处理，减少磁盘IO。

3). Adaptive Hash Index

自适应hash索引，用于优化对Buffer Pool数据的查询。MySQL的innoDB引擎中虽然没有直接支持
hash索引，但是给我们提供了一个功能就是这个自适应hash索引。因为前面我们讲到过，hash索引在
进行等值匹配时，一般性能是要高于B+树的，因为hash索引一般只需要一次IO即可，而B+树，可能需
要几次匹配，所以hash索引的效率要高，但是hash索引又不适合做范围查询、模糊匹配等。

InnoDB存储引擎会监控对表上各索引页的查询，如果观察到在特定的条件下hash索引可以提升速度，
则建立hash索引，称之为自适应hash索引。

```
自适应哈希索引，无需人工干预，是系统根据情况自动完成。
```
参数： adaptive_hash_index

4). Log Buffer

Log Buffer：日志缓冲区，用来保存要写入到磁盘中的log日志数据（redo log 、undo log），
默认大小为 16MB，日志缓冲区的日志会定期刷新到磁盘中。如果需要更新、插入或删除许多行的事
务，增加日志缓冲区的大小可以节省磁盘 I/O。

参数:

innodb_log_buffer_size：缓冲区大小

innodb_flush_log_at_trx_commit：日志刷新到磁盘时机，取值主要包含以下三个：

1: 日志在每次事务提交时写入并刷新到磁盘，默认值。

0: 每秒将日志写入并刷新到磁盘一次。


#### 2: 日志在每次事务提交后写入，并每秒刷新到磁盘一次。

### 6.2.3 磁盘结构

接下来，再来看看InnoDB体系结构的右边部分，也就是磁盘结构：

1). System Tablespace

系统表空间是更改缓冲区的存储区域。如果表是在系统表空间而不是每个表文件或通用表空间中创建
的，它也可能包含表和索引数据。(在MySQL5.x版本中还包含InnoDB数据字典、undolog等)

参数：innodb_data_file_path

系统表空间，默认的文件名叫 ibdata1。

2). File-Per-Table Tablespaces


如果开启了innodb_file_per_table开关 ，则每个表的文件表空间包含单个InnoDB表的数据和索
引 ，并存储在文件系统上的单个数据文件中。

开关参数：innodb_file_per_table ，该参数默认开启。

#### 那也就是说，我们没创建一个表，都会产生一个表空间文件，如图：

3). General Tablespaces

通用表空间，需要通过 CREATE TABLESPACE 语法创建通用表空间，在创建表时，可以指定该表空
间。

A. 创建表空间

#### B. 创建表时指定表空间

4). Undo Tablespaces

撤销表空间，MySQL实例在初始化时会自动创建两个默认的undo表空间（初始大小16M），用于存储
undo log日志。

5). Temporary Tablespaces

InnoDB 使用会话临时表空间和全局临时表空间。存储用户创建的临时表等数据。

6). Doublewrite Buffer Files

```
1 CREATE TABLESPACE ts_name ADD DATAFILE 'file_name' ENGINE = engine_name;
```
```
1 CREATE TABLE xxx ... TABLESPACE ts_name;
```

双写缓冲区，innoDB引擎将数据页从Buffer Pool刷新到磁盘前，先将数据页写入双写缓冲区文件
中，便于系统异常时恢复数据。

7). Redo Log

重做日志，是用来实现事务的持久性。该日志文件由两部分组成：重做日志缓冲（redo log
buffer）以及重做日志文件（redo log）,前者是在内存中，后者在磁盘中。当事务提交之后会把所
有修改信息都会存到该日志中, 用于在刷新脏页到磁盘时,发生错误时, 进行数据恢复使用。

以循环方式写入重做日志文件，涉及两个文件：

前面我们介绍了InnoDB的内存结构，以及磁盘结构，那么内存中我们所更新的数据，又是如何到磁盘
中的呢？ 此时，就涉及到一组后台线程，接下来，就来介绍一些InnoDB中涉及到的后台线程。

### 6.2.4 后台线程


#### 线程类型 默认个数 职责

```
Read thread 4 负责读操作
Write thread 4 负责写操作
Log thread 1 负责将日志缓冲区刷新到磁盘
Insert buffer thread 1 负责将写缓冲区内容刷新到磁盘
```
在InnoDB的后台线程中，分为 4 类，分别是：Master Thread 、IO Thread、Purge Thread、
Page Cleaner Thread。

1). Master Thread

核心后台线程，负责调度其他线程，还负责将缓冲池中的数据异步刷新到磁盘中, 保持数据的一致性，
还包括脏页的刷新、合并插入缓存、undo页的回收 。

2). IO Thread

在InnoDB存储引擎中大量使用了AIO来处理IO请求, 这样可以极大地提高数据库的性能，而IO
Thread主要负责这些IO请求的回调。

我们可以通过以下的这条指令，查看到InnoDB的状态信息，其中就包含IO Thread信息。


3). Purge Thread

主要用于回收事务已经提交了的undo log，在事务提交之后，undo log可能不用了，就用它来回
收。

4). Page Cleaner Thread

协助 Master Thread 刷新脏页到磁盘的线程，它可以减轻 Master Thread 的工作压力，减少阻
塞。

## 6.3 事务原理

### 6.3.1 事务基础

#### 1). 事务

#### 事务 是一组操作的集合，它是一个不可分割的工作单位，事务会把所有的操作作为一个整体一起向系

#### 统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败。

#### 2). 特性

```
1 show engine innodb status \G;
```

- 原子性（Atomicity）：事务是不可分割的最小操作单元，要么全部成功，要么全部失败。
- 一致性（Consistency）：事务完成时，必须使所有的数据都保持一致状态。
- 隔离性（Isolation）：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环
境下运行。
- 持久性（Durability）：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。

那实际上，我们研究事务的原理，就是研究MySQL的InnoDB引擎是如何保证事务的这四大特性的。

而对于这四大特性，实际上分为两个部分。 其中的原子性、一致性、持久化，实际上是由InnoDB中的
两份日志来保证的，一份是redo log日志，一份是undo log日志。 而持久性是通过数据库的锁，
加上MVCC来保证的。

我们在讲解事务原理的时候，主要就是来研究一下redolog，undolog以及MVCC。

### 6.3.2 redo log

#### 重做日志，记录的是事务提交时数据页的物理修改，是用来实现事务的持久性。

该日志文件由两部分组成：重做日志缓冲（redo log buffer）以及重做日志文件（redo log
file）,前者是在内存中，后者在磁盘中。当事务提交之后会把所有修改信息都存到该日志文件中, 用
于在刷新脏页到磁盘,发生错误时, 进行数据恢复使用。

如果没有redolog，可能会存在什么问题的？ 我们一起来分析一下。


我们知道，在InnoDB引擎中的内存结构中，主要的内存区域就是缓冲池，在缓冲池中缓存了很多的数
据页。 当我们在一个事务中，执行多个增删改的操作时，InnoDB引擎会先操作缓冲池中的数据，如果
缓冲区没有对应的数据，会通过后台线程将磁盘中的数据加载出来，存放在缓冲区中，然后将缓冲池中
的数据修改，修改后的数据页我们称为脏页。 而脏页则会在一定的时机，通过后台线程刷新到磁盘
中，从而保证缓冲区与磁盘的数据一致。 而缓冲区的脏页数据并不是实时刷新的，而是一段时间之后
将缓冲区的数据刷新到磁盘中，假如刷新到磁盘的过程出错了，而提示给用户事务提交成功，而数据却
没有持久化下来，这就出现问题了，没有保证事务的持久性。

那么，如何解决上述的问题呢？ 在InnoDB中提供了一份日志 redo log，接下来我们再来分析一
下，通过redolog如何解决这个问题。

有了redolog之后，当对缓冲区的数据进行增删改之后，会首先将操作的数据页的变化，记录在redo
log buffer中。在事务提交时，会将redo log buffer中的数据刷新到redo log磁盘文件中。
过一段时间之后，如果刷新缓冲区的脏页到磁盘时，发生错误，此时就可以借助于redo log进行数据
恢复，这样就保证了事务的持久性。 而如果脏页成功刷新到磁盘 或 或者涉及到的数据已经落盘，此
时redolog就没有作用了，就可以删除了，所以存在的两个redolog文件是循环写的。


那为什么每一次提交事务，要刷新redo log 到磁盘中呢，而不是直接将buffer pool中的脏页刷新
到磁盘呢?

因为在业务操作中，我们操作数据一般都是随机读写磁盘的，而不是顺序读写磁盘。 而redo log在
往磁盘文件中写入数据，由于是日志文件，所以都是顺序写的。顺序写的效率，要远大于随机写。 这
种先写日志的方式，称之为 WAL（Write-Ahead Logging）。

### 6.3.3 undo log

#### 回滚日志，用于记录数据被修改前的信息 , 作用包含两个 : 提供回滚(保证事务的原子性) 和

#### MVCC(多版本并发控制) 。

undo log和redo log记录物理日志不一样，它是逻辑日志。可以认为当delete一条记录时，undo
log中会记录一条对应的insert记录，反之亦然，当update一条记录时，它记录一条对应相反的
update记录。当执行rollback时，就可以从undo log中的逻辑记录读取到相应的内容并进行回滚。

Undo log销毁：undo log在事务执行时产生，事务提交时，并不会立即删除undo log，因为这些
日志可能还用于MVCC。

Undo log存储：undo log采用段的方式进行管理和记录，存放在前面介绍的 rollback segment
回滚段中，内部包含 1024 个undo log segment。

## 6.4 MVCC

### 6.4.1 基本概念

#### 1). 当前读

#### 读取的是记录的最新版本，读取时还要保证其他并发事务不能修改当前记录，会对读取的记录进行加

锁。对于我们日常的操作，如：select ... lock in share mode(共享锁)，select ...
for update、update、insert、delete(排他锁)都是一种当前读。

#### 测试：


#### 在测试中我们可以看到，即使是在默认的RR隔离级别下，事务A中依然可以读取到事务B最新提交的内

容，因为在查询语句后面加上了 lock in share mode 共享锁，此时是当前读操作。当然，当我们
加排他锁的时候，也是当前读操作。

#### 2). 快照读

简单的select（不加锁）就是快照读，快照读，读取的是记录数据的可见版本，有可能是历史数据，
不加锁，是非阻塞读。

- Read Committed：每次select，都生成一个快照读。
- Repeatable Read：开启事务后第一个select语句才是快照读的地方。
- Serializable：快照读会退化为当前读。

#### 测试:


#### 隐藏字段 含义

#### DB_TRX_ID 最近修改事务ID，记录插入这条记录或最后一次修改该记录的事务ID。

#### DB_ROLL_PTR

```
回滚指针，指向这条记录的上一个版本，用于配合undo log，指向上一个版
本。
DB_ROW_ID 隐藏主键，如果表结构没有指定主键，将会生成该隐藏字段。
```
在测试中,我们看到即使事务B提交了数据,事务A中也查询不到。 原因就是因为普通的select是快照
读，而在当前默认的RR隔离级别下，开启事务后第一个select语句才是快照读的地方，后面执行相同
的select语句都是从快照中获取数据，可能不是当前的最新数据，这样也就保证了可重复读。

#### 3). MVCC

全称 Multi-Version Concurrency Control，多版本并发控制。指维护一个数据的多个版本，
使得读写操作没有冲突，快照读为MySQL实现MVCC提供了一个非阻塞读功能。MVCC的具体实现，还需
要依赖于数据库记录中的三个隐式字段、undo log日志、readView。

接下来，我们再来介绍一下InnoDB引擎的表中涉及到的隐藏字段 、undolog 以及 readview，从
而来介绍一下MVCC的原理。

### 6.4.2 隐藏字段

#### 6.4.2.1 介绍

#### 当我们创建了上面的这张表，我们在查看表结构的时候，就可以显式的看到这三个字段。 实际上除了

这三个字段以外，InnoDB还会自动的给我们添加三个隐藏字段及其含义分别是：

#### 而上述的前两个字段是肯定会添加的， 是否添加最后一个字段DB_ROW_ID，得看当前表有没有主键，

#### 如果有主键，则不会添加该隐藏字段。

#### 6.4.2.2 测试

1). 查看有主键的表 stu


进入服务器中的 /var/lib/mysql/itcast/ , 查看stu的表结构信息, 通过如下指令:

查看到的表结构信息中，有一栏 columns，在其中我们会看到处理我们建表时指定的字段以外，还有
额外的两个字段 分别是：DB_TRX_ID 、 DB_ROLL_PTR ，因为该表有主键，所以没有DB_ROW_ID
隐藏字段。

```
1 ibd2sdi stu.ibd
```


2). 查看没有主键的表 employee

建表语句：

#### 此时，我们再通过以下指令来查看表结构及其其中的字段信息：

查看到的表结构信息中，有一栏 columns，在其中我们会看到处理我们建表时指定的字段以外，还有
额外的三个字段 分别是：DB_TRX_ID 、 DB_ROLL_PTR 、DB_ROW_ID，因为employee表是没有
指定主键的。

```
1 create table employee (id int , name varchar( 10 ));
```
```
1 ibd2sdi employee.ibd
```





### 6.4.3 undolog

#### 6.4.3.1 介绍

回滚日志，在insert、update、delete的时候产生的便于数据回滚的日志。

当insert的时候，产生的undo log日志只在回滚时需要，在事务提交后，可被立即删除。

而update、delete的时候，产生的undo log日志不仅在回滚时需要，在快照读时也需要，不会立即
被删除。

#### 6.4.3.2 版本链

#### 有一张表原始数据为：

#### DB_TRX_ID : 代表最近修改事务ID，记录插入这条记录或最后一次修改该记录的事务ID，是

#### 自增的。

```
DB_ROLL_PTR ： 由于这条数据是才插入的，没有被更新过，所以该字段值为null。
```

#### 然后，有四个并发事务同时在访问这张表。

#### A. 第一步

当事务 2 执行第一条修改语句时，会记录undo log日志，记录数据变更之前的样子; 然后更新记录，
并且记录本次操作的事务ID，回滚指针，回滚指针用来指定如果发生回滚，回滚到哪一个版本。

#### B.第二步

当事务 3 执行第一条修改语句时，也会记录undo log日志，记录数据变更之前的样子; 然后更新记
录，并且记录本次操作的事务ID，回滚指针，回滚指针用来指定如果发生回滚，回滚到哪一个版本。


#### C. 第三步

当事务 4 执行第一条修改语句时，也会记录undo log日志，记录数据变更之前的样子; 然后更新记
录，并且记录本次操作的事务ID，回滚指针，回滚指针用来指定如果发生回滚，回滚到哪一个版本。

```
最终我们发现，不同事务或相同事务对同一条记录进行修改，会导致该记录的undolog生成一条
记录版本链表，链表的头部是最新的旧记录，链表尾部是最早的旧记录。
```
### 6.4.4 readview


#### 字段 含义

```
m_ids 当前活跃的事务ID集合
min_trx_id 最小活跃事务ID
max_trx_id 预分配事务ID，当前最大事务ID+1（因为事务ID是自增的）
creator_trx_id ReadView创建者的事务ID
```
#### 条件 是否可以访问 说明

```
trx_id ==
creator_trx_id 可以访问该版本
```
#### 成立，说明数据是当前这个事

#### 务更改的。

```
trx_id < min_trx_id 可以访问该版本 成立，说明数据已经提交了。
```
```
trx_id > max_trx_id 不可以访问该版本 成立，说明该事务是在
ReadView生成后才开启。
min_trx_id <= trx_id
<= max_trx_id
```
```
如果trx_id不在m_ids中，
是可以访问该版本的 成立，说明数据已经提交。
```
ReadView（读视图）是 快照读 SQL执行时MVCC提取数据的依据，记录并维护系统当前活跃的事务
（未提交的）id。

ReadView中包含了四个核心字段：

而在readview中就规定了版本链数据的访问规则：

trx_id 代表当前undolog版本链对应事务ID。

不同的隔离级别，生成ReadView的时机不同：

```
READ COMMITTED ：在事务中每一次执行快照读时生成ReadView。
REPEATABLE READ：仅在事务中第一次执行快照读时生成ReadView，后续复用该ReadView。
```
### 6.4.5 原理分析

#### 6.4.5.1 RC隔离级别

RC隔离级别下，在事务中每一次执行快照读时生成ReadView。


#### 我们就来分析事务 5 中，两次快照读读取数据，是如何获取数据的?

在事务 5 中，查询了两次id为 30 的记录，由于隔离级别为Read Committed，所以每一次进行快照读
都会生成一个ReadView，那么两次生成的ReadView如下。

那么这两次快照读在获取数据时，就需要根据所生成的ReadView以及ReadView的版本链访问规则，
到undolog版本链中匹配数据，最终决定此次快照读返回的数据。

#### A. 先来看第一次快照读具体的读取过程：

在进行匹配时，会从undo log的版本链，从上到下进行挨个匹配：

#### 先匹配 这条记录，这条记录对应的

```
trx_id为 4 ，也就是将 4 带入右侧的匹配规则中。 ①不满足 ②不满足 ③不满足 ④也不满足 ，
都不满足，则继续匹配undo log版本链的下一条。
再匹配第二条 ，这条
记录对应的trx_id为 3 ，也就是将 3 带入右侧的匹配规则中。①不满足 ②不满足 ③不满足 ④也
不满足 ，都不满足，则继续匹配undo log版本链的下一条。
```

#### 再匹配第三条 ，这条记

```
录对应的trx_id为 2 ，也就是将 2 带入右侧的匹配规则中。①不满足 ②满足 终止匹配，此次快照
读，返回的数据就是版本链中记录的这条数据。
```
#### B. 再来看第二次快照读具体的读取过程:

在进行匹配时，会从undo log的版本链，从上到下进行挨个匹配：

#### 先匹配 这条记录，这条记录对应的

```
trx_id为 4 ，也就是将 4 带入右侧的匹配规则中。 ①不满足 ②不满足 ③不满足 ④也不满足 ，
都不满足，则继续匹配undo log版本链的下一条。
再匹配第二条 ，这条
记录对应的trx_id为 3 ，也就是将 3 带入右侧的匹配规则中。①不满足 ②满足 。终止匹配，此次
快照读，返回的数据就是版本链中记录的这条数据。
```
#### 6.4.5.3 RR隔离级别

RR隔离级别下，仅在事务中第一次执行快照读时生成ReadView，后续复用该ReadView。 而RR 是可
重复读，在一个事务中，执行两次相同的select语句，查询到的结果是一样的。

那MySQL是如何做到可重复读的呢? 我们简单分析一下就知道了


我们看到，在RR隔离级别下，只是在事务中第一次快照读时生成ReadView，后续都是复用该
ReadView，那么既然ReadView都一样， ReadView的版本链匹配规则也一样， 那么最终快照读返
回的结果也是一样的。

所以呢，MVCC的实现原理就是通过 InnoDB表的隐藏字段、UndoLog 版本链、ReadView来实现的。
而MVCC + 锁，则实现了事务的隔离性。 而一致性则是由redolog 与 undolog保证。

# 7. MySQL管理

## 7.1 系统数据库

Mysql数据库安装完成后，自带了一下四个数据库，具体作用如下：


#### 数据库 含义

```
mysql 存储MySQL服务器正常运行所需要的各种信息^ （时区、主从、用
户、权限等）
```
```
information_schema 提供了访问数据库元数据的各种表和视图，包含数据库、表、字段类
型及访问权限等
```
```
performance_schema
```
```
为MySQL服务器运行时状态提供了一个底层监控功能，主要用于收集
数据库服务器性能参数
```
```
sys
```
```
包含了一系列方便 DBA 和开发人员利用 performance_schema
性能数据库进行性能调优和诊断的视图
```
## 7.2 常用工具

### 7.2.1 mysql

该mysql不是指mysql服务，而是指mysql的客户端工具。

-e选项可以在Mysql客户端执行SQL语句，而不用连接到MySQL数据库再执行，对于一些批处理脚本，
这种方式尤其方便。

#### 示例：

```
语法 ：
mysql [options] [database]
选项 ：
-u, --user=name #指定用户名
-p, --password[=name] #指定密码
-h, --host=name #指定服务器IP或域名
-P, --port=port #指定连接端口
-e, --execute=name #执行SQL语句并退出
```
```
1 2 3 4 5 6 7 8
```
```
1 mysql -uroot –p123456 db01 -e "select * from stu";
```

### 7.2.2 mysqladmin

mysqladmin 是一个执行管理操作的客户端程序。可以用它来检查服务器的配置和当前状态、创建并
删除数据库等。

#### 示例：

```
通过帮助文档查看选项：
mysqladmin --help
```
```
1
2
```
```
语法:
mysqladmin [options] command ...
选项:
-u, --user=name #指定用户名
-p, --password[=name] #指定密码
-h, --host=name #指定服务器IP或域名
-P, --port=port #指定连接端口
```
```
1 2 3 4 5 6 7
```
```
mysqladmin -uroot –p1234 drop 'test01';
mysqladmin -uroot –p1234 version;
```
```
1
2
```

### 7.2.3 mysqlbinlog

#### 由于服务器生成的二进制日志文件以二进制格式保存，所以如果想要检查这些文本的文本格式，就会使

用到mysqlbinlog 日志管理工具。

#### 示例:

A. 查看 binlog.000008这个二进制文件中的数据信息

上述查看到的二进制日志文件数据信息量太多了，不方便查询。 我们可以加上一个参数 -s 来显示简
单格式。

```
语法 ：
mysqlbinlog [options] log-files1 log-files2 ...
选项 ：
-d, --database=name 指定数据库名称，只列出指定的数据库相关操作。
-o, --offset=# 忽略掉日志中的前n行命令。
-r,--result-file=name 将输出的文本格式日志输出到指定文件。
-s, --short-form 显示简单格式， 省略掉一些信息。
--start-datatime=date1 --stop-datetime=date2 指定日期间隔内的所有日志。
--start-position=pos1 --stop-position=pos2 指定位置间隔内的所有日志。
```
```
1 2 3 4 5 6 7 8 9
```

### 7.2.4 mysqlshow

mysqlshow 客户端对象查找工具，用来很快地查找存在哪些数据库、数据库中的表、表中的列或者索
引。

#### 示例：

```
语法 ：
mysqlshow [options] [db_name [table_name [col_name]]]
选项 ：
--count 显示数据库及表的统计信息（数据库，表 均可以不指定）
-i 显示指定数据库或者指定表的状态信息
示例：
```
```
#查询test库中每个表中的字段书，及行数
mysqlshow -uroot -p2143 test --count
```
```
#查询test库中book表的详细情况
mysqlshow -uroot -p2143 test book --count
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
13
```

#### A. 查询每个数据库的表的数量及表中记录的数量

mysqlshow -uroot -p1234 --count

B. 查看数据库db01的统计信息

mysqlshow -uroot -p1234 db01 --count

C. 查看数据库db01中的course表的信息

mysqlshow -uroot -p1234 db01 course --count

D. 查看数据库db01中的course表的id字段的信息

mysqlshow -uroot -p1234 db01 course id --count

### 7.2.5 mysqldump

mysqldump 客户端工具用来备份数据库或在不同数据库之间进行数据迁移。备份内容包含创建表，及
插入表的SQL语句。


#### 示例:

A. 备份db01数据库

mysqldump -uroot -p1234 db01 > db01.sql

可以直接打开db01.sql，来查看备份出来的数据到底什么样。

```
语法 ：
mysqldump [options] db_name [tables]
mysqldump [options] --database/-B db1 [db2 db3...]
mysqldump [options] --all-databases/-A
连接选项 ：
-u, --user=name 指定用户名
-p, --password[=name] 指定密码
-h, --host=name 指定服务器ip或域名
-P, --port=# 指定连接端口
输出选项：
--add-drop-database 在每个数据库创建语句前加上 drop database 语句
--add-drop-table 在每个表创建语句前加上 drop table 语句 , 默认开启 ; 不
开启 (--skip-add-drop-table)
-n, --no-create-db 不包含数据库的创建语句
-t, --no-create-info 不包含数据表的创建语句
-d --no-data 不包含数据
-T, --tab=name 自动生成两个文件：一个.sql文件，创建表结构的语句；一
个.txt文件，数据文件
```
```
1 2 3 4 5 6 7 8 9
```
```
10
11
12
```
```
13
14
15
16
```

#### 备份出来的数据包含：

#### 删除表的语句

#### 创建表的语句

#### 数据插入语句

#### 如果我们在数据备份时，不需要创建表，或者不需要备份数据，只需要备份表结构，都可以通过对应的

#### 参数来实现。

B. 备份db01数据库中的表数据，不备份表结构(-t)

mysqldump -uroot -p1234 -t db01 > db01.sql

打开 db02.sql ，来查看备份的数据，只有insert语句，没有备份表结构。

C. 将db01数据库的表的表结构与数据分开备份(-T)

mysqldump -uroot -p1234 -T /root db01 score


执行上述指令，会出错，数据不能完成备份，原因是因为我们所指定的数据存放目录/root，MySQL认
为是不安全的，需要存储在MySQL信任的目录下。那么，哪个目录才是MySQL信任的目录呢，可以查看
一下系统变量 secure_file_priv 。执行结果如下：

上述的两个文件 score.sql 中记录的就是表结构文件，而 score.txt 就是表数据文件，但是需
要注意表数据文件，并不是记录一条条的insert语句，而是按照一定的格式记录表结构中的数据。如
下：

### 7.2.6 mysqlimport/source

1). mysqlimport

mysqlimport 是客户端数据导入工具，用来导入mysqldump 加 -T 参数后导出的文本文件。

2). source

如果需要导入sql文件,可以使用mysql中的source 指令 :

```
语法 ：
mysqlimport [options] db_name textfile1 [textfile2...]
示例 ：
mysqlimport -uroot -p2143 test /tmp/city.txt
```
```
1
2
3
4
```
```
语法 ：
source /root/xxxxx.sql
```
```
1
2
```

# 1. 日志

## 1.1 错误日志

#### 错误日志是 MySQL 中最重要的日志之一，它记录了当 mysqld 启动和停止时，以及服务器在运行过

#### 程中发生任何严重错误时的相关信息。当数据库出现任何故障导致无法正常使用时，建议首先查看此日

#### 志。

#### 该日志是默认开启的，默认存放目录 /var/log/，默认的日志文件名为 mysqld.log 。查看日志

#### 位置：

## 1.2 二进制日志

## 1.2.1 介绍

#### 二进制日志（BINLOG）记录了所有的 DDL（数据定义语言）语句和 DML（数据操纵语言）语句，但

#### 不包括数据查询（SELECT、SHOW）语句。

#### 作用：①. 灾难时的数据恢复；②. MySQL的主从复制。在MySQL8版本中，默认二进制日志是开启着

#### 的，涉及到的参数如下：

```
1 show variables like '%log_error%';
```
```
1 show variables like '%log_bin%';
```

#### 日志格式 含义

#### STATEMENT

#### 基于SQL语句的日志记录，记录的是SQL语句，对数据进行修改的SQL都会记录在

#### 日志文件中。

#### ROW 基于行的日志记录，记录的是每一行的数据变更。（默认）

#### MIXED 混合了STATEMENT和ROW两种格式，默认采用STATEMENT，在某些特殊情况下会

#### 自动切换为ROW进行记录。

#### 参数说明：

#### log_bin_basename：当前数据库服务器的binlog日志的基础名称(前缀)，具体的binlog文

#### 件名需要再该basename的基础上加上编号(编号从 000001 开始)。

#### log_bin_index：binlog的索引文件，里面记录了当前服务器关联的binlog文件有哪些。

### 1.2.2 格式

#### MySQL服务器中提供了多种格式来记录二进制日志，具体格式及特点如下：

#### 如果我们需要配置二进制日志的格式，只需要在 /etc/my.cnf 中配置 binlog_format 参数即

#### 可。

### 1.2.3 查看

#### 由于日志是以二进制方式存储的，不能直接读取，需要通过二进制日志查询工具 mysqlbinlog 来查

#### 看，具体语法：

```
1 show variables like '%binlog_format%';
```

#### 指令 含义

#### reset master 删除全部 binlog 日志，删除之后，日志编号，将

#### 从 binlog.000001重新开始

#### purge master logs to

#### 'binlog.*'

#### 删除 * 编号之前的所有日志

#### purge master logs before

#### 'yyyy-mm-dd hh24:mi:ss'

#### 删除日志为 "yyyy-mm-dd hh24:mi:ss" 之前

#### 产生的所有日志

### 1.2.4 删除

#### 对于比较繁忙的业务系统，每天生成的binlog数据巨大，如果长时间不清除，将会占用大量磁盘空

#### 间。可以通过以下几种方式清理日志：

#### 也可以在mysql的配置文件中配置二进制日志的过期时间，设置了之后，二进制日志过期会自动删除。

## 1.3 查询日志

#### 查询日志中记录了客户端的所有操作语句，而二进制日志不包含查询数据的SQL语句。默认情况下，

#### 查询日志是未开启的。

```
mysqlbinlog [ 参数选项 ] logfilename
```
```
参数选项：
-d 指定数据库名称，只列出指定的数据库相关操作。
-o 忽略掉日志中的前n行命令。
-v 将行事件(数据变更)重构为SQL语句
-vv 将行事件(数据变更)重构为SQL语句，并输出注释信息
```
###### 1 2 3 4 5 6 7

```
1 show variables like '%binlog_expire_logs_seconds%';
```

#### 如果需要开启查询日志，可以修改MySQL的配置文件 /etc/my.cnf 文件，添加如下内容：

#### 开启了查询日志之后，在MySQL的数据存放目录，也就是 /var/lib/mysql/ 目录下就会出现

#### mysql_query.log 文件。之后所有的客户端的增删改查操作都会记录在该日志文件之中，长时间运

#### 行后，该日志文件将会非常大。

## 1.4 慢查询日志

#### 慢查询日志记录了所有执行时间超过参数 long_query_time 设置值并且扫描记录数不小于

#### min_examined_row_limit 的所有的SQL语句的日志，默认未开启。long_query_time 默认为

#### 10 秒，最小为 0 ， 精度可以到微秒。

#### 如果需要开启慢查询日志，需要在MySQL的配置文件 /etc/my.cnf 中配置如下参数：

#### 默认情况下，不会记录管理语句，也不会记录不使用索引进行查找的查询。可以使用

#### log_slow_admin_statements和 更改此行为 log_queries_not_using_indexes，如下所

#### 述。

#### 上述所有的参数配置完成之后，都需要重新启动MySQL服务器才可以生效。

###### #该选项用来开启查询日志 ， 可选值 ： 0 或者 1 ； 0 代表关闭， 1 代表开启

```
general_log= 1
#设置日志的文件名 ， 如果没有指定， 默认的文件名为 host_name.log
general_log_file=mysql_query.log
```
###### 1

###### 2

###### 3

###### 4

###### #慢查询日志

```
slow_query_log= 1
#执行时间参数
long_query_time= 2
```
###### 1

###### 2

###### 3

###### 4

###### #记录执行较慢的管理语句

```
log_slow_admin_statements = 1
#记录执行较慢的未使用索引的语句
log_queries_not_using_indexes = 1
```
###### 1

###### 2

###### 3

###### 4


# 2. 主从复制

## 2.1 概述

#### 主从复制是指将主数据库的 DDL 和 DML 操作通过二进制日志传到从库服务器中，然后在从库上对这

#### 些日志重新执行（也叫重做），从而使得从库和主库的数据保持同步。

#### MySQL支持一台主库同时向多台从库进行复制， 从库同时也可以作为其他从服务器的主库，实现链状

#### 复制。

#### MySQL 复制的优点主要包含以下三个方面：

#### 主库出现问题，可以快速切换到从库提供服务。

#### 实现读写分离，降低主库的访问压力。

#### 可以在从库中执行备份，以避免备份期间影响主库服务。

## 2.2 原理

#### MySQL主从复制的核心就是 二进制日志，具体的过程如下：


#### 从上图来看，复制分成三步：

#### 1. Master 主库在事务提交时，会把数据变更记录在二进制日志文件 Binlog 中。

#### 2. 从库读取主库的二进制日志文件 Binlog ，写入到从库的中继日志 Relay Log 。

#### 3. slave重做中继日志中的事件，将改变反映它自己的数据。

## 2.3 搭建

### 2.3.1 准备

#### 准备好两台服务器之后，在上述的两台服务器中分别安装好MySQL，并完成基础的初始化准备(安装、

#### 密码配置等操作)工作。 其中：

#### 192.168.200.200 作为主服务器master

#### 192.168.200.201 作为从服务器slave

### 2.3.2 主库配置

#### 1. 修改配置文件 /etc/my.cnf


#### 2. 重启MySQL服务器

#### 3. 登录mysql，创建远程连接的账号，并授予主从复制权限

#### 4. 通过指令，查看二进制日志坐标

#### 字段含义说明：

#### file : 从哪个日志文件开始推送日志文件

#### position ： 从哪个位置开始推送日志

#### binlog_ignore_db : 指定不需要同步的数据库

```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 232-1，默认为 1
server-id= 1
#是否只读,1 代表只读, 0 代表读写
read-only= 0
#忽略的数据, 指不需要同步的数据库
#binlog-ignore-db=mysql
#指定同步的数据库
#binlog-do-db=db
```
###### 1 2 3 4 5 6 7 8

```
1 systemctl restart mysqld
```
```
#创建itcast用户，并设置密码，该用户可在任意主机连接该MySQL服务
CREATE USER 'itcast'@'%' IDENTIFIED WITH mysql_native_password BY 'Root@123456'
;
```
```
#为 'itcast'@'%' 用户分配主从复制权限
GRANT REPLICATION SLAVE ON *.* TO 'itcast'@'%';
```
###### 1

###### 2

###### 3

###### 4

###### 5

```
1 show master status ;
```

#### 参数名 含义 8.0.23之前

#### SOURCE_HOST 主库IP地址 MASTER_HOST

#### SOURCE_USER 连接主库的用户名 MASTER_USER

#### SOURCE_PASSWORD 连接主库的密码 MASTER_PASSWORD

#### SOURCE_LOG_FILE binlog日志文件名 MASTER_LOG_FILE

#### SOURCE_LOG_POS binlog日志文件位置 MASTER_LOG_POS

### 2.3.3 从库配置

#### 1. 修改配置文件 /etc/my.cnf

#### 2. 重新启动MySQL服务

#### 3. 登录mysql，设置主库配置

#### 上述是8.0.23中的语法。如果mysql是 8.0.23 之前的版本，执行如下SQL：

#### 4. 开启同步操作

```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 2^32-1，和主库不一样即可
server-id= 2
#是否只读,1 代表只读, 0 代表读写
read-only= 1
```
###### 1

###### 2

###### 3

###### 4

```
1 systemctl restart mysqld
```
```
CHANGE REPLICATION SOURCE TO SOURCE_HOST='192.168.200.200', SOURCE_USER='itcast',
SOURCE_PASSWORD='Root@123456', SOURCE_LOG_FILE='binlog.000004',
SOURCE_LOG_POS= 663 ;
```
###### 1

```
CHANGE MASTER TO MASTER_HOST='192.168.200.200', MASTER_USER='itcast',
MASTER_PASSWORD='Root@123456', MASTER_LOG_FILE='binlog.000004',
MASTER_LOG_POS= 663 ;
```
###### 1


#### 5. 查看主从同步状态

### 2.3.4 测试

#### 1. 在主库 192.168.200.200 上创建数据库、表，并插入数据

#### 2. 在从库 192.168.200.201 中查询数据，验证主从是否同步

```
start replica ; #8.0.22之后
start slave ; #8.0.22之前
```
###### 1

###### 2

```
show replica status ; #8.0.22之后
show slave status ; #8.0.22之前
```
###### 1

###### 2

```
create database db01;
use db01;
create table tb_user(
id int( 11 ) primary key not null auto_increment,
name varchar( 50 ) not null,
sex varchar( 1 )
)engine=innodb default charset=utf8mb4;
insert into tb_user(id,name,sex) values(null,'Tom', '1'),(null,'Trigger','0'),
(null,'Dawn','1');
```
###### 1 2 3 4 5 6 7 8


# 3. 分库分表

## 3.1 介绍

## 3.1.1 问题分析

#### 随着互联网及移动互联网的发展，应用系统的数据量也是成指数式增长，若采用单数据库进行数据存

#### 储，存在以下性能瓶颈：

#### 1. IO瓶颈：热点数据太多，数据库缓存不足，产生大量磁盘IO，效率较低。 请求数据太多，带宽

#### 不够，网络IO瓶颈。

#### 2. CPU瓶颈：排序、分组、连接查询、聚合统计等SQL会耗费大量的CPU资源，请求数太多，CPU出

#### 现瓶颈。

#### 为了解决上述问题，我们需要对数据库进行分库分表处理。

#### 分库分表的中心思想都是将数据分散存储，使得单一数据库/表的数据量变小来缓解单一数据库的性能

#### 问题，从而达到提升数据库性能的目的。

## 3.1.2 拆分策略

#### 分库分表的形式，主要是两种：垂直拆分和水平拆分。而拆分的粒度，一般又分为分库和分表，所以组

#### 成的拆分策略最终如下：


### 3.1.3 垂直拆分

#### 1. 垂直分库

#### 垂直分库：以表为依据，根据业务将不同表拆分到不同库中。

#### 特点：

#### 每个库的表结构都不一样。

#### 每个库的数据也不一样。

#### 所有库的并集是全量数据。

#### 2. 垂直分表


#### 垂直分表：以字段为依据，根据字段属性将不同字段拆分到不同表中。

#### 特点：

#### 每个表的结构都不一样。

#### 每个表的数据也不一样，一般通过一列（主键/外键）关联。

#### 所有表的并集是全量数据。

### 3.1.4 水平拆分

#### 1. 水平分库

#### 水平分库：以字段为依据，按照一定策略，将一个库的数据拆分到多个库中。

#### 特点：


#### 每个库的表结构都一样。

#### 每个库的数据都不一样。

#### 所有库的并集是全量数据。

#### 2. 水平分表

#### 水平分表：以字段为依据，按照一定策略，将一个表的数据拆分到多个表中。

#### 特点：

#### 每个表的表结构都一样。

#### 每个表的数据都不一样。

#### 所有表的并集是全量数据。

#### 在业务系统中，为了缓解磁盘IO及CPU的性能瓶颈，到底是垂直拆分，还是水平拆分；具体是分

#### 库，还是分表，都需要根据具体的业务需求具体分析。

### 3.1.5 实现技术

#### shardingJDBC：基于AOP原理，在应用程序中对本地执行的SQL进行拦截，解析、改写、路由处

#### 理。需要自行编码配置实现，只支持java语言，性能较高。

#### MyCat：数据库分库分表中间件，不用调整代码即可实现分库分表，支持多种语言，性能不及前

#### 者。


#### 本次课程，我们选择了是MyCat数据库中间件，通过MyCat中间件来完成分库分表操作。

## 3.2 MyCat概述

### 3.2.1 介绍

#### Mycat是开源的、活跃的、基于Java语言编写的MySQL数据库中间件。可以像使用mysql一样来使用

#### mycat，对于开发人员来说根本感觉不到mycat的存在。

#### 开发人员只需要连接MyCat即可，而具体底层用到几台数据库，每一台数据库服务器里面存储了什么数

#### 据，都无需关心。 具体的分库分表的策略，只需要在MyCat中配置即可。

#### 优势：

#### 性能可靠稳定

#### 强大的技术团队

#### 体系完善

#### 社区活跃


#### 服务器 安装软件 说明

#### 192.168.200.210 JDK、Mycat MyCat中间件服务器

#### 192.168.200.210 MySQL 分片服务器

#### 192.168.200.213 MySQL 分片服务器

#### 192.168.200.214 MySQL 分片服务器

### 3.2.2 下载

#### 下载地址： http://dl.mycat.org.cn/

### 3.2.3 安装

#### Mycat是采用java语言开发的开源的数据库中间件，支持Windows和Linux运行环境，下面介绍

#### MyCat的Linux中的环境搭建。我们需要在准备好的服务器中安装如下软件。

#### MySQL

#### JDK

#### Mycat

#### 具体的安装步骤： 参考资料中提供的 《MyCat安装文档》即可，里面有详细的安装及配置步骤。

### 3.2.4 目录介绍


#### bin : 存放可执行文件，用于启动停止mycat

#### conf：存放mycat的配置文件

#### lib：存放mycat的项目依赖包（jar）

#### logs：存放mycat的日志文件

### 3.2.5 概念介绍

#### 在MyCat的整体结构中，分为两个部分：上面的逻辑结构、下面的物理结构。

#### 在MyCat的逻辑结构主要负责逻辑库、逻辑表、分片规则、分片节点等逻辑结构的处理，而具体的数据

#### 存储还是在物理结构，也就是数据库服务器中存储的。

#### 在后面讲解MyCat入门以及MyCat分片时，还会讲到上面所提到的概念。

## 3.3 MyCat入门

### 3.3.1 需求

#### 由于 tb_order 表中数据量很大，磁盘IO及容量都到达了瓶颈，现在需要对 tb_order 表进行数

#### 据分片，分为三个数据节点，每一个节点主机位于不同的服务器上, 具体的结构，参考下图：


### 3.3.2 环境准备

#### 准备 3 台服务器：

#### 192.168.200.210：MyCat中间件服务器，同时也是第一个分片服务器。

#### 192.168.200.213：第二个分片服务器。

#### 192.168.200.214：第三个分片服务器。


#### 并且在上述 3 台数据库中创建数据库 db01 。

### 3.3.3 配置

#### 1). schema.xml

#### 在schema.xml中配置逻辑库、逻辑表、数据节点、节点主机等相关信息。具体的配置如下：

```
<?xml version="1.0"?>
<!DOCTYPE mycat:schema SYSTEM "schema.dtd">
<mycat:schema xmlns:mycat="http://io.mycat/">
<schema name="DB01" checkSQLschema="true" sqlMaxLimit="100">
<table name="TB_ORDER" dataNode="dn1,dn2,dn3" rule="auto-sharding-long"
/>
</schema>
```
```
<dataNode name="dn1" dataHost="dhost1" database="db01" />
<dataNode name="dn2" dataHost="dhost2" database="db01" />
<dataNode name="dn3" dataHost="dhost3" database="db01" />
```
```
<dataHost name="dhost1" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
```
```
<writeHost host="master" url="jdbc:mysql://192.168.200.210:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</dataHost>
```
```
<dataHost name="dhost2" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
```
```
<writeHost host="master" url="jdbc:mysql://192.168.200.213:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23


#### 2). server.xml

#### 需要在server.xml中配置用户名、密码，以及用户的访问权限信息，具体的配置如下：

```
</dataHost>
```
```
<dataHost name="dhost3" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
```
```
<writeHost host="master" url="jdbc:mysql://192.168.200.214:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</dataHost>
</mycat:schema>
```
###### 24

###### 25

###### 26

###### 27

###### 28

###### 29

###### 30

###### 31

###### 32

```
<user name="root" defaultAccount="true">
<property name="password"> 123456 </property>
<property name="schemas">DB01</property>
```
```
<!-- 表级 DML 权限设置 -->
<!--
<privileges check="true">
<schema name="DB01" dml="0110" >
<table name="TB_ORDER" dml="1110"></table>
</schema>
</privileges>
-->
</user>
<user name="user">
<property name="password"> 123456 </property>
<property name="schemas">DB01</property>
<property name="readOnly">true</property>
</user>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18


#### 上述的配置表示，定义了两个用户 root 和 user ，这两个用户都可以访问 DB01 这个逻辑库，访

#### 问密码都是 123456 ，但是root用户访问DB01逻辑库，既可以读，又可以写，但是 user用户访问

#### DB01逻辑库是只读的。

### 3.3.4 测试

#### 3.3.4.1 启动

#### 配置完毕后，先启动涉及到的 3 台分片服务器，然后启动MyCat服务器。切换到Mycat的安装目录，执

#### 行如下指令，启动Mycat：

#### Mycat启动之后，占用端口号 8066 。

#### 启动完毕之后，可以查看logs目录下的启动日志，查看Mycat是否启动完成。

#### 3.3.4.2 测试

#### 1). 连接MyCat

#### 通过如下指令，就可以连接并登陆MyCat。

#### 我们看到我们是通过MySQL的指令来连接的MyCat，因为MyCat在底层实际上是模拟了MySQL的协议。

#### 2). 数据测试

#### 然后就可以在MyCat中来创建表，并往表结构中插入数据，查看数据在MySQL中的分布情况。

###### #启动

```
bin/mycat start
#停止
bin/mycat stop
```
###### 1

###### 2

###### 3

###### 4

```
1 mysql -h 192.168.200.210 -P 8066 -uroot -p
```
###### CREATE TABLE TB_ORDER (

```
id BIGINT( 20 ) NOT NULL,
```
###### 1

###### 2


#### 经过测试，我们发现，在往 TB_ORDER 表中插入数据时：

#### 如果id的值在1-500w之间，数据将会存储在第一个分片数据库中。

#### 如果id的值在500w-1000w之间，数据将会存储在第二个分片数据库中。

#### 如果id的值在1000w-1500w之间，数据将会存储在第三个分片数据库中。

#### 如果id的值超出1500w，在插入数据时，将会报错。

#### 为什么会出现这种现象，数据到底落在哪一个分片服务器到底是如何决定的呢？ 这是由逻辑表配置时

#### 的一个参数 rule 决定的，而这个参数配置的就是分片规则，关于分片规则的配置，在后面的课程中

#### 会详细讲解。

## 3.4 MyCat配置

### 3.4.1 schema.xml

#### schema.xml 作为MyCat中最重要的配置文件之一 , 涵盖了MyCat的逻辑库 、 逻辑表 、 分片规

#### 则、分片节点及数据源的配置。

```
title VARCHAR( 100 ) NOT NULL ,
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;
```
```
INSERT INTO TB_ORDER(id,title) VALUES( 1 ,'goods1');
INSERT INTO TB_ORDER(id,title) VALUES( 2 ,'goods2');
INSERT INTO TB_ORDER(id,title) VALUES( 3 ,'goods3');
```
```
INSERT INTO TB_ORDER(id,title) VALUES( 1 ,'goods1');
INSERT INTO TB_ORDER(id,title) VALUES( 2 ,'goods2');
INSERT INTO TB_ORDER(id,title) VALUES( 3 ,'goods3');
INSERT INTO TB_ORDER(id,title) VALUES( 5000000 ,'goods5000000');
INSERT INTO TB_ORDER(id,title) VALUES( 10000000 ,'goods10000000');
INSERT INTO TB_ORDER(id,title) VALUES( 10000001 ,'goods10000001');
INSERT INTO TB_ORDER(id,title) VALUES( 15000000 ,'goods15000000');
INSERT INTO TB_ORDER(id,title) VALUES( 15000001 ,'goods15000001');
```
###### 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18


#### 主要包含以下三组标签：

#### schema标签

#### datanode标签

#### datahost标签

#### 3.4.1.1 schema标签

#### 1). schema 定义逻辑库

#### schema 标签用于定义 MyCat实例中的逻辑库 , 一个MyCat实例中, 可以有多个逻辑库 , 可以通

#### 过 schema 标签来划分不同的逻辑库。MyCat中的逻辑库的概念，等同于MySQL中的database概念

#### , 需要操作某个逻辑库下的表时, 也需要切换逻辑库(use xxx)。

#### 核心属性：

#### name：指定自定义的逻辑库库名

#### checkSQLschema：在SQL语句操作时指定了数据库名称，执行时是否自动去除；true：自动去

#### 除，false：不自动去除

#### sqlMaxLimit：如果未指定limit进行查询，列表查询模式查询多少条记录

#### 2). schema 中的table定义逻辑表

#### table 标签定义了MyCat中逻辑库schema下的逻辑表 , 所有需要拆分的表都需要在table标签中定

#### 义 。


#### 核心属性：

#### name：定义逻辑表表名，在该逻辑库下唯一

#### dataNode：定义逻辑表所属的dataNode，该属性需要与dataNode标签中name对应；多个

#### dataNode逗号分隔

#### rule：分片规则的名字，分片规则名字是在rule.xml中定义的

#### primaryKey：逻辑表对应真实表的主键

#### type：逻辑表的类型，目前逻辑表只有全局表和普通表，如果未配置，就是普通表；全局表，配

#### 置为 global

#### 3.4.1.2 datanode标签

#### 核心属性：

#### name：定义数据节点名称

#### dataHost：数据库实例主机名称，引用自 dataHost 标签中name属性

#### database：定义分片所属数据库

#### 3.4.1.3 datahost标签

#### 该标签在MyCat逻辑库中作为底层标签存在, 直接定义了具体的数据库实例、读写分离、心跳语句。

#### 核心属性：

#### name：唯一标识，供上层标签使用

#### maxCon/minCon：最大连接数/最小连接数

#### balance：负载均衡策略，取值 0,1,2,3

#### writeType：写操作分发方式（ 0 ：写操作转发到第一个writeHost，第一个挂了，切换到第二

#### 个； 1 ：写操作随机分发到配置的writeHost）

#### dbDriver：数据库驱动，支持 native、jdbc


### 3.4.2 rule.xml

#### rule.xml中定义所有拆分表的规则, 在使用过程中可以灵活的使用分片算法, 或者对同一个分片算法

#### 使用不同的参数, 它让分片过程可配置化。主要包含两类标签：tableRule、Function。

### 3.4.3 server.xml

#### server.xml配置文件包含了MyCat的系统配置信息，主要有两个重要的标签：system、user。

#### 1). system标签

#### 主要配置MyCat中的系统配置信息，对应的系统配置项及其含义，如下：


##### 属性 取值 含义

##### charset utf8 设置Mycat的字符集, 字符集需要与MySQL的

##### 字符集保持一致

##### nonePasswordLogin 0,1^0 为需要密码登陆、^1 为不需要密码登陆^ ,默认

##### 为 0 ，设置为 1 则需要指定默认账户

##### useHandshakeV10 0,1

##### 使用该选项主要的目的是为了能够兼容高版本

##### 的jdbc驱动, 是否采用

##### HandshakeV10Packet来与client进行通

##### 信, 1:是, 0:否

##### useSqlStat 0,1

##### 开启SQL实时统计, 1 为开启 , 0 为关闭 ;

##### 开启之后, MyCat会自动统计SQL语句的执行

##### 情况 ; mysql -h 127.0.0.1 -P 9066

##### -u root -p 查看MyCat执行的SQL, 执行

##### 效率比较低的SQL , SQL的整体执行情况、读

##### 写比例等 ; show @@sql ; show

##### @@sql.slow ; show @@sql.sum ;

##### useGlobleTableCheck 0,1 是否开启全局表的一致性检测。^1 为开启^ ，^0

##### 为关闭 。

##### sqlExecuteTimeout 1000 SQL语句执行的超时时间 , 单位为 s ;

##### sequnceHandlerType 0,1,2

##### 用来指定Mycat全局序列类型， 0 为本地文

##### 件， 1 为数据库方式， 2 为时间戳列方式，默

##### 认使用本地文件方式，文件方式主要用于测试

##### sequnceHandlerPattern 正则表达式 必须带有MYCATSEQ或者^ mycatseq进入序列

##### 匹配流程 注意MYCATSEQ_有空格的情况

##### subqueryRelationshipCheck true,false 子查询中存在关联查询的情况下,检查关联字

##### 段中是否有分片字段 .默认 false

##### useCompression 0,1

##### 开启mysql压缩协议 , 0 : 关闭, 1 : 开

##### 启

##### fakeMySQLVersion 5.5,5.6 设置模拟的MySQL版本号


##### 属性 取值 含义

##### defaultSqlParser

##### 由于MyCat的最初版本使用了FoundationDB

##### 的SQL解析器, 在MyCat1.3后增加了Druid

##### 解析器, 所以要设置defaultSqlParser属

##### 性来指定默认的解析器; 解析器有两个 :

##### druidparser 和 fdbparser, 在

##### MyCat1.4之后,默认是druidparser,

##### fdbparser已经废除了

##### processors 1,2....

##### 指定系统可用的线程数量, 默认值为CPU核心

##### x 每个核心运行线程数量; processors 会

##### 影响processorBufferPool,

##### processorBufferLocalPercent,

##### processorExecutor属性, 所有, 在性能

##### 调优时, 可以适当地修改processors值

##### processorBufferChunk

##### 指定每次分配Socket Direct Buffer默认

##### 值为 4096 字节, 也会影响BufferPool长度,

##### 如果一次性获取字节过多而导致buffer不够

##### 用, 则会出现警告, 可以调大该值

##### processorExecutor

##### 指定NIOProcessor上共享

##### businessExecutor固定线程池的大小;

##### MyCat把异步任务交给 businessExecutor

##### 线程池中, 在新版本的MyCat中这个连接池使

##### 用频次不高, 可以适当地把该值调小

##### packetHeaderSize 指定MySQL协议中的报文头长度, 默认^4 个字

##### 节

##### maxPacketSize

##### 指定MySQL协议可以携带的数据最大大小, 默

##### 认值为16M

##### idleTimeout 30

##### 指定连接的空闲时间的超时长度;如果超时,将

##### 关闭资源并回收, 默认 30 分钟


##### 属性 取值 含义

##### txIsolation 1,2,3,4

##### 初始化前端连接的事务隔离级别,默认为

##### REPEATED_READ , 对应数字为 3

##### READ_UNCOMMITED=1;

##### READ_COMMITTED=2; REPEATED_READ=3;

##### SERIALIZABLE=4;

##### sqlExecuteTimeout 300 执行SQL的超时时间, 如果SQL语句执行超时,

##### 将关闭连接; 默认 300 秒;

##### serverPort 8066 定义MyCat的使用端口, 默认 8066

##### managerPort 9066 定义MyCat的管理端口, 默认 9066

#### 2). user标签

#### 配置MyCat中的用户、访问密码，以及用户针对于逻辑库、逻辑表的权限信息，具体的权限描述方式及

#### 配置说明如下：

#### 在测试权限操作时，我们只需要将 privileges 标签的注释放开。 在 privileges 下的schema

#### 标签中配置的dml属性配置的是逻辑库的权限。 在privileges的schema下的table标签的dml属性

#### 中配置逻辑表的权限。

## 3.5 MyCat分片

### 3.5.1 垂直拆分


#### 3.5.1.1 场景

#### 在业务系统中, 涉及以下表结构 ,但是由于用户与订单每天都会产生大量的数据, 单台服务器的数据

#### 存储及处理能力是有限的, 可以对数据库表进行拆分, 原有的数据库表如下。

#### 现在考虑将其进行垂直分库操作，将商品相关的表拆分到一个数据库服务器，订单表拆分的一个数据库

#### 服务器，用户及省市区表拆分到一个服务器。最终结构如下：

#### 3.5.1.2 准备

#### 准备三台服务器，IP地址如图所示：


#### 并且在192.168.200.210，192.168.200.213, 192.168.200.214上面创建数据库

#### shopping。

#### 3.5.1.3 配置

#### 1). schema.xml

```
<schema name="SHOPPING" checkSQLschema="true" sqlMaxLimit="100">
<table name="tb_goods_base" dataNode="dn1" primaryKey="id" />
<table name="tb_goods_brand" dataNode="dn1" primaryKey="id" />
<table name="tb_goods_cat" dataNode="dn1" primaryKey="id" />
<table name="tb_goods_desc" dataNode="dn1" primaryKey="goods_id" />
<table name="tb_goods_item" dataNode="dn1" primaryKey="id" />
```
```
<table name="tb_order_item" dataNode="dn2" primaryKey="id" />
<table name="tb_order_master" dataNode="dn2" primaryKey="order_id" />
<table name="tb_order_pay_log" dataNode="dn2" primaryKey="out_trade_no" />
```
```
<table name="tb_user" dataNode="dn3" primaryKey="id" />
<table name="tb_user_address" dataNode="dn3" primaryKey="id" />
```
```
<table name="tb_areas_provinces" dataNode="dn3" primaryKey="id"/>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15


#### 2). server.xml

```
<table name="tb_areas_city" dataNode="dn3" primaryKey="id"/>
<table name="tb_areas_region" dataNode="dn3" primaryKey="id"/>
</schema>
```
```
<dataNode name="dn1" dataHost="dhost1" database="shopping" />
<dataNode name="dn2" dataHost="dhost2" database="shopping" />
<dataNode name="dn3" dataHost="dhost3" database="shopping" />
```
```
<dataHost name="dhost1" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
<writeHost host="master" url="jdbc:mysql://192.168.200.210:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</dataHost>
```
```
<dataHost name="dhost2" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
<writeHost host="master" url="jdbc:mysql://192.168.200.213:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</dataHost>
```
```
<dataHost name="dhost3" maxCon="1000" minCon="10" balance="0"
writeType="0" dbType="mysql" dbDriver="jdbc" switchType="1"
slaveThreshold="100">
<heartbeat>select user()</heartbeat>
<writeHost host="master" url="jdbc:mysql://192.168.200.214:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</dataHost>
```
###### 16

###### 17

###### 18

###### 19

###### 20

###### 21

###### 22

###### 23

###### 24

###### 25

###### 26

###### 27

###### 28

###### 29

###### 30

###### 31

###### 32

###### 33

###### 34

###### 35

###### 36

###### 37

###### 38

###### 39

###### 40


#### 3.5.1.4 测试

#### 1). 上传测试SQL脚本到服务器的/root目录

#### 2). 执行指令导入测试数据

#### 重新启动MyCat后，在mycat的命令行中，通过source指令导入表结构，以及对应的数据，查看数据

#### 分布情况。

#### 将表结构及对应的测试数据导入之后，可以检查一下各个数据库服务器中的表结构分布情况。 检查是

#### 否和我们准备工作中规划的服务器一致。

```
<user name="root" defaultAccount="true">
<property name="password"> 123456 </property>
<property name="schemas">SHOPPING</property>
```
```
<!-- 表级 DML 权限设置 -->
<!--
<privileges check="true">
<schema name="DB01" dml="0110" >
<table name="TB_ORDER" dml="1110"></table>
</schema>
</privileges>
-->
</user>
```
```
<user name="user">
<property name="password"> 123456 </property>
<property name="schemas">SHOPPING</property>
<property name="readOnly">true</property>
</user>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19

```
source /root/shopping-table.sql
source /root/shopping-insert.sql
```
###### 1

###### 2


#### 3). 查询用户的收件人及收件人地址信息(包含省、市、区)。

#### 在MyCat的命令行中，当我们执行以下多表联查的SQL语句时，可以正常查询出数据。

#### 4). 查询每一笔订单及订单的收件地址信息(包含省、市、区)。

#### 实现该需求对应的SQL语句如下：

#### 但是现在存在一个问题，订单相关的表结构是在 192.168.200.213 数据库服务器中，而省市区的数

#### 据库表是在 192.168.200.214 数据库服务器中。那么在MyCat中执行是否可以成功呢？

#### 经过测试，我们看到，SQL语句执行报错。原因就是因为MyCat在执行该SQL语句时，需要往具体的数

#### 据库服务器中路由，而当前没有一个数据库服务器完全包含了订单以及省市区的表结构，造成SQL语句

#### 失败，报错。

```
select ua.user_id, ua.contact, p.province, c.city, r.area , ua.address from
tb_user_address ua ,tb_areas_city c , tb_areas_provinces p ,tb_areas_region r
where ua.province_id = p.provinceid and ua.city_id = c.cityid and ua.town_id =
r.areaid ;
```
###### 1

```
SELECT order_id , payment ,receiver, province , city , area FROM tb_order_master o
, tb_areas_provinces p , tb_areas_city c , tb_areas_region r WHERE
o.receiver_province = p.provinceid AND o.receiver_city = c.cityid AND
o.receiver_region = r.areaid ;
```
###### 1


#### 对于上述的这种现象，我们如何来解决呢？ 下面我们介绍的全局表，就可以轻松解决这个问题。

#### 3.5.1.5 全局表

#### 对于省、市、区/县表tb_areas_provinces , tb_areas_city , tb_areas_region，是属于

#### 数据字典表，在多个业务模块中都可能会遇到，可以将其设置为全局表，利于业务操作。

#### 修改schema.xml中的逻辑表的配置，修改 tb_areas_provinces、tb_areas_city、

#### tb_areas_region 三个逻辑表，增加 type 属性，配置为global，就代表该表是全局表，就会在

#### 所涉及到的dataNode中创建给表。对于当前配置来说，也就意味着所有的节点中都有该表了。

```
<table name="tb_areas_provinces" dataNode="dn1,dn2,dn3" primaryKey="id"
type="global"/>
<table name="tb_areas_city" dataNode="dn1,dn2,dn3" primaryKey="id"
type="global"/>
<table name="tb_areas_region" dataNode="dn1,dn2,dn3" primaryKey="id"
type="global"/>
```
###### 1

###### 2

###### 3


#### 配置完毕后，重新启动MyCat。

#### 1). 删除原来每一个数据库服务器中的所有表结构

#### 2). 通过source指令，导入表及数据

#### 3). 检查每一个数据库服务器中的表及数据分布，看到三个节点中都有这三张全局表

#### 4). 然后再次执行上面的多表联查的SQL语句

```
source /root/shopping-table.sql
source /root/shopping-insert.sql
```
###### 1

###### 2


#### 是可以正常执行成功的。

#### 5). 当在MyCat中更新全局表的时候，我们可以看到，所有分片节点中的数据都发生了变化，每个节

#### 点的全局表数据时刻保持一致。

### 3.5.2 水平拆分

#### 3.5.2.1 场景

#### 在业务系统中, 有一张表(日志表), 业务系统每天都会产生大量的日志数据 , 单台服务器的数据存

#### 储及处理能力是有限的, 可以对数据库表进行拆分。

#### 3.5.2.2 准备

```
SELECT order_id , payment ,receiver, province , city , area FROM tb_order_master o
, tb_areas_provinces p , tb_areas_city c , tb_areas_region r WHERE
o.receiver_province = p.provinceid AND o.receiver_city = c.cityid AND
o.receiver_region = r.areaid ;
```
###### 1


#### 准备三台服务器，具体的结构如下：

#### 并且，在三台数据库服务器中分表创建一个数据库itcast。

#### 3.5.2.3 配置

#### 1). schema.xml

#### tb_log表最终落在 3 个节点中，分别是 dn4、dn5、dn6 ，而具体的数据分别存储在 dhost1、

#### dhost2、dhost3的itcast数据库中。

#### 2). server.xml

#### 配置root用户既可以访问 SHOPPING 逻辑库，又可以访问ITCAST逻辑库。

```
<schema name="ITCAST" checkSQLschema="true" sqlMaxLimit="100">
<table name="tb_log" dataNode="dn4,dn5,dn6" primaryKey="id" rule="mod-long" />
</schema>
```
```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1 2 3 4 5 6 7


#### 3.5.2.4 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

```
<user name="root" defaultAccount="true">
<property name="password"> 123456 </property>
<property name="schemas">SHOPPING,ITCAST</property>
```
```
<!-- 表级 DML 权限设置 -->
<!--
<privileges check="true">
<schema name="DB01" dml="0110" >
<table name="TB_ORDER" dml="1110"></table>
</schema>
</privileges>
-->
</user>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

```
CREATE TABLE tb_log (
id bigint( 20 ) NOT NULL COMMENT 'ID',
model_name varchar( 200 ) DEFAULT NULL COMMENT '模块名',
model_value varchar( 200 ) DEFAULT NULL COMMENT '模块值',
return_value varchar( 200 ) DEFAULT NULL COMMENT '返回值',
return_class varchar( 200 ) DEFAULT NULL COMMENT '返回值类型',
operate_user varchar( 20 ) DEFAULT NULL COMMENT '操作用户',
operate_time varchar( 20 ) DEFAULT NULL COMMENT '操作时间',
param_and_value varchar( 500 ) DEFAULT NULL COMMENT '请求参数名及参数值',
operate_class varchar( 200 ) DEFAULT NULL COMMENT '操作类',
operate_method varchar( 200 ) DEFAULT NULL COMMENT '操作方法',
cost_time bigint( 20 ) DEFAULT NULL COMMENT '执行方法耗时, 单位 ms',
source int( 1 ) DEFAULT NULL COMMENT '来源 : 1 PC , 2 Android , 3 IOS',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16


```
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('1','user','insert','success','java.lang.String','10001','2022-01-06
18:12:28','{\"age\":\"20\",\"name\":\"Tom\",\"gender\":\"1\"}','cn.itcast.contro
ller.UserController','insert','10', 1 );
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('2','user','insert','success','java.lang.String','10001','2022-01-06
18:12:27','{\"age\":\"20\",\"name\":\"Tom\",\"gender\":\"1\"}','cn.itcast.contro
ller.UserController','insert','23', 1 );
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('3','user','update','success','java.lang.String','10001','2022-01-06
18:16:45','{\"age\":\"20\",\"name\":\"Tom\",\"gender\":\"1\"}','cn.itcast.contro
ller.UserController','update','34', 1 );
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('4','user','update','success','java.lang.String','10001','2022-01-06
18:16:45','{\"age\":\"20\",\"name\":\"Tom\",\"gender\":\"1\"}','cn.itcast.contro
ller.UserController','update','13', 2 );
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('5','user','insert','success','java.lang.String','10001','2022-01-06
18:30:31','{\"age\":\"200\",\"name\":\"TomCat\",\"gender\":\"0\"}','cn.itcast.co
ntroller.UserController','insert','29', 3 );
INSERT INTO tb_log (id, model_name, model_value, return_value, return_class,
operate_user, operate_time, param_and_value, operate_class, operate_method,
cost_time，source)
VALUES('6','user','find','success','java.lang.String','10001','2022-01-06
18:30:31','{\"age\":\"200\",\"name\":\"TomCat\",\"gender\":\"0\"}','cn.itcast.co
ntroller.UserController','find','29', 2 );
```
###### 17

###### 18

###### 19

###### 20

###### 21

###### 22


### 3.5.3 分片规则

#### 3.5.3.1 范围分片

#### 1). 介绍

#### 根据指定的字段及其配置的范围与数据节点的对应情况， 来决定该数据属于哪一个分片。

#### 2). 配置

#### schema.xml逻辑表配置：

#### schema.xml数据节点配置：

#### rule.xml分片规则配置：

```
1 <table name="TB_ORDER" dataNode="dn1,dn2,dn3" rule="auto-sharding-long" />
```
```
<dataNode name="dn1" dataHost="dhost1" database="db01" />
<dataNode name="dn2" dataHost="dhost2" database="db01" />
<dataNode name="dn3" dataHost="dhost3" database="db01" />
```
###### 1

###### 2

###### 3


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### mapFile 对应的外部配置文件

#### type 默认值为0 ; 0 表示Integer , 1 表示String

#### defaultNode 默认节点^ 默认节点的所用:枚举分片时,如果碰到不识别的枚举值, 就让它路

#### 由到默认节点 ; 如果没有默认值,碰到不识别的则报错 。

#### 分片规则配置属性含义：

#### 在rule.xml中配置分片规则时，关联了一个映射配置文件 autopartition-long.txt，该配置文

#### 件的配置如下：

#### 含义：0-500万之间的值，存储在 0 号数据节点(数据节点的索引从 0 开始) ； 500 万-1000万之间的

#### 数据存储在 1 号数据节点 ； 1000 万-1500万的数据节点存储在 2 号节点 ；

```
<tableRule name="auto-sharding-long">
<rule>
<columns>id</columns>
<algorithm>rang-long</algorithm>
</rule>
</tableRule>
```
```
<function name="rang-long" class="io.mycat.route.function.AutoPartitionByLong">
<property name="mapFile">autopartition-long.txt</property>
<property name="defaultNode"> 0 </property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

```
# range start-end ,data node index
# K=1000,M=10000.
0-500M= 0
500M-1000M= 1
1000M-1500M= 2
```
###### 1

###### 2

###### 3

###### 4

###### 5


#### 该分片规则，主要是针对于数字类型的字段适用。 在MyCat的入门程序中，我们使用的就是该分片规

#### 则。

#### 3.5.3.2 取模分片

#### 1). 介绍

#### 根据指定的字段值与节点数量进行求模运算，根据运算结果， 来决定该数据属于哪一个分片。

#### 2). 配置

#### schema.xml逻辑表配置：

#### schema.xml数据节点配置：

#### rule.xml分片规则配置：

```
1 <table name="tb_log" dataNode="dn4,dn5,dn6" primaryKey="id" rule="mod-long" />
```
```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### count 数据节点的数量

#### 分片规则属性说明如下：

#### 该分片规则，主要是针对于数字类型的字段适用。 在前面水平拆分的演示中，我们选择的就是取模分

#### 片。

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

#### 3.5.3.3 一致性hash分片

#### 1). 介绍

#### 所谓一致性哈希，相同的哈希因子计算值总是被划分到相同的分区表中，不会因为分区节点的增加而改

#### 变原来数据的分区位置，有效的解决了分布式数据的拓容问题。

```
<tableRule name="mod-long">
<rule>
<columns>id</columns>
<algorithm>mod-long</algorithm>
</rule>
</tableRule>
```
```
<function name="mod-long" class="io.mycat.route.function.PartitionByMod">
<property name="count"> 3 </property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10


#### 2). 配置

#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

```
<!-- 一致性hash -->
<table name="tb_order" dataNode="dn4,dn5,dn6" rule="sharding-by-murmur" />
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### seed 创建murmur_hash对象的种子，默认 0

#### count 要分片的数据库节点数量，必须指定，否则没法分片

#### virtualBucketTimes

#### 一个实际的数据库节点被映射为这么多虚拟节点，默认是 160 倍，也

#### 就是虚拟节点数是物理节点数的 160

#### 倍;virtualBucketTimes*count就是虚拟结点数量 ;

#### weightMapFile

#### 节点的权重，没有指定权重的节点默认是 1 。以properties文件的

#### 格式填写，以从 0 开始到count-1的整数值也就是节点索引为key，

#### 以节点权重值为值。所有权重值必须是正整数，否则以 1 代替

#### bucketMapPath

#### 用于测试时观察各物理节点与虚拟节点的分布情况，如果指定了这个

#### 属性，会把虚拟节点的murmur hash值与物理节点的映射按行输出

#### 到这个文件，没有默认值，如果不指定，就不会输出任何东西

#### 分片规则属性含义：

#### 3). 测试

```
<tableRule name="sharding-by-murmur">
<rule>
<columns>id</columns>
<algorithm>murmur</algorithm>
</rule>
</tableRule>
```
```
<function name="murmur" class="io.mycat.route.function.PartitionByMurmurHash">
<property name="seed"> 0 </property><!-- 默认是0 -->
<property name="count"> 3 </property>
<property name="virtualBucketTimes"> 160 </property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12


#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

```
create table tb_order(
id varchar( 100 ) not null primary key,
money int null,
content varchar( 200 ) null
);
```
```
INSERT INTO tb_order (id, money, content) VALUES ('b92fdaaf-6fc4-11ec-b831-
482ae33c4a2d', 10 , 'b92fdaf8-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b93482b6-6fc4-11ec-b831-
482ae33c4a2d', 20 , 'b93482d5-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b937e246-6fc4-11ec-b831-
482ae33c4a2d', 50 , 'b937e25d-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b93be2dd-6fc4-11ec-b831-
482ae33c4a2d', 100 , 'b93be2f9-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b93f2d68-6fc4-11ec-b831-
482ae33c4a2d', 130 , 'b93f2d7d-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b9451b98-6fc4-11ec-b831-
482ae33c4a2d', 30 , 'b9451bcc-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b9488ec1-6fc4-11ec-b831-
482ae33c4a2d', 560 , 'b9488edb-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b94be6e6-6fc4-11ec-b831-
482ae33c4a2d', 10 , 'b94be6ff-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b94ee10d-6fc4-11ec-b831-
482ae33c4a2d', 123 , 'b94ee12c-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b952492a-6fc4-11ec-b831-
482ae33c4a2d', 145 , 'b9524945-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b95553ac-6fc4-11ec-b831-
482ae33c4a2d', 543 , 'b95553c8-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b9581cdd-6fc4-11ec-b831-
482ae33c4a2d', 17 , 'b9581cfa-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b95afc0f-6fc4-11ec-b831-
482ae33c4a2d', 18 , 'b95afc2a-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b95daa99-6fc4-11ec-b831-
482ae33c4a2d', 134 , 'b95daab2-6fc4-11ec-b831-482ae33c4a2d');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19

###### 20


#### 3.5.3.4 枚举分片

#### 1). 介绍

#### 通过在配置文件中配置可能的枚举值, 指定数据分布到不同数据节点上, 本规则适用于按照省份、性

#### 别、状态拆分数据等业务 。

#### 2). 配置

```
INSERT INTO tb_order (id, money, content) VALUES ('b9667e3c-6fc4-11ec-b831-
482ae33c4a2d', 156 , 'b9667e60-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b96ab489-6fc4-11ec-b831-
482ae33c4a2d', 175 , 'b96ab4a5-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b96e2942-6fc4-11ec-b831-
482ae33c4a2d', 180 , 'b96e295b-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b97092ec-6fc4-11ec-b831-
482ae33c4a2d', 123 , 'b9709306-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b973727a-6fc4-11ec-b831-
482ae33c4a2d', 230 , 'b9737293-6fc4-11ec-b831-482ae33c4a2d');
INSERT INTO tb_order (id, money, content) VALUES ('b978840f-6fc4-11ec-b831-
482ae33c4a2d', 560 , 'b978843c-6fc4-11ec-b831-482ae33c4a2d');
```
###### 21

###### 22

###### 23

###### 24

###### 25

###### 26


#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

#### partition-hash-int.txt ，内容如下 :

###### <!-- 枚举 -->

```
<table name="tb_user" dataNode="dn4,dn5,dn6" rule="sharding-by-intfile-enumstatus"
/>
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3

```
<tableRule name="sharding-by-intfile">
<rule>
<columns>sharding_id</columns>
<algorithm>hash-int</algorithm>
</rule>
</tableRule>
```
```
<!-- 自己增加 tableRule -->
<tableRule name="sharding-by-intfile-enumstatus">
<rule>
<columns>status</columns>
<algorithm>hash-int</algorithm>
</rule>
</tableRule>
```
```
<function name="hash-int" class="io.mycat.route.function.PartitionByFileMap">
<property name="defaultNode"> 2 </property>
<property name="mapFile">partition-hash-int.txt</property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17

###### 18

###### 19


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### mapFile 对应的外部配置文件

#### type 默认值为0 ; 0 表示Integer , 1 表示String

#### defaultNode

#### 默认节点 ; 小于 0 标识不设置默认节点 , 大于等于 0 代表设置默认节点 ;

#### 默认节点的所用:枚举分片时,如果碰到不识别的枚举值, 就让它路由到默认节

#### 点 ; 如果没有默认值,碰到不识别的则报错 。

#### 分片规则属性含义：

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

###### 1 = 0

###### 2 = 1

###### 3 = 2

###### 1

###### 2

###### 3

```
CREATE TABLE tb_user (
id bigint( 20 ) NOT NULL COMMENT 'ID',
username varchar( 200 ) DEFAULT NULL COMMENT '姓名',
status int( 2 ) DEFAULT '1' COMMENT '1: 未启用, 2: 已启用, 3: 已关闭',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
```
insert into tb_user (id,username ,status) values( 1 ,'Tom', 1 );
insert into tb_user (id,username ,status) values( 2 ,'Cat', 2 );
insert into tb_user (id,username ,status) values( 3 ,'Rose', 3 );
insert into tb_user (id,username ,status) values( 4 ,'Coco', 2 );
insert into tb_user (id,username ,status) values( 5 ,'Lily', 1 );
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13


#### 3.5.3.5 应用指定算法

#### 1). 介绍

#### 运行阶段由应用自主决定路由到那个分片 , 直接根据字符子串（必须是数字）计算分片号。

#### 2). 配置

#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

```
insert into tb_user (id,username ,status) values( 6 ,'Tom', 1 );
insert into tb_user (id,username ,status) values( 7 ,'Cat', 2 );
insert into tb_user (id,username ,status) values( 8 ,'Rose', 3 );
insert into tb_user (id,username ,status) values( 9 ,'Coco', 2 );
insert into tb_user (id,username ,status) values( 10 ,'Lily', 1 );
```
###### 14

###### 15

###### 16

###### 17

###### 18

###### <!-- 应用指定算法 -->

```
<table name="tb_app" dataNode="dn4,dn5,dn6" rule="sharding-by-substring" />
```
###### 1

###### 2


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### startIndex 字符子串起始索引

#### size 字符长度

#### partitionCount 分区(分片)数量

#### defaultPartition 默认分片(在分片数量定义时, 字符标示的分片编号不在分片数量内时,

#### 使用默认分片)

#### rule.xml中分片规则配置：

#### 分片规则属性含义：

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3

```
<tableRule name="sharding-by-substring">
<rule>
<columns>id</columns>
<algorithm>sharding-by-substring</algorithm>
</rule>
</tableRule>
```
```
<function name="sharding-by-substring"
class="io.mycat.route.function.PartitionDirectBySubString">
<property name="startIndex"> 0 </property> <!-- zero-based -->
<property name="size"> 2 </property>
<property name="partitionCount"> 3 </property>
<property name="defaultPartition"> 0 </property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13


#### 示例说明 :

#### id=05-100000002 , 在此配置中代表根据id中从 startIndex=0，开始，截取siz=2位数字即

#### 05 ， 05 就是获取的分区，如果没找到对应的分片则默认分配到defaultPartition 。

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

#### 3.5.3.6 固定分片hash算法

#### 1). 介绍

#### 该算法类似于十进制的求模运算，但是为二进制的操作，例如，取 id 的二进制低 10 位 与

#### 1111111111 进行位 & 运算，位与运算最小值为 0000000000 ，最大值为 1111111111 ，转换为十

#### 进制，也就是位于0-1023之间。

```
CREATE TABLE tb_app (
id varchar( 10 ) NOT NULL COMMENT 'ID',
name varchar( 200 ) DEFAULT NULL COMMENT '名称',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
```
insert into tb_app (id,name) values('0000001','Testx00001');
insert into tb_app (id,name) values('0100001','Test100001');
insert into tb_app (id,name) values('0100002','Test200001');
insert into tb_app (id,name) values('0200001','Test300001');
insert into tb_app (id,name) values('0200002','TesT400001');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11


#### 特点：

#### 如果是求模，连续的值，分别分配到各个不同的分片；但是此算法会将连续的值可能分配到相同的

#### 分片，降低事务处理的难度。

#### 可以均匀分配，也可以非均匀分配。

#### 分片字段必须为数字类型。

#### 2). 配置

#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

```
<!-- 固定分片hash算法 -->
<table name="tb_longhash" dataNode="dn4,dn5,dn6" rule="sharding-by-long-hash" />
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3


#### 属性 描述

#### columns 标识将要分片的表字段名

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### partitionCount 分片个数列表

#### partitionLength 分片范围列表

#### 分片规则属性含义：

#### 约束 :

#### 1). 分片长度 : 默认最大2^10 , 为 1024 ;

#### 2). count, length的数组长度必须是一致的 ;

#### 以上分为三个分区:0-255,256-511,512-1023

#### 示例说明 :

```
<tableRule name="sharding-by-long-hash">
<rule>
<columns>id</columns>
<algorithm>sharding-by-long-hash</algorithm>
</rule>
</tableRule>
```
```
<!-- 分片总长度为 1024 ，count与length数组长度必须一致； -->
<function name="sharding-by-long-hash"
class="io.mycat.route.function.PartitionByLong">
<property name="partitionCount">2,1</property>
<property name="partitionLength">256,512</property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12


#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

#### 3.5.3.7 字符串hash解析算法

#### 1). 介绍

#### 截取字符串中的指定位置的子字符串, 进行hash算法， 算出分片。

```
CREATE TABLE tb_longhash (
id int( 11 ) NOT NULL COMMENT 'ID',
name varchar( 200 ) DEFAULT NULL COMMENT '名称',
firstChar char( 1 ) COMMENT '首字母',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
```
insert into tb_longhash (id,name,firstChar) values( 1 ,'七匹狼','Q');
insert into tb_longhash (id,name,firstChar) values( 2 ,'八匹狼','B');
insert into tb_longhash (id,name,firstChar) values( 3 ,'九匹狼','J');
insert into tb_longhash (id,name,firstChar) values( 4 ,'十匹狼','S');
insert into tb_longhash (id,name,firstChar) values( 5 ,'六匹狼','L');
insert into tb_longhash (id,name,firstChar) values( 6 ,'五匹狼','W');
insert into tb_longhash (id,name,firstChar) values( 7 ,'四匹狼','S');
insert into tb_longhash (id,name,firstChar) values( 8 ,'三匹狼','S');
insert into tb_longhash (id,name,firstChar) values( 9 ,'两匹狼','L');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16


#### 2). 配置

#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

#### 分片规则属性含义：

```
<!-- 字符串hash解析算法 -->
<table name="tb_strhash" dataNode="dn4,dn5" rule="sharding-by-stringhash" />
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
```
###### 1

###### 2

```
<tableRule name="sharding-by-stringhash">
<rule>
<columns>name</columns>
<algorithm>sharding-by-stringhash</algorithm>
</rule>
</tableRule>
```
```
<function name="sharding-by-stringhash"
class="io.mycat.route.function.PartitionByString">
<property name="partitionLength"> 512 </property> <!-- zero-based -->
<property name="partitionCount"> 2 </property>
<property name="hashSlice">0:2</property>
</function>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### partitionLength hash求模基数 ; length*count=1024 (出于性能考虑)

#### partitionCount 分区数

#### hashSlice

#### hash运算位 , 根据子字符串的hash运算 ; 0 代表 str.length()

#### , -1 代表 str.length()-1 , 大于 0 只代表数字自身 ; 可以理解

#### 为substring（start，end），start为 0 则只表示 0

#### 示例说明：

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

#### 3.5.3.8 按天分片算法

```
create table tb_strhash(
name varchar( 20 ) primary key,
content varchar( 100 )
)engine=InnoDB DEFAULT CHARSET=utf8mb4;
```
```
INSERT INTO tb_strhash (name,content) VALUES('T1001', UUID());
INSERT INTO tb_strhash (name,content) VALUES('ROSE', UUID());
INSERT INTO tb_strhash (name,content) VALUES('JERRY', UUID());
INSERT INTO tb_strhash (name,content) VALUES('CRISTINA', UUID());
INSERT INTO tb_strhash (name,content) VALUES('TOMCAT', UUID());
```
###### 1 2 3 4 5 6 7 8 9

###### 10


#### 1). 介绍

#### 按照日期及对应的时间周期来分片。

#### 2). 配置

#### schema.xml中逻辑表配置：

#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

###### <!-- 按天分片 -->

```
<table name="tb_datepart" dataNode="dn4,dn5,dn6" rule="sharding-by-date" />
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3

```
<tableRule name="sharding-by-date">
<rule>
<columns>create_time</columns>
<algorithm>sharding-by-date</algorithm>
</rule>
</tableRule>
```
###### 1 2 3 4 5 6 7


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### dateFormat 日期格式

#### sBeginDate 开始日期

#### sEndDate 结束日期，如果配置了结束日期，则代码数据到达了这个日期的分片后，会重

#### 复从开始分片插入

#### sPartionDay 分区天数，默认值 10 ，从开始日期算起，每个 10 天一个分区

#### 分片规则属性含义：

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

```
<function name="sharding-by-date"
class="io.mycat.route.function.PartitionByDate">
<property name="dateFormat">yyyy-MM-dd</property>
<property name="sBeginDate">2022-01-01</property>
<property name="sEndDate">2022-01-30</property>
<property name="sPartionDay"> 10 </property>
</function>
<!--
从开始时间开始，每 10 天为一个分片，到达结束时间之后，会重复开始分片插入
配置表的 dataNode 的分片，必须和分片规则数量一致，例如 2022-01-01 到 2022-12-31 ，每
10 天一个分片，一共需要 37 个分片。
-->
```
###### 8

###### 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16

###### 17


#### 3.5.3.9 自然月分片

#### 1). 介绍

#### 使用场景为按照月份来分片, 每个自然月为一个分片。

#### 2). 配置

#### schema.xml中逻辑表配置：

```
create table tb_datepart(
id bigint not null comment 'ID' primary key,
name varchar( 100 ) null comment '姓名',
create_time date null
);
```
```
insert into tb_datepart(id,name ,create_time) values( 1 ,'Tom','2022-01-01');
insert into tb_datepart(id,name ,create_time) values( 2 ,'Cat','2022-01-10');
insert into tb_datepart(id,name ,create_time) values( 3 ,'Rose','2022-01-11');
insert into tb_datepart(id,name ,create_time) values( 4 ,'Coco','2022-01-20');
insert into tb_datepart(id,name ,create_time) values( 5 ,'Rose2','2022-01-21');
insert into tb_datepart(id,name ,create_time) values( 6 ,'Coco2','2022-01-30');
insert into tb_datepart(id,name ,create_time) values( 7 ,'Coco3','2022-01-31');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13


#### schema.xml中数据节点配置：

#### rule.xml中分片规则配置：

#### 分片规则属性含义：

###### <!-- 按自然月分片 -->

```
<table name="tb_monthpart" dataNode="dn4,dn5,dn6" rule="sharding-by-month" />
```
###### 1

###### 2

```
<dataNode name="dn4" dataHost="dhost1" database="itcast" />
<dataNode name="dn5" dataHost="dhost2" database="itcast" />
<dataNode name="dn6" dataHost="dhost3" database="itcast" />
```
###### 1

###### 2

###### 3

```
<tableRule name="sharding-by-month">
<rule>
<columns>create_time</columns>
<algorithm>partbymonth</algorithm>
</rule>
</tableRule>
```
```
<function name="partbymonth" class="io.mycat.route.function.PartitionByMonth">
<property name="dateFormat">yyyy-MM-dd</property>
<property name="sBeginDate">2022-01-01</property>
<property name="sEndDate">2022-03-31</property>
</function>
<!--
从开始时间开始，一个月为一个分片，到达结束时间之后，会重复开始分片插入
配置表的 dataNode 的分片，必须和分片规则数量一致，例如 2022-01-01 到 2022-12-31 ，一
共需要 12 个分片。
-->
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15

###### 16


#### 属性 描述

#### columns 标识将要分片的表字段

#### algorithm 指定分片函数与function的对应关系

#### class 指定该分片算法对应的类

#### dateFormat 日期格式

#### sBeginDate 开始日期

#### sEndDate 结束日期，如果配置了结束日期，则代码数据到达了这个日期的分片后，会重复

#### 从开始分片插入

#### 3). 测试

#### 配置完毕后，重新启动MyCat，然后在mycat的命令行中，执行如下SQL创建表、并插入数据，查看数

#### 据分布情况。

## 3.6 MyCat管理及监控

```
create table tb_monthpart(
id bigint not null comment 'ID' primary key,
name varchar( 100 ) null comment '姓名',
create_time date null
);
```
```
insert into tb_monthpart(id,name ,create_time) values( 1 ,'Tom','2022-01-01');
insert into tb_monthpart(id,name ,create_time) values( 2 ,'Cat','2022-01-10');
insert into tb_monthpart(id,name ,create_time) values( 3 ,'Rose','2022-01-31');
insert into tb_monthpart(id,name ,create_time) values( 4 ,'Coco','2022-02-20');
insert into tb_monthpart(id,name ,create_time) values( 5 ,'Rose2','2022-02-25');
insert into tb_monthpart(id,name ,create_time) values( 6 ,'Coco2','2022-03-10');
insert into tb_monthpart(id,name ,create_time) values( 7 ,'Coco3','2022-03-31');
insert into tb_monthpart(id,name ,create_time) values( 8 ,'Coco4','2022-04-10');
insert into tb_monthpart(id,name ,create_time) values( 9 ,'Coco5','2022-04-30');
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13

###### 14

###### 15


### 3.6.1 MyCat原理

#### 在MyCat中，当执行一条SQL语句时，MyCat需要进行SQL解析、分片分析、路由分析、读写分离分析

#### 等操作，最终经过一系列的分析决定将当前的SQL语句到底路由到那几个(或哪一个)节点数据库，数据

#### 库将数据执行完毕后，如果有返回的结果，则将结果返回给MyCat，最终还需要在MyCat中进行结果合

#### 并、聚合处理、排序处理、分页处理等操作，最终再将结果返回给客户端。

#### 而在MyCat的使用过程中，MyCat官方也提供了一个管理监控平台MyCat-Web（MyCat-eye）。

#### Mycat-web 是 Mycat 可视化运维的管理和监控平台，弥补了 Mycat 在监控上的空白。帮 Mycat

#### 分担统计任务和配置管理任务。Mycat-web 引入了 ZooKeeper 作为配置中心，可以管理多个节

#### 点。Mycat-web 主要管理和监控 Mycat 的流量、连接、活动线程和内存等，具备 IP 白名单、邮

#### 件告警等模块，还可以统计 SQL 并分析慢 SQL 和高频 SQL 等。为优化 SQL 提供依据。

### 3.6.2 MyCat管理

#### Mycat默认开通 2 个端口，可以在server.xml中进行修改。

#### 8066 数据访问端口，即进行 DML 和 DDL 操作。

#### 9066 数据库管理端口，即 mycat 服务管理控制功能，用于管理mycat的整个集群状态

#### 连接MyCat的管理控制台：

```
1 mysql -h 192.168.200.210 -p 9066 -uroot -p123456
```

#### 命令 含义

#### show @@help 查看Mycat管理工具帮助文档

#### show @@version 查看Mycat的版本

#### reload @@config 重新加载Mycat的配置文件

#### show @@datasource 查看Mycat的数据源信息

#### show @@datanode 查看MyCat现有的分片节点信息

#### show @@threadpool 查看Mycat的线程池信息

#### show @@sql 查看执行的SQL

#### show @@sql.sum 查看执行的SQL统计

### 3.6.3 MyCat-eye

#### 3.6.3.1 介绍

#### Mycat-web(Mycat-eye)是对mycat-server提供监控服务，功能不局限于对mycat-server使

#### 用。他通过JDBC连接对Mycat、Mysql监控，监控远程服务器(目前仅限于linux系统)的cpu、内

#### 存、网络、磁盘。

#### Mycat-eye运行过程中需要依赖zookeeper，因此需要先安装zookeeper。

#### 3.6.3.2 安装

#### 1). zookeeper安装

#### 2). Mycat-web安装

#### 具体的安装步骤，请参考资料中提供的《MyCat-Web安装文档》

#### 3.6.3.3 访问

#### http://192.168.200.210:8082/mycat


#### 3.6.3.4 配置

#### 1). 开启MyCat的实时统计功能(server.xml)

#### 2). 在Mycat监控界面配置服务地址

#### 3.6.3.5 测试

#### 配置好了之后，我们可以通过MyCat执行一系列的增删改查的测试，然后过一段时间之后，打开

#### mycat-eye的管理界面，查看mycat-eye监控到的数据信息。

#### A. 性能监控

```
1 <property name="useSqlStat"> 1 </property> <!-- 1为开启实时统计、 0 为关闭 -->
```

#### B. 物理节点

#### C. SQL统计


#### D. SQL表分析

#### E. SQL监控

#### F. 高频SQL


# 4. 读写分离

## 4.1 介绍

#### 读写分离,简单地说是把对数据库的读和写操作分开,以对应不同的数据库服务器。主数据库提供写操

#### 作，从数据库提供读操作，这样能有效地减轻单台数据库的压力。

#### 通过MyCat即可轻易实现上述功能，不仅可以支持MySQL，也可以支持Oracle和SQL Server。

## 4.2 一主一从

## 4.2.1 原理

#### MySQL的主从复制，是基于二进制日志（binlog）实现的。


#### 主机 角色 用户名 密码

#### 192.168.200.211 master root 1234

#### 192.168.200.212 slave root 1234

### 4.2.2 准备

#### 备注：主从复制的搭建，可以参考前面课程中 主从复制 章节讲解的步骤操作。

## 4.3 一主一从读写分离

#### MyCat控制后台数据库的读写分离和负载均衡由schema.xml文件datahost标签的balance属性控

#### 制。

### 4.3.1 schema.xml配置


#### 上述配置的具体关联对应情况如下：

#### writeHost代表的是写操作对应的数据库，readHost代表的是读操作对应的数据库。 所以我们要想

#### 实现读写分离，就得配置writeHost关联的是主库，readHost关联的是从库。

#### 而仅仅配置好了writeHost以及readHost还不能完成读写分离，还需要配置一个非常重要的负责均衡

#### 的参数 balance，取值有 4 种，具体含义如下：

###### <!-- 配置逻辑库 -->

```
<schema name="ITCAST_RW" checkSQLschema="true" sqlMaxLimit="100" dataNode="dn7">
</schema>
```
```
<dataNode name="dn7" dataHost="dhost7" database="itcast" />
```
```
<dataHost name="dhost7" maxCon="1000" minCon="10" balance="1" writeType="0"
dbType="mysql" dbDriver="jdbc" switchType="1" slaveThreshold="100">
<heartbeat>select user()</heartbeat>
```
```
<writeHost host="master1" url="jdbc:mysql://192.168.200.211:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" >
<readHost host="slave1" url="jdbc:mysql://192.168.200.212:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</writeHost>
</dataHost>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13


#### 参数

#### 值 含义

#### 0 不开启读写分离机制 , 所有读操作都发送到当前可用的writeHost上

#### 1 全部的readHost 与^ 备用的writeHost 都参与select 语句的负载均衡（主要针对

#### 于双主双从模式）

#### 2 所有的读写操作都随机在writeHost , readHost上分发

#### 3

#### 所有的读请求随机分发到writeHost对应的readHost上执行, writeHost不负担读压

#### 力

#### 所以，在一主一从模式的读写分离中，balance配置 1 或 3 都是可以完成读写分离的。

### 4.3.2 server.xml配置

#### 配置root用户可以访问SHOPPING、ITCAST 以及 ITCAST_RW逻辑库。

### 4.3.3 测试

#### 配置完毕MyCat后，重新启动MyCat。

```
<user name="root" defaultAccount="true">
<property name="password"> 123456 </property>
<property name="schemas">SHOPPING,ITCAST,ITCAST_RW</property>
```
```
<!-- 表级 DML 权限设置 -->
<!--
<privileges check="true">
<schema name="DB01" dml="0110" >
<table name="TB_ORDER" dml="1110"></table>
</schema>
</privileges>
-->
</user>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13


#### 然后观察，在执行增删改操作时，对应的主库及从库的数据变化。 在执行查询操作时，检查主库及从

#### 库对应的数据变化。

#### 在测试中，我们可以发现当主节点Master宕机之后，业务系统就只能够读，而不能写入数据了。

#### 那如何解决这个问题呢？这个时候我们就得通过另外一种主从复制结构来解决了，也就是我们接下来讲

#### 解的双主双从。

## 4.4 双主双从

### 4.4.1 介绍

#### 一个主机 Master1 用于处理所有写请求，它的从机 Slave1 和另一台主机 Master2 还有它的从

#### 机 Slave2 负责所有读请求。当 Master1 主机宕机后，Master2 主机负责写请求，Master1 、

#### Master2 互为备机。架构图如下:

### 4.4.2 准备

```
bin/mycat stop
bin/mycat start
```
###### 1

###### 2


#### 编号 IP 预装软件 角色

#### 1 192.168.200.210 MyCat、MySQL MyCat中间件服务器

#### 2 192.168.200.211 MySQL M1

#### 3 192.168.200.212 MySQL S1

#### 4 192.168.200.213 MySQL M2

#### 5 192.168.200.214 MySQL S2

#### 我们需要准备 5 台服务器，具体的服务器及软件安装情况如下：

#### 关闭以上所有服务器的防火墙：

#### systemctl stop firewalld

#### systemctl disable firewalld

### 4.4.3 搭建

#### 4.4.3.1 主库配置

#### 1). Master1(192.168.200.211)


#### A. 修改配置文件 /etc/my.cnf

#### B. 重启MySQL服务器

#### C. 创建账户并授权

#### 通过指令，查看两台主库的二进制日志坐标

#### 2). Master2(192.168.200.213)

```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 2^32-1，默认为 1
server-id= 1
#指定同步的数据库
binlog-do-db=db01
binlog-do-db=db02
binlog-do-db=db03
# 在作为从数据库的时候，有写入操作也要更新二进制日志文件
log-slave-updates
```
###### 1 2 3 4 5 6 7 8

```
1 systemctl restart mysqld
```
```
#创建itcast用户，并设置密码，该用户可在任意主机连接该MySQL服务
CREATE USER 'itcast'@'%' IDENTIFIED WITH mysql_native_password BY 'Root@123456'
;
#为 'itcast'@'%' 用户分配主从复制权限
GRANT REPLICATION SLAVE ON *.* TO 'itcast'@'%';
```
###### 1

###### 2

###### 3

###### 4

```
1 show master status ;
```

#### A. 修改配置文件 /etc/my.cnf

#### B. 重启MySQL服务器

#### C. 创建账户并授权

```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 2^32-1，默认为 1
server-id= 3
#指定同步的数据库
binlog-do-db=db01
binlog-do-db=db02
binlog-do-db=db03
# 在作为从数据库的时候，有写入操作也要更新二进制日志文件
log-slave-updates
```
###### 1 2 3 4 5 6 7 8

```
1 systemctl restart mysqld
```
```
#创建itcast用户，并设置密码，该用户可在任意主机连接该MySQL服务
CREATE USER 'itcast'@'%' IDENTIFIED WITH mysql_native_password BY 'Root@123456'
;
#为 'itcast'@'%' 用户分配主从复制权限
GRANT REPLICATION SLAVE ON *.* TO 'itcast'@'%';
```
###### 1

###### 2

###### 3

###### 4


#### 通过指令，查看两台主库的二进制日志坐标

#### 4.4.3.2 从库配置

#### 1). Slave1(192.168.200.212)

#### A. 修改配置文件 /etc/my.cnf

#### B. 重新启动MySQL服务器

```
1 show master status ;
```
```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 232-1，默认为 1
server-id= 2
```
###### 1

###### 2

```
1 systemctl restart mysqld
```

#### 2). Slave2(192.168.200.214)

#### A. 修改配置文件 /etc/my.cnf

#### B. 重新启动MySQL服务器

#### 4.4.3.3 从库关联主库

#### 1). 两台从库配置关联的主库

```
#mysql 服务ID，保证整个集群环境中唯一，取值范围：1 – 232-1，默认为 1
server-id= 4
```
###### 1

###### 2

```
1 systemctl restart mysqld
```

#### 需要注意slave1对应的是master1，slave2对应的是master2。

#### A. 在 slave1(192.168.200.212)上执行

#### B. 在 slave2(192.168.200.214)上执行

#### C. 启动两台从库主从复制，查看从库状态

```
CHANGE MASTER TO MASTER_HOST='192.168.200.211', MASTER_USER='itcast',
MASTER_PASSWORD='Root@123456', MASTER_LOG_FILE='binlog.000002',
MASTER_LOG_POS= 663 ;
```
###### 1

```
CHANGE MASTER TO MASTER_HOST='192.168.200.213', MASTER_USER='itcast',
MASTER_PASSWORD='Root@123456', MASTER_LOG_FILE='binlog.000002',
MASTER_LOG_POS= 663 ;
```
###### 1

```
start slave;
show slave status \G;
```
###### 1

###### 2


#### 2). 两台主库相互复制

#### A. 在 Master1(192.168.200.211)上执行

```
1 Master2 复制 Master1，Master1 复制 Master2。
```
```
CHANGE MASTER TO MASTER_HOST='192.168.200.213', MASTER_USER='itcast',
MASTER_PASSWORD='Root@123456', MASTER_LOG_FILE='binlog.000002',
MASTER_LOG_POS= 663 ;
```
###### 1


#### B. 在 Master2(192.168.200.213)上执行

#### C. 启动两台从库主从复制，查看从库状态

#### 经过上述的三步配置之后，双主双从的复制结构就已经搭建完成了。 接下来，我们可以来测试验证一

#### 下。

### 4.4.4 测试

#### 分别在两台主库Master1、Master2上执行DDL、DML语句，查看涉及到的数据库服务器的数据同步情

#### 况。

```
CHANGE MASTER TO MASTER_HOST='192.168.200.211', MASTER_USER='itcast',
MASTER_PASSWORD='Root@123456', MASTER_LOG_FILE='binlog.000002',
MASTER_LOG_POS= 663 ;
```
###### 1

```
start slave;
show slave status \G;
```
###### 1

###### 2

```
create database db01;
use db01;
create table tb_user(
id int( 11 ) not null primary key ,
name varchar( 50 ) not null,
sex varchar( 1 )
)engine=innodb default charset=utf8mb4;
```
```
insert into tb_user(id,name,sex) values( 1 ,'Tom','1');
```
###### 1 2 3 4 5 6 7 8 9


#### 在Master1中执行DML、DDL操作，看看数据是否可以同步到另外的三台数据库中。

#### 在Master2中执行DML、DDL操作，看看数据是否可以同步到另外的三台数据库中。

#### 完成了上述双主双从的结构搭建之后，接下来，我们再来看看如何完成这种双主双从的读写分离。

## 4.5 双主双从读写分离

### 4.5.1 配置

#### MyCat控制后台数据库的读写分离和负载均衡由schema.xml文件datahost标签的balance属性控

#### 制，通过writeType及switchType来完成失败自动切换的。

#### 1). schema.xml

#### 配置逻辑库：

#### 配置数据节点：

#### 配置节点主机：

```
insert into tb_user(id,name,sex) values( 2 ,'Trigger','0');
insert into tb_user(id,name,sex) values( 3 ,'Dawn','1');
insert into tb_user(id,name,sex) values( 4 ,'Jack Ma','1');
insert into tb_user(id,name,sex) values( 5 ,'Coco','0');
insert into tb_user(id,name,sex) values( 6 ,'Jerry','1');
```
###### 10

###### 11

###### 12

###### 13

###### 14

```
<schema name="ITCAST_RW2" checkSQLschema="true" sqlMaxLimit="100" dataNode="dn7">
</schema>
```
###### 1

###### 2

```
1 <dataNode name="dn7" dataHost="dhost7" database="db01" />
```

#### 具体的对应情况如下：

#### 属性说明：

#### balance="1"

#### 代表全部的 readHost 与 stand by writeHost 参与 select 语句的负载均衡，简

#### 单的说，当双主双从模式(M1->S1，M2->S2，并且 M1 与 M2 互为主备)，正常情况下，

#### M2,S1,S2 都参与 select 语句的负载均衡 ;

```
<dataHost name="dhost7" maxCon="1000" minCon="10" balance="1" writeType="0"
dbType="mysql" dbDriver="jdbc" switchType="1" slaveThreshold="100">
<heartbeat>select user()</heartbeat>
```
```
<writeHost host="master1" url="jdbc:mysql://192.168.200.211:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" >
<readHost host="slave1" url="jdbc:mysql://192.168.200.212:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</writeHost>
```
```
<writeHost host="master2" url="jdbc:mysql://192.168.200.213:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" >
<readHost host="slave2" url="jdbc:mysql://192.168.200.214:3306?
useSSL=false&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf8"
user="root" password="1234" />
</writeHost>
</dataHost>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11


#### writeType

#### 0 : 写操作都转发到第 1 台writeHost, writeHost1挂了, 会切换到writeHost2上;

#### 1 : 所有的写操作都随机地发送到配置的writeHost上 ;

#### switchType

#### -1 : 不自动切换

#### 1 : 自动切换

#### 2). user.xml

#### 配置root用户也可以访问到逻辑库 ITCAST_RW2。

### 4.5.2 测试

#### 登录MyCat，测试查询及更新操作，判定是否能够进行读写分离，以及读写分离的策略是否正确。

#### 当主库挂掉一个之后，是否能够自动切换。

```
<user name="root" defaultAccount="true">
<property name="password"> 123456 </property>
<property name="schemas">SHOPPING,ITCAST,ITCAST_RW2</property>
```
```
<!-- 表级 DML 权限设置 -->
<!--
<privileges check="true">
<schema name="DB01" dml="0110" >
<table name="TB_ORDER" dml="1110"></table>
</schema>
</privileges>
-->
</user>
```
###### 1 2 3 4 5 6 7 8 9

###### 10

###### 11

###### 12

###### 13