package net.sr89.mathy.comp;

/**
 * A very inefficient way to count.
 */
public class Counting {
    public static void main() {
        int[] digits = new int[3];
        final int highestDigit = 9;

        printArray(digits);

        int i = digits.length - 1;

        while (i >= 0) {
            if (digits[i] == highestDigit) {
                digits[i] = 0;
                i--;
            } else {
                digits[i]++;
                printArray(digits);
                i = digits.length - 1;
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
