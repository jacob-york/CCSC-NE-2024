package com.york.leetcode.medium.integerToRoman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        List<Integer> cases = readUserInput();
        cases.stream()
                .map(Solution::solveCase)
                .forEach(System.out::println);
    }

    public static List<Integer> readUserInput() {
        List<Integer> cases = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String currentLine = scanner.nextLine();
        while (!currentLine.equals("quit")) {
            cases.add(Integer.parseInt(currentLine));
            currentLine = scanner.nextLine();
        }

        return cases;
    }

    public static String intToRoman(int num) {
        return switch (num) {
            case 1 -> "I";
            case 5 -> "V";
            case 10 -> "X";
            case 50 -> "L";
            case 100 -> "C";
            case 500 -> "D";
            case 1000 -> "M";
            default -> "";
        };
    }

    public static String solveCaseHelper(int num) {
        if (num == 0) return "";
        if (num > 1000) return intToRoman(num).repeat(num / 1000);

        int curPos = 1;
        while (num >= (curPos*10)) {
            curPos *= 10;
        }

        if (num + curPos == curPos * 10) {
            return intToRoman(curPos) + intToRoman(curPos*10);
        } else if (num + curPos == curPos * 5) {
            return intToRoman(curPos) + intToRoman(curPos*5);
        } else if (num >= curPos * 5) {
            return intToRoman(curPos * 5) +
                    intToRoman(curPos).repeat((num-curPos*5) / curPos);
        } else {
            return intToRoman(curPos).repeat(num / curPos);
        }
    }

    public static List<Integer> divisions(int testCase) {
        List<Integer> returnVal =  new ArrayList<>();
        int curCase = testCase;
        for (int p = 1; (curCase > 0); p++) {
            int pos = curCase % (int) Math.pow(10, p);
            curCase -= pos;
            returnVal.add(0, pos);
        }
        return returnVal;
    }

    public static String solveCase(int testCase) {
        return divisions(testCase)
                .stream()
                .map(Solution::solveCaseHelper)
                .collect(Collectors.joining());
    }
}
