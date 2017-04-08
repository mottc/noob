# OkHttp

​       在使用OkHttp时，都需要有一个OkHttp的客户端，一个请求Request，一个相应Response。OkHttp 处理了很多网络疑难杂症：会从很多常用的连接问题中自动恢复。如果您的服务器配置了多个IP地址，当第一个IP连接失败的时候，OkHttp会自动尝试下一个IP。

---



- ### 同步get方法

  使用get方法时，首先要建立一个OkHttp的客户端。在get方法中，Request只需要url参数。
```java
public class GetExample {
  OkHttpClient client = new OkHttpClient();

  String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public static void main(String[] args) throws IOException {
    GetExample example = new GetExample();
    String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
    System.out.println(response);
  }
}
```
- ### 异步get方法
```java
private final OkHttpClient client = new OkHttpClient();
public void run() throws Exception {
    Request request = new Request.Builder()
        .url("http://publicobject.com/helloworld.txt")
        .build();
 
    client.newCall(request).enqueue(new Callback() {
      @Override 
      public void onFailure(Request request, Throwable throwable) {
        throwable.printStackTrace();
      }
 
      @Override 
      public void onResponse(Response response) throws IOException {
        if (!response.isSuccessful()) 
        	throw new IOException("Unexpected code " + response);
 
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
          System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        System.out.println(response.body().string());
        response.body().close;   //防止内存泄露
      }
    });
}
```
- ### post方法
  使用post方法要比使用get方法复杂一些，因为首先要创建一个RequestBody。RequestBody里面封装了要上传的内容。
1. post方法提交Json数据
```java
public class PostExample {
  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  OkHttpClient client = new OkHttpClient();

  String post(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  String bowlingJson(String player1, String player2) {
    return "{'winCondition':'HIGH_SCORE',"
        + "'name':'Bowling',"
        + "'round':4,"
        + "'lastSaved':1367702411696,"
        + "'dateStarted':1367702378785,"
        + "'players':["
        + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
        + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
        + "]}";
  }

  public static void main(String[] args) throws IOException {
    PostExample example = new PostExample();
    String json = example.bowlingJson("Jesse", "Jake");
    String response = example.post("http://www.roundsapp.com/post", json);
    System.out.println(response);
  }
}
```

2. POST提交键值对
```java
OkHttpClient client = new OkHttpClient();
String post(String url) throws IOException {
    RequestBody formBody = new FormEncodingBuilder()
		.add("platform", "android")
		.add("name", "bug")
		.add("subject", "XXXXXXXXXXXXXXX")
		.build();
        
	Request request = new Request.Builder()
		.url(url)
		.post(formBody)
         .build();
        
	Response response = client.newCall(request).execute();
    	if (response.isSuccessful()) {
           return response.body().string();
        } else {
           throw new IOException("Unexpected code " + response);
        }
    }
```