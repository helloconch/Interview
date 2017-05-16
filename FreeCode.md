### html

##### 样式
```
.text-content{
 color:pink !important;//最强大
}

类选择器 .class ,对同一元素，使用多个类选择器，在styles中后面类选择器，覆盖前面的类选择器，

ID选择器 #ID，可覆盖类选择器

内联样式表，覆盖类选择器和ID选择器
```

##### Bootstrap
```
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
常用class

HTML内容放在class为container-fluid的div下。

通过Bootstrap，我们要做的只是给图片添加 img-responsive class属性。这样图片的宽度就能完美地适配你的页面的宽度了。

既然我们在使用Bootstrap，我们可以通过居中头部元素来使它看起来更棒。 我们所要做的只是把text-center class属性添加给 h2 元素。

Bootstrap有它自己的 button 按钮风格， 看起来要比默认的按钮好看得多。在三只猫咪图片下面创建一个新的 button 元素。
给它添加 btn/btn-block/btn-primary/btn-info/btn-danger class 属性以及"Like"文本。

class属性 col-md-* 正被使用。在这里，md 表示 medium (中等的)，* 代表一个数字，它指定了这个元素所占的列宽。通过此图表的属性设置可知，在中等大小的屏幕上(例如笔记本电脑)，元素的列宽被指定了。
在我们创建的 Cat Photo App 中，将会使用 col-xs-* ，其中 xs 是 extra small 缩写（应用于较小的屏幕，比如手机屏幕），* 是你需要填写的数字，代表在一行中,各个元素应该占的列宽。
把 Like, Info 和 Delete 三个按钮一并放入一个 <div class="row"> 元素中；然后，其中的每一个按钮都需要各自被一个 <div class="col-xs-4"> 元素包裹。
当div 元素设置了 class 属性 row 之后，那几个按钮便可嵌入其中。



```
