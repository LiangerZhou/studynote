#
# @lc app=leetcode id=9 lang=python3
#
# [9] Palindrome Number
#
# https://leetcode.com/problems/palindrome-number/description/
#
# algorithms
# Easy (41.74%)
# Total Accepted:    506.5K
# Total Submissions: 1.2M
# Testcase Example:  '121'
#
# Determine whether an integer is a palindrome. An integer is a palindrome when
# it reads the same backward as forward.
# 
# Example 1:
# 
# 
# Input: 121
# Output: true
# 
# 
# Example 2:
# 
# 
# Input: -121
# Output: false
# Explanation: From left to right, it reads -121. From right to left, it
# becomes 121-. Therefore it is not a palindrome.
# 
# 
# Example 3:
# 
# 
# Input: 10
# Output: false
# Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
# 
# 
# Follow up:
# 
# Coud you solve it without converting the integer to a string?
# 
#
class Solution:
    def isPalindrome(self, x: 'int') -> 'bool':
        # if len(str(x)) < 2:
        #     return True
        # elif str(x)[0] == str(x)[-1]:
        #     return self.isPalindrome(str(x)[1:-1])
        # else:
        #     return False
        res=0
        if x<0 or (x%10==0 and x!=0):
            return False
        while x>res:
            res=res*10+x%10
            x=x//10
        
        # 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        # 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        # 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x==res or x==res//10
        
