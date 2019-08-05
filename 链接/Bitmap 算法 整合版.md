> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191272&idx=1&sn=9bbcd172b611b455ebfc4b7fb9a6a55e&chksm=8c990eb2bbee87a486c55572a36c577a48df395e13e74314846d221cbcfd364d44c280250234&scene=21#wechat_redirect

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkBHjyicKWZvvLdFNWto7mQia9SaxuI04VRMI1ia4XHdMhDU3uQrd5XkT2w/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkNt29adlLPkZ4jLQAWntRnaicpFp6rfgHHhibOr2I62vBAQFuaDID6XIg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkB1fLsfXpfzfQyBWiajbGnrnh6YQC8hJ5udseKDaKMAzAgicH0bib0xxNA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkKS0yNuI6bUoZkkMlmRDobC5QR77ian7n9fsEafCfEjYlwg6xtxic7Cfw/0?wx_fmt=jpeg)

两个月之前——

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIk91cnuPmXkpL5gypnPpEUJ2753RUic1pC8gdiaBb0Zq2mfxay3VwaRSpg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkS891yvzsy8shSzLZP1sia5r5jCR3KzGybicRGQEzMUeEB9T640F5JOpQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIk9oQU9YKcz0Gx9NfULwRZice2CVPbia36oQ53QfhYy5lumTCIBBY8QLgg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkkU1bJQPs3NdSXeTicG2lNDdLd51rKWqb8rMNzbadiaiavgFw9hyodqyOA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpxwEYlAPH34EyFRxRnOlIkooz1sDzCia1eq37Sh65ePOUK2Gy2gFnIQicr4qERRc2o0oqFiciaNgT5tA/0?wx_fmt=jpeg)

为满足用户标签的统计需求，小灰利用 Mysql 设计了如下的表结构，每一个维度的标签都对应着 Mysql 表的一列：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8tYpEKTGWwDZAoCvibC0hacic1yBdyiaeEDtPrb3R6oT5AwgwA7CLWr79A/0?wx_fmt=jpeg)

要想统计所有 90 后的程序员该怎么做呢？

用一条求交集的 SQL 语句即可：  

**Select count（distinct Name） as 用户数 from table whare age = '90 后' and Occupation = '程序员' ;**  

要想统计所有使用苹果手机或者 00 后的用户总合该怎么做？

用一条求并集的 SQL 语句即可：  

**Select count（distinct Name） as 用户数 from table whare Phone = '苹果' or age = '00 后' ;**

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrialib9rwmUnoiazNvLZ68GJOFKHnV1OyjrkhhQ8z2EZuo7wYicpEF9FtIHcxE8DGA5eowew7Y88pNBA/0?wx_fmt=jpeg)

两个月之后——

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8Meib9r3flp7cYp0UqnicVXGbZl7DHYfvIHt4vgtceBCqsCiatONstOIOw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R81BeA0BWEics5hN1vNOrIibxevZFHpYj3YOUEQomPDeWo2wcMhticgxYhg/0?wx_fmt=jpeg)  

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8ISSc8hSgugemKTzsOlFEWZicyjIgVoiaiags6sSY5RRFdevh9qsYhVmFg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8c3dVcPsyRmbm0xGtmrQ6Mao18o6WIs6egtvBcBbXO4opgPzXsBeibYw/0?wx_fmt=jpeg)

———————————————

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8xroW503xgCamOlAeQjd9JyaJic4T5qp9ss9kNDY7CjF7kcwbboDu5Rg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8rLHCktuFibd3h2icE3YRQwibbqO9voh7aVMyNRRLLu9m3tPzYEuj0eTIQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R88eCunLb2zI1eFhStmTlUxxdDDWLtGDiaesYWybw2jY8JbWPdicdBXdhg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8ta9AunX1AcJIiaE6YS5e7kzciaKcNM5gWvNibGLibSpNUQBhfg1gKwut7Q/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8p7Gkngme9MmNyGdfdj8MOTrWBt4xQORWniar3bn4M7R1bcvMLF4VDcw/0?wx_fmt=jpeg)

1. 给定长度是 10 的 bitmap，每一个 bit 位分别对应着从 0 到 9 的 10 个整型数。此时 bitmap 的所有位都是 0。  

    ![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8Ze6icx7L1WklSjhgiclRnhxIjiaROmzyqDztswwEpUa0MI9JOgxUCC99A/0?wx_fmt=png)

