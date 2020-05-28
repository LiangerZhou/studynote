/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 */

// @lc code=start
class Solution {
    // 转换成字符来做
    // public boolean isPalindrome(int x) {
    // String s = String.valueOf(x);
    // for (int i = 0; i < s.length() / 2; i++) {
    // if (s.charAt(i) != s.charAt(s.length()-1 - i)) {
    // return false;
    // }
    // }
    // return true;
    // }
    // 数学解法：求出首位和末位，不相同就返回false
    // public boolean isPalindrome(int x) {
    //     // 边界判断
    //     if (x < 0)
    //         return false;
    //     int div = 1;
    //     //
    //     while (x / div >= 10)
    //         div *= 10;
    //     while (x > 0) {
    //         int left = x / div;
    //         int right = x % 10;
    //         if (left != right)
    //             return false;
    //         x = (x % div) / 10;
    //         div /= 100;
    //     }
    //     return true;
    // }

    // **不转换成字符来做,巧妙解法**
    // 每次进行取余操作 （ %10），取出最低的数字：y = x % 10
    // 将最低的数字加到取出数的末尾：revertNum = revertNum * 10 + y
    // 每取一个最低位数字，x 都要自除以 10
    // 判断 x 是不是小于 revertNum ，当它小于的时候，说明数字已经对半或者过半了
    // 最后，判断奇偶数情况：如果是偶数的话，revertNum 和 x 相等；如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10
    // 以后应该和 x 相等。
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {// 负数排除，10的倍数不算
            return false;
        }
        int res = 0;
        while (x > res) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return (x == res || x == res / 10);
    }

}
// @lc code=end
