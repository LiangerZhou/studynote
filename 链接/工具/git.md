# Git 用法

* 用于提交新commit

> git add . + git commit + git push origin master:refs/for/master

* 用于修改上一次提交内容，然后合并为同一个commit

> git add + git commit --amend + git push origin HEAD:refs/for/${目标分支}

* 显示所有commit

> git log

* 用于回归push到某一个版本【这会丢掉该版本后的所有push记录】

> git reset --hard  commit-id   + git push -f
