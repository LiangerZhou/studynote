> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/weixin_40753536/article/details/81285046

使用注解的优势：

1. 采用纯 java 代码，不在需要配置繁杂的 xml 文件

2. 在配置中也可享受面向对象带来的好处

3. 类型安全对重构可以提供良好的支持

4. 减少复杂配置文件的同时亦能享受到 springIoC 容器提供的功能

**一、注解详解（配备了完善的释义****）------(可采用 ctrl+F 来进行搜索哦~~~~)**

@SpringBootApplication：申明让 spring boot 自动给程序进行必要的配置，这个配置等同于：

@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。

@ResponseBody：表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用，用于构建 RESTful 的 api。在使用 @RequestMapping 后，返回值通常解析为跳转路径，加上 @esponsebody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。比如异步获取 json 数据，加上 @Responsebody 后，会直接返回 json 数据。该注解一般会配合 @RequestMapping 一起使用。

@Controller：用于定义控制器类，在 spring 项目中由控制器负责将用户发来的 URL 请求转发到对应的服务接口（service 层），一般这个注解在类中，通常方法需要配合注解 @RequestMapping。

@RestController：用于标注控制层组件 (如 struts 中的 action)，@ResponseBody 和 @Controller 的合集。

@RequestMapping：提供路由信息，负责 URL 到 Controller 中的具体函数的映射。

@EnableAutoConfiguration：SpringBoot 自动配置（auto-configuration）：尝试根据你添加的 jar 依赖自动配置你的 Spring 应用。例如，如果你的 classpath 下存在 HSQLDB，并且你没有手动配置任何数据库连接 beans，那么我们将自动配置一个内存型（in-memory）数据库”。你可以将 @EnableAutoConfiguration 或者 @SpringBootApplication 注解添加到一个 @Configuration 类上来选择自动配置。如果发现应用了你不想要的特定自动配置类，你可以使用 @EnableAutoConfiguration 注解的排除属性来禁用它们。

@ComponentScan：表示将该类自动发现扫描组件。个人理解相当于，如果扫描到有 @Component、@Controller、@Service 等这些注解的类，并注册为 Bean，可以自动收集所有的 Spring 组件，包括 @Configuration 类。我们经常使用 @ComponentScan 注解搜索 beans，并结合 @Autowired 注解导入。可以自动收集所有的 Spring 组件，包括 @Configuration 类。我们经常使用 @ComponentScan 注解搜索 beans，并结合 @Autowired 注解导入。如果没有配置的话，Spring Boot 会扫描启动类所在包下以及子包下的使用了 @Service,@Repository 等注解的类。

@Configuration：相当于传统的 xml 配置文件，如果有些第三方库需要用到 xml 文件，建议仍然通过 @Configuration 类作为项目的配置主类——可以使用 @ImportResource 注解加载 xml 配置文件。

@Import：用来导入其他配置类。

@ImportResource：用来加载 xml 配置文件。

@Autowired：自动导入依赖的 bean

@Service：一般用于修饰 service 层的组件

@Repository：使用 @Repository 注解可以确保 DAO 或者 repositories 提供异常转译，这个注解修饰的 DAO 或者 repositories 类会被 ComponetScan 发现并配置，同时也不需要为它们提供 XML 配置项。

@Bean：用 @Bean 标注方法等价于 XML 中配置的 bean。

@Value：注入 Spring boot application.properties 配置的属性的值。示例代码：

@Inject：等价于默认的 @Autowired，只是没有 required 属性；

@Component：泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。

@Bean: 相当于 XML 中的, 放在方法的上面，而不是类，意思是产生一个 bean, 并交给 spring 管理。

@AutoWired：自动导入依赖的 bean。byType 方式。把配置好的 Bean 拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。当加上（required=false）时，就算找不到 bean 也不报错。

@Qualifier：当有多个同一类型的 Bean 时，可以用 @Qualifier(“name”) 来指定。与 @Autowired 配合使用。@Qualifier 限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者，具体使用方式如下：

@Resource(name=”name”,type=”type”)：没有括号内内容的话，默认 byName。与 @Autowired 干类似的事。

**二、注解列表如下**

@SpringBootApplication：包含了 @ComponentScan、@Configuration 和 @EnableAutoConfiguration 注解。其中

@ComponentScan：让 spring Boot 扫描到 Configuration 类并把它加入到程序上下文。

@Configuration ：等同于 spring 的 XML 配置文件；使用 Java 代码可以检查类型安全。

@EnableAutoConfiguration ：自动配置。

@ComponentScan ：组件扫描，可自动发现和装配一些 Bean。

