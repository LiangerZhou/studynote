/*
 * @lc app=leetcode.cn id=151 lang=java
 *
 * [151] 翻转字符串里的单词
 */

// // @lc code=start
// class Solution {
//     public String reverseWords(String s) {
//         if(s.isEmpty()){
//             return "";
//         }
//         char[] a = s.toCharArray();
//         int len = a.length;
//         // 1.翻转字符串数组
//         reverse(a, 0, len-1);
//         // 2.反转每一个单词
//         reverseWords(a, len);

//         // 3. 清楚空格

//         return cleanSpaces(a, len);
//     }

//     // 1.反转字符串数组，首尾互相交换,i:首位的index，j:末尾的index
//     private void reverse(char[] a, int i, int j){
//         while(i<j){
//             char t = a[i];
//             a[i++] = a[j];
//             a[j--] = t;
//         }
//     }

//     // 2. 获取每个连续的单词，并反转
//     private void reverseWords(char[] ch, int n){
//         int i = 0; 
//         int j = 0;

//         while(i<n){
//             while(i<j || i<n && ch[i]==' '){ //跳过空格
//                 i++;
//             }
//             while(j<i || j<n && ch[j] != ' '){ //跳过非空格
//                 j++;
//             }
//             reverse(ch,i,j-1); //反转单词
//         }
//     }

//     // 3.去掉前后的空格和多余的空格
//     private String cleanSpaces(char[] ch, int n){
//         int i=0;
//         int j=0;
//         while(j<n){
//             while(j<n && ch[j]==' '){ //跳过空格
//                 j++;
//             }
//             while(j<n && ch[j] != ' '){ //保持没空格
//                 ch[i++] = ch[j++];
//             }
//             while(j<n && ch[j]==' ') j++; //跳过空格

//             if(j<n){
//                 ch[i++] = ' '; //保持唯一空格
//             }
//         }
//         return new String(ch).substring(0,i);
//     }
// }



class Solution {
    public String reverseWords(String s) {
        if(s.isEmpty()){
            return "";
        }
        Stack<String> stk = new Stack<>();
        StringBuilder tmp = new StringBuilder();
        String[] str = s.trim().split(" ");
        for(String aString:str){
            if(!aString.isEmpty()){
                stk.push(aString);
            }
        }
        if(stk.isEmpty()){
            return "";
        }
        tmp.append(stk.pop());
        while(!stk.isEmpty()){
            tmp.append(" ");
            tmp.append(stk.pop());
        }

        return tmp.toString();
        
    }
}
// @lc code=end

//栈来解决

// public String reverseWords(String s) {
//     Stack<String> stack = new Stack<>();
//     StringBuilder temp = new StringBuilder();
//     s += " ";
//     for (int i = 0; i < s.length(); i++) {
//         if (s.charAt(i) == ' ') {
//             if (temp.length() != 0) {
//                 stack.push(temp.toString());
//                 temp = new StringBuilder();
//             }
//         } else {
//             temp.append(s.charAt(i));
//         }
//     }
//     if (stack.isEmpty()) {
//         return "";
//     }
//     StringBuilder res = new StringBuilder();
//     res.append(stack.pop());
//     while (!stack.isEmpty()) {
//         res.append(" ");
//         res.append(stack.pop());
//     }
//     return res.toString();
// }

// 作者：windliang
// 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-36/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。