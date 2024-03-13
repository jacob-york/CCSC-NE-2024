package com.york.competition.c2021.zigZag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        List<int[]> cases = readUserInput();
        cases.stream()
                .map(Solution::processTestCase)
                .forEach(System.out::println);
    }

    public static List<int[]> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<int[]> returnVal = new ArrayList<>();

        String curLine = scanner.nextLine();
        while (!curLine.equals("quit")) {
            returnVal.add(Arrays.stream(curLine.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray());
            curLine = scanner.nextLine();
        }

        return returnVal;
    }

    public static String processTestCase(int[] testCase) {
        int prev = 0;
        for (int num : testCase) {
            if (num == 0) return "Zero Value";
            else if (sameSignAndGTZero(prev, num)) return "No";
            prev = num;
        }
        return "Yes";
    }

    public static boolean sameSignAndGTZero(int a, int b) {
        return (a > 0) == (b > 0);  // NOT(XOR(a, b))
    }
}
