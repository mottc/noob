#HTML页面标签   
---
>本文档中所出现的标识，都是在写在`<body></body>`中的标识，大都可以在页面上显示出来。  

###一、标题标签`<hn>`    
用于指定不同级别的标题 (n=1～6),从`<h1>`到`<h6>`共有六种标题标识。  
基本语法：`<hn>` 标题文字 `</hn>`  
举例：
```
<body>
  <h1>网站设计与开发</h1>
  <h2>网站设计与开发</h2>   
  <h3>网站设计与开发</h3> 
  <h4>网站设计与开发</h4> 
  <h5>网站设计与开发</h5> 
  <h6>网站设计与开发</h6> 
</body>   
```  
###二、换行标签`<br>`  
这是一个单标签，相当于“回车”。  
###三、水平线`<hr>`  
作为文本与文本之间的分界标识，通常是一个全宽的水平线。  
###四、段落标签`<p></p>`  
`<p></p>`之间的文字以段落的形式显示，其间的文字格式最多包括一个空格。 
###五、预格式化`<pre></pre>`  
在`<pre></pre>`之间的内容将以预格式化的文本方式显示，包括回车换行以及跳格等。      
###六、文字标签     
#####1、字体标签`<font></font>`  
举例：`<p><font size="6" color="#8a2be2" face="微软雅黑"> 文字大小、文字颜色、文字字体</font></p> `  
注意：Html5已基本废弃不用该标签，而用CSS来设定！  
#####2、字体字号属性标签  
举例：`<p style="color: red;font-size: 30px;font-family:微软雅黑;">文字大小、文字颜色、文字字体</p> `     
#####3、字符格式   
|格式标记|字体效果|
|---|---|
|`<b>受影响的文字</b>`|加粗|
|`<i>受影响的文字</i>`|斜体|
|`<sub>受影响的文字</sub>`|下标|
|`<sup>受影响的文字</sup>`|上标|
|`<em>受影响的文字</em>`|斜体|
|`<strong>受影响的文字</strong>`|粗体|
|`<u>受影响的文字</u>`|下划线（弃用）|  
###七、无断行空格  
 `&nbsp;`相当于实际的空格效果。（切勿忘记";"!）   
###八、列表标签    
####1、有序号列表  
基本语法：
```
<ol type="序号类型"  start=n> 
   <li>  项目1 
   <li>  项目2 
</ol>
```    
说明： `序号类型可以是数字、小写字母、大写字母。`  
举例：  
```  
<ol type="A"> 
  <li>项目一 
  <li>项目二 
     <ol type="a"> 
       <li>子项目A 
       <li>子项目B 
     </ol> 
  <li>项目三 
</ol> 
```    
####2、无序号列表  
基本语法：  
```  
<ul type="square"> 
   <li>第一章 
   <li>第二章 
   <li>第三章 
   <li>第四章 
</ul>   
```  
####3.定义列表  
基本语法：  
`<dl></dl>`  设定定义列表的标识    
`<dt>`  设定定义列表的项目   
`<dd>`  设定定义列表的项目说明   
`<dl compact>`  设定紧密排列的定义列表    
举例：  
```  
<dl compact> 
<dt>Today 
<dd>Today will be yesterday. 
<dt>Tomorrow 
<dd>Tomorrow will be today. 
</dl>  
```    
####4、菜单列表  
举例：    
```
<menu type="toolbar"> 
 <li> 
  <menu label="File"> 
   <button type="button" onclick="file_new()">New</button> 
   <button type="button" onclick="file_open()">Open</button> 
   <button type="button" onclick="file_save()">Save</button> 
  </menu> 
 </li> 
 <li> 
  <menu label="Edit"> 
   <button type="button" onclick="edit_cut()">Cut</button> 
   <button type="button" onclick="edit_copy()">Copy</button> 
   <button type="button" onclick="edit_paste()">Paste</button> 
  </menu> 
 </li> 
</menu>  
```  
###九、表格标签  
基本语法：  
`<table> </table>` 用来定义一个表格。   
`<caption> </caption>`  给出表格的标题。  
`<tr> </tr>`  表格行。   
`<th> </th>` 表头单元。   
`<td> </td>` 表格单元。    
举例：    
```  
<table border="1"> 
  <caption>学生信息</caption> 
    <tr> 
     <th>姓名</th> 
	 <th>性别</th> 
	 <th>年龄</th> 
	</tr> 
	<tr> 
	 <td>张丽</td> 
	 <td>女</td> 
	 <td>20</td> 
	</tr> 
</table>   
```  
属性：    

