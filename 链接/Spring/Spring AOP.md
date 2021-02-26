# AOP

## AOP相关概念

| 序号 | 名称          | 含义                                                                                                                                                                                                                             |
| ---- | ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1    | Aspect        | This is a module which has a set of APIs providing cross-cutting requirements. For example, a logging module would be called AOP aspect for logging. An application can have any number of aspects depending on the requirement. |
| 2    | Join Point    | This represents a point in your application where you can plug-in the AOP aspect. You can also say, it is the actual place in the application where an action will be taken using Spring AOP framework.                          |
| 3    | Advice        | This is the actual action to be taken either before or after the method execution. This is an actual piece of code that is invoked during the program execution by Spring AOP framework.                                         |
| 4    | PointCut      | This is a set of one or more join points where an advice should be executed. You can specify pointcuts using expressions or patterns as we will see in our AOP examples.                                                         |
| 5    | Introduction  | An introduction allows you to add new methods or attributes to the existing classes.                                                                                                                                             |
| 6    | Target Object | The object being advised by one or more aspects. This object will always be a proxied object, also referred to as the advised object.                                                                                            |
| 7    | Weaving       | Weaving is the process of linking aspects with other application types or objects to create an advised object. This can be done at compile time, load time, or at runtime.                                                       |

### Advice类型

Spring aspects有如下五种Advice类型

| 序号 | Advice          | 描述                                                                                   |
| ---- | --------------- | -------------------------------------------------------------------------------------- |
| 1    | before          | Run advice before the a method execution.                                              |
| 2    | after           | Run advice after the method execution, regardless of its outcome.                      |
| 3    | after-returning | Run advice after the a method execution only if method completes successfully.         |
| 4    | after-throwing  | Run advice after the a method execution only if method exits by throwing an exception. |
| 5    | around          | Run advice before and after the advised method is invoked.                             |

### 自定义Aspects实现

Spring支持 **@AspectJ注解**方法 和 **schema-based** 的方法来实现自定义切面。以下详细说明了这两种方法。

| 序号 | 方法             | 描述                                                                                                       |
| ---- | ---------------- | ---------------------------------------------------------------------------------------------------------- |
| 1    | XML Schema based | Aspects are implemented using the regular classes along with XML based configuration.                      |
| 2    | @ApectJ based    | @AspectJ refers to a style of declaring aspects as regular Java classes annotated with Java 5 annotations. |

## Spring AOP的实现

## Spring AOP实现日志

## Spring AOP实现事务

## Spring AOP 和AspectJ对比

### 涉及 静态代理、JDK动态代理、CGLIB代理

## 参考

1.[Java代理](../Java/Java%20代理.md)
2. [spring aop](https://www.tutorialspoint.com/spring/aop_with_spring.htm)
