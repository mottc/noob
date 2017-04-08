# Snackbar

Snackbar可以用来替代Toast，起到提示作用。它和Toast最大的不同就是它可以相应点击事件。

### 基本用法

```java
Snackbar.make(view, message, duration)
		.setAction(action message, click listener)
         .show();
```
##### 方法:

make() – 生成Snackbar消息
setAction() – 设置点击事件action
show() – 显示Snackbar消息
##### 属性:
1. make()方法
   - 第一个参数是一个view, snackbar会试着寻找一个父view来hold这个view。Snackbar将遍历整个view tree 来寻找一个合适的父view。
   - 第二个参数为message，即要显示的文字内容。
   - 第三个参数duration和Toast中的duration参数类似，只能是LENGTH_SHORT 或 LENGTH_LONG，不能是其它任何随机数。
2. setAction()方法
   - 第一个参数为action message，是Snackbar中最右侧按钮显示的文字。
   - 第二个参数为click listener，是一个点击的监听事件。

### 示例

```java
Snackbar.make(rootlayout, "Hello SnackBar!", Snackbar.LENGTH_SHORT)
       .setAction("Undo", new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Perform anything for the action selected
           }
       })
       .show();
```

我们可以使用额外的可选操作来配置snackbar。

比如：

 `setActionTextColor` 设置action按钮的颜色。

`setDuration`设置持续时间。

```java
Snackbar.make(rootlayout, "Hello SnackBar!", Snackbar.LENGTH_SHORT)
       .setAction("Undo", new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Perform anything for the action selected
           }
       })
       .setActionTextColor(R.color.material_blue)
       .setDuration(4000)
  	   .show();
```

当父容器rootlayput为CoordinateorLayout且存在Floating Action Button时，Snackbar出现时，不会遮挡住Floating Action Button。



