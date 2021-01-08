//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 1041 👎 0


package leetcode.editor.cn.solved;

import java.util.Stack;

//Java：柱状图中最大的矩形
public class P84LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new P84LargestRectangleInHistogram().new Solution();
        // TO TEST
        System.out.println(solution.largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] l = new int[n], r = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i ++) {
                while (!stack.empty()) {
                    Integer top = stack.pop();
                    if (heights[top] < heights[i]) {
                        stack.push(top);
                        break;
                    }
                }
                if (!stack.empty()) {
                    Integer peek = stack.peek();
                    l[i] = peek;
                } else {
                    l[i] = -1;
                }
                stack.push(i);
            }
            stack.clear();
            for (int i = n - 1; i >= 0; i --) {
                while (!stack.empty()) {
                    Integer top = stack.pop();
                    if (heights[top] < heights[i]) {
                        stack.push(top);
                        break;
                    }
                }
                if (!stack.empty()) {
                    Integer peek = stack.peek();
                    r[i] = peek;
                } else {
                    r[i] = n;
                }
                stack.push(i);
            }
            int ans = 0;
            for (int i = 0; i < n; i ++) {
                ans = Math.max(heights[i] * (r[i] - l[i] - 1), ans);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}