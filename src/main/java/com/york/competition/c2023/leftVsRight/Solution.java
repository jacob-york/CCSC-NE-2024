package com.york.competition.c2023.leftVsRight;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Recursive Backtracking Solution:
 * - generates ascending permutations w/ backtracking,
 * - checks each one for the L-R condition,
 * - returns the first one it finds that satisfies it.
 *
 * @author jacob york
 */
public class Solution {

    public static StringBuilder current;

    public static String solveLine(String line) {

        // Line has to be inputted in ascending order for permutationHelper to hit them in a.o.
        char[] lineAscending = line.replaceAll("\\s", "").toCharArray();
        current = new StringBuilder();
        Arrays.sort(lineAscending);
        return findLRPermutation(lineAscending);
    }

    public static String findLRPermutation(char[] lineAscending) {
        char prev = 0;
        boolean atLeaf = true;

        for (int i = 0; i < lineAscending.length; i++) {
            char num = lineAscending[i];

            if (num != 0 && num != prev) {
                atLeaf = false;

                lineAscending[i] = 0;
                current.append(num);
                String result = findLRPermutation(lineAscending);
                if (result != null) return result;
                lineAscending[i] = num;
                current.deleteCharAt(current.length() - 1);
            }
            prev = num;
        }
        if (satisfiesLR() && atLeaf) return current.toString();
        else return null;
    }

    public static boolean satisfiesLR() {
        int left = 1;
        int right = 1;

        for (int i = 0; i < current.length(); i++) {
            int curNumber = Integer.parseInt(current.substring(i, i+1));
            int approxMid = current.length() / 2;

            // skips the center number if |int arr| is odd, since it goes into both and doesn't affect left > right.
            if (i < approxMid) left *= curNumber;
            else if (i > approxMid || current.length() % 2 == 0) right *= curNumber;
        }
        return left > right;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLines = Integer.parseInt(scanner.nextLine());
        String[] inputLines = new String[numLines];

        for (int i = 0; i < numLines; i++) {
            inputLines[i] = scanner.nextLine();
        }
        for (String line : inputLines) {
            System.out.println(solveLine(line));
        }
        System.out.println("done");
    }
}