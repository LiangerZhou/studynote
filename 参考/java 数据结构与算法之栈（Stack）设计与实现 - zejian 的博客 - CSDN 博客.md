> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/javazejian/article/details/53362993 [](http://creativecommons.org/licenses/by-sa/4.0/)版权声明：本文为博主原创文章，遵循 [CC 4.0 by-sa](http://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接和本声明。 本文链接：[https://blog.csdn.net/javazejian/article/details/53362993](https://blog.csdn.net/javazejian/article/details/53362993)

> 【版权申明】转载请注明出处（请尊重原创，博主保留追究权）  
> [http://blog.csdn.net/javazejian/article/details/53362993](http://blog.csdn.net/javazejian/article/details/53362993)  
> 出自[【zejian 的博客】](http://blog.csdn.net/javazejian)

关联文章:

[java 数据结构与算法之顺序表与链表设计与实现分析](http://blog.csdn.net/javazejian/article/details/52953190)  
[java 数据结构与算法之双链表设计与实现](http://blog.csdn.net/javazejian/article/details/53047590)  
[java 数据结构与算法之改良顺序表与双链表类似 ArrayList 和 LinkedList（带 Iterator 迭代器与 fast-fail 机制）](http://blog.csdn.net/javazejian/article/details/53073995)  
[java 数据结构与算法之栈（Stack）设计与实现](http://blog.csdn.net/javazejian/article/details/53362993)  
[java 数据结构与算法之队列 (Queue) 设计与实现](http://blog.csdn.net/javazejian/article/details/53375004)  
[java 数据结构与算法之递归思维 (让我们更通俗地理解递归)](http://blog.csdn.net/javazejian/article/details/53452971)  
[java 数据结构与算法之树基本概念及二叉树（BinaryTree）的设计与实现](http://blog.csdn.net/javazejian/article/details/53727333)  
[java 数据结构与算法之平衡二叉查找树 (AVL 树) 的设计与实现](http://blog.csdn.net/javazejian/article/details/53892797)

  本篇是 java 数据结构与算法的第 4 篇，从本篇开始我们将来了解栈的设计与实现，以下是本篇的相关知识点：

*   [栈的抽象数据类型](#栈的抽象数据类型)
*   [顺序栈的设计与实现](#顺序栈的设计与实现)
*   [链式栈的设计与实现](#链式栈的设计与实现)
*   [栈的应用](#栈的应用)

栈的抽象数据类型
========

  栈是一种用于存储数据的简单数据结构，有点类似链表或者顺序表（统称线性表），栈与线性表的最大区别是数据的存取的操作，我们可以这样认为栈 (Stack) 是一种特殊的线性表，其插入和删除操作只允许在线性表的一端进行，一般而言，把允许操作的一端称为栈顶(Top)，不可操作的一端称为栈底(Bottom)，同时把插入元素的操作称为入栈(Push), 删除元素的操作称为出栈(Pop)。若栈中没有任何元素，则称为空栈，栈的结构如下图：  
![](https://img-blog.csdn.net/20161127093301536)  
  由图我们可看成栈只能从栈顶存取元素，同时先进入的元素反而是后出，而栈顶永远指向栈内最顶部的元素。到此可以给出栈的正式定义：栈 (Stack) 是一种有序特殊的线性表，只能在表的一端 (称为栈顶，top，总是指向栈顶元素) 执行插入和删除操作，最后插入的元素将第一个被删除，因此栈也称为后进先出 (Last In First Out,LIFO) 或先进后出 (First In Last Out FILO) 的线性表。栈的基本操作创建栈，判空，入栈，出栈，获取栈顶元素等，注意栈不支持对指定位置进行删除，插入，其接口 Stack 声明如下：

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
package com.zejian.structures.Stack;

/**
* Created by zejian on 2016/11/27.
* Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
* 栈接口抽象数据类型
*/
public interface Stack<T> {

   /**
    * 栈是否为空
    * @return
    */
   boolean isEmpty();

   /**
    * data元素入栈
    * @param data
    */
   void push(T data);

   /**
    * 返回栈顶元素,未出栈
    * @return
    */
   T peek();

   /**
    * 出栈,返回栈顶元素,同时从栈中移除该元素
    * @return
    */
   T pop();
}

```

顺序栈的设计与实现
=========

  顺序栈，顾名思义就是采用顺序表实现的的栈，顺序栈的内部以顺序表为基础，实现对元素的存取操作，当然我们还可以采用内部数组实现顺序栈，在这里我们使用内部数据组来实现栈，至于以顺序表作为基础的栈实现，将以源码提供。这里先声明一个顺序栈其代码如下，实现 Stack 和 Serializable 接口：

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
 * 顺序栈的实现
 */
public class SeqStack<T> implements Stack<T>,Serializable {

    private static final long serialVersionUID = -5413303117698554397L;

    /**
     * 栈顶指针,-1代表空栈
     */
    private int top=-1;

    /**
     * 容量大小默认为10
     */
    private int capacity=10;

    /**
     * 存放元素的数组
     */
    private T[] array;

    private int size;

    public SeqStack(int capacity){
        array = (T[]) new Object[capacity];
    }

    public SeqStack(){
        array= (T[]) new Object[this.capacity];
    }
    //.......省略其他代码
}

```

其获取栈顶元素值的 peek 操作过程如下图（未删除只获取值）：  
![](https://img-blog.csdn.net/20161127154514508)

代码如下：

```
1
2
3
4
5
6
7
8
9
10
/**
  * 获取栈顶元素的值,不删除
  * @return
  */
 @Override
 public T peek() {
     if(isEmpty())
         new EmptyStackException();
     return array[top];
 }

```

从栈添加元素的过程如下（更新栈顶 top 指向）：  
![](https://img-blog.csdn.net/20161127154556321)

代码如下：

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
/**
 * 添加元素,从栈顶(数组尾部)插入
 * 容量不足时，需要扩容
 * @param data
 */
@Override
public void push(T data) {
    //判断容量是否充足
    if(array.length==size)
        ensureCapacity(size*2+1);//扩容

    //从栈顶添加元素
    array[++top]=data;
    }

```

栈弹出栈顶元素的过程如下（删除并获取值）：  
![](https://img-blog.csdn.net/20161127154539415)

代码如下：

```
1
2
3
4
5
6
7
8
9
10
11
/**
  * 从栈顶(顺序表尾部)删除
  * @return
  */
 @Override
 public T pop() {
     if(isEmpty())
         new EmptyStackException();
     size--;
     return array[top--];
 }

```

到此，顺序栈的主要操作已实现完，是不是发现很简单，确实如此，栈的主要操作就这样，当然我们也可以通过前一篇介绍的 MyArrayList 作为基础来实现顺序栈，这个也比较简单，后面也会提供带代码，这里就不过多啰嗦了。下面给出顺序栈的整体实现代码：

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
package com.zejian.structures.Stack;

import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
 * 顺序栈的实现
 */
public class SeqStack<T> implements Stack<T>,Serializable {

    private static final long serialVersionUID = -5413303117698554397L;

    /**
     * 栈顶指针,-1代表空栈
     */
    private int top=-1;

    /**
     * 容量大小默认为10
     */
    private int capacity=10;

    /**
     * 存放元素的数组
     */
    private T[] array;

    private int size;

    public SeqStack(int capacity){
        array = (T[]) new Object[capacity];
    }

    public SeqStack(){
        array= (T[]) new Object[this.capacity];
    }

    public  int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return this.top==-1;
    }

    /**
     * 添加元素,从栈顶(数组尾部)插入
     * @param data
     */
    @Override
    public void push(T data) {
        //判断容量是否充足
        if(array.length==size)
            ensureCapacity(size*2+1);//扩容

        //从栈顶添加元素
        array[++top]=data;

        size++;
    }

    /**
     * 获取栈顶元素的值,不删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty())
            new EmptyStackException();
        return array[top];
    }

    /**
     * 从栈顶(顺序表尾部)删除
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty())
            new EmptyStackException();
        size--;
        return array[top--];
    }

    /**
     * 扩容的方法
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity<size)
            return;

        T[] old = array;
        array = (T[]) new Object[capacity];
        //复制元素
        for (int i=0; i<size ; i++)
            array[i]=old[i];
    }

    public static void main(String[] args){
        SeqStack<String> s=new SeqStack<>();
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println("size->"+s.size());
        int l=s.size();//size 在减少,必须先记录
        for (int i=0;i<l;i++){
            System.out.println("s.pop->"+s.pop());
        }

        System.out.println("s.peek->"+s.peek());
    }
}

```

链式栈的设计与实现
=========

  了解完顺序栈，我们接着来看看链式栈，所谓的链式栈（Linked Stack），就是采用链式存储结构的栈，由于我们操作的是栈顶一端，因此这里采用单链表（不带头结点）作为基础，直接实现栈的添加，获取，删除等主要操作即可。其操作过程如下图：  
![](https://img-blog.csdn.net/20161127171742066)  
从图可以看出，无论是插入还是删除直接操作的是链表头部也就是栈顶元素，因此我们只需要使用不带头结点的单链表即可。代码实现如下，比较简单，不过多分析了：

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
package com.zejian.structures.Stack;

import com.zejian.structures.LinkedList.singleLinked.Node;

import java.io.Serializable;

/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
 * 栈的链式实现
 */
public class LinkedStack<T> implements Stack<T> ,Serializable{

    private static final long serialVersionUID = 1911829302658328353L;

    private Node<T> top;

    private int size;

    public LinkedStack(){
        this.top=new Node<>();
    }

    public int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return top==null || top.data==null;
    }

    @Override
    public void push(T data) {
        if (data==null){
            throw new StackException("data can\'t be null");
        }
        if(this.top==null){//调用pop()后top可能为null
            this.top=new Node<>(data);
        }else if(this.top.data==null){
            this.top.data=data;
        }else {
           Node<T> p=new Node<>(data,this.top);
            top=p;//更新栈顶
        }
        size++;
    }

    @Override
    public T peek()  {
        if(isEmpty()){
            throw new EmptyStackException("Stack empty");
        }

        return top.data;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException("Stack empty");
        }

        T data=top.data;
        top=top.next;
        size--;
        return data;
    }
    //测试
    public static void main(String[] args){
        LinkedStack<String> sl=new LinkedStack<>();
        sl.push("A");
        sl.push("B");
        sl.push("C");
        int length=sl.size();
        for (int i = 0; i < length; i++) {
            System.out.println("sl.pop->"+sl.pop());
        }
    }
}

```

最后我们来看看顺序栈与链式栈中各个操作的算法复杂度（时间和空间）对比，顺序栈复杂度如下：

| 操作 | 时间复杂度 |
| --- | --- |
| SeqStack 空间复杂度 (用于 N 次 push) | O(n) |
| push() 时间复杂度 | O(1) |
| pop() 时间复杂度 | O(1) |
| peek() 时间复杂度 | O(1) |
| isEmpty() 时间复杂度 | O(1) |

链式栈复杂度如下：

| 操作 | 时间复杂度 |
| --- | --- |
| SeqStack 空间复杂度创建 (用于 N 次 push) | O(n) |
| push() 时间复杂度 | O(1) |
| pop() 时间复杂度 | O(1) |
| peek() 时间复杂度 | O(1) |
| isEmpty() 时间复杂度 | O(1) |

由此可知栈的主要操作都可以在常数时间内完成，这主要是因为栈只对一端进行操作，而且操作的只是栈顶元素。

栈的应用
====

栈是一种很重要的数据结构，在计算机中有着很广泛的应用，如下一些操作都应用到了栈。

*   符号匹配
*   中缀表达式转换为后缀表达式
*   计算后缀表达式
*   实现函数的嵌套调用
*   HTML 和 XML 文件中的标签匹配
*   网页浏览器中已访问页面的历史记录

接下来我们分别对符合匹配，中缀表达式转换为后缀表达式进行简单的分析，以加深我们对栈的理解。

*   符号匹配  
    在编写程序的过程中，我们经常会遇到诸如圆括号 “()” 与花括号 “{}”，这些符号都必须是左右匹配的，这就是我们所说的符合匹配类型，当然符合不仅需要个数相等，而且需要先左后右的依次出现，否则就不符合匹配规则，如“)(”，明显是错误的匹配，而“()” 才是正确的匹配。有时候符合如括号还会嵌套出现，如 “9-(5+(5+1))”, 而嵌套的匹配原则是一个右括号与其前面最近的一个括号匹配，事实上编译器帮我检查语法错误是也是执行一样的匹配原理，而这一系列操作都需要借助栈来完成，接下来我们使用栈来实现括号”()” 是否匹配的检测。  
    判断原则如下（str=”((5-3)*8-2)”）：
    
    *   a. 设置 str 是一个表达式字符串，从左到右依次对字符串 str 中的每个字符 char 进行语法检测，如果 char 是，左括号则入栈，如果 char 是右括号则出栈 (有一对匹配就可以去匹配一个左括号，因此可以出栈)，若此时出栈的字符 char 为左括号，则说明这一对括号匹配正常，如果此时栈为空或者出栈字符不为左括号，则表示缺少与 char 匹配的左括号，即目前不完整。
    *   b. 重复执行 a 操作，直到 str 检测结束，如果此时栈为空，则全部括号匹配，如果栈中还有左括号，是说明缺少右括号。
    
    整个检测算法的执行流程如下图：  
    ![](https://img-blog.csdn.net/20161127183931473)  
    接着我们用栈作为存储容器通过代码来实现这个过程，代码比较简单，如下：
    
    ```
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    24
    25
    26
    27
    28
    29
    30
    31
    32
    33
    34
    35
    36
    37
    38
    39
    40
    package com.zejian.structures.Stack;
    
    /**
    * Created by zejian on 2016/11/27.
    * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
    * 表达式检测
    */
    public class CheckExpression {
    
      public static String isValid(String expstr)
      {
          //创建栈
          LinkedStack<String> stack = new LinkedStack<>();
    
          int i=0;
          while(i<expstr.length())
          {
              char ch=expstr.charAt(i);
              i++;
              switch(ch)
              {
                  case '(': stack.push(ch+"");//左括号直接入栈
                      break;
                  case ')': if (stack.isEmpty() || !stack.pop().equals("(")) //遇见右括号左括号直接出栈
                      return "(";
              }
          }
          //最后检测是否为空,为空则检测通过
          if(stack.isEmpty())
              return "check pass!";
          else
              return "check exception!";
      }
    
      public static void main(String args[])
      {
          String expstr="((5-3)*8-2)";
          System.out.println(expstr+"  "+isValid(expstr));
      }
    }
    
    ```
    
*   中缀表达式转换为后缀表达式  
    我们先来了解一下什么是中缀表达式，平常所见到的计算表达式都算是中缀表达式，如以下的表达式：
    
    ```
    1
    //1+3*(9-2)+9 --->中缀表达式（跟日常见到的表达式没啥区别）
    
    ```
    
    了解中缀表达式后来看看其定义：将运算符写在两个操作数中间的表达式称为中缀表达式。在中缀表达式中，运算符拥有不同的优先级，同时也可以使用圆括号改变运算次序，由于这两点的存在，使用的中缀表达式的运算规则比较复杂，求值的过程不能从左往右依次计算，当然这也是相对计算机而言罢了，毕竟我们日常生活的计算使用的还是中缀表达式。既然计算机感觉复杂，那么我们就需要把中缀表达式转化成计算机容易计算而且不复杂的表达式，这就是后缀表达式了，在后缀表达式中，运算符是没有优先级的，整个计算都是遵守从左往右的次序依次计算的，如下我们将中缀表达式转为后缀表达式：
    
    ```
    1
    2
    //1+3*(9-2)+9        转化前的中缀表达式
    //1 3 9 2 - * + 9 +  转化后的后缀表达式
    
    ```
    
    中缀转后缀的转换过程需要用到栈，这里我们假设栈 A 用于协助转换，并使用数组 B 用于存放转化后的后缀表达式具体过程如下：  
    1）如果遇到操作数，我们就直接将其放入数组 B 中。  
    2）如果遇到运算符，则我们将其放入到栈 A 中，遇到左括号时我们也将其放入栈 A 中。  
    3）如果遇到一个右括号，则将栈元素弹出，将弹出的运算符输出并存入数组 B 中直到遇到左括号为止。注意，左括号只弹出并不存入数组。  
    4）如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，从栈中弹出元素存入数组 B 直到遇到发现更低优先级的元素 (或者栈为空) 为止。弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到” ) “的情况下我们才弹出” ( “，其他情况我们都不会弹出” ( “。  
    5）如果我们读到了输入的末尾，则将栈中所有元素依次弹出存入到数组 B 中。  
    6）到此中缀表达式转化为后缀表达式完成，数组存储的元素顺序就代表转化后的后缀表达式。  
    执行图示过程如下：  
    ![](https://img-blog.csdn.net/20161127225741649)
    
    简单分析一下流程，当遇到操作数时（规则 1），直接存入数组 B 中，当 i=1（规则 2）时，此时运算符为 +，直接入栈，当 i=3(规则 2) 再遇到运算符 *，由于栈内的运算符 + 优先级比 * 低，因此_直接入栈，当 i=4 时，遇到运算符’(‘，直接入栈，当 i=6 时，遇运算符 -，直接入栈，当 i=8 时（规则 3），遇’)’，- 和’(‘直接出栈，其中运算符 - 存入后缀数组 B 中，当 i=9 时（规则 5），由于 * 优先级比 + 高，而 + 与 + 平级，因此_和 + 出栈，存入数组 B，而后面的 + 再入栈，当 i=10（规则 5），结束，+ 直接出栈存入数组 B，此时数组 B 的元素顺序即为`1 3 9 2 - * + 9 +`，这就是中缀转后缀的过程。  
    接着转成后缀后，我们来看看计算机如何利用后缀表达式进行结果运算，通过前面的分析可知，后缀表达式是没有括号的，而且计算过程是按照从左到右依次进行的，因此在后缀表达的求值过程中，当遇到运算符时，只需要取前两个操作数直接进行计算即可，而当遇到操作数时不能立即进行求值计算，此时必须先把操作数保存等待获取到运算符时再进行计算，如果存在多个操作数，其运算次序是后出现的操作数先进行运算，也就是后进先运算，因此后缀表达式的计算过程我们也需要借助栈来完成，该栈用于存放操作数，后缀表达式的计算过程及其图解如下：  
    ![](https://img-blog.csdn.net/20161128094423012)
    
    借助栈的程序计算过程：
    
    ![](https://img-blog.csdn.net/20161128094454507)
    
    简单分析说明一下：  
    1) 如果 ch 是数字，先将其转换为整数再入栈  
    2) 如果是运算符，将两个操作数出栈，计算结果再入栈  
    3) 重复 1）和 2）直到后缀表达式结束，最终栈内的元素即为计算的结果。  
    整体整体呈现实现如下：
    
    ```
    1
    2
    3
    4
    5
    6
    7
    8
    9
    10
    11
    12
    13
    14
    15
    16
    17
    18
    19
    20
    21
    22
    23
    24
    25
    26
    27
    28
    29
    30
    31
    32
    33
    34
    35
    36
    37
    38
    39
    40
    41
    42
    43
    44
    45
    46
    47
    48
    49
    50
    51
    52
    53
    54
    55
    56
    57
    58
    59
    60
    61
    62
    63
    64
    65
    66
    67
    68
    69
    70
    71
    72
    73
    74
    75
    76
    77
    78
    79
    80
    81
    82
    83
    84
    85
    86
    87
    88
    89
    90
    91
    92
    93
    94
    95
    96
    97
    98
    99
    100
    101
    102
    103
    104
    105
    106
    107
    108
    109
    110
    111
    112
    113
    114
    115
    116
    117
    118
    119
    120
    121
    122
    123
    124
    125
    126
    127
    128
    129
    130
    131
    132
    133
    134
    135
    136
    137
    138
    package com.zejian.structures.Stack;
    
    /**
    * Created by zejian on 2016/11/28.
    * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
    * 中缀转后缀,然后计算后缀表达式的值
    */
    public class CalculateExpression {
    
      /**
       * 中缀转后缀
       * @param expstr 中缀表达式字符串
       * @return
       */
      public static String toPostfix(String expstr)
      {
          //创建栈,用于存储运算符
          SeqStack<String> stack = new SeqStack<>(expstr.length());
    
          String postfix="";//存储后缀表达式的字符串
          int i=0;
          while (i<expstr.length())
          {
              char ch=expstr.charAt(i);
              switch (ch)
              {
                  case '+':
                  case '-':
                      //当栈不为空或者栈顶元素不是左括号时,直接出栈,因此此时只有可能是*/+-四种运算符(根据规则4),否则入栈
                      while (!stack.isEmpty() && !stack.peek().equals("(")) {
                          postfix += stack.pop();
                      }
                      //入栈
                      stack.push(ch+"");
                      i++;
                      break;
                  case '*':
                  case '/':
                      //遇到运算符*/
                      while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                          postfix += stack.pop();
                      }
                      stack.push(ch+"");
                      i++;
                      break;
                  case '(':
                      //左括号直接入栈
                      stack.push(ch+"");
                      i++;
                      break;
                  case ')':
                      //遇到右括号(规则3)
                      String out = stack.pop();
                      while (out!=null && !out.equals("("))
                      {
                          postfix += out;
                          out = stack.pop();
                      }
                      i++;
                      break;
                  default:
                      //操作数直接入栈
                      while (ch>='0' && ch<='9')
                      {
                          postfix += ch;
                          i++;
                          if (i<expstr.length())
                              ch=expstr.charAt(i);
                          else
                              ch='=';
                      }
                      //分隔符
                      postfix += " ";
                      break;
              }
          }
          //最后把所有运算符出栈(规则5)
          while (!stack.isEmpty())
              postfix += stack.pop();
          return postfix;
      }
    
      /**
       * 计算后缀表达式的值
       * @param postfix 传入后缀表达式
       * @return
       */
      public static int calculatePostfixValue(String postfix)
      {
          //栈用于存储操作数,协助运算
          LinkedStack<Integer> stack = new LinkedStack<>();
          int i=0, result=0;
          while (i<postfix.length())
          {
              char ch=postfix.charAt(i);
              if (ch>='0' && ch<='9')
              {
                  result=0;
                  while (ch!=' ')
                  {
                      //将整数字符转为整数值ch=90
                      result = result*10 + Integer.parseInt(ch+"");
                      i++;
                      ch = postfix.charAt(i);
                  }
                  i++;
                  stack.push(result);//操作数入栈
              }
              else
              {  //ch 是运算符,出栈栈顶的前两个元素
                  int y= stack.pop();
                  int x= stack.pop();
                  switch (ch)
                  {   //根据情况进行计算
                      case '+': result=x+y; break;
                      case '-': result=x-y; break;
                      case '*': result=x*y; break;
                      case '/': result=x/y; break;   //注意这里并没去判断除数是否为0的情况
                  }
                  //将运算结果入栈
                  stack.push(result);
                  i++;
              }
          }
          //将最后的结果出栈并返回
          return stack.pop();
      }
      //测试
      public static void main(String args[])
      {
          String expstr="1+3*(9-2)+90";
          String postfix = toPostfix(expstr);
          System.out.println("中缀表达式->expstr=  "+expstr);
          System.out.println("后缀表达式->postfix= "+postfix);
          System.out.println("计算结果->value= "+calculatePostfixValue(postfix));
      }
    
    }
    
    ```
    
    以上便是利用转实现中缀与后缀的转换过程并且通过后缀计算机能及其简单计算出后缀表达式的结果。ok~，到此我们对栈的分析就结束了，本来还想聊聊函数调用的问题，但感觉这个问题放在递归算法更恰当，嗯，源码地址如下：  
    [github 源码下载，欢迎 star（含文章列表，持续更新）](https://github.com/shinezejian/javaStructures)