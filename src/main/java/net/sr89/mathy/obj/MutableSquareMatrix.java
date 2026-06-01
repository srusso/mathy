package net.sr89.mathy.obj;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class MutableSquareMatrix {
    // min and max values for each element of the matrix
    private static final int MIN = -5;
    private static final int MAX = 5;

    public final int[][] matrix;
    public final int size;

    // current row and column, used by toNext() and hasNext()
    public int row;
    public int column;

    public MutableSquareMatrix(int size) {
        this.matrix = new int[size][size];
        this.size = size;
        this.row = size - 1;
        this.column = size - 1;
        setAll(MIN);
    }

    public void mult(MutableSquareMatrix other, MutableSquareMatrix result) {
        result.setAll(0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    // possible optimization: surround this with if(result.matrix[i][j] != 0)
                    // and remove setAll(0)
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }
    }

    private void setAll(int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = value;
            }
        }
    }

    public void generateAllIteratively(Consumer<MutableSquareMatrix> consumer) {
        consumer.accept(this);
        while(canKeepCounting()) {
            consumeNext(consumer);
        }
    }

    public void generateAllRecursively(int row, int column, Consumer<MutableSquareMatrix> consumer) {
        if (row == size) {
            consumer.accept(this);
            return;
        }

        for (int v = MIN ; v <= MAX; v++) {
            matrix[row][column] = v;

            if (column < size - 1) {
                generateAllRecursively(row, column + 1, consumer);
            } else {
                generateAllRecursively(row + 1, 0, consumer);
            }
        }
    }

    public void toFirst() {
        row = 0;
        column = -1;
        setAll(MIN);
    }

    public MutableSquareMatrix copy() {
        var copy = new MutableSquareMatrix(size);

        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, copy.matrix[i], 0, size);
        }

        return copy;
    }

    private void consumeNext(Consumer<MutableSquareMatrix> consumer) {
        if (matrix[row][column] == MAX) {
            matrix[row][column] = MIN;
            stepBack();
        } else {
            matrix[row][column]++;
            consumer.accept(this);
            row = size - 1;
            column = size - 1;
        }
    }

    private void stepBack() {
        column--;
        if (column < 0) {
            column = size - 1;
            row--;
        }
    }

    private boolean canKeepCounting() {
        final var last = row == 0
                && column == 0
                && matrix[row][column] == MAX;
        return !last;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MutableSquareMatrix that = (MutableSquareMatrix) o;
        return size == that.size && Objects.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(matrix), size);
    }
}
