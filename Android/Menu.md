- OptionMenu选项菜单  
一个activity只能拥有一个选项菜单  
```java
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.add(1, 100, 1, "菜单一");
		item.setTitle("aaa");
		item.setIcon(R.drawable.ic_launcher);// api>=11 不显示图标 ，
		menu.add(1, 101, 1, "菜单二");
		menu.add(1, 102, 1, "菜单三");
		menu.add(1, 103, 1, "菜单四");
		menu.add(1, 104, 1, "菜单五");
		menu.add(1, 105, 1, "菜单六");
		menu.add(1, 106, 1, "菜单七");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 100:
			Intent intent = new Intent(MainActivity.this, SecondActivity.class);
			item.setIntent(intent);
			break;

		case 101:
			Toast.makeText(MainActivity.this, "点击了菜单二", Toast.LENGTH_SHORT)
					.show();
			break;
		case 102:
			Toast.makeText(MainActivity.this, "点击了菜单三", Toast.LENGTH_SHORT)
					.show();
			break;
		case 103:
			Toast.makeText(MainActivity.this, "点击了菜单四", Toast.LENGTH_SHORT)
					.show();
			break;
		case 104:
			Toast.makeText(MainActivity.this, "点击了菜单五", Toast.LENGTH_SHORT)
					.show();
			break;
		case 105:
			Toast.makeText(MainActivity.this, "点击了菜单六", Toast.LENGTH_SHORT)
					.show();
			break;
		case 106:
			Toast.makeText(MainActivity.this, "点击了菜单七", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
```

- ContextMenu上下文菜单   
每个View都可以设置上下文菜单，常用于ListView和GridView
使用上下文菜单步骤：
1.在需要上下文菜单的view控件中注册上下文菜单registerForContextMenu
2.重写activity的onCreateContextMenu方法，创建自定义菜单（xml加载/动态添加）
3.重写activity的onContextItemSelected，根据上下文中各个子项的id来实现不同的菜单操作  
```java
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showListView();
	}

	/**
	 * 设置listview的显示内容
	 */
	private void showListView() {
		ListView listview = (ListView) findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listview.setAdapter(adapter);
		this.registerForContextMenu(listview);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		//设置Menu显示内容
		menu.setHeaderTitle("文件操作");
		menu.setHeaderIcon(R.drawable.ic_launcher);
//		menu.add(1, 1, 1, "复制");
//		menu.add(1, 2, 1, "粘贴");
//		menu.add(1, 3, 1, "剪切");
//		menu.add(1, 4, 1, "重命名");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.context_menu_item1:
			Toast.makeText(MainActivity.this, "点击复制",Toast.LENGTH_SHORT).show();
			break;

		case R.id.context_menu_item2:
			Toast.makeText(MainActivity.this, "点击粘贴",Toast.LENGTH_SHORT).show();
			break;

		case R.id.context_menu_item3:
			Toast.makeText(MainActivity.this, "点击剪切",Toast.LENGTH_SHORT).show();
			break;

		case R.id.context_menu_item4:
			Toast.makeText(MainActivity.this, "点击重命名",Toast.LENGTH_SHORT).show();
			break;
		}
		
		return super.onContextItemSelected(item);
	
	}
	
	/**
	 * 构造listview显示的数据
	 * 
	 * @return
	 */
	private ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("文件" + (i + 1));
		}
		return list;
	}
}
```
- SubMenu 子菜单
给选项菜单添加子菜单   
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android" >

    <item
        android:showAsAction="never"
        android:title="文件">
        <menu>
            <item
                android:id="@+id/new_file"
                android:showAsAction="never"
                android:title="新建"/>
            <item
                android:id="@+id/open_file"
                android:showAsAction="never"
                android:title="打开"/>
            <item
                android:id="@+id/save_file"
                android:showAsAction="never"
                android:title="保存"/>
        </menu>
    </item>
	 <item
        android:showAsAction="never"
        android:title="编辑">
        <menu>
            <item
                android:id="@+id/c_edit"
                android:showAsAction="never"
                android:title="复制"/>
            <item
                android:id="@+id/v_edit"
                android:showAsAction="never"
                android:title="粘贴"/>
            <item
                android:id="@+id/x_edit"
                android:showAsAction="never"
                android:title="剪切"/>
        </menu>
    </item>
</menu>
```
```java
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
/**
*注释内容为动态添加的方法
*/
//		SubMenu file = menu.addSubMenu("文件");
//		SubMenu edit = menu.addSubMenu("编辑");
//		file.add(1, 1, 1, "新建");
//		file.add(1, 2, 1, "打开");
//		file.add(1, 3, 1, "保存");
//		file.setHeaderTitle("文件操作");//子菜单标题
//		file.setHeaderIcon(R.drawable.ic_launcher);//子菜单图标
//		edit.add(2, 1, 1, "复制");
//		edit.add(2, 2, 1, "粘贴");
//		edit.add(2, 3, 1, "剪切");
//		edit.setHeaderTitle("编辑操作");
//		edit.setHeaderIcon(R.drawable.ic_launcher);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.new_file:
			Toast.makeText(this, "点击了新建",Toast.LENGTH_SHORT).show();
			break;
		case R.id.open_file:
			Toast.makeText(this, "点击了打开",Toast.LENGTH_SHORT).show();
			break;
		case R.id.save_file:
			Toast.makeText(this, "点击了保存",Toast.LENGTH_SHORT).show();
			break;
		case R.id.c_edit:
			Toast.makeText(this, "点击了复制",Toast.LENGTH_SHORT).show();
			break;
		case R.id.v_edit:
			Toast.makeText(this, "点击了粘贴",Toast.LENGTH_SHORT).show();
			break;
		case R.id.x_edit:
			Toast.makeText(this, "点击了剪切",Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
```