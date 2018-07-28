/* Koon Chua
 * EN 605.202.81
 * Lab 2
 *
 * Matrix class that instantiates Array-based matrix
 * Contains Value, insertVal, checkSquare, numRow, numCol, and toString methods
 */

public class ArrayMatrix {
    private int size_i; // num rows
    private int size_j; // num cols
    private int[][] matrix;
    private int empty = -9999;

    /**
     * Constructor: create empty 2d matrix
     * default 1-based, 10x10
     *
     */
    public ArrayMatrix() {
        int n = 10; //dimension of matrix\
        matrix = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                matrix[i][j] = empty;
            }
        }
        size_i = 0;
        size_j = 0;
    }

    public int Value(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Insert value into matrix at the given indices
     * @param i     column index
     * @param j     row index
     * @param val   value to be inserted
     */
    public void insertVal(int i, int j, int val) {
        matrix[i][j] = val;
        if (i > size_i) {
            size_i = i;
        }
        if (j > size_j) {
            size_j = j;
        }
    }

    /**
     * Test if matrix is square
     * @return true if matrix is square
     */
    public boolean checkSquare() {
        return (size_i == size_j);
    }

    public int numRow() {
        return size_i;
    }

    public int numCol() {
        return size_j;
    }

    /**
     * Create String representation of the matrix
     * @return Matrix as a String-type for printing
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= size_i; i++) {
            for (int j = 1; j <= size_j; j++) {
                if (Value(i, j) != empty) {
                    output += String.valueOf(Value(i,j));
                    output += " ";
                }
            }
            output += "\n";
        }
        return output;
    }
}