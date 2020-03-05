/*
 * @lc app=leetcode.cn id=38 lang=java
 *
 * [38] 报数
 */

// @lc code=start
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for(int i=0;i<n-1;i++){
            String ns = "";
            for(int j=0;j<s.length();j++){
                int k = j;
                while(k<s.length()&&s.charAt(k)==s.charAt(j)){
                    k++;
                }
                ns +=String.valueOf(k-j) + s.charAt(j);
                j = k - 1;
            }
            s = ns;
        }
        return s;
    }
}
// @lc code=end

