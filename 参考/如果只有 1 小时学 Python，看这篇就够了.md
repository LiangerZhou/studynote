> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/O550NgRATaOggnEpBLuDkA

大家好，我是大鹏，城市数据团联合发起人，致力于 Python 数据分析、数据可视化的应用与教学。

和很多同学接触过程中，我发现自学 Python 数据分析的一个难点是资料繁多，过于复杂。大部分网上的资料总是从 Python 语法教起，夹杂着大量 Python 开发的知识点，花了很多时间却始终云里雾里，不知道哪些知识才是真正有用的。本来以为上手就能写爬虫出图，却在看基础的过程中消耗了一周又一周，**以至于很多励志学习 Python 的小伙伴牺牲在了入门的前一步。**

![](https://mmbiz.qpic.cn/mmbiz_jpg/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwRaeSTx4K3MTO9M3wGVAICuTc8Hz9IYGibhdPA2iczeUl6EGFrO7ib0GZA/640?wx_fmt=jpeg)

于是，我总结了以下一篇干货，来帮助大家理清思路，提高学习效率。总共分为三大部分：做 Python 数据分析必知的语法，如何实现爬虫，怎么做数据分析。

**1. **必须知道的两组 Python 基础术语****  

**A. 变量和赋值**

Python 可以直接定义变量名字并进行赋值的，例如我们写出 **a**** =**** 4** 时，Python 解释器干了两件事情：

*   在内存中创建了一个值为 4 的整型数据
    
*   在内存中创建了一个名为 a 的变量，并把它指向 4
    

用一张示意图表示 **Python 变量和赋值的重点**：

**![](https://mmbiz.qpic.cn/mmbiz_jpg/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwwtg1HkzJ0cNjYkwXpHwlicT0rViae4QCyXAlyaNSgl9X2kG36cc8ToLg/640?wx_fmt=jpeg)**

例如下图代码，“=” 的作用就是赋值，同时 Python 会自动识别数据类型:

```
a=4 #整型数据
b=2 #整型数据
c=“4” #字符串数据
d=“2” #字符串数据

print(“a+b结果为”，a+b)#两个整数相加，结果是6
print(“c+d结果为”，c+d)#两个文本合并，结果是文本“42”

#以下为运行结果
>>>a+b结果为 6
>>>c+d结果为 42


```

**B. 数据类型**

在初级的数据分析过程中，有三种数据类型是很常见的：

*   **列表 list（Python 内置）**
    
*   **字典 dic（Python 内置）**
    
*   **DataFrame（工具包 pandas 下的数据类型，需要 import pandas 才能调用）**
    

它们分别是这么写的：

**列表（list）：**

```
#列表
liebiao=[1,2.223,-3,'刘强东','章泽天','周杰伦','昆凌',['微博','B站','抖音']]

```

> list 是一种**有序**的集合，里面的元素可以是之前提到的任何一种数据格式和数据类型（整型、浮点、列表……），并可以随时指定顺序添加其中的元素，其形式是：

```
#ist是一个可变的有序表，所以，可以往list中追加元素到末尾：
liebiao.append('瘦')
ptint(liebiao)
#结果1
>>>[1, 2.223, -3, '刘强东', '章泽天', '周杰伦', '昆凌', ['微博', 'B站', '抖音'], '瘦']

#也可以把元素插入到指定的位置，比如索引号为5的位置,插入“胖”这个元素：
liebiao.insert(5, '胖')
print(liebiao)
#结果2
>>>[1, 2.223, -3, '刘强东', '章泽天', '胖', '周杰伦', '昆凌', ['微博', 'B站', '抖音'], '瘦']


```

**字典（dict）：**  

```
#字典
zidian={'刘强东':'46','章泽天':'36','周杰伦':'40','昆凌':'26'}

```

> 字典使用**键 - 值（key-value）**存储，无序，具有极快的查找速度。以上面的字典为例，想要快速知道周杰伦的年龄，就可以这么写：

```
zidian['周杰伦']
>>>'40'


```

dict 内部存放的顺序和 key 放入的顺序是没有关系的, 也就是说，"章泽天" 并非是在 "刘强东" 的后面。

**DataFrame：**

DataFrame 可以简单理解为 **excel 里的表格格式**。导入 pandas 包后，字典和列表都可以转化为 DataFrame，以上面的字典为例，转化为 DataFrame 是这样的：

```
import pandas as pd


df=pd.DataFrame.from_dict(zidian,orient='index',columns=['age'])#注意DataFrame的D和F是大写
df=df.reset_index().rename(columns={'index':'name'})#给姓名加上字段名

```

![](https://mmbiz.qpic.cn/mmbiz_jpg/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwjjGBt7yMwbUHSGZMrw5BLUGiaduj0cMU720BMjrq9TjKdDocP0s5ficA/640?wx_fmt=jpeg)

和 excel 一样，DataFrame 的任何一列或任何一行都可以单独选出进行分析。  

**以上三种数据类型是 python 数据分析中用的最多的类型，基础语法到此结束，接下来就可以着手写一些函数计算数据了。**

**2. 从 Python 爬虫学循环函数**  

掌握了以上基本语法概念，我们就足以开始学习一些有趣的函数。我们以爬虫中绕不开的遍历 url 为例，讲讲大家最难理解的**循环函数 for** 的用法：

**A.for 函数**

for 函数是一个常见的循环函数，先从简单代码理解 for 函数的用途：

```
zidian={'刘强东':'46','章泽天':'36','周杰伦':'40','昆凌':'26'}
for key in zidian:
        print(key)
>>>
刘强东
章泽天
周杰伦
昆凌


```

因为 dict 的存储不是按照 list 的方式顺序排列，所以，迭代出的结果顺序很可能不是每次都一样。默认情况下，dict 迭代的是 key。如果要迭代 value，可以用 for value in d.values()，如果要同时 #迭代 key 和 value，可以用 for k, v in d.items()  

可以看到，字典里的人名被一一打印出来了。**for 函数的作用就是用于遍历数据**。掌握 for 函数，可以说是真正入门了 Python 函数。

**B. 爬虫和循环**

for 函数在书写 Python 爬虫中经常被应用，因为**爬虫经常需要遍历每一个网页**，以获取信息，所以构建完整而正确的网页链接十分关键。以某票房数据网为例，他的网站信息长这样：

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOAoSsg1qBiaDnFzSITvTluia8scnHXViaXz3gFvmEcAvUlXicviahXpDuPqNFB2seLPjr7cR8fFMQGhCxA/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwOS2hraOzdR2fjrJpxLRicM6ic4X8jlx138RJc1SYzv9edUUK1t3NLNUg/640?wx_fmt=png)

该网站的周票房 json 数据地址可以通过抓包工具找到，网址为 http://www.cbooo.cn/BoxOffice/getWeekInfoData?sdate=20190114

仔细观察，该网站不同日期的票房数据网址（url）只有后面的日期在变化，访问不同的网址（url）就可以看到不同日期下的票房数据：

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwUWLvibWa6xNr0nRozMab7Rvb6q422RyaQ0alHaUxXMILDZPaxRBhTPQ/640?wx_fmt=png)

我们要做的是，**遍历每一个日期下的网址，用 Python 代码把数据爬下来****。**此时 for 函数就派上用场了，使用它我们可以快速生成多个符合条件的网址：

```
import pandas as pd

url_df = pd.DataFrame({'urls':['http://www.cbooo.cn/BoxOffice/getWeekInfoData?sdate=' for i in range(5)],'date' :pd.date_range(20190114,freq = 'W-MON',periods = 5)})

'''
将网址相同的部分生成5次，并利用pandas的时间序列功能生成5个星期一对应的日期。
其中用到了第一部分提供的多个数据类型：
range(5)属于列表，
'urls'：[]属于字典，
pd.dataframe属于dataframe
'''
url_df['urls'] = url_df['urls'] + url_df['date'].astype('str')

```

滑动滑块可以看到完整代码和中间的注释。

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwGOsA1FZvTHoJUJQF3VQJ8VI3OYAXnUujmh5ibPhHXbPmTibEAWBzoprg/640?wx_fmt=png)