@Component 可配合 CommandLineRunner 使用，在程序启动后执行一些基础任务。

@RestController：注解是 @Controller 和 @ResponseBody 的合集, 表示这是个控制器 bean, 并且是将函数的返回值直 接填入 HTTP 响应体中, 是 REST 风格的控制器。

@Autowired：自动导入。

@PathVariable：获取参数。

@JsonBackReference：解决嵌套外链问题。

@RepositoryRestResourcepublic：配合 spring-boot-starter-data-rest 使用。

**三、JPA 注解**

@Entity：@Table(name=”“)：表明这是一个实体类。一般用于 jpa 这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table 可以省略

@MappedSuperClass: 用在确定是父类的 entity 上。父类的属性子类可以继承。

@NoRepositoryBean: 一般用作父类的 repository，有这个注解，spring 不会去实例化该 repository。

@Column：如果字段名与列名相同，则可以省略。

@Id：表示该属性为主键。

@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)：表示主键生成策略是 sequence（可以为 Auto、IDENTITY、native 等，Auto 表示可在多个数据库间切换），指定 sequence 的名字是 repair_seq。

@SequenceGeneretor(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1)：name 为 sequence 的名称，以便使用，sequenceName 为数据库的 sequence 名称，两个名称可以一致。

@Transient：表示该属性并非一个到数据库表的字段的映射, ORM 框架将忽略该属性。如果一个属性并非数据库表的字段映射, 就务必将其标示为 @Transient, 否则, ORM 框架默认其注解为 @Basic。@Basic(fetch=FetchType.LAZY)：标记可以指定实体属性的加载方式

