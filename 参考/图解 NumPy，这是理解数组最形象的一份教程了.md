> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/2g2lYSZI-IOTaZWJwIO9Bg

![](https://mmbiz.qpic.cn/mmbiz_gif/KfLGF0ibu6cLJiaicLnPCqYROg1Y8spzCyZGKg6ra9H53HCaejCGGjuXl9x9VZ0iaQQCSCzibMkS0iciaQpAntVMQibPgQ/640?wx_fmt=gif)

**导读：**本文用可视化的方式介绍了 NumPy 的功能和使用示例。

作者：Jay Alammar

来源：机器之心（ID：almosthuman2014）编译，参与：高璇、路

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxibhj5mOV77c38NaRqCMXIMCL2ugfsuaZ4rAnFxoHVuTiaW7gDJHib9Y4Gg/640?wx_fmt=png)

NumPy 软件包是 Python 生态系统中数据分析、机器学习和科学计算的主力军。**它极大地简化了向量和矩阵的操作处理。**

Python 的一些主要软件包（如 scikit-learn、SciPy、pandas 和 tensorflow）都以 NumPy 作为其架构的基础部分。除了能对数值数据进行切片（slice）和切块（dice）之外，使用 NumPy 还能为处理和调试上述库中的高级实例带来极大便利。

本文将介绍使用 NumPy 的一些主要方法，以及在将数据送入机器学习模型之前，它如何表示不同类型的数据（表格、图像、文本等）。

```
import numpy as np

```

**01 创建数组**

我们可以通过传递一个 python 列表并使用 `np.array()` 来创建 NumPy 数组（极大可能是多维数组）。在本例中，python 创建的数组如下图右所示：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib8hEtpY9zC1VDuCHOyYx1k0tYibvbASLOs0EVfWz04mBq5H7t5liaicMsQ/640?wx_fmt=png)

通常我们希望 NumPy 能初始化数组的值，为此 NumPy 提供了 ones()、zeros() 和 random.random() 等方法。我们只需传递希望 NumPy 生成的元素数量即可：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibSLx5EmdRZIibh2sX9dzoUMRu53dZMxdvIG6icsnazttiarKnvqZaZhIaQ/640?wx_fmt=png)

一旦创建了数组，我们就可以尽情对它们进行操作。

**02 数组运算**

让我们创建两个 NumPy 数组来展示数组运算功能。我们将下图两个数组称为 `data` 和 `ones`：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibcjcyE0Ql53FILl9h6OEV0qHl6BNc7pvjcESZcQJWawfldHLTOGib13Q/640?wx_fmt=png)

将它们按位置相加（即每行对应相加），直接输入 `data + ones` 即可：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibdDKPKNS9Kib7Ntrvvk0JiaxoB55W6scre8BLVIblq83HcRKDjSGgdb5g/640?wx_fmt=png)

当我开始学习这些工具时，我发现这样的抽象让我不必在循环中编写类似计算。此类抽象可以使我在更高层面上思考问题。

除了「加」，我们还可以进行如下操作：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibWZnN1XykKb3hF1dlDLLklg1MxNiaWb1eNl1ksiboEXv4SYpGx8rpwhTg/640?wx_fmt=png)

通常情况下，我们希望数组和单个数字之间也可以进行运算操作（即向量和标量之间的运算）。比如说，我们的数组表示以英里为单位的距离，我们希望将其单位转换为千米。只需输入 `data * 1.6` 即可：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibrW2TEwJYDCAGyKSJeic4Ru7Mk1T6q4Z7I5p8X747I2jDZiadY22YPdRw/640?wx_fmt=png)

看到 NumPy 是如何理解这个运算的了吗？这个概念叫做广播机制（broadcasting），它非常有用。

**03 索引**

我们可以我们像对 python 列表进行切片一样，对 NumPy 数组进行任意的索引和切片：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib8VKoGgOPAcYSNsT8sib77dJbDLiaqsc1JqAoq7Wq6qiaQic1QAnVG6ZcYw/640?wx_fmt=png)

**04 聚合**

NumPy 还提供聚合功能：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibkRicq2JzDlSNmkTxjfIXGaAQWoFkTIwzgiaV19yMw5cia9ROPy3icrUfsQ/640?wx_fmt=png)

除了 `min`、`max` 和 `sum` 之外，你还可以使用 `mean` 得到平均值，使用 `prod` 得到所有元素的乘积，使用 `std` 得到标准差等等。

**05 更多维度**

上述的例子都在一个维度上处理向量。NumPy 之美的关键在于，它能够将上述所有方法应用到任意数量的维度。

**1. 创建矩阵**

我们可以传递下列形状的 python 列表，使 NumPy 创建一个矩阵来表示它：

