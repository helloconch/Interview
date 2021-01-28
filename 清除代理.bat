adb root
TIMEOUT /T 1
adb remount
TIMEOUT /T 1
adb push E:\box-hosts\2\hosts  /system/etc/hosts
TIMEOUT /T 1
adb shell pm clear com.gitv.tv.live
TIMEOUT /T 3
adb shell am start -n com.gitv.tv.live/com.gitv.tv.live.activity.VodActivity
TIMEOUT /T 1