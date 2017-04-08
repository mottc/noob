# TextInputLayout

首先要明确TextInputLayout是一种布局方式。它包含一个EditText(或者其子类)，当EditText中有输入时，它的提示内容会浮动上移而不是消失。

setErrorEnabled(boolean) 设置是否可以进行错误提示。

setError(CharSequence)设置错误提示的内容。

setCounterEnabled(boolean)设置是否计数。

setPasswordVisibilityToggleEnabled(boolean)设置密码文本后面的密码可见小眼睛。

在TextInputLayout中推荐使用TextInputEditText，这是一个EditText的子类，它和TextInputLayout配合使用会有更好的显示效果。

使用示例：

```xml
 <android.support.design.widget.TextInputLayout
         android:layout_width="match_parent"
         android:layout_height="wrap_content">

     <android.support.design.widget.TextInputEditText
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:hint="@string/form_username"/>

 </android.support.design.widget.TextInputLayout>
```



### xml属性&常用方法

- counterEnabled: false or true，用于设置字符计数器的开启或关闭。

  对应方法：setCounterEnabled(boolean)

- counterMaxLength：设置字符计数器的最大长度。（仅用于设置计数器最大值，并不影响文本实际能输入的最大长度）

  对应方法：setCounterMaxLength(int)

- errorEnabled：false or true，用于设置错误提示是否开启。

  对应方法：setErrorEnabled(boolean)

- hint：设置输入框的提示语。

  对应方法：setHint(CharSequence)

- hintAnimationEnabled：true or false，开启或关闭hint浮动成标签的动画效果。

  对应方法：setHintAnimationEnabled(boolean)

- hintEnabled：true or false，开启或关闭hint浮动的功能，设为false的话就和之前的EditText一样，在输入文字后，提示语就消失了。

  对应方法：setHintEnabled(boolean)

- hintTextAppearance：设置hint的style，字体颜色，字体大小等，可引用系统自带的也可以自定义。若要使用请统一使用，以保证APP体验的统一性。

  对应方法：setHintTextAppearance(int)

当文本输入类型为密码时，系统提供了一个开关来控制密码是否可见（默认为眼睛👁）。此为design包24.2.0新提供的功能。

- passwordToggleEnabled：控制密码可见开关是否启用。设为false则该功能不启用，密码输入框右侧也没有控制密码可见与否的开关。

  对应方法：setPasswordVisibilityToggleEnabled(boolean)

- passwordToggleDrawable：设置密码可见开关的图标。通常我们会在不同的情况下设定不同的图标，可通过自定义一个selector，根据“state_checked”属性来控制图标的切换。后面代码实践里会有示范。

  对应方法：setPasswordVisibilityToggleDrawable(Drawable)

- passwordToggleTint：控制密码可见开关图标的颜色。在开启或关闭的状态下我们可以设定不同的颜色，可通过自定义一个color的selector，根据“state_checked”和“state_selected”属性来控制颜色的切换。后面代码实践里会有示范。

  对应方法：setPasswordVisibilityToggleTintList(ColorStateList)





### 参考文章：

- [官方文档](https://developer.android.com/reference/android/support/design/widget/TextInputLayout.html)
- [简单心里·技术团队—TextInput详解·Material Design Part 1](https://jiandanxinli.github.io/2016-11-14.html)