2. 把整型数 4 存入 bitmap，对应存储的位置就是下标为 4 的位置，将此 bit 置为 1。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8TYV4UzFeLjGouPFLKv0tf4x3oFPCYDf7P4qxFib44sF6Rr9pQfRsEvQ/0?wx_fmt=png)

3. 把整型数 2 存入 bitmap，对应存储的位置就是下标为 2 的位置，将此 bit 置为 1。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8GulRqPoiaBLfz8Wpfich1fQLiaabsjibrZ7kvTh3HI1bU7nTEF4WRicGc0Q/0?wx_fmt=png)

4. 把整型数 1 存入 bitmap，对应存储的位置就是下标为 1 的位置，将此 bit 置为 1。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R87hV9ILWEa1HxqUU3DOtQCxh7KBf8Vbvqxwo1RSqo13gCYRyY5YGxRQ/0?wx_fmt=png)

5. 把整型数 3 存入 bitmap，对应存储的位置就是下标为 3 的位置，将此 bit 置为 1。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R89ibzAqhibG2icfcsTibdO7SsfriaRdYgT1JI8GhSxxiaTxahaibjj1ibtMvtiaw/0?wx_fmt=png)

要问此时 bitmap 里存储了哪些元素？显然是 4,3,2,1，一目了然。

Bitmap 不仅方便查询，还可以去除掉重复的整型数。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8Ksia80HrHLTRqnOAHSSyj6GWhmEA9ybibK09fHOXExIczV8X5tl5RrSg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8qXDZ3sYMTh3LiaibxEGfe4GnGOcjPaDFFUlnUswMn7xDTOutpiatKH6mQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8bWgnicMp33mDEaDvprIsJ2Hnibqpt3K7F6kO52mJJVJeRPibia46m3U3vQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8jgf4slIBtYeIm80IBVNwoszodflMzyvTMDTl2VbMx9JRK59IAOgm6w/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8K4ZqPBpgs1q76h8n0oxc9EG1vvEgatzNYjWGp7ibNx2H9Vc550PE6Bw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8FxMLqDfwicpMwVGIPPkaH5WYUCSicOyicDmQhVHnV311FrjUWfdTMEictQ/0?wx_fmt=jpeg)

1. 建立用户名和用户 ID 的映射：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8Xd5VB17RDX8kxibAFRyJKEwxBKltbibNLmibsBmh2JzKibXNv8uIrA8BqQ/0?wx_fmt=png)

2. 让每一个标签存储包含此标签的所有用户 ID，每一个标签都是一个独立的 Bitmap。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8lKWgcehrkBuvqDc36Utnk1G0YPc96hLqH07O5RR85Rbn4657CU0Lfg/0?wx_fmt=png)

3. 这样，实现用户的去重和查询统计，就变得一目了然：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8rPmoIBL7u7zeOELWW3qyyB8qJvHavlgtv0sNUNToWUz2KJCicopw3Mw/0?wx_fmt=png)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R88KwrsuXaDSSYhUPfuWnbsL2dHQAeC9iaM1cYH26E9lF5lK2fpXWs4Eg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8ib9yxiaOicboy0NeniautjppllOD0p6JJUfFKib3Wq4z4Pp1JPAic0u9d2OA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R85wRFR9w9CalaBwbv8NxoJ1HencRgggWKgJbPYJSBxydibic8NS8wtqXg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R872aIiaVvlHWr2QibwXCsdgPqTjL0f4r8Egebd2l70w2zrGryiafwla47A/0?wx_fmt=jpeg)

1. 如何查找使用苹果手机的程序员用户？

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpk4yr81iaVE41KpY1I8nRzaNClq8xz6WaAibNd5QMuRz84gkiaqdZAEacRsn1wqxJfl0kbX4gZ176Ng/0?wx_fmt=png)

