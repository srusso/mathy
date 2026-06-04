package net.sr89.mathy.comp;

import net.sr89.mathy.obj.MutableSquareMatrix;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Inspired by the note at page 175 in Linear Algebra Done Right (4th edition) by Sheldon Axler
 */
public class CommutativeMatrices {
    public long countCommutativeMatrices(final int matrixSize, final int minValue, final int maxValue) {
        final var matrixCount = Math.powExact((maxValue - minValue + 1), matrixSize * matrixSize);
        final var forPercCalculations = matrixCount * 2000;
        final var combinations = matrixCount * matrixCount;

        final var ab = new MutableSquareMatrix(matrixSize);
        final var ba = new MutableSquareMatrix(matrixSize);

        final var commutativePairs = new AtomicLong();
        final var nonCommutativePairs = new AtomicLong();

        new MutableSquareMatrix(matrixSize).generateAll(minValue, maxValue, a -> {
            new MutableSquareMatrix(matrixSize).generateAll(minValue, maxValue, b -> {
                a.mult(b, ab);
                b.mult(a, ba);

                if(ab.equals(ba)) {
                    commutativePairs.incrementAndGet();
                } else {
                    nonCommutativePairs.incrementAndGet();
                }

                if ((commutativePairs.get() + nonCommutativePairs.get()) % forPercCalculations == 0) {
                    System.out.println(cutToTwoDecimalDigits((commutativePairs.get() + nonCommutativePairs.get()) * 100.0 / combinations)  + "% done");
                }
            });
        });

        return commutativePairs.get();
    }

    private static String cutToTwoDecimalDigits(double v) {
        return String.format("%.2f", v);
    }
}
