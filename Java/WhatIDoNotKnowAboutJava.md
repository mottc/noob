# What I don't kown about Java
---
1. Java中，作用域由花括号的位置决定。
```java
{
    int x = 12;//只有x
    {
        int q = 96;//有x和q
    }
    //只有x
}
```
2. 类成员变量会被自动初始化，方法中的成员变量不会被自动初始化。
3. 在构造器中调用构造器时，必须将构造器的调用置于最起初处。
4. 在static方法的内部不能调用非静态方法。
5. 在类的内部，变量定义的先后顺序决定了初始化的顺序。即使变量定义散布与方法定义间，它们仍旧会在任何方法（包括构造器）被调用前得到初始化。
6. 静态数据只有在第一次被调用时，才会被初始化。
7. 初始化的顺序是先静态对象（如果它们尚未因前面的对象创建过程而被初始化），而后是非静态对象。
8. 数组初始化三种方法：
```java
int[] a = {1,2,3};
int[] b = new int[]{1,2,3};
int[] c = new int[3];
for(int i = 0; i < c.length; i++){
    c[i] = i + 1;
}
```