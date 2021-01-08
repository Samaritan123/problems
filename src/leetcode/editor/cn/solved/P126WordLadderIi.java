//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法 
// 👍 365 👎 0


package leetcode.editor.cn.solved;

import java.util.*;

//Java：单词接龙 II
public class P126WordLadderIi {
    public static void main(String[] args) {
        Solution solution = new P126WordLadderIi().new Solution();
        // TO TEST
        String[] strings = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution.findLadders("hit",
                "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int n;

        private List<List<Integer>> h = new ArrayList<>();

        private LinkedList<String> list = new LinkedList<>();

        private List<List<String>> ans = new ArrayList<>();

        private int[] dis;


        private void calc(int t, List<String> wordList) {
            list.addFirst(wordList.get(t));
            if (t == n - 2) {
                ans.add((List<String>) list.clone());
                list.pollFirst();
                return;
            }
            for (Integer k : h.get(t)) {
                if (dis[k] + 1 == dis[t]) {
                    calc(k, wordList);
                }
            }
            list.pollFirst();
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            boolean flag = false;
            for (String s : wordList) {
                if (s.equals(endWord)) flag = true;
            }
            if (!flag) return Collections.emptyList();
            List<String> words = new ArrayList<>(wordList);
            words.add(beginWord);
            words.add(endWord);
            n = words.size();
            dis = new int[n];
            for (int i = 0; i < n; i ++) dis[i] = -1;
            for (int i = 0; i < n; i ++) {
                h.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (i != j && reachable(words.get(i), words.get(j))) {
                        h.get(i).add(j);
                    }
                }
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(n - 2);
            dis[n - 2] = 0;
            while (!deque.isEmpty()) {
                Integer x = deque.pollFirst();
                for (Integer t : h.get(x)) {
                    if (dis[t] == -1) {
                        dis[t] = dis[x] + 1;
                        deque.addLast(t);
                    }
                }
            }

            calc(n - 1,  words);
            return ans;

        }

        private boolean reachable(String s1, String s2) {
            int num = 0;
            for (int i = 0; i < s1.length(); i ++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    num ++;
                }
            }
            return num == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}