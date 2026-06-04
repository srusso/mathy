package net.sr89.mathy.comp;

import net.sr89.mathy.obj.MutableSquareMatrix;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Inspired by the note at page 175 in Linear Algebra Done Right (4th edition) by Sheldon Axler
 */
public class CommutativeMatrices {
    public long countCommutativeMatrices() {
        final var matrixCount2x2 = 14641;
        final var forPercCalculations = matrixCount2x2 * 2000;
        final var combinations = matrixCount2x2 * matrixCount2x2;

        final var ab = new MutableSquareMatrix(2);
        final var ba = new MutableSquareMatrix(2);

        AtomicLong commutativePairs = new AtomicLong();
        AtomicLong nonCommutativePairs = new AtomicLong();

        new MutableSquareMatrix(2).generateAll(a -> {
            new MutableSquareMatrix(2).generateAll(b -> {
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
