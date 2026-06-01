package net.sr89.mathy.comp;

import net.sr89.mathy.obj.MutableSquareMatrix;

import java.time.Duration;
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

        var t = System.nanoTime();
        new MutableSquareMatrix(2).generateAll(mat -> matrices.add(mat.copy()));
        System.out.println("Total time: " + Duration.ofNanos((System.nanoTime() - t)).toMillis() + "ms");

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
}
