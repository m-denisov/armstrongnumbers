package com.mezgan;

import java.util.*;

public class ArmstrongNumbers {
    static long[][] degrees;

    public static List<Long> getArmstrongNumbers(long N) {
        List<Long> numberList = new ArrayList<>();

        int lengthN = String.valueOf(N).length();

        degrees = fillingDegrees(lengthN);

        for (int i = 1; i < lengthN + 1; i++) {
            int[] num = new int[i];
            Arrays.fill(num, 0);
            recursiveArmstrongCalculate(numberList, num, i-1, 0);
        }

        numberList.removeIf(aLong -> aLong >= N);
        Collections.sort(numberList);

        return numberList;
    }

    /**
     * Method fills list @param numberList
     * @param numberList list for filling
     * @param array with length 1...lenN+1
     * @param currentIndex in the array
     * @param lastValue array[currentIndex]
     */
    private static void recursiveArmstrongCalculate(List<Long> numberList, int[] array, int currentIndex, int lastValue) {
        array[currentIndex] = lastValue;
        int indexInTable = array.length - 1;

        while (array[currentIndex] < 10) {
            if (currentIndex > 0) {
                recursiveArmstrongCalculate(numberList, array, currentIndex -1, array[currentIndex]);
            } else {
                long powerSum = 0;
                for (int n : array) {
                    powerSum += degrees[indexInTable][n];
                }

                if (checkNumber(powerSum, array.length)) {
                    numberList.add(powerSum);
                }
            }
            array[currentIndex]++;
        }
    }

    /**
     * Method checks if a power sum of a number is equal to the number
     * @param powerSum is a power sum of a number
     * @param numberLength is number of digits
     * @return result of checking
     */
    private static boolean checkNumber(long powerSum, int numberLength) {
        long workNumberPowerSum = powerSum;
        int[] numbersPowerSum = new int[numberLength];

        long pow = 1;
        int numberLengthDecrement = numberLength - 1;
        for (int i = 0; i < numberLengthDecrement; i++) {
            pow *= 10;
        }

        long mostSignificant = workNumberPowerSum / pow;
        if ( (mostSignificant > 9) || (mostSignificant < 1) ) {
            return false;
        }

        for (int i = numberLength - 1; i > -1; i--) {
            long rest = workNumberPowerSum % 10;
            numbersPowerSum[i] = (int) rest;
            workNumberPowerSum = workNumberPowerSum / 10;
        }

        long powerSumCheck = 0;
        for (int n : numbersPowerSum) {
            long p = 1;
            int arl = numbersPowerSum.length;
            for (int i = 0; i < arl; i++) {
                p *= n;
            }
            powerSumCheck += p;
        }

        return powerSumCheck == powerSum;
    }


    /**
     * Method fills array with powers 1...lengthN of numbers 0...9
     * @param lengthN is length of given in method <code>getArmstrongNumbers()</code> number
     * @return the table with numbers from 0 to 9 (decimal notation) to the power from 1 to lengthN
     */
    private static long[][] fillingDegrees(int lengthN) {
        long[][] degrees = new long[lengthN][10];
        for (int i = 0; i < lengthN; i++) {
            for (int j = 0; j < 10; j++) {
                long pow = 1;
                for (int x = 0; x < i+1; x++)
                    pow *= j;
                degrees[i][j] = pow;
            }
        }
        return degrees;
    }
}
