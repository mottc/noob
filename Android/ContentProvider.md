# ContentProvider   
如果想写出一个程序，该程序的数据可以被其他程序进行增删改查，那么这个程序就必须有实现ContentProvider类，并且，在ContentProvider实现了哪些方法，其他程序就只能进行哪些操作。比如，只在ContentProvider中实现了增和删，那么第三方程序只能对这个程序的数据进行增和删。之所以我们能对“联系人”进行增删改查，是因为这些程序已经写好了ContentProvider。   
- 为自己的程序写一个ContentProvider就要创建一个类继承抽象类Contentprovider   
oncreat（在创建后被调用）   
delet（根据uri删除selection指定的条件所匹配全部记录）   
insert（根据uri插入values对应的数据）   
update（根据uri修改selection指定的条件所匹配的全部记录）   
query（更加uri查询出selection指定的条件所匹配的全部记录，并且可以指定查询哪些列以什么方式排序）    
getType（返回当前uri的MIME类型，如果uri对应数据包括多条记录那么MIME类型字符串就是以vnd.android.dir/开头 如果只对应一条记录就是vnd.android.cursor.item/开头）   

- 何为Uri?
-Uri是指通用资源标志符
content://com.imooc.provider/music/#
content://---前缀表明数据受控于一个内容提供者.它从不修改,也就是schema
com.imooc.provider---是指在AndroidMainfest.xml中我们注册的provider中的authorities属性所对应的唯一的
/music---具体操作于哪个条目
/#---具体指定到哪个条目下的那条记录(#标识通配符)

- UriMatcher类
UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
UriMatcher.NO_MATCH 表示不匹配任何路径的返回码
matcher.addURI("com.imooc.provider","music",1);
往UriMatcher类里面添加一个拼凑的Uri
UriMatcher为一个Uri的容器,容器里面包含着我们即将可能要操作的Uri
如果通过match()方法匹配成功就返回code值
matcher.match(uri)
首先与通过addURI()方法添加进来的Uri匹配
匹配成功则返回设置的code值,反之,返回一个UriMatcher.NO_MATCH常量(-1)

- ContentResolver:   
使用该类时，注意添加权限
使用ContentResolver操作ContentProvider中的数据:
当外部应用需要对ContentProvider中的数据进行添加,删除,修改和查询操作时,可以使用ContentResolver类来完成
使用Activity提供的getContentResolver()方法获取ContentResolver对象
ContentResolver类提供了与ContentProvider类相同签名的四个方法   
- 查询示例：  
```java
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver cr = getContentResolver();
		Cursor c = cr.query(Contacts.CONTENT_URI, new String[] { Contacts._ID,
				Contacts.DISPLAY_NAME }, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				int id = c.getInt(c.getColumnIndex("_id"));
				Log.i("info", "_id:" + id);
				Log.i("info","name:" + c.getString(c.getColumnIndex("display_name")));
				Cursor c1 = cr.query(Phone.CONTENT_URI, new String[] {
						Phone.NUMBER, Phone.TYPE },
						Phone.CONTACT_ID + "=" + id, null, null);
				// 根据联系人ID查询出联系人的电话号码
				if (c1 != null) {
					while (c1.moveToNext()) {
						int type = c1.getInt(c1.getColumnIndex(Phone.TYPE));
						if (type == Phone.TYPE_HOME) {
							Log.i("info","家庭电话："+ c1.getString(c1.getColumnIndex(Phone.NUMBER)));
						} else if (type == Phone.TYPE_MOBILE) {
							Log.i("info","手机："+ c1.getString(c1.getColumnIndex(Phone.NUMBER)));
						}
					}
					c1.close();
				}
				// 根据联系人的ID去查询出联系人的邮箱地址
				Cursor c2 = cr.query(Email.CONTENT_URI, new String[] {
						Email.DATA, Email.TYPE }, Email.CONTACT_ID + "=" + id,
						null, null);
				if (c2 != null) {
					while (c2.moveToNext()) {
						int type = c2.getInt(c2.getColumnIndex(Email.TYPE));
						if (type == Email.TYPE_WORK) {
							Log.i("info","工作邮箱："+ c2.getString(c2.getColumnIndex(Email.DATA)));
						}
					}
					c2.close();
				}
			}
			c.close();
		}
	}

}
```   
- 增添示例：  
```java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver cr =  getContentResolver();
        //向联系人中 插入一行数据
        ContentValues values = new ContentValues();
       Uri uri =  cr.insert(RawContacts.CONTENT_URI, values);
       Long raw_contact_id = ContentUris.parseId(uri);
       values.clear();
       //插入人名
       values.put(StructuredName.RAW_CONTACT_ID, raw_contact_id);
       values.put(StructuredName.DISPLAY_NAME, "张三三");
       values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
       uri = cr.insert(Data.CONTENT_URI, values);
       //插入电话信息
       values.clear();
       values.put(Phone.RAW_CONTACT_ID,raw_contact_id);
       values.put(Phone.NUMBER,"13333333333");
       values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
       uri = cr.insert(Data.CONTENT_URI, values);
    }
}
```  

- android系统管理联系人的URI如下
ContactsContract.Contacts.CONTENT_URI 管理联系人的Uri
ContactsContract.CommonDataKinds.Phone.CONTENT_URI 管理联系人的电话的Uri
ContactsContract.CommonDataKinds.Email.CONTENT_URI 管理联系人的Email的Uri
（注：Contacts有两个表，分别是rawContact和Data，rawContact记录了用户的id和name,其中id栏名称为：ContactsContract.Contacts._ID, name名称栏为ContactContract.Contracts.DISPLAY_NAME,电话信息表的外键id为ContactsContract.CommonDataKinds.Phone.CONTACT_ID,电话号码栏名称为：
ContactsContract.CommonDataKinds.Phone.NUMBER.
data表中Email地址栏名称为：
ContactsContract.CommonDataKinds.Email.DATA
其外键栏为：ContactsContract.CommonDataKinds.Email.CONTACT_ID)

- android为多媒体提供的ContentProvider的Uri如下：
MediaStore.Audio.Media.EXTERNAL_CONTENT_URI 存储在sd卡上的音频文件
MediaStore.Audio.Media.INTERNAL_CONTENT_URI 存储在手机内部存储器上的音频文件
MediaStore.Audio.Images.EXTERNAL_CONTENT_URI SD卡上的图片文件内容
MediaStore.Audio.Images.INTERNAL_CONTENT_URI 手机内部存储器上的图片
MediaStore.Audio.Video.EXTERNAL_CONTENT_URI SD卡上的视频
MediaStore.Audio.Video.INTERNAL_CONTENT_URI 手机内部存储器上的视频
(注：图片的显示名栏：Media.DISPLAY_NAME，图片的详细描述栏为：Media.DESCRIPTION 图片的保存位置：Media.DATA)
- 短信URI： Content://sms
- 发送箱中的短信URI： Content://sms/outbox