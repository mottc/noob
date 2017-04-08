# AsyncTask  
- AsyncTask,即异步任务,是Android给我们提供的一个处理异步任务的类.通过此类,可以实现UI线程和后台线程进行通讯,后台线程执行异步任务,并把结果返回给UI线程.  

- 为什么需要使用异步任务?
我们知道,Android中只有UI线程,也就是主线程才能进行对UI的更新操作,而其他线程是不能直接操作UI的.这样的好处是保证了UI的稳定性和准确性,避免多个线程同时对UI进行操作而造成UI的混乱.但Android是一个多线程的操作系统,我们总不能把所有的任务都放在主线程中进行实现,比如网络操作,文件读取等耗时操作,如果全部放到主线程去执行,就可能会造成后面任务的阻塞.Android会去检测这种阻塞,当阻塞时间太长的时候,就会抛出Application Not Responsed(ANR)错误.所以我们需要将这些耗时操作放在非主线程中去执行.这样既避免了Android的单线程模型,又避免了ANR.  

- AsyncTask为何而生?
提到异步任务,我们能想到用线程,线程池去实现.确实,Android给我们提供了主线程与其他线程通讯的机制.但同时,Android也给我们提供了一个封装好的组件--AsyncTask.利用AsyncTask,我们可以很方便的实现异步任务处理.AsyncTask可以在子线程中更新UI,也封装简化了异步操作.使用线程,线程池处理异步任务涉及到了线程的同步,管理等问题.而且当线程结束的时候还需要使用Handler去通知主线程来更新UI.而AsyncTask封装了这一切,使得我们可以很方便的在子线程中更新UI.

- 构建AsyncTask子类的泛型参数
AsyncTask<Params,Progress,Result>是一个抽象类,通常用于被继承.继承AsyncTask需要指定如下三个泛型参数:  
Params:启动任务时输入的参数类型.  
Progress:后台任务执行中返回进度值的类型.  
Result:后台任务执行完成后返回结果的类型.  

- 构建AsyncTask子类的回调方法
AsyncTask主要有如下几个方法:  
doInBackground:必须重写,异步执行后台线程要完成的任务,耗时操作将在此方法中完成.  
onPreExecute:执行后台耗时操作前被调用,通常用于进行初始化操作.  
onPostExecute:当doInBackground方法完成后,系统将自动调用此方法,并将doInBackground方法返回的值传入此方法.通过此方法进行UI的更新.  
onProgressUpdate:当在doInBackground方法中调用publishProgress方法更新任务执行进度后,将调用此方法.通过此方法我们可以知晓任务的完成进度.   

- 使用AsyncTask的注意事项
① 必须在UI线程中创建AsyncTask的实例.  
② 只能在UI线程中调用AsyncTask的execute方法.  
③ AsyncTask被重写的四个方法是系统自动调用的,不应手动调用.  
④ 每个AsyncTask只能被执行(execute方法)一次,多次执行将会引发异常.  
⑤ AsyncTask的四个方法,只有doInBackground方法是运行在其他线程中,其他三个方法都运行在UI线程中,也就说其他三个方法都可以进行UI的更新操作.  

### 示例一
1.image.xml   
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:gravity="center"
              android:layout_height="match_parent">
	<ImageView
        android:id="@+id/image"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
    <ProgressBar
		android:visibility="gone"
        android:id="@+id/progress"
		android:layout_centerInparent="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</RelativeLayout>
```
2.ImageActivity.java
```java
public class ImageActivity extends Activity {
    private ImageView imageView ;
    private ProgressBar progressBar ;
    private static String URL = "http://pic3.zhongsou.com/image/38063b6d7defc892894.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //通过调用execute方法开始处理异步任务.相当于线程中的start方法.
        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap> {

        //onPreExecute用于异步处理前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //此处将progressBar设置为可见.
            progressBar.setVisibility(View.VISIBLE);
        }

        //在doInBackground方法中进行异步任务的处理.
        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection ;
            InputStream is ;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                //为了更清楚的看到加载图片的等待操作,将线程休眠3秒钟.
                Thread.sleep(3000);
                BufferedInputStream bis = new BufferedInputStream(is);
                //通过decodeStream方法解析输入流
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //隐藏progressBar
            progressBar.setVisibility(View.GONE);
            //更新imageView
            imageView.setImageBitmap(bitmap);
        }
    }
}
```

### 示例二  
progress.xml   
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:gravity="center"
              android:layout_height="match_parent">
    <ProgressBar
        style="?android:attr/progressBarStyleHorizontal"
        android:id="@+id/progress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```
ProgressActivity.java
```java
public class ProgressActivity extends Activity{
    private ProgressBar progressBar;
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        myAsyncTask = new MyAsyncTask();
        //启动异步任务的处理
        myAsyncTask.execute();
    }

    //AsyncTask是基于线程池进行实现的,当一个线程没有结束时,后面的线程是不能执行的.
    @Override
    protected void onPause() {
        super.onPause();
        if (myAsyncTask != null && myAsyncTask.getStatus() == Status.RUNNING) {
            //cancel方法只是将对应的AsyncTask标记为cancelt状态,并不是真正的取消线程的执行.
            myAsyncTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //通过publishProgress方法传过来的值进行进度条的更新.
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //使用for循环来模拟进度条的进度.
            for (int i = 0;i < 100; i ++){
                //如果task是cancel状态,则终止for循环,以进行下个task的执行.
                if (isCancelled()){
                    break;
                }
                //调用publishProgress方法将自动触发onProgressUpdate方法来进行进度条的更新.
                publishProgress(i);
                try {
                    //通过线程休眠模拟耗时操作
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
```