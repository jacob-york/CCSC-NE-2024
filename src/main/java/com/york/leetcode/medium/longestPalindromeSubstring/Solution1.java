package com.york.leetcode.medium.longestPalindromeSubstring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        List<String> cases = readUserInput();
        cases.stream()
                .map(Solution1::longestPalindrome)
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

    public static String searchForPalindrome(String str, int size) {
        for (int i = 0; (size+i) <= str.length(); i++) {
            String maybePalindrome = str.substring(i, size+i);
            if (isPalindrome(maybePalindrome)) return maybePalindrome;
        }
        return null;
    }

    public static boolean isPalindrome(String str) {
        int minApprox = str.length() / 2;
        String left = str.substring(0, minApprox);
        String right = str.substring(str.length() % 2 == 0 ? minApprox : minApprox + 1);
        return left.equals(new StringBuilder(right).reverse().toString());
    }

    public static String longestPalindrome(String s) {
        for (int i = s.length(); i > 1; i--) {
            String maybePalindrome = searchForPalindrome(s, i);
            if (maybePalindrome != null) return maybePalindrome;
        }
        return String.valueOf(s.charAt(0));
    }
}
