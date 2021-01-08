//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3460 👎 0


package leetcode.editor.cn.solved;

//Java：寻找两个正序数组的中位数
public class P4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1,5}, new int[]{2,4}));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int findKth(int[] nums1, int[] nums2, int l1, int r1, int l2, int r2, int k) {
            if (l1 > r1 || l2 > r2) {
                if (l1 <= r1) {
                    return nums1[l1 + k - 1];
                } else {
                    return nums2[l2 + k - 1];
                }
            } else {
                if (k == 1) {
                    return Math.min(nums1[l1], nums2[l2]);
                }
                int len1 = r1 - l1 + 1, len2 = r2 - l2 + 1;
                int mid1 = Math.min(len1, k / 2), mid2 = Math.min(len2, k / 2);
                if (nums1[l1 + mid1 - 1] <= nums2[l2 + mid2 - 1]) {
                    return findKth(nums1, nums2, l1 + mid1, r1, l2, r2, k - mid1);
                } else {
                    return findKth(nums1, nums2, l1, r1, l2 + mid2, r2, k - mid2);
                }
            }
        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length, len = len1 + len2;
            if (len % 2 == 1){
                return findKth(nums1, nums2, 0, len1 - 1, 0, len2 - 1, (len + 1) / 2);
            } else {
                int p = findKth(nums1, nums2, 0, len1 - 1, 0, len2 - 1, len / 2);
                int q = findKth(nums1, nums2, 0, len1 - 1, 0, len2 - 1, len / 2 + 1);
                return (p + q) / 2.0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}