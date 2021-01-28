D:
TIMEOUT /T 1
cd D:\Android\SDK\build-tools\29.0.3\lib
TIMEOUT /T 1
java -jar apksigner.jar sign --ks C:\key\gitv.keystore --ks-key-alias gitv.keystore --ks-pass pass:bGlsZWkgbG92ZSBnaXR2 --key-pass pass:bGlsZWkgbG92ZSBnaXR2 --out E:\output\test-signed-jiagu.apk E:\output\test.apk
TIMEOUT /T 1
java -jar apksigner.jar verify -v E:\output\test-signed-jiagu.apk
TIMEOUT /T 60