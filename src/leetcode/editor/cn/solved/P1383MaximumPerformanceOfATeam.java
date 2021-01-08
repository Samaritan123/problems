//公司有编号为 1 到 n 的 n 个工程师，给你两个数组 speed 和 efficiency ，其中 speed[i] 和 efficiency[i] 分
//别代表第 i 位工程师的速度和效率。请你返回由最多 k 个工程师组成的 最大团队表现值 ，由于答案可能很大，请你返回结果对 10^9 + 7 取余后的结果。 
//
// 团队表现值 的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。 
//
// 
//
// 示例 1： 
//
// 输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
//输出：60
//解释：
//我们选择工程师 2（speed=10 且 efficiency=4）和工程师 5（speed=5 且 efficiency=7）。他们的团队表现值为 per
//formance = (10 + 5) * min(4, 7) = 60 。
// 
//
// 示例 2： 
//
// 输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
//输出：68
//解释：
//此示例与第一个示例相同，除了 k = 3 。我们可以选择工程师 1 ，工程师 2 和工程师 5 得到最大的团队表现值。表现值为 performance = 
//(2 + 10 + 5) * min(5, 4, 7) = 68 。
// 
//
// 示例 3： 
//
// 输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
//输出：72
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^5 
// speed.length == n 
// efficiency.length == n 
// 1 <= speed[i] <= 10^5 
// 1 <= efficiency[i] <= 10^8 
// 1 <= k <= n 
// 
// Related Topics 贪心算法 排序 
// 👍 53 👎 0


package leetcode.editor.cn.solved;

import java.util.*;

//Java：最大的团队表现值
public class P1383MaximumPerformanceOfATeam {
    public static void main(String[] args) {
        Solution solution = new P1383MaximumPerformanceOfATeam().new Solution();
        // TO TEST
        System.out.println(solution.maxPerformance(6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private final int MOD = 1000000000 + 7;

        class Cook {
            int speed, efficiency;

            public Cook(int speed, int efficiency) {
                this.speed = speed;
                this.efficiency = efficiency;
            }
        }

        public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            Cook[] cooks = new Cook[n];
            for (int i = 0; i < n; i ++) {
                cooks[i] = new Cook(speed[i], efficiency[i]);
            }
            Arrays.sort(cooks, new Comparator<Cook>() {
                @Override
                public int compare(Cook cook1, Cook cook2) {
                    return cook1.efficiency - cook2.efficiency;
                }
            });
            Queue<Cook> queue = new PriorityQueue<>(new Comparator<Cook>() {
                @Override
                public int compare(Cook cook1, Cook cook2) {
                    return cook1.speed - cook2.speed;
                }
            });
            long sum = 0, ans = 0;
            for (int i = n - 1; i >= 0; i --) {
                if (queue.size() < k) {
                    queue.add(cooks[i]);
                    sum += cooks[i].speed;
                } else {
                    Cook cook = queue.peek();
                    if (cook.speed < cooks[i].speed) {
                        queue.poll();
                        queue.add(cooks[i]);
                        sum = sum - cook.speed + cooks[i].speed;
                    }
                }
                ans = Math.max(ans, sum * cooks[i].efficiency);
            }
            return (int) (ans % MOD);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}