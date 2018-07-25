---
title: ..
tags: retrofit,rxjava,学习MVP
---
学习MVP Rxjava Retrofit 的封装
参考地址: https://github.com/Lloyd0577/MVPDemo
首先作者的思路还是很清晰的。分析透彻。每一个行的代码发生的更改以及引入的库，都说的很细。
确实受益颇多。网上其他的教程确实质量有限。
    
    rxbus的使用
 1. 先试用Retrofit
 2. 使用Rxjava
 3. 创建Mvp，mvp有多个变形。
 4. 沉浸式状态栏  github地址:  https://github.com/gyf-dev/ImmersionBar
 5. 使用的第三种方法 
 

``` javascript
使用ImmersionBar的fitsSystemWindows(boolean fits)方法

    ImmersionBar.with(this)
        .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
        .statusBarColor(R.color.colorPrimary)
        .init();
```

**总结一下，确实是最近写的Activity太冗长啦，而且逻辑也是不太好顺啦。
只想说MVP就是想把网络请求的内容给了Presenter，并且Presenter将网络请求的结果传递给View展示。
,让View专注于视图的展示。**