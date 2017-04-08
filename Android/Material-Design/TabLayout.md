# TabLayout

Tab滑动切换View并不是一个新的概念，但是Google却是第一次在support库中提供了完整的支持，而且，Design library的TabLayout 既实现了固定的选项卡 的宽度平均分配，也实现了可滚动的选项卡宽度不固定同时可以横向滚动。

```java
TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
tabLayout.addTab(tabLayout.newTab().setText("tab1"));
tabLayout.addTab(tabLayout.newTab().setText("tab2"));
tabLayout.addTab(tabLayout.newTab().setText("tab3"));
```

但大部分时间我们都不会这样用，通常滑动布局都会和ViewPager配合起来使用。

```java
mViewPager = (ViewPager) findViewById(R.id.viewpager);
// 设置ViewPager的数据等
setupViewPager();
TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
tabLayout.setupWithViewPager(mViewPager);
```


通过一句话setupWithViewPager，我们就把ViewPager和TabLayout结合了起来。