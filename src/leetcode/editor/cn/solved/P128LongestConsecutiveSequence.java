//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 
// 👍 633 👎 0


package leetcode.editor.cn.solved;

import java.util.HashMap;
import java.util.Map;

//Java：最长连续序列
public class P128LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new P128LongestConsecutiveSequence().new Solution();
        // TO TEST
        System.out.println(solution.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        Map<Integer, Integer> map = new HashMap<>();

        int[] fa, sz;

        public int longestConsecutive(int[] nums) {
            int k = 1;
            for (int num : nums) {
                Integer x = map.get(num);
                if (x == null) {
                    map.put(num, k);
                    k++;
                }
            }
            fa = new int[k];
            sz = new int[k];
            for (int i = 1; i < k; i ++) sz[i] = 1;
            for (int num : nums) {
                Integer p = map.get(num);
                Integer q = map.get(num + 1);
                if (q != null) {
                    union(p, q);
                }
            }
            int ans = 0;
            for (int i = 1; i < k; i ++) {
                if (sz[i] > ans) ans = sz[i];
            }
            return ans;
        }

        void union(Integer p, Integer q) {
            int fp = find(p), fq = find(q);
            if (fp == fq) return;
            sz[fq] += sz[fp];
            fa[fp] = fq;
        }

        int find(Integer p) {
            if (fa[p] == 0) return p;
            fa[p] = find(fa[p]);
            return fa[p];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}