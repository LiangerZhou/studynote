#
# @lc app=leetcode id=13 lang=python3
#
# [13] Roman to Integer
#
# https://leetcode.com/problems/roman-to-integer/description/
#
# algorithms
# Easy (51.43%)
# Total Accepted:    361K
# Total Submissions: 701.5K
# Testcase Example:  '"III"'
#
# Roman numerals are represented by seven different symbols: I, V, X, L, C, D
# and M.
# 
# 
# Symbol       Value
# I             1
# V             5
# X             10
# L             50
# C             100
# D             500
# M             1000
# 
# For example, two is written as II in Roman numeral, just two one's added
# together. Twelve is written as, XII, which is simply X + II. The number
# twenty seven is written as XXVII, which is XX + V + II.
# 
# Roman numerals are usually written largest to smallest from left to right.
# However, the numeral for four is not IIII. Instead, the number four is
# written as IV. Because the one is before the five we subtract it making four.
# The same principle applies to the number nine, which is written as IX. There
# are six instances where subtraction is used:
# 
# 
# I can be placed before V (5) and X (10) to make 4 and 9. 
# X can be placed before L (50) and C (100) to make 40 and 90. 
# C can be placed before D (500) and M (1000) to make 400 and 900.
# 
# 
# Given a roman numeral, convert it to an integer. Input is guaranteed to be
# within the range from 1 to 3999.
# 
# Example 1:
# 
# 
# Input: "III"
# Output: 3
# 
# Example 2:
# 
# 
# Input: "IV"
# Output: 4
# 
# Example 3:
# 
# 
# Input: "IX"
# Output: 9
# 
# Example 4:
# 
# 
# Input: "LVIII"
# Output: 58
# Explanation: L = 50, V= 5, III = 3.
# 
# 
# Example 5:
# 
# 
# Input: "MCMXCIV"
# Output: 1994
# Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
# 
#

"""
罗马数字采用七个罗马字母作数字： 
I（1）、X（10）、C（100）、M（1000）、V（5）、L（50）、D（500）。

记数的方法： 
1. 相同的数字连写，所表示的数等于这些数字相加得到的数，如 III=3； 
2. 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 VIII=8、XIII=12； 
3. 小的数字（限于 I、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 IV=4、IX=9； 
4. 在一个数的上面画一条横线，表示这个数增值 1,000 倍。
--------------------- 

根据上面说的计数方法的前三条。对于输入的罗马数字字符串，
从后向前扫描，遇到前面数大于等于后面的最大数的时候，相加；
遇到前面数小于后面的最大数的时候，相减。
"""

class Solution:
    def romanToInt(self, s: 'str') -> 'int':
        digits = {"I":1, "V":5, "X":10, "L":50, "C":100, "D":500, "M":1000}
        sum = 0
        maxDigit = 1
        for i in range(len(s)-1, -1, -1):
            if digits[s[i]] >= maxDigit:
                maxDigit = digits[s[i]]
                sum += digits[s[i]]
            else:
                sum -= digits[s[i]]

        return sum

