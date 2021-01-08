//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> intention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1279 👎 0


package leetcode.editor.cn.solved;

//Java：编辑距离
public class P72EditDistance {
    public static void main(String[] args) {
        Solution solution = new P72EditDistance().new Solution();
        // TO TEST
        System.out.println(solution.minDistance("intention", "execution"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            int l1 = word1.length(), l2 = word2.length();
            int[][] dp = new int[l1 + 1][l2 + 1];
            for (int i = 0; i <= l1; i ++) {
                for (int j = 0; j <= l2; j ++) {
                    if (i == 0) {
                        dp[i][j] = j;
                    } else if (j == 0) {
                        dp[i][j] = i;
                    } else {
                        int k = word1.charAt(i - 1)==word2.charAt(j - 1)?0:1;
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + k);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
            return dp[l1][l2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}