import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 创建hashmap对象，key使用字符串转换后相同的东西，value里存各个string
        Map<String,List<String>> hs = new HashMap<String,List<String>>();

        for(String str: strs){
            char[] chastr = str.toCharArray();
            Arrays.sort(chastr);
            String keyStr = String.valueOf(chastr);
            if(!hs.containsKey(keyStr)){
                hs.put(keyStr,new ArrayList<>());
            }
            hs.get(keyStr).add(str);
        }
        return new ArrayList<List<String>>(hs.values());
    }
}
// @lc code=end

