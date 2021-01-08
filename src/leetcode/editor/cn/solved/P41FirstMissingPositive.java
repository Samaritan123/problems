//给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,0]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [3,4,-1,1]
//输出: 2
// 
//
// 示例 3: 
//
// 输入: [7,8,9,11,12]
//输出: 1
// 
//
// 
//
// 提示： 
//
// 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。 
// Related Topics 数组 
// 👍 870 👎 0


package leetcode.editor.cn.solved;

//Java：缺失的第一个正数
public class P41FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new P41FirstMissingPositive().new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(solution.firstMissingPositive(new int[]{1,2,0}));
        System.out.println(solution.firstMissingPositive(new int[]{7,8,9,11,12}));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i ++) {
                if (nums[i] < 1 || nums[i] > n) {
                    nums[i] = 0;
                }
            }
            for (int i = 0; i < n; i ++) {
                if (nums[i] != 0 && nums[i] != -(n + 1)) {
                    int k = nums[i];
                    if (nums[i] < 0) {
                        k = nums[i] + n + 1;
                    }
                    if (nums[k - 1] >= 0) nums[k - 1] -= n + 1;
                }
            }
            int ans  = n + 1;
            for (int i = 0; i < n; i ++) {
                if (nums[i] >= 0) {
                    ans = i + 1;
                    break;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}