```
np.array([[1,2],[3,4]])

```

我们也可以使用上面提到的方法（`ones()`、`zeros()` 和 `random.random()`），只要写入一个描述我们创建的矩阵维数的元组即可：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib3Y0UD2J5ohmDj4txAL6zV2fr541aibWUFnQt6pTic2Na2MxICBfEWvrQ/640?wx_fmt=png)

**2. 矩阵运算**

如果两个矩阵大小相同，我们可以使用算术运算符（`+-*/`）对矩阵进行加和乘。NumPy 将它们视为 position-wise 运算：

![](https://mmbiz.qpic.cn/mmbiz_png/KfLGF0ibu6cL8helpX0YXEfHiavWOLzXJ0QgE7qOs570Row8XGYic7iahDM8zicZiahcw00n39iaaSjhjk7NMJnyQG1xg/640?wx_fmt=png)

我们也可以对不同大小的两个矩阵执行此类算术运算，但前提是某一个维度为 1（如矩阵只有一列或一行），在这种情况下，NumPy 使用广播规则执行算术运算：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib9NFy7etpXbBxmUwicqegYPd5bL4JcaZcmecHQibVVWEQNgicJUibibgcNHA/640?wx_fmt=png)

**3. 点乘**

算术运算和矩阵运算的一个关键区别是矩阵乘法使用点乘。NumPy 为每个矩阵赋予 `dot()` 方法，我们可以用它与其他矩阵执行点乘操作：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibQrlbftOc93eibE4icDXkriaqiaNbKkibUibIZDC3JYzav76zPYsOvIRZAiaqA/640?wx_fmt=png)

我在上图的右下角添加了矩阵维数，来强调这两个矩阵的临近边必须有相同的维数。你可以把上述运算视为：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibXSBRa2h4cwK594C12QYCKhF1NayM82C0iaSfibS3M9JMNTiajENc0tIGA/640?wx_fmt=png)

**4. 矩阵索引**

当我们处理矩阵时，索引和切片操作变得更加有用：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib5hqbs8DUUbe3y9P8EusTREgxZmO3l5k4trARibNkEQWarN0UFNDbExw/640?wx_fmt=png)

**5. 矩阵聚合**

我们可以像聚合向量一样聚合矩阵：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibhNIVJkvhH3CicuDlsSotOGUlN0CqQic6mB5nuT8AsYxyibibfXlFZZ7bKg/640?wx_fmt=png)

我们不仅可以聚合矩阵中的所有值，还可以使用 `axis` 参数执行跨行或跨列聚合：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibkRWHPptib1a7ic4QhNP14vICWhDC9gibXyjiaLMtjCvgXrCNt2LAbLYB4w/640?wx_fmt=png)

**6. 转置和重塑**

处理矩阵时的一个常见需求是旋转矩阵。当需要对两个矩阵执行点乘运算并对齐它们共享的维度时，通常需要进行转置。NumPy 数组有一个方便的方法 `T` 来求得矩阵转置：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibtFI8QaEKko6jMTOZYU9lLdO65ynwIZic3Cl2rriaP3tzed9w8libfXlsw/640?wx_fmt=png)

在更高级的实例中，你可能需要变换特定矩阵的维度。在机器学习应用中，经常会这样：某个模型对输入形状的要求与你的数据集不同。在这些情况下，NumPy 的 `reshape()` 方法就可以发挥作用了。只需将矩阵所需的新维度赋值给它即可。可以为维度赋值 - 1，NumPy 可以根据你的矩阵推断出正确的维度：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibzX4du7quJGoIc6K2ic0QsYZsa4jB7icWCmRQgNpdymH3GFUtYHNXyia0g/640?wx_fmt=png)

**06 再多维度**

NumPy 可以在任意维度实现上述提到的所有内容。其中心数据结构被叫作 ndarray（N 维数组）不是没道理的。

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibiaNfqn6OCOf4AEQuqwZhKwBKtvNibHOysMfsIib50YSlncv6ZyE7vRcCA/640?wx_fmt=png)

在很多情况下，处理一个新的维度只需在 NumPy 函数的参数中添加一个逗号：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibuibKXqEG3xjD3APPPbd3h5bzypOprjdzicxpZkr2w7d9ibRPsEOPQl1Sg/640?wx_fmt=png)

**07 实际用法**

以下是 NumPy 可实现的有用功能的实例演示。

**1. 公式**

实现可用于矩阵和向量的数学公式是 NumPy 的关键用例。这就是 NumPy 是 python 社区宠儿的原因。例如均方差公式，它是监督机器学习模型处理回归问题的核心：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibdR5w2VgZicK6Iezibas2vFIEGq4DX8eSUgKfj75aBRkCqF7ers7FPbSg/640?wx_fmt=png)

