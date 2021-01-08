//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 
// 👍 1839 👎 0


package leetcode.editor.cn.solved;

//Java：接雨水
public class P42TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
        // TO TEST
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int n = height.length, ans = 0;
            if (n == 0) return 0;
            int[] l = new int[n],r = new int[n];
            l[0] = height[0];
            for (int i = 1; i < n; i ++) {
                l[i] = Math.max(height[i], l[i - 1]);
            }
            r[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i --) {
                r[i] = Math.max(height[i], r[i + 1]);
            }
            for (int i = 1; i < n - 1; i ++) {
                int h = Math.min(l[i - 1], r[i + 1]);
                if (h > height[i]) {
                    ans += h - height[i];
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}