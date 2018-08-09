[学习链接1](http://www.ruanyifeng.com/blog/2015/12/git-cheat-sheet.html)


[学习链接2](http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

```
1.查看本地分支
git branch

2.查看所有分支--带remotes代表远程分支
git branch -a

3.本地新创建一个分支dev
git checkout -b dev
Switched to branch 'dev'

4.若远程没有dev分支，将新创建的分支push到远程
git push origin dev
当push时遇到如下错误:
fatal: The current branch develop has no upstream branch.
To push the current branch and set the remote as upstream, use
git push --set-upstream origin dev


5.新clone一个项目，git branch时只显示master分支，查看git branch -a 显示所有分支
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/dev
  remotes/origin/master

此时本地想创建一个dev分支与远程dev关联
$ git checkout -b dev origin/dev
Switched to a new branch 'dev'
Branch 'dev' set up to track remote branch 'dev' from 'origin'.

在dev修改某一文件
git add .
git commit -m 'update'
git push

6.clone远程某一分支dev
$ git clone -b dev https://github.com/helloconch/TT.git


7.切换分支
git checkout dev

8.在dev上开发完功能，合并到主分支
a.dev分支将新修改的内容add、commit、push到dev分支
b.切换到master 执行git merge dev 然后push
若dev功能还未开发完，可先推到远程dev分支


9.删除本地分支dev2
a.切换到master分支 git checkout master
b.删除分支 git branch -d dev2




将电脑隐藏文件进行显示，命令行执行完毕后，重启Finder
defaults write com.apple.finder AppleShowAllFiles -bool true


mac系统下，如果改坏了bash_profile后的救助方法
export PATH=/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin

```



