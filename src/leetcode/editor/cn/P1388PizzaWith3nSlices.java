//给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨： 
//
// 
// 你挑选 任意 一块披萨。 
// Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。 
// Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。 
// 重复上述过程直到没有披萨剩下。 
// 
//
// 每一块披萨的大小按顺时针方向由循环数组 slices 表示。 
//
// 请你返回你可以获得的披萨大小总和的最大值。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：slices = [1,2,3,4,5,6]
//输出：10
//解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小
//为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
// 
//
// 示例 2： 
//
// 
//
// 输入：slices = [8,9,8,6,1,1]
//输出：16
//解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
// 
//
// 示例 3： 
//
// 输入：slices = [4,1,2,5,8,3,1,9,7]
//输出：21
// 
//
// 示例 4： 
//
// 输入：slices = [3,1,2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= slices.length <= 500 
// slices.length % 3 == 0 
// 1 <= slices[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 61 👎 0


package leetcode.editor.cn;

//Java：3n 块披萨
public class P1388PizzaWith3nSlices {
    public static void main(String[] args) {
        Solution solution = new P1388PizzaWith3nSlices().new Solution();
        // TO TEST
        System.out.println(solution.maxSizeSlices(new int[]{3,1,2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSizeSlices(int[] slices) {
            int n = slices.length, m = 2 * n - 1;
            int[] s = new int[m];
            int[][] dp = new int[m + 1][m + 1];
            for (int i = 0; i < m; i ++) {
                if (i >= n) {
                    s[i] = slices[i - n];
                } else {
                    s[i] = slices[i];
                }
            }
            for (int i = m - 1; i >= 0; i --) {
                for (int j = m - 1; j > i; j --) {
                    if ((j - i + 1) % 3 > 0 || j - i + 1 > n) continue;
                    for (int p = i + 1; p <= j; p += 3) {
                        for (int q = p + 1; q <= j; q += 3) {
                            dp[i][j] = Math.max(dp[i][j], dp[i + 1][p - 1] + dp[p + 1][q - 1] + dp[q + 1][j] + s[p]);
//                            if (j - i + 1 <= n) {
////                                System.out.println(i + " " + j + " " + dp[i][j]);
//                            }
                        }
                    }
                }
            }
            int ans = 0;
            for (int x = 0; x < n; x ++) {
                for (int y = x + 1; y < n; y += 3) {
                    for (int z = y + 1; z < n; z += 3) {
                        int k = Math.max(s[x], Math.max(s[y], s[z]));
                        ans = Math.max(ans, k + dp[x + 1][y - 1] + dp[y + 1][z - 1] + dp[z + 1][n + x - 1]);
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}