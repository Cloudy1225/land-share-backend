# 边学边写

## 各种O

VO（View Object）：视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
DTO（Data Transfer Object）：数据传输对象，这个概念来源于J2EE的设计模式，原来的目的是为了EJB的分布式应用提供粗粒度的数据实体，以减少分布式调用的次数，从而提高分布式调用的性能和降低网络负载，但在这里，我泛指用于展示层与服务层之间的数据传输对象。
DO（Domain Object）：领域对象，就是从现实世界中抽象出来的有形或无形的业务实体。
PO（Persistent Object）：持久化对象，它跟持久层（通常是关系型数据库）的数据结构形成一一对应的映射关系，如果持久层是关系型数据库，那么，数据表中的每个字段（或若干个）就对应PO的一个（或若干个）属性。

DTO叫做数据传输对象，用DTO接收前端传来的数据或向前端传递数据是非常合适的。而VO叫做视图对象，只有向表示层传递数据的功能。**DTO代表服务层需要接收的数据和返回的数据，而VO代表展示层需要显示的数据。**

原文链接：https://blog.csdn.net/zjrbiancheng/article/details/6253232



## 各种@

@NoArgsConstructor ： 生成一个无参数的构造方法

@AllArgsContructor： 生成一个包含所有参数的构造方法

@Data : 注解在类上, 为类提供getter,setter, 此外还提供了 equals()、hashCode()、toString() 方法

   好处：不需要自己写构造方法，代码简洁规范



@Autowired：

spring项目中我们经常使用到spring的一个注解@Autowired，这个注解是用于实现spring的特性，IOC（Inversion of Control）控制反转和DI依赖注入，spring将项目中的实体进行集中管理，然后通过@Autowired注解作为标识，以自动注入的方式来给实体注入属性，这些操作都是基于java反射机制来实现的



@RequestMapping是一个用来处理请求[地址映射](https://so.csdn.net/so/search?q=地址映射&spm=1001.2101.3001.7020)的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。https://blog.csdn.net/renanrenan/article/details/84654362/



@Param

首先明确这个注解是为SQL语句中参数赋值而服务的。

      @Param的作用就是给参数命名，比如在mapper里面某方法A（int id），当添加注解后A（@Param("userId") int id），也就是说外部想要取出传入的id值，只需要取它的参数名userId就可以了。将参数值传如SQL语句中，通过#{userId}进行取值给SQL的参数赋值。
------------------------------------------------
版权声明：本文为CSDN博主「CodingLJ」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/Sunshineoe/article/details/114697944

