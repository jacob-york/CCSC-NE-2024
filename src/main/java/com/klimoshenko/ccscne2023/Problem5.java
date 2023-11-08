package com.klimoshenko.ccscne2023;

import java.util.*;

public class Problem5 {
    public static void main(String[] args) {
        List<List<Integer>> inputs = readTestCases();

        inputs.stream()
                .map(Problem5::processTestCase)
                .forEach(System.out::println);
        System.out.println("done");
    }

    private static int processTestCase(List<Integer> testCase) {
        return permutations(testCase).stream()
                .filter(list -> {
                    boolean odd = list.size() % 2 == 1;
                    List<Integer> left = list.subList(0, list.size() / 2);
                    List<Integer> right = odd
                            ? list.subList(list.size() / 2 + 1, list.size())
                            : list.subList(list.size() / 2, list.size());

                    return product(left) > product(right);
                })
                .map(Problem5::asNumber)
                .min(Comparator.naturalOrder())
                .get();
    }

    private static int product(List<Integer> list) {
        return list.stream().reduce((a, b) -> a * b).get();
    }

    private static int asNumber(List<Integer> list) {
        return list.stream().reduce(0, (acc, digit) -> 10 * acc + digit);
    }

    private static List<List<Integer>> permutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> available = new ArrayList<>(list);
        List<Integer> buffer = new ArrayList<>();
        permutationsHelper(result, available, buffer);

        return result;
    }

    private static void permutationsHelper(List<List<Integer>> result, List<Integer> available, List<Integer> buffer) {
        if (available.isEmpty()) {
            result.add(new ArrayList<>(buffer));
            return;
        }

        for (int i = 0; i < available.size(); i++) {
            int n = available.get(i);
            available.remove(i);
            buffer.add(n);
            permutationsHelper(result, available, buffer);
            buffer.remove(buffer.size() - 1);
            available.add(i, n);
        }
    }

    private static List<List<Integer>> readTestCases() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<List<Integer>> result = new ArrayList<>();

            int numTestCases = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numTestCases; i++) {
                String line = scanner.nextLine();
                List<Integer> nums = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList();

                result.add(nums);
            }

            return result;
        }
    }
}
