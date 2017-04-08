## Handler基础  
1. handler是什么？   
（1）handler是android给我们提供用来更新UI的一套机制，也是一套消息处理的机制，我们可以发送消息，也可以通过它处理消息；   
（2）目的：更新UI，处理、发送消息。   
2. 为什么要用handler？   
因为android在设计的时候，就封装了一套消息创建、传递、处理机制，如果不遵循这样的机制就没有办法更新UI信息，就会抛出异常信息。    

## Handler的用法  
1. Handler中常用的四个方法：  
sendMessage(Message msg)   
sendMessageDelayed(Message msg, long delayMillis)  
post(Runnable r)   
postDelayed(Runnable r, long delayMillis)    
2. 我们可以自己在UI线程之上再创建线程，然后我们需要通过Handler来使得我们的线程和UI线程通信。这样我们才能在我们的子线程中更新UI。因为Android中UI的更新只能在UI线程中进行。我们只能通过Handler的通信来使得我们从子线程中更新UI   
3. 从上面的介绍我们知道，一个Handler实例其实绑定了一个关联的thread线程和那个线程的message queue；handler会讲message和runnable对象发送到它所关联的message queue中去，同时它也会执行从message queue中出来的message和runnable。  
4. Handler的两大用途：1. 在未来某个时间点执行message和runnable对象；2. 将要执行的action放到message queue中去，然后使得另外的不同的线程可以执行这个action。  

- 示例  
```java
public class DisplayMessageActivity extends AppCompatActivity {
    private TextView textview;
	private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					handler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// 更新文本信息
							// 此时直接在这里更新ui的时候,程序将会直接崩溃掉
							textView.setText("更新了");
						}
					});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}.start();
    }
}
```

```java
public class DisplayMessageActivity extends AppCompatActivity {
    private ImageView imageViewHandle;
    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();
    private int[] images = {R.drawable.image1,R.drawable.image2};
    private int index;

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            index++;
            index = index % 3;
            imageViewHandle.setImageResource(images[index]);
            handler.postDelayed(myRunnable, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        imageViewHandle = (ImageView) findViewById(R.id.handler);
        handler.postDelayed(myRunnable, 1000);
    }
}
```
```java
public class MainActivity extends AppCompatActivity {
    private TextView textView;
private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            textView.setText(""+ message.obj);
            //false时不截获，可以执行下一个handleMessage;
            //true时截获，下一个handleMessage不执行
            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            textView.setText("" + msg.arg1 + msg.arg2);
//            textView.setText(""+ msg.obj);
        }
    };
    class Person {
        public String name;
        public int age;
        Person(String name, int age){
            this.age = age;
            this.name = name;
        }
        @Override
        public String toString() {
            return name + age;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    Message message = new Message();  //方式1
                    Message message = handler.obtainMessage();//方式2
                    message.arg1 = 88;
                    message.arg2 = 100;  //传递整数
                    message.obj = new Person("Tom", 20);  //传递对象
                    /**
                     * 发送以后的信息会被handleMessage()接收，并作为其参数。
                     */
                    handler.sendMessage(message);  //发送
//                    message.sendToTarget();//这种发送方式只适用于“方式2”方式生成的Message
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
```
- 为什么使用Handler  
handler的设计缘由是考虑到多线程并发的问题，如果没有这种机制，可能会导致多线程下多个线程更新UI错乱的问题（没有枷锁），若加锁的话，则会影响性能。Handler通过消息队列，保证了消息处理的先后有序。
- Handler机制作用  
只要遵循Android使用handler来更新UI的机制，我们就不用关心多线程的问题，所有的更新UI的操作都是在主线程的消息队列中去轮询处理的。
- Looper  
（1）Looper作为一个消息封装的载体，包含了一个消息队列messageQueue，所有的Handler发送的消息都经过这个消息队列——要发送的消息会往这个消息队列中插入或移除。
（2）Looper.loop()方法是一个死循环，不断轮询messageQueue，如果有消息存在取出并发送给handler，然后handler处理消息，没有就阻塞
- Handler  
内部会和Looper进行关联，也就是说在Handler内部可以找到Looper，找到了Looper也就找到了MessageQueue。调用handler.sendMessage()就是向消息队列中发送消息；然后Looper轮询MessageQueue，将message发送给Handler本身进行处理。
- MessageQueue  
一个消息队列，可以添加消息，并处理消息
- 总结：  
1.handler负责发送消息，Looper负责接收Hnandler发送的消息，并直接把消息回传给hanlder自己。MessageQueue是一个存储消息的容器。  
2.handler把消息封装好后发送给looper（通过sendmessage、post发送），looper接收到消息后，把信息放到messagequeue里面，并不断从messagequeue里提取消息。当提取到某消息时，就把该消息传回给发送该消息的handler。handler通过handleMessage对传回的消息进行处理。  
3.handler、looper、messagequeue、message四者可以这样理解：handler:工人；looper:传送带移动的动力；messagequeue:传送带；message:传送带上面的产品。工人（handler）把自己的产品(message)放在传送带(messagequeue)尾部，在动力(looper)作用下，传送带向前移动，最终产品到达传送带头部，被取出来处理（handmessage()）。
