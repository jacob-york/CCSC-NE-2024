package com.york.competition.c2021.romanNumeral;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        List<String> cases = readUserInput();
        cases.stream()
                .map(Solution::solveCase)
                .forEach(System.out::println);
    }

    public static List<String> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<String> returnVal = new ArrayList<>();

        String curLine = scanner.nextLine();
        while (!curLine.equals("quit")) {
            returnVal.add(curLine);
            curLine = scanner.nextLine();
        }

        return returnVal;
    }

    public static int romanCharToInt(int ch) {
        return switch ((char) ch) {
            case 'M' -> 1000;
            case 'D' -> 500;
            case 'C' -> 100;
            case 'L' -> 50;
            case 'X' -> 10;
            case 'V' -> 5;
            case 'I' -> 1;
            default -> -1;
        };
    }

    public static int solveCase(String numerals) {
        int[] vals = numerals.chars().map(Solution::romanCharToInt).toArray();

        int total = 0;
        int prev = -1;
        for (int i = vals.length-1; i >= 0; i--) {
            total += prev > vals[i] ? -1 * vals[i] : vals[i];
            prev = vals[i];
        }
        return total;
    }
}
