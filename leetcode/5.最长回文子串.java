/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
	
	public String longestPalindrome(String s) {
		String result = "";
        for (int i = 0; i < s.length() - result.length(); i++) {
			for (int j = s.length() - 1; j >= i; j--) {
				if(isPalidrome(s.substring(i, j + 1)) && (j + 1 - i) > result.length()) {
					result = s.substring(i, j + 1);
					break;
				}
			}
		}
        return result;
    }
	
	private boolean isPalidrome(String s) {
		for (int i = 0; i < s.length() / 2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
// @lc code=end