@JsonIgnore：作用是 json 序列化时将 [Java](http://lib.csdn.net/base/java)bean 中的一些属性忽略掉, 序列化和反序列化都受影响。

@JoinColumn（name=”loginId”）: 一对一：本表中指向另一个表的外键。一对多：另一个表指向本表的外键。

@OneToOne、@OneToMany、@ManyToOne：对应 [hibernate](http://lib.csdn.net/base/javaee) 配置文件中的一对一，一对多，多对一。

**四、springMVC 相关注解**

@RequestMapping：@RequestMapping(“/path”)表示该控制器处理所有 “/path” 的 UR L 请求。RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上。  
用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。该注解有六个属性：  
params: 指定 request 中必须包含某些参数值是，才让该方法处理。  
headers: 指定 request 中必须包含某些指定的 header 值，才能让该方法处理请求。  
value: 指定请求的实际地址，指定的地址可以是 URI Template 模式  
method: 指定请求的 method 类型， GET、POST、PUT、DELETE 等  
consumes: 指定处理请求的提交内容类型（Content-Type），如 application/json,text/html;  
produces: 指定返回的内容类型，仅当 request 请求头中的 (Accept) 类型中包含该指定类型才返回

@RequestParam：用在方法的参数前面。  
@RequestParam  
String a =request.getParameter(“a”)。

@PathVariable: 路径变量。如

参数与大括号里的名字一样要相同。

**五、全局异常处理**

@ControllerAdvice：包含 @Component。可以被扫描到。统一处理异常。

@ExceptionHandler（Exception.class）：用在方法上面表示遇到这个异常就执行以下方法。

* * *

**六、项目中具体配置解析和使用环境**

### **@MappedSuperclass：**

1.@MappedSuperclass 注解使用在父类上面，是用来标识父类的

2.@MappedSuperclass 标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够映射在其子类对用的数据库表中

3.@MappedSuperclass 标识的类不能再有 @Entity 或 @Table 注解

**@Column：**

1. 当实体的属性与其映射的数据库表的列不同名时需要使用 @Column 标注说明，该属性通常置于实体的属性声明语句之前，还可与 @Id 标注一起使用。

2.@Column 标注的常用属性是 name，用于设置映射数据库表的列名。此外，该标注还包含其它多个属性，如：unique、nullable、length、precision 等。具体如下：

1 name 属性：name 属性定义了被标注字段在数据库表中所对应字段的名称

2 unique 属性：unique 属性表示该字段是否为唯一标识，默认为 false，如果表中有一个字段需要唯一标识，则既可以使用该标记，也可以使用 @Table 注解中的 @UniqueConstraint

3 nullable 属性：nullable 属性表示该字段是否可以为 null 值，默认为 true

4 insertable 属性：insertable 属性表示在使用”INSERT” 语句插入数据时，是否需要插入该字段的值

5 updateable 属性：updateable 属性表示在使用”UPDATE” 语句插入数据时，是否需要更新该字段的值

6 insertable 和 updateable 属性：一般多用于只读的属性，例如主键和外键等，这些字段通常是自动生成的

7 columnDefinition 属性：columnDefinition 属性表示创建表时，该字段创建的 SQL 语句，一般用于通过 Entity 生成表定义时使用，如果数据库中表已经建好，该属性没有必要使用

8 table 属性：table 属性定义了包含当前字段的表名

9 length 属性：length 属性表示字段的长度，当字段的类型为 varchar 时，该属性才有效，默认为 255 个字符

10 precision 属性和 scale 属性：precision 属性和 scale 属性一起表示精度，当字段类型为 double 时，precision 表示数值的总长度，scale 表示小数点所占的位数

    具体如下：  
   1.double 类型将在数据库中映射为 double 类型，precision 和 scale 属性无效  
   2.double 类型若在 columnDefinition 属性中指定数字类型为 decimal 并指定精度，则最终以 columnDefinition 为准  
   3.BigDecimal 类型在数据库中映射为 decimal 类型，precision 和 scale 属性有效  
   4.precision 和 scale 属性只在 BigDecimal 类型中有效

3.@Column 标注的 columnDefinition 属性: 表示该字段在数据库中的实际类型. 通常 ORM 框架可以根据属性类型自动判断数据库中字段的类型, 但是对于 Date 类型仍无法确定数据库中字段类型究竟是 DATE,TIME 还是 TIMESTAMP. 此外, String 的默认映射类型为 VARCHAR, 如果要将 String 类型映射到特定数据库的 BLOB 或 TEXT 字段类型.

4.@Column 标注也可置于属性的 getter 方法之前

@Getter 和 @Setter（Lombok）

@Setter：注解在属性上；为属性提供 setting 方法 @Getter：注解在属性上；为属性提供 getting 方法

  1 @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了 equals、canEqual、hashCode、toString 方法  
  2   
  3 @Setter：注解在属性上；为属性提供 setting 方法  
  4   
  5 @Getter：注解在属性上；为属性提供 getting 方法  
  6   
  7 @Log4j2 ：注解在类上；为类提供一个 属性名为 log 的 log4j 日志对象，和 @Log4j 注解类似  
  8   
  9 @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法  
 10   
 11 @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法  
 12   
 13 @EqualsAndHashCode: 默认情况下，会使用所有非瞬态 (non-transient) 和非静态 (non-static) 字段来生成 equals 和 hascode 方法，也可以指定具体使用哪些属性。  
 14   
 15 @toString: 生成 toString 方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。  
 16   
 17 @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor  
 18 无参构造器、部分参数构造器、全参构造器，当我们需要重载多个构造器的时候，只能自己手写了  
 19   
 20 @NonNull：注解在属性上，如果注解了，就必须不能为 Null  
 21   
 22 @val: 注解在属性上，如果注解了，就是设置为 final 类型，可查看源码的注释知道

当你在执行各种持久化方法的时候，实体的状态会随之改变，状态的改变会引发不同的生命周期事件。这些事件可以使用不同的注释符来指示发生时的回调函数。

@javax.persistence.PostLoad：加载后。

@javax.persistence.PrePersist：持久化前。

@javax.persistence.PostPersist：持久化后。

@javax.persistence.PreUpdate：更新前。

@javax.persistence.PostUpdate：更新后。

@javax.persistence.PreRemove：删除前。

@javax.persistence.PostRemove：删除后。

**1）数据库查询**

@PostLoad 事件在下列情况下触发：

执行 EntityManager.find() 或 getreference() 方法载入一个实体后。

执行 JPQL 查询后。

EntityManager.refresh() 方法被调用后。

**2）数据库插入**

@PrePersist 和 @PostPersist 事件在实体对象插入到数据库的过程中发生：

@PrePersist 事件在调用 persist() 方法后立刻发生，此时的数据还没有真正插入进数据库。

@PostPersist 事件在数据已经插入进数据库后发生。

**3）数据库更新**

@PreUpdate 和 @PostUpdate 事件的触发由更新实体引起：

@PreUpdate 事件在实体的状态同步到数据库之前触发，此时的数据还没有真正更新到数据库。

@PostUpdate 事件在实体的状态同步到数据库之后触发，同步在事务提交时发生。

**4）数据库删除**

@PreRemove 和 @PostRemove 事件的触发由删除实体引起：

@PreRemove 事件在实体从数据库删除之前触发，即在调用 remove() 方法删除时发生，此时的数据还没有真正从数据库中删除。

@PostRemove 事件在实体从数据库中删除后触发。