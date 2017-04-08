# HTML头部标识    
---
>本文档中所出现的标识，都是在写在`<head></head>`中的标识，主要是`<meta>`元素。
>`<meta>`元素可提供有关页面的元信息（meta-information），比如针对搜索引擎和更新频度的描述和关键词。   
>`<meta>`标签位于文档的头部，其包含的内容一般不会显示在网页中。`<meta>`标签的属性定义了与文档相关联的名称/值对。
  


####示例
    <head> 
    <!--The site is designed by mottc, 04/2016 -->
	<title>TITLE</title>  
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="author" content="**@gmail.com">
	<meta name="description" content="*****">
    <meta name="keywords" content="**,**,**"> 
	<meta name="expires" content="31 Dec 2018 24:00:00 GMT">
	</head>

----------  
#### 1.注释
`<!--The site is designed by **,inc 03/2016 --> `       公司版权注释   
####2.页面关键字  
基本语法：	`<meta name="keywords" content="输入具体的关键字"> `  
举例：    	`<meta name="keywords" content="网页，学习">`  
####3.页面描述    
基本语法：`<meta name="description" content="设置页面描述"> `  
举例：`<meta name=“description” content="这是为了学习而建的网站">`  
####4.作者信息   
基本语法：`<meta name="author" content="作者的姓名"> `  
举例：`<meta name="author" content="mottc">`  
####5.网页到期时间  
基本语法：`<meta http-equiv="expires" content="过期时间">`   
举例：`<meta http-equiv="expires" content="31 Dec 2018 24:00:00 GMT">`    
说明: 必须使用GMT时间格式!
####6.禁止读缓存调阅页面内容   
当用户希望访问者每次访问都刷新网页广告的图标或每次都刷 新网页的计数器，就要禁用缓存了。  
基本语法：`<meta http-equiv="pragma" content="no-cache"> `  
####7.设置cookie过期  
基本语法：`<meta http-equiv="set-cookie" content=“过期时间">`    
举例：`<meta http-equiv=“set-cookie” content="31 Dec 2016 24:00:00 GMT">`  
说明: 必须使用GMT时间格式!  
####8.强制以独立页面打开  
基本语法：`<meta http-equiv="window-target" content="_top">`    
####9.定义网页文字及语言   
基本语法：`<meta http-equiv="content-type" content="text/html; charset=字符集类型">`   
举例：`<meta http-equiv="content-type" content="text/html; charset=utf-8">`     
####10.定义网页的定时跳转  
基本语法：`<meta http-equiv="refresh" content="跳转的时间;URL=跳转到的地址">`   
举例：`<meta http-equiv="refresh" content="5;URL=http://www.google.com">`   
####11.网页打开时或退出时的效果  
基本语法：`<meta http-equiv="page-exit" content="revealtrans(duration=延迟时间(秒),transition=数字 (转换方式))">`    
   &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;	&ensp;	  `<meta http-equiv="page-enter" content="revealtrans(duration=延迟时间(秒),transition=数字 (转换方式))">`  
举例：`<meta http-equiv="page-exit" content="revealtrans(duration=10,transition=21)">`   
 &ensp;&ensp;&ensp;&ensp; &ensp;  `<meta http-equiv="page-enter" content="revealtrans(duration=8,transition=12)">`   

|效果|transition|效果|transition|
|---|---|---|---|
|盒状收缩| 0| 溶解| 12|
|盒装展开|1 |左右向中部收缩| 13 |
|圆形收缩|2 |中部向左右展开 |14 |
|圆形展开| 3 |上下向中部收缩| 15|
|向上擦除| 4 |中部向上下展开| 16|
|向下擦除| 5 |阶梯状向左下展开| 17 |
|向左擦除 |6 |阶梯状向左上展开| 18|
|向右擦除 |7 |阶梯状向右下展开| 19|
|垂直百叶窗| 8| 阶梯状向右上展开| 20|
|水平百叶窗| 9| 随即水平线 |21 |
|横向棋盘式| 10 |随即垂直线 |22|
|纵向棋盘式| 11 |随即 |23|  
<br><br>

> ## 小结   
|属性|值|描述|
|---|---|---|
|name|keywords<br>description<br>author|把 content 属性关联到一个名称。|
|http-equiv|expires<br>pragma<br>set-cookie<br>window-target<br>content-type<br>refresh<br>page-exit<br>page-enter|把 content 属性关联到 HTTP 头部。|






    

    
