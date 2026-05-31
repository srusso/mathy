package net.sr89.mathy.comp;

/**
 * A very inefficient way to count.
 */
public class Counting {
    public static void main() {
        int[] array = new int[3];
        final int highestDigit = 9;

        printArray(array);

        int i = array.length - 1;

        while (i >= 0) {
            if (array[i] == highestDigit) {
                array[i] = 0;
                i--;
            } else {
                array[i]++;
                printArray(array);
                i = array.length - 1;
            }
        }
    }

    private static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j);
        }
        System.out.println();
    }
}
