/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 */

// @lc code=start
class Solution {
    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        while(l<r){
            int mid = (l + r + 1)>>>1; // 舍去小数（模板二）
            if(mid<=x/mid){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return r;
    }
}
// @lc code=end

