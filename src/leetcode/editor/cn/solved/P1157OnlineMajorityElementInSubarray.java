//实现一个 MajorityChecker 的类，它应该具有下述几个 API： 
//
// 
// MajorityChecker(int[] arr) 会用给定的数组 arr 来构造一个 MajorityChecker 的实例。 
// int query(int left, int right, int threshold) 有这么几个参数：
// 
// 0 <= left <= right < arr.length 表示数组 arr 的子数组的长度。 
// 2 * threshold > right - left + 1，也就是说阈值 threshold 始终比子序列长度的一半还要大。 
// 
// 
// 
//
// 每次查询 query(...) 会返回在 arr[left], arr[left+1], ..., arr[right] 中至少出现阈值次数 thresh
//old 的元素，如果不存在这样的元素，就返回 -1。 
//
// 
//
// 示例： 
//
// MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
//majorityChecker.query(0,5,4); // 返回 1
//majorityChecker.query(0,3,3); // 返回 -1
//majorityChecker.query(2,3,2); // 返回 2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 20000 
// 1 <= arr[i] <= 20000 
// 对于每次查询，0 <= left <= right < len(arr) 
// 对于每次查询，2 * threshold > right - left + 1 
// 查询次数最多为 10000 
// 
// Related Topics 线段树 数组 二分查找 
// 👍 39 👎 0


package leetcode.editor.cn.solved;

import java.util.ArrayList;
import java.util.List;

//Java：子数组中占绝大多数的元素
public class P1157OnlineMajorityElementInSubarray {
    public static void main(String[] args) {
        MajorityChecker majorityChecker = new P1157OnlineMajorityElementInSubarray().new MajorityChecker(new int[]{1,1,2,2,1,1});
        // TO TEST
        System.out.println(majorityChecker.query(0, 5, 4));
        System.out.println(majorityChecker.query(0, 3, 3));
        System.out.println(majorityChecker.query(2, 3, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MajorityChecker {

        int n;
        int[] a;
        Node root, ans;
        List<List<Integer>> numList;

        class Node {
            int l, r, overHalfNum;
            Node lChild, rChild;
        }

        int getFrequency(int num, int k) {
            if (k < 0) return 0;
            List<Integer> list = numList.get(num);
            if (list.size() == 0) return 0;
            int l = 0, r = list.size() - 1;
            while (l != r) {
                int mid = (l + r + 1) / 2;
                Integer u = list.get(mid);
                if (u <= k) l = mid; else r = mid - 1;
            }
            if (list.get(l) <= k) return l + 1;
            return 0;
        }


        int getFrequency(int num, int l, int r) {
            if (l > r) return 0;
            return getFrequency(num, r) - getFrequency(num, l - 1);
        }


        Node build(int l, int r) {
            Node node = null;
            if (l != r) {
                int mid = (l + r) / 2;
                Node lChild = build(l, mid);
                Node rChild = build(mid + 1, r);
                node = merge(lChild, rChild);
                node.lChild = lChild;
                node.rChild = rChild;
            } else {
                node = new Node();
                node.overHalfNum = a[l];
            }
            node.l = l; node.r = r;
            return node;
        }


        public MajorityChecker(int[] arr) {
            numList = new ArrayList<>();
            for (int i = 0; i <= 20000; i ++) numList.add(new ArrayList<>());
            for (int i = 0; i < arr.length; i ++) {
                numList.get(arr[i]).add(i);
            }
            a = arr;
            n = arr.length;
            root = build(0, n - 1);
        }


        public int query(int left, int right, int threshold) {
            ans = null;
            getOverHalfNum(root, left, right);
            if (ans.overHalfNum == 0) return -1;
            if (getFrequency(ans.overHalfNum, left, right) >= threshold) {
                return ans.overHalfNum;
            }
            return -1;
        }

        void getOverHalfNum(Node node, int left, int right) {
            if (node.l > right || node.r < left) return;
            if (left <= node.l && node.r <= right) {
                if (ans == null) {
                    ans = node;
                } else {
                    ans = merge(ans, node);
                }
                return;
            }
            getOverHalfNum(node.lChild, left, right);
            getOverHalfNum(node.rChild, left, right);
        }

        Node merge(Node p, Node q) { //p.r + 1 need equals q.l
            Node node = new Node();
            node.l = p.l;
            node.r = q.r;
            node.overHalfNum = 0;
            if (p.overHalfNum > 0) {
                if (2 * getFrequency(p.overHalfNum, node.l, node.r) > node.r - node.l + 1) {
                    node.overHalfNum = p.overHalfNum;
                }
            }
            if (q.overHalfNum > 0) {
                if (2 * getFrequency(q.overHalfNum, node.l, node.r) > node.r - node.l + 1) {
                    node.overHalfNum = q.overHalfNum;
                }
            }
            return node;
        }

    }

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
//leetcode submit region end(Prohibit modification and deletion)

}