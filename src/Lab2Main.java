/* Koon Chua
 * EN 605.202.81
 * Lab 2
 *
 * Lab 2 Driver
 * Uses ReadWrite to open an input file, and write to an output file
 * Instantiate a matrix using the ArrayMatrix class
 * Run computeDeterminant to calculate the determinant for a nxn matrix
 * The output file is written with the input matrix followed by the calculated determinant
 */

import java.util.*;

public class Lab2Main {
    public static void main(String[] args) {
        Scanner scanner;
        ArrayMatrix matrix;
        int det;                // determinant of input matrix
        int val;                // value inserted into ArrayMatrix
        int mat_size;           // size of input matrix
        String err_msg;         // Error handling messages for different scenarios

        // runtime metric
        long startTime = System.currentTimeMillis();

        // initiate main algorithm
        try {

            ReadWrite readWrite = new ReadWrite(args[0], args[1]);

            while (readWrite.hasNextInput()) {
                scanner = new Scanner(readWrite.nextInput());
                if (scanner.hasNextInt()) {
                    mat_size = scanner.nextInt();

                    // Case when there is an empty line in the input file
                } else {
                    err_msg = "Empty line in input file. Check Input.";
                    System.out.println(err_msg);
                    readWrite.writeOutput(err_msg);
                    break;
                }

                // Case when input file indicates 0 or number less than 1 for matrix size
                if (scanner.hasNext()) {
                    err_msg = "Matrix size should only be 1 argument. Check Input.";
                    System.out.println(err_msg);
                    readWrite.writeOutput(err_msg);
                    break;

                } else {
                    matrix = new ArrayMatrix();
                    int mat_col = 1;
                    int mat_row = 1;

                    // Populate  Matrix
                    for (int i = 1; i <= mat_size; i++) {

                        // Scan each line of matrix
                        scanner = new Scanner(readWrite.nextInput());
                        if (scanner.hasNextInt()) {
                            while (scanner.hasNextInt()) {
                                val = scanner.nextInt();
                                matrix.insertVal(mat_col, mat_row, val);
                                mat_row++;
                            }
                            mat_col++;
                            mat_row = 1;
                        }
                    }

                    // Given a square matrix, compute the determinant
                    if (matrix.checkSquare()) {
                        Determinant detCalc = new Determinant();
                        det = detCalc.computeDeterminant(matrix);
                        readWrite.writeOutput(matrix.toString());
                        readWrite.writeOutput("The Determinant of the matrix is: " + String.valueOf(det) + "\n\n");

                    // Error handling: matrix not square
                    } else {
                        readWrite.writeOutput(matrix.toString());
                        err_msg = "Matrix not square. Check Input";
                        readWrite.writeOutput(err_msg);
                    }
                }

            }
            readWrite.closeOutput();

            // runtime calculations
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("\n The program took " + elapsedTime + " ms to run!");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("there was an input/output parameter issue, check input file");
        }
    }
}
