package linkedlists;
public class SpiralMatrixII {

    public static void main(String[] args) {
        SpiralMatrixII spiralMatrixII = new SpiralMatrixII();
        int[][] res = spiralMatrixII.generateMatrix(5);
//        int[][] res = spiralMatrixII.generateMatrix(4);
    }
    public int[][] generateMatrix(int A) {

        int[][] res = new int[A][A];

        int i = 0, j = 0;
        int seq = 1;
        int cycle = 0;
        while ( cycle < A/2) {

            //left to right
            while (j < A - cycle) {
                res[i][j++] = seq++;
            }

            j--;
            i++;
            //right to bottom
            while (i < A - cycle) {
                res[i++][j] = seq++;
            }
            i--;
            j--;

            //right to left
            while (j >= cycle) {
                res[i][j--] = seq++;
            }
            j++;
            i--;

            //left to top
            while (i > cycle) {
                res[i--][j] = seq++;
            }
            i++;
            j++;

            cycle++;
        }
        if ((A & 1) == 1) {
            res[i][j] = seq;
        }
        return res;
    }
}
