## Broadcast和BroadcastReceiver   
1. 概念：   
1）Broadcast一种广泛运用的在应用程序之间传输信息的机制   
2）BroadcastReceiver对发送出来的广播进行过滤接收并响应的一类组件，它可以用来接收来自系统和应用中的广播    
3）用途    
开机完成后系统会产生一条广播    
网络状态改变时系统会产生一条广播    
电量改变时系统产生一条广播    
2. 为什么要设计广播   
大大减少开发工作量和周期（程序员不需要关注底层如何实现，只需要掌握BroadcastReceiver就行了）   
3. 使用方法：    
1）发送：    
将信息装入一个Intent对象（如Action、Category），通过调用相应的方法将Intent对象以广播方式发送出去    
`sendBroadcast()` 、 `sendOrderBroadcast()` 、 `sendStickyBroadcast()`    
2）接收：    
当Intent发送之后，所有已经注册的BroadcastReceiver会检查注册时的IntentFilter是否与发送的Intent相匹配，匹配成功的话就会调用BroadcastReceiver的onReceive()方法。      
所以定义一个BroadcastReceiver的时候都需要实现onReceive()方法     
4. 生命周期     
生命周期只有5s左右，每次广播来的时候都会创建BroadcastReceiver对象，并调用onReceive()方法。  
所以不能在onReceive()做一些耗时操作，可以通过发送Intent给service，由service完成耗时操作。    
注意子线程也不行    
5. 广播的种类   
普通广播、有序广播、异步广播   
注意有序广播是可以终止的，且接收者可以篡改内容   
6. 普通广播的特点：
 - 同级别接收先后是随机的（无序）
 - 级别低的后收到广播（priority）
 - 接收器不能截断广播的传播也不能处理广播（比如开机广播）
 - 同级别动态注册高于静态注册
7. 有序广播的特点：
 - 同级别接收顺序是随机的
 - 能截断广播的继续传播，高级别的广播接收器收到广播后，可以决定是否截断该广播
 - 接收器能截断广播的继续传播，也能处理广播
 - 同级别动态注册高于静态注册    

- 发送广播：   
```java
Intent intent=new Intent（）;
intent.putExtra("msg","这是普通广播");
Intent.setAction("BC_One");//确保Intent的唯一性，最好是包名加类名
sendBroadcast(intent);
```
- 接受广播：   
类BC1继承BroadcastReceiver,实现其方法onReceiver()  
```java
String s=intent.getStringExtra("msg");
System.out.println("接收到消息"+s);
// abortBroadcast();  截断广播
```
- 注册列表里（静态注册）：
```xml
<receiver android:name="com.imooc.broadcastreceiverdemo.BC1" >
	<intent-filter
			android：priority="100"> //设置优先级，值为值为-1000~1000
		<action android:name="BC_One" />
	</intent-filter>
</receiver>
```
- onCreate()方法中动态注册方法：
```java
IntentFilter intentfilter=new IntentFilter("BC_One");
BC2 bc2=new BC2();
registerReceiver(bc2,intentfilter);
```
- 注意一点
动态注册的接收者需要在程序结束时销毁掉
unregisterReceiver(receiver3)    

- 示例：   
```java
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/**
*以下注释为动态注册的用法
*/
//		IntentFilter intentfilter = new IntentFilter("BC_One");
//		BC2 bc2 = new BC2();
//		registerReceiver(bc2, intentfilter);
	}
	public void doClick(View v){
		switch (v.getId()) {
		case R.id.send1://发送一条普通广播
			Intent intent = new Intent();
			intent.putExtra("msg", "这是一条普通广播");
			intent.setAction("BC_One");
			sendBroadcast(intent);
			break;
		case R.id.send2://发送一条有序广播
			Intent intent2 = new Intent();
			intent2.putExtra("msg", "这是一条有序广播");
			intent2.setAction("BC_One");
			sendOrderedBroadcast(intent2, null);
			break;
		case R.id.send3://发送一条异步广播
			Intent intent3 = new Intent();
			intent3.putExtra("msg", "这是一条异步广播");
			intent3.setAction("BC_Three");
			sendStickyBroadcast(intent3);
			IntentFilter intentfilter = new IntentFilter("BC_Three");
			BC3 bc3 = new BC3();
			registerReceiver(bc3, intentfilter);
			break;
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		unregisterReceiver(bc3);
	}
}
```
```java
public class BC1 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String s = intent.getStringExtra("msg");
		System.out.println("reveiver1收到消息："+s);
		abortBroadcast();
//		Bundle bundle = 	new Bundle();
//		bundle.putString("test", "广播处理的数据");
//		setResultExtras(bundle);
	}

}
```
```java
public class BC2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String s = intent.getStringExtra("msg");
		System.out.println("reveiver2收到消息："+s);
//		abortBroadcast();
		Bundle bundle = getResultExtras(true);
		String s2 = bundle.getString("test");
		System.out.println("得到的处理结果是："+s2);
	}

}
```
```java
public class BC3 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("收到了异步广播!");
	}

}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.imooc.broadcastreceiverdemo"
    android:versionCode="1"
    android:versionName="1.0" >
    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="10" />
    <uses-permission android:name="android.permission.BROADCAST_STICKY"/>
    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.imooc.broadcastreceiverdemo.MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <receiver android:name="com.imooc.broadcastreceiverdemo.BC1" >
            <intent-filter>
                <action android:name="BC_One" />
            </intent-filter>
        </receiver>
        <receiver android:name="com.imooc.broadcastreceiverdemo.BC2" >
            <intent-filter>
                <action android:name="BC_One" />
            </intent-filter>
        </receiver>
    </application>
</manifest>
```
