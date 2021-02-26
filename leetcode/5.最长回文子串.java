/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
// 暴力解法，不好
// class Solution {

// 	public String longestPalindrome(String s) {
// 		if(s==null ||s.length()<1){
// 			return "";
// 		}
// 		String result = "";
//         for (int i = 0; i < s.length() - result.length(); i++) { //从index=0开始，从最后一个元素减少，然后从index++开始，从最后一个元素减少
// 			for (int j = s.length() - 1; j >= i; j--) {
// 				if(isPalidrome(s.substring(i, j + 1)) && (j + 1 - i) > result.length()) {
// 					result = s.substring(i, j + 1);
// 					break;
// 				}
// 			}
// 		}
//         return result;
//     }

// 	// 判断是否是回文串：从两端往中间判断
// 	private boolean isPalidrome(String s) {
// 		for (int i = 0; i < s.length() / 2; i++) {
// 			if(s.charAt(i) != s.charAt(s.length() - i - 1)) {
// 				return false;
// 			}
// 		}
// 		return true;
// 	}
// }

// 中心扩散法
// (1) 长度为奇数的回文子串中心有一个元素，例如 "aba" 的中心元素为 "b" ；
// (2) 长度为偶数的回文子串中心有两个元素，例如 "abba" 的中心元素为 "bb" 。
// (3) 分别以两种中心为基础寻找并比较出最长回文子串。
class Solution {
	public String longestPalindrome(String s) {
		if (s.length() < 1) {
			return "";
		}
		int left = 0;
		int right = 0;
		for (int i = 0; i < s.length()-1; i++) {
			int len1 = centerlength(s, i, i);
			int len2 = centerlength(s, i, i + 1);
			int maxlen = Math.max(len1, len2);
			if (maxlen > right - left + 1) {
				left = i - (maxlen - 1) / 2; // 最大长度的一半，当为奇数时，则以当前i为中心，长度-1，除以2两边各自减去或加上；当为偶数时，则以当前i和i+1为中心，左边为长度-2，i-(maxlen-2)/2,右边为i+(maxlen-2)/2+1;
				right = i + maxlen / 2;
			}
		}
		return s.substring(left, right + 1);
	}

	public int centerlength(String s, int start, int end) {
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
			start--;
			end++;
		}
		end = end - 1; // 不满足时，跳出循环，要将位置还原到上一次位置
		start = start + 1; // 不满足时，跳出循环，要将位置还原到上一次位置
		return end - start + 1; // 后减去前,+1等于长度
	}
}
// class Solution {
// public String longestPalindrome(String s) {
// if (s.isEmpty()) {
// return "";
// }
// // 记录最长回文子串开始和结束的位置。
// int start = 0, end = 0;
// for (int i = 0; i < s.length(); i++) {
// // 查找以当前元素为中心的最长回文子串长度。
// int len1 = function(s, i, i);
// // 查找以当前元素和下一个元素为中心的最长回文子串长度。
// int len2 = function(s, i, i + 1);
// int maxLen = Math.max(len1, len2);
// // 判断记录遍历过位置能找到的最长回文子串起始位置。
// if (maxLen > end - start) {
// start = i - (maxLen - 1) / 2;
// end = i + maxLen / 2;
// }
// }
// return s.substring(start, end + 1);
// }

// private int function(String s, int left, int right) {
// // 以传入的相邻两位置为中心找最大回文子串长度，两位置相同表示一个元素为中心。
// while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
// {
// left--;
// right++;
// }
// return right - left - 1;
// }
// }

// class Solution {
// public String longestPalindrome(String s) {
// if (s.isEmpty()) {
// return "";
// }
// // 先加入特殊字符预处理字符串，使字符串每个字符被隔开且变成奇数长度。
// String str = preHandleString(s);
// // 处理后的字符串长度。
// int len = str.length();
// // 记录遍历过的最长回文子串右边界。
// int right = 0;
// // 右边界对应的回文串中心。
// int rightCenter = 0;
// // 保存以每个字符为中心的回文长度半径。
// int[] halfLenArr = new int[len];
// // 记录遍历过的最长回文子串中心。
// int center = 0;
// // 记录遍历过的最长回文子串半径。
// int longestHalf = 0;
// for (int i = 0; i < len; i++) {
// // 标记当前位置是否需要中心扩展找回文子串。
// boolean needCalc = true;
// // 如果在右边界的覆盖范围之内。
// if (right > i) {
// // 计算 i 以 rightCenter 为中心的对称位置。
// int leftCenter = 2 * rightCenter - i;
// // 回文串的对称位置为中心的回文子串相同。
// halfLenArr[i] = halfLenArr[leftCenter];
// // 对称后以 i 为中心的回文串超过了右边界，则记录半径到右边界。
// if (i + halfLenArr[i] > right) {
// halfLenArr[i] = right - i;
// }
// // 对称后以 i 为中心的回文串小于右边界，则不需要继续扩散查找。
// if (i + halfLenArr[leftCenter] < right) {
// needCalc = false;
// }
// }

// // 中心扩散得出以当前元素为中心的最长回文子串半径。
// if (needCalc) {
// while (i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
// if (str.charAt(i - 1 - halfLenArr[i]) == str.charAt(i + 1 + halfLenArr[i])) {
// halfLenArr[i]++;
// } else {
// break;
// }
// }
// // 更新记录遍历过的最长回文子串右边界以及中心。
// right = i + halfLenArr[i];
// rightCenter = i;
// // 记录最长回文子串的中心和半径。
// if (halfLenArr[i] > longestHalf) {
// center = i;
// longestHalf = halfLenArr[i];
// }
// }
// }
// // 去掉之前添加的 # 。
// StringBuilder sb = new StringBuilder();
// for (int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
// sb.append(str.charAt(i));
// }
// return sb.toString();
// }

// private String preHandleString(String s) {
// StringBuilder sb = new StringBuilder();
// int len = s.length();
// // 在字符串所有字符两边都加上 # ，使字符隔开并且字符串变为奇数长度串。
// sb.append('#');
// for (int i = 0; i < len; i++) {
// sb.append(s.charAt(i));
// sb.append('#');
// }
// return sb.toString();
// }
// }

// @lc code=end
