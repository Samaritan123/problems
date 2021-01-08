//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 807 👎 0


package leetcode.editor.cn.solved;
//Java：K 个一组翻转链表
public class P25ReverseNodesInKGroup{
      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.reverseKGroup(listNode1, 2));

        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    private int len = 0, n;

    public ListNode reverseKGroup(ListNode head, int k) {
        n = k;
        ListNode p = head;
        while (p != null) {
            len ++;
            p = p.next;
        }
        return reverse(head, 1);
    }

    private ListNode reverse(ListNode head, int i) {
        if (len - i + 1 <= len % n) return head;
        ListNode node = null;
        if (head.next != null) {
            node = reverse(head.next, i + 1);
        }
        if (i % n == 0) {
            head.next = node;
            return head;
        } else {
            ListNode p = head.next;
            head.next = p.next;
            p.next = head;
            return node;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}