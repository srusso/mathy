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
        this.row = 0;
        this.column = -1;
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

    public void generateAll(int row, int column, Consumer<MutableSquareMatrix> print) {
        if (row == size) {
            print.accept(this);
            return;
        }

        for (int v = MIN ; v <= MAX; v++) {
            matrix[row][column] = v;

            if (column < size - 1) {
                generateAll(row, column + 1, print);
            } else {
                generateAll(row + 1, 0, print);
            }
        }
    }

    public void toFirst() {
        row = 0;
        column = -1;
        setAll(MIN);
    }

    public void toNext() {
        if (column == -1) {
            column = 0;
        } else if (matrix[row][column] < MAX) {
            matrix[row][column]++;
        } else if (column < size - 1) {
            column++;
        } else if (row < size - 1) {
            row++;
            column = 0;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean hasNext() {
        return row < size - 1 || column < size - 1;
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
