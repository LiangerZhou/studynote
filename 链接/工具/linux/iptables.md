# iptables

## 禁用3306端口

> iptables -A INPUT -p tcp --dport 3306 -j DROP

## 删除INPUT规则 第一条

> iptables -D INPUT 1

## 查看所有规则

> iptables -L -n --line-number
