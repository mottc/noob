# Service  
- 特点：  
Service优先级高于activity（内存不足时，先杀优先级低的）  
后台运行，不可见，没有界面   
运行在主线程，不能用它来做耗时操作，耗时操作可以再开一个线程；   
- 用途：播放音乐，记录地理信息位置的改变；    
- 分类：启动式，绑定式   
start方式特点：服务和启动源没有任何联系，无法得到服务对象   
Bind方式特点：通过Ibinder接口实例，返回一个ServiceConnection对象给启动源，通过ServiceConnection对象的相关方法可以得到Service对象

### StartService
1. 使用方法：  
（1）写一个MyStartService继承自Service，重写它的各种方法onCreate()、onStartCommand()、onDestory()  
（2）在AndroidManifest.xml中注册这个Service  
（3）在主线程Activity中通过startSerice(intent)方式启动   
（4）通过stopService(intent)方式停止    
2. 关于StartService   
（1）启动方式是通过启动intent方式实现    
（2）启动之后该Service和启动源没有关系，即使主线程退出了，service还会继续运行  
3. 示例  
```java
public class MyStartService extends Service {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("info", "Service--onCreate()");
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("info", "Service--onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("info", "Service--onDestroy()");
		super.onDestroy();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "Service--onBind()");
		return null;
	}
}
```
```java
public class MainActivity extends Activity {
	Intent intent1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void doClick(View v){
		switch (v.getId()) {
		case R.id.start:
			intent1 = 	new Intent(MainActivity.this, MyStartService.class);
			startService(intent1);
			break;

		case R.id.stop:
			stopService(intent1);
			break;
		}
	}
}
```
### bindService   
通过bindService()得到的Service是和启动源（Activity）绑定在一起的，在Activity退出的时候需要调用unbindService()进行解绑定（停止）。
调用bindService()时会调用到目标Service的onBind()函数，通过IBinder接口实例，返回一个Binder对象给启动源。然后启动源可以通过ServiceConnection对象得到启动的Service对象  
- 使用方法：  
（1）重写onBind()方法  
```java
public IBinder onBind(Intent intent){
	return new MyBinder();
}
```
（2）MyBinder是继承自Binder类的，而Binder类实际上实现了IBinder接口  
```java
public class MyBinder extends Binder{
	public MyBindService getService(){
		return MyBindService.this; //返回这个Service的实例
	}
}
```
（3）在启动源的Activity中创建一个ServiceConnection实例  
```java
ServiceConnection conn = new ServiceConnection(){
	//当启动源跟service的连接意外丢失的时候会调用
	//比如service崩溃了，或被强行杀死了
	public void onServiceDisconnected(ComponentName name) {
	}
	//当启动源跟service成功连接之后会调用这个方法
	public void onServiceConnected(ComponentName name, IBinder service) {
	MyBindService myService = ((MyBinder)service).getService();
	}
};
```
（4）bindService()中指定ServiceConnection conn参数  
```java
bindService(intent2, conn, Service.BIND_AUTO_CREATE);
```  
- 示例  
```java
public class MyBindService extends Service{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--onCreate()");
		super.onCreate();
	}
	public class MyBinder extends Binder{
		public MyBindService getService(){
			return MyBindService.this;
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--onBind()");
		return new MyBinder();
	}
	@Override
	public void unbindService(ServiceConnection conn) {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--unbindService()");
		super.unbindService(conn);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("info", "BindService--onDestroy()");
		super.onDestroy();
	}
	public void Play(){
		Log.i("info", "播放");
	}
	public void Pause(){
		Log.i("info", "暂停");
	}
	public void Pervious(){
		Log.i("info", "上一首");
	}
	public void next(){
		Log.i("info", "下一首");
	}
}
```
```java
public class MainActivity extends Activity {
	Intent intent2;
	MyBindService service;
	ServiceConnection conn = new ServiceConnection() {
		@Override//当服务跟启动源断开的时候 会自动回调
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		@Override//当服务跟启动源连接的时候 会自动回调
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			service = ((MyBinder)binder).getService();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void doClick(View v){
		switch (v.getId()) {
		case R.id.play:
			service.Play();
			break;
		case R.id.pause:
			service.Pause();
			break;
		case R.id.pervious:
			service.Pervious();
			break;
		case R.id.next:
			service.next();
			break;
		case R.id.bind:
			intent2 = new Intent(MainActivity.this, MyBindService.class);
			startService(intent2);
			bindService(intent2, conn, Service.BIND_AUTO_CREATE);
			break;
		case R.id.unbind:
			unbindService(conn);
			break;
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		stopService(intent2);
		unbindService(conn);
		super.onDestroy();
	}
}
```