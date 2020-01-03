/*
 * @lc app=leetcode.cn id=35 lang=java
 *
 * [35] 搜索插入位置
 */

// @lc code=start
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        if(target<nums[0]){
            return 0;
        }
        if(target>nums[r]){
            return r +1;
        }
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>=target){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
}
// @lc code=end

