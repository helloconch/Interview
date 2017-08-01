### adb常用命令
```
1，  开启adb  服务
adb start-server
2，  关闭adb 服务
adb kill-server
3，  查看设备
adb devices
4，  安装软件
adb install xxx.apk
5，  卸载软件
adb uninstall xxx.apk
6，  进入设备或模拟器
adb shell  devicename
如果当前只有一个设备，devicename也可以为空
7，  从电脑上发送文件到设备
adb push  PCfilepath devicepath
8，  从设备上copy文件到电脑
adb pull devicepath  PCpath
9，  查看bug报告
adb bugreport
10， 获得设备序列ID
adb get-product

11 查看log
 logcat -s  tag
 logcat -v time -s tag标记

将logcat日志进行保存
adb shell
logcat -c 
logcat -v time > ../../../../../xx.log


12，adb install -r apk位置
13，启动某一应用
adb shell am start -n com.example.apptest/com.example.apptest.MainActivity [ package/activity]
14.清除缓存
adb shell 进入设备
adb shell pm  clear 包名  ---清除缓存
15.如何用adb获得手机里面某个apk的应用信息、版本信息
adb shell dumpsys package com.examle.xx
16.获取应用的包名
adb shell  
# pm list packages  
pm list packages  
17.删除某一进程
adb shell
#ps v
#kill -9 pid

18.选中某一设备
adb devices
adb -s  xxxx shell

```
###
```
```
### 机顶盒连接调试
```
删除Android系统下的软件，使用adb shell进入系统，然后使用命令重新挂载“/system”目录为读写权限，具体操作及命令如下
1.adb shell  
2.root@android:/ # su  
3.重新挂载： mount -o remount,rw rootfs /system/
root@android:/ # mount -o remount,rw rootfs /system/ 
4.进入系统安装目录： system/app
root@android:/ # cd system/app  
5.查看apk包：ls
root@android:/ # ls 
6.删除apk包：rm -r com_voice_upgrade.apk
root@android:/system/app # rm -r com_voice_upgrade.apk  

二、删除包后，运行程序调试会出现INSTALL_FAILED_UPDATE_INCOMPATIBLE的安装错误
1、进入adb shell
2、进入程序安装信息目录
root@android:/ # cd data/data  
3、ls查询信息，删除apk信息
root@android:/data/data # rm -r com.voice.upgrade 
4、修改/data/system/packages.xml中安装包信息，将xml导出到桌面
adb pull/data/system/packages.xml C:\Users\Administrator\Desktop 
5、编辑xml删除与之前删除apk的信息
找到你需要的包名，删除<package>到</package>的一段数据。例如：
<package name="com.voice.upgrade" codePath="/system/app/LiveWallpapers.apk" nativeLibraryPath="/data/data/com.android.wallpaper/lib" flags="1" ft="11b7e237e00" it="11b7e237e00" ut="11b7e237e00" version="10" userId="10012">  
<sigs count="1">  
<cert index="2" />  
</sigs>  
</package>  
保存package.xml
6.最后将xml导入系统中
adb push C:\Users\Administrator\Desktop\packages.xml/data/system  
7.设置完成了一定要重启手机：adb reboot
adb reboot  


```
### 更高效的一种卸载内置App方式

```
第一步：先把原来已经存在的相同包名的APK删除，如：adb shell rm system/app/OutdoorMeter/OutdoorMeter.apk

第二步：将data/data/目录下该应用的包名的目录删除掉，如：adb shell rm -rf data/data/com.runbo.outdoormeter/

第三步：重启Android手机 adb reboot

第四步：再重新安装该APK
```


### adb 通过网络连接设备
```
1，  确认本机PC 没有任何adb 设备连接，包括模拟器和usb连接， 即 使用

“adb devices” 命令后， 列表显示为空， 这个是为了后面敲命令容易一些

2，  将远程android设备接入wifi， 并查看其IP地址：设置—〉无线或网络---〉WLAN设置—〉按menu键并选择“高级”选项---〉IP 地址  例如192.168.2.11

3，  本机command 命令下执行 ping  192.168.2.11， 如果ping成功，说明可以连接

4，  执行 adb connect 192.168.2.11

如果显示连接成功，则可继续执行后面的操作；如果不成功，如显示：

unable to connect to 192.168.2.11:5555

有可能是设备监听端口的问题，则可以在手机终端执行如下命令：

su  //获取root权限

setprop service.adb.tcp.port 5555   //设置监听的端口，端口可以自定义，如5554，5555是默认的

stop adbd   //关闭adbd

start adbd   //重新启动adbd

执行这个的前提是你手机可以root权限，如果没有root权限仍然不能执行su
```

### 更改Hosts配置
```
adb shell
mount -o remount,rw /system
vi /system/etc/hosts

按ESC  :wq退出并保存
```
### Monkey
```
adb shell monkey -p com.voole.epg -vvv --throttle 500 200000
 
adb shell monkey -p com.xy.android.junit -s 500 -v 10000
表示产生时间序列的种子值：500， 产生 10000个事件 




标准的monkey 命令
[adb shell] monkey [options] <eventcount> , 例如：
adb shell monkey -v 500   产生500次随机事件，作用在系统中所有activity（其实也不是所有的activity，而是包含  Intent.CATEGORY_LAUNCHER 或Intent.CATEGORY_MONKEY 的activity）。
上面只是一个简单的例子，实际情况中通常会有很多的options 选项。
2：常用选项
--help：打印帮助信息
-v：指定打印信息的详细级别，一个 -v增加一个级别 ， 默认级别为 0 。

3.事件选项
-s：指定产生随机事件种子值，相同的种子值产生相同的事件序列。如： -s 200
--throttle：每个事件结束后的间隔时间——降低系统的压力（如不指定，系统会尽快的发送事件序列）。如：--throttle 100
--pct-touch：指定触摸事件的百分比，如：--pct-touch 5% ， 相关的还有以下option：
--pct-motion <percent> （滑动事件）、 --pct-trackball <percent> （轨迹球事件） 、 --pct-nav <percent> （导航事件 up/down/left/right）、 --pct-majornav <percent> (主要导航事件 back key 、 menu key)、 --pct-syskeys <percent> (系统按键事件 Home 、Back 、startCall 、 endCall 、 volumeControl)、 --pct-appswitch <percent> （activity之间的切换）、 --pct-anyevent <percent>（任意事件）。

4.约束选项
-p：指定有效的package（如不指定，则对系统中所有package有效），一个-p 对应一个有效package， 如：-p com.ckt -p com.ckt.asura；
-c：activity必须至少包含一个指定的category，才能被启动，否则启动不了。

5.调试选项
--dbg-no-events：初始化启动的activity，但是不产生任何事件。
--hprof：指定该项后在事件序列发送前后会立即生成分析报告  —— 一般建议指定该项。
--ignore-crashes：忽略崩溃
--ignore-timeouts：忽略超时
--ignore-security-exceptions：忽略安全异常
--kill-process-after-error：发生错误后直接杀掉进程
--monitor-native-crashes：跟踪本地方法的崩溃问题
--wait-dbg：知道连接了调试器才执行monkey测试。
```
