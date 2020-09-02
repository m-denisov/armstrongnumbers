package com.mezgan;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long d = new Date().getTime();
        long memoryStart = Runtime.getRuntime().freeMemory();
        List<Long> armstrongs = ArmstrongNumbers.getArmstrongNumbers(Long.MAX_VALUE);
        System.out.println("found " + armstrongs.size() + " Armstrong numbers");
        for (int i = 1; i < armstrongs.size() + 1; i++)
            System.out.println(i + ": " + armstrongs.get(i - 1));
        long memoryEnd = Runtime.getRuntime().freeMemory();
        long nd = new Date().getTime();
        System.out.println("");
        System.out.println("search time: " + (nd-d) + " milliseconds");
        System.out.println("used memory: " + (memoryEnd-memoryStart)/1048576 + "Mb");
    }
}
