> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/fly910905/article/details/86685667 [](http://creativecommons.org/licenses/by-sa/4.0/)版权声明：本文为博主原创文章，遵循 [CC 4.0 BY-SA](http://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接和本声明。 本文链接：[https://blog.csdn.net/fly910905/article/details/86685667](https://blog.csdn.net/fly910905/article/details/86685667)

SQL 是如何注入的
==========

> SQL 注入是目前黑客最常用的攻击手段，它的原理是**利用数据库对特殊标识符的解析强行从页面向后台传入**。改变 SQL 语句结构，达到扩展权限、创建高等级用户、强行修改用户资料等等操作。 

为什么这么说，下面就以 JAVA 为例进行说明：

假设数据库中存在这样的表：

```
table user(
id     varchar(20)    PRIMARY KEY ,        
name     varchar(20)              ,
age     varchar(20)             );
table user(
id     varchar(20)    PRIMARY KEY ,        
name     varchar(20)              ,
age     varchar(20)             );

```

然后使用 JDBC 操作表：

```
private String getNameByUserId(String userId) {
    Connection conn = getConn();//获得连接
    String sql = "select name from user where id=" + userId;
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    ResultSet rs=pstmt.executeUpdate();
    ......
}
private String getNameByUserId(String userId) {
    Connection conn = getConn();//获得连接
    String sql = "select name from user where id=" + userId;
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    ResultSet rs=pstmt.executeUpdate();
    ......
}

```

上面的代码经常被一些开发人员使用。想象这样的情况，**当传入的 userId 参数为 "3;drop table user;" 时**，执行的 sql 语句如下：

```
select name from user where id=3; drop table user;

```

数据库在编译执行之后，删除了 user 表。瞧, 一个简单的 SQL 注入攻击生效了！之所以这样，是因为上面的代码**没有符合编程规范**。

**SQL 注入防御**
============

**预编译语句**
---------

当我们按照规范编程时，SQL 注入就不存在了。这也是避免 **SQL 注入的第一种方式：预编译语句**，代码如下：

```
Connection conn = getConn();//获得连接
String sql = "select name from user where id= ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId);
ResultSet rs=pstmt.executeUpdate();
......
Connection conn = getConn();//获得连接
String sql = "select name from user where id= ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId);
ResultSet rs=pstmt.executeUpdate();
......

```

为什么上面的代码就不存在 SQL 注入了呢？因为**使用了预编译语句**，预编译语句在执行时会把 "select name from user where id= ?" 语句事先编译好，这样**当执行时仅仅需要用传入的参数替换掉？占位符即可**。而**对于第一种不符合规范的情况，程序会先生成 sql 语句，然后带着用户传入的内容去编译，这恰恰是问题所在**。

存储过程
----

除了使用预编译语句之外，还有第二种避免 SQL 注入攻击的方式：存储过程。

> 在一些安全性要求很高的应用中（比如银行软件），经常使用将 **SQL** **语句**全部替换为**存储过程**这样的方式，来防止 SQL 注入。这当然是**一种很安全的方式**，但我们平时开发中，可能不需要这种死板的方式。

**存储过程（Stored Procedure）是一组完成特定功能的 SQL 语句集**，经编译后存储在数据库中，用户通过调用存储过程并给定参数（如果该存储过程带有参数）就可以执行它，也可以避免 SQL 注入攻击

```
Connection conn = getConn();
stmt = conn.prepareCall("{call name_from_user(?,?)}");  
stmt.setInt(1,2);  
stmt.registerOutParameter(2, Types.VARCHAR);  
tmt.execute();  
String name= stmt.getString(2); 
Connection conn = getConn();
stmt = conn.prepareCall("{call name_from_user(?,?)}");  
stmt.setInt(1,2);  
stmt.registerOutParameter(2, Types.VARCHAR);  
tmt.execute();  
String name= stmt.getString(2); 

```

上面的代码中对应的存储过程如下：

```
use user;
delimiter //
create  procedure name_from_user(in user_id int,out user_name varchar(20))
begin
    select name into user_name from user where id=user_id;
end
//
delimiter ;
use user;
delimiter //
create  procedure name_from_user(in user_id int,out user_name varchar(20))
begin
    select name into user_name from user where id=user_id;
end
//
delimiter ;

```

当然用户也可以在前端做字符检查，这也是一种避免 SQL 注入的方式：比如**对于上面的 userId 参数，用户检查到包含分号就提示错误**。  
不过，从最根本的原因看，**SQL 注入攻击之所以存在，是因为 app 在访问数据库时没有使用最小权限**。

想来也是，大家好像一直都在使用 root 账号访问数据库。  
 

Mybatis 是如何防止 SQL 注入
====================

1、首先看一下下面两个 sql 语句的区别：
----------------------

```
<select parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = #{username,jdbcType=VARCHAR}
and password = #{password,jdbcType=VARCHAR}
</select>
<select parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = #{username,jdbcType=VARCHAR}
and password = #{password,jdbcType=VARCHAR}
</select>

```

```
<select parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = ${username,jdbcType=VARCHAR}
and password = ${password,jdbcType=VARCHAR}
</select>
<select parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = ${username,jdbcType=VARCHAR}
and password = ${password,jdbcType=VARCHAR}
</select>

```

### **mybatis 中的 #和 $ 的区别：**

> 1、# 将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。  
> 如：where username=#{username}，如果传入的值是 111, 那么解析成 sql 时的值为 where user, 如果传入的值是 id，则解析成的 sql 为 where user.　
> 
>   
> 2、$ 将传入的数据直接显示生成在 sql 中。  
> 如：where username=${username}，如果传入的值是 111, 那么解析成 sql 时的值为 where username=111；  
> 如果传入的值是; drop table user;，则解析成的 sql 为：select id, username, password, role from user where username=;drop table user;
> 
>   
> 3、# 方式能够很大程度防止 sql 注入，$ 方式无法防止 Sql 注入。
> 
>   
> 4、$ 方式一般用于传入数据库对象，例如传入表名.
> 
>   
> 5、一般能用 #的就别用 $，若不得不使用 “${xxx}” 这样的参数，要手工地做好过滤工作，来防止 sql 注入攻击。
> 
>   
> 6、在 MyBatis 中，**“${xxx}” 这样格式的参数会直接参与 SQL 编译，**从而不能避免注入攻击。但涉及到动态表名和列名时，只能使用 “${xxx}” 这样的参数格式。所以，这样的参数需要我们在代码中手工进行处理来防止注入。
> 
>   
> 【结论】**在编写 MyBatis 的映射语句时，尽量采用 “#{xxx}” 这样的格式**。若不得不使用 “${xxx}” 这样的参数，要手工地做好过滤工作，来防止 SQL 注入攻击。

2、Mybatis 是如何做到防止 sql 注入的
-------------------------

　　[MyBatis](https://mybatis.github.io/mybatis-3/) 框架作为一款半自动化的持久层框架，其 SQL 语句都要我们自己手动编写，这个时候当然需要防止 SQL 注入。其实，MyBatis 的 SQL 是一个具有 “**输入 + 输出**” 的功能，类似于函数的结构，参考上面的两个例子。

       其中，parameterType 表示了输入的参数类型，resultType 表示了输出的参数类型。回应上文，如果我们想防止 SQL 注入，理所当然地要在输入参数上下功夫。上面代码中使用 #的即输入参数在 SQL 中拼接的部分，传入参数后，打印出执行的 SQL 语句，会看到 SQL 是这样的：

```
select id, username, password, role from user where username=? and password=?

```

　　不管输入什么参数，打印出的 SQL 都是这样的。这是因为 **MyBatis 启用了预编译功能**，在 SQL 执行前，会先将上面的 SQL 发送给数据库进行编译；执行时，直接使用编译好的 SQL，替换占位符 “?” 就可以了。**因为 SQL 注入只能对编译过程起作用**，所以这样的方式就很好地避免了 SQL 注入的问题。

【底层实现原理】MyBatis 是如何做到 SQL 预编译的呢？

> 其实在框架底层，是 JDBC 中的 PreparedStatement 类在起作用，PreparedStatement 是我们很熟悉的 Statement 的子类，它的对象包含了编译好的 SQL 语句。
> 
> **这种 “准备好” 的方式不仅能提高安全性，而且在多次执行同一个 SQL 时，能够提高效率。**原因是 SQL 已编译好，再次执行时无需再编译。

### 安全的，预编译了的

```
//安全的，预编译了的
Connection conn = getConn();//获得连接
String sql = "select id, username, password, role from user where id=?"; //执行sql前会预编译号该条语句
PreparedStatement pstmt = conn.prepareStatement(sql); 
pstmt.setString(1, id); 
ResultSet rs=pstmt.executeUpdate(); 
......
//安全的，预编译了的
Connection conn = getConn();//获得连接
String sql = "select id, username, password, role from user where id=?"; //执行sql前会预编译号该条语句
PreparedStatement pstmt = conn.prepareStatement(sql); 
pstmt.setString(1, id); 
ResultSet rs=pstmt.executeUpdate(); 
......

```

### 不安全的，没进行预编译

```
//不安全的，没进行预编译
private String getNameByUserId(String userId) {
    Connection conn = getConn();//获得连接
    String sql = "select id,username,password,role from user where id=" + id;
    //当id参数为"3;drop table user;"时，执行的sql语句如下:
    //select id,username,password,role from user where id=3; drop table user;  
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    ResultSet rs=pstmt.executeUpdate();
    ......
}
//不安全的，没进行预编译
private String getNameByUserId(String userId) {
    Connection conn = getConn();//获得连接
    String sql = "select id,username,password,role from user where id=" + id;
    //当id参数为"3;drop table user;"时，执行的sql语句如下:
    //select id,username,password,role from user where id=3; drop table user;  
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    ResultSet rs=pstmt.executeUpdate();
    ......
}

```

**Mybatis 防止 SQL 注入：结论**：
=========================

<table border="0"><tbody><tr><td>#{}：相当于 JDBC 中的 PreparedStatement</td></tr><tr><td>${}：是输出变量的值</td></tr></tbody></table>

> 简单说，#{} 是经过预编译的，是安全的；${} 是未经过预编译的，仅仅是取变量的值，是非安全的，存在 SQL 注入。  
> 如果我们 order by 语句后用了 ${}，那么不做任何处理的时候是存在 SQL 注入危险的。你说怎么防止，那我只能悲惨的告诉你，你得手动处理过滤一下输入的内容。如**判断一下输入的参数的长度是否正常（注入语句一般很长）**，更精确的过滤则可以查询一下**输入的参数是否在预期的参数集合**中。

**Hibernate 防止 SQL 注入**
=======================

Hibernate 是一个开放源代码的对象关系映射框架，它对 JDBC 进行了非常轻量级的对象封装，使得 Java 程序员可以随心所欲的使用对象编程思维来操纵数据库。

在获取便利操作的同时，SQL 的注入问题也值得我们的密切注意，下面就来谈谈几点如何避免 SQL 注入：

**1. 对参数名称进行绑定：**
-----------------

```
Query query=session.createQuery(hql);
query.setString(“name”,name);
Query query=session.createQuery(hql);
query.setString(“name”,name);

```

**2. 对参数位置进行邦定：**
-----------------

```
Query query=session.createQuery(hql);
query.setString(0,name1);
query.setString(1,name2);
...
Query query=session.createQuery(hql);
query.setString(0,name1);
query.setString(1,name2);
...

```

**3.setParameter() 方法：**
------------------------

```
Query query=session.createQuery(hql); 
query.setParameter(“name”,name,Hibernate.STRING);
Query query=session.createQuery(hql); 
query.setParameter(“name”,name,Hibernate.STRING);

```

**4.setProperties() 方法：**
-------------------------

```
Entity entity=new Entity();
entity.setXx(“xx”);
entity.setYy(100);
Query query=session.createQuery(“from Entity c where c.xx=:xx and c.yy=:yy ”); 
query.setProperties(entity);
Entity entity=new Entity();
entity.setXx(“xx”);
entity.setYy(100);
Query query=session.createQuery(“from Entity c where c.xx=:xx and c.yy=:yy ”); 
query.setProperties(entity);

```

### **5.HQL 拼接方法**

这种方式是最常用，而且容易忽视且容易被注入的，通常做法就是**对参数的特殊字符进行过滤**，推荐大家使用 Spring 工具包的 **StringEscapeUtils.escapeSql()** 方法对参数进行过滤：

```
import org.apache.commons.lang.StringEscapeUtils;
public static void main(String[] args) {
  String str = StringEscapeUtils.escapeSql("'");
  System.out.println(str);
}
import org.apache.commons.lang.StringEscapeUtils;
public static void main(String[] args) {
  String str = StringEscapeUtils.escapeSql("'");
  System.out.println(str);
}

```

### **StringEscapeUtils.escapeSql();**

```
 /**
     * <p>Escapes the characters in a <code>String</code> to be suitable to pass to
     * an SQL query.</p>
     *
     * <p>For example,
     * <pre>statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE='" + 
     *   StringEscapeUtils.escapeSql("McHale's Navy") + 
     *   "'");</pre>
     * </p>
     *
     * <p>At present, this method only turns single-quotes into doubled single-quotes
     * (<code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>). It does not
     * handle the cases of percent (%) or underscore (_) for use in LIKE clauses.</p>
     *
     * see http://www.jguru.com/faq/view.jsp?EID=8881
     * @param str  the string to escape, may be null
     * @return a new String, escaped for SQL, <code>null</code> if null string input
     */
    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return StringUtils.replace(str, "'", "''");
    }
 /**
     * <p>Escapes the characters in a <code>String</code> to be suitable to pass to
     * an SQL query.</p>
     *
     * <p>For example,
     * <pre>statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE='" + 
     *   StringEscapeUtils.escapeSql("McHale's Navy") + 
     *   "'");</pre>
     * </p>
     *
     * <p>At present, this method only turns single-quotes into doubled single-quotes
     * (<code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>). It does not
     * handle the cases of percent (%) or underscore (_) for use in LIKE clauses.</p>
     *
     * see http://www.jguru.com/faq/view.jsp?EID=8881
     * @param str  the string to escape, may be null
     * @return a new String, escaped for SQL, <code>null</code> if null string input
     */
    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return StringUtils.replace(str, "'", "''");
    }

```

>   输出结果：''