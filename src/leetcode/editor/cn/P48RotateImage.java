//给定一个 n × n 的二维矩阵表示一个图像。 
//
// 将图像顺时针旋转 90 度。 
//
// 说明： 
//
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。 
//
// 示例 1: 
//
// 给定 matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// 示例 2: 
//
// 给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
// Related Topics 数组 
// 👍 746 👎 0


package leetcode.editor.cn;

//Java：旋转图像
public class P48RotateImage {
    public static void main(String[] args) {
        Solution solution = new P48RotateImage().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            int p = n / 2, q = (n + 1) / 2;
            for (int i = 0; i < p; i ++) {
                for (int j = 0; j < q; j ++) {
                    int n0 = i, m0 = j;
                    int n1 = m0, m1 = n - 1 - n0;
                    int n2 = n - 1 - n0, m2 = n - 1 - m0;
                    int n3 = n - 1 - n1, m3 = n - 1 - m1;
                    int t = matrix[n3][m3];
                    matrix[n3][m3] = matrix[n2][m2];
                    matrix[n2][m2] = matrix[n1][m1];
                    matrix[n1][m1] = matrix[n0][m0];
                    matrix[n0][m0] = t;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}