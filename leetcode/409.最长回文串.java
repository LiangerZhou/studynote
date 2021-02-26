import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=409 lang=java
 *
 * [409] 最长回文串
 */

// @lc code=start
class Solution {
    public int longestPalindrome(String s) {
        HashSet<Character> hashsets = new HashSet<>();
        int len = s.length();
        int n = 0;
        for(int i=0;i<len;i++){
            if(hashsets.contains(s.charAt(i))){
                hashsets.remove(s.charAt(i));
                n++;
            }else{
                hashsets.add(s.charAt(i));
            }
        }
        if(hashsets.isEmpty()){
            return n*2;
        }else{
            return n*2+1;
        }

    }
}
// @lc code=end