|属性|值|描述|
|---|---|---|
|align|•left <br>•center<br> •right| 规定表格相对周围元素的对齐方式(不赞成使用,请使用样式代替)|
|bgcolor|•rgb(x,x,x)<br> •#xxxxxx <br>•colorname| 规定表格的背景颜色(不赞成使用。请使用样式代替)|
|border|pixels|规定表格边框的宽度|
|cellpadding|•pixels <br>•% |规定单元边沿与其内容之间的空白|
|cellspacing|•pixels <br>•% |规定单元格之间的空白|
|width|•% <br>•pixels|规定表格的宽度|
|colspan|•number|创建跨多列的单元格|
|rolspan|•number|跨多行的单元格|      
###十、图像标签  
基本语法：  
`<img src=" " alt=" ">`  
举例：    
`<img src="../img/IMG_0055.JPG" alt="美图" width="400" height="278">`    

|属性|值|描述|
|---|---|---|
|alt|text|规定图像的替代文本|
|src|URL| 规定显示图像的 URL|
|height|·pixels<br>·%|规定图像的高度|
|width|•pixels <br>•% |规定图像的宽度|  
###十一、超链接   
####1、文本链接  
`<a  href="register.html">[免费注册]</a>`    
####2、图像链接    
`<a  href="链接地址"> <img src="图像地址"> </a>`   
####3、邮件链接  
`<a href=“mailto:电子邮件地址”>…</a>`  
####4、下载链接  
`<a href=“文件地址”>…</a>`    
###十二、表单标签  
####1、input标签    
`<input>`是个单标记，它必须嵌套在表单标记中使用，用于定义一个用户的输入项。   
基本语法：  
`<form> <input name=" " type=" "> </form>`  
说明：  
`<input>`标记主要属性有：type，name，size，value，maxlength等。  
其中name和type是必选的两个属性；  
 • maxlength：设置单行输入框可以输入的最大字符数，例如限制邮政编码为6个数字、密码最多为10个字符等等；  
 • size：设置单行输入框可显示的最大字符数，这个值总是小于等于maxlength属性的值，当输入的字符数超过文本框的长度时，用户可以通过移动光标来查看超过超出的内容；   
 • value：文本框的值，可以通过设置value属性的值来指定当表单首次被载入时显示在输入框中的值。   
***type类型***    
  