2. 如何查找所有男性或者 00 后的用户？

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrialib9rwmUnoiazNvLZ68GJO8LUEefVq9plI7J9XPZArY3kmQ9rzpbP3x4jvGXI14CTLhPiaic7TI2EQ/0?wx_fmt=png)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8jJImp5SyxWa9uGrIP4ZdvY4zhib7FutyXzAzGdicGq1lsnDPWUibmWBrw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8Ej9eeJUrSzcx2ABtCjUWZOR3ls8Wrr3ZueJI3JbdvXzrqqBHrFrI1Q/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8NCMyicpRraPGuPUoA7uqxzfWCGCYchtE929R0fjltqBJWmFDwdibicRBw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8FYY4icHgDvzY7I43Z2u45VD9a4Wn4Cia2wAX31j1LTXqTqpsmZ4xaLpA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8q0IP7NY0Rp8NDeeaBkOXgOXHiaJVcb4pPE0Fkjan6QpRIYZaBKlGdPQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8vwqva08sLaDicgiaQsHhtiaRpIkyEFCfVSnuyAyILELPWUV2LfrBgl8cA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGr3J970kQ7RPlua1wBUcI3QNDeTzzmia5lPPsg6sHlShMSkqDKJkqMuNRltibjMDInG5vMQCVHiacNdA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoJyK40iarFL0JB0ReyeO5R8VX5GfPS7ibowsGIe6DDkN60ZnwIPicCg8wQDWQ8QIibgxsVmMqIIg6ZJw/0?wx_fmt=jpeg)

一周之后......

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrBicrlyPEgCeNGVxLveJctcoc9nScY5CKMA39w4m6rs4yEbcic1k2DIlbsW1wL1nH2s700Et7A3aoA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrBicrlyPEgCeNGVxLveJctcJJ6oq8yfIAK6icNPsiao1LSoQXSpZS6vuZD0cSASbxp2m6tYvbgh3GpA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrBicrlyPEgCeNGVxLveJctcaPzYNXVGS1p9VibLgEbkm7FcOiaBBoQ60KELS7r95GDsJehFEyGpWwBA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrBicrlyPEgCeNGVxLveJctchrZLCUQVBVYshPIkFYFoqUbb4YjibDhuAIdAUS4EpEk7X8fyqiauTib4A/0?wx_fmt=jpeg)

我们以上一期的用户数据为例，用户基本信息如下。按照年龄标签，可以划分成 90 后、00 后两个 Bitmap：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyEWGIlkBej9iaRqCKibQbLMT5RgPjmCicursEGibrSL9f5XahicYExrHkVzA/0?wx_fmt=png)

用更加形象的表示，90 后用户的 Bitmap 如下：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyMchf6L7Xq6fLDtK3IXJNYHC7wxz7Yz11BuRlcswiauiaUJFglIL0tKtg/0?wx_fmt=png)

这时候可以直接求得**非** 90 后的用户吗？直接进行非运算？  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITycNP4sxHp9c8aKmhUe4gfibH2BHHjlcdOO2KpBdTtFolAFT8EC4r9l0w/0?wx_fmt=png)

显然，非 90 后用户实际上只有 1 个，而不是图中得到的 8 个结果，所以不能直接进行非运算。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITy70BhuQMK9t4f9dqviccL6UAPGrbF0HmuAsY6Bj1HDaMQG2qzBHBWOuA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITy3tJAw61ez1lREkiaRIriaKPL44zWYp4ibqc5LMgkV4eVQSaJYqicibibEMaw/0?wx_fmt=jpeg)

同样是刚才的例子，我们给定 90 后用户的 Bitmap，再给定一个全量用户的 Bitmap。最终要求出的是存在于全量用户，但又不存在于 90 后用户的部分。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyicILFvkHbNic5sWlXzLPGgPQJQQc6tD6yGiauHicwAzRL3cwnsaRoZp6Kw/0?wx_fmt=png)

