/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 */

// @lc code=start
// class Solution {
//     public int findDuplicate(int[] nums) {
//         int l = 1;
//         int r = nums.length - 1;
//         while(l<r){
//             int mid = (l+r)/2;
//             int cnt = 0;
//             for(int i:nums){
//                 if(i<=mid){
//                     cnt++;
//                 }
//             }
//             if(cnt>mid){
//                 r = mid;
//             }else{
//                 l = mid+1;
//             }
//         }
//         return r;
//     }
// }


class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length - 1;
        while(l<r){
            int mid = (l+r)/2;
            int cnt = 0;
            for(int i:nums){
                if(i<=mid && i>=l){ //统计数组中小于等于mid大于左的数个数
                    cnt++;
                }
            }
            if(cnt>(mid-l+1)){ //如果数个数大于mid，说明左半边有重复数
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return r;
    }
}
// @lc code=end

