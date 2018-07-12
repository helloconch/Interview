
[启动模式](https://blog.csdn.net/yeyuxp/article/details/21952323)

### MVC MVP MVVM
[详解](http://www.iteye.com/news/32931)
```
MVC[1.View被点击->2.将请求转发给Controller->3.controller操作Model->4.Model通知View更新]
M:表示model，专门用来处理业务逻辑
V:表示View,专注页面布局和数据显示。
C:表示Controller，处理View的请求，操作Model


MVP
View只知道Presenter,不知道Model。Model与View进行了分离。
View层提取出接口，Presenter只依赖这个接口。

MVVM
ViewModel，可以与View层绑定，当ViewModel的变化，View立刻就会变化。

```

[oauth认证协议原理](https://blog.csdn.net/mwq384807683/article/details/70313871)

### LruCache原理
[单一职责原则+开闭原则+LruCache实现ImageLoader](https://github.com/helloconch/Interview/tree/master/TIKU/app/src/main/java/com/conch/tiku/imageloader)


[原理详解](https://www.jianshu.com/p/b49a111147ee)
```
LRU(Least Recently Used)
LruCache和DisLruCache，分别用于实现内存缓存和硬盘缓存，其核心思想都是LRU缓存算法。
LruCache内部使用LinkedHashMap进行数据存储,LinkedHashMap是由数组和双向链表的数据结构来实现。
双向链表结构可以实现访问顺序和插入顺序，使得LinkedHashMap<Key,Value>可以按照一定顺序排列起来。
通过构造函数 LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
中的accessOrder区分是访问顺序还是插入顺序。accessOrder：true访问顺序，false为插入顺序。
LruCache中源码：
  public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

put()方法，在添加缓存对象后，调用trimToSize()判断缓存是否已满，如果满了，则删除近期最少使用。
trimToSize()方法不断地删除LinkedHashMap中队尾的元素，即近期最少访问的，直到缓存大小小于最大值。
get()方法获取集合中缓存对象，将会更新队列，保持整个队列按照访问顺序排序。


```

### DiskLruCache解析
[详解](https://blog.csdn.net/guolin_blog/article/details/28863651)

### 图片加载原理
```
图片加载，首先是CPU计算出图像形状，完成之后，将图像交给GPU进行渲染，然后通过Dispay屏幕进行展示。
但是需要注意的一点，当CPU和GPU处理1帧图像时间超过16ms,那么这帧图像需要等到第2个VSync出现，
才能在屏幕进行显示，意味着32ms内所看到的是同1帧图像，产生所谓掉帧，也就是卡帧。

有几个因素使得CPU,GPU处理时间过长？
.过度绘制
2.动画使用次数过多
3.短时间创建对象过多（android垃圾回收机制，当heap被占用的空间达到一个阈值，GC开始回收对象，
GC回收时大部分线程是阻塞的，当GC耗时超过16ms，则出现失帧卡顿的现象）
```
### 模块化实现（好处、原因）
```
模块化是一种将复杂的系统分解为可管理模块的方式。
.模块间解耦(每个模块功能不同、业务逻辑不同、模块间业务解耦)
.提升开发效率(每个模块实际是一个项目，可以单独编译调试)
.多团队并行开发，测试。

模块化业务分层，从下到上
1.基础组件层(底层库和一些封装的libs)
2.业务组件层(支付、即时通信)
3.业务模块层(资讯模块、购物模块)


组件化是以重用化为目的，将一个系统拆分成一个个单独组件。
.避免重复造轮子，节省开发维护成本。
.降低项目复杂性，提升开发效率
.多个团队用同一个组件，在一定程度确保技术方案统一性。

```
### JVM
```
```
### 视频、文件加密传输

[详解](https://github.com/helloconch/Interview/blob/master/TIKU/app/src/main/java/com/conch/tiku/utils/FileEncryptAndDecrypt.java)

### 统计启动时长 标准
```
启动类型
1.冷启动，application没有被创建，需要先创建进程（fork一个新的进程，比较耗时），然后启动MainActivity.
2.热启动，已经启动过application,并驻留在系统内存内，只需要唤醒该进程，启动MainActivity

统计启动时间
1.物理统计：通过高速相机，从点击launcher图标开始，到MainActivity第一个可见帧，算启动时间
2.adb统计： adb shell am start -W package/xxx.MainActivity

启动流程：
1.点击桌面应用图标，launcher进程将启动的目标Activity的请求以Binder方式发送给AMS.
2.AMS收到请求后，以Socket方式请求Zygote进程
3.Zygote收到请求，fork出新进程。
4.在新进程中创建ActivityThread对象，新创建的进程就是应用的主线程，
在主线程里开启Looper消息循环，开始处理创建Activity
5.ActivityThread利用ClassLoader去加载Activity,创建Activity实例，并回调Activity
的onCreate().


```
### ThreadLocal原理
```
ThreadLocal用来提供线程内部的局部变量。这种变量在多线程环境下访问(通过get或set方法访问)
时能保证各个线程里的变量相对独立于其他线程内的变量。

ThreadLocal设计思路：
每个ThreadLocal维护一个ThreadLocalMap映射表，这个映射表key为ThrealLocal本身，value是真正
需要存储的Object.

```
### 强引用（StrongReference）、软引用（SoftReference）、弱引用（WeakReference）、虚引用（PhantomReference）
[详解](https://blog.csdn.net/mazhimazh/article/details/19752475)
### 谈谈classloader
```
jvm有三类classloader,分别为bootstrapclassloader,extendedclassloader以及systemclassloader.
bootstrap classloader是系统在启动jvm时默认加载的。
bootstrap classloader加载后，会载入extended classloader,并将extended classloader的父类
设为bootstrap classloader.
然后bootstrap classloader接着载入system classloader,并将system classloader父类设为extended 
classloader.至此bootstrap--extended--system三级继承结构形成。

类加载器的任务是将class二进制文件加载到方法区，供虚拟机模制出在堆中存放的对象。

classloader加载类的过程为：双亲委托机制
1.检查被加载类是否被加载
2.如果没有被加载，则调用父classloader加载该类。
3.如果1、2不成功，则由自身的类加载。

Dalvik虚拟机类加载器
Android的类加载器主要有2个PathClassLoader和DexClassLoader.
PathClassLoader：是用来加载Android系统类和应用的类。
DEXClassLoader:支持加载APK,DEX和JAR，也可以从SD卡进行加载。
PathClassLoader、DexClassLoader继承于BaseDexClassLoader，BaseDexClassLoader继承ClassLoader
```
### 热修复，插件化
```
共同原理都是使用classloader来实现加载新的功能类，都可以使用PathClassloader与DexClassloader
不同是热修复是为了修复bug,将新的同名类替代同名的bug类。

插件化是增加新的功能或者资源文件，不涉及抢先加载旧的类这样的使用。

插件化比热修复简单，热修复是在插件化的基础上进行替换旧的Bug类。
```
### HashMap源码， SparseArray原理

[详解](https://www.jianshu.com/p/7b9a1b386265)
```
SparseArray采用2个数组，一个数组int[] mKeys存放key值，
因为key值为int，不需要hashcode操作，插入和查找基于二分法。
另一个数组int[] mValues存放值。

在数据量小的时候，一般为1000以下，当key值为int时候，使用SparseArray是一个不错
选择，相比较hashMap内存大概节省30% ,因为key值不需要进行装箱操作，所以性能较优。

```
### Collection的List ArrayList LinkedList
```
List(列表，线性顺序存储，可查找)
ArrayList（数组，中间插入删除慢，查找快）
LinkedList(链表，中间插入删除快，查找慢)

```
### map- HashMap Hashtable LinkedHashMap和TreeMap
```
HashMap：根据key的hashcode来存储数据，遍历时，取得数据随机，由于是线程不安全，
当需要同步时可用Collections.synchronizedMap()使HashMap具有同步能力，或者使用
ConcurrentHashMap，HashMap最多允许一条记录key=null,允许多条记录值为null.

HashTable:继承Dictionary类，线程安全，某一时刻只有一个线程写入，写入较慢，不允许键值为空.

LinkedHashMap：extends Hashmap，但它保存写入顺序，遍历时如果希望和写入顺序相同，可以考虑使用.

TreeMap:间接implements SortMap接口，能够把它保存的记录根据键排序，默认为升序排序，
也可以指定排序比较器，当用Iterator遍历TreeMap时，得到的记录是排过序的。


```

### HashMap、ConcurrentHashMap实现原理
[HashMap详解](http://www.importnew.com/16301.html)
[ConcurrentHashMap详解](https://blog.csdn.net/dingji_ping/article/details/51005799)

```
HashMap底层是一个Entry[]数组结构，数组中的每一项是一个链表。
Entry是由key-value组成。

当需要存储一个对象时，会计算key键的hash值，若不存在相同的hash值，则
存到链表中。若存在相同的hash值，且equals()相等，则将旧值进行覆盖。
若equals不相等，则存储在该链表的下一个节点中。

当获取对象时，会根据计算key键的hash值找到对应链表,然后查找链表中上的对应元素。
 if (e.hash == hash && ((k = e.key) == key || key.equals(k)))  
                return e.value;  


ConcurrentHashMap类创建16个并发的segment,每个segment里面包含多个Hash表，每个
Hash链都是有HashEntry节点组成。
HashTable是线程安全的，是因为将整张表加锁实现同步，一个缺陷是程序效率变得很低。
而ConcurrentHashMap将锁加在每个分段segment，这样我们队segment1操作的时候，
同时也可以对segment2的数据操作，这样效率会高很多。
```

### HashMap如何避免碰撞以及如何扩容
[详解](https://blog.csdn.net/caisini_vc/article/details/52452498)
```
HashMap使用链表来解决碰撞问题，当碰撞发生，对象将会存储在链表的下一个节点中，
HashMap在每一个链表节点存储键值对象。当两个不同的键有相同的hashcode，将被存储
在同一个bucket位置的链表中。

put操作如何覆盖相同的key,进行值替换
当有相同的hashcode值&&(key&&key||key.equals(key))则进行替换


HashMap扩容问题，初始容量的大小为2的4次方（1<<4），最大容量为2的30次方。
当我们自定义容量大小时，构造函数并非直接把我们定义的数值当做HashMap容量大小，
而是将该参数的2的倍数当做数组的容量。
HashMap使用懒加载，构造完Hashmap对象后，只要不进行put方法插入元素之前，hashmap
并不会去初始化或者扩容table.
当添加元素后，发现size大于threshold(阈值)则调用resize方法进行扩容

```

### 性能优化，怎么保证应用启动不卡顿
[详解](https://blog.csdn.net/axi295309066/article/details/72675365)

```
应用在启动过程中，影响启动速度地方：
.Application的OnCreate
.首屏Activity的渲染

1.使用Traceview工具观察启动过程中方法耗时情况，重点关注onCreate()方法
2.分析自定义Application耗时操作，判断onCreate()方法中内容（如第三方工具是否可以不占用主线程进行初始化）
3.查看界面是否过度绘制
4.利用Hierarchy Viewer（层级）工具查看界面优化点
5.启动过程中的白屏优化
```

### 怎样去除重复代码
```
重构的主要任务是去除重复代码，有效的减少重复代码，提高软件的扩展性。
1.为项目定义一个基Activity或Fragment
2.代码的去重复技巧（如提炼方法，抽象基类）
3.用include减少局部布局重复
4.用ViewStub减少整体布局的重复

```
### 多进程共享数据方法
```
SharedPreferences(多进程模式)、广播、Socket、ContentProvider、Messenger、AIDL
Messager是一种轻量级IPC方案，底层实现原理就是AIDL，它对AIDL做了一次封装，它的效率比较低，
一次只能处理一次请求，所以不存在线程同步问题。
```

### SP是进程同步的吗？有什么方法做到同步
[详解](https://www.jianshu.com/p/bdebf741221e)
```
SP不能保证多进程间同步。
为了减少IO造成的性能损失，SP使用缓存机制，会将数据保持到内存中，
在读取的时候直接从内存获取，而写入的时候才保持到文件。如果多个进程都使用了普通的SP，
分别保存就会导致相互覆盖。

设置了MODE_MUTI_PROCESS之后，在多进程使用的时候，会在检测到文件变化时候，重新
加载文件到内存汇总，这样虽然损失一部分性能，却部分实现了多进程间同步。

为什么是部分实现多进程同步？当多个进程同时而又高频的调用commit方法时，就会导致文件
反复覆盖写入，而并没有被及时读取，造成进程间数据不同步。


通过ContentProvider进行实现多进程共享SP

```

### SurfaceView
[详解](https://blog.csdn.net/android_cmos/article/details/68955134)
```
SDK文档描述，SurfaceView就是在窗口里挖了一个洞，它显示在这个洞里，其他的View显示在
窗口上，所以view可以显示在surfaceview之上。surfaceview属于view的子类，是专门为制作
游戏产生，功能强大，最重要的是支付OPENGL ES库，2D,3D效果可以实现。创建SurfaceView时候
需要实现SurfaceHolder.Callback接口，它可以用来监听SurfaceView状态。

SurfaceView相当于另一个绘图线程，它是不会阻碍主线程，并且它在底层机制中实现双缓冲机制。


SurfaceView和View不同之处：
1.SurfaceView允许其它线程更新视图对象，而View不允许这么做，它只允许UI线程更新视图对象。
2.SurfaceView是放在其它最底层的视图层次中，所有其它视图都在它上面，所以在它之上可以添加一些层，
而且它不能是透明的。
3.它执行动画效率比View高，可以控制帧数。
4.SurfaceView在绘图的时使用双缓冲机制，而View没有。

```

### BroadcastReceiver、LocalBroadcastReceiver区别
```
BroadcastReceiver是跨应用广播，利用Binder机制实现。
LocalBroadcastReceiver是应用内广播，利用Handler实现，利用IntentFilter的match功能，
提供信息的发送与接收,效率比较高。

```


### Bundle机制
```
Bundle实现Parcelable接口，支持进程间通讯，保存特定数据。
```


### Handler机制
```
1.Handler通过sendMessage()发送消息message到消息队列MessageQueue.
2.Looper通过loop()不断提取触发条件message,并将message交给对应的target handler处理
3.target handler调用自身handleMessage()方法来处理Message

Message Queue（消息队列)
定义：采用单链表的数据结构来存储消息列表
作用：用来存放通过Handler发过来的Message，按照先进先出执行

Looper（循环器）
static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
作用： 
消息循环：循环取出Message Queue的Message 
消息派发：将取出的Message交付给相应的Handler

1.在子线程中通过Looper.prepare()生成一个Looper存放在对应的sThreadLocal中，并且生成了mQueue
2..构建Handler时通过构造函数调用Looper.myLooper()找到mLooper
2.通过mLooper找到对应的mQueue
3.Lopper.loop()进行消息处理

```


### Android事件传递机制
[详解](https://blog.csdn.net/carson_ho/article/details/54136311)
```
public boolean dispatchTouchEvent(MotionEvent ev){
  boolean consume = false;
  if(onInterceptTouchEvent(ev)){
      consume=onTouchEvent(ev);
 }else{
      consume=child.dispatchTouchEvent(ev);
  }
  return consume;
}
对于一个根ViewGroup来说，点击事件产生后，首先会调用它，这时它的dispatchTouchEvent就会调用，
如果这个ViewGroup的onInterceptTouchEvent方法返回true就表示他要拦截当前事件，
接着这个ViewGroup的onTouchEvent方法就会调用
（这里默认它没设置OnTouchListener，具体为什么，这里先不讲），
如果onInterceptTouchEvent返回false，就表示它不拦截当前事件，
这时事件就会继续传递给它的子元素，接着子元素的dispatchTouchEvent方法就会被调用，
然后继续上述过程，直到事件被处理。


```


### java synchronized对象锁和类锁的区别
[详解](https://blog.csdn.net/zhujiangtaotaise/article/details/55509939)
```
synchronized加到static方法前面是给class加锁，即类锁。
1.对象锁和类锁是不同的锁，所以多个线程执行2个不同的锁的方法是，是异步操作。
2.类锁对该类的所有对象都能起作用，而对象锁不能。

1.普通同步方法，锁是当前实例对象
2.静态同步方法，锁是当前类的class对象
3.同步方法块，锁是括号里面的对象

当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。


```


### 动态加载、静态类加载
```
编译时加载是静态加载类。
运行时加载为动态加载类。
在日常中我们希望用到哪个就加载哪个，不用不加载，就需要动态加载类。
使用动态加载类时，我们不用定义100种功能，只需要通过实现某种标准（实现某个接口）。
public class Main{  
    public static void main(String args[]){  
        try{  
              
            Class c=Class.forName(args[0]);  
          
            All a=(All)c.newInstance();  
            a.start();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}  
class A implements All{  
    public void start(){  
        System.out.println("A....START");  
    }  
}  
class B implements All{  
    public void start(){  
        System.out.println("B....START");  
    }  
}  
//接口  
interface All{  
    public void start();  
}

```


### Android的大体架构图
```
Android系统架构图，从上往下
1.应用程序层
2.应用程序框架层
3.系统库和Android运行时
4.Linux内核
```

### Android Studio的build按钮后发生什么
```
build过程将资源和代码文件打包成apk
1.通过appt工具，将资源文件生成R.java文件，将aidl文件转化为对应java文件
2.编译java文件，生成.class文件
3.将.class文件转化为Android虚拟机支持的.dex文件
4.通过apkbuilder将dex文件和编译后的资源文件生成apk文件
5.对apk进行签名和对齐
```

### 大体说一下应用程序安装到手机发生了什么
```
1.复制apk到/data/app目录下，解压并扫描安装包
2.资源管理器解析apk里的资源文件
3.解析AndroidManifest清单文件，并在data/data/目录下创建对应的应用数据目录
4.对dex文件进行优化，并保持在dalvik-cache目录下
5.将AndroidManifest文件解析出的四大组件信息注册到PackManagerService中
6.安装完成后，发送广播

```

### JVM/Dalvik/ART 虚拟机有基本的了解
```
JVM运行在操作系统上，是一种基于栈结构，java编译后的字节码在JVM上运行，
jvm屏蔽底层实现，由它把字节码翻译成机器指令。

DVM:Dalvik Virtual Machine安卓虚拟机，基于寄存器结构，每一个应用都对应一个Dalvik虚拟机实例。
DVM通过解释DEX文件来执行这些字节码。

ART:安装应用时，dex中的字节码将被编译为本地机器码，之后每次打开应用，执行都是本地机器码，
移除运行时解释执行，效率更高，启动更快。
```
### Android上的IPC跨进程通信如何工作
```
Android各种IPC方式，使用文件共享、使用Messenger、使用AIDL、使用ContentProvider、使用Socket、Binder
```

### APP是如何沙箱化，为什么要这么做
```
Android“沙箱”的本质是为了实现不同应用程序和进程之间的互相隔离，
即在默认情况 下，应用程序没有权限访问系统资源或其它应用程序的资源。
每个APP和系统进程都被分配唯一并且固定的User Id，这个uid与内核层进程的uid对应。
每个APP在各自独立的Dalvik虚拟机中运行，拥有独立的地址空间和资源。
运行于Dalvik虚拟机中的 进程必须依托内核层Linux进程而存在，
因此Android使用Dalvik虚拟机和Linux的文件访问控制来实现沙箱机制，
任何应用程序如果想要访 问系统资源或者其它应用程序的资源必须在自己的manifest文件中进行声明权限或者共享uid.

```
### recycleView 、listview区别，性能
```
从布局层面来讲：recycleview支持线性布局、网格布局、瀑布流布局，而且还支持横向还是纵向滚动。
从API角度：recycleview提供大量API供开发者使用如：布局管理器LayoutManager,局部刷新notifyItemChanged
动画效果等。

```
### 快速排序的实现
```
快速排序相比冒泡排序，每次交换是跳跃式的。每次排序的时候，设置一个基准点，
将小于等于基准点的数全部放到基准点的左边，将大于等于基准点的数全部放到基准点的右边，
每次交换的时候就不会像冒泡排序一样每次只能在相邻的数之间进行交换，交换的距离就大的多了，
因此总的比较和交换次数就少，速度自然就提高。其实快速排序基于一种“二分”思想。
```
### synchronized与Lock的区别 
[详解](https://blog.csdn.net/u012403290/article/details/64910926?locationNum=11&fps=1)
```
线程5种状态
1.新建状态：新建线程对象，并没有调用start()方法之前
2.就绪状态:调用start()方法线程就进入就绪状态，但不一定运行，线程
睡眠和挂起中恢复的时候也会进入到就绪状态。
3.运行状态：线程被设置为当前线程，开始执行run()方法。
4.阻塞状态：线程被暂停，调用sleep()方法后线程进入阻塞状态
5.死亡状态：线程执行结束。


二者区别：
1.synchronzied属于java关键字，在JVM层面，而Lock是一个类
2.锁的释放前者在获取锁的线程执行完同步代码，就会释放锁，
而Lock在finally中必须释放锁，不然容易造成线程死锁。
3.锁的状态，synchronzied无法判断，而Lock是可以判断的
4.性能上synchronzied可少量同步，后者Lock大量同步

```

### TCP、UDP区别
[详解](https://blog.csdn.net/qzcsu/article/details/72861891)
### TCP、UDP区别
```
TCP连接需要经过3次握手（最开始客户端和服务器都是处于CLOSED状态，
主动打开连接的是客服端，被动打开的是服务器）
1.客户端发送请求报文(SYN=1,初始序列号seq=x)到服务器，并进入SYN_SEND状态，等待服务器确认。
2.服务器收到请求报文，同意连接，则发出确认报文(ACK=1,SYN=1,确认号ack=x+1,初始序列号seq=y),
TCP服务器进入SYN_RCND状态。
3.客户端收到服务器确认报文后，还要向服务器发送确认报文(ACK=1,ack=y+1,序列号seq=x+1)，
此时TCP连接建立，客户端进入ESTABLISHED状态，服务器收到客户端确认后也进入ESTABLISHED。

为什么TCP客户端最后还要发送一次确认呢？
主要防止已经失效的连接请求报文突然又传送到了服务器，从而产生错误。

断开过程需要经过4次握手（客户端主动关闭，服务端被动关闭）
1.客户端发出连续释放报文，并停止发送数据，释放数据报文(FIN=1,序列号seq=u),
此时客户端进入FIN-WAIT-1(终止等待1)状态。
2.服务器收到连续释放报文，发出确认报文(ACK=1，ack=u=1,序列号seq=v),
此时服务端进入CLOSE-WAIT(关闭等待)状态。
3.客户端收到服务器确认请求后，客户端进入FIN-WAIT-2，等待服务器发送连接释放报文。
4.服务器将最后数据发送完毕后，向客户端发送连接释放报文(FIN=1,ack=u+1),
服务器进入LAST-ACK（最后确认）状态，等待客户端确认。
5.客户端收到服务器连接释放报文后，必须发出发出确认(ACK=1,ack=w+1,seq=u+1),
此时客户端进入TIME-WAIT（时间等待），服务器收到客户端发出的确认，立即进入CLOSED状态，
结束这次TCP连接。

区别:
1.基于连接与无连接
2.TCP要求系统资源多，UDP较少
3.UDP程序结构较简单
4.流模式(TCP)与数据报模式(UDP)
5.TCP保证数据正确性，UDP可能丢包
6.TCP保证数据顺序，UDP不保证

UDP应用场景
1.面向数据报方式
2.网络数据大多为短消息
3.拥有大量Client
4.对数据安全性无特殊要求
5.网络负担非常重，但对响应速度要求高




```

### Http与Socket区别
[详解](https://blog.csdn.net/fulai00/article/details/46491749)
```
Http在每次请求结束后都会主动释放连接，因此HTTP连接是一种"短链接"，要保持客户端
的在线状态，需要不断地向服务器发起连接请求。


Socket：套接字是通信的基石，是支持TCP/IP协议的网络通信的基本操作单元。
创建Socket连接时，可以指定使用的传输层协议，Socket可以支持不同的传输层协议(TCP/UDP)
当使用TCP协议进行连接时，该Socket连接就是一个TCP连接。
```
### volatile
```
volatile让变量每次在使用的时候，都从主内存存取。而不是从各个线程的“工作内存”；
volatile具有synchronized关键字的"可见性"，但是没有synchronized关键字"并发正确性"，
并不保证线程执行的有序性。
volatile变量对于每次使用，线程都能得到当前volatile变量的最新值。但volatile并不能保证
并发的正确性。
```
### java线程池
```
使用场景：单个任务处理时间短，且需要处理的任务数量大，
频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。
通过线程池解决这个问题。

好处：
重用存在的线程，减少对象创建，销毁的开销，提升性能。
可有效控制最大并发线程数，提高系统资源使用率。
提供定时执行，定期执行，单线程，并发数控制等功能。

Executors.newCachedThreadPool();
首先会按照需要创建足够多的线程来执行任务，处理一定数量的任务线程数目不确定。
Executors.newFixedThreadPool(1);
创建一个指定工作线程数量的线程池，如果工作线程数达到线程池的最大数，则任务存入到池队列中。
Executors.newScheduledThreadPool(2);
创建一个线程池，它可安排在给定延迟后运行命令或定期执行命令。
Executors.newSingleThreadExecutor();
创建一个使用worker线程的Executor,可保证顺序执行各个任务，
并且在任意给定的时间不会有多个线程是活动的。


```
### java中对象和类的生命周期
[详解](https://blog.csdn.net/huangzhen0914/article/details/45172597)
```
对象的生命周期大致分7个阶段
1.创建阶段
2.应用阶段
3.不可视阶段
4.不可到达阶段
5.可收集阶段
6.终结阶段
7.释放阶段
对象生命周期最后一个阶段是可收集阶段，终结阶段与释放阶段。
当对象处于这个阶段时候，可能处于下面3种情况：
a)垃圾回收器发现该对象已经不可达
b)finalize方法已经被执行
c)对象空间已被重用


类的生命周期

加载-连接-初始化-使用-卸载

```
### 类的加载机制
[详解](https://www.cnblogs.com/ityouknow/p/5603287.html)
```
类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区
的方法区内，然后在堆区创建一个java.class.Class对象，用来封装方法区的数据结构。
```
### MVP模式/MVC模式
```
MVP:Model/View/Presenter
MVC:Model/View/Controller

两者最明显区别：MVC是允许Model和View进行交互。
MVP中，Model与View之间交互由Presenter完成,还有一点
Presenter与View之间交互通过接口。
```
### RXJava
```
```
### 抽象类和接口区别
```
1.前者可以有默认的方法实现，接口不存在方法实现。
2.前者子类通过extends方式继承抽象类，后者通过implements方式实现接口
3.抽象类可以有构造器，接口不能
4.抽象类不能被实例化，接口也是不能
5.抽象类可以继承一个类实现多个接口，接口只能继承一个或多个接口
6.抽象类比接口速度快，接口稍微慢，它需要时间去寻找类中实现的方法。
```
### 集合Set实现Hash 怎么防止碰撞
```
HashSet集合特点是元素无序和不重复。
HashSet底层数据结构为哈希表，哈希表存储一系列哈希值的表，而哈希值是由对象的hashCode()方法生成。
确保元素唯一性两个方法，hashCode()和equals()方法。
当调用add()方法向集合存入对象，先比较此对象与原有对象的哈希值有没有一样，如果都不一样直接存入。
若存在相同的哈希值，则继续比较2个对象是否为同一个对象，此时调用对象的equals()方法。
总之，只有hashCode值相等，才会调用equals()方法。
```
### JVM内存区域，开线程影响那块内存
```
每当有线程被创建的时候，JVM就需要为其在内存中分配虚拟机栈和本地方法栈来记录
调用方法的内容，分配程序计数器记录指令执行的位置。
```
### 二叉树 深度遍历与广度遍历
[详情](https://blog.csdn.net/dyy_gusi/article/details/46414677)
```
     A
  B       C
D   E    F  G

对于二叉树，深度优先搜索是沿着树的深度遍历树的节点，尽可能深的搜索树的节点。

深度遍历顺序ABDECFG.深度优先搜索二叉树是先访问根节点，然后遍历左子树接着遍历右子树。我们可以利用
堆栈的先进后出特点，将右子树压栈，再将左子树压栈，这样左子树位于栈顶。

广度遍历ABCDEFG，广度优先，是从根节点开始沿着树的宽度搜索遍历。
广度优先遍历需要使用队列(Queue)这种数据结构，queue特点是先进先出。

```

### B树  B+树
[详解](https://blog.csdn.net/zhuanzhe117/article/details/78039692)
```
```

### 进程调度
[详解](https://www.jianshu.com/p/6e1fa5cf62cb)
```
当处于就绪状态的用户进程数多于CPU数时，就会产生多个进程或者线程同时竞争CPU的结果。
```

### 进程与线程
```
1.同一个进程可以包含多个线程，并且线程可以共享进程资源。
2.进程结束后所有的线程都将销毁，而线程结束不影响进程中其它线程。
3.进程是资源分配和调度的一个独立单元，而线程是CPU调度基本单元。
4.线程是轻量级，创建和销毁所需要时间比进程小
5.线程执行一般都是要进行同步或互斥，因为它们共享同一进程的所有资源。
```

### JVM内存模型
[详解](https://blog.csdn.net/zjf280441589/article/details/53437703)
```
```

### 并发集合有哪些
```
 ConcurrentHashMap、CopyOnWriteArrayList、CopyOnWriteArraySet
```

### 死锁
```
产生原因：相互等待资源而产生的一种僵持状态，如果没有外力干预将一直持续。
死锁必要条件：系统资源不足，相互竞争资源，请求资源顺序不当。互斥、不可抢占、循环等待、请求与保持
解决方法：破坏请求与保持、不可抢占、循环等待、请求资源使用完毕及时释放资源。
```

### CAS介绍
[详解](https://www.jianshu.com/p/fb6e91b013cc)
```
CAS:Compare and Swap 即比较及替换，实现并发算法的常用到的技术。
CAS的思想：三个参数，一个当前内存值V、旧的预期值A,即将更新的值B,
只有预期值A和内存值V相同时，将内存值修改为B并返回true,否则什么都不做，并返回false.

```

### 开启线程的三种方式，run()和start()方法区别
```
1. extends Thread

2.implements Runnable

3.通过Callable和Future创建线程

对比：
实现Runnable/Callable优势线程类实现Runnable或Callable接口，还可以继承其它类，
这种方式，多个线程可以共享一个target对象，将CPU/代码/数据分开，形成清晰模型。

劣势：
编程稍微复杂，要访问当前线程，必须使用Thread.currentThread()方法

继承Thread优势，编写简单，直接使用this可获得当前线程。
劣势无法在继承其它父类。
```
### 常用数据结构简介
```
Collection（所有集合类的接口）
    List
        LinkedList 链表，随机位置插入，删除数据比线性表快，遍历比线性表慢
        Vector    线性表  同步， 插入，删除较慢
        ArrayList 线性表，非同步，  插入，删除较慢
    Set：保证集合中元素唯一
        TreeSet：内部维护一个TreeMap
        HashSet:内部维护一个HashMap
Map
  TreeMap：是由Entry对象为节点组成的一颗红黑树，put到TreeMap的数据默认按key的自然顺序排序，
  
  HashMap：将put进来的key-value封装成一个Entry对象存储到Entry数组中，
           位置由key的哈希值与数组长度计算而来。如果数据当前下标已有值，
           则将数组当前下标的值指向新添加的Entry对象。
     LinkedHashMap
```

### 如何判断单链表是否存在环
[详解](https://blog.csdn.net/njr465167967/article/details/52634352)
```

public static boolean hasLoop(LinkedLoop head) {
        //定义一个引用指向头结点
        LinkedLoop p1 = head;
        //定义另一个引用指向结点的下一个结点
        LinkedLoop p2 = head.next;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p2 == null)
                return false;
            //为了防止p2.value出现空指针异常，需要对p2进行判断
            int val1 = p1.value;
            int val2 = p2.value;
            if (val1 == val2)
                return true;
        }

        return false;
    }


    public static class LinkedLoop {
        public int value;
        public LinkedLoop next;

        public LinkedLoop(int v) {
            this.value = v;
        }
    }

```

### 排序 堆积排序实现
```
堆积排序特性：堆积是一种特殊的二叉树，可分为最大堆积树和最小堆积树
最大堆积树：
1.它是一个完全二叉树
2.所有节点的值都大于或等于它左右子节点的值。
3.树根是堆积树种最大的
最小堆积树：
1.它是一个完全二叉树
2.所有节点值小于或等于它左右子节点的值。
3.树根是堆积树最小的。
```
### 单向链表
```
class Node {
		int data;
		int np;
		String name;
		Node next;

		public Node(int data, int np, String name) {
			this.data = data;
			this.np = np;
			this.name = name;
			this.next = null;
		}
	}

	class LinkData {
		private Node first;
		private Node last;

		public void print() {
			Node current = first;
			while (current != null) {
				System.out.println(current.data + "==" + current.name);
				current = current.next;
			}
		}

		public void insert(int data, int np, String name) {
			Node node = new Node(data, np, name);
			if (first == null) {
				first = node;
				last = node;
				System.out.println();
			} else {
				//将2个节点串起来
				last.next = node;
				last = node;
				System.out.println();
			}
		}
	}
```

### 单向链表反转
```
class RevertLinkData extends LinkData{
		public void revertPrint() {
			//使用三个指针，解决链表反转问题
			Node current=first;
			Node before=null;
			while(current!=null) {
				last=before;
				before=current;
				current=current.next;
				before.next=last;
			}
			current=before;
			while(current!=null) {
				System.out.println("链表反转=="+current.data + "==" + current.name);
				current = current.next;
			}
		}
	}
```

### 字符串反转
```
public static String reversel1(String str){  
    return new StringBuffer(str).reverse().toString();  
} 

public static String reverse3(String s)  
{  
    int length = s.length();  
    String reverse = "";  //注意这是空串，不是null  
  
    for (int i = 0; i < length; i++){  
        //在字符串前面连接，  而非常见的后面  
        reverse = s.charAt(i) + reverse;
        //即String="reverse"，  
        // 放入时会"e";"s";"r";"e";"v";"e";"r";的放入String。charAt（i++）  
        //从尾到头正序放入  
    }  
    return reverse;  
}  


```
### 输出该链表中倒数第几个结点
[详解](https://www.cnblogs.com/mthoutai/p/6872784.html)
```
public ListNode FindKthToTail(ListNode head,int k){
		if(head == null || k <= 0){
			return null;
		}
		ListNode ANode = head;
		ListNode BNode = null;
		for(int i = 0;i<k-1;i++){
			if(ANode.next != null)
				ANode = ANode.next;
			else
				return null;
		}
		BNode = head;
		while(ANode.next != null){
			ANode = ANode.next;
			BNode = BNode.next;
		}
		return BNode;
	}
	public static void main(String[] args){
		ListNode head = new ListNode();
		ListNode second = new ListNode();
		ListNode third = new ListNode();
		ListNode forth = new ListNode();
		head.next = second;
		second.next = third;
		third.next = forth;
		head.data = 1;
		second.data = 2;
		third.data = 3;
		forth.data = 4;
		E15KthNodeFromEnd test = new E15KthNodeFromEnd();
		ListNode result = test.FindKthToTail(head, -1);
		System.out.println(result);
	}
```

### 二叉树(最多只能有2个节点，当n=2时，它的链接浪费率最低，所以用二叉树取代树状结构)
```
class TreeNode {
		int value;
		TreeNode leftNode;
		TreeNode rightNode;

		public TreeNode(int value) {
			this.value = value;
			this.leftNode = null;
			this.rightNode = null;
		}
	}

	class BinaryTree {
		// 二叉树根节点
		TreeNode rootNode;

		public BinaryTree(int[] data) {
			for (int i = 0; i < data.length; i++) {
				add(data[i]);
			}
		}

		void add(int value) {
			TreeNode currentNode = rootNode;
			// 建立树根
			if (rootNode == null) {
				rootNode = new TreeNode(value);
				return;
			}
			// 建立二叉树
			while (true) {
				// 在左树
				if (value < currentNode.value) {
					if (currentNode.leftNode == null) {
						currentNode.leftNode = new TreeNode(value);
						return;
					} else {
						currentNode = currentNode.leftNode;
					}
				}
				// 在右树
				else {
					if (currentNode.rightNode == null) {
						currentNode.rightNode = new TreeNode(value);
						return;
					} else {
						currentNode = currentNode.rightNode;
					}
				}
			}
		}
	}

```

### Math.ceil、Math.floor、Math.round
```
 /** 
         *Math.sqrt()//计算平方根
         *Math.cbrt()//计算立方根
         *Math.pow(a, b)//计算a的b次方
         *Math.max( , );//计算最大值
         *Math.min( , );//计算最小值
         */ 

static double ceil(double a)
返回值为double类型，返回一个大于或等于参数a的最小整数。即它返回一个整数，这个整数是所有大于等于a的整数中最小的一个。

static double floor(double a)
返回值为double类型，返回一个小于或等于参数a的最大整数。即它返回一个整数，这个整数是所有小于等于a的整数中最大的一个。

round方法：
返回最接近参数a的整数，该方法等同于Math.floor(a + 0.5)并将结果转换为long或int类型，即四舍五入取整。
//12
System.out.println(Math.round(11.5));
//-11
System.out.println(Math.round(-11.5));

```

### 红黑树
[详解](https://blog.csdn.net/u010853261/article/details/54312932)
```
红黑树，本质上市一颗二叉查找树，它在二叉查找树的基础上增加着色和相关的性质，
使得红黑树相对平衡，从而保证红黑树的查找、插入、删除的时间复杂度最坏为O(log n)

红黑树5条特性：
1.每个节点要么是红的，要么是黑的。
2.根节点是黑的。
3.每个叶节点（叶节点即指树尾端NIL指针或NULL节点）是黑的
4.如果一个节点是红的，那么它的两个儿子是黑的。
5.对于任一节点而言，其到叶节点树尾端NIL指针的每一条路径都包含相同数目的黑节点。

```
### KMP
[详解](http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html)
```
```

### 算法时间复杂度的表示法O(n²)、O(n)、O(1)、O(nlogn)
```
时间复杂度：当数据量相当大时，排序算法所花费的时间显得相当重要。
排序算法的时间复杂度可分为最好情况(best case)、最坏情况(worth case)、平衡情况(Average case)

空间复杂度：算法在执行过程中所需要付出的额外内存空间。
排序法所用到的额外空间越少，它的空间复杂度就越佳。
简单理解：就是变量为n的时候，算法需要对变量操作次数的量级。

冒泡排序:最坏情况O(n^2) 最好情况O(n)

插入排序：最坏情况O(n^2) 最好情况O(n)

选择排序：无论最坏，最佳还是平均，时间复杂度O(n^2)
比如从小到大排列，则先找到第一个，然后，查找第2到第n中最小的，若有最小的，则互换。
然后查找第2个，与第3到第n中进行查找，找到最小的，则与位置2进行互换，以此类推。

快速排序：在最快的情况下时间复杂对为O(nlog2n),最坏情况O(n^2)

```

### 快速排序
```
public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
         arr[low] = arr[i];
         arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    public static void main(String[] args){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

```


### 冒泡排序
```
private static void sort(int[] datas) {
		int start = datas.length - 1;
		for (int i = start; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				// > 升序排列
				if (datas[j] > datas[j + 1]) {
					int temp = datas[j + 1];
					datas[j + 1] = datas[j];
					datas[j] = temp;
				}
			}
		}
	}
```

### 选择排序
```
private static void sort3(int[] datas) {
		int start = datas.length - 1;
		for (int i = 0; i < start; i++) {
			for (int j = i + 1; j < start + 1; j++) {
				//升序
				if (datas[i] > datas[j]) {
					int temp = datas[j];
					datas[j] = datas[i];
					datas[i] = temp;
				}
			}
		}
	}
```


### 动态权限适配方案
[详解](https://blog.csdn.net/xc765926174/article/details/49103483)
```
```

### 网络请求缓存处理，okhttp如何处理网络缓存的
[详解](https://www.jianshu.com/p/d04a9508640d)
[详解](http://chuansong.me/n/1185791251527)
```
okhttp如何用拦截器添加Cache-Control消息头进行缓存定制。
但会出现一个缺点，拦截器定义缓存的方法导致不同的请求缓存时间是一样的，这样不合理。
应该是每一个Request有它的缓存时间。


okhttp官方文档建议使用CacheControl这个类进行缓存策略定制。
它内部有2个重要的静态实例。
FORCE_NETWORK 常量 用来强制使用网络请求。FORCE_CACHE 只取本地的缓存。

Request request=new Request.Builder().url("http://xxxx").cacheControl(Cache.FORCE_NETWORK).build().
```


### 图片加载相关，bitmap如何处理大图，如一张30M大图，如何预防OOM
[详解](https://www.jianshu.com/p/b0e2be9e0f8c)
```
1.通过BitmapFactory.Options 

2.巨图分段加载 BitmapRegionDecoder
```


### 进程保活
[详解](https://blog.csdn.net/u013263323/article/details/56285475)
```
```


### Service与IntentServcie
```
IntentService是继承于Service并处理异步请求一个类。
在IntentServcie内有一个工作线程来处理耗时操作，启动IntentService方式和启动传统
Service一样，同时，当任务执行完后，IntentService会自动停止，而不需要手动控制。
可以启动IntentService多次，而每一个耗时操作都会以工作队列的方式在IntentService的onHandlerIntent()
回调方法中执行，每次只执行一个工作线程，以此类推。
```

### 多线程断点续传原理
[详解](https://blog.csdn.net/crazy__chen/article/details/41947577)
[详解](https://blog.csdn.net/seu_calvin/article/details/53749776)
```
在文件下载过程中使用数据库实时记录文件已下载的位置。
当进行续传时，可以通过http的Get请求中的setRequestProperty()方法
setRequestProperty("Range","bytes=" + start + "-" + info.getLength()); 
告诉服务器，数据的开始和结束位置。
同时在本地文件写入时，RandomAccessFile的seek()支持在文件的任意位置进行写操作。

```

### EventBus实现原理
[详解](https://www.jianshu.com/p/7271b8e8c33e)
```
一个静态的单例模式，成员变量是一个List容器，注册时用来存放观察者，发送时，用来通知观察者。
```

### java7、java8新特性
```
java7: 
switch支持字符串条件、
泛型支持实例化类型自动推断 List<String> ll=new ArrayList<>();
新增一些取环境信息的工具方法

java8:
支持lambda表达式
接口可以非抽象的方法，这个特征为扩展方法，需要使用default关键字
函数式接口
```


### http
```
http响应头是服务端根据客户端的请求发出的一串信息，可能包含内容类型，长度，编码字符集，日期时间，过期时间，修改时间，
服务器型号，是否进行缓存等。
1.建立tcp连接，向服务器发送请求命令如：GET /smaple/hello.jsp HTTP/1.1
2.向服务器发送请求命令后，还要以头信息的形式向web服务器发送一些别的信息，之后
发送一空白行来通知服务器，已经结束该头信息发送
3.服务器向客户端做应答，HTTP 1.1 200 OK 协议版本号和状态码
4.服务器发送应答头信息，服务器也会发送自己头的数据和请求文档
5.服务器向客户端发送头信息后，发送一个空白行来标示头信息发送结束，接着
以Content-Type应答头信息所描述的格式发送用户请求的实际数据。

```


### 单例 枚举
[详解](https://jingyan.baidu.com/article/f79b7cb309c11c9145023e7a.html)
```
懒汉模式
public class Singleton{
private static volatile Singleton instanc=null;
private Singleton(){}
public static Singleton getInstance(){
//双重锁
if(instance==null){
synchronzied(Singleton.class){
if(instance==null){
instance=new Singleton();
}
}
}
return instance;
}
}

饿汉模式:

public class Singleton{
private static Singleton s1=new Singleton();
public static Singleton getInstance(){
  return s1;
}
}

枚举确实使得代码更加易读，但在编写高效Android代码时避免使用枚举，它
会减缓执行速度并增加文件体积，这要是减少OOM的一方面。
```

### Android用到的设计模式
[详解](https://www.cnblogs.com/android-blogs/p/5530239.html)
```
单例模式、Builder模式、观察者模式、简单工厂、动态代理。
```
### Android主流设计架构
```
MVC、MVP、MVVM
```

### Integer ==
```
Integer是int的封装对象，==比较的是栈中的值
Integer a=new Intger(1);
Intger b=new Intger(1);
a与b存的是Integer的堆中的地址，而不是值。
a/b指向堆中地址显然不同a==b false

int c=1 int为值类型，引用类型Integer与值类型int比较显然比较的是值 a==c true


		Integer a1=new Integer(100);
		Integer a2=new Integer(100);
		int a3=100;
		//false
		System.out.println(a1==a2);
		//true
		System.out.println(a1.equals(a2));
		//true
		System.out.println(a1==a3);
		//true
		System.out.println(a1.equals(a3));
		
		String s1="abc";
		String s2=new String("abc");
		String s3=new StringBuilder("abc").toString();
		String s4="abc";
		//false
		System.out.println(s1==s2);
		//true
		System.out.println(s1.equals(s2));
		//false
		System.out.println(s1==s3);
		//true
		System.out.println(s1.equals(s3));
		//true
		System.out.println(s1==s4);
		//true
		System.out.println(s1.equals(s4));


```

### Android实现长按事件
[详解](https://blog.csdn.net/qq_28269905/article/details/71480672)
```
在定义View的 dispatchTouchEvent方法中，去判断Action_DOWN事件类型，发起一个postDelay()延迟请求。

在Action_DOWN/Action_UP中通过removeCallbacks()移除

```

### handler发送消息给子线程,looper怎么启动
```
1.handler在子线程创建，但需要建立消息循环的步骤Looper.prepare()/Looper.loop()
```


### 常用的图片加载库
[详解](https://www.jianshu.com/p/97994c9693f9)
[fresco使用详解](https://blog.csdn.net/rongbinjava/article/details/51836835)
```
ImageLoader:已停止维护
Picasso：同样Square的，Square公司其他开源库和Retrofit或者OkHttp
和Picasso搭配使用兼容性会更好些，占用体积100k左右
Glide:Glide是在Picasso基础之上进行2次开发，大于500k左右，有更多的表现
形式，支持Gif、WebP、缩略图,甚至Video
Fresco: Fresco将图片放到一个特别的内存区域叫Ashmem区（Native堆），图片将
不占用App的内存，这里属于C++地盘。

```
### Android各版本对应的SDK和JDK版本
```
平台版本	   SDK版    版本名称            JDK版本
Android 8.0	26	Oreo                -
Android 7.1	25	Nougat              -
Android 7.0	24	Nougat              -
Android 6.0	23	Marshmallow         -
Android 5.1	22	Lollipop            7
Android 5.0	21	Lollipop            7
Android 4.4	19	KITKAT              6
```
### 常见的数据格式
```
xml/json/protocol buffer
```
### json
```
fastJson/GSON/Jackson
```
### xml解析
```
DOM: 是一种用于XML文档的对象模型，可用于直接访问 XML 文档的各个部分。
它是一次性全部将内容加载在内存中，生成一个树状结构,它没有涉及回调和复杂的状态管理。
缺点是加载大文档时效率低下。

SAX: 使用流式处理的方式，它并不记录所读内容的相关信息。
它是一种以事件为驱动的XML API，解析速度快，占用内存少。使用回调函数来实现。

PULL:内置于 Android 系统中。也是官方解析布局文件所使用的方式。
Pull 与 SAX 有点类似，都提供了类似的事件，如开始元素和结束元素。
不同的是，SAX 的事件驱动是回调相应方法，需要提供回调的方法，而后在 SAX 内部自动调用相应的方法。
而Pull解析器并没有强制要求提供触发的方法。因为他触发的事件不是一个方法，而是一个数字。它使用方便，效率高。


解析xml中部分内容：Xpath/dom4j
```
### java 8 种数据类型 字节大小，int 4个字节，boolean几个字节
```
类型	大小
byte	1个字节
char	2个字节
short	2个字节
int	4个字节
float	4个字节
long	8个字节
double	8个字节
boolean JVM规范中，boolean变量作为int处理，也就是4字节；boolean数组当做byte数组处理是1个字节。
```

### &、&&、|、||、^
```
&&（短路与）：操作如果第一个条件不满足 那么后面的条件就不用再判断了
&（与）： 要求所有条件都判断一遍
||(短路或)：如果第一个条件为true 则后面的条件则不再判断
|（或）：所有条件都要求判断

^:异或运算符(把数据转换二进制，然后按位进行运算)
运算规则：0^0 = 0， 1^0 = 1，  0^1 = 1，  1^1 = 0，运算对象相同为0，不同为1.

<<: 左移运算符，num << 1,相当于num*2

>>:右移运算符,num >> 1,相当于 num/2

>>>:无符号右移，忽略符号位，空位都以0补齐。
```

### String、StringBuffer和StringBuilder的区别
```
String 类型和StringBuffer的主要性能区别：String是不可变的对象, 因此在每次对String 类型进行改变的时候，
都会生成一个新的 String 对象，然后将指针指向新的 String 对象，所以经常改变内容的字符串最好不要用 String ，
因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，性能就会降低。

使用 StringBuffer 类时，每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象并改变对象引用。
所以多数情况下推荐使用 StringBuffer ，特别是字符串对象经常改变的情况下。


```
### Message.obtain()和Handler.obtainMessage()
```
Handler.obtainMessage()内部是Message.obtain()去实现。
obtain()方法是从global pool中返回一个message.避免重新分配新对象。
public static Message obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                m.flags = 0; // clear in-use flag
                sPoolSize--;
                return m;
            }
        }
        return new Message();
    }

```
### Android为何只能在主线程修改UI
```
View只所以不能在子线程操作UI,是因为在ViewRootImpl里做线程检测checkThread()
```
### RxAndroid
```
AndroidSchedulers:
该类提供主线程的调度，会使用到这个类中的mainThread()方法.
 public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    private static final Scheduler MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler(
            new Callable<Scheduler>() {
                @Override public Scheduler call() throws Exception {
                    return MainHolder.DEFAULT;
                }
            });
 private static final class MainHolder {

        static final Scheduler DEFAULT = new HandlerScheduler(new Handler(Looper.getMainLooper()));
    }
    
HandlerScheduler：
该类构造需要传递Handler，Handler的产生通过Looper.getMainLooper()

```
### 常见的状态码
```
200 OK 
请求正常处理完毕
204 No Content 
请求成功处理，没有实体的主体返回
206 Partial Content 
GET范围请求已成功处理
301 Moved Permanently 
永久重定向，资源已永久分配新URI
302 Found 
临时重定向，资源已临时分配新URI
303 See Other 
临时重定向，期望使用GET定向获取
304 Not Modified 
发送的附带条件请求未满足
307 Temporary Redirect 
临时重定向，POST不会变成GET
400 Bad Request 
请求报文语法错误或参数错误
401 Unauthorized 
需要通过HTTP认证，或认证失败
403 Forbidden 
请求资源被拒绝
404 Not Found 
无法找到请求资源（服务器无理由拒绝）
500 Internal Server Error 
服务器故障或Web应用故障
503 Service Unavailable 
服务器超负载或停机维护

```
### Android Binder机制
[详解](http://weishu.me/2016/01/12/binder-index-for-newer/)
```
1.通常意义下，Binder指的是一种通信机制，我们说AIDL使用Binder进行通信，指的就是
Binder这种IPC机制。
2.对于Server进程，Binder指的是Binder本地对象。
3.对于Client来说，Binder指的是Binder代理对象，它只是Binder本地对象的一个远程代理。
对这个Binder代理对象的操作，会通过驱动最终转发到Binder本地对象上去完成。对于一个拥有
Binder对象的使用者来说，无须关心这是一个Binder代理对象还是Binder本地对象。
4.对于传输过程，Binder是可以进行跨进程传递对象，Binder驱动会对具有跨进程传递能力
的对象做特殊处理，自动完成代理对象和本地对象的转换。
```
### AIDL
[详解](https://blog.csdn.net/luoyanglizi/article/details/51980630)
```
```
### 常用的网络通讯协议
[详解](https://www.cnblogs.com/zknublx/p/5874921.html)
```
ARP:地址解析协议
RARP:反向地址转换协议
IP:网际协议
TCP:
UDP:
HTTP:
HTTPS:
DNS:
POP3:
SMTP:
FTP:
TELNET:
DHCP:
```
### okhttp源码
[详解](https://www.jianshu.com/p/27c1554b7fee)
```
整个流程是，通过OkHttpClient将构建的Request转换为Call，然后在RealCall中进行异步或同步任务，
最后通过一些的拦截器interceptor发出网络请求和得到返回的response。
```
### Android中Serializable和Parcelable
```
区别	  Serializable	                      Parcelable
所属API	  JAVA API	                      Android SDK API
原理	  序列化和反序列化过程需要大量的I/O操作	序列化和反序列化过程不需要大量的I/O操作
开销	  开销大	                           开销小
效率	  低	                             很高
使用场景	序列化到本地或者通过网络传输	         内存序列化

transient关键字
java 的transient关键字为我们提供了便利，你只需要实现Serilizable接口，
将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。

transient关键字修饰以后将不能序列化了吗？
对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，则所有的序列化将会自动进行，
若实现的是Externalizable接口，则没有任何东西可以自动序列化，
需要在writeExternal方法中进行手工指定所要序列化的变量，这与是否被transient修饰无关。

```
### 横竖屏切换时候Activity的生命周期
```
1、不设置Activity的android:configChanges时，
切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次

2、设置Activity的android:configChanges="orientation"时，
切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次

3、设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"时，
切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法
```
###
```
public class A1 {
	static {
		System.out.println("static a1");
	}
	
	public A1(){
		System.out.println("A1()");
	}

}
public class B1 extends A1 {
	static {
		System.out.println("static b1");
	}
	
	public B1(){
		System.out.println("B1()");
	}

}
A1 a1=new B1();
a1=new B1();
//打印》》》》
static a1
static b1
A1()
B1()
A1()
B1()

```
### ViewGroup测量过程以及怎么让自定义view的wrap_content属性生效
[详解](https://www.jianshu.com/p/ca118d704b5e)
```
//获取宽-测量规则的模式和大小
int widthMode = MeasureSpec.getMode(widthMeasureSpec);
int widthSize = MeasureSpec.getSize(widthMeasureSpec);

// 获取高-测量规则的模式和大小
int heightMode = MeasureSpec.getMode(heightMeasureSpec);
int heightSize = MeasureSpec.getSize(heightMeasureSpec);

// 设置wrap_content的默认宽 / 高值
int mWidth = 400;
int mHeight = 400;
// 当布局参数设置为wrap_content时，设置默认值
if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT 
&& getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
setMeasuredDimension(mWidth, mHeight);
}else if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT ){
 //宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
 setMeasuredDimension(mWidth,heightSize);
}else if(getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
setMeasuredDimension(widthSize,mHeight);
}

```

### 自定义流式Tag
[详解](https://blog.csdn.net/hanhailong726188/article/details/49283873)

### 代码获取系统最上层Activity的方法
```
ActivityMananger.getRunningTask(1).getTopActivity()
```

### app数据库结构升级可能遇到的问题以及如何处理
```
```

### 容易导致内存泄露
[详情](https://www.cnblogs.com/andashu/p/6440944.html)

### view的生命周期函数
```
onVisibilityChanged->construct->onFinishInflate->onAttachedToWindow->onMeasure->onSizeChanged->
onLayout->onDraw->onDetachedFromWindow
```

### Fragment生命周期
```
onAttach->oncreate->onCreateview->onActivityCreated->onstart->onResume
->onPause->onStop->onDestroyView->onDestroy->onDetach
```

### Activity生命周期
```
onCreate->onStart->onResume->onPause->onStop->onDestroy
```

### Android中突发情况Activity数据的保存和恢复
```
无论出现怎样的情况，比如程序突然死亡了，能保证的就是onPause方法是一定会调用的，
而onStop和onDestory方法并不一定，所以这个特性使得onPause是持久化相关数据的最后的可靠时机。
当然onPause方法不能做大量的操作，这会影响下一个Activity入栈。

onSaveInstanceState并不是百分百调用的（比如点击了back键），显然一些永久性的数据，
我们并不能在此中保存。
onSaveInstance方法一定是在onStop()方法之前调用。

临时数据使用onSaveInstanceState保存恢复，永久性数据使用onPause方法保存。

onSaveInstanceState()方法什么时候执行?
调用时机：Activity容易被销毁的时候调用，注意是容易被销毁，
也可能没有销毁就调用。
1.当用户按下Home键
2.长按Home键，选择允许其他程序。
3.按下电源键时,屏幕关闭,Activity进入后台。
3.横竖屏切换时
4.启动其它Activity，Activity被压入任务栈的栈底。


```

### Android 避免Overdraw 过度绘制
[详解](https://blog.csdn.net/cq121237785/article/details/51595617)
```
合理选择控件容器、去掉window的默认背景、去掉其他不必要的背景、ViewStub
、Merge、善用draw9patch、慎用Alpha、避免“OverDesign”
```

### view动画
```
补间动画（translate/scale/rotale/alpha）、属性动画、帧动画
```

### Android属性动画（二）之插值器与估值器
[详解](https://blog.csdn.net/qq_24530405/article/details/50630744)
```
插值器Interpolator：
根据事件流逝的百分比 计算 当前属性改变的百分比.举个例子,
就是根据你设置的Durantion流逝的百分比,来改变位移(tanslate)的速度.

估值器Evaluator：
设置 属性值 从初始值过渡到结束值 的变化具体数值
```
### 自定义的RecycleView.LayoutManager需要怎么做
[详解](https://blog.csdn.net/qq_31370269/article/details/52932315)
```
实现自己的自定义LayoutManager主要的三个步骤： 
- 计算每个ItemView的位置； 
- 添加滑动事件； 
- 实现缓存。

```

### View.setLayerType()
```
Android3.0 (API level11)开始，你对何时以及如何通过View.setLayerType()
方法来使用layer有了更多的控制能力．此方法有两个参数：你想使用的layer的类型和一个可选的Paint对象，
这个对象描述了layer应被如何组合．你可以使用Paint参数来应用颜色过滤，或指定混合模式
或不透明度到一个layer．view可以使用以下三个类型之一：
• LAYER_TYPE_NONE:view按一般方式绘制，不使用离屏缓冲．这是默认的行为．
• LAYER_TYPE_HARDWARE:如果应用被硬加速了，view会被绘制到一个硬件纹理中．
如果应用没被硬加速，此类型的layer的行为同于LAYER_TYPE_SOFTWARE．
• LAYER_TYPE_SOFTWARE:view被绘制到一个bitmap中．
```
### Android多进程导致多次初始化Application
[详解](https://blog.csdn.net/lhd201006/article/details/50972843)

### 进程保活
[详解](https://www.jianshu.com/p/63aafe3c12af)
```
白色保活（调用系统api启动一个前台的Service进程）

灰色保活
思路一：API < 18，启动前台Service时直接传入new Notification()；
思路二：API >= 18，同时启动两个id相同的前台Service，然后再将后启动的Service做stop处理

```

### 关于static变量的线程间共享，进程间不共享
[详解](https://blog.csdn.net/foxliucong/article/details/4512121)

#### 内存抖动
[详解](https://www.jianshu.com/p/69e6f894c698)
```
内存抖动是由于大量对象在短时间内被配置而引起的，
所以我们要做的就是谨慎对待那些可能会大量创建对象的情况。

```

### HandlerThread
[详解](https://www.cnblogs.com/zhaoyanjun/p/6062880.html)
```
HandlerThread将loop转到子线程中处理，说白了就是将分担MainLooper的工作量，
降低了主线程的压力，使主界面更流畅。

开启一个线程起到多个线程的作用。处理任务是串行执行，按消息发送顺序进行处理。
HandlerThread本质是一个线程，在线程内部，代码是串行处理的。

但是由于每一个任务都将以队列的方式逐个被执行到，一旦队列中有某个任务执行时间过长，
那么就会导致后续的任务都会被延迟处理。

HandlerThread拥有自己的消息队列，它不会干扰或阻塞UI线程。

对于网络IO操作，HandlerThread并不适合，因为它只有一个线程，还得排队一个一个等着。


```
### Android常用的加密算法
```
对称加密：DES,AES
非对称加密：SHA、RSA、MD5
BASE64:算不上加密算法，只是对数据进行编码传输。
对称加密可以互逆，通过key加密解密
非对称加密则不可互逆，公钥和私钥

```
### ViewGroup与View的点击冲突(获取焦点)android:descendantFocusability
```
属性的值有三种： 
beforeDescendants：viewgroup会优先其子类控件而获取到焦点 
afterDescendants：viewgroup只有当其子类控件不需要获取焦点时才获取焦点 
blocksDescendants：viewgroup会覆盖子类控件而直接获得焦点 

```
### scrollview和内部控件产生滑动冲突处理
```
触摸事件决定滑动事件是否发生，一旦发生滚动事件，也就是发生MotionEvent.ACTION_MOVE.
那么带有滑动属性的控件就会自行将onInterceptTouchEvent(MotionEvent ev)
的返回值变成true.

内部控件通过view.getParent().requestDisallowInterceptTouchEvent(true)，
让父控件不拦截事件。

```

### webview与android交互
[详解](https://blog.csdn.net/hello2mao/article/details/54016002)
```
1.java代用js
WebSettings webSettings = mWebView.getSettings();  
webSettings.setJavaScriptEnabled(true);
mWebView.loadUrl("file:///android_asset/web.html");
mWebView.loadUrl("javascript:toast()");  


2.js调用java
webView.getSettings().setJavaScriptEnabled(true);
webView.addJavascriptInterface(new JSBridge(), "android");
 // 必须在主线程中
webView.loadUrl("http://hello2mao.github.io/pages/about.html");
webView.setWebViewClient(new WebViewClient() {
...
view.loadUrl("javascript:window.android.show(document.body.innerHTML);");
}

public class JSBridge {
        @JavascriptInterface
        public void show(String data) {
            // 这里的data就webview加载的内容，即使页面跳转页都可以获取到，这样就可以做自己的处理了
            Log.d("LOG_TAG", data);
        }
    }

```

### synchronized锁住的是代码还是对象
```
//锁的是对象
public void test() {
	synchronized(this){
		System.out.println("test开始..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束..");
	}
}
//锁的是对象
public synchronized void test() {
		System.out.println("test开始..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束..");
}
//锁的是类
public void test() {
	synchronized(xxx.class){
		System.out.println("test开始..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束..");
	}
}

//锁的是类
public static synchronized void test() {
		System.out.println("test开始..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束..");
}
```

### 打印n*n矩阵的螺旋方形和回字形
[详解](https://github.com/helloconch/Interview/blob/master/%E7%AE%97%E6%B3%95.md)

### 如何减少布局层级和复杂度
[详解](https://www.jianshu.com/p/4943dae4c333)
```
1.尽量不要嵌套使用RelativeLayout
2.尽量不要在嵌套的LinearLayout中使用weight
3.Layout的选择, 以尽量减少View树的层级为主.
4.去除不必要的父布局.
5.善用TextView的Drawable减少布局层级
6.如果H Viewer查看层级超过5层, 你就需要考虑优化下布局

善用Tag
<include>
<merge>使用<merge>来解决include或自定义组合ViewGroup导致的冗余层级问题
<ViewStub>
```
### 线程的关闭方式
[详解](https://blog.csdn.net/u010429311/article/details/53333262)
```
1.使用中间变量去控制。
 private static volatile boolean isDestroy=false;
 修改isDestroy的值，根据JVM内存模型可知，每个线程都有自己的工作内存，
 工作内存存储主内存的变量副本，volatile可当主线程改变刷新主内存后，其它线程
 去主内存中读取该变量。
 
 2.interrupt打断
 判断线程是否中止采用isInterrupted，
 如果线程中有Thread.sleep方法，当设置中断后，执行这个方法会抛出异常，
 就务必在异常中继续关闭线程Thread.currentThread().interrupt();
 while (true&&(!Thread.currentThread().isInterrupted())) {
            try {
                // 每执行一次暂停40毫秒
                //当sleep方法抛出InterruptedException  中断状态也会被清掉
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //如果抛出异常则再次设置中断请求
                Thread.currentThread().interrupt();
            }
        }


为何不用stop??
不安全，暴力，也是遗弃的方法，导致资源无法释放。
```

### app优化怎么做
[详解](https://www.jianshu.com/p/6d855e984b99)

### List
```
List<String> datas=new ArrayList<>();
datas.add("a");
datas.add("b");
从datas中获取0到1，不包括1的数据集合
List aa=datas.subList(0,1)
//对aa添加移除数据，datas也发生变化
aa.add("c")


工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出异常。
asList的返回对象是一个Arrays内部类,并没有实现集合的修改方法。
Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
String[] str = new String[]{"1","2"};
List list = Arrays.asList(str);

```

### 用设计模式来代替臃肿的ifelse层层判断
```
策略模式
public class NoIfElse {
      public static void main(String args[]){
         MyPaper myPaper = new MyPaper(new White());
         myPaper.choicePen();
          }
       }
interface PaperColor{
     public void getPenColor();
}
class White implements PaperColor{
      public void getPenColor(){
         System.out.println("You need a white pen!");
      }
}
class Red implements PaperColor{
       public void getPenColor(){
          system.out.println("You need a red pen!");
       }
}

泛化查表法

public class IfElseHandler {

    static HashMap<String,Flatten> workers=new HashMap<>();

    static void init(){
        workers.put("AAA",new TextMsgHandler());
        workers.put("BBB",new ImageMsgHandler());
    }

    static Object handle(String key){
        return workers.get(key).work(key);
    }

}

public interface Flatten {

    <T, F> T work(F f);
}
public class ImageMsgHandler implements Flatten{
    @Override
    public <T, F> T work(F f) {
        return (T)"Image processed";
    }
}
public class TextMsgHandler implements Flatten {
    @Override
    public <T, F> T work(F f) {
        return (T) "Text processed";
    }
}
```

### 简单工厂、工厂、抽象工厂
[详解](https://blog.csdn.net/lqw05/article/details/51909449)
[详解](https://www.jianshu.com/p/5d1a3f0ec226)
```
简单工厂：（一个工厂对多个产品）工厂根据不同的参数创建对应的产品。

工厂:(一个工厂对一个产品)是简单工厂的升级，不同的工厂实现同一个工厂方法生产不同的产品。

抽象工厂：简单工厂+工厂方法
```

### SharedPreference.Editor的apply和commit方法异同
```
1. apply没有返回值而commit返回boolean表明修改是否提交成功 
2. apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘,
而commit是同步的提交到硬件磁盘，因此，在多个并发的提交commit的时候，
他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。而apply只是原子的提交到内容
，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。 
3. apply方法不会提示任何失败的提示。 
由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，
如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。

```

### android播放视频
[详解](https://blog.csdn.net/wufeiqing/article/details/74139767)
```
android播放视频api有surface/videoview/textureview

textureview必须在Anroid4.0以上，比surfaceview方便一些。

```

### String类为何为不可变
```
String类是用final关键字修饰，说明String不可被继承。
String类内部value是一个final类型的char数组，且被private修饰，外界无法改变。
不可变有何好处？
安全、不可变对象不能被写，所以线程安全。在大量使用字符串的情况下，可以节省内存空间，提高效率。
```

### fragment各种情况下的生命周期
```
1.Fragment在Activity中replace
新替换的fragment:onAttach-oncreate-oncreateview-onviewcreated-onactivitycreated-onstart-onresume
被替换的fragment:onpause-onstop-onDestroyview-onDestroy-onDetach

2.Fragment在Activity中replace，并addToBackStack
新替换的fragment（没有在BackStack）:
onAttach-oncreate-oncreateview-onviewcreated-onactivitycreated-onstart-onresume
新替换的fragment（在BackStack）:
oncreateview-onviewcreated-onactivitycreated-onstart-onresume
被替换的fragment:
onPause-onstop-onDestroyView

3.fragment在ViewPage中切换（前面fragment-PF/后面fragment-NF/其它fragment-OF）
在viewpager中setUserVisibleHint反映出fragment是切换到后台或前台。通过setUserVisibleHint、getUserVisibleHint（）实现
懒加载，且setUserVisibleHint在onCreate前执行
a:如果重写了FragmentPagerAdapter的destroyItem方法，并且相关Fragment已经加载过
则相互切换只会调用setUserVisibleHint

4.如果fragment进入运行状态
fragmnet在上述各种情况进入onResume后，进入运行状态，以下4个生命周期跟随所属Activity一起被调用
onPause-onstop-onStart-onResume

5.关于Fragment的onActivityResult方法
使用Fragment的startActivity方法时，FragmentActivity的onActivityResult方法
会回调相应的Fragment的onActivityResult方法，所以在重写FragmentActivity的onActivityResult方法时，
注意调super.onActivityResult。

```

### Activity上有Dialog时候按home键的生命周期
```
有/无Dialog-按home键：
onPause->onStop->onRestart->onStart->onResume
无Dialog-按Back键：
onPause->onStop->onDestroy->onCreate->onStart->onResume

```
### Application和Activity的context对象的区别
```
这是两种不同的context，也是最常见的两种.第一种中context的生命周期与Application的生命周期相关的，
context随着Application的销毁而销毁，伴随application的一生，与activity的生命周期无关.
第二种中的context跟Activity的生命周期是相关的，但是对一个Application来说，Activity可以销毁几次，
那么属于Activity的context就会销毁多次.
在使用context的时候，小心内存泄露，防止内存泄露，注意一下几个方面：
1. 不要让生命周期长的对象引用activity context，即保证引用activity的对象要与activity本身生命周期是一样的
2. 对于生命周期长的对象，可以使用application context
3. 避免非静态的内部类，尽量使用静态类，避免生命周期问题，注意内部类对外部对象引用导致的生命周期变化
```

### List和Map使用场景筛选
[详解](https://www.cnblogs.com/nucdy/p/5867210.html)
### 静态内部类的设计意图
```
内部类，即定义在一个类的内部的类。
使用内部类的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。

静态内部类：
非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围内，但是静态内部类却没有。
没有这个引用就意味着：
它的创建是不需要依赖于外围类的。
它不能使用任何外围类的非static成员变量和方法。

```
### 双向链表
[详解](https://blog.csdn.net/qq_30507287/article/details/51793285)
```
 A  -->  B 
    <--
 向B节点添加一个节点C【B.pre=C/A.next=C/C.pre=A/C.next=B】
 
 将B节点移除[A.next=B.next / B.next.pre=A]
 
```
### LinearLayout 和RelativeLayout 性能比较
[详解](https://www.jianshu.com/p/8a7d059da746)
```
1.从源码中看出，Relativeout不如LinearyLaout快的根本原因是Relativelayout需要对
子view进行两次measure过程，而LinearLayout则只需要一次measure过程。
而如果LinearLayout中有weight属性，也需要进行两次measure。
2.RelativeLayout的子view如果高度和RelativeLaout不同，则会引发效率问题。子view很复杂，
问题更严重，如果可以，进行使用padding
3.不影响层级深度情况下，使用LinearLayout和FragmentLaout而不是RelativeLayout.

```
### 类型转化
```
数字型字符('0'-'9')，转化为数字 如'7'转化为7
int value='7'-'0';

数字(0-9)转化为字符'0'-'9'
char c=(char)(7+48);

字符型转化为数字 'a'....'z'..'?'
直接通过int获取

```
### JNI与NDK
[详解](https://www.jianshu.com/p/38859f881888)
```
AS2.2以下
1.在项目中声明需要调用的Native方法
2.用C++实现java的Native方法
3.添加Android.mk/Application.mk文件
4.ndk-build生成对应.so文件
5.编译项目，实现Android调用本地代码

AS2.2以上
1.创建工程时直接勾选include C++ Support
2.同时添加CMake的支持
3.修改native-lib.cpp
4.调用本地native方法
```
### AIDL
[详解](https://www.jianshu.com/p/29999c1a93cd)
```
service 指定android:exported = true
是否支持其它应用调用当前组件

activity和service，如果service没有设定属性android:process=":remote"，
service会和activity跑在同一个进程中，由于一个进程只有一个UI线程，
所以，service和acitivity就是在同一个线程里面的。

如果Activity想访问service中的对象或方法，如果service设定属性android:process=":remote"，
那么就是跨进程访问。

Service 的两大功能是什么？怎样实现？
情况1：当Acitivity和Service处于同一个Application和进程时，通过继承Binder类来实现。
情况2：跨进程通讯，使用AIDL;

```
### 
```
```
### 
```
```
### 
```
```




