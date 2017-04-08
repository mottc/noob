#Retrofit

使用Retrofit，要添加依赖。
```
compile 'com.squareup.retrofit2:retrofit:2.1.0'
```
如果和本示例一样，要把返回的数据进行格式转换，还需要添加其它依赖。（这里只写出Json数据转换的依赖）
```
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
```
---



1. 首先创建请求的接口。在这个接口里面，不会包含基础的路径，只用来提供不同查询的接口。
```java
public interface GetInfoService {
    @GET("{user}")
    Call<User> getInfo(@Path("user") String user);
}
```
2. 在上面的接口中，所需要的返回数据为User对象。所以我们还需要创建一个User类。

```java
public class User {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```

3. 然后就要请求数据了。在这个请求的例子中，因为要把返回的Json数据转换为User对象，所以在创建mRetrofit时，要添加	`.addConverterFactory(GsonConverterFactory.create())`。
```java
public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.text)
    TextView mText;

    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_acticity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://7xktkd.com1.z0.glb.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetInfoService getInfoService = mRetrofit.create(GetInfoService.class);
        Call<User> call = getInfoService.getInfo("ccc");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    String firstName = response.body().getFirstName();
                    String lastName = response.body().getLastName();
                    mText.setText(firstName + " " + lastName);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```