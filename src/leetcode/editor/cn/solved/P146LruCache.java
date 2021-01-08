//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 
// 👍 1022 👎 0


package leetcode.editor.cn.solved;

import java.util.HashMap;
import java.util.Map;

//Java：LRU 缓存机制
public class P146LruCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new P146LruCache().new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        private Map<Integer, Node> map = new HashMap<>();
        private int capacity;
        private Node head, tail;

        class Node {
            int k, v;
            Node next, pre;

            public Node(int k, int v, Node next, Node pre) {
                this.k = k;
                this.v = v;
                this.next = next;
                this.pre = pre;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node(0, 0, null, null);
            tail = new Node(0, 0, null, head);
            head.next = tail;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
            return node.v;
        }

        public void put(int key, int value) {
            int k = get(key);
            if (k != -1) {
                Node pre = tail.pre;
                pre.v = value;
            } else {
                if (map.size() == capacity) {
                    Node next = head.next;
                    map.remove(next.k);
                    head.next = next.next;
                    next.next.pre = head;
                }
                Node node = new Node(key, value, tail, tail.pre);
                map.put(key, node);
                tail.pre.next = node;
                tail.pre = node;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}