在 NumPy 中实现该公式很容易：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibqJobwTsec6p6UDQQZCdKTRibjOUNP5946ks7j5ZcuEOsUa44nWGZfqw/640?wx_fmt=png)

这样做的好处在于，NumPy 并不关心 `predictions` 和 `labels` 包含一个值还是一千个值（只要它们大小相同）。我们可以通过一个示例依次执行上面代码行中的四个操作：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibD9u9GOuuxFFdOmdfmwCW0O6rJdeFJNeic5hMj4ib4eh5CtaTqofulupw/640?wx_fmt=png)

预测和标签向量都包含三个值，也就是说 n 的值为 3。减法后，得到的值如下：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibWaRqAcglKGGib6sibLDNVKhlk87VtbOWHY5w2OwYQEQ9icEwZwQsnCXqA/640?wx_fmt=png)

  

然后将向量平方得到：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibfHAJxhGX2FbobpW34MicAmEFm6zGgciceJspbmpXXEVS9cc2O1IjF3rg/640?wx_fmt=png)

现在对这些值求和：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibXhJbiaKxlrmpWn5A6YMKhwiciaFnbFkeqLfX29VacUEicxgJwlq2oXT11g/640?wx_fmt=png)

得到的结果即为该预测的误差值和模型质量评分。

**2. 数据表示**

考虑所有需要处理和构建模型所需的数据类型（电子表格、图像、音频等），其中很多都适合在 n 维数组中表示：

*   **表格和电子表格**
    

电子表格或值表是二维矩阵。电子表格中的每个工作表都可以是它自己的变量。python 中最流行的抽象是 pandas 数据帧，它实际上使用了 NumPy 并在其之上构建。

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib8IMTNiaE3QQY7hiciaz1AiahpXicuEIZ7sxaDrRMzz6YkwwJusnHn36M12Q/640?wx_fmt=png)

*   **音频和时间序列**
    

音频文件是样本的一维数组。每个样本都是一个数字，代表音频信号的一小部分。CD 质量的音频每秒包含 44,100 个样本，每个样本是 - 65535 到 65536 之间的整数。这意味着如果你有一个 10 秒的 CD 质量 WAVE 文件，你可以将它加载到长度为 10 * 44,100 = 441,000 的 NumPy 数组中。如果想要提取音频的第一秒，只需将文件加载到 `audio` 的 NumPy 数组中，然后获取 `audio[:44100]`。

以下是一段音频文件：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxib6smtp2GvJuwMAf7nQdzB867ZPTOo2GDM40jvFccLND1vwDBPBiaL8pA/640?wx_fmt=png)

时间序列数据也是如此（如股票价格随时间变化）。

*   **图像**
    

图像是尺寸（高度 x 宽度）的像素矩阵。

如果图像是黑白（即灰度）的，则每个像素都可以用单个数字表示（通常在 0（黑色）和 255（白色）之间）。想要裁剪图像左上角 10 x 10 的像素吗？在 NumPy 写入 `image[:10,:10]` 即可。

下图是一个图像文件的片段：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibXKuFd2akVDibm2aJ2P1RatBuegibWUTWtX62GJicaeSvn4uRha5cTF11A/640?wx_fmt=png)

如果图像是彩色的，则每个像素由三个数字表示——红色、绿色和蓝色。在这种情况下，我们需要一个三维数组（因为每个单元格只能包含一个数字）。因此彩色图像由尺寸为（高 x 宽 x3）的 ndarray 表示：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibtdltDzcmPvTBicYcJvq6hJiaBM2tepH9dxPAZqGpjlGBFUicDXO5NjVWA/640?wx_fmt=png)

*   **语言**
    

如果我们处理文本，情况就不同了。文本的数字表示需要一个构建词汇表的步骤（模型知道的唯一字清单）和嵌入步骤。让我们看看用数字表示以下文字的步骤：

模型需要先查看大量文本，再用数字表示这位诗人的话语。我们可以让它处理一个小数据集，并用它来构建一个词汇表（71,290 个单词）：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibYDfMIict6s34LBtmVdle1V5jIuJnOpChIkkaMEqRvnYIPLWibxXZo7hQ/640?wx_fmt=png)

这个句子可以被分成一个 token 数组（基于通用规则的单词或单词的一部分）：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibfJNVlnpml2OhBNFr2Qbzl1VEvgo6zCVqgNnNibCyEvqZWhDYe7XPkqw/640?wx_fmt=png)

然后我们用词汇表中的 ID 替换每个单词：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5Crxibak2UMDsR9hIAc5GJpoFib232ZwvQ7ibhB3y0RH9gxPduU6GFgrwHuIPQ/640?wx_fmt=png)

