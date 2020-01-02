/*
 * @lc app=leetcode.cn id=74 lang=java
 *
 * [74] 搜索二维矩阵
 */

// @lc code=start
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {
//         if(matrix.length==0 || matrix[0].length==0){ //判断矩阵是否为空，或这样[[]]
//             return false;
//         }
//         int l = 0;
//         int m = matrix.length; //矩阵行数
//         int n = matrix[0].length; //矩阵列数
//         int r = m*n-1; //矩阵元素总个数
//         while(l<r){
//             int mid = l+(r-l)/2; //矩阵中间数，二分
//             if(matrix[mid/n][mid%n]>=target){ //mid/n 取得行数，mid%n取得列数
//                 r = mid;
//             }else{
//                 l = mid + 1;
//             }
//         }
//         return matrix[l/n][l%n]==target; 
//     }
// }


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0){ //判断矩阵是否为空，或这样[[]]
            return false;
        }
        int l = 0;
        int m = matrix.length; //矩阵行数
        int n = matrix[0].length; //矩阵列数
        int r = m*n-1; //矩阵元素总个数
        while(l<r){
            int mid = l+(r-l+1)/2; //矩阵中间数，二分
            if(matrix[mid/n][mid%n]<=target){ //mid/n 取得行数，mid%n取得列数
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return matrix[l/n][l%n]==target; 
    }
}
// @lc code=end

