# Java反射


## 1. Class类
1. 在面向对象的世界里，万事万物皆对象。

   类是对象，类是java.lang.Class类的实例对象。
   There is a class named Class
2. Foo的实例对象如何表示:`Foo foo1=new Foo();`//foo1就表示出来了

   Foo这个类也是一个实例对象，Class类的实例对象，如何表示呢？任何一个类都是Class的实例对象，这个实例对象有三种表示方式:

   //第一种表示方式--->实际在告诉我们任何一个类都有一个隐含的静态成员变量class

     `Class c1=Foo.class; `

   //第二种表达方式--->已经知道该类的对象通过getClassF方法

    `Class c2=foo1.getClass();`
    
   //第三种表达方式

     `Class c3=null;`

     `c3=Class.forName("com.imooc.reflect.Foo");`

   //我们完全可以通过类的类类型创建类的对象实例--->通过c1 or c2 or c3创建Foo的实例对象

     `Foo foo=(Foo)c1.newInstance();//需要有无参数的构造方法,强制类型转换`
## 2. Java动态加载类

编译时刻加载类是静态加载类，运行时刻加载类是动态加载类。

new 创建对象是静态加载类，在编译时刻就需要加载所有的可能使用到的类。

通过`Class a=Class.forName(arg[0]);`此时为动态加载，因为编译时不知道使用哪个类，因此编译没有加载任何类，通过编译。运行时，根据 `Javac office.java word ` （word为arg[0]，也是类类型）,去确定a是哪个类。这就是动态加载。如果word不存在，此时运行会报错。这就是为何有时候会出现编译通过，运行报错的原因。

动态加载一个好处，就是可以随时增加需要编译的类。例如没有excel类，只有word类，也可以运行，需要excel类时再由程序员写此类（为了能统一控制，word类、excel类需要继承同一个父类或者继承同一个接口）。
## 3. 获取方法信息

```java
public class ClassUtil {
/**
	 * 打印类的信息，包括类的成员函数
	 * @param obj 该对象所属类的信息
	 */
	public static void printClassMethodMessage(Object obj){
		//要获取类的信息  首先要获取类的类类型
		Class c = obj.getClass();//传递的是哪个子类的对象  c就是该子类的类类型
		//获取类的名称
		System.out.println("类的名称是:"+c.getName());
		/*
		 * Method类，方法对象
		 * 一个成员方法就是一个Method对象
		 * getMethods()方法获取的是所有的public的函数，包括父类继承而来的
		 * getDeclaredMethods()获取的是所有该类自己声明的方法，不问访问权限
		 */
		Method[] ms = c.getMethods();//c.getDeclaredMethods()
		for(int i = 0; i < ms.length;i++){
			//得到方法的返回值类型的类类型
			Class returnType = ms[i].getReturnType();
			System.out.print(returnType.getName()+" ");
			//得到方法的名称
			System.out.print(ms[i].getName()+"(");
			//获取参数类型--->得到的是参数列表的类型的类类型
			Class[] paramTypes = ms[i].getParameterTypes();
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
}
```
## 4. 获取成员变量和构造方法信息
```java

public class ClassUtil {
	
    /**
     * 获取成员变量的信息
     * @param obj
     */
	public static void printFieldMessage(Object obj) {
		Class c = obj.getClass();
		/*
		 * 成员变量也是对象
		 * java.lang.reflect.Field
		 * Field类封装了关于成员变量的操作
		 * getFields()方法获取的是所有的public的成员变量的信息
		 * getDeclaredFields获取的是该类自己声明的成员变量的信息
		 */
		//Field[] fs = c.getFields();
		Field[] fs = c.getDeclaredFields();
		for (Field field : fs) {
			//得到成员变量的类型的类类型
			Class fieldType = field.getType();
			String typeName = fieldType.getName();
			//得到成员变量的名称
			String fieldName = field.getName();
			System.out.println(typeName+" "+fieldName);
		}
	}
	/**
	 * 打印对象的构造函数的信息
	 * @param obj
	 */
	public static void printConMessage(Object obj){
		Class c = obj.getClass();
		/*
		 * 构造函数也是对象
		 * java.lang. Constructor中封装了构造函数的信息
		 * getConstructors获取所有的public的构造函数
		 * getDeclaredConstructors得到所有的构造函数
		 */
		//Constructor[] cs = c.getConstructors();
		Constructor[] cs = c.getDeclaredConstructors();
		for (Constructor constructor : cs) {
			System.out.print(constructor.getName()+"(");
			//获取构造函数的参数列表--->得到的是参数列表的类类型
			Class[] paramTypes = constructor.getParameterTypes();
			for (Class class1 : paramTypes) {
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
}
```
## 5. Java方法反射的基本操作
```java
public class MethodDemo1 {
	public static void main(String[] args) {
	   //要获取print(int ,int )方法  1.要获取一个方法就是获取类的信息，获取类的信息首先要获取类的类类型
		A a1 = new A();
		Class c = a1.getClass();
		/*
		 * 2.获取方法 名称和参数列表来决定  
		 * getMethod获取的是public的方法
		 * getDelcaredMethod自己声明的方法
		 */
	    try {
			//Method m =  c.getMethod("print", new Class[]{int.class,int.class});
	    	Method m = c.getMethod("print", int.class,int.class);
	    	
	    	//方法的反射操作  
	    	//a1.print(10, 20);方法的反射操作是用m对象来进行方法调用 和a1.print调用的效果完全相同
	        //方法如果没有返回值返回null,有返回值返回具体的返回值
	    	//Object o = m.invoke(a1,new Object[]{10,20});
	    	Object o = m.invoke(a1, 10,20);
	    	System.out.println("==================");
	    	//获取方法print(String,String)
             Method m1 = c.getMethod("print",String.class,String.class);
             //用方法进行反射操作
             //a1.print("hello", "WORLD");
             o = m1.invoke(a1, "hello","WORLD");
             System.out.println("===================");
           //  Method m2 = c.getMethod("print", new Class[]{});
                Method m2 = c.getMethod("print");
               // m2.invoke(a1, new Object[]{});
                m2.invoke(a1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
     
	}
}
class A{
	public void print(){
		System.out.println("helloworld");
	}
	public void print(int a,int b){
		System.out.println(a+b);
	}
	public void print(String a,String b){
		System.out.println(a.toUpperCase()+","+b.toLowerCase());
	}
}
```
## 6. 集合泛型的本质
```java
public class MethodDemo4 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("hello");
		//list1.add(20);错误的
		Class c1 = list.getClass();
		Class c2 = list1.getClass();
		System.out.println(c1 == c2);
		//反射的操作都是编译之后的操作
		
		/*
		 * c1==c2结果返回true说明编译之后集合的泛型是去泛型化的
		 * Java中集合的泛型，是防止错误输入的，只在编译阶段有效，
		 * 绕过编译就无效了
		 * 验证：我们可以通过方法的反射来操作，绕过编译
		 */
		try {
			Method m = c2.getMethod("add", Object.class);
			m.invoke(list1, 20);//绕过编译操作就绕过了泛型
			System.out.println(list1.size());
			System.out.println(list1);
			/*for (String string : list1) {
				System.out.println(string);
			}*///现在不能这样遍历
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}

}
```