/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (40.63%)
 * Total Accepted:    381.5K
 * Total Submissions: 938.8K
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,5,6], 5
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,3,5,6], 2
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [1,3,5,6], 7
 * Output: 4
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: [1,3,5,6], 0
 * Output: 0
 * 
 * 
 */


 /**
  * 二分查找法
  */
class Solution {
    public int searchInsert(int[] nums, int target) {
        // for (int i=0; i<nums.length-1; i++) {
        //     if(target==nums[i])
        //         return i;
        //     else if(i>0 && target<nums[i] && target>nums[i-1])
        //         return i;
        //     else if(target<nums[0])
        //         return 0;
        //     else if(target>nums[nums.length-1])
        //         return nums.length;
        // }
        // return 0;

        int low = 0, high = nums.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
}

