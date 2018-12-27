
[参考](https://www.jianshu.com/p/6a945778125a)

```

1.自定义版本号 在app/build.gradle 中
buildTypes {
    debug {
        buildConfigField"String","JENKINS_BUILD_NUMBER", JENKINS_BUILD_NUMBER
        }
}

2.在工程目录下的gradle.properties文件中配置
JENKINS_BUILD_NUMBER= "#1"

其实上面两步就是自定义一个变量在BuildConfig中,编译后可以直接在代码中使用BuildConfig.JENKINS_BUILD_NUMBER
3.重点是在jenkins打包构建前，替换掉#1这个数字，这时需要你在jenkins配置的Execute Shell中配置
sed -i '' "s/JENKINS_BUILD_NUMBER.*/JENKINS_BUILD_NUMBER=\ \"$BUILD_NUMBER\"/g" $WORKSPACE/gradle.properties

($BUILD_NUMBER是jenkins build number，$WORKSPACE为你的项目目录)



```
