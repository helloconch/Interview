adb kill-server
TIMEOUT /T 3
adb start-server
TIMEOUT /T 3
rem adb connect 10.1.36.222
rem adb connect 192.168.0.104
adb connect 10.1.36.63
TIMEOUT /T 3
adb devices
TIMEOUT /T 3