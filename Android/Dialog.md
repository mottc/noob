一、Dialog  
对话框是在当前界面弹出的一个小窗口，用于显示重要提示信息，提示用户输入信息，确认信息，或者显示某种状态，如下载进度，退出提示等等。一般情况下，用户要与对话框进行交互，然后返回到被只改的界面以继续运行当前的应用程序。    
二、AlertDialog    
要创建一个AlertDialog，就要用到AlertDialog.Builder的create()方法。   
setTitle：为对话框设置标题  
setIcon：为对话框设置图标   
setMessage：为对话框设置内容   
setView：给对话框设置自定义样式   
setItems：设置对话框要显示的一个list，一般用于显示几个命令时   
setMultiChoiceItems：用来设置对话框显示一系列的复选框    
setSingleChoiceItems：设置单选对话框   
setNeutralButton：普通按钮   
setPositiveButton：给对话框添加“确认”按钮   
setNegativeButton：给对话框添加“取消”按钮   

- 确定对话框  
```java
private void showDialog1() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("确认对话框");//设置标题
	builder.setIcon(R.drawable.ic_launcher);//设置图标
	builder.setMessage("确认对话框提示内容");//设置内容
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		//這個OnClicklistener和上面的包不一樣
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(MainActivity.this, "点击了确定按钮！"，Toast.LENGTH_SHORT).show();
		}
	});
	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
		Toast.makeText(MainActivity.this, "点击了取消按钮！",Toast.LENGTH_SHORT).show();
	}
	});
	AlertDialog dialog = builder.create();//获取dialog
	dialog.show();//显示对话框
}
```
- 单选对话框
```java
//单选对话框
String[] single_list={"男"，"女"};
private void showDialog2(){
	AlertDialog.Builder builder=new AlertDialog.Builder(this);
	builder.setTitle("选择性别")；//设置标题
	builder.setIcon(R.drawable.ic_launcher);//设置图标
	//设置选项：第二个参数0表示默认选中第一个选项
	builder.setSingleChoiceItems(single_list,0,new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog,int which){
			String str=single_list[which];
			Toast.makeText(MainActivity.this,"这个人的性别为"+str,Toast.LENGTH_SHORT).show();
		}
	});
	AlertDialog dialog=builder.create();
	dialog.show();
}
```
- 多选对话框
```java
private void showDialog3() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("爱好");//设置标题
	builder.setIcon(R.drawable.ic_launcher);//设置图标
	builder.setMultiChoiceItems(multi_list, null, new DialogInterface.OnMultiChoiceClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				Toast.makeText(MainActivity.this, "我喜欢上了"+multi_list[which]+"！",Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(MainActivity.this, "我不喜欢"+multi_list[which]+"了！",Toast.LENGTH_SHORT).show();
			}
		}
	});
	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.dismiss();//隐藏对话框
		}
	});
	AlertDialog dialog = builder.create();//获取dialog
	dialog.show();//显示对话框
}
```
- 列表对话框
```java
private void showListDialog() {
	// TODO Auto-generated method stub
	AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
	builder.setIcon(R.drawable.ic_launcher);
	builder.setTitle("下面显示的是列表对话框");
	builder.setItems(job, new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(SecondActivity.this, "我就是喜欢当" + job[arg1], 2000).show();
		}
	});
	AlertDialog dialog = builder.create();
	dialog.show();
}
```
- 自定义布局
```java
//首先，自定义一个布局文件dialog_layout
private void showSelfDialog() {
	LayoutInflater inflater=LayoutInflater.from(this);
	View view=inflater.inflate(R.layout.dialog_layout, null);
	AlertDialog.Builder builder=new AlertDialog.Builder(this);
	builder.setTitle("自定义对话框");
	builder.setIcon(R.drawable.ic_launcher);
	builder.setView(view);
	AlertDialog dialog=builder.create();
	dialog.show();
}
```
