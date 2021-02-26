/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 *
 * https://leetcode.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (31.60%)
 * Total Accepted:    405K
 * Total Submissions: 1.3M
 * Testcase Example:  '"hello"\n"ll"'
 *
 * Implement strStr().
 * 
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Example 1:
 * 
 * 
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * 
 * 
 * Clarification:
 * 
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 * 
 */

/**
 * KMP算法
 */

class Solution {
    public int strStr(String haystack, String needle) {
        // if(needle.length()==0 || haystack.equals(needle) || haystack.replace("neddle","")==null){
        //     return 0;
        // }
        // String ss = haystack.replace(needle, ",");
        // if(ss.length()<haystack.length()&&ss.length()>1){
        //     return ss.split(",")[0].length();
        // }  
        // return -1;
        
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                System.out.println(i);
                System.out.println(j);
              if (j == needle.length()) return i;
              if (i + j == haystack.length()) return -1;
              if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    public static void main(String args[]){
        String a = "BBC ABCDAB ABCDABCDABDE";
        String b = "ABCDABD";
        Solution slu = new Solution();
        int res = slu.strStr(a, b);
        System.out.println(res);
    }
}

