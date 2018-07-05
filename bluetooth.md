### 蓝牙BLE(Bluetooth low energy)
[Android官方文档](https://developer.android.com/guide/topics/connectivity/bluetooth-le)

[BlueTooth BLE 之 Central 与 Peripheral 理解](http://www.knowsky.com/982766.html)

[深入蓝牙基础、深入、总结](https://blog.csdn.net/androidstarjack/article/details/60595241)

```
蓝牙4.0标准包含两个蓝牙标准（双模的标准），包含传统蓝牙部分（也有称之为经典蓝牙Classic Bluetooth）
和低功耗蓝牙部分（Bluetooth Low Energy）。该技术拥有极低的运行和待机功耗，使用一粒纽扣电池甚至可连续工作数年之久。

蓝牙4.0是集成了传统蓝牙和低功耗蓝牙两个标准的，并不只是低功耗蓝牙。

传统蓝牙可以用与数据量比较大的传输，如语音，音乐，较高数据量传输等。

低功耗蓝牙这样应用于实时性要求比较高，但是数据速率比较低的产品，
如遥控类的，如鼠标，键盘，遥控鼠标(Air Mouse)，传感设备的数据发送，如心跳带，血压计，温度传感器等，
低功耗蓝牙是通过Gatt协议来实现。

Android 4.3 (API level 18) introduces built-in platform support for Bluetooth Low Energy (BLE) 

```
### 权限
[6.0设备着重注意地方](https://www.cnblogs.com/Free-Thinker/p/6566713.html)
```
声明权限
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>

如果想让你的app仅仅对BLE可用，则需要配置下manifest.
<uses-feature android:name="android.hardware.bluetooth_le" android:required="true"/>

检测设备是否支持BLE
if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
    Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
    finish();
}

设备6.0增加   <uses-permission-sdk-23 android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

### 说明
```
如果设备不支持BLE，则所有BLE特性需要被禁用。
如果支持BLE，但不可用，可用引导用户启动蓝牙，设置过程中通过BluetoothAdapter.
1)
private BluetoothAdapter mBluetoothAdapter;
...
// Initializes Bluetooth adapter.
final BluetoothManager bluetoothManager =
        (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
mBluetoothAdapter = bluetoothManager.getAdapter();

2)判断Bluetooth是否激活，未激活则跳转到打开界面
if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
}

```

### 查找BLE设备
```
通过 startLeScan() 查找BLE devices.
有两条注意事项
1)一旦找到想要的设备，停止扫描。
2)设置扫描时间，避免循环一直扫描。

````
### 连接设备
```
device的connectGatt方法进行设备连接
BluetoothGatt gatt = device.connectGatt(this, false, mGattCallback);


```

### BLE
```
有多个service组成， 每个service有多个characteristic,
每个characteristic有1个value和多个Descriptor组成，
1个Descriptor包含1个value.

```

