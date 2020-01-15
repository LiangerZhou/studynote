 /*
 * @lc app=leetcode.cn id=278 lang=java
 *
 * [278] 第一个错误的版本
 */

// @lc code=start
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while(l<r){
            int mid = l+(r-l)/2;
            if(isBadVersion(mid)==true){ //模板一
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
// @lc code=end

