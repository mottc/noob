# ToolBar  
----
ToolBar用来替代ActionBar

### 1.首先，在布局文件 `activity_tool_bar.xml` 中添加进我们需要的 Toolbar 控件
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/color_0176da">

        <!--自定义控件-->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Clock" />
    </android.support.v7.widget.Toolbar>
</LinearLayout>
```
### 2.接着在 base_toolbar_menu.xml 中添加 action menu 菜单项
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@id/action_search"
        android:icon="@mipmap/ic_search"
        android:title="@string/menu_search"
        app:showAsAction="ifRoom" />

    <item
        android:id="@id/action_notification"
        android:icon="@mipmap/ic_notifications"
        android:title="@string/menu_notifications"
        app:showAsAction="ifRoom" />

    <item
        android:id="@+id/action_item1"
        android:title="@string/item_01"
        app:showAsAction="never" />

    <item
        android:id="@+id/action_item2"
        android:title="@string/item_02"
        app:showAsAction="never" />
</menu>
```
### 3.最后到 ToolbarActivity 中调用代码拿到这 Toolbar 控件，并在代码中做各种setXXX操作
```java
public class ToolBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("Title");//设置主标题
        toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(ToolBarActivity.this , R.string.menu_search , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(ToolBarActivity.this , R.string.menu_notifications , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(ToolBarActivity.this , R.string.item_01 , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(ToolBarActivity.this , R.string.item_02 , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

    }

}
```