如何求出呢？我们可以使用**异或**操作，即相同位为 0，不同位为 1。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyuAINjbw6Xt7uM2NjHxfIjYeJg5gMicFibm7Gscnr0VfR2qUuwtfRsHgw/0?wx_fmt=png)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyiaBDNkGjwRtuiapKSwtV9bfWjJOIk7Ednv5u1zrbicmy757POhn76Cu4w/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGo9WQ0N4Sic4Oic2YpzSBRITyBR8eFfMObicUicvTXvVXFUQn8Xo9nJeC4Hdce1KoHAgIfKhChKNnwgqA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGD1oj8CGufsmic2S4lMGSibZcbfkzpac8WY9P678AoJsVXjA5BqEWSVyA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGH24EZhNMugiaf6T5vnkMtV6ALsSpolzN3JQsIwgH1MhaJxpmbIAKKqA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGsGIvPfoMlYibxJ8BAxc5QlAtqfuWtia4zQJ1vrekwFicbqkPsGKicA4Eow/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpNNYfhWEaicpw8aPePt52VG13RUeffR3RqknwrUyicLMwW8Rrdr17zQsVGzcyl4usUn34hYq5Vgdmg/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGibtEcbRS7VPc04ESvuZFG5lvrGKPxiaUeWFm64Q5j2tzutNjWqC5bDxQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGmibjavI6vNmziaMWqdxZPcUAhbW9FPIicA5yy8ibnIGs4UztUcZkKbuqgA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGsP7V3SiaOxowxMoQkVZ3kvdoDnVQ3iaiah1q05KncrbksiasicKq3EGox8g/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGemsPXFA4niaLc0UVynCHFgSxRKZQy88akXiafcYvomgjp7Hcy6xXkkIw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGhYWxKibRlBBNLO7ZrXyu1dQiaPUvFrKtV6U2pWz8OO9yJzNxfK3J8vhw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpNNYfhWEaicpw8aPePt52VGkWlSMlONniaPm8yhDFkzuyuEjVpncXPhl0JpjdPribjVbvCNhn3aagyg/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliajPNgTUmxtcPWx5miaZfbwia8dRVoTnGZCllu0swUxegk8zUVeVCHToRw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaIACApWrs2nEMTR9ibbkoK0x2Xbcn4kCtzsTYghia7N0tHacuoibP6m5aQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaI3X8ic3g9btIbm8nWaLWz99LRt8StrbtAINcELlcicbibDqFInibG0UPEw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaJGcUdel2athHIUCSmNhG718ud9HNPxtiaSpQI58dvyhfAe1Cia5LYFxw/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaz5n1obZ4SLrnegJYmf4nYAgFEjCT0FZQA7pOqZphUEvUnd9SGTfJuA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaLFvD4e5kvHf7ub3BMia8f15j9ibkvYolgCcdpfZ4CTY7Ge7SKJNI5NdQ/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliabppGQSbunpRaPZ16OITNicEfXLOZZibyFLv3T4CHUicU5CB1iasjrHkkwg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliayLG4vHR7fP5a2qoiaxLdQNV6aaoN1qJtB38th1DCXPv7ljxyNkqxsmg/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaTTtN4ncx6gPl3JaYhdeOtT5lEOyBlExkboSVzwIlhvx4DD85RBvraw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaeh6M8jstCia4yTXH1VMZz59AK9ibSP351Vc8XMPOTXjaO0B1gCMP1wgQ/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaHqAZaOC09ibHxiaNoESjHgj4BQtrzZjAvDD5Kz0jbSVYXcbRQRnic1LNw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaJKlucBGgZXkekDcibQ22rhnV369y2S9Lx5iajRfYOoe964nq4lBprCmg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliarrH0qJzduKdulUuZIXn2QXOsobiaxTQ1cJDSC9ibCHqyW9ALUMtDYjwA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplianIcbAFMIQCclrBKicCibd1W9bx6icRVgZr4ODj07jrSRqjRbRyeBuMnsA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaJ1aFVrrrxCEkDtOMj1fDCQBPZByfK4JJyKUwkEONMry5wWoNsBvtvg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaAmoU8qy9coWCBVPDWSPuuSNrzJw2JDkvuiah6C6Pia0CmYnlmzYHrdxA/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaRYvPF0rHOZx2ibv28vSbEYZrY3bP5TcxKBlPCn6ngO0B4uuKgyM2MDg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia5dOpJDb0pAXkv3GZYoGia7LHSicwm83M5xzyQ97SBd8NJ1UiaPRwmhKSQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia98ia7OCMz96EibDNwaxBPxibTYeDyia7hSHwNesmVLz9hyNGvKy36ykLzg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia1Le5sG3EM7QfeT5VVl0dvZAkyw0vML5tlzlc32icYLaQlG0lr83wfxw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaeHG0QqAwUMcxthj1rbCOusUYE19vB0K3UAm7zMudXjDGaCKaBo8Gmw/0?wx_fmt=png)

25769803776L  =  11000000000000000000000000000000000B

