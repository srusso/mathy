package net.sr89.mathy.comp;

import net.sr89.mathy.obj.MutableSquareMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Inspired by the note at page 175 in Linear Algebra Done Right (4th edition) by Sheldon Axler
 */
public class CommutativeMatrices {
    public static void main() {
        final var matrixCount2x2 = 14641;
        final var forPercCalculations = matrixCount2x2 * 1000;
        final var combinations = matrixCount2x2 * matrixCount2x2;
        final List<MutableSquareMatrix> matrices = new ArrayList<>(matrixCount2x2);

        new MutableSquareMatrix(2).generateAllRecursively(0, 0, mat -> matrices.add(mat.copy()));

        final var ab = new MutableSquareMatrix(2);
        final var ba = new MutableSquareMatrix(2);

        long commutativePairs = 0;
        long nonCommutativePairs = 0;

        for (int i = 0; i < matrixCount2x2; i++) {
            var a = matrices.get(i);
            for (int j = 0; j < matrixCount2x2; j++) {
                var b = matrices.get(j);

                a.mult(b, ab);
                b.mult(a, ba);

                if(ab.equals(ba)) {
                    commutativePairs++;
                } else {
                    nonCommutativePairs++;
                }

                if ((commutativePairs + nonCommutativePairs) % forPercCalculations == 0) {
                    System.out.println(cutToTwoDecimalDigits((commutativePairs + nonCommutativePairs) * 100.0 / combinations)  + "% done");
                }
            }
        }

        final var totalPairs = commutativePairs + nonCommutativePairs;
        System.out.println("Total pairs: " + totalPairs);
        System.out.println("Commutative pairs: " + commutativePairs + " (" + cutToTwoDecimalDigits((100.0 * commutativePairs / totalPairs)) + "%)");
    }

    private static String cutToTwoDecimalDigits(double v) {
        return String.format("%.2f", v);
    }

    public static void main3() {
        var i = new MutableSquareMatrix(2);

        List<MutableSquareMatrix> matrices = new ArrayList<>();
        List<MutableSquareMatrix> matrices2 = new ArrayList<>();

        i.generateAllRecursively(0, 0, m -> matrices.add(m.copy()));
        i.generateAllIteratively(m -> matrices2.add(m.copy()));

        System.out.println("Count (recursive): " + matrices.size());
        System.out.println("Count (iterative): " + matrices2.size());
    }

    private static void printMatrix(MutableSquareMatrix matrix) {
        for (int i = 0; i < matrix.size; i++) {
            for (int j = 0; j < matrix.size; j++) {
                System.out.print(matrix.matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }
}
