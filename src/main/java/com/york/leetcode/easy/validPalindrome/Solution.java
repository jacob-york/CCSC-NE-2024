package com.york.leetcode.easy.validPalindrome;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        List<String> cases = readUserInput();
            cases.stream()
                    .map(Solution::isPalindrome)
                    .forEach(System.out::println);
    }

    public static List<String> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<String> cases = new ArrayList<>();

        String current = scanner.nextLine();
        while (!current.equals("-1")) {
            cases.add(current);
            current = scanner.nextLine();
        }
        return cases;
    }

    public static String applyFilter(String s) {
        return s.chars()
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }
    public static boolean isPalindrome(String s) {
        if (s.length() < 2) return true;
        String si = applyFilter(s);
        if (si.length() < 2) return true;
        return si.charAt(0) == si.charAt(si.length()-1) && palHelper(si.substring(1, si.length()-1));
    }
    public static boolean palHelper(String s) {
        if (s.length() < 2) return true;
        return s.charAt(0) == s.charAt(s.length()-1) && palHelper(s.substring(1, s.length()-1));
    }
}