8589947086L = 1000000000000000000011000011001110B

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia306jqJzHmYdrUl5gLx5qFoOZOpnGjUUSR13Xkl7JKibdXWnDicgxt43A/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaKkSRcgjdiaeX5v7pjlN9sJJzef7vib6wkn1NDibSF3zd7ic318tcI55b9w/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaVPypVhU9B57P89Gv5ibiaOzJWD9O8omQvwx5QYV4txLWHNC4Aia9uBFmA/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliavIm7GKtFIebpMVdcUKLmlnIJ8GUiaK8TYgbcWZuRRna5m9nQFN4znhA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaXAKS6TEibq7Fb1bHfCypDsDE2M9sn2EW6qbvuGmfvtE4libXOl2IDWHQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaqaDia3LVM6IvZe1se885UD1KQ0N6ksHiaLtzkkOzHYpH8LnpZtQvWhnA/0?wx_fmt=jpeg)

1. 解析 Word0，得知当前 RLW 横跨的空 Word 数量为 0，后面有连续 3 个普通 Word。  

2. 计算出当前 RLW 后方连续普通 Word 的最大 ID 是  64 X  (0 + 3) -1 = 191。

3. 由于 191 < 400003，所以新 ID 必然在下一个 RLW（Word4）之后。

4. 解析 Word4，得知当前 RLW 横跨的空 Word 数量为 6247，后面有连续 1 个普通 Word。

5. 计算出当前 RLW（Word4）后方连续普通 Word 的最大 ID 是 191 + （6247 + 1）X64  = 400063。

6. 由于 400003 < 400063，因此新 ID 400003 的正确位置就在当前 RLW（Word4）的后方普通 Word，也就是 Word5 当中。

最终插入结果如下：  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaooia0IZZLRvkvdZxhqPIMYor7xLxeF3GOFzFr227ehCVL1ysDNicxTYg/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaCYsSZZz7mp6Ql5MKJJ1jeXd1naYZRciaVd7BXWLN00CgguXfaysEnDQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia5wibupcenyq9qPhjLxqIoejj9icdbJ7ibAjwPGjv6AibM856OmfV1LticnA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp9iaqMfWklZnozc7kibmWplia5Y4XuflKiaDwXMCLcfJbkZdibub5IfOmes7SXemhVQIVjUt3oFfgL99A/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaQ8icQbEQVENwiczjiapSQtQBa8UHnxxxwYjfF6jbjiczSa26MwHekD9ibMQ/0?wx_fmt=jpeg)

官方说明如下：

```
* Though you can set the bits in any order (e.g., set(100), set(10), set(1),
* you will typically get better performance if you set the bits in increasing order (e.g., set(1), set(10), set(100)).
* 
* Setting a bit that is larger than any of the current set bit
* is a constant time operation. Setting a bit that is smaller than an 
* already set bit can require time proportional to the compressed
* size of the bitmap, as the bitmap may need to be rewritten.




```

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp9iaqMfWklZnozc7kibmWpliaoOk4lT4DvdOepaBPwZ1ntl8rl61C3JfzYibOMPGMXQPPiaRv4efLIbBg/0?wx_fmt=jpeg)

**几点说明：**

1. 该项目最初的技术选型并非 Mysql，而是内存数据库 hana。本文为了便于理解，把最初的存储方案写成了 Mysq 数据库。

1. 文中介绍的 Bitmap 优化方法在一定程度上做了简化，源码中的逻辑要复杂得多。比如对于插入数据 400003 的定位，和实际步骤是有出入的。

2. 如果同学们有兴趣，可以亲自去阅读源码，甚至是尝试实现自己的 Bitmap 算法。虽然要花不少时间，但这确实是一种很好的学习方法。

```
EWAHCompressedBitmap对应的maven依赖如下：



```

```
<dependency>
  <groupId>com.googlecode.javaewah</groupId>
  <artifactId>JavaEWAH</artifactId>
  <version>1.1.0</version>
</dependency>

```

—————END—————

喜欢本文的朋友们，欢迎长按下图关注订阅号**梦见**，收看更多精彩内容

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoBj18gILw2hefgpNaCia1eRhNCzRx29e1DpVhicyenCic4RQibDTbzySoqqpOrmBxu7KlLZM73YDDPJg/0?wx_fmt=jpeg)