#环境
Mac+ShadowSocks  
###步骤
1.设置git代理  
git config --global http.proxy socks5://localhost:1080  
2.进行clone   
```
Cloning into 'volley'...
fatal: unable to access 'https://android.googlesource.com/platform/frameworks/volley/': Could not resolve host: android.googlesource.com

```
3.通过nslookup android.googlesource.com查找对应IP地址  
4.将其添加到/etc/hosts文件下，当然需要权限  
5.再次尝试进行clone
```
localhost:~ cheyanxu$ git clone https://android.googlesource.com/platform/frameworks/volley
Cloning into 'volley'...
remote: Counting objects: 177, done
remote: Finding sources: 100% (177/177)
remote: Total 3235 (delta 302), reused 3235 (delta 302)
Receiving objects: 100% (3235/3235), 1.23 MiB | 172.00 KiB/s, done.
Resolving deltas: 100% (302/302), done.
Checking connectivity... done.

```

6.需要重设代理的话
```
git config --global --unset http.proxy
git config --global --unset https.proxy
```
