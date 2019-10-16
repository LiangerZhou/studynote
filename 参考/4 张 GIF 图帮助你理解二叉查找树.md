> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/DygzH_FarNH90qf2ttYReA

（点击上方公众号，可快速关注）

> 来源：伯乐在线 - 伯小乐 
> 
> 链接：http://blog.jobbole.com/101366/

二叉查找树（Binary Search Tree），也称二叉搜索树，是指一棵空树或者具有下列性质的二叉树：

*   任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
    
*   任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
    
*   任意节点的左、右子树也分别为二叉查找树；
    
*   没有键值相等的节点。
    

二叉查找树相比于其他数据结构的优势在于查找、插入的时间复杂度较低。为 O(log n)。二叉查找树是基础性数据结构，用于构建更为抽象的数据结构，如集合、multiset、关联数组等。（摘自维基百科）

下面 4 张 GIF 动图，是 penjee 官博制作分享，正好伯小乐最近看到，分享给大家。

### **图 1：****查找 BST 中的某个元素**

在二叉搜索树 b 中查找 x 的过程为：

1.  若 b 是空树，则搜索失败，否则：
    
2.  若 x 等于 b 的根节点的数据域之值，则查找成功；否则：
    
3.  若 x 小于 b 的根节点的数据域之值，则搜索左子树；否则：
    
4.  查找右子树。
    

![](https://mmbiz.qpic.cn/mmbiz/QtPIxk7nOVeibpicoibyjNZKic9Ke41yzr53oVM4Hb15icz4HnsuD5x2sh3xX5pmSCmkTZCQ4ia2tCSAianEGMm8X6S1g/640?wx_fmt=gif)

### **图 2 ↓ ：****从有序数组构造一个二叉查找树**

![](https://mmbiz.qpic.cn/mmbiz/QtPIxk7nOVeibpicoibyjNZKic9Ke41yzr53nolQM6w5ibEKzg8GCbbCrSOs75gH6ibzsKuyWLtqfJOgwMW0mibprneEQ/640?wx_fmt=gif)

### **图 3 ↓：****往 BST 中插入元素**

向一个二叉搜索树 b 中插入一个节点 s 的算法，过程为：

1.  若 b 是空树，则将 s 所指结点作为根节点插入，否则：
    
2.  若 s->data 等于 b 的根节点的数据域之值，则返回，否则：
    
3.  若 s->data 小于 b 的根节点的数据域之值，则把 s 所指节点插入到左子树中，否则：
    
4.  把 s 所指节点插入到右子树中。（新插入节点总是叶子节点）
    

![](https://mmbiz.qpic.cn/mmbiz/QtPIxk7nOVeibpicoibyjNZKic9Ke41yzr53uPGhSCDVZIBR6zZABqlnvTxoC9DJSibZPqPVjbxyDrT3surW9xPxN7w/640?wx_fmt=gif)

### **图 4 ↓：****BST 转成有序数组**

![](https://mmbiz.qpic.cn/mmbiz/QtPIxk7nOVeibpicoibyjNZKic9Ke41yzr53CkNXLLYUB4GRaWrrmMPlEEolic8TcVHMTtpJjrXgxnHpC1UHQAe9O6A/640?wx_fmt=gif)

![](https://mmbiz.qpic.cn/mmbiz/QtPIxk7nOVcFcJfc3l7xpLl48d2YHYK16VobcpfoBx3z2ibBOS7sNeAumibnmK2zVwxLMibVZBqyL5j7u7TkTfPOA/640?wx_fmt=png)