这些 ID 仍然没有为模型提供太多信息价值。因此，在将这一组单词输入到模型之前，我们需要用嵌入替换 token / 单词（在本例中为 50 维 word2vec 嵌入）：

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibssvJ9QoI4uNZ1KkZL0531M8N0bDNlQJNM7aOTjgQgeb0WQj2HvEGLA/640?wx_fmt=png)

可以看到，该 NumPy 数组的维度为 [embedding_dimension x sequence_length]。出于性能原因，深度学习模型倾向于保留批大小的第一维（因为如果并行训练多个示例，模型训练速度会加快）。在这种情况下，`reshape()` 变得非常有用。如像 BERT 这样的模型期望的输入形式是：[batch_size，sequence_length，embedding_size]。

![](https://mmbiz.qpic.cn/mmbiz_png/KmXPKA19gWibyia4UqdvqQFPEEgdu5CrxibPxibWWSRJW1CGvSlIAPiahDGIVBHV6teW9TR9gWNNnOSEhFuWJHSKIcw/640?wx_fmt=png)

现在这是 numeric volume 形式，模型可以处理并执行相应操作。其他行虽然留空，但是它们会被填充其他示例以供模型训练（或预测）。

原文链接：https://jalammar.github.io/visual-numpy/

![](https://mmbiz.qpic.cn/mmbiz/cZV2hRpuAPiaJQXWGyC9wrUzIicibgXayrgibTYarT3A1yzttbtaO0JlV21wMqroGYT3QtPq2C7HMYsvicSB2p7dTBg/640?)

****有话要说👇****

**Q:** **你用 NumPy 的哪些神操作？**

欢迎留言与大家分享

**猜你想看👇**

*   [最近都在谈的「私域流量」，究竟有没有前途？](http://mp.weixin.qq.com/s?__biz=MjM5ODE1NDYyMA==&mid=2653393529&idx=3&sn=64d9e211717fb3f1fb14e97b47cb0b2d&chksm=bd1c2a6a8a6ba37cba5449f3dcc926fc44623a67de74f3cb3eb14f3f5a2d2aca01f06061ac5c&scene=21#wechat_redirect)  
    
*   [一文读懂「中台」的前世今生](http://mp.weixin.qq.com/s?__biz=MjM5ODE1NDYyMA==&mid=2653393521&idx=1&sn=8d10b6335d2d7ed0a27cbbddc1d0c36b&chksm=bd1c2a628a6ba37486ff57d097dd807c1ab9cc3015133eb94260e7ccf16d7e312731420a867c&scene=21#wechat_redirect)  
    
*   [「最有用」的特殊大数据：一文看懂文本信息系统的概念框架及功能](http://mp.weixin.qq.com/s?__biz=MjM5ODE1NDYyMA==&mid=2653393463&idx=2&sn=5c0a9e0ff33c034ac1fcdc57225e26e2&chksm=bd1c2a248a6ba332c55a7fbb6f56781ff0d40378febc31dd300272e6aa0c45c7b516d3fd3e54&scene=21#wechat_redirect)  
    
*   [作品拍卖价碾压毕加索，没有灵魂的 AI 灵魂画手有怎样的未来？](http://mp.weixin.qq.com/s?__biz=MjM5ODE1NDYyMA==&mid=2653393421&idx=2&sn=9a740d39bd9377e87f07a5013607feed&chksm=bd1c2a1e8a6ba308d84599c021b4adca6f13f84bebf87fabab51409a41f30d455ea430a9af4d&scene=21#wechat_redirect)
    

**更多精彩👇**

在公众号对话框输入以下**关键词**

查看更多优质内容！

**PPT** | **报告** | **读书** | **书单** | **干货**

**大数据** | **揭秘** | **Python** | **可视化**

**AI** | **人工智能** | **5G** | **区块链**

**机器学习** | **深度学习** | **神经网络**

**合伙人**| **1024** | **段子** | **数学** | **高考**

据统计，99% 的大咖都完成了这个神操作

**👇**

![](https://mmbiz.qpic.cn/mmbiz_png/KfLGF0ibu6cJmXkAITIpknZ8zcoh12627WuIN0F7w9mpOTDJkO1lU1flVPjb4NghlSK1h0NNl94SDR5sz2lzJBw/640?wx_fmt=png)

觉得不错，请把这篇文章分享给你的朋友  

转载 / 投稿请联系：baiyu@hzbook.com

更多精彩，请在后台点击 “历史文章” 查看

![](https://mmbiz.qpic.cn/mmbiz_jpg/KfLGF0ibu6cI6yiax4muuJvS6ywtkAXeVBHj0fiaQ9zOzAvFaEdy6LFmm6SCQSRMcvDu0gJCZlbIOC3gnnKXctkicg/640?wx_fmt=jpeg)