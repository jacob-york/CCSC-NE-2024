package com.york.competition.c2017.question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public record Case(String key, String message) {}

    public static void main(String[] args) {
        List<Case> cases = readCases();
        cases.stream()
                .map(Solution::solveCase)
                .forEach(System.out::println);
    }

    public static List<Case> readCases() {
        List<Case> cases = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String current = scanner.nextLine();

        while (!current.equals("quit")) {
            cases.add(new Case(current, scanner.nextLine()));
            current = scanner.nextLine();
        }

        return cases;
    }

    public static String solveCase(Case case_) {
        StringBuilder stringBuilder = new StringBuilder();

        case_.message.chars()
                .map(c -> c < 'A' || c > 'Z' ? c : case_.key.charAt(c - 'A'))
                .mapToObj(c -> (char) c)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
