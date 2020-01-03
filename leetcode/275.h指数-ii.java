/*
 * @lc app=leetcode.cn id=275 lang=java
 *
 * [275] H指数 II
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int l = 0;
        int r = citations.length;
        while(l<r){
            int mid = (l+r)/2;
            if(citations[mid]>=citations.length - mid){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return citations.length-l;
    }
}
// @lc code=end

