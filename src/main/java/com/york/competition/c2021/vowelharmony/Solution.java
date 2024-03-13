package com.york.competition.c2021.vowelharmony;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        List<char[]> cases = readUserInput();
        cases.stream()
                .map(Solution::processCase)
                .forEach(System.out::println);
    }

    public static List<char[]> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<char[]> returnVal = new ArrayList<>();

        String curLine = scanner.nextLine();
        while (!curLine.equals("quit")) {
            returnVal.add(curLine.toCharArray());
            curLine = scanner.nextLine();
        }

        return returnVal;
    }

    public static String processCase(char[] testCase) {
        String vowels = "aeiouAEIOU";
        String upper = "AEIOU";
        List<Character> chars = new ArrayList<>();

        for (int i = 0; i < testCase.length; i++) {
            if (vowels.contains(testCase[i]+"")) {
                chars.add(Character.toLowerCase(testCase[i]));
            }
        }
        chars.sort(Comparator.naturalOrder());

        for (int i = 0; i < testCase.length; i++) {
            if (vowels.contains(testCase[i]+"")) {
                testCase[i] = upper.contains(testCase[i]+"") ?
                        Character.toUpperCase(chars.remove(0)) : chars.remove(0);
            }
        }

        return new String(testCase);
    }
}
