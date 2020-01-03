/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        int l = 0;
        int r = nums.length - 1;
        while(l<r){
            int mid = (l + r)/2;
            if(nums[mid]>=target){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        int start = r;
        
        if(nums[r]!=target){
            return new int[]{-1,-1};
        }
        l = 0;
        r = nums.length - 1;
        while(l<r){
            int mid = (l + r + 1)/2;  // 需要加1，否则mid-1 会出现负数
            if(nums[mid]<=target){
                l = mid;
            }else{
                r = mid -1;
            }
        }
        int end = r;
        return new int[]{start,end};

    }
}
// @lc code=end

