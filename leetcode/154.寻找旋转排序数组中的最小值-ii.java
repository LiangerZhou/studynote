/*
 * @lc app=leetcode.cn id=154 lang=java
 *
 * [154] 寻找旋转排序数组中的最小值 II
 */

// @lc code=start
/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--37/
 * 最全解释
 */
class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length -1;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid] < nums[r]){
                r = mid;
            }else if (nums[mid] > nums[r]){
                l = mid + 1;
            }else{
                r = r - 1;
            }
        }
        return nums[l];
    }
}
// @lc code=end

