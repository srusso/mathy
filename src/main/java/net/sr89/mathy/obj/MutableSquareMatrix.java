package net.sr89.mathy.obj;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class MutableSquareMatrix {
    public final int[][] matrix;
    public final int size;

    // current row and column, used by toNext() and hasNext()
    public int row;
    public int column;

    public MutableSquareMatrix(int size) {
        this.matrix = new int[size][size];
        this.size = size;
    }

    public void mult(MutableSquareMatrix other, MutableSquareMatrix result) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                result.matrix[i][j] = 0;

                for (int k = 0; k < size; k++) {
                    int value = matrix[i][k] * other.matrix[k][j];
                    result.matrix[i][j] += value;
                }
            }
        }
    }

    /**
     * Same logic as {@link net.sr89.mathy.comp.Counting}
     */
    public void generateAll(int min, int max, Consumer<MutableSquareMatrix> consumer) {
        row = size - 1;
        column = size - 1;
        setAll(min);

        consumer.accept(this);

        while (!(row == 0 && column == 0
                && matrix[row][column] == max)) {
            if (matrix[row][column] == max) {
                matrix[row][column] = min;

                column--;
                if (column < 0) {
                    column = size - 1;
                    row--;
                }
            } else {
                matrix[row][column]++;
                consumer.accept(this);
                row = size - 1;
                column = size - 1;
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
