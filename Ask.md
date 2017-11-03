#### 接口的意义
```
规范、利于扩展
```
#### 接口与抽象类区别
```
1.接口中所有方法都是抽象的，而抽象类中可以有非抽象方法。
2.接口中所有非方法和属性都是Public类型，而抽象类中可以有Private类型。
3.普通类实现接口的话，要实现接口里所有的方法。但普通类定义为抽象类时，可以选择性实现或不实现接口里的方法。
4.普通类继承抽象类的话，要实现抽象类里得所有抽象方法。但普通类定义为抽象类，继承抽象类，可以选择性实现抽象类的抽象方法。
5.普通类可以实现多个接口,普通类只能继承一个抽象类。接口可以继承多个接口。
6.抽象类和接口无法自己进行实例化

接口是对动作的抽象，而抽象类是对根源的抽象。对于抽象类，比如男人，女人这两个类，
那我们可以为这两个类设计一个更高级别的抽象类--人。
对于接口，我们可以坐着吃饭，可以站着吃饭，可以用筷子吃饭，可以用叉子吃饭，甚至可以学三哥一样用手抓着吃饭，
那么可以把这些吃饭的动作抽象成一个接口--吃饭。所以在高级语言中（如Java,C#），
一个类只能继承一个抽象类（因为你不可能同时是生物又是非生物）。
但是一个类可以同时实现多个接口，比如开车接口，滑冰接口，啪啪啪接口，踢足球接口，游泳接口。

```
#### 抽象类的意义
```
1.为子类提供一个公共的类型。
2.抽象类更利于代码的维护和重用。
```
#### 内部类的作用
```
为什么要使用内部类？在《Think in java》中有这样一句话：
使用内部类最吸引人的原因是：
每个内部类都能独立地继承一个（接口的）实现，
所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。
内部类最大的优点就在于它能够非常好的解决多重继承的问题。

1.内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。
2.在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类。
3.创建内部类对象的时刻并不依赖于外围类对象的创建。
4.内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。
5.内部类提供了更好的封装，除了该外围类，其他类都不能访问


public class OuterClass {
    private String name ;
    private int age;

    /**省略getter和setter方法**/
    
    public class InnerClass{
        public InnerClass(){
            name = "chenssy";
            age = 23;
        }
        
        public void display(){
            System.out.println("name：" + getName() +"   ;age：" + getAge());
        }
    }
    
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
    }
}
 在Java中内部类主要分为成员内部类、局部内部类、匿名内部类、静态内部类。
非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围内，但是静态内部类却没有。
```

