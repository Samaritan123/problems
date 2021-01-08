//我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量 capacity 都相同。 
//
// 实现一个叫「餐盘」的类 DinnerPlates： 
//
// 
// DinnerPlates(int capacity) - 给出栈的最大容量 capacity。 
// void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。 
// int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。 
// int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -
//1。 
// 
//
// 
//
// 示例： 
//
// 输入： 
//["DinnerPlates","push","push","push","push","push","popAtStack","push","push",
//"popAtStack","popAtStack","pop","pop","pop","pop","pop"]
//[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
//输出：
//[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
//
//解释：
//DinnerPlates D = DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
//D.push(1);
//D.push(2);
//D.push(3);
//D.push(4);
//D.push(5);         // 栈的现状为：    2  4
//                                    1  3  5
//                                    ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 2。栈的现状为：      4
//                                          1  3  5
//                                          ﹈ ﹈ ﹈
//D.push(20);        // 栈的现状为：  20  4
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.push(21);        // 栈的现状为：  20  4 21
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
//                                            1  3  5
//                                            ﹈ ﹈ ﹈
//D.popAtStack(2);   // 返回 21。栈的现状为：       4
//                                            1  3  5
//                                            ﹈ ﹈ ﹈ 
//D.pop()            // 返回 5。栈的现状为：        4
//                                            1  3 
//                                            ﹈ ﹈  
//D.pop()            // 返回 4。栈的现状为：    1  3 
//                                           ﹈ ﹈   
//D.pop()            // 返回 3。栈的现状为：    1 
//                                           ﹈   
//D.pop()            // 返回 1。现在没有栈。
//D.pop()            // 返回 -1。仍然没有栈。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 20000 
// 1 <= val <= 20000 
// 0 <= index <= 100000 
// 最多会对 push，pop，和 popAtStack 进行 200000 次调用。 
// 
// Related Topics 设计 
// 👍 24 👎 0


package leetcode.editor.cn.solved;



import java.util.*;

//Java：餐盘栈
public class P1172DinnerPlateStacks {
    public static void main(String[] args) {
        DinnerPlates dinnerPlates = new P1172DinnerPlateStacks().new DinnerPlates(2);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        dinnerPlates.push(4);
        dinnerPlates.push(5);
        System.out.println(dinnerPlates.popAtStack(0));
        dinnerPlates.push(20);
        dinnerPlates.push(21);
        System.out.println(dinnerPlates.popAtStack(0));
        System.out.println(dinnerPlates.popAtStack(2));
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class DinnerPlates {

        List<Stack<Integer>> stackList = new ArrayList<>();

        Integer capacity;

        List<Integer> nums = new ArrayList<>();

        Queue<Integer> hasEleQueue;

        Queue<Integer> notFullQueue;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            hasEleQueue = new PriorityQueue<>((o1, o2) -> {
                return o2 - o1;
            });
            notFullQueue =  new PriorityQueue<>();
        }

        public void push(int val) {
            while (!notFullQueue.isEmpty()) {
                Integer k = notFullQueue.peek();
                if (stackList.get(k).size() == capacity) {
                    notFullQueue.poll();
                } else {
                    break;
                }
            }
            if (notFullQueue.isEmpty()) {
                Stack<Integer> stack = new Stack<>();
                stack.add(val);
                stackList.add(stack);
                if (stack.size() < capacity) {
                    notFullQueue.add(stackList.size() - 1);
                }
                hasEleQueue.add(stackList.size() - 1);
            } else {
                Integer k = notFullQueue.peek();
                stackList.get(k).add(val);
                if (stackList.get(k).size() == 1) {
                    hasEleQueue.add(k);
                }
            }
        }

        public int pop() {
            while (!hasEleQueue.isEmpty()) {
                Integer k = hasEleQueue.peek();
                if (stackList.get(k).size() == 0) {
                    hasEleQueue.poll();
                } else {
                    break;
                }
            }
            if (hasEleQueue.isEmpty()) return -1;
            Integer k = hasEleQueue.peek();
            Integer x = stackList.get(k).pop();
            if (stackList.get(k).size() == capacity - 1) {
                notFullQueue.add(k);
            }
            return x;
        }

        public int popAtStack(int index) {
            if (index >= stackList.size()) return -1;
            if (stackList.get(index).size() == 0) return -1;
            Integer x = stackList.get(index).pop();
            if (stackList.get(index).size() == capacity - 1) {
                notFullQueue.add(index);
            }
            return x;
        }
    }

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

}