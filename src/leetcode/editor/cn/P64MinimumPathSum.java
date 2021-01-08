//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 752 👎 0


package leetcode.editor.cn;

//Java：最小路径和
public class P64MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new P64MinimumPathSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int[][] dp = new int[n][m];
            dp[0][0] = grid[0][0];
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < m; j ++) {
                    if (i + j == 0) continue;
                    dp[i][j] = 1000000000;
                    if (i > 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
                    }
                }
            }
            return dp[n - 1][m - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}