#### 父类的静态方法能否被子类重写
```
不能
```
[java排序算法](http://blog.csdn.net/qy1387/article/details/7752973)


#### 列举java的集合和继承关系
```
Collection->List(LinkedList,ArrayList,Vector(->Stack))
Collection->Set
Map->Hashtable
Map->HashMap
Map->WeakHashMap

```

####java虚拟机的特性
```
java语言特点就是与平台的无关性，java虚拟机是实现这一特性的关键所在。
一般高级语言想在不同平台进行运行，至少需要编译不同的目标代码。
而引入java虚拟机，java语言在不同平台则不需要重新编译。
java虚拟机在执行字节码时，把字节码解释成具体平台的机器指令执行。
```

####哪些情况下的对象会被垃圾回收机制处理掉
```
垃圾回收基本做法是分代回收。内存区域被划分为不同时代（年轻、年老、永久）。
对象根据其存活时间被保存在不同的时代中。当一个对象存活时间足够长，将被复制年老时代。
每个时代可以使用不同的垃圾回收算法。
```

####进程和线程的区别
```
一个应用程序至少有一个进程，一个进程至少有一个线程。
线程的划分尺度小于进程，使得多线程程序并发性高。
进程在执行过程中拥有独立的内存单元，而多线程共享内存，极大提高程序运行效率。
线程不能独立运行，必须依存在应用程序中。

进程有独立的地址空间，一个进程崩溃后，在保护模式下不会对其它进程产生影响，而线程只是一个进程中的不同执行路径。
线程有自己的堆栈和局部变量，但线程之间没有单独的地址空间，一个线程死掉就等于整个进程死掉，
所以多进程的程序要比多线程的程序健壮，但在进程切换时，耗费资源较大，效率要差一些。
但对于一些要求同时进行并且又要共享某些变量的并发操作，只能用线程，不能用进程。
```

####java中==和equals和hashCode的区别
```
1.java的基本数据类型，也称为原始的数据类型。它们分别是： 
byte, short, char, int, long, float, double, boolean . 
基本数据类型进行 “==” 比较时，比较的是 它们的值是否相同，无equals方法。

2.当 两个对象使用 “==” 进行比较时，比较的是它们在 内存中存放的地址。
也就是说，除非是 同一个new() 出来的两个对象(内存地址相同)， 否则比较的结果都是false。

3.默认情况下，当我们调用equals() 方法时，也仍然是比较两个对象 是否指向同一块内存空间。 

4.诡异的String 
String c="CCCCCC";
String c1=c;
System.out.println(c==c1);
System.out.println(c.equals(c1));
		
String c2="CCCCCC";
System.out.println(c==c2);
System.out.println(c.equals(c2));
true
true
true
true

```

####HashMap的实现原理
```
HashMap是基于哈希表Map接口的非同步实现。可以存在null-null组合，并且不保证存储顺序不变。
在java语言中，最基本的结构就是2种，1种是数组，另一种是模拟指针。
所有的数据结构，都可以用这2个基本的结构来构造，HashMap也不例外，
HashMap是一个“链表散列”的数据结构，即数组+链表结构，
HashMap底层是一个数组结构，每一个数组项是一个链表。
```

####int-char-long各占多少字节数
```
      位数   字节数
byte   8      1

short  16     2

int    32     4

long  64      8

float 32      4

double 64     8

char 16       2
```

####java int与integer的区别
```
从大的方面区别int是基本数据类型，integer是包装类。
int基本类型，直接存值，integer是对象，用一引用指向这个对象。
```


####string-stringbuffer-stringbuilder区别
```
String 字符串常量

StringBuffer 字符串变量（线程安全）

StringBuilder 字符串变量（非线程安全）
```

####java多态
```
面向对象的三大特性：封装、继承、多态。从一定角度来看，封装和继承几乎都是为多态而准备的。
多态的定义：指允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式。
多态的作用：消除类型之间的耦合关系。
现实中，关于多态的例子不胜枚举。比方说按下 F1 键这个动作，如果当前在 Flash 界面下弹出的就是 AS3 的帮助文档；
如果当前在 Word 下弹出的就是 Word 帮助；在 Windows 下弹出的就是 Windows 帮助和支持。同一个事件发生在不同的对象上会产生不同的结果。
多态存在的三个必要条件 一、要有继承； 二、要有重写； 三、父类引用指向子类对象。
Java中多态的实现方式：接口实现，继承父类进行方法重写，同一个类中进行方法重载。
```
####什么导致线程阻塞
```
阻塞指的是暂停一个线程的执行以等待某个条件发生（如某资源就绪）,Java 提供了大量方法来支持阻塞，下面让我们逐一分析。
1.sleep() 方法：sleep() 允许 指定以毫秒为单位的一段时间作为参数，它使得线程在指定的时间内进入阻塞状态，
不能得到CPU 时间，指定的时间一过，线程重新进入可执行状态。 典型地，sleep() 被用在等待某个资源就绪的情形：
测试发现条件不满足后，让线程阻塞一段时间后重新测试，直到条件满足为止。
2.suspend() 和 resume() 方法：两个方法配套使用，suspend()使得线程进入阻塞状态，并且不会自动恢复，
必须其对应的resume() 被调用，才能使得线程重新进入可执行状态。
典型地，suspend() 和 resume() 被用在等待另一个线程产生的结果的情形：测试发现结果还没有产生后，让线程阻塞，
另一个线程产生了结果后，调用 resume() 使其恢复。
3.yield() 方法：yield() 使得线程放弃当前分得的 CPU 时间，但是不使线程阻塞，即线程仍处于可执行状态，
随时可能再次分得 CPU 时间。调用 yield() 的效果等价于调度程序认为该线程已执行了足够的时间从而转到另一个线程.
4.wait() 和 notify() 方法：两个方法配套使用，wait() 使得线程进入阻塞状态，
它有两种形式，一种允许 指定以毫秒为单位的一段时间作为参数，另一种没有参数，
前者当对应的 notify() 被调用或者超出指定时间时线程重新进入可执行状态，后者则必须对应的 notify() 被调用.
它们与 suspend() 和 resume() 方法对没有什么分别，但是事实上它们是截然不同的。
区别的核心在于，前面叙述的所有方法，阻塞时都不会释放占用的锁（如果占用了的话），而这一对方法则相反。

```
####Java中hashmap和hashtable的区别
```
1、 继承和实现区别
　　Hashtable是基于陈旧的Dictionary类，完成了Map接口；
  HashMap是Java 1.2引进的Map接口的一个实现（HashMap继承于AbstractMap,AbstractMap完成了Map接口）。
　　2、 线程安全不同
　　HashTable的方法是同步的，HashMap是未同步，所以在多线程场合要手动同步HashMap。
　　3、 对null的处理不同
　　HashTable不允许null值(key和value都不可以),HashMap允许null值(key和value都可以)。
   即 HashTable不允许null值其实在编译期不会有任何的不一样，会照样执行，只是在运行期的时候Hashtable中设置的话回出现空指针异常。
   HashMap允许null值是指可以有一个或多个.
```
####如何导入外部数据库
```
把原数据库包括在项目源码的 res/raw
android系统下数据库应该存放在 /data/data/com..（package name）/ 目录下，所以我们需要做的是把已有的数据库传入那个目录下.
操作方法是用FileInputStream读取原数据库，再用FileOutputStream把读取到的东西写入到那个目录.
```
####本地广播和全局广播有什么差别
```
本地广播数据在本应用范围内传播，不用担心隐私数据泄露的问题。 
不用担心别的应用伪造广播，造成安全隐患。 相比在系统内发送全局广播，它更高效。
```
####AIDL解决了什么问题
```
AIDL (Android Interface Definition Language) 是一种IDL 语言，
用于生成可以在Android设备上两个进程之间进行进程间通信(interprocess communication, IPC)的代码。
如果在一个进程中（例如Activity）要调用另一个进程中（例如Service）对象的操作，就可以使用AIDL生成可序列化的参数。
AIDL IPC机制是面向接口的，像COM或Corba一样，但是更加轻量级。它是使用代理类在客户端和实现端传递数据。
```
####intentService作用是什么
```
提供了一个onStartCommand()方法的默认实现，它将Intent先传送至工作队列，然后从工作队列中每次取出一个传送至onHandleIntent()方法，
在该方法中对Intent对相应的处理。
```
####Activity/Window/View三者的差别,fragment的特点
```
Activity像一个工匠（控制单元），Window像窗户（承载模型），View像窗花（显示视图） LayoutInflater像剪刀，Xml配置像窗花图纸。
1.在Activity中调用attach，创建了一个Window
2.创建的window是其子类PhoneWindow，在attach中创建PhoneWindow
3.在Activity中调用setContentView(R.layout.xxx)
4.其中实际上是调用的getWindow().setContentView()
5.调用PhoneWindow中的setContentView方法
6.创建ParentView： 作为ViewGroup的子类，实际是创建的DecorView(作为FramLayout的子类）
7.将指定的R.layout.xxx进行填充 通过布局填充器进行填充【其中的parent指的就是DecorView】
8.调用到ViewGroup
9.调用ViewGroup的removeAllView()，先将所有的view移除掉
10.添加新的view：addView()


fragment 特点
Fragment可以作为Activity界面的一部分组成出现；
可以在一个Activity中同时出现多个Fragment，并且一个Fragment也可以在多个Activity中使用；
在Activity运行过程中，可以添加、移除或者替换Fragment；
Fragment可以响应自己的输入事件，并且有自己的生命周期，它们的生命周期会受宿主Activity的生命周期影响。

```
####Handler,Thread和HandlerThread的差别
```
实现原理
1.在介绍原理之前，我们先使用普通的Thread来创建一个Handler，创建的过程大致如下：
Handler mHandler;
private void createManualThreadWithHandler() {
  new Thread() {
      @Override
        public void run() {
            super.run();
            Looper.prepare();
            mHandler = new Handler(Looper.myLooper());
            Looper.loop();
        }
    }.start();
}
实现很简单，在目标线程内如下配置
调用Looper.prepare 创建与当前线程绑定的Looper实例
使用上面创建的Looper生成Handler实例
调用Looper.loop()实现消息循环。

2.HandlerThread使用起来很容易，首先需要进行初始化。
private Handler mHandler;
private LightTaskManager() {
    HandlerThread workerThread = new HandlerThread("LightTaskThread");
    workerThread.start();
    mHandler = new Handler(workerThread.getLooper());
}
注意：上面的workerThread.start();必须要执行。


```
####LaunchMode应用场景
```
standard，创建一个新的Activity。
singleTop，栈顶不是该类型的Activity，创建一个新的Activity。否则，onNewIntent。
singleTask，回退栈中没有该类型的Activity，创建Activity，否则，onNewIntent+ClearTop。
注意:
设置了"singleTask"启动模式的Activity，它在启动的时候，会先在系统中查找属性值affinity等于它的属性值taskAffinity的Task存在；
如果存在这样的Task，它就会在这个Task中启动，否则就会在新的任务栈中启动。
因此， 如果我们想要设置了"singleTask"启动模式的Activity在新的任务中启动，就要为它设置一个独立的taskAffinity属性值。
如果设置了"singleTask"启动模式的Activity不是在新的任务中启动时，它会在已有的任务中查看是否已经存在相应的Activity实例，
如果存在，就会把位于这个Activity实例上面的Activity全部结束掉，即最终这个Activity 实例会位于任务的Stack顶端中。
在一个任务栈中只有一个”singleTask”启动模式的Activity存在。他的上面可以有其他的Activity。这点与singleInstance是有区别的。
singleInstance，回退栈中，只有这一个Activity，没有其他Activity。
singleInstance，回退栈中，只有这一个Activity，没有其他Activity。

singleTop适合接收通知启动的内容显示页面。
例如，某个新闻客户端的新闻内容页面，如果收到10个新闻推送，每次都打开一个新闻内容页面是很烦人的。

singleTask适合作为程序入口点。
例如浏览器的主界面。不管从多少个应用启动浏览器，只会启动主界面一次，其余情况都会走onNewIntent，并且会清空主界面上面的其他页面。

singleInstance应用场景：
闹铃的响铃界面。 你以前设置了一个闹铃：上午6点。在上午5点58分，你启动了闹铃设置界面，并按 Home 键回桌面；
在上午5点59分时，你在微信和朋友聊天；在6点时，闹铃响了，并且弹出了一个对话框形式的 Activity(名为 AlarmAlertActivity) 
提示你到6点了(这个 Activity 就是以 SingleInstance 加载模式打开的)，你按返回键，
回到的是微信的聊天界面，这是因为 AlarmAlertActivity 所在的 Task 的栈只有他一个元素，
因此退出之后这个 Task 的栈空了。如果是以 SingleTask 打开 AlarmAlertActivity，
那么当闹铃响了的时候，按返回键应该进入闹铃设置界面。
```
[Android消息处理机制](http://www.jianshu.com/p/02962454adf7)

[Touch事件传递流程](http://www.jianshu.com/p/e99b5e8bd67b)
[Touch事件传递](http://hanhailong.com/2015/09/24/Android-%E4%B8%89%E5%BC%A0%E5%9B%BE%E6%90%9E%E5%AE%9ATouch%E4%BA%8B%E4%BB%B6%E4%BC%A0%E9%80%92%E6%9C%BA%E5%88%B6/)

[View绘制流程](http://www.codekk.com/blogs/detail/54cfab086c4761e5001b253f)

[线程同步1](http://www.itzhai.com/java-based-notebook-thread-synchronization-problem-solving-synchronization-problems-synchronized-block-synchronized-methods.html#read-more)
[线程同步2](https://www.juwends.com/tech/android/android-inter-thread-comm.html)



####单例
```
public class Singleton{
private volatile static Singleton mSingleton;
private Singleton(){
}
public static Singleton getInstance(){
  if(mSingleton == null){\\A
    synchronized(Singleton.class){\\C
     if(mSingleton == null)
      mSingleton = new Singleton();\\B
      }
    }
    return mSingleton;
  }
}
```
####什么情况导致内存泄漏
```
1.资源对象没关闭造成的内存泄漏
2.构造Adapter时，没有使用缓存的convertView
3.Bitmap对象不在使用时调用recycle()释放内存
4.试着使用关于application的context来替代和activity相关的context
5.注册没取消造成的内存泄漏
6.集合中对象没清理造成的内存泄漏
```
####ANR定位和修正
```
如果开发机器上出现问题，我们可以通过查看/data/anr/traces.txt即可，最新的ANR信息在最开始部分。

主线程被IO操作（从4.0之后网络IO不允许在主线程中）阻塞。

主线程中存在耗时的计算

主线程中错误的操作，比如Thread.wait或者Thread.sleep等 Android系统会监控程序的响应状况，一旦出现下面两种情况，则弹出ANR对话框

1.应用在5秒内未响应用户的输入事件（如按键或者触摸）

2.BroadcastReceiver未在10秒内完成相关的处理

3.Service在特定的时间内无法处理完成 20秒

4.使用AsyncTask处理耗时IO操作。

5.使用Thread或者HandlerThread时，调用Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)设置优先级，
否则仍然会降低程序响应，因为默认Thread的优先级和主线程相同。

6.使用Handler处理工作线程结果，而不是使用Thread.wait()或者Thread.sleep()来阻塞主线程。

7.Activity的onCreate和onResume回调中尽量避免耗时的代码

8.BroadcastReceiver中onReceive代码也要尽量减少耗时，建议使用IntentService处理。

```
####Service与Activity之间通信的几种方式
```
1.通过Binder对象
2.通过broadcast(广播)的形式
```
####如何保证service在后台不被Kill
```
一、onStartCommand方法，返回START_STICKY
二、提升service优先级
在AndroidManifest.xml文件中对于intent-filter可以通过android:priority = "1000"这个属性设置最高优先级，
1000是最高值，如果数字越小则优先级越低，同时适用于广播。
三、提升service进程优先级
Android中的进程是托管的，当系统进程空间紧张的时候，会依照优先级自动进行进程的回收。
Android将进程分为6个等级,它们按优先级顺序由高到低依次是:
前台进程( FOREGROUND_APP)
可视进程(VISIBLE_APP )
次要服务进程(SECONDARY_SERVER )
后台进程 (HIDDEN_APP)
内容供应节点(CONTENT_PROVIDER)
空进程(EMPTY_APP)
当service运行在低内存的环境时，将会kill掉一些存在的进程。因此进程的优先级将会很重要，可以使用startForeground 将service放到前台状态。
这样在低内存时被kill的几率会低一些。
四、onDestroy方法里重启service
service +broadcast  方式，就是当service走ondestory的时候，发送一个自定义的广播，当收到广播的时候，重新启动service；
五、Application加上Persistent属性
六、监听系统广播判断Service状态
通过系统的一些广播，比如：手机重启、界面唤醒、应用状态改变等等监听并捕获到，然后判断我们的Service是否还存活，别忘记加权限啊。
```
####Requestlayout,onlayout,onDraw,DrawChild区别与联系
```
requestLayout()方法 ：会导致调用measure()过程 和 layout()过程, 将会根据标志位判断是否需要ondraw

onLayout()方法(如果该View是ViewGroup对象，需要实现该方法，对每个子视图进行布局)

调用onDraw()方法绘制视图本身   (每个View都需要重载该方法，ViewGroup不需要实现该方法)

drawChild()去重新回调每个子视图的draw()方法
```

#### invalidate()和postInvalidate()的区别及使用
```
invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，
调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。 
一个Android 程序默认情况下也只有一个进程，但一个进程下却可以有许多个线程。
在这么多线程当中，把主要是负责控制UI界面的显示、更新和控件交互的线程称为UI线程，
由于onCreate()方法是由UI线程执行的，所以也可以把UI线程理解为主线程。其余的线程可以理解为工作者线程。
invalidate()得在UI线程中被调动，在工作者线程中可以通过Handler来通知UI线程进行界面更新。
而postInvalidate()在工作者线程中被调用
```
#### Android动画框架实现原理
```
Animation框架定义了透明度，旋转，缩放和位移几种常见的动画，而且控制的是整个View，
实现原理是每次绘制视图时View所在的ViewGroup中的drawChild函数获取该View的Animation的Transformation值，然后调用canvas.concat(transformToApply.getMatrix())，通过矩阵运算完成动画帧，如果动画没有完成，
继续调用invalidate()函数，启动下次绘制来驱动动画，动画过程中的帧之间间隙时间是绘制函数所消耗的时间，
可能会导致动画消耗比较多的CPU资源，最重要的是，动画改变的只是显示，并不能相应事件。
```
#### Android为每个应用程序分配的内存大小是多少
```
android程序内存一般限制在16M，也有的是24M
```
#### View刷新机制
```
由ViewRoot对象的performTraversals()方法调用draw()方法发起绘制该View树，值得注意的是每次发起绘图时，
并不会重新绘制每个View树的视图，而只会重新绘制那些“需要重绘”的视图，View类内部变量包含了一个标志位DRAWN，
当该视图需要重绘时，就会为该View添加该标志位。
```
#### LinearLayout和RelativeLayout性能对比
```
RelativeLayout会让子View调用2次onMeasure，LinearLayout 在有weight时，也会调用子View2次onMeasure
RelativeLayout的子View如果高度和RelativeLayout不同，则会引发效率问题，
当子View很复杂时，这个问题会更加严重。如果可以，尽量使用padding代替margin。
在不影响层级深度的情况下,使用LinearLayout和FrameLayout而不是RelativeLayout。
```
[volley解析](http://a.codekk.com/detail/Android/grumoon/Volley%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)

[Glide源码](http://www.lightskystreet.com/2015/10/12/glide_source_analysis/)

[Android设计模式](http://blog.csdn.net/bboyfeiyu/article/details/44563871)


[Android架构设计](https://github.com/JackyAndroid/AndroidInterview-Q-A/blob/master/picture/architucture.png)

[Android架构设计](https://www.tianmaying.com/tutorial/AndroidMVC)

#### 
```
```
#### 
```
```
#### 
```
```
#### 
```
```
#### 
```
```
