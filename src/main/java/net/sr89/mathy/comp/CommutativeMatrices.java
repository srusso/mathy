package net.sr89.mathy.comp;

import net.sr89.mathy.obj.MutableSquareMatrix;

import java.util.concurrent.atomic.AtomicInteger;

public class CommutativeMatrices {
    public static void main() {
        var i = new MutableSquareMatrix(2);

//        i.generateAll(0, 0, CommutativeMatrices::printMatrix);

        AtomicInteger count = new AtomicInteger();

        i.generateAll(0, 0, m -> count.incrementAndGet());

        System.out.println("Count: " + count.get());
    }

    public static void main2() {
        var i = new MutableSquareMatrix(2);
        var j = new MutableSquareMatrix(2);
        var ij = new MutableSquareMatrix(2);
        var ji = new MutableSquareMatrix(2);

        long commutativePairs = 0;
        long nonCommutativePairs = 0;

        while(i.hasNext()) {
            i.toNext();

            while(j.hasNext()) {
                j.toNext();

                i.mult(j, ij);
                j.mult(i, ji);

                if(ij.equals(ji)) {
                   commutativePairs++;
                } else {
                    nonCommutativePairs++;
                }

                if ((commutativePairs + nonCommutativePairs) % 100 == 0) {
                    System.out.println("Done: " + (commutativePairs + nonCommutativePairs));
                }
            }

            j.toFirst();
        }

        System.out.println("Total pairs: " + (commutativePairs + nonCommutativePairs));
        System.out.println("Commutative pairs: " + commutativePairs);
        System.out.println("Non commutative pairs: " + nonCommutativePairs);
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
