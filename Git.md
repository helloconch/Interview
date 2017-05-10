[学习链接1](http://www.ruanyifeng.com/blog/2015/12/git-cheat-sheet.html)


[学习链接2](http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

```
1.查看本地分支<br/>
git branch<br/>

2.查看所有分支<br/>
git branch --all<br/>

3.创建一个新分支与远程相关联<br/>

git checkout -b develop origin/develop<br/>

4.查看当前分支关联情况<br/>
git checkout <br/>

5.
创建一个分支与远程相关联后，Push总被拒绝<br/>
使用该命令，告诉推送的关联关系<br/>
git push origin localBranchName:RomoteBranchName <br/>
如: git push origin daojia6.0.2:feature/daojia6.0.2<br/>
<br/>

6.clone远程某一分支
git clone -b <branchName> gitaddress

将电脑隐藏文件进行显示，命令行执行完毕后，重启Finder
defaults write com.apple.finder AppleShowAllFiles -bool true

7.将本地文件发到远程仓库
1)将本地内容进行add commit
2)$ git remote add origin git@github.com:michaelliao/learngit.git
3)$ git push -u origin master

```
