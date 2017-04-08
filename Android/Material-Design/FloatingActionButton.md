# FloatingActionButton

FloatingActionButton是一个悬浮在界面之上的圆形图标，它拥有一些特别的动画。FloatingActionButton有两种大小：默认和mini型，可以通过fabSize属性进行设置。它是继承于ImageView的，所以可以通过setImageDrawable(Drawable)来设置其显示的图标。它的背景色默认是当前主题的colorAccent,但是可以通过setBackgroudTintList(colorStateList)来设置。

```xml
 <android.support.design.widget.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="right|bottom"
    android:src="@drawable/ic_add"/>
```



### XML属性及对应方法

- elevation：设置悬浮高度，参数为浮点数。

  对应方法：setCompatElevation(float)

- fabSize：设置图标大小。ffffffff代表自适应，1代表mini，2代表normal。

  对应方法：setSize(int)

- rippleColor：设置波纹颜色，参数为一个颜色。

  对应方法：setRippleColor(int)

- useCompatPadding：是否添加内边距，参数为true或者false。

  对应方法：setUseCompatPaddding(boolean)







