//对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。 
//
// 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。 
//
// 
//
// 示例 1： 
//
// 
//输入："13"
//输出："3"
//解释：13 的 3 进制是 111。
// 
//
// 示例 2： 
//
// 
//输入："4681"
//输出："8"
//解释：4681 的 8 进制是 11111。
// 
//
// 示例 3： 
//
// 
//输入："1000000000000000000"
//输出："999999999999999999"
//解释：1000000000000000000 的 999999999999999999 进制是 11。
// 
//
// 
//
// 提示： 
//
// 
// n的取值范围是 [3, 10^18]。 
// 输入总是有效且没有前导 0。 
// 
//
// 
// Related Topics 数学 二分查找 
// 👍 48 👎 0


package leetcode.editor.cn.solved;

//Java：最小好进制
public class P483SmallestGoodBase {
    public static void main(String[] args) {
        Solution solution = new P483SmallestGoodBase().new Solution();
        // TO TEST
        System.out.println(solution.smallestGoodBase("1000000000000000000"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String smallestGoodBase(String n) {
            long N = Long.parseLong(n);
            for (int t = 63; t >= 2; t --) {
                long l = 2, r = N - 1;
                while (l != r) {
                    long mid = (l + r + 1) / 2;
                    if (less(mid, t, N)) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                if (equal(l, t, N)) return Long.toString(l);
            }
            return null;
        }

        boolean equal(long k, int t, long n) {
            long sum = 1, p = 1;
            for (int i = 1; i < t; i ++) {
                p = mul(p, k);
                if (p == -1) return false;
                sum = add(sum, p);
                if (sum == -1) return false;
                if (sum > n) return false;
            }
            return sum == n;
        }

        boolean less(long k, int t, long n) {
            long sum = 1, p = 1;
            for (int i = 1; i < t; i ++) {
                p = mul(p, k);
                if (p == -1) return false;
                sum = add(sum, p);
                if (sum == -1) return false;
                if (sum > n) return false;
            }
            return true;
        }

        long add(long p, long q) {
            long w;
            try {
                w = Math.addExact(p, q);
            } catch (Exception e) {
                w = -1;
            }
            return w;
        }

        long mul(long p, long q) {
            long w;
            try {
                w = Math.multiplyExact(p, q);
            } catch (Exception e) {
                w = -1;
            }
            return w;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}