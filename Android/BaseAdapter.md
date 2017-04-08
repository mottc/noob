# BaseAdapter   

- ListView的各数据项需要适配器进行适配  
- 通过数据适配器，可以将数据源和UI控件联系起来。
- 数据适配器adapter的作用就是将多种多样的数据源适配成listView可以读取的模式。  
（1）降低程序的耦合性  
（2）容易扩展  
- 缓存机制  
需要才显示，显示完就被回收到缓冲池中。  
1.屏幕上滑时，消失的item回收到View缓冲池中  
2.新要显示的item从View缓冲池中取出，并重新设置好item所要显示3.的数据  
item显示出来  

### 1. BaseAdapter基本结构
public int getCount();//适配器中数据集中数据的个数
public Object getItem(int position);//获取数据集中与指定索引对应的数据项
public long getItemId(int position);//获取指定行对应的ID
public View getView(int position,View convertView,ViewGroup parent);//获取每一个Item显示内容
### 2.使用步骤    
1.在布局文件中添加一个ListView。  
2.新建一个item布局文件，在该布局中显示一个item的布局样式。  
3.创建数据源 
> 新建一个ItemBean类,在该类的构造方法里把在item布局文件中定义的控件初始化。  

4.在main方法中，创建一个List。该List的泛型为ItemBean。为该List里的每一个ItemBean添加数据。  
5.新建一个类继承BaseAdapter。
> 1.在该类的构造方法中，把List传进来。
> 2.实现getCount();getItem();getItemId();等方法。

6.三种方法重写getView()方法。
7.在main方法中，找出布局文件中定义的ListView，并为其绑定适配器。

### 3.示例  
1.activity_main.xml  
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <ListView
        android:id="@+id/lv_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```
2.item.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:layout_width="60dp"
        android:layout_height="60dp"
        android:id="@+id/iv_image"
        android:src="@mipmap/ic_launcher" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:text="Title"
        android:gravity="center"
        android:textSize="25sp"
        android:id="@+id/tv_title"
        android:layout_alignParentTop="true"
        android:layout_toEndOf="@+id/iv_image" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:text="Content"
        android:textSize="20sp"
        android:gravity="center_vertical"
        android:id="@+id/tv_content"
        android:layout_below="@id/tv_title"
        android:layout_toEndOf="@+id/iv_image" />

</RelativeLayout>
```
3.MainActivity.java
```java

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        List<ItemBean> dataList = new ArrayList<>();
        // 创建假数据
        for (int i = 0; i < 20; i++) {
            dataList.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "我是标题" + i,
                    "我是内容" + i));
        }
        // 设置适配器
        listView.setAdapter(new MyAdapter(this, dataList));
    }
}
```
4.ItemBean
```java

public class ItemBean {

    public int itemImageResid;
    public String itemTitle;
    public String itemContent;

    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
```
5.MyAdapter.java
```java
public class MyAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    // 映射数据
    private List<ItemBean> mDataList;
    private long mSumTime;

    public MyAdapter(Context context, List<ItemBean> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = list;
    }
    // 获取数据量
    @Override
    public int getCount() {
        return mDataList.size();
    }

    // 获取对应ID项对应的Item
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    // 获取对应项ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 逗比式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
        long start = System.nanoTime();
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
        View view = mLayoutInflater.inflate(R.layout.item, null);
        // 实例化控件
        ImageView itemImage = (ImageView) view.findViewById(R.id.iv_image);
        TextView itemTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView itemContent = (TextView) view.findViewById(R.id.tv_content);
        // 取出bean对象
        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
        itemImage.setImageResource(bean.itemImageResid);
        itemTitle.setText(bean.itemTitle);
        itemContent.setText(bean.itemContent);
        long end = System.nanoTime();
        long dValue = end - start;
        mSumTime += dValue;
        // 输出每次getView消耗的时间和
        Log.d("xys", String.valueOf(mSumTime));
        return view;
        // 逗比式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>24409529

        // 普通式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
//        long start = System.nanoTime();
//        if (convertView == null) {
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//            convertView = mLayoutInflater.inflate(R.layout.item, null);
//        }
//        ImageView itemImage = (ImageView) convertView.findViewById(R.id.iv_image);
//        TextView itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView itemContent = (TextView) convertView.findViewById(R.id.tv_content);
//        // 取出bean对象
//        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
//        itemImage.setImageResource(bean.itemImageResid);
//        itemTitle.setText(bean.itemTitle);
//        itemContent.setText(bean.itemContent);
//        long end = System.nanoTime();
//        long dValue = end - start;
//        mSumTime += dValue;
        // 输出每次getView消耗的时间和
//        Log.d("xys", String.valueOf(mSumTime));
//        return convertView;
        // 普通式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>19271161

        // 文艺式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
//        long start = System.nanoTime();
//        ViewHolder holder = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//            convertView = mLayoutInflater.inflate(R.layout.item, null);
//            holder.img = (ImageView) convertView.findViewById(R.id.iv_image);
//            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
//            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
        // 取出bean对象
//        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
//        holder.img.setImageResource(bean.itemImageResid);
//        holder.title.setText(bean.itemTitle);
//        holder.content.setText(bean.itemContent);
//        long end = System.nanoTime();
//        long dValue = end - start;
//        mSumTime += dValue;
        // 输出每次getView消耗的时间和
//        Log.d("xys", String.valueOf(mSumTime));
//        return convertView;
        // 文艺式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>16325820
    }

    // ViewHolder用于缓存控件
    class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView content;
    }
}
```