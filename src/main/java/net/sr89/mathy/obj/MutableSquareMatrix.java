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

    /**
     * Same logic as {@link net.sr89.mathy.comp.Counting}
     */
    public void generateAll(Consumer<MutableSquareMatrix> consumer) {
        row = size - 1;
        column = size - 1;
        setAll(MIN);

        consumer.accept(this);

        while (!(row == 0 && column == 0
                && matrix[row][column] == MAX)) {
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
    }

    private void stepBack() {
        column--;
        if (column < 0) {
            column = size - 1;
            row--;
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
