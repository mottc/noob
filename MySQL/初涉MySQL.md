# 启动和停止MySQL服务

##### 启动MySQL服务：net start mysql

##### 关闭MySQL服务：net stop mysql

(注：所有win服务都可以使用net start/stop <win服务名>进行启动和停止)

# 登录和退出MySQL

##### 登录：`mysql -uroot -p -P3306 -h127.0.0.1`

-u后为用户名， -p可输入明文密码， -P为服务端口号，-h为服务器地址

如果使用默认端口号3306，可以不加-P。

如果使用本地服务器，可以不加-h。

##### 退出：`mysql> exit;` 或 `mysql> quit;` 或 `mysql> \q;`

# 修改MySQL提示符

1. 在登录MySQL时修改

   mysql -uroot -p --prompt ***

2. 已经登录后修改

   prompt ***

MySQL提示符

| 参数   | 描述    |
| ---- | :---- |
| \D   | 完整日期  |
| \d   | 当前数据库 |
| \h   | 服务器名称 |
| \u   | 当前用户  |

把上述***改为\u@\h \d> ，则提示符变为“当前登录的用户名@当前服务器名称  当前打开的数据库名称”。

# MySQL常用命令以及语法规范

##### 常用命令：

显示当前服务器版本：SELECT VERSION();
显示当前日期时间：SELECT NOW();
显示当前用户：SELECT USER();

##### MySQL语句规范：

①关键字和函数名称全部大写
②数据库名称、表名称、字段名称全部小写
③SQL语句必须以分号结尾

# 操作数据库

 ##### 一、数据库创建：CREATE

1. 语法：CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name [DEFAULT] CHARACTER SET [=] charset_name.
2. DATABASE和SCHEMA是相同的，任选其一
3. IF NOT EXISTS:如果创建的数据库存在，则不只报出warning，不写会报错
4. CHRARCTER SET gbk:为表设置编码方式，如果不设置则用mysql默认的编码方式(一般在配置文件中设置默认编码方式为UTF8)

##### 二、查看数据库列表：SHOW

1. SHOW { DATABASES | SCHEMAS } [LIKE 'pattern' | WHERE expr];：查看数据库列表
2. SHOW DATABASES; ：展示所有数据库
3. SHOW CREATE DATABASE xx; ：显示xx数据库信息，展示数据库的创建命令和编码形式
4. SHOW WARNINGS; ：展示警告信息

 ##### 三、数据库的修改：ALTER

1. 修改数据库编码方式：ALTER { DATABASE | SCHEMAS } db_name [DEFAULT] CHARACTER SET [=] charset_name 

##### 四、删除数据库：DROP

1. 删除数据库：DROP { DATABASE | SCHEMAS } [IF EXISTS] db_name;

