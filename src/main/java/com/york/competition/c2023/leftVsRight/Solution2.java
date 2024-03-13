package com.york.competition.c2023.leftVsRight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Solution2 {

    public static void main(String[] args) {
        List<List<Integer>> cases = readUserInput();

        cases.stream()
                .map(Solution2::solveCase)
                .forEach(System.out::println);
    }

    private static int solveCase(List<Integer> case_) {
        List<List<Integer>> permutations = getPermutations(case_);
        return permutations
                        .stream()
                        .filter(Solution2::satisfiesLeftRight)
                        .mapToInt(intList -> intList
                                        .stream()
                                        .reduce(0, (acc, digit) -> 10 * acc + digit)
                        )
                        .min()
                        .getAsInt();
    }

    private static boolean satisfiesLeftRight(List<Integer> integer) {
        int minApprox = (integer.size() / 2);
        int lowerBound = integer.size() % 2 == 0 ? minApprox : minApprox + 1;
        List<Integer> left = integer.subList(0, minApprox);
        List<Integer> right = integer.subList(lowerBound, integer.size());

        return product(left) > product(right);
    }

    private static int product(List<Integer> integers) {
        return integers.stream().reduce(1, (a, b) -> a * b);
    }

    private static List<List<Integer>> getPermutations(List<Integer> case_) {
        List<List<Integer>> permutations = new ArrayList<>();
        dfs(permutations, new ArrayList<>(case_), new ArrayList<>());
        return permutations;
    }

    private static void dfs(List<List<Integer>> permutations, List<Integer> available, List<Integer> current) {
        if (available.size() == current.size()) {
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < available.size(); i++) {
            if (available.get(i) == null) continue;
            int elem = available.get(i);

            current.add(elem);
            available.set(i, null);
            dfs(permutations, available, current);
            current.remove(current.size() - 1);
            available.set(i, elem);
        }
    }

    private static List<List<Integer>> readUserInput() {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> cases = new ArrayList<>();

        int numCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numCases; i++) {
            String rawString = scanner.nextLine();
            List<Integer> case_ = Arrays
                    .stream(rawString.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed().toList();
            cases.add(case_);
        }

        return cases;
    }
}
