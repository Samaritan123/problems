//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组 
// 👍 330 👎 0


package leetcode.editor.cn.solved;

//Java：插入区间
public class P57InsertInterval {
    public static void main(String[] args) {
        int a[][] = {{1, 5}, {6, 9}};
        Solution solution = new P57InsertInterval().new Solution();
        System.out.println(solution.insert(a, new int[]{2, 5}));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length, k = 0, flag = 0, l = newInterval[0], r = newInterval[1];
            int[][] s = new int[n + 5][2];
            for (int i = 0; i < n; i ++) {
                if (intervals[i][1] < l || intervals[i][0] > r) {
                    if (intervals[i][0] > r && flag == 0) {
                        s[k][0] = l;
                        s[k][1] = r;
                        k ++;
                        flag = 1;
                    }
                    s[k][0] = intervals[i][0];
                    s[k][1] = intervals[i][1];
                    k ++;
                } else {
                    l = Math.min(l, intervals[i][0]);
                    r = Math.max(r, intervals[i][1]);
                }

            }
            if (flag == 0) {
                s[k][0] = l;
                s[k][1] = r;
                k ++;
            }
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; i ++) {
                ans[i][0] = s[i][0];
                ans[i][1] = s[i][1];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}