#
# @lc app=leetcode id=1 lang=python3
#
# [1] Two Sum
#
# https://leetcode.com/problems/two-sum/description/
#
# algorithms
# Easy (40.52%)
# Total Accepted:    1.4M
# Total Submissions: 3.5M
# Testcase Example:  '[2,7,11,15]\n9'
#
# Given an array of integers, return indices of the two numbers such that they
# add up to a specific target.
# 
# You may assume that each input would have exactly one solution, and you may
# not use the same element twice.
# 
# Example:
# 
# 
# Given nums = [2, 7, 11, 15], target = 9,
# 
# Because nums[0] + nums[1] = 2 + 7 = 9,
# return [0, 1].
# 
# 
# 
# 
#
class Solution:
    def twoSum(self, nums: 'List[int]', target: 'int') -> 'List[int]':
        if len(nums)<2:
            raise Exception
        elif len(nums)==2:
            if nums[0]+nums[1]==target:
                return [0,1]
        else:
            for idx in range(len(nums)):
                tmp = target - nums[idx]
                for nxt in range(len(nums)):               
                    if nums[nxt] == tmp and idx != nxt:
                        return [idx, nxt]
         