|值|描述|
|---|---|
|button|定义可点击按钮（多数情况下，用于通过JavaScript启动脚本）|
|checkbox|定义复选框|
|file|定义输入字段和"浏览"按钮，供文件上传|
|hidden|定义隐藏的输入字段|
|image|定义图像形式的提交按钮|
|password|定义密码字段。该字段中的字符被掩码。|
|radio|定义单选按钮。|
|reset|定义重置按钮。重置按钮会清除表单中的所有数据。|
|submit|定义提交按钮。提交按钮会把表单数据发送到服务器。 |
|text|定义单行的输入字段，用户可在其中输入文本。默认宽度为20个字符。|   
|email|用于应该包含 e-mail 地址的输入域|  
|URL|用于应该包含 e-mail 地址的输入域|
|number|用于应该包含数值的输入域,还能够设定对所接受的数字的限定|
|range|包含一定范围内数字值的输入域,外观为一个滑动条|
|date|选取日、月、年|
|color|颜色选择器用于挑选色彩，外观为一个取色器|
举例：  
**①type类型为text**    
```
<form> 
登录名：<input name="text"  type="text" maxlength="30" size="20" value="YourName">   
</form> 
```    
**②type类型为image**   
```
<form> 
<input name="image"  type="image" src="url"> 
</form> 
```     
**③type类型为number**    
```
<form>
<input type="number" name="points" min="1" max="10">  
</form>
```  
**④type类型为range**    
```
<form>
<input type="range" name="points" min="1" max="10">  
</form>
```  
####2、多行文本输入框`<textarea>`    
用`<textarea>`标记可以来定义高度超过一行的文本输 入框,首标记`<textarea>`和尾标记`</textarea>`之间 的内容就是显示在文本输入框中的初始信息。   
基本语法：  
```
<form> 
<textarea name="textarea" cols="" rows="" wrap="">初始信息</textarea> 
</form> 
```   
说明：  
name：用于指定文本输入框的名字。   
rows：设置多行文本输入框的行数，此属性的值是数字，浏览器会自动为高度超过一行的文本输入框添加垂直滚动条。但是，当输入文本的行数小于或等于rows属性的值时，滚动条将不起作用。    
cols：设置多行文本输入框的列数。    
disabled：规定第一次加载的时候该文本区不可用。    
Readonly：将文本区的内容设置为不可修改。    
举例:    
```
<form> 
请提宝贵意见：<br>   
<textarea name="yoursuggest" cols="50" rows="3">意见</textarea>  <br>    
<input type="submit" value="提交">    
<input type="reset" value="重写">    
</form>  
```  
####3、下拉列表框    
在表单中，通过`<select>`和`<option>`标记可以在浏览器中设计一个 下拉式的列表或带有滚动条的列表，用户可以在列表中选中一个或多个选项。  
首标记`<select>`和尾标记`</select>`之间的内容就是一个下拉式菜单的内容。  
`<select>`标记必须与`<option>`标记配套使用。  
`<option>`标记用于定义列表中的各个选项。  
基本语法：   
```
<form> 
<select name=" " size=" " > 
<options value=" "> 
…
<options value=" "> 
</select> 
</form> 
```  
说明：  
name：设定下拉列表名字。  
size：可选项，用于改变下拉框的大小。size属性的值是数字，表示显示在列表中选项的数目，当size属性的值小于列表框中的列表项数目时，浏览器会为该下拉框添加滚动条，用户可以使用滚动条来查看所有的选项，size默认值为1。  
multiple：如果加上该属性，表示允许用户从列表中选择多项。  
`<option>`标记用来定义列表中的选项，设置列表中显示的文字和列表条目的值，列表中每个选项有一个显示的文本和一个value值 (当选项被选择时传送给处理程序的信息)。  
`<option>`标记必须嵌套在`<select>`标记中使用。一个列表中有多少个选项，就要有多少个`<option>`标记与之相对应，选项的具体内容写在每个`<option>`之后。  
`<option>`标记有两个属性：value和selected，它们都是可选项。 
 • value: 用于设置当该选项被选中并提交后，浏览器传送给服务器的数据。如果是默认状态，浏览器将传送选项的内容。 
 • selected :用来指定选项的初始状态，表示该选项在初始时被选中。  
举例：    
``` 
<form> 
你最喜欢的运动： 
<select name="sports"> 
  <option value="football">足球 
  <option value="bastetball">篮球 
  <option value="volleyball">排球 
</select> <br> 
<select  name="爱好" size=4  multiple="multiple"> 
  <option  value="1">音乐 
  <option  value="2">美术 
  <option  value="3">体育 
</select> 
<input type="submit" value="提交"> 
</form>
```  
####4、定义域集合`<fieldset>`  
如果表单内包含多个控件，可以通过`<fieldset>`标签将相关主题 的控件或标签组合在一起（定义域集合），这样使网页中的表单分布更合理，结构更清晰，并增加网页的易读性，同时也有利于Tab键在元素之间移动。  
基本语法：    
```
<form> 
<fieldset> 
<legend>请登录</legend> 
用户名: <input type="text"  name="userName"  id="userName"> 
密码: <input type="password" name="userPwsd"> 
</fieldset> 
</form>
```  
说明：   
`<fieldset>` 标签将表单内容的一部分打包，生成一组相关表单的字段。  
`<legend>`标签为`<fieldset>`设置标题。  
当一组表单元素放到 `<fieldset>`标签内时，浏览器会以特殊方式来显示它们，它们可能有特殊的边界或者可创建一个子表单来处理这些元素。
 






















  