为了方便理解，我给大家画了一个 for 函数的遍历过程示意图：

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwY13wVO6o7ZFvEjoXROwm1UFm2EsamhEugSyVPnaOCAWEOflXohh4qA/640?wx_fmt=png)

此处省略掉后续爬取过程，相关爬虫代码见文末。我们使用爬虫爬取了 **5800 + 条数据，包含 20 个字段**，时间囊括了从 2008 年 1 月开始至 2019 年 2 月十一年期间的**单周票房、累计票房、观影人次、场均人次、场均票价、场次环比变化等信息**。

**3.Python 怎么实现数据分析？**  

除了爬虫，分析数据也是 Python 的重要用途之一，**Excel 能做的事，Python 究竟怎么实现呢；Excel 不能做的事，Python 又是否能实现呢？**利用电影票房数据，我们分别举一个例子说明：  

**A.Python 分析**

在做好数据采集和导入后，选择字段进行初步分析可以说是数据分析的必经之路。在 Dataframe 数据格式的帮助下，这个步骤变得很简单。

比如当我们想看单周票房第一的排名分别都是哪些电影时，可以使用 pandas 工具库中常用的方法，筛选出周票房为第一名的所有数据，并保留相同电影中周票房最高的数据进行分析整理：

```
import pandas as pd
data = pd.read_csv('中国票房数据爬取测试20071-20192.csv',engine='python')
data[data['平均上座人数']>20]['电影名']
#计算周票房第一随时间变化的结果，导入数据，并选择平均上座人数在20以上的电影为有效数据

dataTop1_week = data[data['排名']==1][['电影名','周票房']]
#取出周票房排名为第一名的所有数据，并保留“电影名”和“周票房”两列数据

dataTop1_week = dataTop1_week.groupby('电影名').max()['周票房'].reset_index()
#用“电影名”来分组数据，相同电影连续霸榜的选择最大的周票房保留，其他数据删除

dataTop1_week = dataTop1_week.sort_values(by='周票房',ascending=False)
#将数据按照“周票房”进行降序排序

dataTop1_week.index = dataTop1_week['电影名']
del dataTop1_week['电影名']
#整理index列，使之变为电影名，并删掉原来的电影名列

dataTop1_week
#查看数据

```

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwp0sutVdADF5Sk9hRliaiatjKNWKSibzOKRDrvPIawSbtPAvGoPpkZukGw/640?wx_fmt=png)

