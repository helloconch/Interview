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
字体text-primary text-center 
通过Bootstrap，我们要做的只是给图片添加 img-responsive class属性。这样图片的宽度就能完美地适配你的页面的宽度了。

既然我们在使用Bootstrap，我们可以通过居中头部元素来使它看起来更棒。 我们所要做的只是把text-center class属性添加给 h2 元素。

Bootstrap有它自己的 button 按钮风格， 看起来要比默认的按钮好看得多。在三只猫咪图片下面创建一个新的 button 元素。
给它添加 btn/btn-block/btn-primary/btn-info/btn-danger class 属性以及"Like"文本。

class属性 col-md-* 正被使用。在这里，md 表示 medium (中等的)，* 代表一个数字，它指定了这个元素所占的列宽。通过此图表的属性设置可知，在中等大小的屏幕上(例如笔记本电脑)，元素的列宽被指定了。
在我们创建的 Cat Photo App 中，将会使用 col-xs-* ，其中 xs 是 extra small 缩写（应用于较小的屏幕，比如手机屏幕），* 是你需要填写的数字，代表在一行中,各个元素应该占的列宽。
把 Like, Info 和 Delete 三个按钮一并放入一个 <div class="row"> 元素中；然后，其中的每一个按钮都需要各自被一个 <div class="col-xs-4"> 元素包裹。
当div 元素设置了 class 属性 row 之后，那几个按钮便可嵌入其中。

把 "Things cats love" 中的 "love" 放到 span 标签下,然后为其添加 text-danger class 来使文字变成红色


Font Awesome 是一个非常方便的图标库。这些图标都是矢量图形，被保存在 .svg 的文件格式中。这些图标就和字体一样，你可以通过像素单位指定它们的大小，它们将会继承其父HTML元素的字体大小。
你可以将 Font Awesome 图标库增添至任何一个应用中，方法很简单，只需要在你的 HTML 头部增加下列代码即可：
<link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css"/>
i 元素起初一般是让其它元素有斜体(italic)的功能，不过现在一般用来指代图标。你可以将 Font Awesome 中的 class 属性添加到 i 元素中，
把它变成一个图标，比如：<i class="fa fa-info-circle/fa-trash/fa-thumbs-up/fa-paper-plane"></i>


给你表单的文本输入框增加 classform-control 。在你的表单提交按钮中增加 class btn btn-primary 。同样，在这个提交按钮中增加 Font Awesome 的 fa-paper-plane 图标。


```
