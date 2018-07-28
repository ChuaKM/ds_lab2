/* Koon Chua
 * EN 605.202.81
 * Lab 2
 *
 * This class calculates the cofactor and determinant of a nxn matrix
 */

public class Determinant {

    /**
     * Function that recursively calculates the determinant of our input matrix
     * @param matrix    input matrix
     * @return          value of calculated determinant
     */
    public int computeDeterminant(ArrayMatrix matrix) {
        int sum = 0;
        final int col_num = 1;
        final int exp_sign = -1;

        // Base case when n = 1,
        if (matrix.numRow() == 1) {
            return matrix.Value(1,1);
//
//        // Increase efficiency by not recursing to n = 1 base case
//        // Hard-coded calculations for determinant of 2x2 and 3x3 matrix
//        // Case when n = 2,
//        } else if (matrix.numRow() == 2) {
//            sum = (matrix.Value(1,1) * matrix.Value(2,2)) - (matrix.Value(2,1) * matrix.Value(1,2));
//            return sum;
//
//        // Case when n = 3,
//        } else if (matrix.numRow() == 3) {
//            sum = matrix.Value(1,1) * (matrix.Value(2,2) * matrix.Value(3,3) - matrix.Value(3,2) * matrix.Value(2,3))
//                    - matrix.Value(2,1) * (matrix.Value(1,2) * matrix.Value(3,3) - matrix.Value(3,2) * matrix.Value(1,3))
//                    + matrix.Value(3,1) * (matrix.Value(1,2) * matrix.Value(2,3) - matrix.Value(2,2) * matrix.Value(1,3));
//            return sum;

        // Recursive Step
        } else {
            for (int row = 1; row <= matrix.numRow(); row++) {
                int sign = (int) Math.pow(exp_sign, col_num + row);
                sum += sign * matrix.Value(row, col_num) * computeDeterminant(computeCofactor(matrix, row, col_num));
            }
            return sum;
        }
    }

    /**
     * Function that creates cofactor from the original matrix
     * @param matrix    original matrix
     * @param row       row that is "removed"
     * @param col       column that is "removed"
     * @return          ArrayMatrix that is the cofactor of the original
     */
    public ArrayMatrix computeCofactor(ArrayMatrix matrix, int row, int col) {
        int i_orig = 1;
        int j_orig = 1;

        ArrayMatrix cofactor = new ArrayMatrix();

        if (matrix.checkSquare() && matrix.numCol() == 1) {
            System.out.println("1x1 matrix, no minor");

        } else {

            // matrix must have 1 less row
            for (int i = 1; i < matrix.numRow(); i++) {
                // skip row that contains this element
                if (i == row) {
                    i_orig++;
                }
                // matrix must have 1 less column
                for (int j = 1; j < matrix.numCol(); j++) {
                    // skip column that contains this element
                    if (j == col) {
                        j_orig++;
                    }
                    cofactor.insertVal(i, j, matrix.Value(i_orig, j_orig));
                    j_orig++;
                }
                j_orig = 1; // Reset to column to grab first value
                i_orig++; // Next row
            }
        }
        return cofactor;
    }
}