9 行代码，我们完成了 Excel 里的透视表、拖动、排序等鼠标点击动作。最后再用 Python 中的可视化包 matplotlib，快速出图：

![](https://mmbiz.qpic.cn/mmbiz_gif/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwgCUoLY7hbWMapFBNZM5WETZnN4Hc59jfIJT1K8I8Xfv5lVXJfjbCcw/640?wx_fmt=gif)

![](https://mmbiz.qpic.cn/mmbiz_png/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDw6Via3QRCMIsibGiceFxDpQicU1O6WldRJITBJMj5XNB9Amicc4NrMtDcAYg/640?wx_fmt=png)

**B. 函数化分析**

以上是一个简单的统计分析过程。接下来就讲讲 Excel 基础功能不能做的事——自定义函数提效。观察数据可以发现，数据中记录了周票房和总票房的排名，**那么刚刚计算了周票房排名的代码，还能不能复用做一张总票房分析呢？**

![](https://mmbiz.qpic.cn/mmbiz_jpg/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwNaMSzL4frJJ082OcIw8yLUibYyMibFDAAiaokM0vA9j4AonLHbDmFcx4A/640?wx_fmt=jpeg)

当然可以，只要使用 **def 函数和刚刚写好的代码**建立自定义函数，并说明函数规则即可：

```
def pypic(pf):
    #定义一个pypic函数，变量是pf
    dataTop1_sum = data[['电影名',pf]]
    #取出源数据中，列名为“电影名”和pf两列数据

    dataTop1_sum = dataTop1_sum.groupby('电影名').max()[pf].reset_index()
    #用“电影名”来分组数据，相同电影连续霸榜的选择最大的pf票房保留，其他数据删除

    dataTop1_sum = dataTop1_sum.sort_values(by=pf,ascending=False)
    #将数据按照pf进行降序排序

    dataTop1_sum.index = dataTop1_sum['电影名']
    del dataTop1_sum['电影名']
    #整理index列，使之变为电影名，并删掉原来的电影名列

    dataTop1_sum[:20].iloc[::-1].plot.barh(figsize = (6,10),color = 'orange')
    name=pf+'top20分析'
    plt.title(name)
    #根据函数变量名出图


```

定义函数后，批量出图 so easy：

![](https://mmbiz.qpic.cn/mmbiz_gif/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwbLQoMJQhSxia6nxEZGQIGlFvVfgYXLJ1icx2TdjHLAtic8QGEWJuHLdRw/640?wx_fmt=gif)

![](https://mmbiz.qpic.cn/mmbiz_jpg/DDGCfqwWPOD56w0ibbibvcKKQwtfib7ZpDwMHKpNCRNuJDicEY46FOyFgb2Kyribicib12k080IO53Rh9lLb0cRbicqQkw/640?wx_fmt=jpeg)

**学会函数的构建，一个数据分析师才算真正能够告别 Excel 的鼠标点击模式，迈入高效分析的领域**。

**4. 光看不练是永远不能入门的**  

如果只有一小时学习，以上就是大家一定要掌握的 Python 知识点。光看不练永远都会是门外汉，如果你有兴趣学习 Python 数据分析，却在过程中感到困惑，欢迎来参加我在网易云课堂的免费直播，每晚一个主题，有学有练，让你快速入门 Python 数据分析：

**不间断直播陪你从入门到精通**

**![](https://mmbiz.qpic.cn/mmbiz_jpg/4MXV7svuTWL2Z8R6ibKbbP33SJIdTUoP3GP0uGfUmm8MyTj8KIjFrRDMvDvmB9EDTafjyxkRiamxeSAgX6VwlEbA/640?wx_fmt=jpeg)**

**扫码即可预约免费直播席位**

**5.7 周二 20:00**

《快速入门：搞定初学 Python 的十大易错点》

1. python 基础语法学习路径

2. 十大易错点分别是哪些？

3. 用 python 实现第一个数据爬虫

**5.8 周三 20:00**

《告别加班：用 Pandas 代替 Excel 快速处理数据》

1. 如何用 Python 快速处理数据？

2. 初学者使用 pandas 最容易犯的错误

3.  Pandas 套路总结

**5.9 周四 20:00**

《穷人和富人就差 1% 的努力：用随机数模拟社会财富分配》

1. 什么是蒙特卡罗思想

2. 随机数模拟的前提: 准确判断数据分布

3. 模型构建，模拟社会财富分配

**5.13 周一 20:00**

《Python 数据可视化利器：pyecharts！》

1. 为什么需要交互式图表来做数据表达？

2. pyecharts 基础操作

3. 数据可视化技能图谱详解

**5.14 周二 20:00**

《1 小时入门 python 爬虫：当数据分析师就该自己爬数据！》

1. 快读读懂网页结构

2. 页面解析及标签提取

3. 实现第 1 个数据爬虫

**5.15 周三 20:00**

《用数据做攻略：找到一个城市最有趣的地方》

1. 数据爬虫构建

2. 字段筛选与数据清洗

3. 筛选机制及评价方法

4. 空间数据可视化表达结果

**5.16 周四 20:00**

《人口数据：揭秘一年来上海工作的人员流动情况》

1. 全国人口迁徙数据采集

2. 数据整理与核心城市筛选

3. 数据表达：OD 图制作方法与技巧

**赠送免费课程**  

![](https://mmbiz.qpic.cn/mmbiz_jpg/4MXV7svuTWJa5jPtXibh6p97aiaQBiat7sWASraXlpic1E7qyzso8hDPRQPrUOTLuORYFA6jYEqaib0BOMn4u8nokng/640?wx_fmt=jpeg)

**课程大纲**

1. Python 语言入门

2. 每月净收入模型构建

3. 每月支出模型构建

4. 不同情况下的花呗还款场景模拟

5. 负债积累问题

6. 如何用图表讲好一个故事？(彩蛋)

**6G 入门资料**

**![](https://mmbiz.qpic.cn/mmbiz_jpg/4MXV7svuTWIBWYlJJPcxQlQw8YVvyNzKx2Sz4JLkQwI03vq9YlMrddMiaojDGiciaZOjS5cNcicle9XZI0HmYqkucQ/640?wx_fmt=jpeg)**

本文所示票房数据爬取和分析代码以及中国票房数据源数据，已经放在百度网盘 6g 资料包内

**福利领取方式**

****所有以上福利，扫码添加网易云课堂小助手即可获得****

![](https://mmbiz.qpic.cn/mmbiz_jpg/4MXV7svuTWL2Z8R6ibKbbP33SJIdTUoP3GP0uGfUmm8MyTj8KIjFrRDMvDvmB9EDTafjyxkRiamxeSAgX6VwlEbA/640?wx_fmt=jpeg)

**微信号：neteasepython**

**席位有限，先到先得**