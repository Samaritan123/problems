//给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 929 👎 0


package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

//Java：子集
public class P78Subsets {
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<List<Integer>> set =  new HashSet<>();
        public List<List<Integer>> subsets(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < (1 << n); i ++) {
                List<Integer> list = new ArrayList<>();
                for (int k = 0; k < n; k ++) {
                    if (((1 << k) & i) > 0) {
                        list.add(nums[k]);
                    }
                }
                Collections.sort(list);
                set.add(list);
            }
            return new ArrayList<>(set);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}