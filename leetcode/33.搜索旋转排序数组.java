/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        //找出最小值
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]<=nums[r]){
                r = mid;
            }else{
                l = mid + 1;
            }
        }

        //判断target在最小值的哪一边
        if(nums[nums.length-1]>=target){
            r = nums.length -1;
        }else{
            l = 0;
            r = r-1;
        }
        //根据边求出target
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]>=target){
                r = mid;
            }else{
                l = mid + 1;
            } 
        }
        //如果target存在，返回l,不存在返回-1
        if(nums[l]==target){
            return l;
        }else{
            return -1;
        }
    }
}


// class Solution {
//     public int search(int[] nums, int target) {
//         if (nums == null || nums.length == 0) {
//             return -1;
//         }
//         int start = 0;
//         int end = nums.length - 1;
//         int mid;
//         while (start <= end) {
//             mid = start + (end - start) / 2;
//             if (nums[mid] == target) {
//                 return mid;
//             }
//             //前半部分有序,注意此处用小于等于
//             if (nums[start] <= nums[mid]) {
//                 //target在前半部分
//                 if (target >= nums[start] && target < nums[mid]) {
//                     end = mid - 1;
//                 } else {
//                     start = mid + 1;
//                 }
//             } else {
//                 if (target <= nums[end] && target > nums[mid]) {
//                     start = mid + 1;
//                 } else {
//                     end = mid - 1;
//                 }
//             }

//         }
//         return -1;

//     }
// }
// @lc code=end

