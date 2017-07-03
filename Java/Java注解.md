# Java注解

## 1. JDK内置系统注解

`@Override` 用于修饰此方法覆盖了父类的方法;

`@Deprecated` 用于修饰已经过时的方法;

`@suppressWarings("deprecation")` 用于通知java编译器忽略特定的编译警告。

## 2. 注解分类

1. 按照运行机制分为:

    源码注解：注解只在源码中存在，编译成.class文件就不存在了

    编译时注解：注解在源码和.class文件中都存在（如：JDK内置系统注解）

    运行时注解：在运行阶段还起作用，甚至会影响运行逻辑的注解（如：Spring中@Autowried）

2. 按照来源分为：JDK内置系统注解、元注解、自定义注解、第三方注解

## 3. 自定义注解
```java
    @Target({ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented	
public @interface Description{//使用@interface关键字注解
	String name();//成员以无参无异常方式声明
	String author();
	int age() default 19;//可以用default为成员变量指定一个默认值
	}
```
1. 成员类型是受限的，合法的类型包括原始类型及String,Class,Annotation,Enumeration
2. 如果注解只有一个成员，则成员名必须取名为Value(),在使用的时可以忽略成员名和赋值号（=）
3. 没有成员的注解称为标识注解
4. 元注解

    @Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})

    // Target 注解的作用域   CONSTRUCTOR 构造方法声明，FIELD 字段声明，LOCAL_VARIABLE 局部变量声明 ，METHOD 方法声明，PACKAGE 包声明，PARAMETER 参数声明，TYPE 类接口。

    @Retention(RetentionPolicy.RUNTIME)

    //Retention 生命周期——SOURCE 只在源码显示，编译时会丢弃；CLASS 编译时会记录到class中，运行时忽略；RUNTIME 运行时存在，可以通过反射读取。

    @Inherited 

    //Inherited 允许子类继承

    @Documented 

    //Documented 生成javadoc的时候包含注解
## 4. 使用自定义注解
使用注解的语法：

@<注解名>(<成员名1>=<成员值1>,<成员名2>=<成员值2>,...)
```java
@Description(desc ="I am eyeColor",author="C boy",age=18)
public String eyeColor(){
	return "red";
}
``` 