//给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 输入：
//  s = "barfoothefoobarman",
//  words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 输入：
//  s = "wordgoodgoodgoodbestword",
//  words = ["word","good","best","word"]
//输出：[]
// 
// Related Topics 哈希表 双指针 字符串 
// 👍 386 👎 0


package leetcode.editor.cn.solved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：串联所有单词的子串
public class P30SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        Solution solution = new P30SubstringWithConcatenationOfAllWords().new Solution();
        String[] words = {"a","a","a","a"};
        String s = "aaaaaaaaaaaaaaaaaa";
        System.out.println(solution.findSubstring(s, words));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> mapNow = new HashMap<>();
            for (int i = 0; i < words.length; i ++) {
                Integer num = map.getOrDefault(words[i], 0);
                num ++;
                map.put(words[i], num);
            }
            int len = words[0].length(), l = s.length();
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < len; i ++) {
                if (i + words.length * len > l) break;
                int k = 0;
                mapNow.clear();
                for (int j = 0; j < words.length; j ++) {
                    String substring = s.substring(i + j * len, i + j * len + len);
                    Integer num = mapNow.getOrDefault(substring, 0);
                    if (num.equals(map.get(substring))) k --;
                    num ++;
                    if (num.equals(map.get(substring))) k ++;
                    mapNow.put(substring, num);
                }
                if (k == map.size()) ans.add(i);
                for (int j = i + len; j + words.length * len <= l; j += len) {
                    String substring = s.substring(j - len, j);
                    Integer num = mapNow.get(substring);
                    if (num.equals(map.get(substring))) k --;
                    num --;
                    if (num.equals(map.get(substring))) k ++;
                    mapNow.put(substring, num);

                    substring = s.substring(j + (words.length - 1) * len, j + words.length * len);
                    num = mapNow.getOrDefault(substring, 0);
                    if (num.equals(map.get(substring))) k --;
                    num ++;
                    if (num.equals(map.get(substring))) k ++;
                    mapNow.put(substring, num);
                    if (k == map.size()) ans.add(j);
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}