- 自定义一个Activity  
继承activity（不同的activity名字不同）-->重写方法（重写onCreate等的方法)）-->显示布局（setContentView(R.layout.(xml的名字))）-->在androidmanifest中注册（name=包名+java文件的名字，如果是主入口的activity则要在标签下方加上........）   


- Activity的四种状态：   
——活动状态（Active/Running）ActivityAc处理界面的最顶端，拥有焦点  
——暂停状态（Paused）:Activity失去焦点，但对用户可见  
——停止状态（Stopped）Activity被完全遮挡，但保留所有状态和成员信息  
——非活动状态（Killed）Activity被停止  
  
- 四种状态详解：  
1.onCreate()→onStart()→onResume() 这个时候的Activity处于活动状态，它获取了焦点，显示在界面最顶端与用户进行交互  
2.onCreate()→onStart()→onResume()→onPause()→onResume() 从启动到失去焦点，再到获取到焦点的生命周期（当Activity失去焦点，如在Activity上弹出一个半透明的Activity或者是一个对话框的时候，它就会调用onPause()这个方法，当Activity从新获取到焦点的时候，它会再调用onResume()这个方法）  
3.onCreate()→onStart()→onResume()→onPause()→onStop()→onRestart()→onStart()→onResume() 从启动到后台，再到前台的生命周期（当Activity弹出一个整屏的Activity，把当前的Activity完全遮盖住了，这个时候就会调用onPause()→onStop()，然后当我前面这个Activity退出了的时候，后面的Activity就会调用onRestart()→onStart()→onResume()，此时的后面的Activity会再次呈现在界面最顶端）  
4.onCreate()→onStart()→onResume()→onPause()→onStop()→onDestroy() 从创建到销毁的的生命周期  


1. 创建到销毁的过程：   
onCreate()->onStart()->onResume()->onPause()->onStop()->onDestory()   
2. 启动到后台，再到前台的过程（完全遮挡）   
onCreate()->onStart()->onResume()->onPause()->onStop()->onRestart()->onStart()->onResume()  
onPause()是到后台调用的接口。onRestart()是再到前台的接口。   
3. 启动到失去焦点，再到获取焦点  
onCreate()->onStart()->onResume()->onPause()->onResume()   
onPause()是失去焦点调用的接口，onResume()是再次获取焦点的接口。   

Intent
1.Intent:意图，协助完成Android各个组件之间的通讯
2.
①startActivity(intent)
②startActivityForResult（intent,requestCode）//有返回值
onActivityResult(int requestCode,int resultCode,Intent data)//接收返回值A页面
setResult(resultCode,data)//设置返回值B页面
3.第①中方法
①创建两个Acitivity.java\两个xml
②重写onCreate（）方法、setContentView（R.layout.factivity）引入页面
③在AndroidMainifest.xml中声明<activity>
包括改名字name属性、有intent_filter的是首启动页面
④按钮初始化（声明、findViewById）、setOnclickListener()
⑤在onClick（）中
Intent intent=new Intent(FActivity.this,SAcitivity.class)
//(上下文对象，)上下文对象也可以声明一个Context的全局化变量，在使context=this
//目标文件
startActivity（intent）



第一个页面的工作：
startActivityForResult(Intent xx,int requestCode)
第一个参数是 Intent对象;第二个参数是一个 “标识”

通过 startActivityForResult()方法 跳转页面 还需要一个 方法 接收 第二个页面 返回的 数据
该 方法 是 onActivityResult(int requestCode,int resultCode,Intent data);

onActivityResult()方法写在onCreate()方法外部，与 onCreate()方法 同级
requestCode :请求标识; resultCode : 返回标识；data : 第二个页面返回的数据;

onActivityResult()方法 的实现逻辑：
{
if( requestCode == 1 && resultCode==2){
//通过 判断 请求码 和 回传码 来唯一确认 回传数据 是否为 想要的数据 
String content=data.getStringExtra("data");
tv.setText(content);
}
第二个页面“回传”数据的方法:
1.被动式“回传”数据，回传的数据通过“Intent对象”封装；
2. Intent对象 封装数据的 方法 putExtra(key,value),通过键值对进行标识；
3. 通过 setResult(int resultCode,Intent i)方法 回传数据， resultCode 为 回传标识符；
4. finish()方法 销毁当前页面